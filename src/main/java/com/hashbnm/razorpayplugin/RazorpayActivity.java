package com.hashbnm.razorpayplugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class RazorpayActivity extends Activity implements PaymentResultListener {
    public static String EXTRA_NOTES = "notes";
    public static String EXTRA_PREFILL_CONTACT = "contact";
    public static String EXTRA_PREFILL_EMAIL = "email";
    public static String EXTRA_PRODUCT_AMOUNT = "amount";
    public static String EXTRA_PRODUCT_DESCRIPTION = "description";
    public static String EXTRA_PRODUCT_IMAGE = "image";
    public static String EXTRA_PRODUCT_NAME = "name";
    public static String EXTRA_THEME = "theme";
    public static String PAYMENT_ID = "payment_id";
    public static String RAZORPAY_KEY = "api_key";
    private static final String TAG = "RazorpayActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_razorpay);
        Intent intent = getIntent();
        Checkout.preload(getApplicationContext());
        startPayment(intent);
    }

    public void onPaymentError(int i, String str) {
        try {
            Intent intent = new Intent();
            intent.putExtra(PAYMENT_ID, str);
            setResult(0, intent);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    public void onPaymentSuccess(String str) {
        try {
            Intent intent = new Intent();
            intent.putExtra(PAYMENT_ID, str);
            setResult(-1, intent);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    public void startPayment(Intent intent) {
        Checkout checkout = new Checkout();
        checkout.setKeyID(intent.getStringExtra(RAZORPAY_KEY));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(EXTRA_PRODUCT_NAME, intent.getStringExtra(EXTRA_PRODUCT_NAME));
            jSONObject.put(EXTRA_PRODUCT_DESCRIPTION, intent.getStringExtra(EXTRA_PRODUCT_DESCRIPTION));
            jSONObject.put(EXTRA_PRODUCT_IMAGE, intent.getStringExtra(EXTRA_PRODUCT_IMAGE));
            jSONObject.put(FirebaseAnalytics.Param.CURRENCY, "INR");
            jSONObject.put(EXTRA_PRODUCT_AMOUNT, intent.getStringExtra(EXTRA_PRODUCT_AMOUNT));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("color", intent.getStringExtra(EXTRA_THEME));
            jSONObject.put("theme", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(EXTRA_PREFILL_EMAIL, intent.getStringExtra(EXTRA_PREFILL_EMAIL));
            jSONObject3.put(EXTRA_PREFILL_CONTACT, intent.getStringExtra(EXTRA_PREFILL_CONTACT));
            jSONObject.put("prefill", jSONObject3);
            if (intent.getSerializableExtra(EXTRA_NOTES) != null) {
                JSONObject jSONObject4 = new JSONObject();
                for (Map.Entry entry : ((HashMap) intent.getSerializableExtra(EXTRA_NOTES)).entrySet()) {
                    jSONObject4.put((String) entry.getKey(), (String) entry.getValue());
                }
                jSONObject.put(EXTRA_NOTES, jSONObject4);
            }
            checkout.open(this, jSONObject);
        } catch (Exception e) {
            Toast.makeText(this, "Error in payment: " + e.getMessage(), 0).show();
            e.printStackTrace();
        }
    }
}
