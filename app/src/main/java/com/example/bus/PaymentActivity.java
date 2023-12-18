package com.example.bus;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class PaymentActivity extends Activity implements PaymentResultWithDataListener, ExternalWalletListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Initialize the Razorpay checkout
        Checkout.preload(getApplicationContext());

        Button btnPay = findViewById(R.id.btnPay);



        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRazorpayPayment();
            }
        });
    }

    private void startRazorpayPayment() {


        final Activity activity = this;
        MyGlobalClass globalClass = MyGlobalClass.getInstance();
        String sharedPrice = globalClass.getSharedPrice();
        Integer i=Integer.valueOf(sharedPrice)*100;
        String sharedPricePaise = i.toString();


        final Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_vj6Bz0VFcgxdnI");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "MAD BUS");
            options.put("description", "Payment for XYZ");
            options.put("currency", "INR");
            options.put("amount",sharedPricePaise);
            options.put("prefill.email", "customer@example.com");
            options.put("prefill.contact", "XXXXXXXXXX");
            checkout.open(activity, options);
        } catch (Exception e) {
            e.printStackTrace();
            onPaymentSuccess1();
        }
    }

    @Override
    public void onExternalWalletSelected(String s, PaymentData paymentData) {
        try{
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try{
            Log.d("razorpay", "onPaymentSuccess: 12");
            Intent intent = new Intent(PaymentActivity.this, DownloadActivity.class);
            startActivity(intent);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onPaymentSuccess1() {
        try{
            Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
            startActivity(intent);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try{
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}