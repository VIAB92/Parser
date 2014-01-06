package com.netcracker.training.app4.productparser;

import com.netcracker.training.app4.entity.Product;
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

    @Override
    public void setProductType(String productType) {
        this.productType = productType;
    }

    private String productType;
    private static final String REQUEST_TO_SPLIT = " грн.";
    /*private List<String> productNames;
    private List<String> productDetails;
    private List<Integer> productPrices;*/

    public RozetkaProductParser(String url, String nameClass, String detailClass, String priceClass)
    {
        this.url=url;
        this.nameClass=nameClass;
        this.detailClass=detailClass;
        this.priceClass=priceClass;

    }

    @Override
    public List<Product> parseProducts() throws IOException, ProductNotFoundException, IncorrectListSizeException {
        Document document = Jsoup.connect(url).get();
        List<String> productNames = parseNames(document);
        List<Integer> productPrices = parsePrices(document);
        List<String> productDetails = parseDetails(document);
        List<Product> products = createProducts(productNames, productDetails, productPrices);
        return products;
    }

    private List<String> parseNames(Document document) throws ProductNotFoundException {
        List<String> productNames = new ArrayList<String>();
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
        return productNames;

    }

    private List<String> parseDetails(Document document) throws ProductNotFoundException {
        List<String> productDetails = new ArrayList<String>();
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
        return productDetails;
    }

    private List<Integer> parsePrices(Document document) throws ProductNotFoundException {
        List<Integer> productPrices = new ArrayList<Integer>();
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
        return productPrices;
    }

    private String gimmeValue(String toParse, String whatToCut)
    {
        int index = toParse.indexOf(" грн.");
        String result = toParse.substring(0, index);
        return result;
    }

    private List<Product> createProducts(List<String> productNames, List<String> productDetails, List<Integer> productPrices) throws IncorrectListSizeException {
        if (productNames.size() == productDetails.size() && productNames.size()==productPrices.size())
        {
            List<Product> products = new ArrayList<Product>();
            int size = productNames.size();
            for (int i=0; i<size; i++)
            {
                Product product = new Product();
                product.setName(productNames.get(i));
                product.setDetails(productDetails.get(i));
                product.setType(productType);
                product.setPrice(productPrices.get(i));
                products.add(product);
            }
            return products;

        }
        else
        {
            throw new IncorrectListSizeException("Parsed list sizes are not equal!", new Exception());
        }
    }


}
