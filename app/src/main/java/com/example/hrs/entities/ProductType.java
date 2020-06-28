package com.example.hrs.entities;

public class ProductType {
    private Integer productTypeId;
    private String productTypeName;

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public ProductType(Integer productTypeId, String productTypeName) {
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
    }


    @Override
    public String toString() {

        return getProductTypeName();
    }
}
