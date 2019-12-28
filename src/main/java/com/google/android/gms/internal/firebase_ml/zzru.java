package com.google.android.gms.internal.firebase_ml;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzru implements Serializable, Iterable<Byte> {
    public static final zzru zzbig = new zzse(zzte.zzbmz);
    private static final zzsa zzbih = (zzrp.a() ? new zzsf((zzrv) null) : new zzry((zzrv) null));
    private static final Comparator<zzru> zzbii = new zzrw();
    private int zzade = 0;

    zzru() {
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

    static zzsc b(int i) {
        return new zzsc(i, (zzrv) null);
    }

    /* access modifiers changed from: private */
    public static int zzb(byte b) {
        return b & 255;
    }

    public static zzru zzc(byte[] bArr, int i, int i2) {
        b(i, i + i2, bArr.length);
        return new zzse(zzbih.zzd(bArr, i, i2));
    }

    public static zzru zzci(String str) {
        return new zzse(str.getBytes(zzte.a));
    }

    /* access modifiers changed from: package-private */
    public abstract byte a(int i);

    /* access modifiers changed from: protected */
    public final int a() {
        return this.zzade;
    }

    /* access modifiers changed from: protected */
    public abstract int a(int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public abstract String a(Charset charset);

    /* access modifiers changed from: package-private */
    public abstract void a(zzrt zzrt);

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzade;
        if (i == 0) {
            int size = size();
            i = a(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzade = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzrv(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    public abstract zzru zzc(int i, int i2);

    public abstract byte zzcc(int i);

    public final String zzol() {
        return size() == 0 ? "" : a(zzte.a);
    }

    public abstract boolean zzom();
}
