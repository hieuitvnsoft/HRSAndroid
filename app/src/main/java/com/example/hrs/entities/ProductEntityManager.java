package com.example.hrs.entities;



import com.example.hrs.HRSConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class ProductEntityManager {
    public ArrayList<Product> lstProduct = new ArrayList<>();

    public ProductEntityManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject product = rootJSON.getJSONObject(i);
            int id = Integer.parseInt(product.getString("productId"));
            String name = product.getString("productName");
            String img = product.getString("imgProduct");
            double priceOld = Double.parseDouble(product.getString("priceOlder"));
            String description = product.getString("description");
            int subType = Integer.parseInt(product.getString("subProductTypeId"));
            int producer = Integer.parseInt(product.getString("producerId"));
            int status = Integer.parseInt(product.getString("status"));
            double priceNew = Double.parseDouble(product.getString("priceNew") );
            String imagelink = HRSConstant.HOSTING + "/"+img;
            Product entity = new Product(id, name,priceOld, description,subType , producer, status, imagelink, priceNew);
            lstProduct.add(entity);
        }
    }
}
