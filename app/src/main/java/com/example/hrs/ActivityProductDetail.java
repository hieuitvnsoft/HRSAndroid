package com.example.hrs;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrs.entities.ProductDetail;
import com.example.hrs.entities.ProductDetailEntityManager;
import com.example.hrs.entities.ProductEntityManager;

import org.json.JSONException;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityProductDetail extends AppCompatActivity {

    List<ProductDetail> mList;
    Context mContext;
    AdapterProductDetail adatAdapterProductDetail;
    LinearLayout linearLayout;
    ImageView imageView;
    TextView mProductName,mItemPrice;
    WebView mWebView;
    List<ImageView> mItemImg =new ArrayList<>();
    ProductDetail idProductDetail;
    Button mAddCart;
    ImageButton  mbtnMinus, mbtnAdd;;
    EditText quantity;


    private void ResetSizeImage(){

        for (int i = 0; i <mItemImg.size() ; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            mItemImg.get(i).setLayoutParams(layoutParams);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        this.mContext = ActivityProductDetail.this;
        imageView = (ImageView) findViewById(R.id.item_image);
       // int id = getIntent().getIntExtra("productId",0);
        linearLayout = (LinearLayout) findViewById(R.id.lineImageThumbai);
        mProductName = (TextView) findViewById(R.id.item_name);
        mWebView = (WebView) findViewById(R.id.item_desc);
        mAddCart = (Button) findViewById(R.id.btnAdd2Card);
        mbtnMinus = (ImageButton) findViewById(R.id.btnMinus);
        mbtnAdd = (ImageButton) findViewById(R.id.btnAdd);
        quantity = (EditText) findViewById(R.id.txtQuantity);
        mItemPrice = (TextView) findViewById(R.id.item_price_new);
        Runnable mTaskLoadProductDetail = new Runnable() {
            @Override
            public void run() {

                String data[][] = {
                        {"id", String.valueOf(getIntent().getIntExtra("productId",0))}

                };

                HRSServer server = new HRSServer();
                String result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_PRODUCT_DETAIL, data);
                Log.e("Get Detail", "" + result);
                try {
                    ProductDetailEntityManager manager = new ProductDetailEntityManager(result);

                    mList = manager.lstProduct;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            idProductDetail = mList.get(0);
                            for (int i = 0; i < mList.size(); i++) {



                                //Tạo ảnh
                                final ImageView imgImage = new ImageView(mContext);
                                if (i==0){
                                    LinearLayout.LayoutParams layoutParamsS = new LinearLayout.LayoutParams(120, 120);
                                    imgImage.setLayoutParams(layoutParamsS);

                                }else {

                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 100);
                                    imgImage.setLayoutParams(layoutParams);
                                }

                                //imgImage.getLayoutParams().height=100;
                                String img = mList.get(i).getImgProduct();
                                String img2 = mList.get(0).getImgProduct();
                                 String dess = mList.get(i).getDescription();
                                final String name = mList.get(i).getProductName();
                                final String name2 = mList.get(0).getProductName();
                                String imagenew = img.replace("/HRS//HRS/","/HRS/" );
                                String imagenew2 = img2.replace("/HRS//HRS/","/HRS/" );
                                ImageViewLoader.load(mContext,imageView,imagenew2);
                                final String size =  mList.get(i).getSizeName();
                                final String size2 =  mList.get(0).getSizeName();
                                final String color =  mList.get(i).getColorName();
                                final String color2 =  mList.get(0).getColorName();
                               final   double price = mList.get(i).getPriceNew();
                               final   double price2 = mList.get(0).getPriceNew();
                                String colorCode =  mList.get(i).getColorCode();
                                ImageViewLoader.load(mContext,imgImage,imagenew);
                                mProductName.setText(name2+"--("+color2+"---"+size2+")");

                                NumberFormat format =NumberFormat.getInstance(new Locale("vi","VN"));

                                mItemPrice.setText(format.format(mList.get(0).getPriceNew()) +"   đ");
                                 final int id = i;

                                linearLayout.addView(imgImage);
                                mWebView.loadData(dess, "text/html", "UTF-8");
                                mItemImg.add(imgImage);


                                imgImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        imageView.setImageDrawable(imgImage.getDrawable());
                                        // imgImage.setBackground();
                                        mProductName.setText(name+"--("+color+"---"+size+")");
                                        idProductDetail = mList.get(id);
                                        ResetSizeImage();
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120 , 120);
                                        imgImage.setLayoutParams(layoutParams);
                                        int qtt = Integer.parseInt(quantity.getText().toString());


                                        NumberFormat format =NumberFormat.getInstance(new Locale("vi","VN"));
                                        mItemPrice.setText(format.format(price * qtt) +"   đ");
                                       // Toast.makeText(ActivityProductDetail.this, "Bạn chọn " + idProductDetail, Toast.LENGTH_SHORT).show();
                                    }
                                });

                                mbtnAdd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int qtt = Integer.parseInt(quantity.getText().toString());
                                        int qttn = qtt+=1;
                                        quantity.setText(qttn + "");
                                        NumberFormat format =NumberFormat.getInstance(new Locale("vi","VN"));
                                        mItemPrice.setText(format.format(idProductDetail.getPriceNew()* qttn) +"   đ");
                                    }
                                });
                                mbtnMinus.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int qtt = Integer.parseInt(quantity.getText().toString());
                                        if (qtt==1){

                                            Toast.makeText(mContext, "Số lượng không thể nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                                        }else {
                                            int qttn = qtt-=1;
                                            quantity.setText(qttn+"");
                                            NumberFormat format =NumberFormat.getInstance(new Locale("vi","VN"));
                                            mItemPrice.setText(format.format(idProductDetail.getPriceNew() * qttn) +"   đ");
                                        }

                                    }
                                });
                            }

                            Log.e("Số lượng ảnh là","#"+ mList.size());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread h = new Thread(mTaskLoadProductDetail);
        h.start();
        //Toast.makeText(mContext, "Mã sản phẩm dc c h"+id, Toast.LENGTH_SHORT).show();
        mAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalApplication ga = (GlobalApplication) getApplicationContext();
                if (ga.gGioHang.size()==0){
                    ProductDetail p =idProductDetail;
                    p.setQuantityOrder(Integer.parseInt(quantity.getText().toString()));
                    double amount = p.getQuantityOrder() * p.getPriceNew();
                    p.setPriceProductOrder(p.getPriceNew());
                    p.setPriceProductOrder(amount);
                    p.setAmount(amount);
                    ga.gGioHang.add(p);

                }else {
                    boolean checkItem = checkContain(idProductDetail);
                    if (checkItem==true){
                        ProductDetail hang=ga.gGioHang.get(ga.gGioHang.indexOf(idProductDetail));
                        hang.setQuantityOrder(hang.getQuantityOrder()+Integer.parseInt(quantity.getText().toString()));
                        double amount = hang.getQuantityOrder() * hang.getPriceNew();
                        hang.setPriceProductOrder(hang.getPriceNew());
                        hang.setAmount(amount);

                    }else {
                        ProductDetail p =idProductDetail;
                        p.setQuantityOrder(Integer.parseInt(quantity.getText().toString()));
                        double amount = p.getQuantityOrder() * p.getPriceNew();
                        p.setPriceProductOrder(p.getPriceNew());
                        p.setPriceProductOrder(amount);
                        p.setAmount(amount);
                        ga.gGioHang.add(p);

                    }

                }




                Toast.makeText(mContext, "Thêm vào giỏ hàng thành công  ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private boolean checkContain(ProductDetail p){
        GlobalApplication ga = (GlobalApplication) getApplicationContext();
        boolean c= false;
        for (int i=0; i<ga.gGioHang.size();i++){
        if (p.getId()==ga.gGioHang.get(i).getId()){

            c=true;
            break;
        }else {

            c=false;
        }

        }
        return c;
    }

}
