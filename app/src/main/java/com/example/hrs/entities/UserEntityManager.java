package com.example.hrs.entities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class UserEntityManager {
    public UserEntity lstUser = new UserEntity();

    public UserEntityManager(String data) throws JSONException {
        JSONObject rootJSON = new JSONObject(data);

            int userId = Integer.parseInt(rootJSON.getString("userId"));
            String username = rootJSON.getString("userName");
            String password = rootJSON.getString("password");
            String fullname = rootJSON.getString("fullName");
            String address = rootJSON.getString("address");
            String email = rootJSON.getString("email");
            String date = rootJSON.getString("birthDay");
            String avatar = rootJSON.getString("avatar");
            String phone = rootJSON.getString("phone");
            float point = (float) rootJSON.getDouble("point");
            int status = rootJSON.getInt("status");
            boolean sex = rootJSON.getBoolean("sex");
            String birth = rootJSON.getString("birthDay");
        Log.e("=====" ,"12212"+birth);
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date birthDay = null;
        try {
            birthDay = format.parse(birth);
            Log.e("dã chuyển",""+birthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        UserEntity entity = new UserEntity(userId, username, password, fullname, address, email, birthDay, sex, avatar, phone, point, status);
            lstUser=entity;

    }
}
