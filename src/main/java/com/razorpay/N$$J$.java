package com.razorpay;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.JavascriptInterface;

final class N$$J$ {
    private SharedPreferences.Editor G__G_;
    private SharedPreferences d__1_;

    N$$J$(Context context) {
        this.d__1_ = Q__v$.E$_j$(context);
        this.G__G_ = Q__v$.R$$r_(context);
    }

    @JavascriptInterface
    public final boolean getBoolean(String str) {
        try {
            return this.d__1_.getBoolean(str, false);
        } catch (Exception unused) {
            return false;
        }
    }

    @JavascriptInterface
    public final float getFloat(String str) {
        try {
            return this.d__1_.getFloat(str, 0.0f);
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    @JavascriptInterface
    public final int getInt(String str) {
        try {
            return this.d__1_.getInt(str, 0);
        } catch (Exception unused) {
            return 0;
        }
    }

    @JavascriptInterface
    public final String getString(String str) {
        try {
            return this.d__1_.getString(str, (String) null);
        } catch (Exception unused) {
            return null;
        }
    }

    @JavascriptInterface
    public final void setBoolean(String str, boolean z) {
        try {
            this.G__G_.putBoolean(str, z);
            this.G__G_.commit();
        } catch (Exception unused) {
        }
    }

    @JavascriptInterface
    public final void setFloat(String str, float f) {
        try {
            this.G__G_.putFloat(str, f);
            this.G__G_.commit();
        } catch (Exception unused) {
        }
    }

    @JavascriptInterface
    public final void setInt(String str, int i) {
        try {
            this.G__G_.putInt(str, i);
            this.G__G_.commit();
        } catch (Exception unused) {
        }
    }

    @JavascriptInterface
    public final void setString(String str, String str2) {
        try {
            this.G__G_.putString(str, str2);
            this.G__G_.commit();
        } catch (Exception unused) {
        }
    }
}
