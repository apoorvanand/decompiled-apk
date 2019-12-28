package com.google.android.gms.internal.firebase_ml;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class zzgh extends zzfv {
    private static final String[] zzvj;
    private final HostnameVerifier hostnameVerifier;
    private final zzgc zzwh;
    private final SSLSocketFactory zzwi;

    static {
        String[] strArr = {"DELETE", "GET", "HEAD", "OPTIONS", "POST", "PUT", "TRACE"};
        zzvj = strArr;
        Arrays.sort(strArr);
    }

    public zzgh() {
        this((zzgc) null, (SSLSocketFactory) null, (HostnameVerifier) null);
    }

    private zzgh(zzgc zzgc, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier2) {
        this.zzwh = System.getProperty("com.google.api.client.should_use_proxy") != null ? new zzgd(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("https.proxyHost"), Integer.parseInt(System.getProperty("https.proxyPort"))))) : new zzgd();
        this.zzwi = null;
        this.hostnameVerifier = null;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzfw a(String str, String str2) {
        Object[] objArr = {str};
        if (zzag(str)) {
            HttpURLConnection zza = this.zzwh.zza(new URL(str2));
            zza.setRequestMethod(str);
            return new zzge(zza);
        }
        throw new IllegalArgumentException(zzlg.zzb("HTTP method %s not supported", objArr));
    }

    public final boolean zzag(String str) {
        return Arrays.binarySearch(zzvj, str) >= 0;
    }
}
