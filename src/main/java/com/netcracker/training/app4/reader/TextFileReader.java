package com.netcracker.training.app4.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @VIAB
 */
public class TextFileReader {

   public List<String> readFile(String path) throws FileNotFoundException {
        List<String> urls = new ArrayList<String>();
        Scanner in = new Scanner(new File(path));
        while (in.hasNext())
        {
            urls.add(in.nextLine()+"\r\n");
        }
        return urls;
    }
}
