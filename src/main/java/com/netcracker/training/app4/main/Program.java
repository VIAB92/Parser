package com.netcracker.training.app4.main;

import com.netcracker.training.app4.controller.MainController;
import com.netcracker.training.app4.db.manager.DBException;
import com.netcracker.training.app4.productparser.ProductNotFoundException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @VIAB
 */
public class Program {
    Logger logger = Logger.getLogger(Program.class);
    public static void main(String[] args) throws DBException, ProductNotFoundException, IOException {
        BasicConfigurator.configure();

        MainController controller = new MainController();
        controller.loadUrls();
        controller.loadProperties();
        controller.beginParserWorking();

    }
}
