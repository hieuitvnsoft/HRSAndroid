package com.example.hrs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrs.entities.Order;
import com.example.hrs.entities.PaymentMethod;
import com.example.hrs.entities.PaymentMethodEntityManager;
import com.example.hrs.entities.ProductType;
import com.example.hrs.entities.ProductTypeEntityManager;
import com.example.hrs.entities.ShipMethod;
import com.example.hrs.entities.ShipMethodEntityManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class InforPaymentActivity extends AppCompatActivity {

    Context mContext;
    EditText mFullName, mAddress, mPhone, mDesscription;
    TextView mTotal;
    Spinner mSpinPayment, mSpinShip;
    List<PaymentMethod> mListPayment;
    List<ShipMethod> mListShip;
    Button mPayments;
    Runnable tskLoadPaymentMethod = new Runnable() {
        @Override
        public void run() {
            HRSServer server = new HRSServer();
            final String result1 = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_PAYMENTMETHOD, new String[][]{});
            Log.e("Load:", "###Lỗi paymaent" + result1);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PaymentMethodEntityManager m = null;
                    try {
                        m = new PaymentMethodEntityManager(result1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mListPayment = m.lstP;
                    ArrayAdapter<PaymentMethod> adapter = new ArrayAdapter<PaymentMethod>(mContext, android.R.layout.simple_spinner_item, mListPayment);
                    mSpinPayment.setAdapter(adapter);
                }
            });
        }
    };
    Runnable tskLoadShipMethod = new Runnable() {
        @Override
        public void run() {
            HRSServer server = new HRSServer();
            final String result1 = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_SHIPMETHOD, new String[][]{});
            // Log.e("Hieu xau giai:", "###" + result1);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ShipMethodEntityManager m = null;
                    try {
                        m = new ShipMethodEntityManager(result1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mListShip = m.lstP;
                    ArrayAdapter<ShipMethod> adapter = new ArrayAdapter<ShipMethod>(mContext, android.R.layout.simple_spinner_item, mListShip);
                    mSpinShip.setAdapter(adapter);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_payment);
        this.mContext = InforPaymentActivity.this;
        final GlobalApplication globalApplication = (GlobalApplication) getApplicationContext();
        mFullName = (EditText) findViewById(R.id.FullName);
        mAddress = (EditText) findViewById(R.id.Adress);
        mPhone = (EditText) findViewById(R.id.Phone);
        mDesscription = (EditText) findViewById(R.id.Desscription);
        mSpinPayment = (Spinner) findViewById(R.id.cboPayment);
        mSpinShip = (Spinner) findViewById(R.id.cboShip);
        mPayments = (Button) findViewById(R.id.Submit);
        mTotal = (TextView) findViewById(R.id.txtTotal);
        if (globalApplication.gUserId == 0) {
            mFullName.setText("");
            mAddress.setText("");
            mPhone.setText("");
            mDesscription.setText("");


        } else {
            mFullName.setText(globalApplication.gFullName);
            mAddress.setText(globalApplication.gAddress);
            mPhone.setText(globalApplication.gPhone);


        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Double totalAmountOrder = 0.0;
                for (int i = 0; i < globalApplication.gGioHang.size(); i++) {
                    totalAmountOrder += globalApplication.gGioHang.get(i).getAmount();
                }
                NumberFormat f = NumberFormat.getInstance(new Locale("vi", "VN"));
                mTotal.setText(f.format(totalAmountOrder));
            }
        });



//        mSpinShip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                ShipMethod shipMethod = (ShipMethod) mSpinShip.getSelectedItem();
//                if (shipMethod.getShipMethodId()==2){
//                    runOnUiThread();
//                }
//            }
//        });

        Thread t1 = new Thread(tskLoadPaymentMethod);
        t1.start();
        Thread t2 = new Thread(tskLoadShipMethod);
        t2.start();





        mPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String total = String.valueOf(mTotal.getText()+"").replace(".","");
                final Integer id = globalApplication.gUserId;
                final String fullName = String.valueOf(mFullName.getText() + "");
                final String add = String.valueOf(mAddress.getText()+"");
                final String phone = String.valueOf(mPhone.getText() + "");
                final String dess = String.valueOf(mDesscription.getText() + "");
                final PaymentMethod paymentMethod = (PaymentMethod) mSpinPayment.getSelectedItem();
                final ShipMethod shipMethod = (ShipMethod) mSpinShip.getSelectedItem();
                if (fullName.length() == 0 || add.length() == 0 || phone.length() == 0) {

                    Toast.makeText(mContext, "Yêu cầu nhập đầy đủ thông tin thanh toán", Toast.LENGTH_SHORT).show();
                } else {
                    Runnable taskPayment = new Runnable() {
                        @Override
                        public void run() {


//                            String d[][] = {
//                                    {"userId", String.valueOf(id)},
//                                    {"fullName", fullName},
//                                    {"addressShip", add},
//                                    {"phone", phone},
//                                    {"description", dess},
//                                    {"paymentMethod", String.valueOf(paymentMethod.getPaymentMethodId())},
//                                    {"shipMethod", String.valueOf(shipMethod.getShipMethodId())},
//                                    {"detail", globalApplication.getJSONFromCart()},
//                                    {"totalAmountOrder", total}
//                            };
                            JSONObject object = new JSONObject();
                            try {
                                object.put("userId",String.valueOf(id));
                                object.put("fullName",fullName);
                                object.put("addressShip",add);
                                object.put("phone",phone);
                                object.put("description",dess);
                                object.put("paymentMethod",String.valueOf(paymentMethod.getPaymentMethodId()));
                                object.put("shipMethod",String.valueOf(shipMethod.getShipMethodId()));
                                object.put("totalAmountOrder",total);
                                object.put("detail", globalApplication.getJSONFromCart());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String data[][] = {
                                    {"data", object.toString()}
                            };
                            HRSServer server = new HRSServer();
                            final String result = server.sendRequest(1, HRSConstant.HOSTING_API + HRSConstant.PAGE_CART, data);
                           // Log.e("Đơn hàng", "=============" + result);
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
                                    if (resultEntity.id!=0){
                                        globalApplication.gGioHang.clear();
                                        Intent intent = new Intent(mContext,MainActivity.class);
                                        startActivity(intent);
                                    }
                                    Toast.makeText(mContext, "Kết quả ĐẶT HÀNG: " +
                                            resultEntity.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                    };
                    Thread tpm = new Thread(taskPayment);
                    tpm.start();
                }


            }
        });
    }
}
