package com.netcracker.training.app4.urlparser;

/**
 * @VIAB
 */

public interface UrlParser {
    public void parseType(String domain);
    public boolean checkRelevance();
    public String getProductType();
}
