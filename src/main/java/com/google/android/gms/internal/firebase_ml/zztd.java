package com.google.android.gms.internal.firebase_ml;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zztd extends zzro<Integer> implements zztj, zzuy, RandomAccess {
    private static final zztd zzbmx;
    private int size;
    private int[] zzbmy;

    static {
        zztd zztd = new zztd(new int[0], 0);
        zzbmx = zztd;
        zztd.zzoh();
    }

    zztd() {
        this(new int[10], 0);
    }

    private zztd(int[] iArr, int i) {
        this.zzbmy = iArr;
        this.size = i;
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

    public static zztd zzpz() {
        return zzbmx;
    }

    private final void zzq(int i, int i2) {
        int i3;
        a();
        if (i < 0 || i > (i3 = this.size)) {
            throw new IndexOutOfBoundsException(zzca(i));
        }
        int[] iArr = this.zzbmy;
        if (i3 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i3 - i);
        } else {
            int[] iArr2 = new int[(((i3 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.zzbmy, i, iArr2, i + 1, this.size - i);
            this.zzbmy = iArr2;
        }
        this.zzbmy[i] = i2;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzq(i, ((Integer) obj).intValue());
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        a();
        zzte.a(collection);
        if (!(collection instanceof zztd)) {
            return super.addAll(collection);
        }
        zztd zztd = (zztd) collection;
        int i = zztd.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzbmy;
            if (i3 > iArr.length) {
                this.zzbmy = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zztd.zzbmy, 0, this.zzbmy, this.size, zztd.size);
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
        if (!(obj instanceof zztd)) {
            return super.equals(obj);
        }
        zztd zztd = (zztd) obj;
        if (this.size != zztd.size) {
            return false;
        }
        int[] iArr = zztd.zzbmy;
        for (int i = 0; i < this.size; i++) {
            if (this.zzbmy[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    public final int getInt(int i) {
        zzbz(i);
        return this.zzbmy[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzbmy[i2];
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        a();
        zzbz(i);
        int[] iArr = this.zzbmy;
        int i2 = iArr[i];
        int i3 = this.size;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final boolean remove(Object obj) {
        a();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzbmy[i]))) {
                int[] iArr = this.zzbmy;
                System.arraycopy(iArr, i + 1, iArr, i, (this.size - i) - 1);
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
            int[] iArr = this.zzbmy;
            System.arraycopy(iArr, i2, iArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        a();
        zzbz(i);
        int[] iArr = this.zzbmy;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final int size() {
        return this.size;
    }

    /* renamed from: zzcx */
    public final zztj zzcb(int i) {
        if (i >= this.size) {
            return new zztd(Arrays.copyOf(this.zzbmy, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzcy(int i) {
        zzq(this.size, i);
    }
}
