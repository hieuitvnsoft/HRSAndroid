package com.example.hrs.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodEntityManager {
    public List<PaymentMethod> lstP = new ArrayList<>()  ;

    public PaymentMethodEntityManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject producttype = rootJSON.getJSONObject(i);
            int id = Integer.parseInt(producttype.getString("paymentMethodId"));
            String name = producttype.getString("paymentMethodName");
            PaymentMethod entity = new PaymentMethod(id, name);
            lstP.add(entity);

        }
    }
}
