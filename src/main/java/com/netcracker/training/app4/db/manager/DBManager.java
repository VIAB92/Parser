package com.netcracker.training.app4.db.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * создано на основе материалов задания 2
 */
public interface DBManager {
    public void executeUpdate(String sql) throws DBException;

    public ResultSet execSQL(String sql) throws DBException;

    public PreparedStatement prepareStatement(String sql) throws DBException;

    public void close() throws DBException;
}
