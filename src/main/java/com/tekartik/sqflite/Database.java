package com.tekartik.sqflite;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class Database {
    final boolean a;
    final String b;
    final int c;
    final int d;
    SQLiteDatabase e;
    boolean f;

    Database(String str, int i, boolean z, int i2) {
        this.b = str;
        this.a = z;
        this.c = i;
        this.d = i2;
    }

    /* access modifiers changed from: package-private */
    public String a() {
        Thread currentThread = Thread.currentThread();
        return "" + this.c + "," + currentThread.getName() + "(" + currentThread.getId() + ")";
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return "[" + a() + "] ";
    }

    public void close() {
        this.e.close();
    }

    public boolean enableWriteAheadLogging() {
        try {
            return this.e.enableWriteAheadLogging();
        } catch (Exception e2) {
            Log.e(Constant.TAG, b() + "enable WAL error: " + e2);
            return false;
        }
    }

    public SQLiteDatabase getReadableDatabase() {
        return this.e;
    }

    public SQLiteDatabase getWritableDatabase() {
        return this.e;
    }

    public void open() {
        this.e = SQLiteDatabase.openDatabase(this.b, (SQLiteDatabase.CursorFactory) null, 268435456);
    }

    public void openReadOnly() {
        this.e = SQLiteDatabase.openDatabase(this.b, (SQLiteDatabase.CursorFactory) null, 1, new DatabaseErrorHandler() {
            public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            }
        });
    }
}
