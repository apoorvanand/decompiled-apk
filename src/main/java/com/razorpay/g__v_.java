package com.razorpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import com.facebook.internal.NativeProtocol;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

final class g__v_ {
    private static Dialog R$$r_;

    interface d__1_ {
        void a_$P$();

        void d__1_();
    }

    g__v_() {
    }

    static String G__G_(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&" : "?");
        sb.append(str2);
        String obj = sb.toString();
        if (str3 == null) {
            return obj;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(obj);
        sb2.append("=");
        sb2.append(str3);
        return sb2.toString();
    }

    static JSONArray G__G_(Context context) {
        List<ResolveInfo> listOfAppsWhichHandleDeepLink = BaseUtils.getListOfAppsWhichHandleDeepLink(context, "upi://pay");
        if (listOfAppsWhichHandleDeepLink == null || listOfAppsWhichHandleDeepLink.size() <= 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (ResolveInfo R$$r_2 : listOfAppsWhichHandleDeepLink) {
            jSONArray.put(R$$r_(context, R$$r_2));
        }
        return jSONArray;
    }

    static void Q_$2$(Context context, String str, String str2, String str3, final d__1_ d__1_2) {
        new AlertDialog.Builder(context).setMessage(str).setPositiveButton(str2, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                d__1_2.a_$P$();
            }
        }).setNegativeButton(str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                d__1_2.d__1_();
            }
        }).show();
    }

    private static JSONObject R$$r_(Context context, ResolveInfo resolveInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("package_name", resolveInfo.activityInfo.packageName);
            jSONObject.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, BaseUtils.getAppNameOfResolveInfo(resolveInfo, context));
            jSONObject.put("app_icon", BaseUtils.getBase64FromOtherAppsResource(context, resolveInfo.activityInfo.packageName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    static void R$$r_(Context context) {
        if (f$_G$.f$_G$().J$$A_() && context != null && !((Activity) context).isFinishing()) {
            Dialog dialog = R$$r_;
            if (dialog == null || !dialog.isShowing()) {
                Dialog dialog2 = new Dialog(context);
                R$$r_ = dialog2;
                dialog2.requestWindowFeature(1);
                R$$r_.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                R$$r_.setContentView(R.layout.rzp_loader);
                ((CircularProgressView) R$$r_.findViewById(R.id.progressBar)).setColor(Color.parseColor(f$_G$.f$_G$().h__y_()));
                ((LinearLayout) R$$r_.findViewById(R.id.ll_loader)).setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        g__v_.d__1_();
                    }
                });
                try {
                    R$$r_.show();
                } catch (Exception unused) {
                }
            }
        }
    }

    static void a_$P$(Context context, String str, String str2) {
        SharedPreferences.Editor Q_$2$ = Q__v$.Q_$2$(context);
        if (str2 == null) {
            Q_$2$.remove("pref_merchant_options_".concat(String.valueOf(str)));
        } else {
            Q_$2$.putString("pref_merchant_options_".concat(String.valueOf(str)), str2);
        }
        Q_$2$.apply();
    }

    static void d__1_() {
        Dialog dialog = R$$r_;
        if (dialog != null) {
            if (dialog.isShowing()) {
                try {
                    R$$r_.dismiss();
                } catch (Exception unused) {
                }
            }
            R$$r_ = null;
        }
    }
}
