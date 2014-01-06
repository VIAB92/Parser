package com.netcracker.training.app4.productparser;

import com.netcracker.training.app4.entity.Product;

import java.io.IOException;
import java.util.List;

/**
 * @VIAB
 */
public interface ProductParser {
    public List<Product> parseProducts() throws IOException, ProductNotFoundException, IncorrectListSizeException;
    public void setProductType(String productType);

}
