package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

public final class zzjs extends zzge {
    private static final String[] zztw = {"firebase_", "google_", "ga_"};
    private static final List<String> zzua = Collections.unmodifiableList(Arrays.asList(new String[]{"source", FirebaseAnalytics.Param.MEDIUM, FirebaseAnalytics.Param.CAMPAIGN, FirebaseAnalytics.Param.TERM, FirebaseAnalytics.Param.CONTENT}));
    private int zzag;
    private SecureRandom zztx;
    private final AtomicLong zzty = new AtomicLong(0);
    private Integer zztz = null;

    zzjs(zzfj zzfj) {
        super(zzfj);
    }

    @VisibleForTesting
    static long a(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int i = 0;
        Preconditions.checkState(bArr.length > 0);
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j += (((long) bArr[length]) & 255) << i;
            i += 8;
            length--;
        }
        return j;
    }

    static boolean a(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        return zzb(context, Build.VERSION.SDK_INT >= 24 ? "com.google.android.gms.measurement.AppMeasurementJobService" : "com.google.android.gms.measurement.AppMeasurementService");
    }

    static boolean a(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    static boolean a(Boolean bool, Boolean bool2) {
        if (bool == null && bool2 == null) {
            return true;
        }
        if (bool == null) {
            return false;
        }
        return bool.equals(bool2);
    }

    static boolean a(String str) {
        Preconditions.checkNotEmpty(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    static boolean a(String str, String str2, String str3, String str4) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (!isEmpty && !isEmpty2) {
            return !str.equals(str2);
        }
        if (isEmpty && isEmpty2) {
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) ? !TextUtils.isEmpty(str4) : !str3.equals(str4);
        }
        if (isEmpty || !isEmpty2) {
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
        if (TextUtils.isEmpty(str4)) {
            return false;
        }
        return TextUtils.isEmpty(str3) || !str3.equals(str4);
    }

    static boolean a(@Nullable List<String> list, @Nullable List<String> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        return list.equals(list2);
    }

    static byte[] a(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    static Bundle[] a(Object obj) {
        Object[] array;
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        }
        if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            array = Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        } else if (!(obj instanceof ArrayList)) {
            return null;
        } else {
            ArrayList arrayList = (ArrayList) obj;
            array = arrayList.toArray(new Bundle[arrayList.size()]);
        }
        return (Bundle[]) array;
    }

    static MessageDigest d() {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    static boolean d(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    static boolean e(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    private static Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zza(String.valueOf(obj), i, z);
            }
            return null;
        }
    }

    public static String zza(String str, int i, boolean z) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
        }
        return null;
    }

    private static boolean zza(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    private final boolean zza(String str, String str2, int i, Object obj, boolean z) {
        if (obj != null && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Boolean) && !(obj instanceof Double)) {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                String valueOf = String.valueOf(obj);
                if (valueOf.codePointCount(0, valueOf.length()) > i) {
                    zzab().zzgp().zza("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
                    return false;
                }
            } else if ((obj instanceof Bundle) && z) {
                return true;
            } else {
                if ((obj instanceof Parcelable[]) && z) {
                    for (Parcelable parcelable : (Parcelable[]) obj) {
                        if (!(parcelable instanceof Bundle)) {
                            zzab().zzgp().zza("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable.getClass(), str2);
                            return false;
                        }
                    }
                    return true;
                } else if (!(obj instanceof ArrayList) || !z) {
                    return false;
                } else {
                    ArrayList arrayList = (ArrayList) obj;
                    int size = arrayList.size();
                    int i2 = 0;
                    while (i2 < size) {
                        Object obj2 = arrayList.get(i2);
                        i2++;
                        if (!(obj2 instanceof Bundle)) {
                            zzab().zzgp().zza("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return true;
    }

    private static void zzb(Bundle bundle, Object obj) {
        Preconditions.checkNotNull(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0008, code lost:
        r3 = r1.getServiceInfo(new android.content.ComponentName(r3, r4), 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zzb(android.content.Context r3, java.lang.String r4) {
        /*
            r0 = 0
            android.content.pm.PackageManager r1 = r3.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0019 }
            if (r1 != 0) goto L_0x0008
            return r0
        L_0x0008:
            android.content.ComponentName r2 = new android.content.ComponentName     // Catch:{ NameNotFoundException -> 0x0019 }
            r2.<init>(r3, r4)     // Catch:{ NameNotFoundException -> 0x0019 }
            android.content.pm.ServiceInfo r3 = r1.getServiceInfo(r2, r0)     // Catch:{ NameNotFoundException -> 0x0019 }
            if (r3 == 0) goto L_0x0019
            boolean r3 = r3.enabled     // Catch:{ NameNotFoundException -> 0x0019 }
            if (r3 == 0) goto L_0x0019
            r3 = 1
            return r3
        L_0x0019:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjs.zzb(android.content.Context, java.lang.String):boolean");
    }

    @VisibleForTesting
    private static boolean zzbn(String str) {
        Preconditions.checkNotNull(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    private static int zzbo(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        return "_id".equals(str) ? 256 : 36;
    }

    public static long zzc(long j, long j2) {
        return (j + (j2 * 60000)) / 86400000;
    }

    public static Bundle zzc(List<zzjn> list) {
        Bundle bundle = new Bundle();
        if (list == null) {
            return bundle;
        }
        for (zzjn next : list) {
            if (next.zzkr != null) {
                bundle.putString(next.name, next.zzkr);
            } else if (next.zzts != null) {
                bundle.putLong(next.name, next.zzts.longValue());
            } else if (next.zztu != null) {
                bundle.putDouble(next.name, next.zztu.doubleValue());
            }
        }
        return bundle;
    }

    public static ArrayList<Bundle> zzd(List<zzq> list) {
        if (list == null) {
            return new ArrayList<>(0);
        }
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        for (zzq next : list) {
            Bundle bundle = new Bundle();
            bundle.putString("app_id", next.packageName);
            bundle.putString("origin", next.origin);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, next.creationTimestamp);
            bundle.putString("name", next.zzdw.name);
            zzgg.zza(bundle, next.zzdw.getValue());
            bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, next.active);
            if (next.triggerEventName != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, next.triggerEventName);
            }
            if (next.zzdx != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, next.zzdx.name);
                if (next.zzdx.zzfq != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, next.zzdx.zzfq.zzcv());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, next.triggerTimeout);
            if (next.zzdy != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, next.zzdy.name);
                if (next.zzdy.zzfq != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, next.zzdy.zzfq.zzcv());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, next.zzdw.zztr);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, next.timeToLive);
            if (next.zzdz != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, next.zzdz.name);
                if (next.zzdz.zzfq != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, next.zzdz.zzfq.zzcv());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    @VisibleForTesting
    private final boolean zzd(Context context, String str) {
        zzeh zzeh;
        String str2;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (CertificateException e) {
            e = e;
            zzeh = zzab().zzgk();
            str2 = "Error obtaining certificate";
            zzeh.zza(str2, e);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
            zzeh = zzab().zzgk();
            str2 = "Package name not found";
            zzeh.zza(str2, e);
            return true;
        }
    }

    public static Bundle zzh(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str, new Bundle((Bundle) obj));
            } else {
                int i = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i < parcelableArr.length) {
                        if (parcelableArr[i] instanceof Bundle) {
                            parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                        }
                        i++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i < list.size()) {
                        Object obj2 = list.get(i);
                        if (obj2 instanceof Bundle) {
                            list.set(i, new Bundle((Bundle) obj2));
                        }
                        i++;
                    }
                }
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final long a(Context context, String str) {
        zzo();
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest d = d();
        if (d == null) {
            zzab().zzgk().zzao("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zzd(context, str)) {
                    PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return a(d.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    zzab().zzgn().zzao("Could not get signatures");
                    return -1;
                }
            } catch (PackageManager.NameNotFoundException e) {
                zzab().zzgk().zza("Package name not found", e);
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final Bundle a(@NonNull Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        if (uri == null) {
            return null;
        }
        try {
            if (uri.isHierarchical()) {
                str4 = uri.getQueryParameter("utm_campaign");
                str3 = uri.getQueryParameter("utm_source");
                str2 = uri.getQueryParameter("utm_medium");
                str = uri.getQueryParameter("gclid");
            } else {
                str4 = null;
                str3 = null;
                str2 = null;
                str = null;
            }
            if (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str)) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str4)) {
                bundle.putString(FirebaseAnalytics.Param.CAMPAIGN, str4);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString("source", str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString(FirebaseAnalytics.Param.MEDIUM, str2);
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("gclid", str);
            }
            String queryParameter = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter);
            }
            String queryParameter2 = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString(FirebaseAnalytics.Param.CONTENT, queryParameter2);
            }
            String queryParameter3 = uri.getQueryParameter(FirebaseAnalytics.Param.ACLID);
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter3);
            }
            String queryParameter4 = uri.getQueryParameter(FirebaseAnalytics.Param.CP1);
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter4);
            }
            String queryParameter5 = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString("anid", queryParameter5);
            }
            return bundle;
        } catch (UnsupportedOperationException e) {
            zzab().zzgn().zza("Install referrer url isn't a hierarchical URI", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final Bundle a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object a = a(str, bundle.get(str));
                if (a == null) {
                    zzab().zzgp().zza("Param value can't be null", zzy().b(str));
                } else {
                    a(bundle2, str, a);
                }
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0069, code lost:
        if (a("event param", 40, r15) == false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0088, code lost:
        if (a("event param", 40, r15) == false) goto L_0x0077;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0148  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle a(java.lang.String r18, java.lang.String r19, android.os.Bundle r20, @androidx.annotation.Nullable java.util.List<java.lang.String> r21, boolean r22, boolean r23) {
        /*
            r17 = this;
            r6 = r17
            r7 = r18
            r8 = r20
            r9 = r21
            r10 = 0
            if (r8 == 0) goto L_0x018c
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>(r8)
            com.google.android.gms.measurement.internal.zzs r0 = r17.zzad()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzak.zziw
            boolean r0 = r0.zze(r7, r1)
            if (r0 == 0) goto L_0x0026
            java.util.TreeSet r0 = new java.util.TreeSet
            java.util.Set r1 = r20.keySet()
            r0.<init>(r1)
            goto L_0x002a
        L_0x0026:
            java.util.Set r0 = r20.keySet()
        L_0x002a:
            java.util.Iterator r12 = r0.iterator()
            r14 = 0
        L_0x002f:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x018d
            java.lang.Object r0 = r12.next()
            r15 = r0
            java.lang.String r15 = (java.lang.String) r15
            r5 = 40
            r0 = 3
            if (r9 == 0) goto L_0x004a
            boolean r1 = r9.contains(r15)
            if (r1 != 0) goto L_0x0048
            goto L_0x004a
        L_0x0048:
            r1 = 0
            goto L_0x008c
        L_0x004a:
            r1 = 14
            if (r22 == 0) goto L_0x006c
            java.lang.String r2 = "event param"
            boolean r2 = r6.a((java.lang.String) r2, (java.lang.String) r15)
            if (r2 != 0) goto L_0x0058
        L_0x0056:
            r2 = 3
            goto L_0x006d
        L_0x0058:
            java.lang.String r2 = "event param"
            boolean r2 = r6.a((java.lang.String) r2, (java.lang.String[]) r10, (java.lang.String) r15)
            if (r2 != 0) goto L_0x0063
            r2 = 14
            goto L_0x006d
        L_0x0063:
            java.lang.String r2 = "event param"
            boolean r2 = r6.a((java.lang.String) r2, (int) r5, (java.lang.String) r15)
            if (r2 != 0) goto L_0x006c
            goto L_0x0056
        L_0x006c:
            r2 = 0
        L_0x006d:
            if (r2 != 0) goto L_0x008b
            java.lang.String r2 = "event param"
            boolean r2 = r6.b((java.lang.String) r2, (java.lang.String) r15)
            if (r2 != 0) goto L_0x0079
        L_0x0077:
            r1 = 3
            goto L_0x008c
        L_0x0079:
            java.lang.String r2 = "event param"
            boolean r2 = r6.a((java.lang.String) r2, (java.lang.String[]) r10, (java.lang.String) r15)
            if (r2 != 0) goto L_0x0082
            goto L_0x008c
        L_0x0082:
            java.lang.String r1 = "event param"
            boolean r1 = r6.a((java.lang.String) r1, (int) r5, (java.lang.String) r15)
            if (r1 != 0) goto L_0x0048
            goto L_0x0077
        L_0x008b:
            r1 = r2
        L_0x008c:
            r4 = 1
            if (r1 == 0) goto L_0x00a5
            boolean r2 = zza((android.os.Bundle) r11, (int) r1)
            if (r2 == 0) goto L_0x0185
            java.lang.String r2 = zza((java.lang.String) r15, (int) r5, (boolean) r4)
            java.lang.String r3 = "_ev"
            r11.putString(r3, r2)
            if (r1 != r0) goto L_0x0185
            zzb((android.os.Bundle) r11, (java.lang.Object) r15)
            goto L_0x0185
        L_0x00a5:
            java.lang.Object r3 = r8.get(r15)
            r17.zzo()
            if (r23 == 0) goto L_0x00e4
            java.lang.String r0 = "param"
            boolean r1 = r3 instanceof android.os.Parcelable[]
            if (r1 == 0) goto L_0x00b9
            r1 = r3
            android.os.Parcelable[] r1 = (android.os.Parcelable[]) r1
            int r1 = r1.length
            goto L_0x00c4
        L_0x00b9:
            boolean r1 = r3 instanceof java.util.ArrayList
            if (r1 == 0) goto L_0x00db
            r1 = r3
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r1 = r1.size()
        L_0x00c4:
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r1 <= r2) goto L_0x00db
            com.google.android.gms.measurement.internal.zzef r2 = r17.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgp()
            java.lang.String r4 = "Parameter array is too long; discarded. Value kind, name, array length"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.zza(r4, r0, r15, r1)
            r0 = 0
            goto L_0x00dc
        L_0x00db:
            r0 = 1
        L_0x00dc:
            if (r0 != 0) goto L_0x00e4
            r0 = 17
            r10 = 1
            r13 = 40
            goto L_0x0124
        L_0x00e4:
            com.google.android.gms.measurement.internal.zzs r0 = r17.zzad()
            boolean r0 = r0.c(r7)
            if (r0 == 0) goto L_0x00f4
            boolean r0 = e(r19)
            if (r0 != 0) goto L_0x00fa
        L_0x00f4:
            boolean r0 = e(r15)
            if (r0 == 0) goto L_0x0110
        L_0x00fa:
            java.lang.String r1 = "param"
            r4 = 256(0x100, float:3.59E-43)
            r0 = r17
            r2 = r15
            r16 = r3
            r3 = r4
            r10 = 1
            r4 = r16
            r13 = 40
        L_0x0109:
            r5 = r23
            boolean r0 = r0.zza(r1, r2, r3, r4, r5)
            goto L_0x011f
        L_0x0110:
            r16 = r3
            r10 = 1
            r13 = 40
            java.lang.String r1 = "param"
            r3 = 100
            r0 = r17
            r2 = r15
            r4 = r16
            goto L_0x0109
        L_0x011f:
            if (r0 == 0) goto L_0x0123
            r0 = 0
            goto L_0x0124
        L_0x0123:
            r0 = 4
        L_0x0124:
            if (r0 == 0) goto L_0x0148
            java.lang.String r1 = "_ev"
            boolean r1 = r1.equals(r15)
            if (r1 != 0) goto L_0x0148
            boolean r0 = zza((android.os.Bundle) r11, (int) r0)
            if (r0 == 0) goto L_0x0185
            java.lang.String r0 = zza((java.lang.String) r15, (int) r13, (boolean) r10)
            java.lang.String r1 = "_ev"
            r11.putString(r1, r0)
            java.lang.Object r0 = r8.get(r15)
            zzb((android.os.Bundle) r11, (java.lang.Object) r0)
            goto L_0x0185
        L_0x0145:
            r10 = 0
            goto L_0x002f
        L_0x0148:
            boolean r0 = a((java.lang.String) r15)
            if (r0 == 0) goto L_0x0189
            int r14 = r14 + 1
            r0 = 25
            if (r14 <= r0) goto L_0x0189
            r0 = 48
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Event can't contain more than 25 params"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.google.android.gms.measurement.internal.zzef r1 = r17.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgm()
            com.google.android.gms.measurement.internal.zzed r2 = r17.zzy()
            r3 = r19
            java.lang.String r2 = r2.a((java.lang.String) r3)
            com.google.android.gms.measurement.internal.zzed r4 = r17.zzy()
            java.lang.String r4 = r4.a((android.os.Bundle) r8)
            r1.zza(r0, r2, r4)
            r0 = 5
            zza((android.os.Bundle) r11, (int) r0)
        L_0x0185:
            r11.remove(r15)
            goto L_0x0145
        L_0x0189:
            r3 = r19
            goto L_0x0145
        L_0x018c:
            r11 = 0
        L_0x018d:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjs.a(java.lang.String, java.lang.String, android.os.Bundle, java.util.List, boolean, boolean):android.os.Bundle");
    }

    /* access modifiers changed from: package-private */
    public final zzai a(String str, String str2, Bundle bundle, String str3, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (b(str2) == 0) {
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            bundle2.putString("_o", str3);
            return new zzai(str2, new zzah(a(a(str, str2, bundle2, CollectionUtils.listOf("_o"), false, false))), str3, j);
        }
        zzab().zzgk().zza("Invalid conditional property event name", zzy().c(str2));
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public final Object a(String str, Object obj) {
        boolean z;
        int i = 256;
        if ("_ev".equals(str)) {
            z = true;
        } else {
            if (!e(str)) {
                i = 100;
            }
            z = false;
        }
        return zza(i, obj, z);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(Bundle bundle, long j) {
        long j2 = bundle.getLong("_et");
        if (j2 != 0) {
            zzab().zzgn().zza("Params already contained engagement", Long.valueOf(j2));
        }
        bundle.putLong("_et", j + j2);
    }

    /* access modifiers changed from: package-private */
    public final void a(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                zzab().zzgp().zza("Not putting event parameter. Invalid value type. name, type", zzy().b(str), obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zza(bundle, i);
        if (!zzad().zze(str, zzak.zzip) ? !TextUtils.isEmpty(str2) : !(TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3))) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.b.zzae();
        this.b.zzq().logEvent("auto", "_err", bundle);
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(String str, int i, String str2) {
        if (str2 == null) {
            zzab().zzgm().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            zzab().zzgm().zza("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(String str, String str2) {
        if (str2 == null) {
            zzab().zzgm().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzab().zzgm().zza("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                zzab().zzgm().zza("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    zzab().zzgm().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(String str, String[] strArr, String str2) {
        boolean z;
        boolean z2;
        if (str2 == null) {
            zzab().zzgm().zza("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr2 = zztw;
        int length = strArr2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (str2.startsWith(strArr2[i])) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            zzab().zzgm().zza("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            Preconditions.checkNotNull(strArr);
            int length2 = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    z2 = false;
                    break;
                } else if (d(str2, strArr[i2])) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z2) {
                zzab().zzgm().zza("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int b(String str) {
        if (!b(NotificationCompat.CATEGORY_EVENT, str)) {
            return 2;
        }
        if (!a(NotificationCompat.CATEGORY_EVENT, zzgj.zzpn, str)) {
            return 13;
        }
        return !a(NotificationCompat.CATEGORY_EVENT, 40, str) ? 2 : 0;
    }

    /* access modifiers changed from: package-private */
    public final int b(String str, Object obj) {
        return "_ldl".equals(str) ? zza("user property referrer", str, zzbo(str), obj, false) : zza("user property", str, zzbo(str), obj, false) ? 0 : 7;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void b() {
        zzo();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzab().zzgn().zzao("Utils falling back to Random for random id");
            }
        }
        this.zzty.set(nextLong);
    }

    /* access modifiers changed from: package-private */
    public final boolean b(String str, String str2) {
        if (str2 == null) {
            zzab().zzgm().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzab().zzgm().zza("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        zzab().zzgm().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzab().zzgm().zza("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final int c(String str) {
        if (!b("user property", str)) {
            return 6;
        }
        if (!a("user property", zzgl.zzpp, str)) {
            return 15;
        }
        return !a("user property", 24, str) ? 6 : 0;
    }

    /* access modifiers changed from: package-private */
    public final Object c(String str, Object obj) {
        int zzbo;
        boolean z;
        if ("_ldl".equals(str)) {
            zzbo = zzbo(str);
            z = true;
        } else {
            zzbo = zzbo(str);
            z = false;
        }
        return zza(zzbo, obj, z);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final SecureRandom c() {
        zzo();
        if (this.zztx == null) {
            this.zztx = new SecureRandom();
        }
        return this.zztx;
    }

    /* access modifiers changed from: package-private */
    public final boolean c(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (zzbn(str)) {
                return true;
            }
            if (this.b.zzhw()) {
                zzab().zzgm().zza("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzef.a(str));
            }
            return false;
        } else if (TextUtils.isEmpty(str2)) {
            if (this.b.zzhw()) {
                zzab().zzgm().zzao("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            }
            return false;
        } else if (zzbn(str2)) {
            return true;
        } else {
            zzab().zzgm().zza("Invalid admob_app_id. Analytics disabled.", zzef.a(str2));
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean d(String str) {
        zzo();
        if (Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzab().zzgr().zza("Permission not granted", str);
        return false;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String e() {
        byte[] bArr = new byte[16];
        c().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    public final boolean f(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzbu = zzad().zzbu();
        zzae();
        return zzbu.equals(str);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final URL zza(long j, @NonNull String str, @NonNull String str2) {
        try {
            Preconditions.checkNotEmpty(str2);
            Preconditions.checkNotEmpty(str);
            return new URL(String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s", new Object[]{String.format("v%s.%s", new Object[]{Long.valueOf(j), Integer.valueOf(zzjx())}), str2, str}));
        } catch (IllegalArgumentException | MalformedURLException e) {
            zzab().zzgk().zza("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
            return null;
        }
    }

    public final void zza(int i, String str, String str2, int i2) {
        a((String) null, i, str, str2, i2);
    }

    public final void zza(zzp zzp, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i);
        try {
            zzp.zzb(bundle);
        } catch (RemoteException e) {
            this.b.zzab().zzgn().zza("Error returning int value to wrapper", e);
        }
    }

    public final void zza(zzp zzp, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j);
        try {
            zzp.zzb(bundle);
        } catch (RemoteException e) {
            this.b.zzab().zzgn().zza("Error returning long value to wrapper", e);
        }
    }

    public final void zza(zzp zzp, Bundle bundle) {
        try {
            zzp.zzb(bundle);
        } catch (RemoteException e) {
            this.b.zzab().zzgn().zza("Error returning bundle value to wrapper", e);
        }
    }

    public final void zza(zzp zzp, ArrayList<Bundle> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            zzp.zzb(bundle);
        } catch (RemoteException e) {
            this.b.zzab().zzgn().zza("Error returning bundle list to wrapper", e);
        }
    }

    public final void zza(zzp zzp, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z);
        try {
            zzp.zzb(bundle);
        } catch (RemoteException e) {
            this.b.zzab().zzgn().zza("Error returning boolean value to wrapper", e);
        }
    }

    public final void zza(zzp zzp, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zzp.zzb(bundle);
        } catch (RemoteException e) {
            this.b.zzab().zzgn().zza("Error returning byte array to wrapper", e);
        }
    }

    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }

    public final void zzb(zzp zzp, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zzp.zzb(bundle);
        } catch (RemoteException e) {
            this.b.zzab().zzgn().zza("Error returning string value to wrapper", e);
        }
    }

    public final int zzd(int i) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(getContext(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public final long zzjv() {
        long andIncrement;
        long j;
        if (this.zzty.get() == 0) {
            synchronized (this.zzty) {
                long nextLong = new Random(System.nanoTime() ^ zzx().currentTimeMillis()).nextLong();
                int i = this.zzag + 1;
                this.zzag = i;
                j = nextLong + ((long) i);
            }
            return j;
        }
        synchronized (this.zzty) {
            this.zzty.compareAndSet(-1, 1);
            andIncrement = this.zzty.getAndIncrement();
        }
        return andIncrement;
    }

    public final int zzjx() {
        if (this.zztz == null) {
            this.zztz = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(getContext()) / 1000);
        }
        return this.zztz.intValue();
    }

    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }
}
