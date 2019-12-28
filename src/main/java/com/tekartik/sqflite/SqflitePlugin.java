package com.tekartik.sqflite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import com.tekartik.sqflite.dev.Debug;
import com.tekartik.sqflite.operation.ExecuteOperation;
import com.tekartik.sqflite.operation.MethodCallOperation;
import com.tekartik.sqflite.operation.Operation;
import com.tekartik.sqflite.operation.SqlErrorInfo;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqflitePlugin implements MethodChannel.MethodCallHandler {
    private static boolean QUERY_AS_MAP_LIST = false;
    private static int THREAD_PRIORITY = 10;
    static final Map<String, Integer> a = new HashMap();
    static int b = 0;
    static String c;
    private static Context context;
    @SuppressLint({"UseSparseArrays"})
    static final Map<Integer, Database> d = new HashMap();
    private static int databaseId = 0;
    /* access modifiers changed from: private */
    public static final Object databaseMapLocker = new Object();
    /* access modifiers changed from: private */
    public static Handler handler;
    /* access modifiers changed from: private */
    public static HandlerThread handlerThread;
    /* access modifiers changed from: private */
    public static final Object openCloseLocker = new Object();

    private class BgResult implements MethodChannel.Result {
        final Handler a;
        /* access modifiers changed from: private */
        public final MethodChannel.Result result;

        private BgResult(MethodChannel.Result result2) {
            this.a = new Handler();
            this.result = result2;
        }

        public void error(final String str, final String str2, final Object obj) {
            this.a.post(new Runnable() {
                public void run() {
                    BgResult.this.result.error(str, str2, obj);
                }
            });
        }

        public void notImplemented() {
            this.a.post(new Runnable() {
                public void run() {
                    BgResult.this.result.notImplemented();
                }
            });
        }

        public void success(final Object obj) {
            this.a.post(new Runnable() {
                public void run() {
                    BgResult.this.result.success(obj);
                }
            });
        }
    }

    SqflitePlugin(Context context2) {
        context = context2;
    }

    static Map a(int i, boolean z, boolean z2) {
        HashMap hashMap = new HashMap();
        hashMap.put(ShareConstants.WEB_DIALOG_PARAM_ID, Integer.valueOf(i));
        if (z) {
            hashMap.put(Constant.PARAM_RECOVERED, true);
        }
        if (z2) {
            hashMap.put(Constant.PARAM_RECOVERED_IN_TRANSACTION, true);
        }
        return hashMap;
    }

    static boolean a(String str) {
        return str == null || str.equals(":memory:");
    }

    private static List<Object> cursorRowToList(Cursor cursor, int i) {
        String str;
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            Object cursorValue = cursorValue(cursor, i2);
            if (Debug.EXTRA_LOGV) {
                String str2 = null;
                if (cursorValue != null) {
                    if (cursorValue.getClass().isArray()) {
                        str2 = "array(" + cursorValue.getClass().getComponentType().getName() + ")";
                    } else {
                        str2 = cursorValue.getClass().getName();
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append("column ");
                sb.append(i2);
                sb.append(" ");
                sb.append(cursor.getType(i2));
                sb.append(": ");
                sb.append(cursorValue);
                if (str2 == null) {
                    str = "";
                } else {
                    str = " (" + str2 + ")";
                }
                sb.append(str);
                Log.d(Constant.TAG, sb.toString());
            }
            arrayList.add(cursorValue);
        }
        return arrayList;
    }

    private static Map<String, Object> cursorRowToMap(Cursor cursor) {
        Object obj;
        String str;
        HashMap hashMap = new HashMap();
        String[] columnNames = cursor.getColumnNames();
        int length = columnNames.length;
        for (int i = 0; i < length; i++) {
            if (Debug.EXTRA_LOGV) {
                Log.d(Constant.TAG, "column " + i + " " + cursor.getType(i));
            }
            switch (cursor.getType(i)) {
                case 0:
                    str = columnNames[i];
                    obj = null;
                    break;
                case 1:
                    str = columnNames[i];
                    obj = Long.valueOf(cursor.getLong(i));
                    break;
                case 2:
                    str = columnNames[i];
                    obj = Double.valueOf(cursor.getDouble(i));
                    break;
                case 3:
                    str = columnNames[i];
                    obj = cursor.getString(i);
                    break;
                case 4:
                    str = columnNames[i];
                    obj = cursor.getBlob(i);
                    break;
            }
            hashMap.put(str, obj);
        }
        return hashMap;
    }

    private static Object cursorValue(Cursor cursor, int i) {
        switch (cursor.getType(i)) {
            case 0:
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                return cursor.getBlob(i);
            default:
                return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean execute(Database database, Operation operation) {
        if (!executeOrError(database, operation)) {
            return false;
        }
        operation.success((Object) null);
        return true;
    }

    /* access modifiers changed from: private */
    public Database executeOrError(Database database, MethodCall methodCall, MethodChannel.Result result) {
        if (executeOrError(database, new ExecuteOperation(result, getSqlCommand(methodCall), (Boolean) methodCall.argument(Constant.PARAM_IN_TRANSACTION)))) {
            return database;
        }
        return null;
    }

    private boolean executeOrError(Database database, Operation operation) {
        SqlCommand sqlCommand = operation.getSqlCommand();
        if (LogLevel.a(database.d)) {
            Log.d(Constant.TAG, database.b() + sqlCommand);
        }
        Boolean inTransaction = operation.getInTransaction();
        try {
            database.getWritableDatabase().execSQL(sqlCommand.getSql(), sqlCommand.getSqlArguments());
            if (Boolean.TRUE.equals(inTransaction)) {
                database.f = true;
            }
            if (Boolean.FALSE.equals(inTransaction)) {
                database.f = false;
            }
            return true;
        } catch (Exception e) {
            handleException(e, operation, database);
            if (Boolean.FALSE.equals(inTransaction)) {
                database.f = false;
            }
            return false;
        } catch (Throwable th) {
            if (Boolean.FALSE.equals(inTransaction)) {
                database.f = false;
            }
            throw th;
        }
    }

    private static Map<String, Object> fixMap(Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            Object value = next.getValue();
            hashMap.put(toString(next.getKey()), value instanceof Map ? fixMap((Map) value) : toString(value));
        }
        return hashMap;
    }

    private Context getContext() {
        return context;
    }

    private Database getDatabase(int i) {
        return d.get(Integer.valueOf(i));
    }

    private Database getDatabaseOrError(MethodCall methodCall, MethodChannel.Result result) {
        int intValue = ((Integer) methodCall.argument(ShareConstants.WEB_DIALOG_PARAM_ID)).intValue();
        Database database = getDatabase(intValue);
        if (database != null) {
            return database;
        }
        result.error("sqlite_error", "database_closed " + intValue, (Object) null);
        return null;
    }

    private SqlCommand getSqlCommand(MethodCall methodCall) {
        return new SqlCommand((String) methodCall.argument(Constant.PARAM_SQL), (List) methodCall.argument(Constant.PARAM_SQL_ARGUMENTS));
    }

    /* access modifiers changed from: private */
    public void handleException(Exception exc, Operation operation, Database database) {
        if (exc instanceof SQLiteCantOpenDatabaseException) {
            operation.error("sqlite_error", "open_failed " + database.b, (Object) null);
        } else if (exc instanceof SQLException) {
            operation.error("sqlite_error", exc.getMessage(), SqlErrorInfo.getMap(operation));
        } else {
            operation.error("sqlite_error", exc.getMessage(), SqlErrorInfo.getMap(operation));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean insert(com.tekartik.sqflite.Database r10, com.tekartik.sqflite.operation.Operation r11) {
        /*
            r9 = this;
            boolean r0 = r9.executeOrError(r10, r11)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            boolean r0 = r11.getNoResult()
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0014
            r11.success(r2)
            return r3
        L_0x0014:
            java.lang.String r0 = "SELECT changes(), last_insert_rowid()"
            android.database.sqlite.SQLiteDatabase r4 = r10.getWritableDatabase()     // Catch:{ Exception -> 0x00c7, all -> 0x00c4 }
            android.database.Cursor r0 = r4.rawQuery(r0, r2)     // Catch:{ Exception -> 0x00c7, all -> 0x00c4 }
            if (r0 == 0) goto L_0x009f
            int r4 = r0.getCount()     // Catch:{ Exception -> 0x00c2 }
            if (r4 <= 0) goto L_0x009f
            boolean r4 = r0.moveToFirst()     // Catch:{ Exception -> 0x00c2 }
            if (r4 == 0) goto L_0x009f
            int r4 = r0.getInt(r1)     // Catch:{ Exception -> 0x00c2 }
            if (r4 != 0) goto L_0x0069
            int r4 = r10.d     // Catch:{ Exception -> 0x00c2 }
            boolean r4 = com.tekartik.sqflite.LogLevel.a((int) r4)     // Catch:{ Exception -> 0x00c2 }
            if (r4 == 0) goto L_0x0060
            java.lang.String r4 = "Sqflite"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c2 }
            r5.<init>()     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r6 = r10.b()     // Catch:{ Exception -> 0x00c2 }
            r5.append(r6)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r6 = "no changes (id was "
            r5.append(r6)     // Catch:{ Exception -> 0x00c2 }
            long r6 = r0.getLong(r3)     // Catch:{ Exception -> 0x00c2 }
            r5.append(r6)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r6 = ")"
            r5.append(r6)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00c2 }
            android.util.Log.d(r4, r5)     // Catch:{ Exception -> 0x00c2 }
        L_0x0060:
            r11.success(r2)     // Catch:{ Exception -> 0x00c2 }
            if (r0 == 0) goto L_0x0068
            r0.close()
        L_0x0068:
            return r3
        L_0x0069:
            long r4 = r0.getLong(r3)     // Catch:{ Exception -> 0x00c2 }
            int r2 = r10.d     // Catch:{ Exception -> 0x00c2 }
            boolean r2 = com.tekartik.sqflite.LogLevel.a((int) r2)     // Catch:{ Exception -> 0x00c2 }
            if (r2 == 0) goto L_0x0092
            java.lang.String r2 = "Sqflite"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c2 }
            r6.<init>()     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r7 = r10.b()     // Catch:{ Exception -> 0x00c2 }
            r6.append(r7)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r7 = "inserted "
            r6.append(r7)     // Catch:{ Exception -> 0x00c2 }
            r6.append(r4)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x00c2 }
            android.util.Log.d(r2, r6)     // Catch:{ Exception -> 0x00c2 }
        L_0x0092:
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x00c2 }
            r11.success(r2)     // Catch:{ Exception -> 0x00c2 }
            if (r0 == 0) goto L_0x009e
            r0.close()
        L_0x009e:
            return r3
        L_0x009f:
            java.lang.String r4 = "Sqflite"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c2 }
            r5.<init>()     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r6 = r10.b()     // Catch:{ Exception -> 0x00c2 }
            r5.append(r6)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r6 = "fail to read changes for Insert"
            r5.append(r6)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00c2 }
            android.util.Log.e(r4, r5)     // Catch:{ Exception -> 0x00c2 }
            r11.success(r2)     // Catch:{ Exception -> 0x00c2 }
            if (r0 == 0) goto L_0x00c1
            r0.close()
        L_0x00c1:
            return r3
        L_0x00c2:
            r2 = move-exception
            goto L_0x00cb
        L_0x00c4:
            r10 = move-exception
            r0 = r2
            goto L_0x00d5
        L_0x00c7:
            r0 = move-exception
            r8 = r2
            r2 = r0
            r0 = r8
        L_0x00cb:
            r9.handleException(r2, r11, r10)     // Catch:{ all -> 0x00d4 }
            if (r0 == 0) goto L_0x00d3
            r0.close()
        L_0x00d3:
            return r1
        L_0x00d4:
            r10 = move-exception
        L_0x00d5:
            if (r0 == 0) goto L_0x00da
            r0.close()
        L_0x00da:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tekartik.sqflite.SqflitePlugin.insert(com.tekartik.sqflite.Database, com.tekartik.sqflite.operation.Operation):boolean");
    }

    private void onBatchCall(final MethodCall methodCall, MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError != null) {
            final BgResult bgResult = new BgResult(result);
            handler.post(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e2, code lost:
                    r6.handleSuccess(r3);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e9, code lost:
                    r6.handleErrorContinue(r3);
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r10 = this;
                        com.tekartik.sqflite.operation.MethodCallOperation r0 = new com.tekartik.sqflite.operation.MethodCallOperation
                        io.flutter.plugin.common.MethodCall r1 = r4
                        com.tekartik.sqflite.SqflitePlugin$BgResult r2 = r1
                        r0.<init>(r1, r2)
                        boolean r1 = r0.getNoResult()
                        boolean r0 = r0.getContinueOnError()
                        io.flutter.plugin.common.MethodCall r2 = r4
                        java.lang.String r3 = "operations"
                        java.lang.Object r2 = r2.argument(r3)
                        java.util.List r2 = (java.util.List) r2
                        java.util.ArrayList r3 = new java.util.ArrayList
                        r3.<init>()
                        java.util.Iterator r2 = r2.iterator()
                    L_0x0024:
                        boolean r4 = r2.hasNext()
                        r5 = 0
                        if (r4 == 0) goto L_0x00f4
                        java.lang.Object r4 = r2.next()
                        java.util.Map r4 = (java.util.Map) r4
                        com.tekartik.sqflite.operation.BatchOperation r6 = new com.tekartik.sqflite.operation.BatchOperation
                        r6.<init>(r4, r1)
                        java.lang.String r4 = r6.getMethod()
                        r7 = -1
                        int r8 = r4.hashCode()
                        r9 = -1319569547(0xffffffffb158f775, float:-3.15728E-9)
                        if (r8 == r9) goto L_0x0072
                        r9 = -1183792455(0xffffffffb970c2b9, float:-2.2960723E-4)
                        if (r8 == r9) goto L_0x0068
                        r9 = -838846263(0xffffffffce0038c9, float:-5.3780128E8)
                        if (r8 == r9) goto L_0x005e
                        r9 = 107944136(0x66f18c8, float:4.496911E-35)
                        if (r8 == r9) goto L_0x0054
                        goto L_0x007b
                    L_0x0054:
                        java.lang.String r8 = "query"
                        boolean r8 = r4.equals(r8)
                        if (r8 == 0) goto L_0x007b
                        r7 = 2
                        goto L_0x007b
                    L_0x005e:
                        java.lang.String r8 = "update"
                        boolean r8 = r4.equals(r8)
                        if (r8 == 0) goto L_0x007b
                        r7 = 3
                        goto L_0x007b
                    L_0x0068:
                        java.lang.String r8 = "insert"
                        boolean r8 = r4.equals(r8)
                        if (r8 == 0) goto L_0x007b
                        r7 = 1
                        goto L_0x007b
                    L_0x0072:
                        java.lang.String r8 = "execute"
                        boolean r8 = r4.equals(r8)
                        if (r8 == 0) goto L_0x007b
                        r7 = 0
                    L_0x007b:
                        switch(r7) {
                            case 0: goto L_0x00d8;
                            case 1: goto L_0x00c4;
                            case 2: goto L_0x00b0;
                            case 3: goto L_0x009c;
                            default: goto L_0x007e;
                        }
                    L_0x007e:
                        com.tekartik.sqflite.SqflitePlugin$BgResult r0 = r1
                        java.lang.String r1 = "bad_param"
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                        r2.<init>()
                        java.lang.String r3 = "Batch method '"
                        r2.append(r3)
                        r2.append(r4)
                        java.lang.String r3 = "' not supported"
                        r2.append(r3)
                        java.lang.String r2 = r2.toString()
                        r0.error(r1, r2, r5)
                        return
                    L_0x009c:
                        com.tekartik.sqflite.SqflitePlugin r4 = com.tekartik.sqflite.SqflitePlugin.this
                        com.tekartik.sqflite.Database r5 = r0
                        boolean r4 = r4.update(r5, r6)
                        if (r4 == 0) goto L_0x00a7
                        goto L_0x00e2
                    L_0x00a7:
                        if (r0 == 0) goto L_0x00aa
                        goto L_0x00d1
                    L_0x00aa:
                        com.tekartik.sqflite.SqflitePlugin$BgResult r0 = r1
                        r6.handleError(r0)
                        return
                    L_0x00b0:
                        com.tekartik.sqflite.SqflitePlugin r4 = com.tekartik.sqflite.SqflitePlugin.this
                        com.tekartik.sqflite.Database r5 = r0
                        boolean r4 = r4.query(r5, r6)
                        if (r4 == 0) goto L_0x00bb
                        goto L_0x00e2
                    L_0x00bb:
                        if (r0 == 0) goto L_0x00be
                        goto L_0x00d1
                    L_0x00be:
                        com.tekartik.sqflite.SqflitePlugin$BgResult r0 = r1
                        r6.handleError(r0)
                        return
                    L_0x00c4:
                        com.tekartik.sqflite.SqflitePlugin r4 = com.tekartik.sqflite.SqflitePlugin.this
                        com.tekartik.sqflite.Database r5 = r0
                        boolean r4 = r4.insert(r5, r6)
                        if (r4 == 0) goto L_0x00cf
                        goto L_0x00e2
                    L_0x00cf:
                        if (r0 == 0) goto L_0x00d2
                    L_0x00d1:
                        goto L_0x00e9
                    L_0x00d2:
                        com.tekartik.sqflite.SqflitePlugin$BgResult r0 = r1
                        r6.handleError(r0)
                        return
                    L_0x00d8:
                        com.tekartik.sqflite.SqflitePlugin r4 = com.tekartik.sqflite.SqflitePlugin.this
                        com.tekartik.sqflite.Database r5 = r0
                        boolean r4 = r4.execute(r5, r6)
                        if (r4 == 0) goto L_0x00e7
                    L_0x00e2:
                        r6.handleSuccess(r3)
                        goto L_0x0024
                    L_0x00e7:
                        if (r0 == 0) goto L_0x00ee
                    L_0x00e9:
                        r6.handleErrorContinue(r3)
                        goto L_0x0024
                    L_0x00ee:
                        com.tekartik.sqflite.SqflitePlugin$BgResult r0 = r1
                        r6.handleError(r0)
                        return
                    L_0x00f4:
                        if (r1 == 0) goto L_0x00fc
                        com.tekartik.sqflite.SqflitePlugin$BgResult r0 = r1
                        r0.success(r5)
                        goto L_0x0101
                    L_0x00fc:
                        com.tekartik.sqflite.SqflitePlugin$BgResult r0 = r1
                        r0.success(r3)
                    L_0x0101:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tekartik.sqflite.SqflitePlugin.AnonymousClass2.run():void");
                }
            });
        }
    }

    private void onCloseDatabaseCall(MethodCall methodCall, MethodChannel.Result result) {
        final int intValue = ((Integer) methodCall.argument(ShareConstants.WEB_DIALOG_PARAM_ID)).intValue();
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError != null) {
            if (LogLevel.a(databaseOrError.d)) {
                Log.d(Constant.TAG, databaseOrError.b() + "closing " + intValue + " " + databaseOrError.b);
            }
            String str = databaseOrError.b;
            synchronized (databaseMapLocker) {
                d.remove(Integer.valueOf(intValue));
                if (databaseOrError.a) {
                    a.remove(str);
                }
            }
            final BgResult bgResult = new BgResult(result);
            handler.post(new Runnable() {
                public void run() {
                    synchronized (SqflitePlugin.openCloseLocker) {
                        try {
                            databaseOrError.close();
                        } catch (Exception e) {
                            Log.e(Constant.TAG, "error " + e + " while closing database " + intValue);
                        }
                        synchronized (SqflitePlugin.databaseMapLocker) {
                            if (SqflitePlugin.d.isEmpty() && SqflitePlugin.handler != null) {
                                if (LogLevel.a(databaseOrError.d)) {
                                    Log.d(Constant.TAG, databaseOrError.b() + "stopping thread" + SqflitePlugin.handlerThread);
                                }
                                SqflitePlugin.handlerThread.quit();
                                HandlerThread unused = SqflitePlugin.handlerThread = null;
                                Handler unused2 = SqflitePlugin.handler = null;
                            }
                        }
                    }
                    bgResult.success((Object) null);
                }
            });
        }
    }

    private void onDebugCall(MethodCall methodCall, MethodChannel.Result result) {
        HashMap hashMap = new HashMap();
        if ("get".equals((String) methodCall.argument("cmd"))) {
            int i = b;
            if (i > 0) {
                hashMap.put("logLevel", Integer.valueOf(i));
            }
            if (!d.isEmpty()) {
                HashMap hashMap2 = new HashMap();
                for (Map.Entry next : d.entrySet()) {
                    Database database = (Database) next.getValue();
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("path", database.b);
                    hashMap3.put("singleInstance", Boolean.valueOf(database.a));
                    if (database.d > 0) {
                        hashMap3.put("logLevel", Integer.valueOf(database.d));
                    }
                    hashMap2.put(((Integer) next.getKey()).toString(), hashMap3);
                }
                hashMap.put("databases", hashMap2);
            }
        }
        result.success(hashMap);
    }

    private void onDebugModeCall(MethodCall methodCall, MethodChannel.Result result) {
        Debug.LOGV = Boolean.TRUE.equals(methodCall.arguments());
        Debug.EXTRA_LOGV = Debug._EXTRA_LOGV && Debug.LOGV;
        if (!Debug.LOGV) {
            b = 0;
        } else if (Debug.EXTRA_LOGV) {
            b = 2;
        } else if (Debug.LOGV) {
            b = 1;
        }
        result.success((Object) null);
    }

    private void onExecuteCall(final MethodCall methodCall, MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError != null) {
            final BgResult bgResult = new BgResult(result);
            handler.post(new Runnable() {
                public void run() {
                    if (SqflitePlugin.this.executeOrError(databaseOrError, methodCall, bgResult) != null) {
                        bgResult.success((Object) null);
                    }
                }
            });
        }
    }

    private void onInsertCall(final MethodCall methodCall, MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError != null) {
            final BgResult bgResult = new BgResult(result);
            handler.post(new Runnable() {
                public void run() {
                    boolean unused = SqflitePlugin.this.insert(databaseOrError, new MethodCallOperation(methodCall, bgResult));
                }
            });
        }
    }

    private void onOpenDatabaseCall(MethodCall methodCall, MethodChannel.Result result) {
        final int i;
        Database database;
        final String str = (String) methodCall.argument("path");
        final Boolean bool = (Boolean) methodCall.argument("readOnly");
        final boolean a2 = a(str);
        final boolean z = !Boolean.FALSE.equals(methodCall.argument("singleInstance")) && !a2;
        if (z) {
            synchronized (databaseMapLocker) {
                if (LogLevel.b(b)) {
                    Log.d(Constant.TAG, "Look for " + str + " in " + a.keySet());
                }
                Integer num = a.get(str);
                if (!(num == null || (database = d.get(num)) == null)) {
                    if (database.e.isOpen()) {
                        if (LogLevel.b(b)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(database.b());
                            sb.append("re-opened single instance ");
                            sb.append(database.f ? "(in transaction) " : "");
                            sb.append(num);
                            sb.append(" ");
                            sb.append(str);
                            Log.d(Constant.TAG, sb.toString());
                        }
                        result.success(a(num.intValue(), true, database.f));
                        return;
                    } else if (LogLevel.b(b)) {
                        Log.d(Constant.TAG, database.b() + "single instance database of " + str + " not opened");
                    }
                }
            }
        }
        synchronized (databaseMapLocker) {
            i = databaseId + 1;
            databaseId = i;
        }
        final Database database2 = new Database(str, i, z, b);
        final BgResult bgResult = new BgResult(result);
        synchronized (databaseMapLocker) {
            if (handler == null) {
                handlerThread = new HandlerThread(Constant.TAG, THREAD_PRIORITY);
                handlerThread.start();
                handler = new Handler(handlerThread.getLooper());
                if (LogLevel.a(database2.d)) {
                    Log.d(Constant.TAG, database2.b() + "starting thread" + handlerThread + " priority " + THREAD_PRIORITY);
                }
            }
            if (LogLevel.a(database2.d)) {
                Log.d(Constant.TAG, database2.b() + "opened " + i + " " + str);
            }
            final MethodCall methodCall2 = methodCall;
            handler.post(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b7, code lost:
                    r5.success(com.tekartik.sqflite.SqflitePlugin.a(r10, false, false));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c3, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c7, code lost:
                    r1 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c8, code lost:
                    com.tekartik.sqflite.SqflitePlugin.a(r5.i, r1, (com.tekartik.sqflite.operation.Operation) new com.tekartik.sqflite.operation.MethodCallOperation(r8, r5), r7);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d9, code lost:
                    return;
                 */
                /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r5 = this;
                        java.lang.Object r0 = com.tekartik.sqflite.SqflitePlugin.openCloseLocker
                        monitor-enter(r0)
                        boolean r1 = r3     // Catch:{ all -> 0x00da }
                        if (r1 != 0) goto L_0x0048
                        java.io.File r1 = new java.io.File     // Catch:{ all -> 0x00da }
                        java.lang.String r2 = r4     // Catch:{ all -> 0x00da }
                        r1.<init>(r2)     // Catch:{ all -> 0x00da }
                        java.io.File r2 = new java.io.File     // Catch:{ all -> 0x00da }
                        java.lang.String r1 = r1.getParent()     // Catch:{ all -> 0x00da }
                        r2.<init>(r1)     // Catch:{ all -> 0x00da }
                        boolean r1 = r2.exists()     // Catch:{ all -> 0x00da }
                        if (r1 != 0) goto L_0x0048
                        boolean r1 = r2.mkdirs()     // Catch:{ all -> 0x00da }
                        if (r1 != 0) goto L_0x0048
                        boolean r1 = r2.exists()     // Catch:{ all -> 0x00da }
                        if (r1 != 0) goto L_0x0048
                        com.tekartik.sqflite.SqflitePlugin$BgResult r1 = r5     // Catch:{ all -> 0x00da }
                        java.lang.String r2 = "sqlite_error"
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00da }
                        r3.<init>()     // Catch:{ all -> 0x00da }
                        java.lang.String r4 = "open_failed "
                        r3.append(r4)     // Catch:{ all -> 0x00da }
                        java.lang.String r4 = r4     // Catch:{ all -> 0x00da }
                        r3.append(r4)     // Catch:{ all -> 0x00da }
                        java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00da }
                        r4 = 0
                        r1.error(r2, r3, r4)     // Catch:{ all -> 0x00da }
                        monitor-exit(r0)     // Catch:{ all -> 0x00da }
                        return
                    L_0x0048:
                        java.lang.Boolean r1 = java.lang.Boolean.TRUE     // Catch:{ Exception -> 0x00c7 }
                        java.lang.Boolean r2 = r6     // Catch:{ Exception -> 0x00c7 }
                        boolean r1 = r1.equals(r2)     // Catch:{ Exception -> 0x00c7 }
                        if (r1 == 0) goto L_0x0058
                        com.tekartik.sqflite.Database r1 = r7     // Catch:{ Exception -> 0x00c7 }
                        r1.openReadOnly()     // Catch:{ Exception -> 0x00c7 }
                        goto L_0x005d
                    L_0x0058:
                        com.tekartik.sqflite.Database r1 = r7     // Catch:{ Exception -> 0x00c7 }
                        r1.open()     // Catch:{ Exception -> 0x00c7 }
                    L_0x005d:
                        java.lang.Object r1 = com.tekartik.sqflite.SqflitePlugin.databaseMapLocker     // Catch:{ all -> 0x00da }
                        monitor-enter(r1)     // Catch:{ all -> 0x00da }
                        boolean r2 = r9     // Catch:{ all -> 0x00c4 }
                        if (r2 == 0) goto L_0x0073
                        java.util.Map<java.lang.String, java.lang.Integer> r2 = com.tekartik.sqflite.SqflitePlugin.a     // Catch:{ all -> 0x00c4 }
                        java.lang.String r3 = r4     // Catch:{ all -> 0x00c4 }
                        int r4 = r10     // Catch:{ all -> 0x00c4 }
                        java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00c4 }
                        r2.put(r3, r4)     // Catch:{ all -> 0x00c4 }
                    L_0x0073:
                        java.util.Map<java.lang.Integer, com.tekartik.sqflite.Database> r2 = com.tekartik.sqflite.SqflitePlugin.d     // Catch:{ all -> 0x00c4 }
                        int r3 = r10     // Catch:{ all -> 0x00c4 }
                        java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00c4 }
                        com.tekartik.sqflite.Database r4 = r7     // Catch:{ all -> 0x00c4 }
                        r2.put(r3, r4)     // Catch:{ all -> 0x00c4 }
                        monitor-exit(r1)     // Catch:{ all -> 0x00c4 }
                        com.tekartik.sqflite.Database r1 = r7     // Catch:{ all -> 0x00da }
                        int r1 = r1.d     // Catch:{ all -> 0x00da }
                        boolean r1 = com.tekartik.sqflite.LogLevel.a((int) r1)     // Catch:{ all -> 0x00da }
                        if (r1 == 0) goto L_0x00b6
                        java.lang.String r1 = "Sqflite"
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00da }
                        r2.<init>()     // Catch:{ all -> 0x00da }
                        com.tekartik.sqflite.Database r3 = r7     // Catch:{ all -> 0x00da }
                        java.lang.String r3 = r3.b()     // Catch:{ all -> 0x00da }
                        r2.append(r3)     // Catch:{ all -> 0x00da }
                        java.lang.String r3 = "opened "
                        r2.append(r3)     // Catch:{ all -> 0x00da }
                        int r3 = r10     // Catch:{ all -> 0x00da }
                        r2.append(r3)     // Catch:{ all -> 0x00da }
                        java.lang.String r3 = " "
                        r2.append(r3)     // Catch:{ all -> 0x00da }
                        java.lang.String r3 = r4     // Catch:{ all -> 0x00da }
                        r2.append(r3)     // Catch:{ all -> 0x00da }
                        java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00da }
                        android.util.Log.d(r1, r2)     // Catch:{ all -> 0x00da }
                    L_0x00b6:
                        monitor-exit(r0)     // Catch:{ all -> 0x00da }
                        com.tekartik.sqflite.SqflitePlugin$BgResult r0 = r5
                        int r1 = r10
                        r2 = 0
                        java.util.Map r1 = com.tekartik.sqflite.SqflitePlugin.a((int) r1, (boolean) r2, (boolean) r2)
                        r0.success(r1)
                        return
                    L_0x00c4:
                        r2 = move-exception
                        monitor-exit(r1)     // Catch:{ all -> 0x00c4 }
                        throw r2     // Catch:{ all -> 0x00da }
                    L_0x00c7:
                        r1 = move-exception
                        com.tekartik.sqflite.operation.MethodCallOperation r2 = new com.tekartik.sqflite.operation.MethodCallOperation     // Catch:{ all -> 0x00da }
                        io.flutter.plugin.common.MethodCall r3 = r8     // Catch:{ all -> 0x00da }
                        com.tekartik.sqflite.SqflitePlugin$BgResult r4 = r5     // Catch:{ all -> 0x00da }
                        r2.<init>(r3, r4)     // Catch:{ all -> 0x00da }
                        com.tekartik.sqflite.SqflitePlugin r3 = com.tekartik.sqflite.SqflitePlugin.this     // Catch:{ all -> 0x00da }
                        com.tekartik.sqflite.Database r4 = r7     // Catch:{ all -> 0x00da }
                        r3.handleException(r1, r2, r4)     // Catch:{ all -> 0x00da }
                        monitor-exit(r0)     // Catch:{ all -> 0x00da }
                        return
                    L_0x00da:
                        r1 = move-exception
                        monitor-exit(r0)     // Catch:{ all -> 0x00da }
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tekartik.sqflite.SqflitePlugin.AnonymousClass6.run():void");
                }
            });
        }
    }

    private void onQueryCall(final MethodCall methodCall, MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError != null) {
            final BgResult bgResult = new BgResult(result);
            handler.post(new Runnable() {
                public void run() {
                    boolean unused = SqflitePlugin.this.query(databaseOrError, new MethodCallOperation(methodCall, bgResult));
                }
            });
        }
    }

    private void onUpdateCall(final MethodCall methodCall, MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError != null) {
            final BgResult bgResult = new BgResult(result);
            handler.post(new Runnable() {
                public void run() {
                    boolean unused = SqflitePlugin.this.update(databaseOrError, new MethodCallOperation(methodCall, bgResult));
                }
            });
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [java.util.Map, java.util.HashMap] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0077, code lost:
        if (r4 != null) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0079, code lost:
        r4 = new java.util.ArrayList();
        r5 = new java.util.HashMap();
        r6 = r0.getColumnCount();
        r5.put("columns", java.util.Arrays.asList(r0.getColumnNames()));
        r5.put("rows", r4);
        r11 = r6;
        r6 = r4;
        r4 = r5;
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x009d, code lost:
        r6.add(cursorRowToList(r0, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00ab, code lost:
        if (r4 != null) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00ad, code lost:
        r4 = new java.util.HashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b2, code lost:
        r14.success(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0043, code lost:
        r4 = r4;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d0  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean query(com.tekartik.sqflite.Database r13, com.tekartik.sqflite.operation.Operation r14) {
        /*
            r12 = this;
            com.tekartik.sqflite.SqlCommand r0 = r14.getSqlCommand()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            int r2 = r13.d
            boolean r2 = com.tekartik.sqflite.LogLevel.a((int) r2)
            if (r2 == 0) goto L_0x0029
            java.lang.String r2 = "Sqflite"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r13.b()
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r2, r3)
        L_0x0029:
            boolean r2 = QUERY_AS_MAP_LIST
            r3 = 0
            r4 = 0
            com.tekartik.sqflite.SqlCommand r0 = r0.sanitizeForQuery()     // Catch:{ Exception -> 0x00c4 }
            android.database.sqlite.SQLiteDatabase r5 = r13.getReadableDatabase()     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r6 = r0.getSql()     // Catch:{ Exception -> 0x00c4 }
            java.lang.String[] r0 = r0.getQuerySqlArguments()     // Catch:{ Exception -> 0x00c4 }
            android.database.Cursor r0 = r5.rawQuery(r6, r0)     // Catch:{ Exception -> 0x00c4 }
            r6 = r4
            r5 = 0
        L_0x0043:
            boolean r7 = r0.moveToNext()     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            if (r7 == 0) goto L_0x00a5
            if (r2 == 0) goto L_0x0077
            java.util.Map r7 = cursorRowToMap(r0)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            int r8 = r13.d     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            boolean r8 = com.tekartik.sqflite.LogLevel.a((int) r8)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            if (r8 == 0) goto L_0x0073
            java.lang.String r8 = "Sqflite"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            r9.<init>()     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            java.lang.String r10 = r13.b()     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            r9.append(r10)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            java.lang.String r10 = toString(r7)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            r9.append(r10)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            android.util.Log.d(r8, r9)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
        L_0x0073:
            r1.add(r7)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            goto L_0x0043
        L_0x0077:
            if (r4 != 0) goto L_0x009d
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            r4.<init>()     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            r5.<init>()     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            int r6 = r0.getColumnCount()     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            java.lang.String r7 = "columns"
            java.lang.String[] r8 = r0.getColumnNames()     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            java.util.List r8 = java.util.Arrays.asList(r8)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            r5.put(r7, r8)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            java.lang.String r7 = "rows"
            r5.put(r7, r4)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            r11 = r6
            r6 = r4
            r4 = r5
            r5 = r11
        L_0x009d:
            java.util.List r7 = cursorRowToList(r0, r5)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            r6.add(r7)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            goto L_0x0043
        L_0x00a5:
            if (r2 == 0) goto L_0x00ab
            r14.success(r1)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            goto L_0x00b5
        L_0x00ab:
            if (r4 != 0) goto L_0x00b2
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
            r4.<init>()     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
        L_0x00b2:
            r14.success(r4)     // Catch:{ Exception -> 0x00be, all -> 0x00bc }
        L_0x00b5:
            r13 = 1
            if (r0 == 0) goto L_0x00bb
            r0.close()
        L_0x00bb:
            return r13
        L_0x00bc:
            r13 = move-exception
            goto L_0x00ce
        L_0x00be:
            r1 = move-exception
            r4 = r0
            goto L_0x00c5
        L_0x00c1:
            r13 = move-exception
            r0 = r4
            goto L_0x00ce
        L_0x00c4:
            r1 = move-exception
        L_0x00c5:
            r12.handleException(r1, r14, r13)     // Catch:{ all -> 0x00c1 }
            if (r4 == 0) goto L_0x00cd
            r4.close()
        L_0x00cd:
            return r3
        L_0x00ce:
            if (r0 == 0) goto L_0x00d3
            r0.close()
        L_0x00d3:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tekartik.sqflite.SqflitePlugin.query(com.tekartik.sqflite.Database, com.tekartik.sqflite.operation.Operation):boolean");
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodChannel(registrar.messenger(), BuildConfig.APPLICATION_ID).setMethodCallHandler(new SqflitePlugin(registrar.context()));
    }

    private static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof byte[])) {
            return obj instanceof Map ? fixMap((Map) obj).toString() : obj.toString();
        }
        ArrayList arrayList = new ArrayList();
        for (byte valueOf : (byte[]) obj) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList.toString();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean update(com.tekartik.sqflite.Database r8, com.tekartik.sqflite.operation.Operation r9) {
        /*
            r7 = this;
            boolean r0 = r7.executeOrError(r8, r9)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            boolean r0 = r9.getNoResult()
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0014
            r9.success(r3)
            return r2
        L_0x0014:
            android.database.sqlite.SQLiteDatabase r0 = r8.getWritableDatabase()     // Catch:{ Exception -> 0x008d }
            java.lang.String r4 = "SELECT changes()"
            android.database.Cursor r0 = r0.rawQuery(r4, r3)     // Catch:{ Exception -> 0x008d }
            if (r0 == 0) goto L_0x0062
            int r4 = r0.getCount()     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            if (r4 <= 0) goto L_0x0062
            boolean r4 = r0.moveToFirst()     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            if (r4 == 0) goto L_0x0062
            int r3 = r0.getInt(r1)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            int r4 = r8.d     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            boolean r4 = com.tekartik.sqflite.LogLevel.a((int) r4)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            if (r4 == 0) goto L_0x0055
            java.lang.String r4 = "Sqflite"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            r5.<init>()     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            java.lang.String r6 = r8.b()     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            r5.append(r6)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            java.lang.String r6 = "changed "
            r5.append(r6)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            r5.append(r3)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            android.util.Log.d(r4, r5)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
        L_0x0055:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            r9.success(r3)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            if (r0 == 0) goto L_0x0061
            r0.close()
        L_0x0061:
            return r2
        L_0x0062:
            java.lang.String r4 = "Sqflite"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            r5.<init>()     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            java.lang.String r6 = r8.b()     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            r5.append(r6)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            java.lang.String r6 = "fail to read changes for Update/Delete"
            r5.append(r6)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            android.util.Log.e(r4, r5)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            r9.success(r3)     // Catch:{ Exception -> 0x0087, all -> 0x0085 }
            if (r0 == 0) goto L_0x0084
            r0.close()
        L_0x0084:
            return r2
        L_0x0085:
            r8 = move-exception
            goto L_0x0097
        L_0x0087:
            r2 = move-exception
            r3 = r0
            goto L_0x008e
        L_0x008a:
            r8 = move-exception
            r0 = r3
            goto L_0x0097
        L_0x008d:
            r2 = move-exception
        L_0x008e:
            r7.handleException(r2, r9, r8)     // Catch:{ all -> 0x008a }
            if (r3 == 0) goto L_0x0096
            r3.close()
        L_0x0096:
            return r1
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            r0.close()
        L_0x009c:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tekartik.sqflite.SqflitePlugin.update(com.tekartik.sqflite.Database, com.tekartik.sqflite.operation.Operation):boolean");
    }

    /* access modifiers changed from: package-private */
    public void a(MethodCall methodCall, MethodChannel.Result result) {
        Object argument = methodCall.argument("queryAsMapList");
        if (argument != null) {
            QUERY_AS_MAP_LIST = Boolean.TRUE.equals(argument);
        }
        Object argument2 = methodCall.argument("androidThreadPriority");
        if (argument2 != null) {
            THREAD_PRIORITY = ((Integer) argument2).intValue();
        }
        Integer a2 = LogLevel.a(methodCall);
        if (a2 != null) {
            b = a2.intValue();
        }
        result.success((Object) null);
    }

    /* access modifiers changed from: package-private */
    public void b(MethodCall methodCall, MethodChannel.Result result) {
        if (c == null) {
            c = context.getDatabasePath("tekartik_sqflite.db").getParent();
        }
        result.success(c);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r3, io.flutter.plugin.common.MethodChannel.Result r4) {
        /*
            r2 = this;
            java.lang.String r0 = r3.method
            int r1 = r0.hashCode()
            switch(r1) {
                case -1319569547: goto L_0x007f;
                case -1253581933: goto L_0x0075;
                case -1249474914: goto L_0x006a;
                case -1183792455: goto L_0x0060;
                case -838846263: goto L_0x0056;
                case -198450538: goto L_0x004b;
                case -17190427: goto L_0x0041;
                case 93509434: goto L_0x0037;
                case 95458899: goto L_0x002c;
                case 107944136: goto L_0x0022;
                case 1385449135: goto L_0x0017;
                case 1863829223: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0089
        L_0x000b:
            java.lang.String r1 = "getDatabasesPath"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 9
            goto L_0x008a
        L_0x0017:
            java.lang.String r1 = "getPlatformVersion"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 0
            goto L_0x008a
        L_0x0022:
            java.lang.String r1 = "query"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 2
            goto L_0x008a
        L_0x002c:
            java.lang.String r1 = "debug"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 10
            goto L_0x008a
        L_0x0037:
            java.lang.String r1 = "batch"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 7
            goto L_0x008a
        L_0x0041:
            java.lang.String r1 = "openDatabase"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 6
            goto L_0x008a
        L_0x004b:
            java.lang.String r1 = "debugMode"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 11
            goto L_0x008a
        L_0x0056:
            java.lang.String r1 = "update"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 4
            goto L_0x008a
        L_0x0060:
            java.lang.String r1 = "insert"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 3
            goto L_0x008a
        L_0x006a:
            java.lang.String r1 = "options"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 8
            goto L_0x008a
        L_0x0075:
            java.lang.String r1 = "closeDatabase"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 1
            goto L_0x008a
        L_0x007f:
            java.lang.String r1 = "execute"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0089
            r0 = 5
            goto L_0x008a
        L_0x0089:
            r0 = -1
        L_0x008a:
            switch(r0) {
                case 0: goto L_0x00bd;
                case 1: goto L_0x00b9;
                case 2: goto L_0x00b5;
                case 3: goto L_0x00b1;
                case 4: goto L_0x00ad;
                case 5: goto L_0x00a9;
                case 6: goto L_0x00a5;
                case 7: goto L_0x00a1;
                case 8: goto L_0x009d;
                case 9: goto L_0x0099;
                case 10: goto L_0x0095;
                case 11: goto L_0x0091;
                default: goto L_0x008d;
            }
        L_0x008d:
            r4.notImplemented()
            goto L_0x00d3
        L_0x0091:
            r2.onDebugModeCall(r3, r4)
            goto L_0x00d3
        L_0x0095:
            r2.onDebugCall(r3, r4)
            goto L_0x00d3
        L_0x0099:
            r2.b(r3, r4)
            goto L_0x00d3
        L_0x009d:
            r2.a(r3, r4)
            goto L_0x00d3
        L_0x00a1:
            r2.onBatchCall(r3, r4)
            goto L_0x00d3
        L_0x00a5:
            r2.onOpenDatabaseCall(r3, r4)
            goto L_0x00d3
        L_0x00a9:
            r2.onExecuteCall(r3, r4)
            goto L_0x00d3
        L_0x00ad:
            r2.onUpdateCall(r3, r4)
            goto L_0x00d3
        L_0x00b1:
            r2.onInsertCall(r3, r4)
            goto L_0x00d3
        L_0x00b5:
            r2.onQueryCall(r3, r4)
            goto L_0x00d3
        L_0x00b9:
            r2.onCloseDatabaseCall(r3, r4)
            goto L_0x00d3
        L_0x00bd:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "Android "
            r3.append(r0)
            java.lang.String r0 = android.os.Build.VERSION.RELEASE
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r4.success(r3)
        L_0x00d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tekartik.sqflite.SqflitePlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
