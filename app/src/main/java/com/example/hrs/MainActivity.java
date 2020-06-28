package com.example.hrs;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hrs.entities.Product;
import com.example.hrs.entities.ProductEntityManager;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private GridView mGridView;
    List<Product> mListItem = new ArrayList<>();
    AdapterProductNew adapterProduct;
    RecyclerView mRv;
//
//    Runnable mTaskLoadProduct = new Runnable() {
//        @Override
//        public void run() {
//            HRSServer server = new HRSServer();
//            String result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_PRODUCT, new String[][]{});
//            Log.e("MinhVT", result);
//            try {
//                ProductEntityManager manager = new ProductEntityManager(result);
//                adapterProduct = new AdapterProductNew(manager.lstProduct, getApplicationContext());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRv.setAdapter(adapterProduct);
//                    }
//                });
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    };



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = null;
            Fragment fragmentdel = fragmentManager.findFragmentById(R.id.fragmentContent);


            switch (item.getItemId()) {
                case R.id.navigation_home:


                    fragment = new HomeFragment();
                    //mTextMessage.setText("Home");


                    break;
                case R.id.navigation_mesage:


                    fragment = new TestFragment();

                    break;
                case R.id.navigation_cart:


                    fragment = new CartFragment();

                    break;

                case R.id.navigation_new:


                    fragment = new TestFragment();

                    break;
                case R.id.navigation_user:



                    fragment = new UserFragment();

                    break;
            }

            fragmentTransaction.remove(fragmentdel);
            fragmentTransaction.replace(R.id.fragmentContent, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);

        // mGridView = findViewById(R.id.gridProduct);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


}
