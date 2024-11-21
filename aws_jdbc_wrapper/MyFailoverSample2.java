import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import software.amazon.jdbc.PropertyDefinition;
import software.amazon.jdbc.plugin.failover.FailoverFailedSQLException;
import software.amazon.jdbc.plugin.failover.FailoverSuccessSQLException;
import software.amazon.jdbc.plugin.failover.TransactionStateUnknownSQLException;

public class MyFailoverSample2 {

    // User configures connection properties here
    public static final String MYSQL_CONNECTION_STRING =
            "jdbc:aws-wrapper:mysql://jdbc-wrapper-v3052.cluster-cpdziwfew7pa.us-east-1.rds.amazonaws.com:3306/poc";
    private static final String USERNAME = "user_sample";
    private static final String PASSWORD = "password";

    public static void main(String[] args) throws SQLException {

        final Properties props = new Properties();

        // プロパティの設定を変更
        props.setProperty("failoverWriterReconnectIntervalMs", "2000"); // 値を変更
        props.setProperty("failoverTimeoutMs", "30000"); // 値を変更
        props.setProperty("failoverReaderConnectTimeoutMs", "10000"); // 値を変更
        props.setProperty("failoverClusterTopologyRefreshRateMs","2000"); // 値を変更
        // props.setProperty("enableGreenNodeReplacement","true"); // enableGreenNodeReplacement

        // Enable failover plugin and set properties
        props.setProperty(PropertyDefinition.PLUGINS.name, "failover");
        props.setProperty(PropertyDefinition.USER.name, USERNAME);
        props.setProperty(PropertyDefinition.PASSWORD.name, PASSWORD);

        // Transaction Step: Open connection and perform transaction
        try (final Connection conn = DriverManager.getConnection(MYSQL_CONNECTION_STRING, props)) {
            setInitialSessionSettings(conn);
            // Begin business transaction
            conn.setAutoCommit(false);

            // Example business transaction
            updateQueryWithFailoverHandling(conn,
                    "insert into t_tracking(note) values(concat('Insert from connector/j by ',user(),' to ',@@aurora_server_id))");
            updateQueryWithFailoverHandling(conn,
                    "select count(*),@@aurora_server_id,now() from t_tracking");

            // Commit business transaction
            updateQueryWithFailoverHandling(conn, "commit");
        } catch (FailoverFailedSQLException e) {
            // User application should open a new connection, check the results of the failed transaction and re-run it if
            // needed. See:
            // https://github.com/awslabs/aws-advanced-jdbc-wrapper/blob/main/docs/using-the-jdbc-driver/using-plugins/UsingTheFailoverPlugin.md#08001---unable-to-establish-sql-connection
            throw e;
        } catch (TransactionStateUnknownSQLException e) {
            // User application should check the status of the failed transaction and restart it if needed. See:
            // https://github.com/awslabs/aws-advanced-jdbc-wrapper/blob/main/docs/using-the-jdbc-driver/using-plugins/UsingTheFailoverPlugin.md#08007---transaction-resolution-unknown
            throw e;
        } catch (SQLException e) {
            // Unexpected exception unrelated to failover. This should be handled by the user application.
            throw e;
        }
    }

    public static void setInitialSessionSettings(Connection conn) throws SQLException {
        try (Statement stmt1 = conn.createStatement()) {
            // User can edit settings
            stmt1.executeUpdate("SET NAMES utf8mb4");

        }
    }

    public static void updateQueryWithFailoverHandling(Connection conn, String query) throws SQLException {
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
        } catch (FailoverFailedSQLException e) {
            // Connection failed, and JDBC wrapper failed to reconnect to a new instance.
            throw e;
        } catch (FailoverSuccessSQLException e) {
            // Query execution failed and JDBC wrapper successfully failed over to a new elected writer node.
            // Reconfigure the connection
            setInitialSessionSettings(conn);
            // Re-run query
            updateQueryWithFailoverHandling(conn, query);
            return;
        } catch (TransactionStateUnknownSQLException e) {
            // Connection failed while executing a business transaction.
            // Transaction status is unknown. The driver has successfully reconnected to a new writer.
            throw e;
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

