package com.netcracker.training.app4.manager;

import com.netcracker.training.app4.entity.Product;
import com.netcracker.training.app4.productparser.ProductNotFoundException;
import com.netcracker.training.app4.productparser.ProductParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @VIAB
 */
public class ProductManager {
    private List<String> productNames;
    private List<String> productDetails;
    private List<Integer> productPrices;

    private ProductParser parser;
    private String productType;
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public ProductManager(ProductParser pp, String productType)
    {
        this.parser = pp;
        this.productType=productType;
    }

    public void makeProducts() throws IncorrectListSizeException, IOException, ProductNotFoundException {
        parser.parseProducts();
        this.productNames=parser.getProductNames();
        this.productDetails=parser.getProductDetails();
        this.productPrices=parser.getProductPrices();
        formProductList();
    }

    private void formProductList() throws IncorrectListSizeException {
        if (productNames.size()==productDetails.size() && productNames.size()==productPrices.size())
        {
            products = new ArrayList<Product>();
            int size = productNames.size();
            for (int i=0; i<size; i++)
            {
                Product product = new Product();
                product.setName(productNames.get(i));
                product.setDetails(productDetails.get(i));
                product.setType(productType);
                product.setPrice(productPrices.get(i));
                products.add(product);
            }
        }
        else
        {
            throw new IncorrectListSizeException("Parsed list sizes are not equal!", new Exception());
        }
    }

}
