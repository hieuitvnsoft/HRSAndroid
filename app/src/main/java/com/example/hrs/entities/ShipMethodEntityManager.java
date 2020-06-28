package com.example.hrs.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShipMethodEntityManager {
    public List<ShipMethod> lstP = new ArrayList<>()  ;

    public ShipMethodEntityManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject producttype = rootJSON.getJSONObject(i);
            int id = Integer.parseInt(producttype.getString("shipMethodId"));
            String name = producttype.getString("shipMethodName");
            ShipMethod entity = new ShipMethod(id, name);
            lstP.add(entity);

        }
    }
}
