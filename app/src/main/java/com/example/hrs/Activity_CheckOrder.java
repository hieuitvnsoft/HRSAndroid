package com.example.hrs;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.example.hrs.entities.Order;
import com.example.hrs.entities.OrderDetail;
import com.example.hrs.entities.OrderDetailEntityManager;
import com.example.hrs.entities.OrderEntityManager;
import com.example.hrs.entities.ProductEntityManager;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity_CheckOrder extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<Order> orders;
    HashMap<Order,List<OrderDetail>> listOrderDetail;
    AdapterCustomExpandView adapterCustomExpandView;
    List<OrderDetail> orderDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__check_order);
        expandableListView = (ExpandableListView) findViewById(R.id.listExpand);
        addControl();

    }
    private void addControl(){

        orders = new ArrayList<>();
        listOrderDetail = new HashMap<Order, List<OrderDetail>>();
        orderDetails = new ArrayList<>();
        final GlobalApplication ga = (GlobalApplication) getApplicationContext();

        Runnable mTaskLoadOrder = new Runnable() {
            @Override
            public void run() {
                String data[][] = {
                        {"userId", String.valueOf(ga.gUserId)}
                };
                HRSServer server = new HRSServer();
                String result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_ORDERCHECK, data);
                Log.e("order=========", "" + result);
                try {
                    OrderEntityManager manager = new OrderEntityManager(result);
                    orders = manager.lstP;
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    Log.e("Lỗi","Lỗi");
                }
            }
        };
        Runnable mTaskLoadOrderDetail = new Runnable() {
            @Override
            public void run() {
                String data[][] = {
                        {"userId", String.valueOf(ga.gUserId)}
                };
                HRSServer server = new HRSServer();
                String result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_ORDERCHECKDETAIL, data);
                Log.e("detail========", "" + result);
                try {
                    OrderDetailEntityManager manager = new OrderDetailEntityManager(result);
                    orderDetails = manager.lstP;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread(mTaskLoadOrder);
        thread1.start();
        Thread thread2 = new Thread(mTaskLoadOrderDetail);
        thread2.start();

        try {
            thread1.join();
            thread2.join();

            for (Order o: orders) {
                List<OrderDetail> cc = new ArrayList<>();
                for (OrderDetail dt: orderDetails) {
                    if (o.getOrderId() == dt.getOrderId()){

                        cc.add(dt);
                    }

                }

                listOrderDetail.put(o,cc);
            }

            adapterCustomExpandView = new AdapterCustomExpandView(Activity_CheckOrder.this,orders,listOrderDetail);
            expandableListView.setAdapter(adapterCustomExpandView);
            adapterCustomExpandView.notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e("Hieu loi: ", e.toString());
            Log.e("Hieu vi: ", e.getMessage());
        }
    }
}
