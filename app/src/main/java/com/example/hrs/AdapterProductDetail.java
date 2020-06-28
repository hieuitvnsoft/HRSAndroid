package com.example.hrs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hrs.entities.Product;
import com.example.hrs.entities.ProductDetail;

import java.text.NumberFormat;
import java.util.List;

public class AdapterProductDetail extends ArrayAdapter<ProductDetail> {
    Context mContext;
    int mLayout;
    List<ProductDetail> mLst;

    public AdapterProductDetail(Context context, int resource, List<ProductDetail> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mLayout = resource;
        this.mLst = objects;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = LayoutInflater.from(this.mContext).inflate(mLayout, null);
        }

        ProductDetail productDetail = mLst.get(position);
        ImageView  imgImage;
        imgImage = (ImageView) row.findViewById(R.id.img_thumbnail);
        String img = productDetail.getImgProduct();
        String imagenew = img.replace("/HRS//HRS/","/HRS/" );

        ImageViewLoader.load(mContext,imgImage,imagenew);
        return row;

    }
}
