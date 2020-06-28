package com.example.hrs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrs.entities.ProductEntityManager;
import com.example.hrs.entities.UserEntity;
import com.example.hrs.entities.UserEntityManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    RelativeLayout rActivity, rActivity2;
    Context mContext;
    Button btnLogin, btnRegister, btnBackLogin, btnRegis2;
    EditText txtUserName, txtPass;
    TextView mMess;
    EditText mFullname, mtxtUserName, mPass, mAddress, mPhone, mEmail, mBirrth;
    RadioButton rdNam, rdNu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        rActivity = (RelativeLayout) findViewById(R.id.Login);
        rActivity2 = (RelativeLayout) findViewById(R.id.Regis);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegis2 = (Button) findViewById(R.id.Register2);
        btnBackLogin = (Button) findViewById(R.id.BackLogin);
        btnRegister = (Button) findViewById(R.id.Register);
        this.mContext = LoginActivity.this;
        txtUserName = (EditText) findViewById(R.id.txtUsername);
        txtPass = (EditText) findViewById(R.id.txtPassword);
        mMess = (TextView) findViewById(R.id.mess);
        rActivity.setVisibility(View.VISIBLE);
        rActivity2.setVisibility(View.GONE);

        //Regiss
        mFullname = (EditText) findViewById(R.id.txtName);
        mtxtUserName = (EditText) findViewById(R.id.txtUsername2);
        mPass = (EditText) findViewById(R.id.txtPass);
        mAddress = (EditText) findViewById(R.id.txtAddress);
        mPhone = (EditText) findViewById(R.id.txtPhone);
        mEmail = (EditText) findViewById(R.id.txtEmail);
        mBirrth = (EditText) findViewById(R.id.txtBirthday);
        rdNam = (RadioButton) findViewById(R.id.rdNam);
        rdNu = (RadioButton) findViewById(R.id.rdNu);


        String result;

        btnRegis2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name, username, pass, address, phone, email, birth;
                final boolean sex;
                name = String.valueOf(mFullname.getText() + "");
                username = String.valueOf(mtxtUserName.getText() + "");
                pass = String.valueOf(mPass.getText() + "");
                address = String.valueOf(mAddress.getText() + "");
                phone = String.valueOf(mPhone.getText() + "");
                email = String.valueOf(mEmail.getText() + "");
                birth = String.valueOf(mBirrth.getText() + "");
                if (rdNam.isChecked()) {
                    sex = true;
                } else {

                    sex = false;
                }

                if (name.length() == 0 || username.length() == 0 || pass.length() == 0 || phone.length() == 0 || email.length() == 0) {

                    Toast.makeText(mContext, "Cấn nhập đầy đủ thông tin để đăng ký", Toast.LENGTH_SHORT).show();
                } else {
                    if (username.length() < 6) {

                        Toast.makeText(mContext, "Tên đăng nhập phải 6 ký tự trở lên", Toast.LENGTH_SHORT).show();
                    } else if (address.length() < 10) {

                        Toast.makeText(mContext, "Địa chỉ phải trên 10 ký tự", Toast.LENGTH_SHORT).show();
                    } else if (validateEmailAddress(email)==false) {
                        Toast.makeText(mContext, "Email sai định dạng xin kiểm tra lại", Toast.LENGTH_SHORT).show();
                    }  else if (pass.length() < 6) {
                        Toast.makeText(mContext, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_SHORT).show();

                    } else {
                        Runnable taskResgister = new Runnable() {
                            @Override
                            public void run() {
//

                                JSONObject object = new JSONObject();
                                try {
                                    object.put("username", username);
                                    object.put("password", pass);
                                    object.put("fullName", name);
                                    object.put("email", email);
                                    object.put("birthDay", birth);
                                    object.put("address", address);
                                    object.put("sex", sex);
                                    object.put("avatar", "HRS/Views/Admin/images/product/user.png");
                                    object.put("phone", phone);
                                    object.put("point", 0);
                                    object.put("status", 1);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
//                            byte[] data = object.toString().getBytes();
//                            String base64 = Base64.encodeToString(data, Base64.DEFAULT);

// Receiving side
//                            byte[] data = Base64.decode(base64, Base64.DEFAULT);
//                            String text = new String(data, StandardCharsets.UTF_8);
                                String data[][] = {
                                        {"data", object.toString()}
                                };

                                //String data[][] = {{"data" ,"{"+d+"}"}};
                                Log.e("data", "dataa====" + object.toString());
                                HRSServer server = new HRSServer();
                                final String result;

                                result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_REGISTER, data);
                                Log.e("====================SSS", "ADS" + result);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ResultEntity resultEntity = null;
                                        try {
                                            resultEntity = new ResultEntity(result);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        if (resultEntity == null) {
                                            Log.e("MinhVT", "NULL");
                                        }
                                        Toast.makeText(LoginActivity.this, "Kết quả dăng ký: " +
                                                resultEntity.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        };

                        Thread thread = new Thread(taskResgister);
                        thread.start();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rActivity.setVisibility(View.GONE);
                rActivity2.setVisibility(View.VISIBLE);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (ga.gUserId)
                final String username = txtUserName.getText().toString();
                final String pass = txtPass.getText().toString();
                if (username.length() == 0 || pass.length() == 0) {
                    Toast.makeText(mContext, "Bạn chưa nhập tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    txtUserName.forceLayout();

                } else {
                    final Runnable mTaskLogin = new Runnable() {
                        @Override
                        public void run() {
                            String data[][] = {
                                    {"username", username},
                                    {"password", pass}
                            };
                            HRSServer server = new HRSServer();
                            final String result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_INDEX, data);
                            Log.e("Logon....", "" + result);

                            if (result != null || result != "" || result != "null") {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {

                                            GlobalApplication ga = (GlobalApplication) getApplicationContext();
                                            UserEntityManager manager = new UserEntityManager(result);
                                            UserEntity user = manager.lstUser;
                                            Log.e("SS", user + "");
                                            if (user != null) {

                                                Log.e("BKAP", "Tên : " + user.getFullName());
                                                ga.gUserId = user.getUserId();
                                                ga.gFullName = user.getFullName();
                                                ga.gAddress = user.getAddress();
                                                ga.gEmail = user.getEmail();
                                                ga.gBirthDay = user.getBirthDay();
                                                Log.e("BKAP", "Tên : " + user.getBirthDay());
                                                ga.gSex = user.isSex();
                                                ga.gAvatar = user.getAvatar();
                                                ga.gPhone = user.getPhone();


                                            }
                                        } catch (JSONException e) {
                                            Log.e("Loi", "Loi e:" + e.getMessage());
                                            Log.e("Loi", "Loi e:" + e.toString());
                                            e.printStackTrace();
                                        }
                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        startActivity(intent);
                                    }

                                });


                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mMess.setText("Sai tài khoản hoặc mật khẩu...Xin kiểm tra lại!");
                                    }
                                });


                            }

                        }


                    };
                    Thread thLogin = new Thread(mTaskLogin);
                    thLogin.start();

                }
            }
        });

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rActivity.setVisibility(View.VISIBLE);
                rActivity2.setVisibility(View.GONE);
            }
        });


    }

    public void openDatePicker(View vvvvvv) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mBirrth.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(LoginActivity.this, listener,
                2002, 2, 2);
        dialog.show();
    }


    Pattern pphone = Pattern.compile("(09|01[2|6|8|9])+([0-9]{8})\b");
    Pattern pemail = Pattern.compile("^(.+)@(.+)$");

    public boolean validateEmailAddress(String c) {
        boolean mtch = c.matches(String.valueOf(pemail));
        if (mtch) {
            return true;
        }
        return false;
    }

    public boolean validatephone(String c) {
        boolean mtch = c.matches(String.valueOf(pphone));
        if (mtch) {
            return true;
        }
        return false;
    }
        boolean chk= true;
    public boolean validateuser(final String username) {
        Runnable taskCheckRegis = new Runnable() {
            @Override
            public void run() {
                String data[][] = {
                        {"username", username}
                };
                HRSServer server = new HRSServer();
                final String result;

                result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_CHECKREGISSTER, data);
                //Log.e("====================SSS", "ADS" + result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ResultEntity resultEntity = null;
                        try {
                            resultEntity = new ResultEntity(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (resultEntity == null) {
                            Log.e("MinhVT", "NULL");
                        }
//                Toast.makeText(LoginActivity.this, "Kết quả dăng ký: " +
//                        resultEntity.getMessage(), Toast.LENGTH_SHORT).show();
                        if (resultEntity.getMessage()=="true"){
                            chk= true;
                        }else {
                            chk= false;
                        }
                    }
                });
            }
        };
        Thread tc = new Thread();
        tc.start();

        return chk;
    }


}
