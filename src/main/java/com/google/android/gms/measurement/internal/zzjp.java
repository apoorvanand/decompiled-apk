package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzjp {
    final String a;
    final String b;
    final String c;
    final long d;
    final Object e;

    zzjp(String str, String str2, String str3, long j, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(obj);
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = j;
        this.e = obj;
    }
}
