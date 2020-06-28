package com.example.hrs.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailEntityManager {
    public List<OrderDetail> lstP = new ArrayList<>();

    public OrderDetailEntityManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject product = rootJSON.getJSONObject(i);
            int id = Integer.parseInt(product.getString("id"));
            String productName = product.getString("productName");
            String productImg = product.getString("productImg");
            double priceProductOrder = Double.parseDouble(product.getString("priceProductOrder"));
            int quantity = Integer.parseInt(product.getString("quantity"));
            int status = Integer.parseInt(product.getString("status"));
            int orderId = Integer.parseInt(product.getString("orderId"));
            int productId = Integer.parseInt(product.getString("productId"));
            double amount = Double.parseDouble(product.getString("amount"));

            OrderDetail entity = new OrderDetail( id,  productName,  priceProductOrder,  quantity,  amount,  status,  orderId,  productId,  productImg);
            lstP.add(entity);
        }
    }
}
