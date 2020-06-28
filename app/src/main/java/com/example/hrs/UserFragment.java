package com.example.hrs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrs.entities.OrderEntityManager;

import org.json.JSONException;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment {

    LinearLayout mLogin;
    RelativeLayout mUser;
    Button btnLogin, btnRegiss, btnLogout;
    Context mContext;
    de.hdodenhof.circleimageview.CircleImageView mAvarta;
    TextView mName, mAddres, mEmail, mBirth, mSex, mPhone, mCheckOrder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_user_fragment, container, false);
        mLogin = (LinearLayout) rootView.findViewById(R.id.Login);

        mUser = (RelativeLayout) rootView.findViewById(R.id.userInfor);
        mAvarta = (CircleImageView) rootView.findViewById(R.id.avatar);
        mName = (TextView) rootView.findViewById(R.id.txtName);
        mAddres = (TextView) rootView.findViewById(R.id.txtAddrr);
        mEmail = (TextView) rootView.findViewById(R.id.txtEm);
        mBirth = (TextView) rootView.findViewById(R.id.txtBirth);
        mSex = (TextView) rootView.findViewById(R.id.txtSexx);
        mPhone = (TextView) rootView.findViewById(R.id.txtPhonee);
        mCheckOrder = (TextView) rootView.findViewById(R.id.checkOr);

        GlobalApplication ga = (GlobalApplication) getActivity().getApplicationContext();
        if (ga.gUserId == 0) {
            mLogin.setVisibility(View.VISIBLE);
            mUser.setVisibility(View.GONE);

        } else {
            mLogin.setVisibility(View.GONE);
            mUser.setVisibility(View.VISIBLE);
            mAvarta = (CircleImageView) rootView.findViewById(R.id.avatar);

            ImageViewLoader.load(mContext, mAvarta, HRSConstant.HOSTING2 + ga.gAvatar);
            mName.setText(ga.gFullName);
            mAddres.setText(ga.gAddress);
            mEmail.setText(ga.gEmail);
            Format format = new SimpleDateFormat("dd-MM-yyyy");

            mBirth.setText(format.format(ga.gBirthDay));
            if (ga.gSex == true) {
                mSex.setText("Name");
            } else {
                mSex.setText("Nữ");
            }

            mPhone.setText(ga.gPhone);
            ;

        }
        String check = "Kiểm tra đơn hàng";
        SpannableString chk = new SpannableString(check);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                final GlobalApplication ga = (GlobalApplication) mContext.getApplicationContext();


                Runnable mTaskLoadOrder = new Runnable() {
                    @Override
                    public void run() {
                        String data[][] = {
                                {"userId", String.valueOf(ga.gUserId)}
                        };
                        HRSServer server = new HRSServer();
                        String result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_ORDERCHECKCOUNT, data);
                        Log.e("order=========", "" + result);
                        try {
                            ResultEntity manager = new ResultEntity(result);
                            if (manager.getId()==0){
                            ((Activity)mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tost();
                                }
                            });
                            }else {

                                ((Activity)mContext).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        chuyen();
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                            Log.e("Lỗi", "Lỗi");
                        }
                    }
                };
                Thread thread = new Thread(mTaskLoadOrder);
                thread.start();

            }
        };





        chk.setSpan(clickableSpan, 0, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mCheckOrder.setText(chk);
        mCheckOrder.setMovementMethod(LinkMovementMethod.getInstance());


        btnLogin = (Button) rootView.findViewById(R.id.btnLogin1);
        btnLogout = (Button) rootView.findViewById(R.id.logout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalApplication ga = (GlobalApplication) mContext.getApplicationContext();
                ga.gUserId = 0;
                mLogin.setVisibility(View.VISIBLE);
                mUser.setVisibility(View.GONE);
            }
        });
        return rootView;
    }
    private void chuyen(){

        Intent intent = new Intent(mContext, Activity_CheckOrder.class);
        startActivity(intent);

    }
    private void tost()
    {
        Toast.makeText(mContext, "Bạn chưa có đơn hàng nào", Toast.LENGTH_SHORT).show();


    }

}
