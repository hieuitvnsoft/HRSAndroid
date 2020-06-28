package com.example.hrs.entities;

import com.example.hrs.HRSConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductDetailEntityManager {
    public ArrayList<ProductDetail> lstProduct = new ArrayList<>();

    public ProductDetailEntityManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject product = rootJSON.getJSONObject(i);
            int id = Integer.parseInt(product.getString("id"));
            int quantity = Integer.parseInt(product.getString("quantity"));
            String colorName = product.getString("colorName");
            String productName = product.getString("productName");
            String colorCode = product.getString("colorCode");
            int productId = Integer.parseInt(product.getString("productId"));
            String img = product.getString("imgProduct");

            String description = product.getString("description");
            String producerName = product.getString("producerName");
            String subProductTypeName = product.getString("subProductTypeName");
            String sizeName = product.getString("sizeName");


            double priceNew = Double.parseDouble(product.getString("priceNew") );
            String imagelink = HRSConstant.HOSTING + "/"+img;
            ProductDetail productDetail = new ProductDetail(id,priceNew,quantity,imagelink,colorName,productId,sizeName,productName,colorCode,description,producerName,subProductTypeName);
//           ProductDetail entity = new ProductDetail(id, name,priceOld, description,subType , producer, status, imagelink, priceNew);
           lstProduct.add(productDetail);
        }
    }
}
