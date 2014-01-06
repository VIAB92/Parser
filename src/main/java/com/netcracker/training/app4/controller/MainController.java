package com.netcracker.training.app4.controller;

import com.netcracker.training.app4.db.logic.*;
import com.netcracker.training.app4.db.manager.*;
import com.netcracker.training.app4.productparser.IncorrectListSizeException;
import com.netcracker.training.app4.productparser.*;
import com.netcracker.training.app4.property.*;
import com.netcracker.training.app4.reader.*;
import com.netcracker.training.app4.urlparser.*;
import org.apache.log4j.Logger;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @VIAB
 */
public class MainController {
    private static final String FILE_NAME = "url.txt";
    private static final String PROPERTY_FILE_NAME = "program.property";
    private List<String> urls;
    private Description description;
    private DBLogic dbLogic;
    private DBManager dbManager;
    private ProductParser productParser;
    private UrlParser urlParser;
    private ParserController parserController;

    Logger logger = Logger.getLogger(MainController.class);

    public MainController(){}

    public void loadUrls()
    {
        TextFileReader reader = new TextFileReader();
        try {
            urls = reader.readFile(FILE_NAME);
        } catch (FileNotFoundException e) {
            logger.error("URL file not found", e);
            System.exit(1);
        }
    }

    public void loadProperties()
    {
        try
        {
            PropertyReader propertyReader = new PropertyReader(PROPERTY_FILE_NAME);
            this.description = propertyReader.readProperty();
        }catch(FileNotFoundException ex)
        {
            logger.error("Property file not found", ex);
            System.exit(1);
        }
        catch (IOException e)
        {
            logger.error("An error occurred while reading a property file", e);
            System.exit(1);
        }
    }

    public void beginParserWorking()
    {
        try
        {
            dbManager = new OracleDBManager(description.getConnection(), description.getUserId(), description.getPwd());
            dbLogic = new OracleDBLogic(dbManager);
            int i = 0;
            for(String url : urls)
            {
                logger.info("============================================");
                logger.info("URL номер "+(++i)+", настала твоя очередь!");
                logger.info("============================================");
                productParser = new RozetkaProductParser(url, description.getNameClass(), description.getDescriptionClass(), description.getPriceClass());
                urlParser = new ProductTypeParser(url);
                parserController = new ParserController(urlParser, productParser, dbLogic);
                try
                {
                    parserController.parseProducts();
                }
                catch(IncorrectListSizeException e)
                {
                    logger.error("Incorrect sizes were sent from the url", e);

                }
                catch (IOException ex)
                {
                    logger.error("Error while parsing url", ex);
                }
                catch (ProductNotFoundException exception)
                {
                    logger.error("Products weren't found while pasring this url", exception);
                }

            }
            dbManager.close();
        } catch(DBException ex)
        {
            logger.error("Error working with Database", ex);
            System.exit(1);
        }

    }


}
