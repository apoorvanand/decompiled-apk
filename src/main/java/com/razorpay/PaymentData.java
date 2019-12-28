package com.razorpay;

import java.io.Serializable;
import org.json.JSONObject;

public class PaymentData implements Serializable {
    private JSONObject D$_X_ = new JSONObject();
    private String G__G_;
    private String Q_$2$;
    private String R$$r_;
    private String a_$P$;
    private String b__J_;
    private String d__1_;

    /* access modifiers changed from: package-private */
    public final void E$_j$(String str) {
        this.b__J_ = str;
    }

    /* access modifiers changed from: package-private */
    public final void G__G_(String str) {
        this.a_$P$ = str;
    }

    /* access modifiers changed from: package-private */
    public final void Q_$2$(String str) {
        this.d__1_ = str;
    }

    /* access modifiers changed from: package-private */
    public final void Q_$2$(JSONObject jSONObject) {
        this.D$_X_ = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public final void R$$r_(String str) {
        this.R$$r_ = str;
    }

    /* access modifiers changed from: package-private */
    public final void a_$P$(String str) {
        this.G__G_ = str;
    }

    /* access modifiers changed from: package-private */
    public final void d__1_(String str) {
        this.Q_$2$ = str;
    }

    public JSONObject getData() {
        return this.D$_X_;
    }

    public String getExternalWallet() {
        return this.b__J_;
    }

    public String getOrderId() {
        return this.G__G_;
    }

    public String getPaymentId() {
        return this.R$$r_;
    }

    public String getSignature() {
        return this.Q_$2$;
    }

    public String getUserContact() {
        return this.a_$P$;
    }

    public String getUserEmail() {
        return this.d__1_;
    }
}
