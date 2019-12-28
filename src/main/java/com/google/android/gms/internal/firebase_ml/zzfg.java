package com.google.android.gms.internal.firebase_ml;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.text.Typography;

public final class zzfg extends zzhs {
    private static final zzil zzts = new zzim("=&-_.!~*'()@:$,;/?:", false);
    private String fragment;
    private int port;
    private String zztt;
    private String zztu;
    private String zztv;
    private List<String> zztw;

    public zzfg() {
        this.port = -1;
    }

    public zzfg(String str) {
        this(zzu(str));
    }

    private zzfg(String str, String str2, int i, String str3, String str4, String str5, String str6) {
        this.port = -1;
        this.zztt = str.toLowerCase(Locale.US);
        this.zztu = str2;
        this.port = i;
        this.zztw = zzt(str3);
        String str7 = null;
        this.fragment = str4 != null ? zzik.zzao(str4) : null;
        if (str5 != null) {
            zzgb.zze(str5, this);
        }
        this.zztv = str6 != null ? zzik.zzao(str6) : str7;
    }

    public zzfg(URL url) {
        this(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getRef(), url.getQuery(), url.getUserInfo());
    }

    static void a(Set<Map.Entry<String, Object>> set, StringBuilder sb) {
        boolean z = true;
        for (Map.Entry next : set) {
            Object value = next.getValue();
            if (value != null) {
                String zzas = zzik.zzas((String) next.getKey());
                if (value instanceof Collection) {
                    for (Object zza : (Collection) value) {
                        z = zza(z, sb, zzas, zza);
                    }
                } else {
                    z = zza(z, sb, zzas, value);
                }
            }
        }
    }

    private static boolean zza(boolean z, StringBuilder sb, String str, Object obj) {
        char c;
        if (z) {
            z = false;
            c = '?';
        } else {
            c = Typography.amp;
        }
        sb.append(c);
        sb.append(str);
        String zzas = zzik.zzas(obj.toString());
        if (zzas.length() != 0) {
            sb.append('=');
            sb.append(zzas);
        }
        return z;
    }

    private static List<String> zzt(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        int i = 0;
        while (z) {
            int indexOf = str.indexOf(47, i);
            boolean z2 = indexOf != -1;
            arrayList.add(zzik.zzao(z2 ? str.substring(i, indexOf) : str.substring(i)));
            i = indexOf + 1;
            z = z2;
        }
        return arrayList;
    }

    private static URL zzu(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final /* synthetic */ Object clone() {
        zzfg zzfg = (zzfg) super.clone();
        List<String> list = this.zztw;
        if (list != null) {
            zzfg.zztw = new ArrayList(list);
        }
        return zzfg;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || !(obj instanceof zzfg)) {
            return false;
        }
        return zzeg().equals(((zzfg) obj).zzeg());
    }

    public final int hashCode() {
        return zzeg().hashCode();
    }

    public final String toString() {
        return zzeg();
    }

    public final /* synthetic */ zzhs zzb(String str, Object obj) {
        return (zzfg) super.zzb(str, obj);
    }

    public final /* synthetic */ zzhs zzdr() {
        return (zzfg) clone();
    }

    public final String zzeg() {
        StringBuilder sb = new StringBuilder();
        sb.append((String) zzky.checkNotNull(this.zztt));
        sb.append("://");
        String str = this.zztv;
        if (str != null) {
            sb.append(zzik.zzar(str));
            sb.append('@');
        }
        sb.append((String) zzky.checkNotNull(this.zztu));
        int i = this.port;
        if (i != -1) {
            sb.append(':');
            sb.append(i);
        }
        String valueOf = String.valueOf(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        List<String> list = this.zztw;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                String str2 = this.zztw.get(i2);
                if (i2 != 0) {
                    sb2.append('/');
                }
                if (str2.length() != 0) {
                    sb2.append(zzik.zzap(str2));
                }
            }
        }
        a(entrySet(), sb2);
        String str3 = this.fragment;
        if (str3 != null) {
            sb2.append('#');
            sb2.append(zzts.zzat(str3));
        }
        String valueOf2 = String.valueOf(sb2.toString());
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final URL zzr(String str) {
        try {
            return new URL(zzu(zzeg()), str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final void zzs(String str) {
        this.zztw = zzt((String) null);
    }
}
