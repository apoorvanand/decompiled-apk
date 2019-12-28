package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzae {
    final String a;
    final String b;
    final long c;
    final long d;
    final long e;
    final long f;
    final long g;
    final Long h;
    final Long i;
    final Long j;
    final Boolean k;

    zzae(String str, String str2, long j2, long j3, long j4, long j5, long j6, Long l, Long l2, Long l3, Boolean bool) {
        long j7 = j2;
        long j8 = j3;
        long j9 = j4;
        long j10 = j6;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        boolean z = true;
        Preconditions.checkArgument(j7 >= 0);
        Preconditions.checkArgument(j8 >= 0);
        Preconditions.checkArgument(j9 >= 0);
        Preconditions.checkArgument(j10 < 0 ? false : z);
        this.a = str;
        this.b = str2;
        this.c = j7;
        this.d = j8;
        this.e = j9;
        this.f = j5;
        this.g = j10;
        this.h = l;
        this.i = l2;
        this.j = l3;
        this.k = bool;
    }

    zzae(String str, String str2, long j2, long j3, long j4, long j5, Long l, Long l2, Long l3, Boolean bool) {
        this(str, str2, j2, j3, 0, j4, 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
    }

    /* access modifiers changed from: package-private */
    public final zzae a(long j2) {
        return new zzae(this.a, this.b, this.c, this.d, this.e, j2, this.g, this.h, this.i, this.j, this.k);
    }

    /* access modifiers changed from: package-private */
    public final zzae a(long j2, long j3) {
        return new zzae(this.a, this.b, this.c, this.d, this.e, this.f, j2, Long.valueOf(j3), this.i, this.j, this.k);
    }

    /* access modifiers changed from: package-private */
    public final zzae a(Long l, Long l2, Boolean bool) {
        return new zzae(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, l, l2, (bool == null || bool.booleanValue()) ? bool : null);
    }
}
