package com.netcracker.training.app4.urlparser;

/**
 * @VIAB
 */
public class ProductTypeParser implements UrlParser {
    private String url;
    private String productType;
    public ProductTypeParser(String url)
    {
        this.url=url;
        this.productType=null;
    }

    @Override
    public void parseType(String domain) {

        int begin = url.indexOf(domain)+domain.length();
        String path = url.substring(begin, url.length());
        int slashIndex = path.indexOf("/");
        this.productType = path.substring(0, slashIndex);
    }

    @Override
    public boolean checkRelevance() {
        if (this.url.indexOf("rozetka")!=-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getProductType()
    {
        return this.productType;
    }

}
