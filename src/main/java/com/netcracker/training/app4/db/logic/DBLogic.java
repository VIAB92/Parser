package com.netcracker.training.app4.db.logic;

import com.netcracker.training.app4.db.manager.DBException;
import com.netcracker.training.app4.entity.Product;

/**
 * @VIAB
 */
public interface DBLogic {
    public void insertOrUpdateProduct(Product product) throws DBException;
    public void close() throws DBException;
}
