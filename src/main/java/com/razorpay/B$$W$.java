package com.razorpay;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import com.facebook.internal.ServerProtocol;
import java.util.Iterator;
import org.json.JSONObject;

final class B$$W$ implements O_$v$ {
    private boolean E$_j$;
    private boolean G__G_;
    private boolean Q_$2$;
    private WebView R$$r_;
    private Context a_$P$;
    private x_$Q_ d__1_;
    private T__j$ r$_Y_;

    B$$W$() {
    }

    B$$W$(Activity activity, WebView webView) {
        this.Q_$2$ = false;
        this.G__G_ = false;
        this.E$_j$ = false;
        this.a_$P$ = this.a_$P$;
        this.R$$r_ = webView;
        if (x_$Q_.a_$P$ == null) {
            x_$Q_.a_$P$ = new x_$Q_();
        }
        this.d__1_ = x_$Q_.a_$P$;
        this.d__1_.G__G_.add(this);
        this.r$_Y_ = new T__j$(activity);
        b_$A$.G__G_(f$_G$.f$_G$().L__R$(), new Callback() {
            public final void run(t_$J_ t__j_) {
                if (t__j_.a_$P$() != null) {
                    try {
                        String versionFromJsonString = BaseUtils.getVersionFromJsonString(t__j_.a_$P$(), T__j$.Q_$2$);
                        if (!BaseUtils.getLocalVersion(T__j$.this.d__1_, T__j$.Q_$2$).equals(versionFromJsonString)) {
                            b_$A$.G__G_(f$_G$.f$_G$().J$_0_(), new Callback(versionFromJsonString) {
                                public final void run(t_$J_ t__j_) {
                                    String decryptFile;
                                    if (t__j_.a_$P$() != null && (decryptFile = BaseUtils.decryptFile(t__j_.a_$P$())) != null) {
                                        if (BaseUtils.storeFileInInternal(T__j$.this.d__1_, BaseUtils.getVersionedAssetName(r3, f$_G$.f$_G$().g__v_()), t__j_.a_$P$())) {
                                            String unused = T__j$.this.G__G_ = decryptFile;
                                            BaseUtils.updateLocalVersion(T__j$.this.d__1_, T__j$.Q_$2$, r3);
                                        }
                                    }
                                }
                            });
                        }
                    } catch (Exception e) {
                        AnalyticsUtil.reportError(e, "error", "Could not extract version from server json");
                    }
                }
            }
        });
    }

    static JSONObject G__G_(boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("otp_read", z ? 1 : 0);
            return jSONObject;
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "error", e.getMessage());
            return null;
        }
    }

    public final void G__G_() {
        try {
            this.d__1_.G__G_.remove(this);
        } catch (Exception unused) {
        }
        Iterator<O_$v$> it = this.d__1_.G__G_.iterator();
        while (it.hasNext()) {
            it.next().setSmsPermission(false);
        }
    }

    public final void Q_$2$() {
        if (!this.E$_j$) {
            try {
                JSONObject l__d$ = f$_G$.f$_G$().l__d$();
                l__d$.put("merchant_key", (Object) null);
                l__d$.put("otp_permission", this.Q_$2$);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", f$_G$.R$$r_);
                jSONObject.put("version_code", f$_G$.Q_$2$);
                l__d$.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, jSONObject);
                StringBuilder sb = new StringBuilder("window.__rzp_options = ");
                sb.append(l__d$.toString());
                String obj = sb.toString();
                this.R$$r_.loadUrl(String.format("javascript: %s", new Object[]{obj}));
            } catch (Exception unused) {
            }
            String R$$r_2 = this.r$_Y_.R$$r_();
            this.R$$r_.loadUrl(String.format("javascript: %s", new Object[]{R$$r_2}));
            this.E$_j$ = true;
        }
    }

    public final void R$$r_() {
        this.E$_j$ = false;
    }

    /* access modifiers changed from: package-private */
    public final void a_$P$(boolean z) {
        this.G__G_ = z;
    }

    public final void setSmsPermission(boolean z) {
        this.Q_$2$ = z;
    }
}
