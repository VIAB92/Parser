package com.netcracker.training.app4.reader;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @VIAB
 */
public interface Reader {
    public List<String> readFromPath(String path) throws FileNotFoundException;
}
