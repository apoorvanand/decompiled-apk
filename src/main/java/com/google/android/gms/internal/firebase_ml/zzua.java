package com.google.android.gms.internal.firebase_ml;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzua extends zzro<Long> implements zztl<Long>, zzuy, RandomAccess {
    private static final zzua zzboc;
    private int size;
    private long[] zzbod;

    static {
        zzua zzua = new zzua(new long[0], 0);
        zzboc = zzua;
        zzua.zzoh();
    }

    zzua() {
        this(new long[10], 0);
    }

    private zzua(long[] jArr, int i) {
        this.zzbod = jArr;
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

    private final void zzk(int i, long j) {
        int i2;
        a();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzca(i));
        }
        long[] jArr = this.zzbod;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zzbod, i, jArr2, i + 1, this.size - i);
            this.zzbod = jArr2;
        }
        this.zzbod[i] = j;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzk(i, ((Long) obj).longValue());
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        a();
        zzte.a(collection);
        if (!(collection instanceof zzua)) {
            return super.addAll(collection);
        }
        zzua zzua = (zzua) collection;
        int i = zzua.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzbod;
            if (i3 > jArr.length) {
                this.zzbod = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzua.zzbod, 0, this.zzbod, this.size, zzua.size);
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
        if (!(obj instanceof zzua)) {
            return super.equals(obj);
        }
        zzua zzua = (zzua) obj;
        if (this.size != zzua.size) {
            return false;
        }
        long[] jArr = zzua.zzbod;
        for (int i = 0; i < this.size; i++) {
            if (this.zzbod[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    public final long getLong(int i) {
        zzbz(i);
        return this.zzbod[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzte.zzw(this.zzbod[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        a();
        zzbz(i);
        long[] jArr = this.zzbod;
        long j = jArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final boolean remove(Object obj) {
        a();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzbod[i]))) {
                long[] jArr = this.zzbod;
                System.arraycopy(jArr, i + 1, jArr, i, (this.size - i) - 1);
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
            long[] jArr = this.zzbod;
            System.arraycopy(jArr, i2, jArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        a();
        zzbz(i);
        long[] jArr = this.zzbod;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zztl zzcb(int i) {
        if (i >= this.size) {
            return new zzua(Arrays.copyOf(this.zzbod, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzx(long j) {
        zzk(this.size, j);
    }
}
