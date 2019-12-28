package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzdp implements Serializable, Iterable<Byte> {
    public static final zzdp zzadh = new zzdz(zzez.zzair);
    private static final zzdv zzadi = (zzdi.a() ? new zzdy((zzdo) null) : new zzdt((zzdo) null));
    private static final Comparator<zzdp> zzadk = new zzdr();
    private int zzadj = 0;

    zzdp() {
    }

    static zzdp a(byte[] bArr) {
        return new zzdz(bArr);
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

    static zzdx b(int i) {
        return new zzdx(i, (zzdo) null);
    }

    /* access modifiers changed from: private */
    public static int zza(byte b) {
        return b & 255;
    }

    public static zzdp zzb(byte[] bArr, int i, int i2) {
        b(i, i + i2, bArr.length);
        return new zzdz(zzadi.zzc(bArr, i, i2));
    }

    public static zzdp zzdq(String str) {
        return new zzdz(str.getBytes(zzez.a));
    }

    /* access modifiers changed from: package-private */
    public abstract byte a(int i);

    /* access modifiers changed from: protected */
    public final int a() {
        return this.zzadj;
    }

    /* access modifiers changed from: protected */
    public abstract int a(int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public abstract String a(Charset charset);

    /* access modifiers changed from: package-private */
    public abstract void a(zzdm zzdm);

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzadj;
        if (i == 0) {
            int size = size();
            i = a(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzadj = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzdo(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    public abstract zzdp zza(int i, int i2);

    public abstract byte zzaq(int i);

    public final String zzsa() {
        return size() == 0 ? "" : a(zzez.a);
    }

    public abstract boolean zzsb();
}
