import com.zaxxer.hikari.SQLExceptionOverride;
import java.sql.SQLException;
import software.amazon.jdbc.util.SqlState;

public class HikariCPSQLException implements SQLExceptionOverride {
    public HikariCPSQLException() {}

    public SQLExceptionOverride.Override adjudicate(final SQLException sqlException) {
        String sqlState = sqlException.getSQLState();
        if (sqlState.equalsIgnoreCase(SqlState.COMMUNICATION_LINK_CHANGED.getState()) ||
            sqlState.equalsIgnoreCase(SqlState.CONNECTION_FAILURE_DURING_TRANSACTION.getState())) {
            return SQLExceptionOverride.Override.DO_NOT_EVICT;
        } else {
            return SQLExceptionOverride.Override.CONTINUE_EVICT;
        }
    }
}

