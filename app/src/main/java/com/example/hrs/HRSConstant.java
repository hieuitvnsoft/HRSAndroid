package com.example.hrs;

public class HRSConstant {
    public static final String DOMAIN = "192.168.1.57:8080"; // minhvufc.esy.es
    public static final String HOSTING = "http://" + DOMAIN + "/HRS/";
    public static final String HOSTING2 = "http://" + DOMAIN + "/";
    public static final String HOSTING_API = "http://" + DOMAIN + "/HRS/rest/";
    public static final String PAGE_INDEX = "user/login";
    public static final String PAGE_REGISTER = "user/register";
    public static final String PAGE_PRODUCT = "product/getAll";
    public static final String PAGE_PRODUCT_DETAIL = "product/getAllProductDetail";
    public static final String PAGE_SEARCHPRODUCT= "product/getAllProductByName";
    public static final String PAGE_SUBPRODUCTTYPE = "product/getAllSubProductType";
    public static final String PAGE_PAYMENTMETHOD = "payment/getPaymentMethod";
    public static final String PAGE_SHIPMETHOD = "payment/getShipMethod";
    public static final String PAGE_CHECKREGISSTER = "user/check";
    public static final String PAGE_CART = "payment/order";
    public static final String PAGE_ORDERCHECK = "payment/checkOrder";
    public static final String PAGE_ORDERCHECKCOUNT = "payment/checkCountOder";
    public static final String PAGE_ORDERCHECKDETAIL = "payment/checkOrderDetail";
}
