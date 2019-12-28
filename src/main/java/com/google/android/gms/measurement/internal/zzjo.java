package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import bolts.MeasurementEvent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class zzjo extends zzjh {
    zzjo(zzjg zzjg) {
        super(zzjg);
    }

    static zzbs.zze a(zzbs.zzc zzc, String str) {
        for (zzbs.zze next : zzc.zzmj()) {
            if (next.getName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    static List<Long> a(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    static void a(zzbs.zzc.zza zza, String str, Object obj) {
        List<zzbs.zze> zzmj = zza.zzmj();
        int i = 0;
        while (true) {
            if (i >= zzmj.size()) {
                i = -1;
                break;
            } else if (str.equals(zzmj.get(i).getName())) {
                break;
            } else {
                i++;
            }
        }
        zzbs.zze.zza zzbz = zzbs.zze.zzng().zzbz(str);
        if (obj instanceof Long) {
            zzbz.zzam(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zzbz.zzca((String) obj);
        } else if (obj instanceof Double) {
            zzbz.zza(((Double) obj).doubleValue());
        }
        if (i >= 0) {
            zza.zza(i, zzbz);
        } else {
            zza.zza(zzbz);
        }
    }

    static boolean a(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean a(List<Long> list, int i) {
        if (i >= (list.size() << 6)) {
            return false;
        }
        return ((1 << (i % 64)) & list.get(i / 64).longValue()) != 0;
    }

    static Object b(zzbs.zzc zzc, String str) {
        zzbs.zze a = a(zzc, str);
        if (a == null) {
            return null;
        }
        if (a.zzmx()) {
            return a.zzmy();
        }
        if (a.zzna()) {
            return Long.valueOf(a.zznb());
        }
        if (a.zznd()) {
            return Double.valueOf(a.zzne());
        }
        return null;
    }

    private static String zza(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private final void zza(StringBuilder sb, int i, zzbk.zzb zzb) {
        if (zzb != null) {
            zza(sb, i);
            sb.append("filter {\n");
            if (zzb.zzkp()) {
                zza(sb, i, "complement", (Object) Boolean.valueOf(zzb.zzkq()));
            }
            zza(sb, i, "param_name", (Object) zzy().b(zzb.zzkr()));
            int i2 = i + 1;
            zzbk.zze zzkm = zzb.zzkm();
            if (zzkm != null) {
                zza(sb, i2);
                sb.append("string_filter");
                sb.append(" {\n");
                if (zzkm.zzlk()) {
                    zza(sb, i2, "match_type", (Object) zzkm.zzll().name());
                }
                zza(sb, i2, "expression", (Object) zzkm.zzln());
                if (zzkm.zzlo()) {
                    zza(sb, i2, "case_sensitive", (Object) Boolean.valueOf(zzkm.zzlp()));
                }
                if (zzkm.zzlr() > 0) {
                    zza(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String append : zzkm.zzlq()) {
                        zza(sb, i2 + 2);
                        sb.append(append);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zza(sb, i2);
                sb.append("}\n");
            }
            zza(sb, i2, "number_filter", zzb.zzko());
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzbk.zzc zzc) {
        if (zzc != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzc.zzku()) {
                zza(sb, i, "comparison_type", (Object) zzc.zzkv().name());
            }
            if (zzc.zzkw()) {
                zza(sb, i, "match_as_float", (Object) Boolean.valueOf(zzc.zzkx()));
            }
            zza(sb, i, "comparison_value", (Object) zzc.zzkz());
            zza(sb, i, "min_comparison_value", (Object) zzc.zzlb());
            zza(sb, i, "max_comparison_value", (Object) zzc.zzld());
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzbs.zzi zzi, String str2) {
        if (zzi != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (zzi.zzpz() != 0) {
                zza(sb, 4);
                sb.append("results: ");
                int i2 = 0;
                for (Long next : zzi.zzpy()) {
                    int i3 = i2 + 1;
                    if (i2 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next);
                    i2 = i3;
                }
                sb.append(10);
            }
            if (zzi.zzpw() != 0) {
                zza(sb, 4);
                sb.append("status: ");
                int i4 = 0;
                for (Long next2 : zzi.zzpv()) {
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next2);
                    i4 = i5;
                }
                sb.append(10);
            }
            if (zzad().f(str2)) {
                if (zzi.zzqc() != 0) {
                    zza(sb, 4);
                    sb.append("dynamic_filter_timestamps: {");
                    int i6 = 0;
                    for (zzbs.zzb next3 : zzi.zzqb()) {
                        int i7 = i6 + 1;
                        if (i6 != 0) {
                            sb.append(", ");
                        }
                        sb.append(next3.zzme() ? Integer.valueOf(next3.getIndex()) : null);
                        sb.append(":");
                        sb.append(next3.zzmf() ? Long.valueOf(next3.zzmg()) : null);
                        i6 = i7;
                    }
                    sb.append("}\n");
                }
                if (zzi.zzqf() != 0) {
                    zza(sb, 4);
                    sb.append("sequence_filter_timestamps: {");
                    int i8 = 0;
                    for (zzbs.zzj next4 : zzi.zzqe()) {
                        int i9 = i8 + 1;
                        if (i8 != 0) {
                            sb.append(", ");
                        }
                        sb.append(next4.zzme() ? Integer.valueOf(next4.getIndex()) : null);
                        sb.append(": [");
                        int i10 = 0;
                        for (Long longValue : next4.zzqk()) {
                            long longValue2 = longValue.longValue();
                            int i11 = i10 + 1;
                            if (i10 != 0) {
                                sb.append(", ");
                            }
                            sb.append(longValue2);
                            i10 = i11;
                        }
                        sb.append("]");
                        i8 = i9;
                    }
                    sb.append("}\n");
                }
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final long a(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzz().zzo();
        MessageDigest d = zzjs.d();
        if (d != null) {
            return zzjs.a(d.digest(bArr));
        }
        zzab().zzgk().zzao("Failed to get MD5");
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzab().zzgk().zzao("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends android.os.Parcelable> T a(byte[] r5, android.os.Parcelable.Creator<T> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ ParseException -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ ParseException -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ ParseException -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ ParseException -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ ParseException -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r5 = move-exception
            goto L_0x002d
        L_0x001c:
            com.google.android.gms.measurement.internal.zzef r5 = r4.zzab()     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgk()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.zzao(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjo.a(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    /* access modifiers changed from: package-private */
    public final String a(zzbk.zza zza) {
        if (zza == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zza.zzkb()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zza.getId()));
        }
        zza(sb, 0, MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, (Object) zzy().a(zza.zzjz()));
        String zza2 = zza(zza.zzkf(), zza.zzkg(), zza.zzki());
        if (!zza2.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza2);
        }
        zza(sb, 1, "event_count_filter", zza.zzke());
        sb.append("  filters {\n");
        for (zzbk.zzb zza3 : zza.zzkc()) {
            zza(sb, 2, zza3);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String a(zzbk.zzd zzd) {
        if (zzd == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzd.zzkb()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zzd.getId()));
        }
        zza(sb, 0, "property_name", (Object) zzy().c(zzd.getPropertyName()));
        String zza = zza(zzd.zzkf(), zzd.zzkg(), zzd.zzki());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza);
        }
        zza(sb, 1, zzd.zzli());
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String a(zzbs.zzf zzf) {
        List<zzbs.zze> zzmj;
        if (zzf == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        for (zzbs.zzg next : zzf.zzni()) {
            if (next != null) {
                zza(sb, 1);
                sb.append("bundle {\n");
                if (next.zznx()) {
                    zza(sb, 1, "protocol_version", (Object) Integer.valueOf(next.zzny()));
                }
                zza(sb, 1, "platform", (Object) next.zzom());
                if (next.zzoq()) {
                    zza(sb, 1, "gmp_version", (Object) Long.valueOf(next.zzao()));
                }
                if (next.zzor()) {
                    zza(sb, 1, "uploading_gmp_version", (Object) Long.valueOf(next.zzos()));
                }
                if (next.zzpq()) {
                    zza(sb, 1, "dynamite_version", (Object) Long.valueOf(next.zzaq()));
                }
                if (next.zzpi()) {
                    zza(sb, 1, "config_version", (Object) Long.valueOf(next.zzpj()));
                }
                zza(sb, 1, "gmp_app_id", (Object) next.getGmpAppId());
                zza(sb, 1, "admob_app_id", (Object) next.zzpp());
                zza(sb, 1, "app_id", (Object) next.zzag());
                zza(sb, 1, "app_version", (Object) next.zzal());
                if (next.zzpf()) {
                    zza(sb, 1, "app_version_major", (Object) Integer.valueOf(next.zzpg()));
                }
                zza(sb, 1, "firebase_instance_id", (Object) next.getFirebaseInstanceId());
                if (next.zzow()) {
                    zza(sb, 1, "dev_cert_hash", (Object) Long.valueOf(next.zzap()));
                }
                zza(sb, 1, "app_store", (Object) next.zzan());
                if (next.zzoc()) {
                    zza(sb, 1, "upload_timestamp_millis", (Object) Long.valueOf(next.zzod()));
                }
                if (next.zzoe()) {
                    zza(sb, 1, "start_timestamp_millis", (Object) Long.valueOf(next.zznq()));
                }
                if (next.zzof()) {
                    zza(sb, 1, "end_timestamp_millis", (Object) Long.valueOf(next.zznr()));
                }
                if (next.zzog()) {
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", (Object) Long.valueOf(next.zzoh()));
                }
                if (next.zzoj()) {
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", (Object) Long.valueOf(next.zzok()));
                }
                zza(sb, 1, "app_instance_id", (Object) next.getAppInstanceId());
                zza(sb, 1, "resettable_device_id", (Object) next.zzot());
                zza(sb, 1, "device_id", (Object) next.zzph());
                zza(sb, 1, "ds_id", (Object) next.zzpl());
                if (next.zzou()) {
                    zza(sb, 1, "limited_ad_tracking", (Object) Boolean.valueOf(next.zzov()));
                }
                zza(sb, 1, "os_version", (Object) next.getOsVersion());
                zza(sb, 1, "device_model", (Object) next.zzon());
                zza(sb, 1, "user_default_language", (Object) next.zzcr());
                if (next.zzoo()) {
                    zza(sb, 1, "time_zone_offset_minutes", (Object) Integer.valueOf(next.zzop()));
                }
                if (next.zzox()) {
                    zza(sb, 1, "bundle_sequential_index", (Object) Integer.valueOf(next.zzoy()));
                }
                if (next.zzpb()) {
                    zza(sb, 1, "service_upload", (Object) Boolean.valueOf(next.zzpc()));
                }
                zza(sb, 1, "health_monitor", (Object) next.zzoz());
                if (next.zzpk() && next.zzbd() != 0) {
                    zza(sb, 1, "android_id", (Object) Long.valueOf(next.zzbd()));
                }
                if (next.zzpn()) {
                    zza(sb, 1, "retry_counter", (Object) Integer.valueOf(next.zzpo()));
                }
                List<zzbs.zzk> zzno = next.zzno();
                if (zzno != null) {
                    for (zzbs.zzk next2 : zzno) {
                        if (next2 != null) {
                            zza(sb, 2);
                            sb.append("user_property {\n");
                            zza(sb, 2, "set_timestamp_millis", (Object) next2.zzqs() ? Long.valueOf(next2.zzqt()) : null);
                            zza(sb, 2, "name", (Object) zzy().c(next2.getName()));
                            zza(sb, 2, "string_value", (Object) next2.zzmy());
                            zza(sb, 2, "int_value", (Object) next2.zzna() ? Long.valueOf(next2.zznb()) : null);
                            zza(sb, 2, "double_value", (Object) next2.zznd() ? Double.valueOf(next2.zzne()) : null);
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzbs.zza> zzpd = next.zzpd();
                String zzag = next.zzag();
                if (zzpd != null) {
                    for (zzbs.zza next3 : zzpd) {
                        if (next3 != null) {
                            zza(sb, 2);
                            sb.append("audience_membership {\n");
                            if (next3.zzly()) {
                                zza(sb, 2, "audience_id", (Object) Integer.valueOf(next3.zzlz()));
                            }
                            if (next3.zzma()) {
                                zza(sb, 2, "new_audience", (Object) Boolean.valueOf(next3.zzmb()));
                            }
                            StringBuilder sb2 = sb;
                            String str = zzag;
                            zza(sb2, 2, "current_data", next3.zzlv(), str);
                            zza(sb2, 2, "previous_data", next3.zzlx(), str);
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzbs.zzc> zznl = next.zznl();
                if (zznl != null) {
                    for (zzbs.zzc next4 : zznl) {
                        if (next4 != null) {
                            zza(sb, 2);
                            sb.append("event {\n");
                            zza(sb, 2, "name", (Object) zzy().a(next4.getName()));
                            if (next4.zzml()) {
                                zza(sb, 2, "timestamp_millis", (Object) Long.valueOf(next4.getTimestampMillis()));
                            }
                            if (next4.zzmo()) {
                                zza(sb, 2, "previous_timestamp_millis", (Object) Long.valueOf(next4.zzmm()));
                            }
                            if (next4.zzmp()) {
                                zza(sb, 2, "count", (Object) Integer.valueOf(next4.getCount()));
                            }
                            if (!(next4.zzmk() == 0 || (zzmj = next4.zzmj()) == null)) {
                                for (zzbs.zze next5 : zzmj) {
                                    if (next5 != null) {
                                        zza(sb, 3);
                                        sb.append("param {\n");
                                        zza(sb, 3, "name", (Object) zzy().b(next5.getName()));
                                        zza(sb, 3, "string_value", (Object) next5.zzmy());
                                        zza(sb, 3, "int_value", (Object) next5.zzna() ? Long.valueOf(next5.zznb()) : null);
                                        zza(sb, 3, "double_value", (Object) next5.zznd() ? Double.valueOf(next5.zzne()) : null);
                                        zza(sb, 3);
                                        sb.append("}\n");
                                    }
                                }
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zza(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final List<Long> a(List<Long> list, List<Integer> list2) {
        int i;
        ArrayList arrayList = new ArrayList(list);
        for (Integer next : list2) {
            if (next.intValue() < 0) {
                zzab().zzgn().zza("Ignoring negative bit index to be cleared", next);
            } else {
                int intValue = next.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    zzab().zzgn().zza("Ignoring bit index greater than bitSet size", next, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & (~(1 << (next.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i2 = size2;
            i = size;
            size = i2;
            if (size >= 0 && ((Long) arrayList.get(size)).longValue() == 0) {
                size2 = size - 1;
            }
        }
        return arrayList.subList(0, i);
    }

    /* access modifiers changed from: package-private */
    public final void a(zzbs.zze.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zzmu().zzmv().zzmw();
        if (obj instanceof String) {
            zza.zzca((String) obj);
        } else if (obj instanceof Long) {
            zza.zzam(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else {
            zzab().zzgk().zza("Ignoring invalid (type) event param value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(zzbs.zzk.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zzqz().zzra().zzrb();
        if (obj instanceof String) {
            zza.zzdc((String) obj);
        } else if (obj instanceof Long) {
            zza.zzbl(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zzc(((Double) obj).doubleValue());
        } else {
            zzab().zzgk().zza("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzx().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean a(zzai zzai, zzn zzn) {
        Preconditions.checkNotNull(zzai);
        Preconditions.checkNotNull(zzn);
        if (!TextUtils.isEmpty(zzn.zzcg) || !TextUtils.isEmpty(zzn.zzcu)) {
            return true;
        }
        zzae();
        return false;
    }

    /* access modifiers changed from: package-private */
    public final byte[] b(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzab().zzgk().zza("Failed to ungzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final byte[] c(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzab().zzgk().zza("Failed to gzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final List<Integer> d() {
        Map<String, String> zzk = zzak.zzk(this.a.getContext());
        if (zzk == null || zzk.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = zzak.zzhr.get(null).intValue();
        Iterator<Map.Entry<String, String>> it = zzk.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry next = it.next();
            if (((String) next.getKey()).startsWith("measurement.id.")) {
                try {
                    int parseInt = Integer.parseInt((String) next.getValue());
                    if (parseInt != 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                        if (arrayList.size() >= intValue) {
                            zzab().zzgn().zza("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    zzab().zzgn().zza("Experiment ID NumberFormatException", e);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
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

    public final /* bridge */ /* synthetic */ zzjo zzgw() {
        return super.zzgw();
    }

    public final /* bridge */ /* synthetic */ zzp zzgx() {
        return super.zzgx();
    }

    public final /* bridge */ /* synthetic */ zzx zzgy() {
        return super.zzgy();
    }

    public final /* bridge */ /* synthetic */ zzfd zzgz() {
        return super.zzgz();
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
