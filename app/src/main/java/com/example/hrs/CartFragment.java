package com.example.hrs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrs.entities.Product;
import com.example.hrs.entities.ProductDetail;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment implements AdapterCart.IChange {
    GridView gridView;
    List<ProductDetail> mListItem ;
    Button btnBack, btnPayment;
    AdapterCart adapterProduct;
    TextView mTotalAmount;
    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getActivity().getMenuInflater().inflate(R.menu.delete_menu, menu);



        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        GlobalApplication globalApplication= (GlobalApplication) mContext.getApplicationContext();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        ProductDetail kh = mListItem.get(position);
        int idMenu = item.getItemId();
        switch (idMenu){

            case R.id.delete:
                globalApplication.gGioHang.remove(kh);

        }

        return super.onContextItemSelected(item);
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_cart_fragment, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridCard);

        mTotalAmount = (TextView) rootView.findViewById(R.id.totalAmount);
        btnPayment = (Button) rootView.findViewById(R.id.payment);
         GlobalApplication ga = (GlobalApplication) mContext.getApplicationContext();
         mListItem = ga.gGioHang;

         double total=0;
         for (ProductDetail p : ga.gGioHang
         ) {
            total+=p.getAmount();
         }
         NumberFormat f = NumberFormat.getInstance(new Locale("vi","VN"));
         mTotalAmount.setText(f.format(total)+"   đ");
         if (mListItem.size()==0){
             Toast.makeText(mContext, "Bạn chưa chọn sản phẩm nào", Toast.LENGTH_SHORT).show();
             btnPayment.setVisibility(View.GONE);
         }else {

             adapterProduct = new AdapterCart(getActivity().getApplicationContext(), R.layout.item_cart, mListItem, this);
         }
         btnPayment.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent =new Intent(mContext,InforPaymentActivity.class);
                 startActivity(intent);
             }
         });


        gridView.setAdapter(adapterProduct);
        //egisterForContextMenu(gridView);



        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu menu = new   PopupMenu(mContext, view);
                menu.getMenuInflater().inflate(R.menu.delete_menu, menu.getMenu());
//                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.your_first_menu_item:
//                                // do something
//                                break;
//                        }
//                        return false;
//                    }
//                });
                menu.show();


                return true;
            }
        });

        return rootView;

    }




    @Override
    public void change() {
        GlobalApplication ga = (GlobalApplication) mContext.getApplicationContext();
        mListItem = ga.gGioHang;

        double total=0;
        for (ProductDetail p : ga.gGioHang
        ) {
            total+=p.getAmount();
        }
        NumberFormat f = NumberFormat.getInstance(new Locale("vi","VN"));
        mTotalAmount.setText(f.format(total)+"   đ");
        gridView.setAdapter(adapterProduct);
    }
}
