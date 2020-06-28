package com.example.hrs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hrs.entities.Product;
import com.example.hrs.entities.ProductType;

import java.util.ArrayList;
import java.util.List;

public class TestFragment extends Fragment {


    Context mContext;
    TextView mtxt;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_test_fragment, container, false);
        return rootView;
    }
}
