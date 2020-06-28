package com.example.hrs;

import android.app.Application;

import com.example.hrs.entities.OrderDetail;
import com.example.hrs.entities.Product;
import com.example.hrs.entities.ProductDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class GlobalApplication extends Application {
    Product gProduct;

    // Thông tin này sau khi LOGIN sẽ được gán giá trị
     Integer gUserId=0;
     String gFullName="";
     String gAddress="";
     String gEmail="";
     Date gBirthDay;
     Boolean gSex = true;
     String gAvatar="";
     String gPhone="";

    ArrayList<ProductDetail> gGioHang = new ArrayList<>(); // Không trùng lặp

    /**
     * Chuyển đổi dữ liệu List về định dạng JSON
     * @return
     */
    public String getJSONFromCart() {

        JSONArray arr = new JSONArray();
        for (ProductDetail e : gGioHang) {
//            String obj = "{";
//            obj += "\"productId\":" + e.getProductId() + ",";
//            obj += "\"productName\":" + e.getProductName()+"  ("+e.getColorName() +"---"+e.getSizeName()+")"+ ",";
//            obj += "\"priceProductOrder\":" +e.getPriceNew() + ",";
//            obj += "\"quantity\":" + e.getQuantityOrder()+",";
//            obj += "\"amount\":" + e.getAmount()+"";
//            obj += "\"status\":" + 1+"";
//            obj += "}";
//            if (size < gGioHang.size() - 1) {
//                json += obj + ",";
//            } else {
//                json += obj;
//            }
//            size++;
            JSONObject object = new JSONObject();
            try {
                object.put("productId",e.getId() );
                object.put("productName",e.getProductName()+"  ("+e.getColorName() +"---"+e.getSizeName()+")");
                object.put("priceProductOrder",e.getPriceNew());
                object.put("quantity",e.getQuantityOrder());
                object.put("amount",e.getAmount());
                object.put("status",1);

            } catch (JSONException exx) {
                exx.printStackTrace();
            }
            arr.put(object);
        }
        return arr.toString();
    }

    /*
    * [
	{
		"id_product":1,
		"count": 3,
		"price": 12000
	},
	{
		"id_product":2,
		"count": 1,
		"price": 5000
	},
	{
		"id_product":3,
		"count": 5,
		"price": 800
	}
]
    * */
}
