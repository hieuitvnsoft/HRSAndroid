package com.example.hrs.entities;

import java.util.Date;

public class Product {
    int productId;
    String productName;
    double priceOlder;
    String description;
    int subProductTypeId;
    int  producerId;
    int  status;
    String imgProduct;
    double priceNew;
    public Product() {
    }

    public Product(int productId, String productName, double priceOlder, String description, int subProductTypeId, int producerId, int status, String imgProduct, double priceNew) {
        this.productId = productId;
        this.productName = productName;
        this.priceOlder = priceOlder;
        this.description = description;
        this.subProductTypeId = subProductTypeId;
        this.producerId = producerId;
        this.status = status;
        this.imgProduct = imgProduct;
        this.priceNew = priceNew;
    }

    public double getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(double priceNew) {
        this.priceNew = priceNew;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPriceOlder() {
        return priceOlder;
    }

    public void setPriceOlder(double priceOlder) {
        this.priceOlder = priceOlder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubProductTypeId() {
        return subProductTypeId;
    }

    public void setSubProductTypeId(int subProductTypeId) {
        this.subProductTypeId = subProductTypeId;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }
}
