package com.example.hrs.entities;

import android.util.Log;

import com.example.hrs.HRSConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderEntityManager {
    public List<Order> lstP = new ArrayList<>();

    public OrderEntityManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject product = rootJSON.getJSONObject(i);
            int orderId = Integer.parseInt(product.getString("orderId"));
            String fullName = product.getString("fullName");
            String addressShip = product.getString("addressShip");
            String phone = product.getString("phone");
            int codeId = Integer.parseInt(product.getString("codeId"));
            double pointCount = Double.parseDouble(product.getString("pointCount"));
            double discountAmount = Double.parseDouble(product.getString("discountAmount"));
            double totalAmountOrder = Double.parseDouble(product.getString("totalAmountOrder"));




            String date=product.getString("dateOrder");
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            Date ds = new Date();
            try {
                 ds  = new SimpleDateFormat("MMM dd, yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

//            try {
//                dateOrder = new SimpleDateFormat("MMM dd, yyyy").parse(product.getString("dateOrder"));
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//                System.out.println(e.getMessage());
//            }

            int status = Integer.parseInt(product.getString("status"));
            int paymentMethod = Integer.parseInt(product.getString("paymentMethod"));
            int shipMethod = Integer.parseInt(product.getString("shipMethod"));
            int userId = Integer.parseInt(product.getString("userId"));
            String description = product.getString("description");

            Order entity = new Order(orderId, fullName, addressShip, phone, pointCount, discountAmount, totalAmountOrder, ds, description, status, paymentMethod, shipMethod, userId);
            lstP.add(entity);
        }
    }
}
