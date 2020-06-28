package com.example.hrs.entities;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by minhvufc on 12/10/2017.
 */

public class ResultEntity {
    int id;
    String message;

    public ResultEntity(String data) throws JSONException {
        try {
            JSONObject rootJSON = new JSONObject(data);
            this.id = rootJSON.getInt("id");
            this.message = rootJSON.getString("message");
        } catch (Exception ex){
            Log.e(getClass().getName(), "Lỗi: DỮ LIỆU JSON");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
