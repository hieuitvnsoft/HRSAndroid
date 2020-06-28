package com.example.hrs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hrs.entities.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterProduct extends ArrayAdapter<Product> {
    Context mContext;
    int mLayout;
    List<Product> mLst;

    public AdapterProduct(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mLayout = resource;
        this.mLst = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            row = LayoutInflater.from(this.mContext).inflate(mLayout, null);
        }

        Product it = mLst.get(position);


        // Đổ dữ liệu
        TextView txtName, txtPrice, txtDes;
        ImageView imgLike, imgImage;

        txtName = (TextView) row.findViewById(R.id.item_name);
        txtDes = (TextView) row.findViewById(R.id.item_desc);
        imgImage = (ImageView) row.findViewById(R.id.item_image);
        txtPrice = row.findViewById(R.id.item_price);
        // Cài đặt dữ liệu item
        txtName.setText(it.getProductName());
        txtDes.setText(it.getDescription());
        String img = it.getImgProduct();
        String imagenew = img.replace("/HRS//HRS/", "/HRS/");

//        Log.e("zzz","Hình product" + img);
//        Log.e("zzz","Hình product new" + imagenew);

        ImageViewLoader.load(mContext, imgImage, imagenew);
        String gia = "";
        if (it.getPriceNew() > 0) {
            NumberFormat nf = NumberFormat.getNumberInstance();
            gia = nf.format(it.getPriceNew()) + "   đ";
        } else
            gia = "liên hệ";

        txtPrice.setText(gia);


        return row;

    }
}
