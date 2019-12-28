package com.razorpay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import kotlin.text.Typography;
import org.json.JSONObject;

public final class Q__v$ {
    private static int[] G__G_ = {1075164247, -2107273297, 845002851, -1122794942, -833358106, 102548806, 478321485, 109567584, 382327500, 2029621024, -1336901147, 1216053613, 1717904990, -539593898, -1673444228, -791319119, 1111325825, -60204490};
    private static int Q_$2$ = 0;
    private static SharedPreferences R$$r_ = null;
    private static int a_$P$ = 1;
    private static SharedPreferences.Editor d__1_;

    Q__v$() {
    }

    static SharedPreferences E$_j$(Context context) {
        int i = a_$P$ + 37;
        Q_$2$ = i % 128;
        int i2 = i % 2;
        SharedPreferences sharedPreferences = context.getSharedPreferences("rzp_preferences_storage_bridge", 0);
        int i3 = Q_$2$ + 119;
        a_$P$ = i3 % 128;
        if ((i3 % 2 == 0 ? Typography.quote : ')') != ')') {
            int i4 = 9 / 0;
        }
        return sharedPreferences;
    }

    static SharedPreferences G__G_(Context context) {
        int i = Q_$2$ + 19;
        a_$P$ = i % 128;
        int i2 = i % 2;
        if (!(R$$r_ != null)) {
            int i3 = Q_$2$ + 113;
            a_$P$ = i3 % 128;
            R$$r_ = (i3 % 2 == 0 ? '!' : 3) != 3 ? context.getSharedPreferences("rzp_preference_private", 1) : context.getSharedPreferences("rzp_preference_private", 0);
        }
        return R$$r_;
    }

