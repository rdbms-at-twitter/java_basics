import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import software.amazon.jdbc.PropertyDefinition;
import software.amazon.jdbc.plugin.failover.FailoverFailedSQLException;
import software.amazon.jdbc.plugin.failover.FailoverSuccessSQLException;
import software.amazon.jdbc.plugin.failover.TransactionStateUnknownSQLException;

public class MyFailoverSample  {

  // User configures connection properties here
  public static final String MYSQL_CONNECTION_STRING =
      "jdbc:aws-wrapper:mysql://aurora-sample.cluster-cpdziwfew7pa.us-east-1.rds.amazonaws.com:3306/poc";
  private static final String USERNAME = "sampleuser";
  private static final String PASSWORD = "password";

  public static void main(String[] args) throws SQLException {

    final Properties props = new Properties();

    // Enable failover plugin and set properties
    props.setProperty(PropertyDefinition.PLUGINS.name, "failover");
    props.setProperty(PropertyDefinition.USER.name, USERNAME);
    props.setProperty(PropertyDefinition.PASSWORD.name, PASSWORD);

    // Setup Step: Open connection and create tables - uncomment this section to create table and test values
    // try (final Connection connection = DriverManager.getConnection(POSTGRESQL_CONNECTION_STRING, props)) {
    //   setInitialSessionSettings(connection);
    //   updateQueryWithFailoverHandling(connection,
    //       "CREATE TABLE bank_test (name varchar(40), account_balance int)");
    //   updateQueryWithFailoverHandling(connection,
    //       "INSERT INTO bank_test VALUES ('Jane Doe', 200), ('John Smith', 200)");
    // }

    // Transaction Step: Open connection and perform transaction
    try (final Connection conn = DriverManager.getConnection(MYSQL_CONNECTION_STRING, props)) {
      setInitialSessionSettings(conn);
      // Begin business transaction
      conn.setAutoCommit(false);

      // Example business transaction
      updateQueryWithFailoverHandling(conn,
          "insert into t_tracking(note) values(concat('Insert from connector/j by ',user(),' to ',@@aurora_server_id))");
      //updateQueryWithFailoverHandling(conn,
         // "select count(*) from t_tracking");

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
      stmt.executeUpdate(query);
    } catch (FailoverFailedSQLException e) {
      // Connection failed, and JDBC wrapper failed to reconnect to a new instance.
      throw e;
    } catch (FailoverSuccessSQLException e) {
      // Query execution failed and JDBC wrapper successfully failed over to a new elected writer node.
      // Reconfigure the connection
      setInitialSessionSettings(conn);
      // Re-run query
      try (Statement stmt = conn.createStatement()) {
        stmt.executeUpdate(query);
      }
      return;
    } catch (TransactionStateUnknownSQLException e) {
      // Connection failed while executing a business transaction.
      // Transaction status is unknown. The driver has successfully reconnected to a new writer.
      throw e;
    }
  }
}

