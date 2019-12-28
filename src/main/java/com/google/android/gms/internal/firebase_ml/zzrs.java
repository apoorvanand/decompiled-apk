package com.google.android.gms.internal.firebase_ml;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzrs extends zzro<Boolean> implements zztl<Boolean>, zzuy, RandomAccess {
    private static final zzrs zzbie;
    private int size;
    private boolean[] zzbif;

    static {
        zzrs zzrs = new zzrs(new boolean[0], 0);
        zzbie = zzrs;
        zzrs.zzoh();
    }

    zzrs() {
        this(new boolean[10], 0);
    }

    private zzrs(boolean[] zArr, int i) {
        this.zzbif = zArr;
        this.size = i;
    }

    private final void zza(int i, boolean z) {
        int i2;
        a();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzca(i));
        }
        boolean[] zArr = this.zzbif;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.zzbif, i, zArr2, i + 1, this.size - i);
            this.zzbif = zArr2;
        }
        this.zzbif[i] = z;
        this.size++;
        this.modCount++;
    }

    private final void zzbz(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzca(i));
        }
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
        zza(i, ((Boolean) obj).booleanValue());
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        a();
        zzte.a(collection);
        if (!(collection instanceof zzrs)) {
            return super.addAll(collection);
        }
        zzrs zzrs = (zzrs) collection;
        int i = zzrs.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zzbif;
            if (i3 > zArr.length) {
                this.zzbif = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzrs.zzbif, 0, this.zzbif, this.size, zzrs.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final void addBoolean(boolean z) {
        zza(this.size, z);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzrs)) {
            return super.equals(obj);
        }
        zzrs zzrs = (zzrs) obj;
        if (this.size != zzrs.size) {
            return false;
        }
        boolean[] zArr = zzrs.zzbif;
        for (int i = 0; i < this.size; i++) {
            if (this.zzbif[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzbz(i);
        return Boolean.valueOf(this.zzbif[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzte.zzai(this.zzbif[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        a();
        zzbz(i);
        boolean[] zArr = this.zzbif;
        boolean z = zArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final boolean remove(Object obj) {
        a();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzbif[i]))) {
                boolean[] zArr = this.zzbif;
                System.arraycopy(zArr, i + 1, zArr, i, (this.size - i) - 1);
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
            boolean[] zArr = this.zzbif;
            System.arraycopy(zArr, i2, zArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        a();
        zzbz(i);
        boolean[] zArr = this.zzbif;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zztl zzcb(int i) {
        if (i >= this.size) {
            return new zzrs(Arrays.copyOf(this.zzbif, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
