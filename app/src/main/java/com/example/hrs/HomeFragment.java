package com.example.hrs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnHoverListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrs.entities.Product;
import com.example.hrs.entities.ProductEntityManager;
import com.example.hrs.entities.ProductType;
import com.example.hrs.entities.ProductTypeEntityManager;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public  class HomeFragment extends Fragment {

    GridView gridView;
    List<Product> mListItem = new ArrayList<>();
    List<ProductType> mListPT =null;
    AdapterProduct adapterProduct;
    SpinCboAdapter spinCboAdapter;
    Context mContext;
    Spinner cboP;
    Button btnSearch;
    TextView txtSearch1;
    String txtSearch;
    int idPT;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }

    Runnable mTaskSearchProduct = new Runnable() {
        @Override
        public void run() {
            String data[][] = {
                    {"id", String.valueOf(idPT)},
                    {"name", txtSearch}
            };
            HRSServer server = new HRSServer();
            String result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_SEARCHPRODUCT, data);
            Log.e("Seach", "" + result);
            try {
                ProductEntityManager manager = new ProductEntityManager(result);
                mListItem.clear();
                mListItem = manager.lstProduct;

                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterProduct = new AdapterProduct(mContext, R.layout.item_productx, mListItem);
                        gridView.setAdapter(adapterProduct);
                        // gridView.setAdapter(adapterProduct);
                        adapterProduct.notifyDataSetChanged();
                        // Toast.makeText(mContext, "Refresh...", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    Runnable mTaskLoadProduct = new Runnable() {
        @Override
        public void run() {
            HRSServer server = new HRSServer();
            String result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_PRODUCT, new String[][]{});
            Log.e("MinhVT", "" + result);
            try {
                ProductEntityManager manager = new ProductEntityManager(result);
                mListItem.clear();
                mListItem = manager.lstProduct;

                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterProduct = new AdapterProduct(mContext, R.layout.item_productx, mListItem);
                        gridView.setAdapter(adapterProduct);
                        // gridView.setAdapter(adapterProduct);
                        adapterProduct.notifyDataSetChanged();
                        // Toast.makeText(mContext, "Refresh...", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    Runnable tskLoadSubProductType = new Runnable() {
        @Override
        public void run() {
            HRSServer server = new HRSServer();
            final String result1 = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_SUBPRODUCTTYPE, new String[][]{});
            Log.e("Hieu xau giai:", "###" + result1);

            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ProductTypeEntityManager m = null;
                    try {
                        m = new ProductTypeEntityManager(result1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mListPT = m.lstP;
                    ArrayAdapter<ProductType> adapter = new ArrayAdapter<ProductType>(mContext, android.R.layout.simple_spinner_item, mListPT);
                    cboP.setAdapter(adapter);
                }
            });
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);
        txtSearch1 = (TextView) rootView.findViewById(R.id.txtSearch);
        gridView = (GridView) rootView.findViewById(R.id.lisst);
        adapterProduct = new AdapterProduct(mContext, R.layout.item_productx, mListItem);
        btnSearch = (Button) rootView.findViewById(R.id.btnSearch);
        gridView.setAdapter(adapterProduct);
        //LoadSubProductType();
        cboP = (Spinner) rootView.findViewById(R.id.cboSubProductType);


        Thread h = new Thread(tskLoadSubProductType);
        h.start();


        Thread th = new Thread(mTaskLoadProduct);
        th.start();


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSearch = txtSearch1.getText().toString();
                ProductType select = (ProductType) cboP.getSelectedItem();
                idPT = select.getProductTypeId();

                Thread th = new Thread(mTaskSearchProduct);
                th.start();
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(mContext, "Mã sp " + mListItem.get(position).getProductId(), Toast.LENGTH_SHORT).show();
                // Chuyển
                Intent i = new Intent(mContext, ActivityProductDetail.class);
                i.putExtra("productId",mListItem.get(position).getProductId());
                startActivity(i);
            }
        });

//       gridView.setOnClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
////               Intent intent = new Intent((Activity)mContext, ActivityProductDetail.class);
////               intent.putExtra("productId",mListItem.get(position).getProductId());
////               startActivity(intent);
//           }
//       });


        return rootView;
    }


}