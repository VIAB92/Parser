package com.netcracker.training.app4.productparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @VIAB
 */
public class RozetkaProductParser implements ProductParser {
    private String url;
    private String nameClass;
    private String detailClass;
    private String priceClass;
    private static final String REQUEST_TO_SPLIT = " грн.";
    private List<String> productNames;
    private List<String> productDetails;
    private List<Integer> productPrices;

    public RozetkaProductParser(String url, String nameClass, String detailClass, String priceClass)
    {
        this.url=url;
        this.nameClass=nameClass;
        this.detailClass=detailClass;
        this.priceClass=priceClass;
    }

    @Override
    public List<String> getProductNames() {
        return productNames;
    }

    @Override
    public List<String> getProductDetails() {
        return productDetails;
    }

    @Override
    public List<Integer> getProductPrices() {
        return productPrices;
    }

    @Override
    public void parseProducts() throws IOException, ProductNotFoundException {
        Document document = Jsoup.connect(url).get();
        this.productNames = new ArrayList<String>();
        this.productPrices = new ArrayList<Integer>();
        this.productDetails = new ArrayList<String>();
        parseNames(document);
        parseDetails(document);
        parsePrices(document);
    }

    private void parseNames(Document document) throws ProductNotFoundException {
        Elements names = document.select(".title");
        if (names.size()==0)
        {
            throw new ProductNotFoundException("Names not found", new RuntimeException());
        }
        int size = names.size();
        for (int i=0; i<size; i++)
        {

            String name = names.get(i).text();

            productNames.add(name);
        }

    }

    private void parseDetails(Document document) throws ProductNotFoundException {
        Elements details = document.select(".tool_tip");
        if (details.size()==0)
        {
            throw new ProductNotFoundException("Details not found", new RuntimeException());
        }
        int size = details.size();
        for (int i=0; i<size; i++)
        {
            String detail = details.get(i).text();
            productDetails.add(detail);
        }
    }

    private void parsePrices(Document document) throws ProductNotFoundException {
        Elements prices = document.select(".price");
        if (prices.size()==0)
        {
            throw new ProductNotFoundException("Prices not found", new RuntimeException());
        }
        int size = prices.size();
        for (int i=0; i<size; i++)
        {
            int price = Integer.parseInt(gimmeValue(prices.get(i).text(), REQUEST_TO_SPLIT));
            productPrices.add(price);
        }
    }

    private String gimmeValue(String toParse, String whatToCut)
    {
        int index = toParse.indexOf(" грн.");
        String result = toParse.substring(0, index);
        return result;
    }


}
