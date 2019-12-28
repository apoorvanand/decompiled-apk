package com.google.android.gms.internal.firebase_ml;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class zzgf extends zzfx {
    private final int responseCode;
    private final String responseMessage;
    private final HttpURLConnection zzwc;
    private final ArrayList<String> zzwd = new ArrayList<>();
    private final ArrayList<String> zzwe = new ArrayList<>();

    zzgf(HttpURLConnection httpURLConnection) {
        this.zzwc = httpURLConnection;
        int responseCode2 = httpURLConnection.getResponseCode();
        this.responseCode = responseCode2 == -1 ? 0 : responseCode2;
        this.responseMessage = httpURLConnection.getResponseMessage();
        ArrayList<String> arrayList = this.zzwd;
        ArrayList<String> arrayList2 = this.zzwe;
        for (Map.Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
            String str = (String) entry.getKey();
            if (str != null) {
                for (String str2 : (List) entry.getValue()) {
                    if (str2 != null) {
                        arrayList.add(str);
                        arrayList2.add(str2);
                    }
                }
            }
        }
    }

    public final void disconnect() {
        this.zzwc.disconnect();
    }

    public final InputStream getContent() {
        InputStream inputStream;
        try {
            inputStream = this.zzwc.getInputStream();
        } catch (IOException unused) {
            inputStream = this.zzwc.getErrorStream();
        }
        if (inputStream == null) {
            return null;
        }
        return new zzgg(this, inputStream);
    }

    public final String getContentEncoding() {
        return this.zzwc.getContentEncoding();
    }

    public final long getContentLength() {
        String headerField = this.zzwc.getHeaderField(HttpHeaders.CONTENT_LENGTH);
        if (headerField == null) {
            return -1;
        }
        return Long.parseLong(headerField);
    }

    public final String getContentType() {
        return this.zzwc.getHeaderField(HttpHeaders.CONTENT_TYPE);
    }

    public final String getReasonPhrase() {
        return this.responseMessage;
    }

    public final int getStatusCode() {
        return this.responseCode;
    }

    public final String zzaa(int i) {
        return this.zzwe.get(i);
    }

    public final String zzez() {
        String headerField = this.zzwc.getHeaderField(0);
        if (headerField == null || !headerField.startsWith("HTTP/1.")) {
            return null;
        }
        return headerField;
    }

    public final int zzfa() {
        return this.zzwd.size();
    }

    public final String zzz(int i) {
        return this.zzwd.get(i);
    }
}
