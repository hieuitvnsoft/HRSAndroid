package com.example.hrs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrs.entities.Product;
import com.example.hrs.entities.ProductDetail;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterCart extends ArrayAdapter {
    Context mContext;
    int mLayout;
    List<ProductDetail> mLst;
    IChange mC;

    public interface IChange {
        public void change();
    }

    // GlobalApplication ga = (GlobalApplication) mContext.getApplicationContext();
    public AdapterCart(Context context, int resource, List<ProductDetail> objects, IChange c) {
        super(context, resource, objects);
        this.mContext = context;
        this.mLayout = resource;
        this.mLst = objects;
        this.mC = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final TextView mPrice, mName, mAmount;
        final EditText mQuantity;
        ImageView imgImage;
        ImageButton btnTru, btnCong, btnDelete;
        if (row == null) {
            row = LayoutInflater.from(this.mContext).inflate(mLayout, null);
        }

        final ProductDetail productDetail = mLst.get(position);

        imgImage = (ImageView) row.findViewById(R.id.img_cart);
        mName = (TextView) row.findViewById(R.id.txtProductName);
        mPrice = (TextView) row.findViewById(R.id.txtPrice);
        mAmount = (TextView) row.findViewById(R.id.txtAmount);
        mQuantity = (EditText) row.findViewById(R.id.txtQuan);
        btnTru = (ImageButton) row.findViewById(R.id.tru);
        btnCong = (ImageButton) row.findViewById(R.id.cong);
        btnDelete = (ImageButton) row.findViewById(R.id.delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeQuantity(0, productDetail, mAmount, mQuantity);
            }
        });
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeQuantity(1, productDetail, mAmount, mQuantity);
                Log.e("Tru", "cong====================");
            }
        });

        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeQuantity(2, productDetail, mAmount, mQuantity);
                Log.e("Tru", "trư====================");
            }
        });


        NumberFormat f = NumberFormat.getInstance(new Locale("vi", "VN"));
        mName.setText(productDetail.getProductName() + "--(" + productDetail.getColorName() + "---" + productDetail.getSizeName() + ")");
        mPrice.setText(f.format(productDetail.getPriceNew()) + "");
        mAmount.setText(f.format(productDetail.getAmount()) + "");
        mQuantity.setText(f.format(productDetail.getQuantityOrder()) + "");

        String img = productDetail.getImgProduct();
        String imagenew = img.replace("/HRS//HRS/", "/HRS/");

        ImageViewLoader.load(mContext, imgImage, imagenew);
        return row;

    }

    private void changeQuantity(int cal, ProductDetail pd, TextView total, EditText quantitys) {

        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        int quantity = pd.getQuantityOrder();
        Double price = pd.getPriceProductOrder();
        Double amount = price * quantity;
        if (cal == 1) {

            quantity += 1;
            amount = price * quantity;
            quantitys.setText(quantity + "");
            total.setText(format.format(amount) + "   đ");
            Log.e("Tru", "cong====================" + quantity);
        } else if (cal == 2) {
            if (quantity == 1) {
                Toast.makeText(mContext, "Số lượng không thể nhỏ hơn 1", Toast.LENGTH_SHORT).show();
            } else {
                quantity -= 1;
                amount = price * quantity;
                quantitys.setText(quantity + "");
                total.setText(format.format(amount) + "   đ");
                Log.e("Tru", "trư====================" + quantity);
            }
        } else {

            mLst.remove(pd);

        }

        pd.setQuantityOrder(quantity);
        pd.setAmount(amount);
        // GlobalApplication ga = (GlobalApplication) mContext.getApplicationContext();
        mC.change();
    }
}
