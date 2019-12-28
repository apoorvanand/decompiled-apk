package com.google.android.gms.internal.firebase_ml;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzsm extends zzro<Double> implements zztl<Double>, zzuy, RandomAccess {
    private static final zzsm zzbiw;
    private int size;
    private double[] zzbix;

    static {
        zzsm zzsm = new zzsm(new double[0], 0);
        zzbiw = zzsm;
        zzsm.zzoh();
    }

    zzsm() {
        this(new double[10], 0);
    }

    private zzsm(double[] dArr, int i) {
        this.zzbix = dArr;
        this.size = i;
    }

    private final void zzbz(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzca(i));
        }
    }

    private final void zzc(int i, double d) {
        int i2;
        a();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzca(i));
        }
        double[] dArr = this.zzbix;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zzbix, i, dArr2, i + 1, this.size - i);
            this.zzbix = dArr2;
        }
        this.zzbix[i] = d;
        this.size++;
        this.modCount++;
    }

    private final String zzca(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Double) obj).doubleValue());
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        a();
        zzte.a(collection);
        if (!(collection instanceof zzsm)) {
            return super.addAll(collection);
        }
        zzsm zzsm = (zzsm) collection;
        int i = zzsm.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzbix;
            if (i3 > dArr.length) {
                this.zzbix = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzsm.zzbix, 0, this.zzbix, this.size, zzsm.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzsm)) {
            return super.equals(obj);
        }
        zzsm zzsm = (zzsm) obj;
        if (this.size != zzsm.size) {
            return false;
        }
        double[] dArr = zzsm.zzbix;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzbix[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzbz(i);
        return Double.valueOf(this.zzbix[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzte.zzw(Double.doubleToLongBits(this.zzbix[i2]));
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        a();
        zzbz(i);
        double[] dArr = this.zzbix;
        double d = dArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final boolean remove(Object obj) {
        a();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzbix[i]))) {
                double[] dArr = this.zzbix;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        a();
        if (i2 >= i) {
            double[] dArr = this.zzbix;
            System.arraycopy(dArr, i2, dArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        a();
        zzbz(i);
        double[] dArr = this.zzbix;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zztl zzcb(int i) {
        if (i >= this.size) {
            return new zzsm(Arrays.copyOf(this.zzbix, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(double d) {
        zzc(this.size, d);
    }
}