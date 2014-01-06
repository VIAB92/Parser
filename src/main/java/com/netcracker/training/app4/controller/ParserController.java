package com.netcracker.training.app4.controller;

import com.netcracker.training.app4.db.logic.DBLogic;
import com.netcracker.training.app4.db.manager.DBException;
import com.netcracker.training.app4.entity.Product;
import com.netcracker.training.app4.productparser.IncorrectListSizeException;
import com.netcracker.training.app4.productparser.ProductNotFoundException;
import com.netcracker.training.app4.productparser.ProductParser;
import com.netcracker.training.app4.saver.ProductSaver;
import com.netcracker.training.app4.saver.XmlProductSaver;
import com.netcracker.training.app4.urlparser.UrlParser;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @VIAB
 */
public class ParserController {
    Logger logger = Logger.getLogger(ParserController.class);
    private static final String PARSER_FLAG = "com.ua/";
    private UrlParser urlParser;
    private ProductParser productParser;
    private DBLogic dbLogic;


    public ParserController(UrlParser urlParser, ProductParser productParser, DBLogic dbLogic)
    {
        this.urlParser = urlParser;
        this.productParser = productParser;
        this.dbLogic = dbLogic;
    }

    public void parseProducts() throws IncorrectListSizeException, IOException, ProductNotFoundException, DBException {
        urlParser.parseType(PARSER_FLAG);
        String productType = urlParser.getProductType();
        productParser.setProductType(productType);
        List<Product> productList = productParser.parseProducts();
        logger.info("Продукция со страницы получена: "+productList.size());
        writeProductToDb(productList);
        logger.info("Продукция записана в базу данных");
        writeProductToXml(productList);
        logger.info("Продукция сохранена в XML согласно текущей дате");
    }

    private void writeProductToXml(List<Product> productList) throws FileNotFoundException {
        ProductSaver productSaver = new XmlProductSaver();
        productSaver.saveProductList(productList);
    }

    private void writeProductToDb(List<Product> productList) throws DBException {
        for (Product product:productList)
        {
            dbLogic.insertOrUpdateProduct(product);
        }
    }
}
