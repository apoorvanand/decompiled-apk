package com.razorpay;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

final class O__Y_ {
    private byte[] Q_$2$ = new byte[16];
    private byte[] a_$P$ = new byte[32];
    private Cipher d__1_ = Cipher.getInstance("AES/CBC/PKCS5Padding");

    enum d__1_ {
        ENCRYPT,
        DECRYPT
    }

    O__Y_() {
    }

    private String R$$r_(String str, String str2, d__1_ d__1_2, String str3) {
        String str4 = "";
        int length = str2.getBytes("UTF-8").length;
        int length2 = str2.getBytes("UTF-8").length;
        byte[] bArr = this.a_$P$;
        if (length2 > bArr.length) {
            length = bArr.length;
        }
        int length3 = str3.getBytes("UTF-8").length;
        int length4 = str3.getBytes("UTF-8").length;
        byte[] bArr2 = this.Q_$2$;
        if (length4 > bArr2.length) {
            length3 = bArr2.length;
        }
        System.arraycopy(str2.getBytes("UTF-8"), 0, this.a_$P$, 0, length);
        System.arraycopy(str3.getBytes("UTF-8"), 0, this.Q_$2$, 0, length3);
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.a_$P$, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(this.Q_$2$);
        if (d__1_2.equals(d__1_.ENCRYPT)) {
            this.d__1_.init(1, secretKeySpec, ivParameterSpec);
            str4 = Base64.encodeToString(this.d__1_.doFinal(str.getBytes("UTF-8")), 2);
        }
        if (!d__1_2.equals(d__1_.DECRYPT)) {
            return str4;
        }
        this.d__1_.init(2, secretKeySpec, ivParameterSpec);
        return new String(this.d__1_.doFinal(Base64.decode(str.getBytes(), 2)));
    }

    /* access modifiers changed from: package-private */
    public final String G__G_(String str, String str2, String str3) {
        return R$$r_(str, str2, d__1_.ENCRYPT, str3);
    }

    /* access modifiers changed from: package-private */
    public final String d__1_(String str, String str2, String str3) {
        return R$$r_(str, str2, d__1_.DECRYPT, str3);
    }
}
