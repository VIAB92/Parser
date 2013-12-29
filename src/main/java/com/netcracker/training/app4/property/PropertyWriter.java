package com.netcracker.training.app4.property;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @VIAB
 */
public class PropertyWriter {
    private FileOutputStream fos;
    public PropertyWriter (String propertyName) throws FileNotFoundException {
        fos = new FileOutputStream(propertyName);
    }

    public void writePropertyFile(Description description) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("connection", description.getConnection());
        properties.setProperty("user", description.getUserId());
        properties.setProperty("password", description.getPwd());
        properties.setProperty("description", description.getDescriptionClass());
        properties.setProperty("name", description.getNameClass());
        properties.setProperty("price", description.getPriceClass());
        properties.store(fos,null);
    }
}
