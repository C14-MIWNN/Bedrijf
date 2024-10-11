package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Vincent Velthuizen
 * Bevat veel voorkomende DAO-code zodat die niet herhaald hoeft te worden
 */
public abstract class AbstractDAO {

    private final DBaccess dBaccess;
    private PreparedStatement preparedStatement;

    public AbstractDAO(DBaccess dBaccess) {
        this.dBaccess = dBaccess;
    }

    protected void setupPreparedStatement(String sql) {
        try {
            preparedStatement = dBaccess.getConnection().prepareStatement(sql);
        } catch (SQLException sqlException) {
            sqlExceptionWarning(sqlException);
        }
    }

    protected void setupPreparedStatementWithKey(String sql) {
        try {
            preparedStatement = dBaccess.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException sqlException) {
            sqlExceptionWarning(sqlException);
        }
    }

    protected void executeManipulateStatement() {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlExceptionWarning(sqlException);
        }
    }

    protected ResultSet executeSelectStatement() {
        try {
            return preparedStatement.executeQuery();
        } catch (SQLException sqlException) {
            sqlExceptionWarning(sqlException);
            throw new RuntimeException(sqlException);
        }
    }

    protected int executeInsertStatementWithKey() {
        int gegenereerdeSleutel = 0;

        try {
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                gegenereerdeSleutel = resultSet.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlExceptionWarning(sqlException);
            throw new RuntimeException(sqlException);
        }
        return gegenereerdeSleutel;
    }

    public void sqlExceptionWarning(SQLException sqlException) {
        System.err.println("SQL fout: " + sqlException.getMessage());
    }

    protected PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
}
