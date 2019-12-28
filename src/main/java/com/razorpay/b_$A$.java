package com.razorpay;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class b_$A$ extends AsyncTask<String, Void, t_$J_> {
    private Callback G__G_;
    private Map<String, String> R$$r_ = new HashMap();
    private String a_$P$ = null;
    private String d__1_ = null;

    private b_$A$(Callback callback) {
        this.G__G_ = callback;
    }

    public static AsyncTask G__G_(String str, Callback callback) {
        b_$A$ b__a_ = new b_$A$(callback);
        b__a_.d__1_ = "GET";
        return b__a_.execute(new String[]{str});
    }

    private static String G__G_(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }

    public static AsyncTask R$$r_(String str, String str2, Map<String, String> map, Callback callback) {
        b_$A$ b__a_ = new b_$A$(callback);
        b__a_.d__1_ = "POST";
        b__a_.a_$P$ = str2;
        b__a_.R$$r_ = map;
        return b__a_.execute(new String[]{str});
    }

    static AsyncTask a_$P$(String str, Map<String, String> map, Callback callback) {
        b_$A$ b__a_ = new b_$A$(callback);
        b__a_.d__1_ = "GET";
        b__a_.R$$r_ = map;
        return b__a_.execute(new String[]{str});
    }

    /* access modifiers changed from: private */
    /* renamed from: a_$P$ */
    public t_$J_ doInBackground(String... strArr) {
        t_$J_ t__j_ = new t_$J_();
        InputStream inputStream = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
            for (Map.Entry next : this.R$$r_.entrySet()) {
                httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
            }
            httpURLConnection.setRequestMethod(this.d__1_);
            if (this.a_$P$ != null) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.getOutputStream().write(this.a_$P$.getBytes("UTF-8"));
            }
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            t__j_.Q_$2$(responseCode);
            InputStream errorStream = responseCode >= 400 ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream();
            t__j_.Q_$2$(httpURLConnection.getHeaderFields());
            t__j_.d__1_(G__G_(errorStream));
            if (errorStream != null) {
                try {
                    errorStream.close();
                } catch (Exception e) {
                    AnalyticsUtil.reportError(e, "error", e.getMessage());
                }
            }
        } catch (Exception e2) {
            e2.getLocalizedMessage();
            AnalyticsUtil.reportError(e2, "error", e2.getMessage());
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e3) {
                    AnalyticsUtil.reportError(e3, "error", e3.getMessage());
                }
            }
            throw th;
        }
        return t__j_;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        t_$J_ t__j_ = (t_$J_) obj;
        Callback callback = this.G__G_;
        if (callback != null) {
            callback.run(t__j_);
        }
    }
}
