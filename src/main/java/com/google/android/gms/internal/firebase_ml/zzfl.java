package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzhs;
import com.google.common.net.HttpHeaders;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzfl extends zzhs {
    @zzhu("Accept")
    private List<String> accept;
    @zzhu("Accept-Encoding")
    private List<String> acceptEncoding = new ArrayList(Collections.singleton("gzip"));
    @zzhu("Age")
    private List<Long> age;
    @zzhu("WWW-Authenticate")
    private List<String> authenticate;
    @zzhu("Authorization")
    private List<String> authorization;
    @zzhu("Cache-Control")
    private List<String> cacheControl;
    @zzhu("Content-Encoding")
    private List<String> contentEncoding;
    @zzhu("Content-Length")
    private List<Long> contentLength;
    @zzhu("Content-MD5")
    private List<String> contentMD5;
    @zzhu("Content-Range")
    private List<String> contentRange;
    @zzhu("Content-Type")
    private List<String> contentType;
    @zzhu("Cookie")
    private List<String> cookie;
    @zzhu("Date")
    private List<String> date;
    @zzhu("ETag")
    private List<String> etag;
    @zzhu("Expires")
    private List<String> expires;
    @zzhu("If-Match")
    private List<String> ifMatch;
    @zzhu("If-Modified-Since")
    private List<String> ifModifiedSince;
    @zzhu("If-None-Match")
    private List<String> ifNoneMatch;
    @zzhu("If-Range")
    private List<String> ifRange;
    @zzhu("If-Unmodified-Since")
    private List<String> ifUnmodifiedSince;
    @zzhu("Last-Modified")
    private List<String> lastModified;
    @zzhu("Location")
    private List<String> location;
    @zzhu("MIME-Version")
    private List<String> mimeVersion;
    @zzhu("Range")
    private List<String> range;
    @zzhu("Retry-After")
    private List<String> retryAfter;
    @zzhu("User-Agent")
    private List<String> userAgent;

    public zzfl() {
        super(EnumSet.of(zzhs.zzc.IGNORE_CASE));
    }

    static void a(zzfl zzfl, StringBuilder sb, StringBuilder sb2, Logger logger, zzfw zzfw) {
        HashSet hashSet = new HashSet();
        for (Map.Entry next : zzfl.entrySet()) {
            String str = (String) next.getKey();
            Object[] objArr = {str};
            if (hashSet.add(str)) {
                Object value = next.getValue();
                if (value != null) {
                    zzhr zzal = zzfl.zzgs().zzal(str);
                    if (zzal != null) {
                        str = zzal.getName();
                    }
                    Class<?> cls = value.getClass();
                    if ((value instanceof Iterable) || cls.isArray()) {
                        for (Object zza : zzig.zzi(value)) {
                            zza(logger, sb, sb2, zzfw, str, zza, (Writer) null);
                        }
                    } else {
                        zza(logger, sb, sb2, zzfw, str, value, (Writer) null);
                    }
                }
            } else {
                throw new IllegalArgumentException(zzlg.zzb("multiple headers of the same name (headers are case insensitive): %s", objArr));
            }
        }
    }

    private static Object zza(Type type, List<Type> list, String str) {
        return zzhl.zza(zzhl.zza(list, type), str);
    }

    private static <T> T zza(List<T> list) {
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    private static <T> List<T> zza(T t) {
        if (t == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(t);
        return arrayList;
    }

    private static void zza(Logger logger, StringBuilder sb, StringBuilder sb2, zzfw zzfw, String str, Object obj, Writer writer) {
        if (obj != null && !zzhl.isNull(obj)) {
            String name = obj instanceof Enum ? zzhr.zza((Enum<?>) (Enum) obj).getName() : obj.toString();
            String str2 = ((HttpHeaders.AUTHORIZATION.equalsIgnoreCase(str) || HttpHeaders.COOKIE.equalsIgnoreCase(str)) && (logger == null || !logger.isLoggable(Level.ALL))) ? "<Not Logged>" : name;
            if (sb != null) {
                sb.append(str);
                sb.append(": ");
                sb.append(str2);
                sb.append(zzif.zzaai);
            }
            if (sb2 != null) {
                sb2.append(" -H '");
                sb2.append(str);
                sb2.append(": ");
                sb2.append(str2);
                sb2.append("'");
            }
            if (zzfw != null) {
                zzfw.addHeader(str, name);
            }
            if (writer != null) {
                writer.write(str);
                writer.write(": ");
                writer.write(name);
                writer.write("\r\n");
            }
        }
    }

    public final /* synthetic */ Object clone() {
        return (zzfl) super.clone();
    }

    public final String getContentType() {
        return (String) zza(this.contentType);
    }

    public final String getLocation() {
        return (String) zza(this.location);
    }

    public final void zza(zzfx zzfx, StringBuilder sb) {
        clear();
        zzfm zzfm = new zzfm(this, sb);
        int zzfa = zzfx.zzfa();
        for (int i = 0; i < zzfa; i++) {
            String zzz = zzfx.zzz(i);
            String zzaa = zzfx.zzaa(i);
            List<Type> list = zzfm.d;
            zzhj zzhj = zzfm.c;
            zzhf zzhf = zzfm.a;
            StringBuilder sb2 = zzfm.b;
            if (sb2 != null) {
                StringBuilder sb3 = new StringBuilder(String.valueOf(zzz).length() + 2 + String.valueOf(zzaa).length());
                sb3.append(zzz);
                sb3.append(": ");
                sb3.append(zzaa);
                sb2.append(sb3.toString());
                sb2.append(zzif.zzaai);
            }
            zzhr zzal = zzhj.zzal(zzz);
            if (zzal != null) {
                Type zza = zzhl.zza(list, zzal.getGenericType());
                if (zzig.zzc(zza)) {
                    Class<?> zzb = zzig.zzb(list, zzig.zzd(zza));
                    zzhf.zza(zzal.zzgp(), zzb, zza(zzb, list, zzaa));
                } else if (zzig.zza(zzig.zzb(list, zza), (Class<?>) Iterable.class)) {
                    Collection<Object> collection = (Collection) zzal.zzh(this);
                    if (collection == null) {
                        collection = zzhl.zzb(zza);
                        zzal.zzb(this, collection);
                    }
                    collection.add(zza(zza == Object.class ? null : zzig.zze(zza), list, zzaa));
                } else {
                    zzal.zzb(this, zza(zza, list, zzaa));
                }
            } else {
                ArrayList arrayList = (ArrayList) get(zzz);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    zzb(zzz, arrayList);
                }
                arrayList.add(zzaa);
            }
        }
        zzfm.a.zzgk();
    }

    public final zzfl zzaa(String str) {
        this.ifRange = zza((Object) null);
        return this;
    }

    public final zzfl zzab(String str) {
        this.userAgent = zza(str);
        return this;
    }

    public final /* synthetic */ zzhs zzb(String str, Object obj) {
        return (zzfl) super.zzb(str, obj);
    }

    public final /* synthetic */ zzhs zzdr() {
        return (zzfl) clone();
    }

    public final String zzeh() {
        return (String) zza(this.userAgent);
    }

    public final zzfl zzv(String str) {
        this.authorization = zza((Object) null);
        return this;
    }

    public final zzfl zzw(String str) {
        this.ifModifiedSince = zza((Object) null);
        return this;
    }

    public final zzfl zzx(String str) {
        this.ifMatch = zza((Object) null);
        return this;
    }

    public final zzfl zzy(String str) {
        this.ifNoneMatch = zza((Object) null);
        return this;
    }

    public final zzfl zzz(String str) {
        this.ifUnmodifiedSince = zza((Object) null);
        return this;
    }
}
