package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzem extends zzef<Boolean> implements zzge<Boolean>, zzhr, RandomAccess {
    private static final zzem zzrv;
    private int size;
    private boolean[] zzrw;

    static {
        zzem zzem = new zzem();
        zzrv = zzem;
        zzem.zzci();
    }

    zzem() {
        this(new boolean[10], 0);
    }

    private zzem(boolean[] zArr, int i) {
        this.zzrw = zArr;
        this.size = i;
    }

    private final void zza(int i, boolean z) {
        int i2;
        a();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzag(i));
        }
        boolean[] zArr = this.zzrw;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.zzrw, i, zArr2, i + 1, this.size - i);
            this.zzrw = zArr2;
        }
        this.zzrw[i] = z;
        this.size++;
        this.modCount++;
    }

    private final void zzaf(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzag(i));
        }
    }

    private final String zzag(int i) {
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
        zzga.a(collection);
        if (!(collection instanceof zzem)) {
            return super.addAll(collection);
        }
        zzem zzem = (zzem) collection;
        int i = zzem.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zzrw;
            if (i3 > zArr.length) {
                this.zzrw = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzem.zzrw, 0, this.zzrw, this.size, zzem.size);
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
        if (!(obj instanceof zzem)) {
            return super.equals(obj);
        }
        zzem zzem = (zzem) obj;
        if (this.size != zzem.size) {
            return false;
        }
        boolean[] zArr = zzem.zzrw;
        for (int i = 0; i < this.size; i++) {
            if (this.zzrw[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzaf(i);
        return Boolean.valueOf(this.zzrw[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzga.zzj(this.zzrw[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        a();
        zzaf(i);
        boolean[] zArr = this.zzrw;
        boolean z = zArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, i2 - i);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final boolean remove(Object obj) {
        a();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzrw[i]))) {
                boolean[] zArr = this.zzrw;
                System.arraycopy(zArr, i + 1, zArr, i, this.size - i);
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
            boolean[] zArr = this.zzrw;
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
        zzaf(i);
        boolean[] zArr = this.zzrw;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzge zzah(int i) {
        if (i >= this.size) {
            return new zzem(Arrays.copyOf(this.zzrw, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
