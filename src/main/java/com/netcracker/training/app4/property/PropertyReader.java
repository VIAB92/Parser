package com.netcracker.training.app4.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @VIAB
 */
public class PropertyReader {
    private FileInputStream fin;

    public PropertyReader(String path) throws FileNotFoundException {
        this.fin = new FileInputStream(path);
    }

    public Description readProperty() throws IOException {
        Properties prop = new Properties();
        prop.load(fin);
        Description description = new Description();
        description.setConnection(prop.getProperty("connection"));
        description.setUserId(prop.getProperty("user"));
        description.setPwd(prop.getProperty("password"));
        description.setDescriptionClass(prop.getProperty("description"));
        description.setNameClass(prop.getProperty("name"));
        description.setPriceClass(prop.getProperty("price"));

        return description;

    }

}
