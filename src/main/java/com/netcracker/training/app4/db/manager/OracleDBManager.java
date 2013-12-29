package com.netcracker.training.app4.db.manager;

import java.sql.*;
import java.util.Locale;

/**
 * @VIAB
 */
public class OracleDBManager implements DBManager {

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    private Connection connection = null;
    private Statement statement = null;

    public OracleDBManager(String url, String user, String password) throws DBException {
        try
        {
            Class.forName(DRIVER).newInstance();
            createConnection(url, user, password);
            Locale.setDefault(Locale.ENGLISH);
        }catch (ClassNotFoundException ex) {
            throw new RuntimeException("Class " + DRIVER + " not found", ex);
        } catch (InstantiationException ex) {
            throw new RuntimeException("Driver " + DRIVER + " class InstantiationException.", ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException("IllegalAccess for " + DRIVER + " class.", ex);
        }

    }

    private void createConnection(String url, String user, String password) throws DBException {
        Locale.setDefault(Locale.ENGLISH);
        try
        {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        }
        catch (SQLException ex)
        {
            throw new DBException(ex.getMessage(), ex);
        }
    }

    @Override
    public void executeUpdate(String sql) throws DBException {
        try
        {
            statement.executeUpdate(sql);
        }catch (SQLException ex)
        {
            throw new DBException(ex.getMessage(), ex);
        }
    }

    @Override
    public ResultSet execSQL(String sql) throws DBException {
        try
        {
            return statement.executeQuery(sql);
        } catch (SQLException ex)
        {
            throw new DBException(ex.getMessage(), ex);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws DBException {
        try
        {
            return connection.prepareStatement(sql);
        } catch (SQLException ex)
        {
            throw new DBException(ex.getMessage(), ex);
        }
    }

    @Override
    public void close() throws DBException {
        closeConnection();
    }

    private void closeConnection() throws DBException {
        try
        {
            if (statement != null)
            {
                statement.close();
            }
            if (connection != null)
            {
                connection.close();
            }
        } catch (SQLException ex)
        {
            throw new DBException(ex.getMessage(), ex);
        }
    }
}
