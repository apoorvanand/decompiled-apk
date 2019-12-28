package com.google.android.gms.internal.firebase_auth;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzgf implements Serializable, Iterable<Byte> {
    public static final zzgf zzvv = new zzgp(zzht.EMPTY_BYTE_ARRAY);
    private static final zzgl zzvw = (zzge.a() ? new zzgs((zzgi) null) : new zzgj((zzgi) null));
    private static final Comparator<zzgf> zzvy = new zzgh();
    private int zzvx = 0;

    zzgf() {
    }

    static zzgf a(byte[] bArr) {
        return new zzgp(bArr);
    }

    static int b(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else {
            StringBuilder sb3 = new StringBuilder(37);
            sb3.append("End index: ");
            sb3.append(i2);
            sb3.append(" >= ");
            sb3.append(i3);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    static zzgn b(int i) {
        return new zzgn(i, (zzgi) null);
    }

    /* access modifiers changed from: private */
    public static int zza(byte b) {
        return b & 255;
    }

    public static zzgf zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzgf zza(byte[] bArr, int i, int i2) {
        b(i, i + i2, bArr.length);
        return new zzgp(zzvw.zzc(bArr, i, i2));
    }

    public static zzgf zzdh(String str) {
        return new zzgp(str.getBytes(zzht.a));
    }

    /* access modifiers changed from: package-private */
    public abstract byte a(int i);

    /* access modifiers changed from: protected */
    public final int a() {
        return this.zzvx;
    }

    /* access modifiers changed from: protected */
    public abstract int a(int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public abstract String a(Charset charset);

    /* access modifiers changed from: package-private */
    public abstract void a(zzgg zzgg);

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzvx;
        if (i == 0) {
            int size = size();
            i = a(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzvx = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzgi(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    public abstract zzgf zzd(int i, int i2);

    public final String zzgc() {
        return size() == 0 ? "" : a(zzht.a);
    }

    public abstract boolean zzgd();

    public abstract byte zzp(int i);
}
