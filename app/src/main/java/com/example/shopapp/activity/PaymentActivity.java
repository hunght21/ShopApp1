package com.example.shopapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.shopapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.momo.momo_partner.AppMoMoLib;

public class PaymentActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView subTotal, discount, shipping, total;
    Button paymentBtn;

 //   int amount = 0;
    //private String amount = "0000";
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "DO QUANG HUNG";
    private String merchantCode = "#####";
    private String merchantNameLabel = "Nhà cung cấp";
    private String description = "Thanh toán mua hàng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        int amount = getIntent().getIntExtra("amount", 0);

        subTotal = findViewById(R.id.sub_total);
        discount = findViewById(R.id.textView17);
        shipping = findViewById(R.id.textView18);
        total = findViewById(R.id.total_amt);
        paymentBtn = findViewById(R.id.pay_btn);

        subTotal.setText(amount + " đ");
        total.setText(amount + " đ");

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPayment();
            }

            private void requestPayment() {

 //               final Activity activity = PaymentActivity.this;
                AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
                AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
                Map<String, Object> eventValue = new HashMap<>();

                eventValue.put("merchantname", merchantName); //Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
                eventValue.put("merchantcode", merchantCode); //Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
                eventValue.put("amount", amount); //Kiểu integer
                eventValue.put("orderId", "orderId123456789"); //uniqueue id cho Bill order, giá trị duy nhất cho mỗi đơn hàng
                eventValue.put("orderLabel", "Mã đơn hàng"); //gán nhãn

                //client Optional - bill info
                eventValue.put("merchantnamelabel", "Dịch vụ");//gán nhãn
//            eventValue.put("fee", total_fee); //Kiểu integer
                eventValue.put("description", description); //mô tả đơn hàng - short description

                //client extra data
                eventValue.put("requestId",  merchantCode+"merchant_billId_"+System.currentTimeMillis());
                eventValue.put("partnerCode", merchantCode);
                //Example extra data
                JSONObject objExtraData = new JSONObject();
                try {
                    objExtraData.put("site_code", "008");
                    objExtraData.put("site_name", "CGV Cresent Mall");
                    objExtraData.put("screen_code", 0);
                    objExtraData.put("screen_name", "Special");
                    objExtraData.put("movie_name", "Kẻ Trộm Mặt Trăng 3");
                    objExtraData.put("movie_format", "2D");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                eventValue.put("extraData", objExtraData.toString());

                eventValue.put("extra", "");
                AppMoMoLib.getInstance().requestMoMoCallBack(PaymentActivity.this, eventValue);
            }
        });


//Get token callback from MoMo app an submit to server side
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            if(requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
//                if(data != null) {
//                    if(data.getIntExtra("status", -1) == 0) {
//                        //TOKEN IS AVAILABLE
//                        tvMessage.setText("message: " + "Get token " + data.getStringExtra("message"));
//                        String token = data.getStringExtra("data"); //Token response
//                        String phoneNumber = data.getStringExtra("phonenumber");
//                        String env = data.getStringExtra("env");
//                        if(env == null){
//                            env = "app";
//                        }
//
//                        if(token != null && !token.equals("")) {
//                            // TODO: send phoneNumber & token to your server side to process payment with MoMo server
//                            // IF Momo topup success, continue to process your order
//                        } else {
//                            tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
//                        }
//                    } else if(data.getIntExtra("status", -1) == 1) {
//                        //TOKEN FAIL
//                        String message = data.getStringExtra("message") != null?data.getStringExtra("message"):"Thất bại";
//                        tvMessage.setText("message: " + message);
//                    } else if(data.getIntExtra("status", -1) == 2) {
//                        //TOKEN FAIL
//                        tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
//                    } else {
//                        //TOKEN FAIL
//                        tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
//                    }
//                } else {
//                    tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
//                }
//            } else {
//                tvMessage.setText("message: " + this.getString(R.string.not_receive_info_err));
//            }
//        }
    }
}