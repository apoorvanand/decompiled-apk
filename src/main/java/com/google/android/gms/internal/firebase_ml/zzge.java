package com.google.android.gms.internal.firebase_ml;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

final class zzge extends zzfw {
    private final HttpURLConnection zzwc;

    zzge(HttpURLConnection httpURLConnection) {
        this.zzwc = httpURLConnection;
        httpURLConnection.setInstanceFollowRedirects(false);
    }

    public final void addHeader(String str, String str2) {
        this.zzwc.addRequestProperty(str, str2);
    }

    public final void zza(int i, int i2) {
        this.zzwc.setReadTimeout(i2);
        this.zzwc.setConnectTimeout(i);
    }

    public final zzfx zzey() {
        HttpURLConnection httpURLConnection = this.zzwc;
        if (zzex() != null) {
            String contentType = getContentType();
            if (contentType != null) {
                addHeader(HttpHeaders.CONTENT_TYPE, contentType);
            }
            String contentEncoding = getContentEncoding();
            if (contentEncoding != null) {
                addHeader(HttpHeaders.CONTENT_ENCODING, contentEncoding);
            }
            long contentLength = getContentLength();
            int i = (contentLength > 0 ? 1 : (contentLength == 0 ? 0 : -1));
            if (i >= 0) {
                httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Long.toString(contentLength));
            }
            String requestMethod = httpURLConnection.getRequestMethod();
            if ("POST".equals(requestMethod) || "PUT".equals(requestMethod)) {
                httpURLConnection.setDoOutput(true);
                if (i < 0 || contentLength > 2147483647L) {
                    httpURLConnection.setChunkedStreamingMode(0);
                } else {
                    httpURLConnection.setFixedLengthStreamingMode((int) contentLength);
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    zzex().writeTo(outputStream);
                    try {
                    } catch (IOException e) {
                        throw e;
                    }
                } finally {
                    try {
                        outputStream.close();
                    } catch (IOException unused) {
                    }
                }
            } else {
                Object[] objArr = {requestMethod};
                if (!(i == 0)) {
                    throw new IllegalArgumentException(zzlg.zzb("%s with non-zero content length is not supported", objArr));
                }
            }
        }
        try {
            httpURLConnection.connect();
            return new zzgf(httpURLConnection);
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
    }
}
