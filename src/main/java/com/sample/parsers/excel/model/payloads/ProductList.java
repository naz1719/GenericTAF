package com.sample.parsers.excel.model.payloads;


import com.sample.parsers.excel.model.excel.ProductEx;

import java.util.List;

public class ProductList {
    private String productTitle;
    private List<ProductEx> products;


    @Override
    public String toString() {
        return "ProductList{" +
                "productTitle='" + productTitle + '\'' +
                ", products=" + products +
                '}';
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public List<ProductEx> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEx> products) {
        this.products = products;
    }
}
