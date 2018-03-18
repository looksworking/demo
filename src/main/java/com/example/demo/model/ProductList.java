package com.example.demo.model;

import java.util.List;

public class ProductList {

    ProductList(){
    }

    public ProductList(List<ProductDetails> productDetailsEntities){
        this();
        this.productDetails = productDetailsEntities;
    }

    private List<ProductDetails> productDetails;

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }
}
