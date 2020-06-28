package com.example.hrs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hrs.entities.Order;
import com.example.hrs.entities.OrderDetail;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AdapterCustomExpandView extends BaseExpandableListAdapter {

    Context mContext;
    List<Order> orderList;
    HashMap<Order, List<OrderDetail>> listDetail;

    public AdapterCustomExpandView(Context mContext, List<Order> orderList, HashMap<Order, List<OrderDetail>> listDetail) {
        this.mContext = mContext;
        this.orderList = orderList;
        this.listDetail = listDetail;
    }

    @Override
    public int getGroupCount() {
        return orderList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        int size = listDetail.get(orderList.get(groupPosition)).size();
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return orderList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDetail.get(orderList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Order ord = (Order) getGroup(groupPosition);
        Integer soOrderId = ord.getOrderId();
        Date dateOrder = ord.getDateOrder();
        Integer status = ord.getStatus();
        Double totalAmount = ord.getTotalAmountOrder();
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(dateOrder);
        String num = format.format(totalAmount);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.group_order, null);
        TextView txId, txStt, txData, txtTotal;
        txId = convertView.findViewById(R.id.orderIdz);
        txStt = convertView.findViewById(R.id.statusOrder);
        txData = convertView.findViewById(R.id.dateOrd);
        txtTotal = convertView.findViewById(R.id.totalAmountz);

        txId.setText(ord.getOrderId() + "");
        String stt = "";
        switch (status) {

            case 0:
                stt = "Đơn hàng hủy";
                break;
            case 1:
                stt = "Chờ xử lý";
                break;
            case 2:
                stt = "Đang vận chuyển";
                break;
            case 3:
                stt = "Đã giao hàng";
                break;
            case 4:
                stt = "Đổi trả";
                break;
        }

        txData.setText(strDate);
        txStt.setText(stt);
        txtTotal.setText(num + "  đ");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        OrderDetail ordt = (OrderDetail) getChild(groupPosition, childPosition);
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_listorder, null);

        ImageView imageView = convertView.findViewById(R.id.imgP);
        String img = ordt.getProductImg();
        String imagenew = img.replace("/HRS//HRS/", "/HRS/");
        Log.e("img","Hinhf looi" + imagenew);
        Log.e("img","Hinhf goc" + img);

        ImageViewLoader.load(mContext, imageView, HRSConstant.HOSTING2+ imagenew);


        TextView txQ, txPrice, txTotal, txName;
        txQ = (TextView) convertView.findViewById(R.id.quantt);
        txPrice = (TextView) convertView.findViewById(R.id.price);
        txTotal = (TextView) convertView.findViewById(R.id.totalO);
        txName = (TextView) convertView.findViewById(R.id.namProduct);
        txQ.setText(ordt.getQuantity() + "");
        txPrice.setText(format.format(ordt.getPriceProductOrder()));
        txTotal.setText(format.format(ordt.getAmount()));
        txName.setText(ordt.getProductName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
