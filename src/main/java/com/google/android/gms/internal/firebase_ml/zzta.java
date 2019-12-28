package com.google.android.gms.internal.firebase_ml;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzta extends zzro<Float> implements zztl<Float>, zzuy, RandomAccess {
    private static final zzta zzblz;
    private int size;
    private float[] zzbma;

    static {
        zzta zzta = new zzta(new float[0], 0);
        zzblz = zzta;
        zzta.zzoh();
    }

    zzta() {
        this(new float[10], 0);
    }

    private zzta(float[] fArr, int i) {
        this.zzbma = fArr;
        this.size = i;
    }

    private final void zzbz(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzca(i));
        }
    }

    private final void zzc(int i, float f) {
        int i2;
        a();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzca(i));
        }
        float[] fArr = this.zzbma;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[(((i2 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.zzbma, i, fArr2, i + 1, this.size - i);
            this.zzbma = fArr2;
        }
        this.zzbma[i] = f;
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

    public static zzta zzpk() {
        return zzblz;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Float) obj).floatValue());
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        a();
        zzte.a(collection);
        if (!(collection instanceof zzta)) {
            return super.addAll(collection);
        }
        zzta zzta = (zzta) collection;
        int i = zzta.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.zzbma;
            if (i3 > fArr.length) {
                this.zzbma = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(zzta.zzbma, 0, this.zzbma, this.size, zzta.size);
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
        if (!(obj instanceof zzta)) {
            return super.equals(obj);
        }
        zzta zzta = (zzta) obj;
        if (this.size != zzta.size) {
            return false;
        }
        float[] fArr = zzta.zzbma;
        for (int i = 0; i < this.size; i++) {
            if (Float.floatToIntBits(this.zzbma[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzbz(i);
        return Float.valueOf(this.zzbma[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzbma[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        a();
        zzbz(i);
        float[] fArr = this.zzbma;
        float f = fArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final boolean remove(Object obj) {
        a();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzbma[i]))) {
                float[] fArr = this.zzbma;
                System.arraycopy(fArr, i + 1, fArr, i, (this.size - i) - 1);
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
            float[] fArr = this.zzbma;
            System.arraycopy(fArr, i2, fArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        a();
        zzbz(i);
        float[] fArr = this.zzbma;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zztl zzcb(int i) {
        if (i >= this.size) {
            return new zzta(Arrays.copyOf(this.zzbma, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzt(float f) {
        zzc(this.size, f);
    }
}
