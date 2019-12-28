package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzir implements Cloneable {
    private Object value;
    private zzip<?, ?> zzaop;
    private List<zziy> zzaoq = new ArrayList();

    zzir() {
    }

    private final byte[] toByteArray() {
        byte[] bArr = new byte[a()];
        a(zzio.zzj(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzxc */
    public final zzir clone() {
        Object clone;
        zzir zzir = new zzir();
        try {
            zzir.zzaop = this.zzaop;
            if (this.zzaoq == null) {
                zzir.zzaoq = null;
            } else {
                zzir.zzaoq.addAll(this.zzaoq);
            }
            if (this.value != null) {
                if (this.value instanceof zziw) {
                    clone = (zziw) ((zziw) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    clone = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzir.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        clone = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        clone = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        clone = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        clone = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        clone = ((double[]) this.value).clone();
                    } else if (this.value instanceof zziw[]) {
                        zziw[] zziwArr = (zziw[]) this.value;
                        zziw[] zziwArr2 = new zziw[zziwArr.length];
                        zzir.value = zziwArr2;
                        while (i < zziwArr.length) {
                            zziwArr2[i] = (zziw) zziwArr[i].clone();
                            i++;
                        }
                    }
                }
                zzir.value = clone;
            }
            return zzir;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final int a() {
        if (this.value == null) {
            int i = 0;
            for (zziy next : this.zzaoq) {
                i += zzio.zzbq(next.a) + 0 + next.b.length;
            }
            return i;
        }
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final void a(zzio zzio) {
        if (this.value == null) {
            for (zziy next : this.zzaoq) {
                zzio.zzck(next.a);
                zzio.zzk(next.b);
            }
            return;
        }
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final void a(zziy zziy) {
        List<zziy> list = this.zzaoq;
        if (list != null) {
            list.add(zziy);
            return;
        }
        Object obj = this.value;
        if (obj instanceof zziw) {
            byte[] bArr = zziy.b;
            zzil zzj = zzil.zzj(bArr, 0, bArr.length);
            int zzta = zzj.zzta();
            if (zzta == bArr.length - zzio.zzbj(zzta)) {
                zziw zza = ((zziw) this.value).zza(zzj);
                this.zzaop = this.zzaop;
                this.value = zza;
                this.zzaoq = null;
                return;
            }
            throw zzit.a();
        } else if (obj instanceof zziw[]) {
            Collections.singletonList(zziy);
            throw new NoSuchMethodError();
        } else if (obj instanceof zzgi) {
            Collections.singletonList(zziy);
            throw new NoSuchMethodError();
        } else if (obj instanceof zzgi[]) {
            Collections.singletonList(zziy);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(zziy);
            throw new NoSuchMethodError();
        }
    }

    public final boolean equals(Object obj) {
        List<zziy> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzir)) {
            return false;
        }
        zzir zzir = (zzir) obj;
        if (this.value == null || zzir.value == null) {
            List<zziy> list2 = this.zzaoq;
            if (list2 != null && (list = zzir.zzaoq) != null) {
                return list2.equals(list);
            }
            try {
                return Arrays.equals(toByteArray(), zzir.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            zzip<?, ?> zzip = this.zzaop;
            if (zzip != zzir.zzaop) {
                return false;
            }
            if (!zzip.a.isArray()) {
                return this.value.equals(zzir.value);
            }
            Object obj2 = this.value;
            return obj2 instanceof byte[] ? Arrays.equals((byte[]) obj2, (byte[]) zzir.value) : obj2 instanceof int[] ? Arrays.equals((int[]) obj2, (int[]) zzir.value) : obj2 instanceof long[] ? Arrays.equals((long[]) obj2, (long[]) zzir.value) : obj2 instanceof float[] ? Arrays.equals((float[]) obj2, (float[]) zzir.value) : obj2 instanceof double[] ? Arrays.equals((double[]) obj2, (double[]) zzir.value) : obj2 instanceof boolean[] ? Arrays.equals((boolean[]) obj2, (boolean[]) zzir.value) : Arrays.deepEquals((Object[]) obj2, (Object[]) zzir.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
