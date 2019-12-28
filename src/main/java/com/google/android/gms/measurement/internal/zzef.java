package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzef extends zzge {
    /* access modifiers changed from: private */
    public char zzkg = 0;
    @GuardedBy("this")
    private String zzkh;
    private final zzeh zzki = new zzeh(this, 6, false, false);
    private final zzeh zzkj = new zzeh(this, 6, true, false);
    private final zzeh zzkk = new zzeh(this, 6, false, true);
    private final zzeh zzkl = new zzeh(this, 5, false, false);
    private final zzeh zzkm = new zzeh(this, 5, true, false);
    private final zzeh zzkn = new zzeh(this, 5, false, true);
    private final zzeh zzko = new zzeh(this, 4, false, false);
    private final zzeh zzkp = new zzeh(this, 3, false, false);
    private final zzeh zzkq = new zzeh(this, 2, false, false);
    /* access modifiers changed from: private */
    public long zzr = -1;

    zzef(zzfj zzfj) {
        super(zzfj);
    }

    protected static Object a(String str) {
        if (str == null) {
            return null;
        }
        return new zzeg(str);
    }

    static String a(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String zza = zza(z, obj);
        String zza2 = zza(z, obj2);
        String zza3 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zza)) {
            sb.append(str2);
            sb.append(zza);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zza2)) {
            sb.append(str2);
            sb.append(zza2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str2);
            sb.append(zza3);
        }
        return sb.toString();
    }

    @VisibleForTesting
    private static String zza(boolean z, Object obj) {
        String className;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        int i = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            String str = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)));
            long round2 = Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(str).length());
            sb.append(str);
            sb.append(round);
            sb.append("...");
            sb.append(str);
            sb.append(round2);
            return sb.toString();
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (!(obj instanceof Throwable)) {
                return obj instanceof zzeg ? ((zzeg) obj).zzkr : z ? "-" : String.valueOf(obj);
            }
            Throwable th = (Throwable) obj;
            StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
            String zzan = zzan(zzfj.class.getCanonicalName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i];
                if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzan(className).equals(zzan)) {
                    sb2.append(": ");
                    sb2.append(stackTraceElement);
                    break;
                }
                i++;
            }
            return sb2.toString();
        }
    }

    private static String zzan(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    @VisibleForTesting
    private final String zzgt() {
        String str;
        synchronized (this) {
            if (this.zzkh == null) {
                this.zzkh = this.b.zzhz() != null ? this.b.zzhz() : zzs.a();
            }
            str = this.zzkh;
        }
        return str;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final void a(int i, String str) {
        Log.println(i, zzgt(), str);
    }

    /* access modifiers changed from: protected */
    public final void a(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        String str2;
        if (!z && a(i)) {
            a(i, a(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            Preconditions.checkNotNull(str);
            zzfc b = this.b.b();
            if (b == null) {
                str2 = "Scheduler not set. Not logging error/warn";
            } else if (!b.k()) {
                str2 = "Scheduler not initialized. Not logging error/warn";
            } else {
                if (i < 0) {
                    i = 0;
                }
                b.zza((Runnable) new zzee(this, i >= 9 ? 8 : i, str, obj, obj2, obj3));
                return;
            }
            a(6, str2);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final boolean a(int i) {
        return Log.isLoggable(zzgt(), i);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
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

    public final zzeh zzgk() {
        return this.zzki;
    }

    public final zzeh zzgl() {
        return this.zzkj;
    }

    public final zzeh zzgm() {
        return this.zzkk;
    }

    public final zzeh zzgn() {
        return this.zzkl;
    }

    public final zzeh zzgo() {
        return this.zzkm;
    }

    public final zzeh zzgp() {
        return this.zzkn;
    }

    public final zzeh zzgq() {
        return this.zzko;
    }

    public final zzeh zzgr() {
        return this.zzkp;
    }

    public final zzeh zzgs() {
        return this.zzkq;
    }

    public final String zzgu() {
        Pair<String, Long> zzhl = zzac().zzli.zzhl();
        if (zzhl == null || zzhl == zzeo.a) {
            return null;
        }
        String valueOf = String.valueOf(zzhl.second);
        String str = (String) zzhl.first;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(":");
        sb.append(str);
        return sb.toString();
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