    private static String G__G_(int[] iArr, int i) {
        int i2 = Q_$2$ + 115;
        a_$P$ = i2 % 128;
        int i3 = i2 % 2;
        char[] cArr = new char[4];
        char[] cArr2 = new char[(iArr.length << 1)];
        int[] iArr2 = (int[]) G__G_.clone();
        int i4 = 0;
        while (true) {
            if (!(i4 < iArr.length)) {
                return new String(cArr2, 0, i);
            }
            cArr[0] = (char) (iArr[i4] >> 16);
            cArr[1] = (char) iArr[i4];
            int i5 = i4 + 1;
            cArr[2] = (char) (iArr[i5] >> 16);
            cArr[3] = (char) iArr[i5];
            E$_j$.Q_$2$(cArr, iArr2, false);
            int i6 = i4 << 1;
            cArr2[i6] = cArr[0];
            cArr2[i6 + 1] = cArr[1];
            cArr2[i6 + 2] = cArr[2];
            cArr2[i6 + 3] = cArr[3];
            i4 += 2;
            int i7 = Q_$2$ + 21;
            a_$P$ = i7 % 128;
            if (i7 % 2 == 0) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        if ((d__1_ != null) != true) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        if (r2 != false) goto L_0x0047;
     */
    @android.annotation.SuppressLint({"CommitPrefEdits"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.content.SharedPreferences.Editor Q_$2$(android.content.Context r4) {
        /*
            int r0 = Q_$2$
            int r0 = r0 + 51
            int r1 = r0 % 128
            a_$P$ = r1
            int r0 = r0 % 2
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            r3 = 0
            if (r0 == r2) goto L_0x001e
            android.content.SharedPreferences$Editor r0 = d__1_
            if (r0 != 0) goto L_0x001a
            r0 = 0
            goto L_0x001b
        L_0x001a:
            r0 = 1
        L_0x001b:
            if (r0 == r2) goto L_0x0047
            goto L_0x0029
        L_0x001e:
            android.content.SharedPreferences$Editor r0 = d__1_
            super.hashCode()
            if (r0 != 0) goto L_0x0026
            r2 = 0
        L_0x0026:
            if (r2 == 0) goto L_0x0029
            goto L_0x0047
        L_0x0029:
            android.content.SharedPreferences r0 = R$$r_
            if (r0 != 0) goto L_0x0035
            java.lang.String r0 = "rzp_preference_private"
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r0, r1)
            R$$r_ = r4
        L_0x0035:
            android.content.SharedPreferences r4 = R$$r_
            android.content.SharedPreferences$Editor r4 = r4.edit()
            d__1_ = r4
            int r4 = Q_$2$
            int r4 = r4 + 75
            int r0 = r4 % 128
            a_$P$ = r0
            int r4 = r4 % 2
        L_0x0047:
            android.content.SharedPreferences$Editor r4 = d__1_
            int r0 = Q_$2$
            int r0 = r0 + 71
            int r1 = r0 % 128
            a_$P$ = r1
            int r0 = r0 % 2
            if (r0 != 0) goto L_0x0058
            super.hashCode()
        L_0x0058:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.Q__v$.Q_$2$(android.content.Context):android.content.SharedPreferences$Editor");
    }

    static void Q_$2$(Context context, String str) {
        char c = 0;
        if (R$$r_ == null) {
            int i = Q_$2$ + 65;
            a_$P$ = i % 128;
            if (i % 2 == 0) {
            }
            R$$r_ = context.getSharedPreferences("rzp_preference_private", 0);
        }
        if (!str.equalsIgnoreCase(R$$r_.getString("sdk_version", (String) null))) {
            c = 'a';
        }
        if (c == 'a') {
            int i2 = Q_$2$ + 29;
            a_$P$ = i2 % 128;
            int i3 = i2 % 2;
            SharedPreferences.Editor Q_$2$2 = Q_$2$(context);
            Q_$2$2.putString("rzp_config_json", (String) null);
            Q_$2$2.commit();
            SharedPreferences.Editor Q_$2$3 = Q_$2$(context);
            Q_$2$3.putString("rzp_config_version", (String) null);
            Q_$2$3.commit();
            SharedPreferences.Editor Q_$2$4 = Q_$2$(context);
            Q_$2$4.putString("sdk_version", str);
            Q_$2$4.commit();
        }
    }

    static SharedPreferences.Editor R$$r_(Context context) {
        int i = a_$P$ + 53;
        Q_$2$ = i % 128;
        if (i % 2 != 0) {
        }
        return context.getSharedPreferences("rzp_preferences_storage_bridge", 0).edit();
    }

    static String R$$r_(Context context, String str) {
        boolean z = true;
        if ((R$$r_ == null ? 'U' : 'J') == 'U') {
            R$$r_ = context.getSharedPreferences("rzp_preference_private", 0);
            int i = a_$P$ + 1;
            Q_$2$ = i % 128;
            int i2 = i % 2;
        }
        String string = R$$r_.getString(str, (String) null);
        int i3 = Q_$2$ + 93;
        a_$P$ = i3 % 128;
        if (i3 % 2 == 0) {
            z = false;
        }
        if (z) {
            return string;
        }
        int i4 = 99 / 0;
        return string;
    }

    public static void R$$r_(Context context, String str, String str2) {
        int i = a_$P$ + 23;
        Q_$2$ = i % 128;
        boolean z = false;
        Object[] objArr = null;
        if (!(i % 2 != 0)) {
            SharedPreferences.Editor Q_$2$2 = Q_$2$(context);
            Q_$2$2.putString(str, str2);
            Q_$2$2.commit();
        } else {
            SharedPreferences.Editor Q_$2$3 = Q_$2$(context);
            Q_$2$3.putString(str, str2);
            Q_$2$3.commit();
            super.hashCode();
        }
        int i2 = a_$P$ + 1;
        Q_$2$ = i2 % 128;
        if (i2 % 2 != 0) {
            z = true;
        }
        if (z) {
            int length = objArr.length;
        }
    }

    static SharedPreferences a_$P$(Context context) {
        SharedPreferences sharedPreferences;
        int i = Q_$2$ + 111;
        a_$P$ = i % 128;
        if (i % 2 == 0) {
            try {
                sharedPreferences = context.getSharedPreferences("rzp_preference_public", 0);
            } catch (Exception e) {
                AnalyticsUtil.reportError(e, "critical", e.getMessage());
                return context.getSharedPreferences("rzp_preference_public", 0);
            }
        } else {
            sharedPreferences = context.getSharedPreferences("rzp_preference_public", 1);
        }
        int i2 = Q_$2$ + 95;
        a_$P$ = i2 % 128;
        if ((i2 % 2 == 0 ? '2' : 22) != '2') {
            return sharedPreferences;
        }
        Object[] objArr = null;
        int length = objArr.length;
        return sharedPreferences;
    }

    public static String a_$P$(Context context, String str, String str2) {
        String str3 = null;
        try {
            boolean z = false;
            if (R$$r_ == null) {
                R$$r_ = context.getSharedPreferences("rzp_preference_private", 0);
            }
            String string = R$$r_.getString(str, str3);
            if (string == null) {
                int i = Q_$2$ + 31;
                a_$P$ = i % 128;
                int i2 = i % 2;
                int i3 = Q_$2$ + 13;
                a_$P$ = i3 % 128;
                int i4 = i3 % 2;
                return str3;
            }
            JSONObject jSONObject = new JSONObject(string);
            O__Y_ o__y_ = new O__Y_();
            if (!(!str2.equals(jSONObject.getString("sdk_version")))) {
                return o__y_.d__1_(jSONObject.getString("data"), G__G_(new int[]{694308465, 2143344195, 2000759566, -329650410, -1144654678, -103553561, 279503864, -1121304650, -45742990, 240103162, 434781664, 1048700795, 1429070561, 98796846, 863302109, -489255541}, 32).intern(), jSONObject.getString("iv"));
            }
            int i5 = a_$P$ + 111;
            Q_$2$ = i5 % 128;
            if (i5 % 2 == 0) {
                z = true;
            }
            if (!z) {
                super.hashCode();
            }
            return str3;
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "error", "Unable to decrypt value");
            return str3;
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    static SharedPreferences.Editor d__1_(Context context) {
        SharedPreferences.Editor editor;
        int i = a_$P$ + 79;
        Q_$2$ = i % 128;
        Object[] objArr = null;
        if ((i % 2 != 0 ? Typography.greater : 30) != '>') {
            editor = a_$P$(context).edit();
        } else {
            editor = a_$P$(context).edit();
            super.hashCode();
        }
        int i2 = Q_$2$ + 19;
        a_$P$ = i2 % 128;
        if (!(i2 % 2 == 0)) {
            return editor;
        }
        int length = objArr.length;
        return editor;
    }

    public static void d__1_(Context context, String str, String str2, String str3) {
        try {
            String randomString = BaseUtils.getRandomString();
            boolean z = false;
            String G__G_2 = new O__Y_().G__G_(str2, G__G_(new int[]{694308465, 2143344195, 2000759566, -329650410, -1144654678, -103553561, 279503864, -1121304650, -45742990, 240103162, 434781664, 1048700795, 1429070561, 98796846, 863302109, -489255541}, 32).intern(), randomString);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("data", G__G_2);
            jSONObject.put("iv", randomString);
            jSONObject.put("sdk_version", str3);
            String jSONObject2 = jSONObject.toString();
            SharedPreferences.Editor Q_$2$2 = Q_$2$(context);
            Q_$2$2.putString(str, jSONObject2);
            Q_$2$2.commit();
            int i = Q_$2$ + 71;
            a_$P$ = i % 128;
            if (i % 2 == 0) {
                z = true;
            }
            if (z) {
                Object[] objArr = null;
                int length = objArr.length;
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "error", "Unable to encrypt value");
        }
    }
}
