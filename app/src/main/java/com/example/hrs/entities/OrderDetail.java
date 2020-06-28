package com.example.hrs.entities;

public class OrderDetail {

    private Integer id;
    private String productName;
    private double priceProductOrder;
    private int quantity;
    private double amount;
    private Integer status;
    private Integer orderId;
    private Integer productId;
    private String productImg;

    public OrderDetail(Integer id, String productName, double priceProductOrder, int quantity, double amount, Integer status, Integer orderId, Integer productId, String productImg) {
        this.id = id;
        this.productName = productName;
        this.priceProductOrder = priceProductOrder;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
        this.orderId = orderId;
        this.productId = productId;
        this.productImg = productImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPriceProductOrder() {
        return priceProductOrder;
    }

    public void setPriceProductOrder(double priceProductOrder) {
        this.priceProductOrder = priceProductOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public OrderDetail() {
    }
}
