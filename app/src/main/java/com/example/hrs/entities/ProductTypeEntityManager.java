package com.example.hrs.entities;

import com.example.hrs.HRSConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeEntityManager {
    public List<ProductType> lstP = new ArrayList<>()  ;

    public ProductTypeEntityManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject producttype = rootJSON.getJSONObject(i);
            int id = Integer.parseInt(producttype.getString("subProductTypeId"));
            String name = producttype.getString("subProductTypeName");
            ProductType entity = new ProductType(id, name);
            lstP.add(entity);

        }
    }
}
