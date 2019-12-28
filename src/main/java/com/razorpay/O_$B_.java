package com.razorpay;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import org.json.JSONObject;

public class O_$B_ {
    private String B$$W$;
    private String B_$q$;
    private boolean D$_X_;
    private String E$_6$;
    private JSONObject E$_j$;
    private boolean G__G_;
    private int J$_0_;
    private int K$$z$;
    private String L__R$;
    private boolean O_$B_;
    private boolean O__Y_;
    private String Q_$2$;
    private String R$$r_;
    private String Y$_o$;
    private String a_$P$;
    private String b__J_;
    private JSONObject c__C_;
    private String d__1_;
    private Boolean f$_G$;
    private String g__v_;
    private String l_$w$;
    private String l__d$;
    private boolean r$_Y_;

    O_$B_() {
    }

    static JSONObject Q_$2$(Context context, int i) {
        InputStream openRawResource;
        String string = Q__v$.G__G_(context).getString("rzp_config_json", (String) null);
        if (string == null) {
            try {
                openRawResource = context.getResources().openRawResource(i);
                StringWriter stringWriter = new StringWriter();
                char[] cArr = new char[1024];
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource, "UTF-8"));
                while (true) {
                    int read = bufferedReader.read(cArr);
                    if (read == -1) {
                        break;
                    }
                    stringWriter.write(cArr, 0, read);
                }
                openRawResource.close();
                string = stringWriter.toString();
            } catch (Exception unused) {
                return null;
            } catch (Throwable th) {
                openRawResource.close();
                throw th;
            }
        }
        return new JSONObject(string);
    }

    /* access modifiers changed from: package-private */
    public final Boolean B$$W$() {
        return Boolean.valueOf(this.D$_X_);
    }

    /* access modifiers changed from: package-private */
    public final int D$_X_() {
        return this.K$$z$;
    }

    public final String E$_6$() {
        return this.a_$P$;
    }

    /* access modifiers changed from: package-private */
    public final JSONObject E$_j$() {
        return this.E$_j$;
    }

    public void G__G_(JSONObject jSONObject) {
        try {
            this.K$$z$ = ((Integer) BaseUtils.getJsonValue("update_sdk_config.latest_version", jSONObject, (Object) 1)).intValue();
            this.B_$q$ = (String) BaseUtils.getJsonValue("update_sdk_config.msg", jSONObject, (Object) "");
            this.O__Y_ = ((Boolean) BaseUtils.getJsonValue("update_sdk_config.enable_alert", jSONObject, (Object) Boolean.TRUE)).booleanValue();
            this.Q_$2$ = (String) BaseUtils.getJsonValue("config_end_point", jSONObject, (Object) "");
            this.G__G_ = ((Boolean) BaseUtils.getJsonValue("enable", jSONObject, (Object) "")).booleanValue();
            this.L__R$ = (String) BaseUtils.getJsonValue("permissions.custom_message", jSONObject, (Object) "");
            this.f$_G$ = Boolean.valueOf(((Boolean) BaseUtils.getJsonValue("permissions.enable_custom_message", jSONObject, (Object) Boolean.FALSE)).booleanValue());
            this.J$_0_ = ((Integer) BaseUtils.getJsonValue("permissions.max_ask_count", jSONObject, (Object) 0)).intValue();
            this.r$_Y_ = ((Boolean) BaseUtils.getJsonValue("analytics.lumberjack.enable", jSONObject, (Object) Boolean.TRUE)).booleanValue();
            this.R$$r_ = (String) BaseUtils.getJsonValue("analytics.lumberjack.key", jSONObject, (Object) "");
            this.B$$W$ = (String) BaseUtils.getJsonValue("analytics.lumberjack.end_point", jSONObject, (Object) "");
            this.a_$P$ = (String) BaseUtils.getJsonValue("analytics.lumberjack.sdk_identifier", jSONObject, (Object) "");
            this.D$_X_ = ((Boolean) BaseUtils.getJsonValue("otpelf.enable", jSONObject, (Object) Boolean.TRUE)).booleanValue();
            this.E$_j$ = (JSONObject) BaseUtils.getJsonValue("otpelf.settings", jSONObject, (Object) new JSONObject());
            this.b__J_ = (String) BaseUtils.getJsonValue("otpelf.endpoint", jSONObject, (Object) "https://cdn.razorpay.com/static/otpelf/");
            this.l_$w$ = (String) BaseUtils.getJsonValue("otpelf.version_file_name", jSONObject, (Object) "version.json");
            this.Y$_o$ = (String) BaseUtils.getJsonValue("otpelf.js_file_name", jSONObject, (Object) "otpelf.js");
            this.O_$B_ = ((Boolean) BaseUtils.getJsonValue("magic.enable", jSONObject, (Object) Boolean.TRUE)).booleanValue();
            this.c__C_ = (JSONObject) BaseUtils.getJsonValue("magic.settings", jSONObject, (Object) new JSONObject());
            this.E$_6$ = (String) BaseUtils.getJsonValue("magic.endpoint", jSONObject, (Object) "https://cdn.razorpay.com/static/magic/");
            this.g__v_ = (String) BaseUtils.getJsonValue("magic.version_file_name", jSONObject, (Object) "version.json");
            this.l__d$ = (String) BaseUtils.getJsonValue("magic.js_file_name", jSONObject, (Object) "magic.js");
            this.d__1_ = (String) BaseUtils.getJsonValue("checkout.end_point", jSONObject, (Object) "");
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean G__G_() {
        return this.G__G_;
    }

    public final String J$_0_() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.E$_6$);
        sb.append(this.l__d$);
        return sb.toString();
    }

    public final String L__R$() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.E$_6$);
        sb.append(this.g__v_);
        return sb.toString();
    }

    public final String O_$B_() {
        return this.Y$_o$;
    }

    /* access modifiers changed from: package-private */
    public final String Q_$2$() {
        return this.Q_$2$;
    }

    public final String R$$r_() {
        return this.B$$W$;
    }

    /* access modifiers changed from: package-private */
    public final String Y$_o$() {
        StringBuilder sb = new StringBuilder("https://api.razorpay.com");
        sb.append(this.d__1_);
        return sb.toString();
    }

    public final String a_$P$() {
        return this.R$$r_;
    }

    /* access modifiers changed from: package-private */
    public final String b__J_() {
        return this.B_$q$;
    }

    public final String c__C_() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b__J_);
        sb.append(this.l_$w$);
        return sb.toString();
    }

    public final Boolean d__1_() {
        return Boolean.valueOf(this.r$_Y_);
    }

    /* access modifiers changed from: package-private */
    public final String g__v_() {
        return this.l__d$;
    }

    public final String l_$w$() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b__J_);
        sb.append(this.Y$_o$);
        return sb.toString();
    }

    public final JSONObject l__d$() {
        return this.c__C_;
    }

    /* access modifiers changed from: package-private */
    public final boolean r$_Y_() {
        return this.O__Y_;
    }
}
