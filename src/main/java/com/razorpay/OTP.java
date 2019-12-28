package com.razorpay;

import java.util.regex.Pattern;

public class OTP {
    String R$$r_;
    private String a_$P$;
    private String d__1_;

    OTP(String str, String str2, String str3) {
        this.d__1_ = str;
        this.a_$P$ = str2;
        this.R$$r_ = str3;
        if (!Pattern.compile("^\\d").matcher(this.d__1_).find()) {
            this.d__1_ = this.d__1_.substring(1);
        }
        if (!Pattern.compile("\\d$").matcher(this.d__1_).find()) {
            String str4 = this.d__1_;
            this.d__1_ = str4.substring(0, str4.length() - 1);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Pin: ");
        sb.append(this.d__1_);
        sb.append(" bank: ");
        sb.append(this.a_$P$);
        sb.append(" sender: ");
        sb.append(this.R$$r_);
        return sb.toString();
    }
}
