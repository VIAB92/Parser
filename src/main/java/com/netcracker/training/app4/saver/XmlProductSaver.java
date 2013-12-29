package com.netcracker.training.app4.saver;

import com.netcracker.training.app4.entity.Product;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @VIAB
 */
public class XmlProductSaver implements ProductSaver{

    @Override
    public void saveProductList(List<Product> products) throws FileNotFoundException {
        Date dNow = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd-hh-mm-ss");
        String fileName = "result/products-"+ft.format(dNow)+".xml";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        XMLEncoder encoder = new XMLEncoder(fileOutputStream);
        encoder.writeObject(products);
        encoder.close();
    }
}
