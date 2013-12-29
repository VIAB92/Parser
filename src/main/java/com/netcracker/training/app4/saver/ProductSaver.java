package com.netcracker.training.app4.saver;

import com.netcracker.training.app4.entity.Product;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @VIAB
 */
public interface ProductSaver {
    public void  saveProductList(List<Product> products) throws FileNotFoundException;
}
