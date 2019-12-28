package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import bolts.MeasurementEvent;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzey;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzx extends zzjh {
    /* access modifiers changed from: private */
    public static final String[] zzek = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzel = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzem = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzen = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzeo = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzep = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzeq = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzer = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzy zzes = new zzy(this, getContext(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zzjd zzet = new zzjd(zzx());

    zzx(zzjg zzjg) {
        super(zzjg);
    }

    @WorkerThread
    private final long zza(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = d().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = d().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j2 = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j2;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzab().zzgk().zzao("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzab().zzgk().zzao("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzab().zzgk().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzbk.zza zza) {
        c();
        zzo();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zza);
        Integer num = null;
        if (TextUtils.isEmpty(zza.zzjz())) {
            zzeh zzgn = zzab().zzgn();
            Object a = zzef.a(str);
            Integer valueOf = Integer.valueOf(i);
            if (zza.zzkb()) {
                num = Integer.valueOf(zza.getId());
            }
            zzgn.zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", a, valueOf, String.valueOf(num));
            return false;
        }
        byte[] byteArray = zza.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zza.zzkb() ? Integer.valueOf(zza.getId()) : null);
        contentValues.put(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, zza.zzjz());
        if (zzad().zze(str, zzak.zziy)) {
            contentValues.put("session_scoped", zza.zzkh() ? Boolean.valueOf(zza.zzki()) : null);
        }
        contentValues.put("data", byteArray);
        try {
            if (d().insertWithOnConflict("event_filters", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzab().zzgk().zza("Failed to insert event filter (got -1). appId", zzef.a(str));
            return true;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing event filter. appId", zzef.a(str), e);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzbk.zzd zzd) {
        c();
        zzo();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzd);
        Integer num = null;
        if (TextUtils.isEmpty(zzd.getPropertyName())) {
            zzeh zzgn = zzab().zzgn();
            Object a = zzef.a(str);
            Integer valueOf = Integer.valueOf(i);
            if (zzd.zzkb()) {
                num = Integer.valueOf(zzd.getId());
            }
            zzgn.zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", a, valueOf, String.valueOf(num));
            return false;
        }
        byte[] byteArray = zzd.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzd.zzkb() ? Integer.valueOf(zzd.getId()) : null);
        contentValues.put("property_name", zzd.getPropertyName());
        if (zzad().zze(str, zzak.zziy)) {
            contentValues.put("session_scoped", zzd.zzkh() ? Boolean.valueOf(zzd.zzki()) : null);
        }
        contentValues.put("data", byteArray);
        try {
            if (d().insertWithOnConflict("property_filters", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzab().zzgk().zza("Failed to insert property filter (got -1). appId", zzef.a(str));
            return false;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing property filter. appId", zzef.a(str), e);
            return false;
        }
    }

    private final boolean zza(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        c();
        zzo();
        SQLiteDatabase d = d();
        try {
            long zza = zza("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE, zzad().zzb(str, zzak.zzhk)));
            if (zza <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 140);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return d.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Database error querying filters. appId", zzef.a(str), e);
            return false;
        }
    }

    private final boolean zzcg() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<java.lang.Integer>> a(java.lang.String r8) {
        /*
            r7 = this;
            r7.c()
            r7.zzo()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r7.d()
            r2 = 0
            java.lang.String r3 = "select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x006a, all -> 0x0067 }
            r5 = 0
            r4[r5] = r8     // Catch:{ SQLiteException -> 0x006a, all -> 0x0067 }
            r6 = 1
            r4[r6] = r8     // Catch:{ SQLiteException -> 0x006a, all -> 0x0067 }
            android.database.Cursor r1 = r1.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x006a, all -> 0x0067 }
            boolean r3 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0065 }
            if (r3 != 0) goto L_0x0032
            java.util.Map r8 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0065 }
            if (r1 == 0) goto L_0x0031
            r1.close()
        L_0x0031:
            return r8
        L_0x0032:
            int r3 = r1.getInt(r5)     // Catch:{ SQLiteException -> 0x0065 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0065 }
            java.lang.Object r4 = r0.get(r4)     // Catch:{ SQLiteException -> 0x0065 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ SQLiteException -> 0x0065 }
            if (r4 != 0) goto L_0x004e
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0065 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0065 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0065 }
            r0.put(r3, r4)     // Catch:{ SQLiteException -> 0x0065 }
        L_0x004e:
            int r3 = r1.getInt(r6)     // Catch:{ SQLiteException -> 0x0065 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0065 }
            r4.add(r3)     // Catch:{ SQLiteException -> 0x0065 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0065 }
            if (r3 != 0) goto L_0x0032
            if (r1 == 0) goto L_0x0064
            r1.close()
        L_0x0064:
            return r0
        L_0x0065:
            r0 = move-exception
            goto L_0x006c
        L_0x0067:
            r8 = move-exception
            r1 = r2
            goto L_0x0084
        L_0x006a:
            r0 = move-exception
            r1 = r2
        L_0x006c:
            com.google.android.gms.measurement.internal.zzef r3 = r7.zzab()     // Catch:{ all -> 0x0083 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x0083 }
            java.lang.String r4 = "Database error querying scoped filters. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r8)     // Catch:{ all -> 0x0083 }
            r3.zza(r4, r8, r0)     // Catch:{ all -> 0x0083 }
            if (r1 == 0) goto L_0x0082
            r1.close()
        L_0x0082:
            return r2
        L_0x0083:
            r8 = move-exception
        L_0x0084:
            if (r1 == 0) goto L_0x0089
            r1.close()
        L_0x0089:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.a(java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbk.zza>> a(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.c()
            r12.zzo()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.d()
            r9 = 0
            java.lang.String r2 = "event_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            java.lang.String r4 = "app_id=? AND event_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            r10 = 0
            r5[r10] = r13     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            r11 = 1
            r5[r11] = r14     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x008c }
            if (r1 != 0) goto L_0x0042
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x008c }
            if (r14 == 0) goto L_0x0041
            r14.close()
        L_0x0041:
            return r13
        L_0x0042:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x008c }
            com.google.android.gms.internal.measurement.zzel r2 = com.google.android.gms.internal.measurement.zzel.zztq()     // Catch:{ IOException -> 0x006e }
            com.google.android.gms.internal.measurement.zzbk$zza r1 = com.google.android.gms.internal.measurement.zzbk.zza.zza(r1, r2)     // Catch:{ IOException -> 0x006e }
            int r2 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x008c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x008c }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x008c }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x008c }
            if (r3 != 0) goto L_0x006a
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x008c }
            r3.<init>()     // Catch:{ SQLiteException -> 0x008c }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x008c }
            r0.put(r2, r3)     // Catch:{ SQLiteException -> 0x008c }
        L_0x006a:
            r3.add(r1)     // Catch:{ SQLiteException -> 0x008c }
            goto L_0x0080
        L_0x006e:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzef r2 = r12.zzab()     // Catch:{ SQLiteException -> 0x008c }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()     // Catch:{ SQLiteException -> 0x008c }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r13)     // Catch:{ SQLiteException -> 0x008c }
            r2.zza(r3, r4, r1)     // Catch:{ SQLiteException -> 0x008c }
        L_0x0080:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x008c }
            if (r1 != 0) goto L_0x0042
            if (r14 == 0) goto L_0x008b
            r14.close()
        L_0x008b:
            return r0
        L_0x008c:
            r0 = move-exception
            goto L_0x0093
        L_0x008e:
            r13 = move-exception
            r14 = r9
            goto L_0x00ab
        L_0x0091:
            r0 = move-exception
            r14 = r9
        L_0x0093:
            com.google.android.gms.measurement.internal.zzef r1 = r12.zzab()     // Catch:{ all -> 0x00aa }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ all -> 0x00aa }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r13)     // Catch:{ all -> 0x00aa }
            r1.zza(r2, r13, r0)     // Catch:{ all -> 0x00aa }
            if (r14 == 0) goto L_0x00a9
            r14.close()
        L_0x00a9:
            return r9
        L_0x00aa:
            r13 = move-exception
        L_0x00ab:
            if (r14 == 0) goto L_0x00b0
            r14.close()
        L_0x00b0:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.a(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(String str, zzbv[] zzbvArr) {
        boolean z;
        zzeh zzgn;
        String str2;
        Object a;
        Integer num;
        c();
        zzo();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbvArr);
        SQLiteDatabase d = d();
        d.beginTransaction();
        try {
            c();
            zzo();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase d2 = d();
            d2.delete("property_filters", "app_id=?", new String[]{str});
            d2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzbv zzbv : zzbvArr) {
                c();
                zzo();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzbv);
                Preconditions.checkNotNull(zzbv.zzzh);
                Preconditions.checkNotNull(zzbv.zzzg);
                if (zzbv.zzzf != null) {
                    int intValue = zzbv.zzzf.intValue();
                    zzbk.zza[] zzaArr = zzbv.zzzh;
                    int length = zzaArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            zzbk.zzd[] zzdArr = zzbv.zzzg;
                            int length2 = zzdArr.length;
                            int i2 = 0;
                            while (i2 < length2) {
                                if (!zzdArr[i2].zzkb()) {
                                    zzgn = zzab().zzgn();
                                    str2 = "Property filter with no ID. Audience definition ignored. appId, audienceId";
                                    a = zzef.a(str);
                                    num = zzbv.zzzf;
                                } else {
                                    i2++;
                                }
                            }
                            zzbk.zza[] zzaArr2 = zzbv.zzzh;
                            int length3 = zzaArr2.length;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= length3) {
                                    z = true;
                                    break;
                                } else if (!zza(str, intValue, zzaArr2[i3])) {
                                    z = false;
                                    break;
                                } else {
                                    i3++;
                                }
                            }
                            if (z) {
                                zzbk.zzd[] zzdArr2 = zzbv.zzzg;
                                int length4 = zzdArr2.length;
                                int i4 = 0;
                                while (true) {
                                    if (i4 >= length4) {
                                        break;
                                    } else if (!zza(str, intValue, zzdArr2[i4])) {
                                        z = false;
                                        break;
                                    } else {
                                        i4++;
                                    }
                                }
                            }
                            if (!z) {
                                c();
                                zzo();
                                Preconditions.checkNotEmpty(str);
                                SQLiteDatabase d3 = d();
                                d3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                d3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                            }
                        } else if (!zzaArr[i].zzkb()) {
                            zzgn = zzab().zzgn();
                            str2 = "Event filter with no ID. Audience definition ignored. appId, audienceId";
                            a = zzef.a(str);
                            num = zzbv.zzzf;
                            break;
                        } else {
                            i++;
                        }
                    }
                    zzgn.zza(str2, a, num);
                    break;
                } else {
                    zzab().zzgn().zza("Audience with no ID. appId", zzef.a(str));
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzbv zzbv2 : zzbvArr) {
                arrayList.add(zzbv2.zzzf);
            }
            zza(str, (List<Integer>) arrayList);
            d.setTransactionSuccessful();
        } finally {
            d.endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void a(List<Long> list) {
        zzo();
        c();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzcg()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zza(sb3.toString(), (String[]) null) > 0) {
                zzab().zzgn().zzao("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase d = d();
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 127);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                d.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                zzab().zzgk().zza("Error incrementing retry count. error", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0093  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzbs.zzi> b(java.lang.String r12) {
        /*
            r11 = this;
            r11.c()
            r11.zzo()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.d()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            java.lang.String r2 = "audience_id"
            java.lang.String r3 = "current_results"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            java.lang.String r3 = "app_id=?"
            r9 = 1
            java.lang.String[] r4 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            r10 = 0
            r4[r10] = r12     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            boolean r1 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 != 0) goto L_0x0033
            if (r0 == 0) goto L_0x0032
            r0.close()
        L_0x0032:
            return r8
        L_0x0033:
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap     // Catch:{ SQLiteException -> 0x0072 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x0072 }
        L_0x0038:
            int r2 = r0.getInt(r10)     // Catch:{ SQLiteException -> 0x0072 }
            byte[] r3 = r0.getBlob(r9)     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzel r4 = com.google.android.gms.internal.measurement.zzel.zztq()     // Catch:{ IOException -> 0x0050 }
            com.google.android.gms.internal.measurement.zzbs$zzi r3 = com.google.android.gms.internal.measurement.zzbs.zzi.zze(r3, r4)     // Catch:{ IOException -> 0x0050 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0072 }
            r1.put(r2, r3)     // Catch:{ SQLiteException -> 0x0072 }
            goto L_0x0066
        L_0x0050:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzef r4 = r11.zzab()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgk()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0072 }
            r4.zza(r5, r6, r2, r3)     // Catch:{ SQLiteException -> 0x0072 }
        L_0x0066:
            boolean r2 = r0.moveToNext()     // Catch:{ SQLiteException -> 0x0072 }
            if (r2 != 0) goto L_0x0038
            if (r0 == 0) goto L_0x0071
            r0.close()
        L_0x0071:
            return r1
        L_0x0072:
            r1 = move-exception
            goto L_0x0079
        L_0x0074:
            r12 = move-exception
            r0 = r8
            goto L_0x0091
        L_0x0077:
            r1 = move-exception
            r0 = r8
        L_0x0079:
            com.google.android.gms.measurement.internal.zzef r2 = r11.zzab()     // Catch:{ all -> 0x0090 }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r12)     // Catch:{ all -> 0x0090 }
            r2.zza(r3, r12, r1)     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x008f
            r0.close()
        L_0x008f:
            return r8
        L_0x0090:
            r12 = move-exception
        L_0x0091:
            if (r0 == 0) goto L_0x0096
            r0.close()
        L_0x0096:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.b(java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbk.zzd>> b(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.c()
            r12.zzo()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.d()
            r9 = 0
            java.lang.String r2 = "property_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            java.lang.String r4 = "app_id=? AND property_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            r10 = 0
            r5[r10] = r13     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            r11 = 1
            r5[r11] = r14     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008e }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x008c }
            if (r1 != 0) goto L_0x0042
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x008c }
            if (r14 == 0) goto L_0x0041
            r14.close()
        L_0x0041:
            return r13
        L_0x0042:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x008c }
            com.google.android.gms.internal.measurement.zzel r2 = com.google.android.gms.internal.measurement.zzel.zztq()     // Catch:{ IOException -> 0x006e }
            com.google.android.gms.internal.measurement.zzbk$zzd r1 = com.google.android.gms.internal.measurement.zzbk.zzd.zzb(r1, r2)     // Catch:{ IOException -> 0x006e }
            int r2 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x008c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x008c }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x008c }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x008c }
            if (r3 != 0) goto L_0x006a
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x008c }
            r3.<init>()     // Catch:{ SQLiteException -> 0x008c }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x008c }
            r0.put(r2, r3)     // Catch:{ SQLiteException -> 0x008c }
        L_0x006a:
            r3.add(r1)     // Catch:{ SQLiteException -> 0x008c }
            goto L_0x0080
        L_0x006e:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzef r2 = r12.zzab()     // Catch:{ SQLiteException -> 0x008c }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()     // Catch:{ SQLiteException -> 0x008c }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r13)     // Catch:{ SQLiteException -> 0x008c }
            r2.zza(r3, r4, r1)     // Catch:{ SQLiteException -> 0x008c }
        L_0x0080:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x008c }
            if (r1 != 0) goto L_0x0042
            if (r14 == 0) goto L_0x008b
            r14.close()
        L_0x008b:
            return r0
        L_0x008c:
            r0 = move-exception
            goto L_0x0093
        L_0x008e:
            r13 = move-exception
            r14 = r9
            goto L_0x00ab
        L_0x0091:
            r0 = move-exception
            r14 = r9
        L_0x0093:
            com.google.android.gms.measurement.internal.zzef r1 = r12.zzab()     // Catch:{ all -> 0x00aa }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ all -> 0x00aa }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r13)     // Catch:{ all -> 0x00aa }
            r1.zza(r2, r13, r0)     // Catch:{ all -> 0x00aa }
            if (r14 == 0) goto L_0x00a9
            r14.close()
        L_0x00a9:
            return r9
        L_0x00aa:
            r13 = move-exception
        L_0x00ab:
            if (r14 == 0) goto L_0x00b0
            r14.close()
        L_0x00b0:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.b(java.lang.String, java.lang.String):java.util.Map");
    }

    @WorkerThread
    public final void beginTransaction() {
        c();
        d().beginTransaction();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final long c(String str, String str2) {
        long j;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        c();
        SQLiteDatabase d = d();
        d.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
            sb.append("select ");
            sb.append(str2);
            sb.append(" from app2 where app_id=?");
            j = zza(sb.toString(), new String[]{str}, -1);
            if (j == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (d.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                    zzab().zzgk().zza("Failed to insert column (got -1). appId", zzef.a(str), str2);
                    d.endTransaction();
                    return -1;
                }
                j = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + j));
                if (((long) d.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzab().zzgk().zza("Failed to update column (got 0). appId", zzef.a(str), str2);
                    d.endTransaction();
                    return -1;
                }
                d.setTransactionSuccessful();
                d.endTransaction();
                return j;
            } catch (SQLiteException e) {
                e = e;
            }
        } catch (SQLiteException e2) {
            e = e2;
            j = 0;
            try {
                zzab().zzgk().zza("Error inserting column. appId", zzef.a(str), str2, e);
                d.endTransaction();
                return j;
            } catch (Throwable th) {
                d.endTransaction();
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final SQLiteDatabase d() {
        zzo();
        try {
            return this.zzes.getWritableDatabase();
        } catch (SQLiteException e) {
            zzab().zzgn().zza("Error opening database", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void e() {
        int delete;
        zzo();
        c();
        if (zzcg()) {
            long j = zzac().zzlm.get();
            long elapsedRealtime = zzx().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzak.zzhd.get(null).longValue()) {
                zzac().zzlm.set(elapsedRealtime);
                zzo();
                c();
                if (zzcg() && (delete = d().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzx().currentTimeMillis()), String.valueOf(zzs.zzbs())})) > 0) {
                    zzab().zzgs().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    @WorkerThread
    public final void endTransaction() {
        c();
        d().endTransaction();
    }

    @WorkerThread
    public final void setTransactionSuccessful() {
        c();
        d().setTransactionSuccessful();
    }

    public final long zza(zzbs.zzg zzg) {
        zzo();
        c();
        Preconditions.checkNotNull(zzg);
        Preconditions.checkNotEmpty(zzg.zzag());
        byte[] byteArray = zzg.toByteArray();
        long a = zzgw().a(byteArray);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzg.zzag());
        contentValues.put("metadata_fingerprint", Long.valueOf(a));
        contentValues.put("metadata", byteArray);
        try {
            d().insertWithOnConflict("raw_events_metadata", (String) null, contentValues, 4);
            return a;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing raw event metadata. appId", zzef.a(zzg.zzag()), e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzbs.zzc, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzo()
            r7.c()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.d()     // Catch:{ SQLiteException -> 0x0072, all -> 0x006f }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0072, all -> 0x006f }
            r4 = 0
            r3[r4] = r8     // Catch:{ SQLiteException -> 0x0072, all -> 0x006f }
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x0072, all -> 0x006f }
            r6 = 1
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0072, all -> 0x006f }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0072, all -> 0x006f }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x006d }
            if (r2 != 0) goto L_0x0037
            com.google.android.gms.measurement.internal.zzef r8 = r7.zzab()     // Catch:{ SQLiteException -> 0x006d }
            com.google.android.gms.measurement.internal.zzeh r8 = r8.zzgs()     // Catch:{ SQLiteException -> 0x006d }
            java.lang.String r9 = "Main event not found"
            r8.zzao(r9)     // Catch:{ SQLiteException -> 0x006d }
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r4)     // Catch:{ SQLiteException -> 0x006d }
            long r3 = r1.getLong(r6)     // Catch:{ SQLiteException -> 0x006d }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x006d }
            com.google.android.gms.internal.measurement.zzel r4 = com.google.android.gms.internal.measurement.zzel.zztq()     // Catch:{ IOException -> 0x0055 }
            com.google.android.gms.internal.measurement.zzbs$zzc r8 = com.google.android.gms.internal.measurement.zzbs.zzc.zzc(r2, r4)     // Catch:{ IOException -> 0x0055 }
            android.util.Pair r8 = android.util.Pair.create(r8, r3)     // Catch:{ SQLiteException -> 0x006d }
            if (r1 == 0) goto L_0x0054
            r1.close()
        L_0x0054:
            return r8
        L_0x0055:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzef r3 = r7.zzab()     // Catch:{ SQLiteException -> 0x006d }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ SQLiteException -> 0x006d }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r8)     // Catch:{ SQLiteException -> 0x006d }
            r3.zza(r4, r8, r9, r2)     // Catch:{ SQLiteException -> 0x006d }
            if (r1 == 0) goto L_0x006c
            r1.close()
        L_0x006c:
            return r0
        L_0x006d:
            r8 = move-exception
            goto L_0x0074
        L_0x006f:
            r8 = move-exception
            r1 = r0
            goto L_0x0088
        L_0x0072:
            r8 = move-exception
            r1 = r0
        L_0x0074:
            com.google.android.gms.measurement.internal.zzef r9 = r7.zzab()     // Catch:{ all -> 0x0087 }
            com.google.android.gms.measurement.internal.zzeh r9 = r9.zzgk()     // Catch:{ all -> 0x0087 }
            java.lang.String r2 = "Error selecting main event"
            r9.zza(r2, r8)     // Catch:{ all -> 0x0087 }
            if (r1 == 0) goto L_0x0086
            r1.close()
        L_0x0086:
            return r0
        L_0x0087:
            r8 = move-exception
        L_0x0088:
            if (r1 == 0) goto L_0x008d
            r1.close()
        L_0x008d:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    @WorkerThread
    public final zzw zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzo();
        c();
        String[] strArr = {str};
        zzw zzw = new zzw();
        Cursor cursor = null;
        try {
            SQLiteDatabase d = d();
            cursor = d.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                zzab().zzgn().zza("Not updating daily counts, app is not known. appId", zzef.a(str));
                if (cursor != null) {
                    cursor.close();
                }
                return zzw;
            }
            if (cursor.getLong(0) == j) {
                zzw.b = cursor.getLong(1);
                zzw.a = cursor.getLong(2);
                zzw.c = cursor.getLong(3);
                zzw.d = cursor.getLong(4);
                zzw.e = cursor.getLong(5);
            }
            if (z) {
                zzw.b++;
            }
            if (z2) {
                zzw.a++;
            }
            if (z3) {
                zzw.c++;
            }
            if (z4) {
                zzw.d++;
            }
            if (z5) {
                zzw.e++;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j));
            contentValues.put("daily_public_events_count", Long.valueOf(zzw.a));
            contentValues.put("daily_events_count", Long.valueOf(zzw.b));
            contentValues.put("daily_conversions_count", Long.valueOf(zzw.c));
            contentValues.put("daily_error_events_count", Long.valueOf(zzw.d));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzw.e));
            d.update("apps", contentValues, "app_id=?", strArr);
            if (cursor != null) {
                cursor.close();
            }
            return zzw;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error updating daily counts. appId", zzef.a(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return zzw;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c9 A[LOOP:0: B:18:0x0054->B:42:0x00c9, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00cb A[EDGE_INSN: B:56:0x00cb->B:43:0x00cb ?: BREAK  , SYNTHETIC] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<android.util.Pair<com.google.android.gms.internal.measurement.zzbs.zzg, java.lang.Long>> zza(java.lang.String r13, int r14, int r15) {
        /*
            r12 = this;
            r12.zzo()
            r12.c()
            r0 = 1
            r1 = 0
            if (r14 <= 0) goto L_0x000c
            r2 = 1
            goto L_0x000d
        L_0x000c:
            r2 = 0
        L_0x000d:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)
            if (r15 <= 0) goto L_0x0014
            r2 = 1
            goto L_0x0015
        L_0x0014:
            r2 = 0
        L_0x0015:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r12.d()     // Catch:{ SQLiteException -> 0x00d3 }
            java.lang.String r4 = "queue"
            java.lang.String r5 = "rowid"
            java.lang.String r6 = "data"
            java.lang.String r7 = "retry_count"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6, r7}     // Catch:{ SQLiteException -> 0x00d3 }
            java.lang.String r6 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00d3 }
            r7[r1] = r13     // Catch:{ SQLiteException -> 0x00d3 }
            r8 = 0
            r9 = 0
            java.lang.String r10 = "rowid"
            java.lang.String r11 = java.lang.String.valueOf(r14)     // Catch:{ SQLiteException -> 0x00d3 }
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x00d3 }
            boolean r14 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00d3 }
            if (r14 != 0) goto L_0x004e
            java.util.List r13 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x00d3 }
            if (r2 == 0) goto L_0x004d
            r2.close()
        L_0x004d:
            return r13
        L_0x004e:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00d3 }
            r14.<init>()     // Catch:{ SQLiteException -> 0x00d3 }
            r3 = 0
        L_0x0054:
            long r4 = r2.getLong(r1)     // Catch:{ SQLiteException -> 0x00d3 }
            byte[] r6 = r2.getBlob(r0)     // Catch:{ IOException -> 0x00b3 }
            com.google.android.gms.measurement.internal.zzjo r7 = r12.zzgw()     // Catch:{ IOException -> 0x00b3 }
            byte[] r6 = r7.b(r6)     // Catch:{ IOException -> 0x00b3 }
            boolean r7 = r14.isEmpty()     // Catch:{ SQLiteException -> 0x00d3 }
            if (r7 != 0) goto L_0x006e
            int r7 = r6.length     // Catch:{ SQLiteException -> 0x00d3 }
            int r7 = r7 + r3
            if (r7 > r15) goto L_0x00cb
        L_0x006e:
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r7 = com.google.android.gms.internal.measurement.zzbs.zzg.zzpr()     // Catch:{ IOException -> 0x00a0 }
            com.google.android.gms.internal.measurement.zzel r8 = com.google.android.gms.internal.measurement.zzel.zztq()     // Catch:{ IOException -> 0x00a0 }
            com.google.android.gms.internal.measurement.zzdh r7 = r7.zzf(r6, r8)     // Catch:{ IOException -> 0x00a0 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r7 = (com.google.android.gms.internal.measurement.zzbs.zzg.zza) r7     // Catch:{ IOException -> 0x00a0 }
            r8 = 2
            boolean r9 = r2.isNull(r8)     // Catch:{ SQLiteException -> 0x00d3 }
            if (r9 != 0) goto L_0x008a
            int r8 = r2.getInt(r8)     // Catch:{ SQLiteException -> 0x00d3 }
            r7.zzw(r8)     // Catch:{ SQLiteException -> 0x00d3 }
        L_0x008a:
            int r6 = r6.length     // Catch:{ SQLiteException -> 0x00d3 }
            int r3 = r3 + r6
            com.google.android.gms.internal.measurement.zzgi r6 = r7.zzug()     // Catch:{ SQLiteException -> 0x00d3 }
            com.google.android.gms.internal.measurement.zzey r6 = (com.google.android.gms.internal.measurement.zzey) r6     // Catch:{ SQLiteException -> 0x00d3 }
            com.google.android.gms.internal.measurement.zzbs$zzg r6 = (com.google.android.gms.internal.measurement.zzbs.zzg) r6     // Catch:{ SQLiteException -> 0x00d3 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x00d3 }
            android.util.Pair r4 = android.util.Pair.create(r6, r4)     // Catch:{ SQLiteException -> 0x00d3 }
            r14.add(r4)     // Catch:{ SQLiteException -> 0x00d3 }
            goto L_0x00c3
        L_0x00a0:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzef r5 = r12.zzab()     // Catch:{ SQLiteException -> 0x00d3 }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgk()     // Catch:{ SQLiteException -> 0x00d3 }
            java.lang.String r6 = "Failed to merge queued bundle. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r13)     // Catch:{ SQLiteException -> 0x00d3 }
        L_0x00af:
            r5.zza(r6, r7, r4)     // Catch:{ SQLiteException -> 0x00d3 }
            goto L_0x00c3
        L_0x00b3:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzef r5 = r12.zzab()     // Catch:{ SQLiteException -> 0x00d3 }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgk()     // Catch:{ SQLiteException -> 0x00d3 }
            java.lang.String r6 = "Failed to unzip queued bundle. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r13)     // Catch:{ SQLiteException -> 0x00d3 }
            goto L_0x00af
        L_0x00c3:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00d3 }
            if (r4 == 0) goto L_0x00cb
            if (r3 <= r15) goto L_0x0054
        L_0x00cb:
            if (r2 == 0) goto L_0x00d0
            r2.close()
        L_0x00d0:
            return r14
        L_0x00d1:
            r13 = move-exception
            goto L_0x00ef
        L_0x00d3:
            r14 = move-exception
            com.google.android.gms.measurement.internal.zzef r15 = r12.zzab()     // Catch:{ all -> 0x00d1 }
            com.google.android.gms.measurement.internal.zzeh r15 = r15.zzgk()     // Catch:{ all -> 0x00d1 }
            java.lang.String r0 = "Error querying bundles. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r13)     // Catch:{ all -> 0x00d1 }
            r15.zza(r0, r13, r14)     // Catch:{ all -> 0x00d1 }
            java.util.List r13 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00d1 }
            if (r2 == 0) goto L_0x00ee
            r2.close()
        L_0x00ee:
            return r13
        L_0x00ef:
            if (r2 == 0) goto L_0x00f4
            r2.close()
        L_0x00f4:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zza(java.lang.String, int, int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f9, code lost:
        r12 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0100, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0101, code lost:
        r12 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0104, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0105, code lost:
        r12 = r21;
        r11 = r22;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0100 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0127  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzjp> zza(java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r22)
            r21.zzo()
            r21.c()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0104, all -> 0x0100 }
            r3 = 3
            r2.<init>(r3)     // Catch:{ SQLiteException -> 0x0104, all -> 0x0100 }
            r11 = r22
            r2.add(r11)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            java.lang.String r5 = "app_id=?"
            r4.<init>(r5)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            boolean r5 = android.text.TextUtils.isEmpty(r23)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            if (r5 != 0) goto L_0x0032
            r5 = r23
            r2.add(r5)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r6 = " and origin=?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            goto L_0x0034
        L_0x0032:
            r5 = r23
        L_0x0034:
            boolean r6 = android.text.TextUtils.isEmpty(r24)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            if (r6 != 0) goto L_0x004c
            java.lang.String r6 = java.lang.String.valueOf(r24)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r7 = "*"
            java.lang.String r6 = r6.concat(r7)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            r2.add(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r6 = " and name glob ?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
        L_0x004c:
            int r6 = r2.size()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.Object[] r2 = r2.toArray(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            r16 = r2
            java.lang.String[] r16 = (java.lang.String[]) r16     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            android.database.sqlite.SQLiteDatabase r12 = r21.d()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r13 = "user_attributes"
            java.lang.String r2 = "name"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String r8 = "origin"
            java.lang.String[] r14 = new java.lang.String[]{r2, r6, r7, r8}     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r15 = r4.toString()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            java.lang.String r20 = "1001"
            android.database.Cursor r2 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            boolean r4 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            if (r4 != 0) goto L_0x0088
            if (r2 == 0) goto L_0x0087
            r2.close()
        L_0x0087:
            return r0
        L_0x0088:
            int r4 = r0.size()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r6 = 1000(0x3e8, float:1.401E-42)
            if (r4 < r6) goto L_0x00a4
            com.google.android.gms.measurement.internal.zzef r3 = r21.zzab()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            java.lang.String r4 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r3.zza(r4, r6)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r12 = r21
            goto L_0x00e3
        L_0x00a4:
            r4 = 0
            java.lang.String r7 = r2.getString(r4)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r4 = 1
            long r8 = r2.getLong(r4)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r4 = 2
            r12 = r21
            java.lang.Object r10 = r12.zza((android.database.Cursor) r2, (int) r4)     // Catch:{ SQLiteException -> 0x00ee }
            java.lang.String r13 = r2.getString(r3)     // Catch:{ SQLiteException -> 0x00ee }
            if (r10 != 0) goto L_0x00cf
            com.google.android.gms.measurement.internal.zzef r4 = r21.zzab()     // Catch:{ SQLiteException -> 0x00eb }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgk()     // Catch:{ SQLiteException -> 0x00eb }
            java.lang.String r5 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r22)     // Catch:{ SQLiteException -> 0x00eb }
            r14 = r24
            r4.zza(r5, r6, r13, r14)     // Catch:{ SQLiteException -> 0x00eb }
            goto L_0x00dd
        L_0x00cf:
            r14 = r24
            com.google.android.gms.measurement.internal.zzjp r15 = new com.google.android.gms.measurement.internal.zzjp     // Catch:{ SQLiteException -> 0x00eb }
            r4 = r15
            r5 = r22
            r6 = r13
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x00eb }
            r0.add(r15)     // Catch:{ SQLiteException -> 0x00eb }
        L_0x00dd:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00eb }
            if (r4 != 0) goto L_0x00e9
        L_0x00e3:
            if (r2 == 0) goto L_0x00e8
            r2.close()
        L_0x00e8:
            return r0
        L_0x00e9:
            r5 = r13
            goto L_0x0088
        L_0x00eb:
            r0 = move-exception
            r5 = r13
            goto L_0x010c
        L_0x00ee:
            r0 = move-exception
            goto L_0x010c
        L_0x00f0:
            r0 = move-exception
            r12 = r21
            goto L_0x0124
        L_0x00f4:
            r0 = move-exception
            r12 = r21
            goto L_0x010c
        L_0x00f8:
            r0 = move-exception
            r12 = r21
            goto L_0x010b
        L_0x00fc:
            r0 = move-exception
            r12 = r21
            goto L_0x0109
        L_0x0100:
            r0 = move-exception
            r12 = r21
            goto L_0x0125
        L_0x0104:
            r0 = move-exception
            r12 = r21
            r11 = r22
        L_0x0109:
            r5 = r23
        L_0x010b:
            r2 = r1
        L_0x010c:
            com.google.android.gms.measurement.internal.zzef r3 = r21.zzab()     // Catch:{ all -> 0x0123 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x0123 }
            java.lang.String r4 = "(2)Error querying user properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r22)     // Catch:{ all -> 0x0123 }
            r3.zza(r4, r6, r5, r0)     // Catch:{ all -> 0x0123 }
            if (r2 == 0) goto L_0x0122
            r2.close()
        L_0x0122:
            return r1
        L_0x0123:
            r0 = move-exception
        L_0x0124:
            r1 = r2
        L_0x0125:
            if (r1 == 0) goto L_0x012a
            r1.close()
        L_0x012a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zza(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public final void zza(zzae zzae) {
        Preconditions.checkNotNull(zzae);
        zzo();
        c();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzae.a);
        contentValues.put("name", zzae.b);
        contentValues.put("lifetime_count", Long.valueOf(zzae.c));
        contentValues.put("current_bundle_count", Long.valueOf(zzae.d));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzae.f));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzae.g));
        contentValues.put("last_bundled_day", zzae.h);
        contentValues.put("last_sampled_complex_event_id", zzae.i);
        contentValues.put("last_sampling_rate", zzae.j);
        if (zzad().zze(zzae.a, zzak.zziz)) {
            contentValues.put("current_session_count", Long.valueOf(zzae.e));
        }
        contentValues.put("last_exempt_from_sampling", (zzae.k == null || !zzae.k.booleanValue()) ? null : 1L);
        try {
            if (d().insertWithOnConflict("events", (String) null, contentValues, 5) == -1) {
                zzab().zzgk().zza("Failed to insert/update event aggregates (got -1). appId", zzef.a(zzae.a));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing event aggregates. appId", zzef.a(zzae.a), e);
        }
    }

    @WorkerThread
    public final void zza(zzf zzf) {
        Preconditions.checkNotNull(zzf);
        zzo();
        c();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzf.zzag());
        contentValues.put("app_instance_id", zzf.getAppInstanceId());
        contentValues.put("gmp_app_id", zzf.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzf.zzai());
        contentValues.put("last_bundle_index", Long.valueOf(zzf.zzar()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzf.zzaj()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzf.zzak()));
        contentValues.put("app_version", zzf.zzal());
        contentValues.put("app_store", zzf.zzan());
        contentValues.put("gmp_version", Long.valueOf(zzf.zzao()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzf.zzap()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzf.isMeasurementEnabled()));
        contentValues.put("day", Long.valueOf(zzf.zzav()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzf.zzaw()));
        contentValues.put("daily_events_count", Long.valueOf(zzf.zzax()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzf.zzay()));
        contentValues.put("config_fetched_time", Long.valueOf(zzf.zzas()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzf.zzat()));
        contentValues.put("app_version_int", Long.valueOf(zzf.zzam()));
        contentValues.put("firebase_instance_id", zzf.getFirebaseInstanceId());
        contentValues.put("daily_error_events_count", Long.valueOf(zzf.zzba()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzf.zzaz()));
        contentValues.put("health_monitor_sample", zzf.zzbb());
        contentValues.put("android_id", Long.valueOf(zzf.zzbd()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzf.zzbe()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzf.zzbf()));
        contentValues.put("admob_app_id", zzf.zzah());
        contentValues.put("dynamite_version", Long.valueOf(zzf.zzaq()));
        if (zzf.zzbh() != null) {
            if (zzf.zzbh().size() == 0) {
                zzab().zzgn().zza("Safelisted events should not be an empty list. appId", zzf.zzag());
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzf.zzbh()));
            }
        }
        try {
            SQLiteDatabase d = d();
            if (((long) d.update("apps", contentValues, "app_id = ?", new String[]{zzf.zzag()})) == 0 && d.insertWithOnConflict("apps", (String) null, contentValues, 5) == -1) {
                zzab().zzgk().zza("Failed to insert/update app (got -1). appId", zzef.a(zzf.zzag()));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing app. appId", zzef.a(zzf.zzag()), e);
        }
    }

    @WorkerThread
    public final boolean zza(zzbs.zzg zzg, boolean z) {
        zzeh zzgk;
        String str;
        zzo();
        c();
        Preconditions.checkNotNull(zzg);
        Preconditions.checkNotEmpty(zzg.zzag());
        Preconditions.checkState(zzg.zzof());
        e();
        long currentTimeMillis = zzx().currentTimeMillis();
        if (zzg.zznr() < currentTimeMillis - zzs.zzbs() || zzg.zznr() > zzs.zzbs() + currentTimeMillis) {
            zzab().zzgn().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzef.a(zzg.zzag()), Long.valueOf(currentTimeMillis), Long.valueOf(zzg.zznr()));
        }
        try {
            byte[] c = zzgw().c(zzg.toByteArray());
            zzab().zzgs().zza("Saving bundle, size", Integer.valueOf(c.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzg.zzag());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzg.zznr()));
            contentValues.put("data", c);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzg.zzpn()) {
                contentValues.put("retry_count", Integer.valueOf(zzg.zzpo()));
            }
            try {
                if (d().insert("queue", (String) null, contentValues) != -1) {
                    return true;
                }
                zzab().zzgk().zza("Failed to insert bundle (got -1). appId", zzef.a(zzg.zzag()));
                return false;
            } catch (SQLiteException e) {
                e = e;
                zzgk = zzab().zzgk();
                str = "Error storing bundle. appId";
                zzgk.zza(str, zzef.a(zzg.zzag()), e);
                return false;
            }
        } catch (IOException e2) {
            e = e2;
            zzgk = zzab().zzgk();
            str = "Data loss. Failed to serialize bundle. appId";
            zzgk.zza(str, zzef.a(zzg.zzag()), e);
            return false;
        }
    }

    public final boolean zza(zzaf zzaf, long j, boolean z) {
        zzo();
        c();
        Preconditions.checkNotNull(zzaf);
        Preconditions.checkNotEmpty(zzaf.a);
        zzbs.zzc.zza zzah = zzbs.zzc.zzmq().zzah(zzaf.d);
        Iterator<String> it = zzaf.e.iterator();
        while (it.hasNext()) {
            String next = it.next();
            zzbs.zze.zza zzbz = zzbs.zze.zzng().zzbz(next);
            zzgw().a(zzbz, zzaf.e.a(next));
            zzah.zza(zzbz);
        }
        byte[] byteArray = ((zzbs.zzc) ((zzey) zzah.zzug())).toByteArray();
        zzab().zzgs().zza("Saving event, name, data size", zzy().a(zzaf.b), Integer.valueOf(byteArray.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzaf.a);
        contentValues.put("name", zzaf.b);
        contentValues.put("timestamp", Long.valueOf(zzaf.c));
        contentValues.put("metadata_fingerprint", Long.valueOf(j));
        contentValues.put("data", byteArray);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (d().insert("raw_events", (String) null, contentValues) != -1) {
                return true;
            }
            zzab().zzgk().zza("Failed to insert raw event (got -1). appId", zzef.a(zzaf.a));
            return false;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing raw event. appId", zzef.a(zzaf.a), e);
            return false;
        }
    }

    @WorkerThread
    public final boolean zza(zzjp zzjp) {
        Preconditions.checkNotNull(zzjp);
        zzo();
        c();
        if (zze(zzjp.a, zzjp.c) == null) {
            if (zzjs.a(zzjp.c)) {
                if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzjp.a}) >= 25) {
                    return false;
                }
            } else if (!zzad().zze(zzjp.a, zzak.zzij)) {
                if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzjp.a, zzjp.b}) >= 25) {
                    return false;
                }
            } else if (!"_npa".equals(zzjp.c)) {
                if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzjp.a, zzjp.b}) >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzjp.a);
        contentValues.put("origin", zzjp.b);
        contentValues.put("name", zzjp.c);
        contentValues.put("set_timestamp", Long.valueOf(zzjp.d));
        zza(contentValues, "value", zzjp.e);
        try {
            if (d().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) == -1) {
                zzab().zzgk().zza("Failed to insert/update user property (got -1). appId", zzef.a(zzjp.a));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing user property. appId", zzef.a(zzjp.a), e);
        }
        return true;
    }

    @WorkerThread
    public final boolean zza(zzq zzq) {
        Preconditions.checkNotNull(zzq);
        zzo();
        c();
        if (zze(zzq.packageName, zzq.zzdw.name) == null) {
            if (zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzq.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzq.packageName);
        contentValues.put("origin", zzq.origin);
        contentValues.put("name", zzq.zzdw.name);
        zza(contentValues, "value", zzq.zzdw.getValue());
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzq.active));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzq.triggerEventName);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzq.triggerTimeout));
        zzz();
        contentValues.put("timed_out_event", zzjs.a((Parcelable) zzq.zzdx));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzq.creationTimestamp));
        zzz();
        contentValues.put("triggered_event", zzjs.a((Parcelable) zzq.zzdy));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzq.zzdw.zztr));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzq.timeToLive));
        zzz();
        contentValues.put("expired_event", zzjs.a((Parcelable) zzq.zzdz));
        try {
            if (d().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) == -1) {
                zzab().zzgk().zza("Failed to insert/update conditional user property (got -1)", zzef.a(zzq.packageName));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing conditional user property", zzef.a(zzq.packageName), e);
        }
        return true;
    }

    public final boolean zza(String str, Long l, long j, zzbs.zzc zzc) {
        zzo();
        c();
        Preconditions.checkNotNull(zzc);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] byteArray = zzc.toByteArray();
        zzab().zzgs().zza("Saving complex main event, appId, data size", zzy().a(str), Integer.valueOf(byteArray.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", byteArray);
        try {
            if (d().insertWithOnConflict("main_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzab().zzgk().zza("Failed to insert complex main event (got -1). appId", zzef.a(str));
            return false;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing complex main event. appId", zzef.a(str), e);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a1  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzjp> zzaa(java.lang.String r14) {
        /*
            r13 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            r13.zzo()
            r13.c()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r13.d()     // Catch:{ SQLiteException -> 0x0085, all -> 0x0082 }
            java.lang.String r3 = "user_attributes"
            java.lang.String r4 = "name"
            java.lang.String r5 = "origin"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6, r7}     // Catch:{ SQLiteException -> 0x0085, all -> 0x0082 }
            java.lang.String r5 = "app_id=?"
            r11 = 1
            java.lang.String[] r6 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x0085, all -> 0x0082 }
            r12 = 0
            r6[r12] = r14     // Catch:{ SQLiteException -> 0x0085, all -> 0x0082 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "1000"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0085, all -> 0x0082 }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0080 }
            if (r3 != 0) goto L_0x003f
            if (r2 == 0) goto L_0x003e
            r2.close()
        L_0x003e:
            return r0
        L_0x003f:
            java.lang.String r7 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x0080 }
            java.lang.String r3 = r2.getString(r11)     // Catch:{ SQLiteException -> 0x0080 }
            if (r3 != 0) goto L_0x004b
            java.lang.String r3 = ""
        L_0x004b:
            r6 = r3
            r3 = 2
            long r8 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0080 }
            r3 = 3
            java.lang.Object r10 = r13.zza((android.database.Cursor) r2, (int) r3)     // Catch:{ SQLiteException -> 0x0080 }
            if (r10 != 0) goto L_0x006a
            com.google.android.gms.measurement.internal.zzef r3 = r13.zzab()     // Catch:{ SQLiteException -> 0x0080 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ SQLiteException -> 0x0080 }
            java.lang.String r4 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r14)     // Catch:{ SQLiteException -> 0x0080 }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0080 }
            goto L_0x0074
        L_0x006a:
            com.google.android.gms.measurement.internal.zzjp r3 = new com.google.android.gms.measurement.internal.zzjp     // Catch:{ SQLiteException -> 0x0080 }
            r4 = r3
            r5 = r14
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x0080 }
            r0.add(r3)     // Catch:{ SQLiteException -> 0x0080 }
        L_0x0074:
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0080 }
            if (r3 != 0) goto L_0x003f
            if (r2 == 0) goto L_0x007f
            r2.close()
        L_0x007f:
            return r0
        L_0x0080:
            r0 = move-exception
            goto L_0x0087
        L_0x0082:
            r14 = move-exception
            r2 = r1
            goto L_0x009f
        L_0x0085:
            r0 = move-exception
            r2 = r1
        L_0x0087:
            com.google.android.gms.measurement.internal.zzef r3 = r13.zzab()     // Catch:{ all -> 0x009e }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x009e }
            java.lang.String r4 = "Error querying user properties. appId"
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r14)     // Catch:{ all -> 0x009e }
            r3.zza(r4, r14, r0)     // Catch:{ all -> 0x009e }
            if (r2 == 0) goto L_0x009d
            r2.close()
        L_0x009d:
            return r1
        L_0x009e:
            r14 = move-exception
        L_0x009f:
            if (r2 == 0) goto L_0x00a4
            r2.close()
        L_0x00a4:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zzaa(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0119 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x011d A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0153 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0155 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0164 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0179 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0195 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0196 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01a5 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01c0 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01fd  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0204  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzf zzab(java.lang.String r34) {
        /*
            r33 = this;
            r1 = r34
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r34)
            r33.zzo()
            r33.c()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r33.d()     // Catch:{ SQLiteException -> 0x01e6, all -> 0x01e1 }
            java.lang.String r4 = "apps"
            java.lang.String r5 = "app_instance_id"
            java.lang.String r6 = "gmp_app_id"
            java.lang.String r7 = "resettable_device_id_hash"
            java.lang.String r8 = "last_bundle_index"
            java.lang.String r9 = "last_bundle_start_timestamp"
            java.lang.String r10 = "last_bundle_end_timestamp"
            java.lang.String r11 = "app_version"
            java.lang.String r12 = "app_store"
            java.lang.String r13 = "gmp_version"
            java.lang.String r14 = "dev_cert_hash"
            java.lang.String r15 = "measurement_enabled"
            java.lang.String r16 = "day"
            java.lang.String r17 = "daily_public_events_count"
            java.lang.String r18 = "daily_events_count"
            java.lang.String r19 = "daily_conversions_count"
            java.lang.String r20 = "config_fetched_time"
            java.lang.String r21 = "failed_config_fetch_time"
            java.lang.String r22 = "app_version_int"
            java.lang.String r23 = "firebase_instance_id"
            java.lang.String r24 = "daily_error_events_count"
            java.lang.String r25 = "daily_realtime_events_count"
            java.lang.String r26 = "health_monitor_sample"
            java.lang.String r27 = "android_id"
            java.lang.String r28 = "adid_reporting_enabled"
            java.lang.String r29 = "ssaid_reporting_enabled"
            java.lang.String r30 = "admob_app_id"
            java.lang.String r31 = "dynamite_version"
            java.lang.String r32 = "safelisted_events"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32}     // Catch:{ SQLiteException -> 0x01e6, all -> 0x01e1 }
            java.lang.String r6 = "app_id=?"
            r0 = 1
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x01e6, all -> 0x01e1 }
            r11 = 0
            r7[r11] = r1     // Catch:{ SQLiteException -> 0x01e6, all -> 0x01e1 }
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x01e6, all -> 0x01e1 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x01dd, all -> 0x01d9 }
            if (r4 != 0) goto L_0x0069
            if (r3 == 0) goto L_0x0068
            r3.close()
        L_0x0068:
            return r2
        L_0x0069:
            com.google.android.gms.measurement.internal.zzf r4 = new com.google.android.gms.measurement.internal.zzf     // Catch:{ SQLiteException -> 0x01dd, all -> 0x01d9 }
            r5 = r33
            com.google.android.gms.measurement.internal.zzjg r6 = r5.a     // Catch:{ SQLiteException -> 0x01d7 }
            com.google.android.gms.measurement.internal.zzfj r6 = r6.f()     // Catch:{ SQLiteException -> 0x01d7 }
            r4.<init>(r6, r1)     // Catch:{ SQLiteException -> 0x01d7 }
            java.lang.String r6 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            java.lang.String r6 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzb((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 2
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzd(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 3
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzk(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 4
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zze((long) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 5
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzf((long) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 6
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzf((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 7
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzg((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 8
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzh((long) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 9
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzi(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 10
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            if (r7 != 0) goto L_0x00d7
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            if (r6 == 0) goto L_0x00d5
            goto L_0x00d7
        L_0x00d5:
            r6 = 0
            goto L_0x00d8
        L_0x00d7:
            r6 = 1
        L_0x00d8:
            r4.setMeasurementEnabled(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 11
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzn(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 12
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzo(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 13
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzp(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 14
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzq(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 15
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzl(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 16
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzm(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 17
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            if (r7 == 0) goto L_0x011d
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0122
        L_0x011d:
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x01d7 }
        L_0x0122:
            r4.zzg((long) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 18
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zze((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 19
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzs(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 20
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzr(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 21
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzh((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 22
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r8 = 0
            if (r7 == 0) goto L_0x0155
            r6 = r8
            goto L_0x0159
        L_0x0155:
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01d7 }
        L_0x0159:
            r4.zzt(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 23
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            if (r7 != 0) goto L_0x016d
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            if (r6 == 0) goto L_0x016b
            goto L_0x016d
        L_0x016b:
            r6 = 0
            goto L_0x016e
        L_0x016d:
            r6 = 1
        L_0x016e:
            r4.zzb((boolean) r6)     // Catch:{ SQLiteException -> 0x01d7 }
            r6 = 24
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            if (r7 != 0) goto L_0x0181
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01d7 }
            if (r6 == 0) goto L_0x0180
            goto L_0x0181
        L_0x0180:
            r0 = 0
        L_0x0181:
            r4.zzc((boolean) r0)     // Catch:{ SQLiteException -> 0x01d7 }
            r0 = 25
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zzc((java.lang.String) r0)     // Catch:{ SQLiteException -> 0x01d7 }
            r0 = 26
            boolean r6 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x01d7 }
            if (r6 == 0) goto L_0x0196
            goto L_0x019a
        L_0x0196:
            long r8 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x01d7 }
        L_0x019a:
            r4.zzj(r8)     // Catch:{ SQLiteException -> 0x01d7 }
            r0 = 27
            boolean r6 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x01d7 }
            if (r6 != 0) goto L_0x01b7
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x01d7 }
            java.lang.String r6 = ","
            r7 = -1
            java.lang.String[] r0 = r0.split(r6, r7)     // Catch:{ SQLiteException -> 0x01d7 }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ SQLiteException -> 0x01d7 }
            r4.zza((java.util.List<java.lang.String>) r0)     // Catch:{ SQLiteException -> 0x01d7 }
        L_0x01b7:
            r4.zzaf()     // Catch:{ SQLiteException -> 0x01d7 }
            boolean r0 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x01d7 }
            if (r0 == 0) goto L_0x01d1
            com.google.android.gms.measurement.internal.zzef r0 = r33.zzab()     // Catch:{ SQLiteException -> 0x01d7 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ SQLiteException -> 0x01d7 }
            java.lang.String r6 = "Got multiple records for app, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r34)     // Catch:{ SQLiteException -> 0x01d7 }
            r0.zza(r6, r7)     // Catch:{ SQLiteException -> 0x01d7 }
        L_0x01d1:
            if (r3 == 0) goto L_0x01d6
            r3.close()
        L_0x01d6:
            return r4
        L_0x01d7:
            r0 = move-exception
            goto L_0x01ea
        L_0x01d9:
            r0 = move-exception
            r5 = r33
            goto L_0x0202
        L_0x01dd:
            r0 = move-exception
            r5 = r33
            goto L_0x01ea
        L_0x01e1:
            r0 = move-exception
            r5 = r33
            r3 = r2
            goto L_0x0202
        L_0x01e6:
            r0 = move-exception
            r5 = r33
            r3 = r2
        L_0x01ea:
            com.google.android.gms.measurement.internal.zzef r4 = r33.zzab()     // Catch:{ all -> 0x0201 }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgk()     // Catch:{ all -> 0x0201 }
            java.lang.String r6 = "Error querying app. appId"
            java.lang.Object r1 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r34)     // Catch:{ all -> 0x0201 }
            r4.zza(r6, r1, r0)     // Catch:{ all -> 0x0201 }
            if (r3 == 0) goto L_0x0200
            r3.close()
        L_0x0200:
            return r2
        L_0x0201:
            r0 = move-exception
        L_0x0202:
            if (r3 == 0) goto L_0x0207
            r3.close()
        L_0x0207:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zzab(java.lang.String):com.google.android.gms.measurement.internal.zzf");
    }

    public final long zzac(String str) {
        Preconditions.checkNotEmpty(str);
        zzo();
        c();
        try {
            return (long) d().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzad().zzb(str, zzak.zzgu))))});
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error deleting over the limit events. appId", zzef.a(str), e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0073  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzad(java.lang.String r11) {
        /*
            r10 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            r10.zzo()
            r10.c()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.d()     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            java.lang.String r2 = "apps"
            java.lang.String r3 = "remote_config"
            java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            java.lang.String r4 = "app_id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            r9 = 0
            r5[r9] = r11     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0052 }
            if (r2 != 0) goto L_0x0031
            if (r1 == 0) goto L_0x0030
            r1.close()
        L_0x0030:
            return r0
        L_0x0031:
            byte[] r2 = r1.getBlob(r9)     // Catch:{ SQLiteException -> 0x0052 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0052 }
            if (r3 == 0) goto L_0x004c
            com.google.android.gms.measurement.internal.zzef r3 = r10.zzab()     // Catch:{ SQLiteException -> 0x0052 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ SQLiteException -> 0x0052 }
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x0052 }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0052 }
        L_0x004c:
            if (r1 == 0) goto L_0x0051
            r1.close()
        L_0x0051:
            return r2
        L_0x0052:
            r2 = move-exception
            goto L_0x0059
        L_0x0054:
            r11 = move-exception
            r1 = r0
            goto L_0x0071
        L_0x0057:
            r2 = move-exception
            r1 = r0
        L_0x0059:
            com.google.android.gms.measurement.internal.zzef r3 = r10.zzab()     // Catch:{ all -> 0x0070 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x0070 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r11)     // Catch:{ all -> 0x0070 }
            r3.zza(r4, r11, r2)     // Catch:{ all -> 0x0070 }
            if (r1 == 0) goto L_0x006f
            r1.close()
        L_0x006f:
            return r0
        L_0x0070:
            r11 = move-exception
        L_0x0071:
            if (r1 == 0) goto L_0x0076
            r1.close()
        L_0x0076:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zzad(java.lang.String):byte[]");
    }

    public final long zzag(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    @WorkerThread
    public final List<zzq> zzb(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzo();
        c();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzb(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final List<zzq> zzb(String str, String[] strArr) {
        zzo();
        c();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = d().query("conditional_properties", new String[]{"app_id", "origin", "name", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, str, strArr, (String) null, (String) null, "rowid", "1001");
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            while (true) {
                if (arrayList.size() < 1000) {
                    boolean z = false;
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Object zza = zza(cursor, 3);
                    if (cursor.getInt(4) != 0) {
                        z = true;
                    }
                    String string4 = cursor.getString(5);
                    long j = cursor.getLong(6);
                    long j2 = cursor.getLong(8);
                    long j3 = cursor.getLong(10);
                    boolean z2 = z;
                    zzq zzq = r3;
                    zzq zzq2 = new zzq(string, string2, new zzjn(string3, j3, zza, string2), j2, z2, string4, (zzai) zzgw().a(cursor.getBlob(7), zzai.CREATOR), j, (zzai) zzgw().a(cursor.getBlob(9), zzai.CREATOR), cursor.getLong(11), (zzai) zzgw().a(cursor.getBlob(12), zzai.CREATOR));
                    arrayList.add(zzq);
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } else {
                    zzab().zzgk().zza("Read more than the max allowed conditional properties, ignoring extra", 1000);
                    break;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error querying conditional user property value", e);
            List<zzq> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzby() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.d()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r1
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x002b
        L_0x0024:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003f
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            com.google.android.gms.measurement.internal.zzef r3 = r6.zzab()     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zza(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zzby():java.lang.String");
    }

    public final boolean zzbz() {
        return zza("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x015b  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzae zzc(java.lang.String r27, java.lang.String r28) {
        /*
            r26 = this;
            r15 = r27
            r14 = r28
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r27)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r28)
            r26.zzo()
            r26.c()
            com.google.android.gms.measurement.internal.zzs r0 = r26.zzad()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzak.zziz
            boolean r0 = r0.zze(r15, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            java.lang.String r2 = "lifetime_count"
            java.lang.String r3 = "current_bundle_count"
            java.lang.String r4 = "last_fire_timestamp"
            java.lang.String r5 = "last_bundled_timestamp"
            java.lang.String r6 = "last_bundled_day"
            java.lang.String r7 = "last_sampled_complex_event_id"
            java.lang.String r8 = "last_sampling_rate"
            java.lang.String r9 = "last_exempt_from_sampling"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3, r4, r5, r6, r7, r8, r9}
            java.util.List r2 = java.util.Arrays.asList(r2)
            r1.<init>(r2)
            if (r0 == 0) goto L_0x003e
            java.lang.String r2 = "current_session_count"
            r1.add(r2)
        L_0x003e:
            r18 = 0
            android.database.sqlite.SQLiteDatabase r2 = r26.d()     // Catch:{ SQLiteException -> 0x0134, all -> 0x0130 }
            java.lang.String r3 = "events"
            r10 = 0
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0134, all -> 0x0130 }
            java.lang.Object[] r1 = r1.toArray(r4)     // Catch:{ SQLiteException -> 0x0134, all -> 0x0130 }
            r4 = r1
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ SQLiteException -> 0x0134, all -> 0x0130 }
            java.lang.String r5 = "app_id=? and name=?"
            r1 = 2
            java.lang.String[] r6 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x0134, all -> 0x0130 }
            r6[r10] = r15     // Catch:{ SQLiteException -> 0x0134, all -> 0x0130 }
            r11 = 1
            r6[r11] = r14     // Catch:{ SQLiteException -> 0x0134, all -> 0x0130 }
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0134, all -> 0x0130 }
            boolean r2 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            if (r2 != 0) goto L_0x006d
            if (r12 == 0) goto L_0x006c
            r12.close()
        L_0x006c:
            return r18
        L_0x006d:
            long r4 = r12.getLong(r10)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            long r6 = r12.getLong(r11)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            long r16 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r1 = 3
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r8 = 0
            if (r2 == 0) goto L_0x0085
            r19 = r8
            goto L_0x008b
        L_0x0085:
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r19 = r1
        L_0x008b:
            r1 = 4
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            if (r2 == 0) goto L_0x0095
            r21 = r18
            goto L_0x009f
        L_0x0095:
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r21 = r1
        L_0x009f:
            r1 = 5
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            if (r2 == 0) goto L_0x00a9
            r22 = r18
            goto L_0x00b3
        L_0x00a9:
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r22 = r1
        L_0x00b3:
            r1 = 6
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            if (r2 == 0) goto L_0x00bd
            r23 = r18
            goto L_0x00c7
        L_0x00bd:
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r23 = r1
        L_0x00c7:
            r1 = 7
            boolean r2 = r12.isNull(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            if (r2 != 0) goto L_0x00e0
            long r1 = r12.getLong(r1)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r24 = 1
            int r3 = (r1 > r24 ? 1 : (r1 == r24 ? 0 : -1))
            if (r3 != 0) goto L_0x00d9
            r10 = 1
        L_0x00d9:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r10)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r24 = r1
            goto L_0x00e2
        L_0x00e0:
            r24 = r18
        L_0x00e2:
            if (r0 == 0) goto L_0x00f1
            r0 = 8
            boolean r1 = r12.isNull(r0)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            if (r1 != 0) goto L_0x00f1
            long r0 = r12.getLong(r0)     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r8 = r0
        L_0x00f1:
            com.google.android.gms.measurement.internal.zzae r0 = new com.google.android.gms.measurement.internal.zzae     // Catch:{ SQLiteException -> 0x012c, all -> 0x0128 }
            r1 = r0
            r2 = r27
            r3 = r28
            r10 = r16
            r25 = r12
            r12 = r19
            r14 = r21
            r15 = r22
            r16 = r23
            r17 = r24
            r1.<init>(r2, r3, r4, r6, r8, r10, r12, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x0126 }
            boolean r1 = r25.moveToNext()     // Catch:{ SQLiteException -> 0x0126 }
            if (r1 == 0) goto L_0x0120
            com.google.android.gms.measurement.internal.zzef r1 = r26.zzab()     // Catch:{ SQLiteException -> 0x0126 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ SQLiteException -> 0x0126 }
            java.lang.String r2 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r27)     // Catch:{ SQLiteException -> 0x0126 }
            r1.zza(r2, r3)     // Catch:{ SQLiteException -> 0x0126 }
        L_0x0120:
            if (r25 == 0) goto L_0x0125
            r25.close()
        L_0x0125:
            return r0
        L_0x0126:
            r0 = move-exception
            goto L_0x0137
        L_0x0128:
            r0 = move-exception
            r25 = r12
            goto L_0x0159
        L_0x012c:
            r0 = move-exception
            r25 = r12
            goto L_0x0137
        L_0x0130:
            r0 = move-exception
            r25 = r18
            goto L_0x0159
        L_0x0134:
            r0 = move-exception
            r25 = r18
        L_0x0137:
            com.google.android.gms.measurement.internal.zzef r1 = r26.zzab()     // Catch:{ all -> 0x0158 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ all -> 0x0158 }
            java.lang.String r2 = "Error querying events. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r27)     // Catch:{ all -> 0x0158 }
            com.google.android.gms.measurement.internal.zzed r4 = r26.zzy()     // Catch:{ all -> 0x0158 }
            r5 = r28
            java.lang.String r4 = r4.a((java.lang.String) r5)     // Catch:{ all -> 0x0158 }
            r1.zza(r2, r3, r4, r0)     // Catch:{ all -> 0x0158 }
            if (r25 == 0) goto L_0x0157
            r25.close()
        L_0x0157:
            return r18
        L_0x0158:
            r0 = move-exception
        L_0x0159:
            if (r25 == 0) goto L_0x015e
            r25.close()
        L_0x015e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zzc(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzae");
    }

    @WorkerThread
    public final long zzcb() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    @WorkerThread
    public final long zzcc() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final boolean zzcd() {
        return zza("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzce() {
        return zza("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzcf() {
        Cursor cursor = null;
        try {
            cursor = d().rawQuery("select rowid from raw_events order by rowid desc limit 1;", (String[]) null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return -1;
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final void zzd(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        c();
        try {
            zzab().zzgs().zza("Deleted user attribute rows", Integer.valueOf(d().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error deleting user attribute. appId", zzef.a(str), zzy().c(str2), e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzjp zze(java.lang.String r19, java.lang.String r20) {
        /*
            r18 = this;
            r8 = r20
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r19)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            r18.zzo()
            r18.c()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r18.d()     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            java.lang.String r11 = "user_attributes"
            java.lang.String r0 = "set_timestamp"
            java.lang.String r1 = "value"
            java.lang.String r2 = "origin"
            java.lang.String[] r12 = new java.lang.String[]{r0, r1, r2}     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            java.lang.String r13 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r14 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            r1 = 0
            r14[r1] = r19     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            r2 = 1
            r14[r2] = r8     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            boolean r3 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x007a, all -> 0x0076 }
            if (r3 != 0) goto L_0x003f
            if (r10 == 0) goto L_0x003e
            r10.close()
        L_0x003e:
            return r9
        L_0x003f:
            long r5 = r10.getLong(r1)     // Catch:{ SQLiteException -> 0x007a, all -> 0x0076 }
            r11 = r18
            java.lang.Object r7 = r11.zza((android.database.Cursor) r10, (int) r2)     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.String r3 = r10.getString(r0)     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.measurement.internal.zzjp r0 = new com.google.android.gms.measurement.internal.zzjp     // Catch:{ SQLiteException -> 0x0074 }
            r1 = r0
            r2 = r19
            r4 = r20
            r1.<init>(r2, r3, r4, r5, r7)     // Catch:{ SQLiteException -> 0x0074 }
            boolean r1 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x006e
            com.google.android.gms.measurement.internal.zzef r1 = r18.zzab()     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r19)     // Catch:{ SQLiteException -> 0x0074 }
            r1.zza(r2, r3)     // Catch:{ SQLiteException -> 0x0074 }
        L_0x006e:
            if (r10 == 0) goto L_0x0073
            r10.close()
        L_0x0073:
            return r0
        L_0x0074:
            r0 = move-exception
            goto L_0x0087
        L_0x0076:
            r0 = move-exception
            r11 = r18
            goto L_0x00a7
        L_0x007a:
            r0 = move-exception
            r11 = r18
            goto L_0x0087
        L_0x007e:
            r0 = move-exception
            r11 = r18
            r10 = r9
            goto L_0x00a7
        L_0x0083:
            r0 = move-exception
            r11 = r18
            r10 = r9
        L_0x0087:
            com.google.android.gms.measurement.internal.zzef r1 = r18.zzab()     // Catch:{ all -> 0x00a6 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ all -> 0x00a6 }
            java.lang.String r2 = "Error querying user property. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r19)     // Catch:{ all -> 0x00a6 }
            com.google.android.gms.measurement.internal.zzed r4 = r18.zzy()     // Catch:{ all -> 0x00a6 }
            java.lang.String r4 = r4.c(r8)     // Catch:{ all -> 0x00a6 }
            r1.zza(r2, r3, r4, r0)     // Catch:{ all -> 0x00a6 }
            if (r10 == 0) goto L_0x00a5
            r10.close()
        L_0x00a5:
            return r9
        L_0x00a6:
            r0 = move-exception
        L_0x00a7:
            if (r10 == 0) goto L_0x00ac
            r10.close()
        L_0x00ac:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zze(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzjp");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0125  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzq zzf(java.lang.String r30, java.lang.String r31) {
        /*
            r29 = this;
            r7 = r31
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r30)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r31)
            r29.zzo()
            r29.c()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r29.d()     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            java.lang.String r10 = "conditional_properties"
            java.lang.String r11 = "origin"
            java.lang.String r12 = "value"
            java.lang.String r13 = "active"
            java.lang.String r14 = "trigger_event_name"
            java.lang.String r15 = "trigger_timeout"
            java.lang.String r16 = "timed_out_event"
            java.lang.String r17 = "creation_timestamp"
            java.lang.String r18 = "triggered_event"
            java.lang.String r19 = "triggered_timestamp"
            java.lang.String r20 = "time_to_live"
            java.lang.String r21 = "expired_event"
            java.lang.String[] r11 = new java.lang.String[]{r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21}     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            java.lang.String r12 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r13 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            r1 = 0
            r13[r1] = r30     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            r2 = 1
            r13[r2] = r7     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            r14 = 0
            r15 = 0
            r16 = 0
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            boolean r3 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            if (r3 != 0) goto L_0x004e
            if (r9 == 0) goto L_0x004d
            r9.close()
        L_0x004d:
            return r8
        L_0x004e:
            java.lang.String r16 = r9.getString(r1)     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            r10 = r29
            java.lang.Object r5 = r10.zza((android.database.Cursor) r9, (int) r2)     // Catch:{ SQLiteException -> 0x00f0 }
            int r0 = r9.getInt(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            if (r0 == 0) goto L_0x0061
            r20 = 1
            goto L_0x0063
        L_0x0061:
            r20 = 0
        L_0x0063:
            r0 = 3
            java.lang.String r21 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 4
            long r23 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzjo r0 = r29.zzgw()     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = 5
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzai> r2 = com.google.android.gms.measurement.internal.zzai.CREATOR     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable r0 = r0.a((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            r22 = r0
            com.google.android.gms.measurement.internal.zzai r22 = (com.google.android.gms.measurement.internal.zzai) r22     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 6
            long r18 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzjo r0 = r29.zzgw()     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = 7
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzai> r2 = com.google.android.gms.measurement.internal.zzai.CREATOR     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable r0 = r0.a((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            r25 = r0
            com.google.android.gms.measurement.internal.zzai r25 = (com.google.android.gms.measurement.internal.zzai) r25     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 8
            long r3 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 9
            long r26 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzjo r0 = r29.zzgw()     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = 10
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzai> r2 = com.google.android.gms.measurement.internal.zzai.CREATOR     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable r0 = r0.a((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            r28 = r0
            com.google.android.gms.measurement.internal.zzai r28 = (com.google.android.gms.measurement.internal.zzai) r28     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzjn r17 = new com.google.android.gms.measurement.internal.zzjn     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = r17
            r2 = r31
            r6 = r16
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzq r0 = new com.google.android.gms.measurement.internal.zzq     // Catch:{ SQLiteException -> 0x00f0 }
            r14 = r0
            r15 = r30
            r14.<init>(r15, r16, r17, r18, r20, r21, r22, r23, r25, r26, r28)     // Catch:{ SQLiteException -> 0x00f0 }
            boolean r1 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x00f0 }
            if (r1 == 0) goto L_0x00ea
            com.google.android.gms.measurement.internal.zzef r1 = r29.zzab()     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r2 = "Got multiple records for conditional property, expected one"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r30)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzed r4 = r29.zzy()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r4 = r4.c(r7)     // Catch:{ SQLiteException -> 0x00f0 }
            r1.zza(r2, r3, r4)     // Catch:{ SQLiteException -> 0x00f0 }
        L_0x00ea:
            if (r9 == 0) goto L_0x00ef
            r9.close()
        L_0x00ef:
            return r0
        L_0x00f0:
            r0 = move-exception
            goto L_0x0103
        L_0x00f2:
            r0 = move-exception
            r10 = r29
            goto L_0x0123
        L_0x00f6:
            r0 = move-exception
            r10 = r29
            goto L_0x0103
        L_0x00fa:
            r0 = move-exception
            r10 = r29
            r9 = r8
            goto L_0x0123
        L_0x00ff:
            r0 = move-exception
            r10 = r29
            r9 = r8
        L_0x0103:
            com.google.android.gms.measurement.internal.zzef r1 = r29.zzab()     // Catch:{ all -> 0x0122 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ all -> 0x0122 }
            java.lang.String r2 = "Error querying conditional property"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r30)     // Catch:{ all -> 0x0122 }
            com.google.android.gms.measurement.internal.zzed r4 = r29.zzy()     // Catch:{ all -> 0x0122 }
            java.lang.String r4 = r4.c(r7)     // Catch:{ all -> 0x0122 }
            r1.zza(r2, r3, r4, r0)     // Catch:{ all -> 0x0122 }
            if (r9 == 0) goto L_0x0121
            r9.close()
        L_0x0121:
            return r8
        L_0x0122:
            r0 = move-exception
        L_0x0123:
            if (r9 == 0) goto L_0x0128
            r9.close()
        L_0x0128:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zzf(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzq");
    }

    @WorkerThread
    public final int zzg(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        c();
        try {
            return d().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error deleting conditional property", zzef.a(str), zzy().c(str2), e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzu(long r5) {
        /*
            r4 = this;
            r4.zzo()
            r4.c()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.d()     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003e }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.measurement.internal.zzef r6 = r4.zzab()     // Catch:{ SQLiteException -> 0x003e }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgs()     // Catch:{ SQLiteException -> 0x003e }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.zzao(r1)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x0033
            r5.close()
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.String r6 = r5.getString(r6)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x003d
            r5.close()
        L_0x003d:
            return r6
        L_0x003e:
            r6 = move-exception
            goto L_0x0045
        L_0x0040:
            r6 = move-exception
            r5 = r0
            goto L_0x0059
        L_0x0043:
            r6 = move-exception
            r5 = r0
        L_0x0045:
            com.google.android.gms.measurement.internal.zzef r1 = r4.zzab()     // Catch:{ all -> 0x0058 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ all -> 0x0058 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zza(r2, r6)     // Catch:{ all -> 0x0058 }
            if (r5 == 0) goto L_0x0057
            r5.close()
        L_0x0057:
            return r0
        L_0x0058:
            r6 = move-exception
        L_0x0059:
            if (r5 == 0) goto L_0x005e
            r5.close()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zzu(long):java.lang.String");
    }
}
