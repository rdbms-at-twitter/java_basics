import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import software.amazon.jdbc.PropertyDefinition;
import software.amazon.jdbc.plugin.failover.FailoverFailedSQLException;
import software.amazon.jdbc.plugin.failover.FailoverSuccessSQLException;
import software.amazon.jdbc.plugin.failover.TransactionStateUnknownSQLException;
import software.amazon.jdbc.util.SqlState;

public class MyFailoverWHikariCP {

    // User configures connection properties here
    public static final String MYSQL_CONNECTION_STRING =
            "jdbc:aws-wrapper:mysql://sample-jdbc-wrapper.cluster-cpdziwfew7pa.us-east-1.rds.amazonaws.com:3306/poc";
    private static final String USERNAME = "sampleuser";
    private static final String PASSWORD = "password";

    private static HikariDataSource ds;

    private static HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(MYSQL_CONNECTION_STRING);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(10); // コネクションプールの最大数を10に設定
        config.setExceptionOverrideClassName("HikariCPSQLException"); // HikariCPSQLExceptionクラスを指定

        return new HikariDataSource(config);
    }

    public static void main(String[] args) throws SQLException {
        ds = createDataSource();

        final Properties props = new Properties();

        // プロパティの設定を変更
        props.setProperty("failoverWriterReconnectIntervalMs", "1000"); // 値を変更
        props.setProperty("failoverTimeoutMs", "60000"); // 値を変更
        props.setProperty("failoverReaderConnectTimeoutMs", "20000"); // 値を変更
        props.setProperty("failoverClusterTopologyRefreshRateMs", "1000"); // 値を変更
        props.setProperty("enableGreenNodeReplacement","true"); // enableGreenNodeReplacement

        // Enable failover plugin and set properties
        props.setProperty(PropertyDefinition.PLUGINS.name, "failover");
        props.setProperty(PropertyDefinition.USER.name, USERNAME);
        props.setProperty(PropertyDefinition.PASSWORD.name, PASSWORD);

        //try (final Connection conn = ds.getConnection()) {
        //    setInitialSessionSettings(conn);
        //    // Begin business transaction
        //    conn.setAutoCommit(false);

            // 3000回のINSERTを実行
        //    for (int i = 0; i < 3000; i++) {
        //        updateQueryWithFailoverHandling(conn,
        //                "insert into t_tracking(note) values(concat('Insert from connector/j by ',user(),' to ',@@aurora_server_id))");
        //        updateQueryWithFailoverHandling(conn,
        //                 "select count(*),@@aurora_server_id,now() from t_tracking");
        //        Thread.sleep(500); // 0.5秒待機
        //    }

            // Commit business transaction
        //    updateQueryWithFailoverHandling(conn, "commit");
        try (final Connection conn = ds.getConnection()) {
        setInitialSessionSettings(conn);
        // Begin business transaction
        conn.setAutoCommit(false); // オートコミットをオフにする

        // 3000回のINSERTを実行
        for (int i = 0; i < 3000; i++) {
        updateQueryWithFailoverHandling(conn,
                "insert into t_tracking(note) values(concat('Insert from connector/j by ',user(),' to ',@@aurora_server_id))");
        updateQueryWithFailoverHandling(conn, "commit"); // INSERTごとにCOMMITする
        updateQueryWithFailoverHandling(conn,
                "select count(*),@@aurora_server_id,now() from t_tracking");
        Thread.sleep(500); // 0.5秒待機
         }

        } catch (FailoverFailedSQLException e) {
            // User application should open a new connection, check the results of the failed transaction and re-run it if
            // needed. See:
            // https://github.com/awslabs/aws-advanced-jdbc-wrapper/blob/main/docs/using-the-jdbc-driver/using-plugins/UsingTheFailoverPlugin.md#08001---unable-to-establish-sql-connection
            throw e;
        } catch (TransactionStateUnknownSQLException e) {
            // User application should check the status of the failed transaction and restart it if needed. See:
            // https://github.com/awslabs/aws-advanced-jdbc-wrapper/blob/main/docs/using-the-jdbc-driver/using-plugins/UsingTheFailoverPlugin.md#08007---transaction-resolution-unknown
            // 新しいコネクションを取得し、トランザクションを再試行する
            retryQueryWithFreshConnection(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ds.close(); // コネクションプールを閉じる
        }
    }

    public static void setInitialSessionSettings(Connection conn) throws SQLException {
        try (Statement stmt1 = conn.createStatement()) {
            // User can edit settings
            stmt1.executeUpdate("SET NAMES utf8mb4");
        }
    }

    public static void updateQueryWithFailoverHandling(Connection conn, String query) throws SQLException, InterruptedException {
    int retryCount = 0;
    final int MAX_RETRIES = 1000; // 適切な値に変更してください

    while (retryCount < MAX_RETRIES) {
        try (Statement stmt = conn.createStatement()) {
            // クエリがSELECT文の場合はexecuteQuery()を使用する
            if (query.trim().toLowerCase().startsWith("select")) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    // 結果セットの処理
                    printResultSet(rs);
                }
            } else {
                stmt.executeUpdate(query);
            }
            // 正常に実行できた場合は、ループを抜ける
            break;
        } catch (FailoverFailedSQLException e) {
            // Connection failed, and JDBC wrapper failed to reconnect to a new instance.
            throw e;
        } catch (FailoverSuccessSQLException e) {
            // Query execution failed and JDBC wrapper successfully failed over to a new elected writer node.
            // Reconfigure the connection
            setInitialSessionSettings(conn);
            // リトライ回数をインクリメント
            retryCount++;
            // 0.5秒待機
            Thread.sleep(500);
        } catch (TransactionStateUnknownSQLException e) {
            // Connection failed while executing a business transaction.
            // Transaction status is unknown. The driver has successfully reconnected to a new writer.
            // 新しいコネクションを取得し、トランザクションを再試行する
            retryQueryWithFreshConnection(e);
            // ループを抜ける
            break;
        } catch (SQLException e) {
            // 読み取り専用モードに関するエラーの場合はリトライする
            if (e.getMessage().contains("The MySQL server is running with the --read-only option")) {
                // リトライ回数をインクリメント
                retryCount++;
                // 0.5秒待機
                Thread.sleep(500);
            } else {
                // 想定外の例外はそのままスロー
                throw e;
            }
        }
     }

    // 最大リトライ回数に達した場合は例外をスロー
    if (retryCount == MAX_RETRIES) {
        throw new SQLException("Maximum number of retries (" + MAX_RETRIES + ") exceeded for query: " + query);
    }
  }



    private static void retryQueryWithFreshConnection(TransactionStateUnknownSQLException e) throws SQLException {
        try (Connection freshConn = ds.getConnection()) {
            // 新しいコネクションを取得し、セッション設定を行う
            setInitialSessionSettings(freshConn);
            freshConn.setAutoCommit(false);
            // トランザクションの再実行処理を記述する
            // ...
            freshConn.commit();
            freshConn.close(); // 新しいコネクションをクローズ
        } catch (SQLException ex) {
            // 再試行に失敗した場合の処理を記述する
            // ...
            throw ex;
        }
    }

    private static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        // カラム名を表示
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) System.out.print(",  ");
            System.out.print(rsmd.getColumnName(i));
        }
        System.out.println();

        // 行データを表示
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
    }
}

