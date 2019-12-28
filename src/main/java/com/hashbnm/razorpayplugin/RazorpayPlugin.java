package com.hashbnm.razorpayplugin;

import android.app.Activity;
import android.content.Intent;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RazorpayPlugin implements MethodChannel.MethodCallHandler, PluginRegistry.ActivityResultListener {
    private Activity activity;
    private Map<String, Object> arguments;
    private final MethodChannel channel;
    private MethodChannel.Result pendingResult;

    public RazorpayPlugin(Activity activity2, MethodChannel methodChannel) {
        this.activity = activity2;
        this.channel = methodChannel;
        this.channel.setMethodCallHandler(this);
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        MethodChannel methodChannel = new MethodChannel(registrar.messenger(), "razorpay_plugin");
        RazorpayPlugin razorpayPlugin = new RazorpayPlugin(registrar.activity(), methodChannel);
        methodChannel.setMethodCallHandler(razorpayPlugin);
        registrar.addActivityResultListener(razorpayPlugin);
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        String str;
        String str2;
        HashMap hashMap;
        String str3;
        if (i != 8888) {
            return false;
        }
        if (i2 == -1) {
            hashMap = new HashMap();
            str2 = intent.getStringExtra("payment_id");
            str = Constant.PARAM_ERROR_CODE;
            str3 = AppEventsConstants.EVENT_PARAM_VALUE_YES;
        } else {
            hashMap = new HashMap();
            str2 = intent.getStringExtra("payment_id");
            str = Constant.PARAM_ERROR_CODE;
            str3 = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        hashMap.put(str, str3);
        hashMap.put("message", str2);
        this.pendingResult.success(hashMap);
        this.pendingResult = null;
        this.arguments = null;
        return true;
    }

    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        this.pendingResult = result;
        if (methodCall.method.equals("RazorPayForm")) {
            this.arguments = (Map) methodCall.arguments;
            Intent intent = new Intent(this.activity, RazorpayActivity.class);
            intent.putExtra(RazorpayActivity.EXTRA_PRODUCT_NAME, (String) this.arguments.get("name"));
            intent.putExtra(RazorpayActivity.EXTRA_PRODUCT_IMAGE, (String) this.arguments.get(MessengerShareContentUtility.MEDIA_IMAGE));
            intent.putExtra(RazorpayActivity.EXTRA_PRODUCT_DESCRIPTION, (String) this.arguments.get("description"));
            intent.putExtra(RazorpayActivity.EXTRA_PRODUCT_AMOUNT, (String) this.arguments.get("amount"));
            intent.putExtra(RazorpayActivity.EXTRA_PREFILL_EMAIL, (String) this.arguments.get("email"));
            intent.putExtra(RazorpayActivity.EXTRA_THEME, (String) this.arguments.get("theme"));
            intent.putExtra(RazorpayActivity.EXTRA_NOTES, (Serializable) this.arguments.get("notes"));
            intent.putExtra(RazorpayActivity.EXTRA_PREFILL_CONTACT, (String) this.arguments.get("contact"));
            intent.putExtra(RazorpayActivity.RAZORPAY_KEY, (String) this.arguments.get("api_key"));
            this.activity.startActivityForResult(intent, 8888);
            return;
        }
        result.notImplemented();
    }
}
