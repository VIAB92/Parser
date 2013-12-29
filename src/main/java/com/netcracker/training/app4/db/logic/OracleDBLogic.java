package com.netcracker.training.app4.db.logic;

import com.netcracker.training.app4.db.manager.DBException;
import com.netcracker.training.app4.db.manager.DBManager;
import com.netcracker.training.app4.entity.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @VIAB
 */
public class OracleDBLogic implements DBLogic {
    protected static final String UPDATE_PRODUCT_SQL = "UPDATE rozetka_products SET characteristics = ?, price = ? WHERE product_name = ? AND product_type = ?";
    protected static final String INSERT_PRODUCT_SQL = "INSERT INTO rozetka_products VALUES(products.NEXTVAL, ?, ?, ?, ?)";

    private DBManager dbManager;

    public OracleDBLogic(DBManager dbManager)
    {
        this.dbManager=dbManager;
    }

    @Override
    public void insertOrUpdateProduct(Product product) throws DBException {
        if (updateProduct(product) == 0)
        {
            insertProduct(product);
        }
    }

    @Override
    public void close() throws DBException {
        dbManager.close();
    }

    private int updateProduct(Product product) throws DBException {
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = dbManager.prepareStatement(UPDATE_PRODUCT_SQL);
            preparedStatement.setString(1, product.getDetails().substring(0, product.getDetails().length()/2));
            preparedStatement.setInt(2, (Integer) product.getPrice());
            preparedStatement.setString(3, product.getName().substring(0, product.getName().length()/2));
            preparedStatement.setString(4, product.getType());

            return preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            throw new DBException(ex.getMessage(), ex);
        } finally {
            close(preparedStatement);
        }

    }

    private void insertProduct(Product product) throws DBException {
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = dbManager.prepareStatement(INSERT_PRODUCT_SQL);
            preparedStatement.setString(1, product.getType());
            preparedStatement.setString(2, product.getName().substring(0, product.getName().length()/2));
            preparedStatement.setString(3, product.getDetails().substring(0, product.getDetails().length()/2));
            preparedStatement.setInt(4, (Integer) product.getPrice());

            preparedStatement.executeUpdate();

        }catch (SQLException ex) {
            throw new DBException(ex.getMessage(), ex);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    throw new DBException(ex.getMessage(), ex);
                }
            }
        }
    }

    private void close(Statement statement) throws DBException
    {
        if (statement != null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage(), e);
            }
        }

    }
}
