package com.example.hrs;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrs.entities.Product;


import java.util.Locale;

public class ProductActivity extends AppCompatActivity {

    ImageView image;
    TextView name;
    TextView desc;
    TextView priceOld;
    TextView priceNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        this.image = findViewById(R.id.item_image);
        this.name = findViewById(R.id.item_name);
        this.desc = findViewById(R.id.item_desc);
        this.priceOld = findViewById(R.id.item_price_old);
        this.priceNew = findViewById(R.id.item_price_new);
        this.priceOld.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        GlobalApplication global = (GlobalApplication) getApplicationContext();
        Product prod = global.gProduct;

        ImageViewLoader.load(this, image, prod.getImgProduct());
        name.setText(prod.getProductName());
        desc.setText(prod.getDescription());
        priceOld.setText(HRSUtils.formatCurrency(prod.getPriceOlder(), new Locale("vi", "VN")));

    }

    public void add2Card(View view) {
        GlobalApplication global = (GlobalApplication) getApplicationContext();
        Product prod = global.gProduct; // Sản phẩm TẠM để hiển thị chi tiết

        // Cài đặt Số lượng và Giá bán ẢO


        //global.gGioHang.add(prod);

        Toast.makeText(this, "Đã thêm sản phẩm " + prod.getProductName() + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
    }
}
