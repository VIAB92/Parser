package com.netcracker.training.app4.productparser;

import java.io.IOException;
import java.util.List;

/**
 * @VIAB
 */
public interface ProductParser {
    public void parseProducts() throws IOException, ProductNotFoundException;
    public List<String> getProductNames();
    public List<String> getProductDetails();
    public List<Integer> getProductPrices();

}
