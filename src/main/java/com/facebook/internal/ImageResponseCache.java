package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import com.facebook.LoggingBehavior;
import com.facebook.internal.FileLruCache;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

class ImageResponseCache {
    static final String a = "ImageResponseCache";
    private static FileLruCache imageCache;

    private static class BufferedHttpInputStream extends BufferedInputStream {
        HttpURLConnection a;

        BufferedHttpInputStream(InputStream inputStream, HttpURLConnection httpURLConnection) {
            super(inputStream, 8192);
            this.a = httpURLConnection;
        }

        public void close() {
            super.close();
            Utility.disconnectQuietly(this.a);
        }
    }

    ImageResponseCache() {
    }

    static synchronized FileLruCache a(Context context) {
        FileLruCache fileLruCache;
        synchronized (ImageResponseCache.class) {
            if (imageCache == null) {
                imageCache = new FileLruCache(a, new FileLruCache.Limits());
            }
            fileLruCache = imageCache;
        }
        return fileLruCache;
    }

    static InputStream a(Context context, HttpURLConnection httpURLConnection) {
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        Uri parse = Uri.parse(httpURLConnection.getURL().toString());
        InputStream inputStream = httpURLConnection.getInputStream();
        try {
            return isCDNURL(parse) ? a(context).interceptAndPut(parse.toString(), new BufferedHttpInputStream(inputStream, httpURLConnection)) : inputStream;
        } catch (IOException unused) {
            return inputStream;
        }
    }

    static InputStream a(Uri uri, Context context) {
        if (uri != null && isCDNURL(uri)) {
            try {
                return a(context).get(uri.toString());
            } catch (IOException e) {
                Logger.log(LoggingBehavior.CACHE, 5, a, e.toString());
            }
        }
        return null;
    }

    static void b(Context context) {
        try {
            a(context).clearCache();
        } catch (IOException e) {
            LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
            String str = a;
            Logger.log(loggingBehavior, 5, str, "clearCache failed " + e.getMessage());
        }
    }

    private static boolean isCDNURL(Uri uri) {
        if (uri == null) {
            return false;
        }
        String host = uri.getHost();
        if (host.endsWith("fbcdn.net")) {
            return true;
        }
        return host.startsWith("fbcdn") && host.endsWith("akamaihd.net");
    }
}
