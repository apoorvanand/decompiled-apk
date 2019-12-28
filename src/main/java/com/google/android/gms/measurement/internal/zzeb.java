package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzeb extends zzg {
    private final zzea zzjv = new zzea(this, getContext(), "google_app_measurement_local.db");
    private boolean zzjw;

    zzeb(zzfj zzfj) {
        super(zzfj);
    }

    @WorkerThread
    @VisibleForTesting
    private final SQLiteDatabase getWritableDatabase() {
        if (this.zzjw) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzjv.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzjw = true;
        return null;
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{ExifInterface.GPS_MEASUREMENT_3D}, (String) null, (String) null, "rowid desc", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            if (cursor.moveToFirst()) {
                return cursor.getLong(0);
            }
            if (cursor == null) {
                return -1;
            }
            cursor.close();
            return -1;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r7v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c6 A[SYNTHETIC, Splitter:B:49:0x00c6] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0117 A[SYNTHETIC] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r17, byte[] r18) {
        /*
            r16 = this;
            r1 = r16
            r16.zzm()
            r16.zzo()
            boolean r0 = r1.zzjw
            r2 = 0
            if (r0 == 0) goto L_0x000e
            return r2
        L_0x000e:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r0 = "type"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r17)
            r3.put(r0, r4)
            java.lang.String r0 = "entry"
            r4 = r18
            r3.put(r0, r4)
            r4 = 5
            r5 = 0
            r6 = 5
        L_0x0026:
            if (r5 >= r4) goto L_0x012a
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r16.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x00fe, SQLiteDatabaseLockedException -> 0x00ec, SQLiteException -> 0x00c2, all -> 0x00be }
            if (r9 != 0) goto L_0x0038
            r1.zzjw = r8     // Catch:{ SQLiteFullException -> 0x00bc, SQLiteDatabaseLockedException -> 0x00ed, SQLiteException -> 0x00b8 }
            if (r9 == 0) goto L_0x0037
            r9.close()
        L_0x0037:
            return r2
        L_0x0038:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00bc, SQLiteDatabaseLockedException -> 0x00ed, SQLiteException -> 0x00b8 }
            r10 = 0
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r12 = r9.rawQuery(r0, r7)     // Catch:{ SQLiteFullException -> 0x00bc, SQLiteDatabaseLockedException -> 0x00ed, SQLiteException -> 0x00b8 }
            if (r12 == 0) goto L_0x0059
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            if (r0 == 0) goto L_0x0059
            long r10 = r12.getLong(r2)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            goto L_0x0059
        L_0x0050:
            r0 = move-exception
            goto L_0x011f
        L_0x0053:
            r0 = move-exception
            goto L_0x00ba
        L_0x0055:
            r0 = move-exception
            r7 = r12
            goto L_0x0100
        L_0x0059:
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r0 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r0 < 0) goto L_0x00a0
            com.google.android.gms.measurement.internal.zzef r0 = r16.zzab()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            java.lang.String r15 = "Data loss, local db full"
            r0.zzao(r15)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            long r13 = r13 - r10
            r10 = 1
            long r13 = r13 + r10
            java.lang.String r0 = "messages"
            java.lang.String r10 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String[] r11 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            java.lang.String r15 = java.lang.Long.toString(r13)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            r11[r2] = r15     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            int r0 = r9.delete(r0, r10, r11)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            long r10 = (long) r0     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            int r0 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x00a0
            com.google.android.gms.measurement.internal.zzef r0 = r16.zzab()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            java.lang.String r15 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r4 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            java.lang.Long r2 = java.lang.Long.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            long r13 = r13 - r10
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            r0.zza(r15, r4, r2, r10)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
        L_0x00a0:
            java.lang.String r0 = "messages"
            r9.insertOrThrow(r0, r7, r3)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            if (r12 == 0) goto L_0x00b0
            r12.close()
        L_0x00b0:
            if (r9 == 0) goto L_0x00b5
            r9.close()
        L_0x00b5:
            return r8
        L_0x00b6:
            r7 = r12
            goto L_0x00ed
        L_0x00b8:
            r0 = move-exception
            r12 = r7
        L_0x00ba:
            r7 = r9
            goto L_0x00c4
        L_0x00bc:
            r0 = move-exception
            goto L_0x0100
        L_0x00be:
            r0 = move-exception
            r9 = r7
            r12 = r9
            goto L_0x011f
        L_0x00c2:
            r0 = move-exception
            r12 = r7
        L_0x00c4:
            if (r7 == 0) goto L_0x00cf
            boolean r2 = r7.inTransaction()     // Catch:{ all -> 0x00e9 }
            if (r2 == 0) goto L_0x00cf
            r7.endTransaction()     // Catch:{ all -> 0x00e9 }
        L_0x00cf:
            com.google.android.gms.measurement.internal.zzef r2 = r16.zzab()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()     // Catch:{ all -> 0x00e9 }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zza(r4, r0)     // Catch:{ all -> 0x00e9 }
            r1.zzjw = r8     // Catch:{ all -> 0x00e9 }
            if (r12 == 0) goto L_0x00e3
            r12.close()
        L_0x00e3:
            if (r7 == 0) goto L_0x0117
            r7.close()
            goto L_0x0117
        L_0x00e9:
            r0 = move-exception
            r9 = r7
            goto L_0x011f
        L_0x00ec:
            r9 = r7
        L_0x00ed:
            long r10 = (long) r6
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x011d }
            int r6 = r6 + 20
            if (r7 == 0) goto L_0x00f8
            r7.close()
        L_0x00f8:
            if (r9 == 0) goto L_0x0117
        L_0x00fa:
            r9.close()
            goto L_0x0117
        L_0x00fe:
            r0 = move-exception
            r9 = r7
        L_0x0100:
            com.google.android.gms.measurement.internal.zzef r2 = r16.zzab()     // Catch:{ all -> 0x011d }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()     // Catch:{ all -> 0x011d }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zza(r4, r0)     // Catch:{ all -> 0x011d }
            r1.zzjw = r8     // Catch:{ all -> 0x011d }
            if (r7 == 0) goto L_0x0114
            r7.close()
        L_0x0114:
            if (r9 == 0) goto L_0x0117
            goto L_0x00fa
        L_0x0117:
            int r5 = r5 + 1
            r2 = 0
            r4 = 5
            goto L_0x0026
        L_0x011d:
            r0 = move-exception
            r12 = r7
        L_0x011f:
            if (r12 == 0) goto L_0x0124
            r12.close()
        L_0x0124:
            if (r9 == 0) goto L_0x0129
            r9.close()
        L_0x0129:
            throw r0
        L_0x012a:
            com.google.android.gms.measurement.internal.zzef r0 = r16.zzab()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgn()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.zzao(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzeb.zza(int, byte[]):boolean");
    }

    @VisibleForTesting
    private final boolean zzcg() {
        return getContext().getDatabasePath("google_app_measurement_local.db").exists();
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzm();
        zzo();
        try {
            int delete = getWritableDatabase().delete("messages", (String) null, (String[]) null) + 0;
            if (delete > 0) {
                zzab().zzgs().zza("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zza(zzai zzai) {
        Parcel obtain = Parcel.obtain();
        zzai.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzab().zzgn().zzao("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzjn zzjn) {
        Parcel obtain = Parcel.obtain();
        zzjn.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzab().zzgn().zzao("User property too long for local database. Sending directly to service");
        return false;
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v12, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v15, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v17, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v41, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v42, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v43, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v44, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v45, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v46, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v48, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v51, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v54, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v55, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v56, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v57, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r21v14 */
    /* JADX WARNING: type inference failed for: r21v31 */
    /* JADX WARNING: type inference failed for: r21v49 */
    /* JADX WARNING: type inference failed for: r21v52 */
    /* JADX WARNING: type inference failed for: r21v53 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:76|77|78|79) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:91|92|93|94) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:63|64|65|66|196) */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x01e3, code lost:
        r12 = r15;
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        r9 = null;
        r12 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        r9 = null;
        r12 = r15;
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        r9 = null;
        r12 = r15;
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0085, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0086, code lost:
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008b, code lost:
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0090, code lost:
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        zzab().zzgk().zzao("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        r10.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        zzab().zzgk().zzao("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r10.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
        zzab().zzgk().zzao("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        r10.recycle();
        r0 = null;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x00e6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:76:0x0116 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0149 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:141:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x01f3 A[SYNTHETIC, Splitter:B:151:0x01f3] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x020d  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x021d  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x023a  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x024c  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x0240 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0240 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x0240 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zzc(int r21) {
        /*
            r20 = this;
            r1 = r20
            r20.zzo()
            r20.zzm()
            boolean r0 = r1.zzjw
            r2 = 0
            if (r0 == 0) goto L_0x000e
            return r2
        L_0x000e:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            boolean r0 = r20.zzcg()
            if (r0 != 0) goto L_0x001a
            return r3
        L_0x001a:
            r4 = 5
            r5 = 0
            r6 = 0
            r7 = 5
        L_0x001e:
            if (r6 >= r4) goto L_0x0250
            r8 = 1
            android.database.sqlite.SQLiteDatabase r15 = r20.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x0226, SQLiteDatabaseLockedException -> 0x0213, SQLiteException -> 0x01ee, all -> 0x01ea }
            if (r15 != 0) goto L_0x003e
            r1.zzjw = r8     // Catch:{ SQLiteFullException -> 0x0039, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x0034, all -> 0x002f }
            if (r15 == 0) goto L_0x002e
            r15.close()
        L_0x002e:
            return r2
        L_0x002f:
            r0 = move-exception
            r9 = r2
            r12 = r15
            goto L_0x0245
        L_0x0034:
            r0 = move-exception
            r9 = r2
            r12 = r15
            goto L_0x01f1
        L_0x0039:
            r0 = move-exception
            r9 = r2
            r12 = r15
            goto L_0x0229
        L_0x003e:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x01e6, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x01df, all -> 0x01da }
            com.google.android.gms.measurement.internal.zzs r0 = r20.zzad()     // Catch:{ SQLiteFullException -> 0x01e6, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x01df, all -> 0x01da }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzak.zzjd     // Catch:{ SQLiteFullException -> 0x01e6, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x01df, all -> 0x01da }
            boolean r0 = r0.zza(r9)     // Catch:{ SQLiteFullException -> 0x01e6, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x01df, all -> 0x01da }
            r9 = 100
            r18 = -1
            if (r0 == 0) goto L_0x0094
            long r10 = zza((android.database.sqlite.SQLiteDatabase) r15)     // Catch:{ SQLiteFullException -> 0x008f, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x008a, all -> 0x0085 }
            int r0 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x0066
            java.lang.String r0 = "rowid<?"
            java.lang.String[] r12 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0039, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x0034, all -> 0x002f }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0039, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x0034, all -> 0x002f }
            r12[r5] = r10     // Catch:{ SQLiteFullException -> 0x0039, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x0034, all -> 0x002f }
            r13 = r12
            r12 = r0
            goto L_0x0068
        L_0x0066:
            r12 = r2
            r13 = r12
        L_0x0068:
            java.lang.String r10 = "messages"
            java.lang.String r0 = "rowid"
            java.lang.String r11 = "type"
            java.lang.String r14 = "entry"
            java.lang.String[] r11 = new java.lang.String[]{r0, r11, r14}     // Catch:{ SQLiteFullException -> 0x008f, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x008a, all -> 0x0085 }
            r14 = 0
            r0 = 0
            java.lang.String r16 = "rowid asc"
            java.lang.String r17 = java.lang.Integer.toString(r9)     // Catch:{ SQLiteFullException -> 0x008f, SQLiteDatabaseLockedException -> 0x01e3, SQLiteException -> 0x008a, all -> 0x0085 }
            r9 = r15
            r21 = r15
            r15 = r0
            android.database.Cursor r0 = r9.query(r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteFullException -> 0x01d6, SQLiteDatabaseLockedException -> 0x01d3, SQLiteException -> 0x01cf, all -> 0x01cb }
            goto L_0x00b2
        L_0x0085:
            r0 = move-exception
            r21 = r15
            goto L_0x01cc
        L_0x008a:
            r0 = move-exception
            r21 = r15
            goto L_0x01d0
        L_0x008f:
            r0 = move-exception
            r21 = r15
            goto L_0x01d7
        L_0x0094:
            r21 = r15
            java.lang.String r10 = "messages"
            java.lang.String r0 = "rowid"
            java.lang.String r11 = "type"
            java.lang.String r12 = "entry"
            java.lang.String[] r11 = new java.lang.String[]{r0, r11, r12}     // Catch:{ SQLiteFullException -> 0x01d6, SQLiteDatabaseLockedException -> 0x01d3, SQLiteException -> 0x01cf, all -> 0x01cb }
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            java.lang.String r16 = "rowid asc"
            java.lang.String r17 = java.lang.Integer.toString(r9)     // Catch:{ SQLiteFullException -> 0x01d6, SQLiteDatabaseLockedException -> 0x01d3, SQLiteException -> 0x01cf, all -> 0x01cb }
            r9 = r21
            android.database.Cursor r0 = r9.query(r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteFullException -> 0x01d6, SQLiteDatabaseLockedException -> 0x01d3, SQLiteException -> 0x01cf, all -> 0x01cb }
        L_0x00b2:
            r9 = r0
        L_0x00b3:
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            if (r0 == 0) goto L_0x017e
            long r18 = r9.getLong(r5)     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            int r0 = r9.getInt(r8)     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            r10 = 2
            byte[] r11 = r9.getBlob(r10)     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            if (r0 != 0) goto L_0x00fb
            android.os.Parcel r10 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            int r0 = r11.length     // Catch:{ ParseException -> 0x00e6 }
            r10.unmarshall(r11, r5, r0)     // Catch:{ ParseException -> 0x00e6 }
            r10.setDataPosition(r5)     // Catch:{ ParseException -> 0x00e6 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzai> r0 = com.google.android.gms.measurement.internal.zzai.CREATOR     // Catch:{ ParseException -> 0x00e6 }
            java.lang.Object r0 = r0.createFromParcel(r10)     // Catch:{ ParseException -> 0x00e6 }
            com.google.android.gms.measurement.internal.zzai r0 = (com.google.android.gms.measurement.internal.zzai) r0     // Catch:{ ParseException -> 0x00e6 }
            r10.recycle()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            if (r0 == 0) goto L_0x00b3
        L_0x00e0:
            r3.add(r0)     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            goto L_0x00b3
        L_0x00e4:
            r0 = move-exception
            goto L_0x00f7
        L_0x00e6:
            com.google.android.gms.measurement.internal.zzef r0 = r20.zzab()     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ all -> 0x00e4 }
            java.lang.String r11 = "Failed to load event from local database"
            r0.zzao(r11)     // Catch:{ all -> 0x00e4 }
            r10.recycle()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            goto L_0x00b3
        L_0x00f7:
            r10.recycle()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            throw r0     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
        L_0x00fb:
            if (r0 != r8) goto L_0x012e
            android.os.Parcel r10 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            int r0 = r11.length     // Catch:{ ParseException -> 0x0116 }
            r10.unmarshall(r11, r5, r0)     // Catch:{ ParseException -> 0x0116 }
            r10.setDataPosition(r5)     // Catch:{ ParseException -> 0x0116 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzjn> r0 = com.google.android.gms.measurement.internal.zzjn.CREATOR     // Catch:{ ParseException -> 0x0116 }
            java.lang.Object r0 = r0.createFromParcel(r10)     // Catch:{ ParseException -> 0x0116 }
            com.google.android.gms.measurement.internal.zzjn r0 = (com.google.android.gms.measurement.internal.zzjn) r0     // Catch:{ ParseException -> 0x0116 }
            r10.recycle()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            goto L_0x0127
        L_0x0114:
            r0 = move-exception
            goto L_0x012a
        L_0x0116:
            com.google.android.gms.measurement.internal.zzef r0 = r20.zzab()     // Catch:{ all -> 0x0114 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ all -> 0x0114 }
            java.lang.String r11 = "Failed to load user property from local database"
            r0.zzao(r11)     // Catch:{ all -> 0x0114 }
            r10.recycle()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            r0 = r2
        L_0x0127:
            if (r0 == 0) goto L_0x00b3
            goto L_0x00e0
        L_0x012a:
            r10.recycle()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            throw r0     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
        L_0x012e:
            if (r0 != r10) goto L_0x0161
            android.os.Parcel r10 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            int r0 = r11.length     // Catch:{ ParseException -> 0x0149 }
            r10.unmarshall(r11, r5, r0)     // Catch:{ ParseException -> 0x0149 }
            r10.setDataPosition(r5)     // Catch:{ ParseException -> 0x0149 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzq> r0 = com.google.android.gms.measurement.internal.zzq.CREATOR     // Catch:{ ParseException -> 0x0149 }
            java.lang.Object r0 = r0.createFromParcel(r10)     // Catch:{ ParseException -> 0x0149 }
            com.google.android.gms.measurement.internal.zzq r0 = (com.google.android.gms.measurement.internal.zzq) r0     // Catch:{ ParseException -> 0x0149 }
            r10.recycle()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            goto L_0x015a
        L_0x0147:
            r0 = move-exception
            goto L_0x015d
        L_0x0149:
            com.google.android.gms.measurement.internal.zzef r0 = r20.zzab()     // Catch:{ all -> 0x0147 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ all -> 0x0147 }
            java.lang.String r11 = "Failed to load user property from local database"
            r0.zzao(r11)     // Catch:{ all -> 0x0147 }
            r10.recycle()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            r0 = r2
        L_0x015a:
            if (r0 == 0) goto L_0x00b3
            goto L_0x00e0
        L_0x015d:
            r10.recycle()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            throw r0     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
        L_0x0161:
            r10 = 3
            if (r0 != r10) goto L_0x0173
            com.google.android.gms.measurement.internal.zzef r0 = r20.zzab()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgn()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            java.lang.String r10 = "Skipping app launch break"
        L_0x016e:
            r0.zzao(r10)     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            goto L_0x00b3
        L_0x0173:
            com.google.android.gms.measurement.internal.zzef r0 = r20.zzab()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            java.lang.String r10 = "Unknown record type in local database"
            goto L_0x016e
        L_0x017e:
            java.lang.String r0 = "messages"
            java.lang.String r10 = "rowid <= ?"
            java.lang.String[] r11 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            java.lang.String r12 = java.lang.Long.toString(r18)     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            r11[r5] = r12     // Catch:{ SQLiteFullException -> 0x01c6, SQLiteDatabaseLockedException -> 0x01c2, SQLiteException -> 0x01be, all -> 0x01b9 }
            r12 = r21
            int r0 = r12.delete(r0, r10, r11)     // Catch:{ SQLiteFullException -> 0x01b6, SQLiteDatabaseLockedException -> 0x0215, SQLiteException -> 0x01b4 }
            int r10 = r3.size()     // Catch:{ SQLiteFullException -> 0x01b6, SQLiteDatabaseLockedException -> 0x0215, SQLiteException -> 0x01b4 }
            if (r0 >= r10) goto L_0x01a3
            com.google.android.gms.measurement.internal.zzef r0 = r20.zzab()     // Catch:{ SQLiteFullException -> 0x01b6, SQLiteDatabaseLockedException -> 0x0215, SQLiteException -> 0x01b4 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ SQLiteFullException -> 0x01b6, SQLiteDatabaseLockedException -> 0x0215, SQLiteException -> 0x01b4 }
            java.lang.String r10 = "Fewer entries removed from local database than expected"
            r0.zzao(r10)     // Catch:{ SQLiteFullException -> 0x01b6, SQLiteDatabaseLockedException -> 0x0215, SQLiteException -> 0x01b4 }
        L_0x01a3:
            r12.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x01b6, SQLiteDatabaseLockedException -> 0x0215, SQLiteException -> 0x01b4 }
            r12.endTransaction()     // Catch:{ SQLiteFullException -> 0x01b6, SQLiteDatabaseLockedException -> 0x0215, SQLiteException -> 0x01b4 }
            if (r9 == 0) goto L_0x01ae
            r9.close()
        L_0x01ae:
            if (r12 == 0) goto L_0x01b3
            r12.close()
        L_0x01b3:
            return r3
        L_0x01b4:
            r0 = move-exception
            goto L_0x01f1
        L_0x01b6:
            r0 = move-exception
            goto L_0x0229
        L_0x01b9:
            r0 = move-exception
            r12 = r21
            goto L_0x0245
        L_0x01be:
            r0 = move-exception
            r12 = r21
            goto L_0x01f1
        L_0x01c2:
            r12 = r21
            goto L_0x0215
        L_0x01c6:
            r0 = move-exception
            r12 = r21
            goto L_0x0229
        L_0x01cb:
            r0 = move-exception
        L_0x01cc:
            r12 = r21
            goto L_0x01dc
        L_0x01cf:
            r0 = move-exception
        L_0x01d0:
            r12 = r21
            goto L_0x01e1
        L_0x01d3:
            r12 = r21
            goto L_0x01e4
        L_0x01d6:
            r0 = move-exception
        L_0x01d7:
            r12 = r21
            goto L_0x01e8
        L_0x01da:
            r0 = move-exception
            r12 = r15
        L_0x01dc:
            r9 = r2
            goto L_0x0245
        L_0x01df:
            r0 = move-exception
            r12 = r15
        L_0x01e1:
            r9 = r2
            goto L_0x01f1
        L_0x01e3:
            r12 = r15
        L_0x01e4:
            r9 = r2
            goto L_0x0215
        L_0x01e6:
            r0 = move-exception
            r12 = r15
        L_0x01e8:
            r9 = r2
            goto L_0x0229
        L_0x01ea:
            r0 = move-exception
            r9 = r2
            r12 = r9
            goto L_0x0245
        L_0x01ee:
            r0 = move-exception
            r9 = r2
            r12 = r9
        L_0x01f1:
            if (r12 == 0) goto L_0x01fc
            boolean r10 = r12.inTransaction()     // Catch:{ all -> 0x0244 }
            if (r10 == 0) goto L_0x01fc
            r12.endTransaction()     // Catch:{ all -> 0x0244 }
        L_0x01fc:
            com.google.android.gms.measurement.internal.zzef r10 = r20.zzab()     // Catch:{ all -> 0x0244 }
            com.google.android.gms.measurement.internal.zzeh r10 = r10.zzgk()     // Catch:{ all -> 0x0244 }
            java.lang.String r11 = "Error reading entries from local database"
            r10.zza(r11, r0)     // Catch:{ all -> 0x0244 }
            r1.zzjw = r8     // Catch:{ all -> 0x0244 }
            if (r9 == 0) goto L_0x0210
            r9.close()
        L_0x0210:
            if (r12 == 0) goto L_0x0240
            goto L_0x0222
        L_0x0213:
            r9 = r2
            r12 = r9
        L_0x0215:
            long r10 = (long) r7
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x0244 }
            int r7 = r7 + 20
            if (r9 == 0) goto L_0x0220
            r9.close()
        L_0x0220:
            if (r12 == 0) goto L_0x0240
        L_0x0222:
            r12.close()
            goto L_0x0240
        L_0x0226:
            r0 = move-exception
            r9 = r2
            r12 = r9
        L_0x0229:
            com.google.android.gms.measurement.internal.zzef r10 = r20.zzab()     // Catch:{ all -> 0x0244 }
            com.google.android.gms.measurement.internal.zzeh r10 = r10.zzgk()     // Catch:{ all -> 0x0244 }
            java.lang.String r11 = "Error reading entries from local database"
            r10.zza(r11, r0)     // Catch:{ all -> 0x0244 }
            r1.zzjw = r8     // Catch:{ all -> 0x0244 }
            if (r9 == 0) goto L_0x023d
            r9.close()
        L_0x023d:
            if (r12 == 0) goto L_0x0240
            goto L_0x0222
        L_0x0240:
            int r6 = r6 + 1
            goto L_0x001e
        L_0x0244:
            r0 = move-exception
        L_0x0245:
            if (r9 == 0) goto L_0x024a
            r9.close()
        L_0x024a:
            if (r12 == 0) goto L_0x024f
            r12.close()
        L_0x024f:
            throw r0
        L_0x0250:
            com.google.android.gms.measurement.internal.zzef r0 = r20.zzab()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgn()
            java.lang.String r3 = "Failed to read events from database in reasonable time"
            r0.zzao(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzeb.zzc(int):java.util.List");
    }

    public final boolean zzc(zzq zzq) {
        zzz();
        byte[] a = zzjs.a((Parcelable) zzq);
        if (a.length <= 131072) {
            return zza(2, a);
        }
        zzab().zzgn().zzao("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    @WorkerThread
    public final boolean zzgh() {
        return zza(3, new byte[0]);
    }

    @WorkerThread
    public final boolean zzgi() {
        zzo();
        zzm();
        if (this.zzjw || !zzcg()) {
            return false;
        }
        int i = 0;
        int i2 = 5;
        while (i < 5) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = getWritableDatabase();
                if (sQLiteDatabase == null) {
                    this.zzjw = true;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return true;
            } catch (SQLiteFullException e) {
                zzab().zzgk().zza("Error deleting app launch break from local database", e);
                this.zzjw = true;
                if (sQLiteDatabase == null) {
                    i++;
                }
                sQLiteDatabase.close();
                i++;
            } catch (SQLiteDatabaseLockedException unused) {
                SystemClock.sleep((long) i2);
                i2 += 20;
                if (sQLiteDatabase == null) {
                    i++;
                }
                sQLiteDatabase.close();
                i++;
            } catch (SQLiteException e2) {
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                }
                zzab().zzgk().zza("Error deleting app launch break from local database", e2);
                this.zzjw = true;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                    i++;
                } else {
                    i++;
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        }
        zzab().zzgn().zzao("Error deleting app launch break from local database in reasonable time");
        return false;
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

    public final /* bridge */ /* synthetic */ zza zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzgp zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzdy zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzhv zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzhq zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzeb zzu() {
        return super.zzu();
    }

    public final /* bridge */ /* synthetic */ zziw zzv() {
        return super.zzv();
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
