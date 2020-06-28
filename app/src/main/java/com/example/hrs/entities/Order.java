package com.example.hrs.entities;

import java.util.Date;
import java.util.List;

public class Order {

    private Integer orderId;
    private String fullName;
    private String addressShip;
    private String phone;
    private Integer codeId;
    private Double pointCount;
    private Double discountAmount;
    private Double totalAmountOrder;
    private Date dateOrder;
    private String description;
    private Integer status;
    private Integer discountId;
    private Integer paymentMethod;
    private Integer shipMethod;
    private Integer userId;

    public Order(Integer orderId, String fullName, String addressShip, String phone, Double pointCount, Double discountAmount, Double totalAmountOrder, Date dateOrder, String description, Integer status, Integer paymentMethod, Integer shipMethod, Integer userId) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.addressShip = addressShip;
        this.phone = phone;
        this.pointCount = pointCount;
        this.discountAmount = discountAmount;
        this.totalAmountOrder = totalAmountOrder;
        this.dateOrder = dateOrder;
        this.description = description;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.shipMethod = shipMethod;
        this.userId = userId;
    }

    public Order(Integer orderId, String fullName, String addressShip, String phone, Double pointCount, Double discountAmount, Double totalAmountOrder, Date dateOrder, String description, Integer status, Integer discountId, Integer paymentMethod, Integer shipMethod, Integer userId) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.addressShip = addressShip;
        this.phone = phone;
        this.pointCount = pointCount;
        this.discountAmount = discountAmount;
        this.totalAmountOrder = totalAmountOrder;
        this.dateOrder = dateOrder;
        this.description = description;
        this.status = status;
        this.discountId = discountId;
        this.paymentMethod = paymentMethod;
        this.shipMethod = shipMethod;
        this.userId = userId;
    }

    String paymentMethodName;
    String shipMethodName;

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getShipMethodName() {
        return shipMethodName;
    }

    public void setShipMethodName(String shipMethodName) {
        this.shipMethodName = shipMethodName;
    }



    public Order(String fullName, String addressShip, String phone, Double totalAmountOrder, Date dateOrder, String description, Integer status, Integer paymentMethod, Integer shipMethod, Integer userId) {
        this.fullName = fullName;
        this.addressShip = addressShip;
        this.phone = phone;
        this.totalAmountOrder = totalAmountOrder;
        this.dateOrder = dateOrder;
        this.description = description;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.shipMethod = shipMethod;
        this.userId = userId;
    }

    public Order(Integer orderId, String fullName, String addressShip, String phone, Integer codeId, Double pointCount, Double discountAmount, Double totalAmountOrder, Date dateOrder, String description, Integer status, Integer discountId, Integer paymentMethod, Integer shipMethod, Integer userId, String paymentMethodName, String shipMethodName) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.addressShip = addressShip;
        this.phone = phone;
        this.codeId = codeId;
        this.pointCount = pointCount;
        this.discountAmount = discountAmount;
        this.totalAmountOrder = totalAmountOrder;
        this.dateOrder = dateOrder;
        this.description = description;
        this.status = status;
        this.discountId = discountId;
        this.paymentMethod = paymentMethod;
        this.shipMethod = shipMethod;
        this.userId = userId;
        this.paymentMethodName = paymentMethodName;
        this.shipMethodName = shipMethodName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddressShip() {
        return addressShip;
    }

    public void setAddressShip(String addressShip) {
        this.addressShip = addressShip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public Double getPointCount() {
        return pointCount;
    }

    public void setPointCount(Double pointCount) {
        this.pointCount = pointCount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getTotalAmountOrder() {
        return totalAmountOrder;
    }

    public void setTotalAmountOrder(Double totalAmountOrder) {
        this.totalAmountOrder = totalAmountOrder;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(Integer shipMethod) {
        this.shipMethod = shipMethod;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
