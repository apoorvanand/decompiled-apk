package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class zzhe<K, V> extends AbstractMap<K, V> implements Cloneable {
    int a;
    private Object[] zzye;

    final class zza implements Map.Entry<K, V> {
        private int index;

        zza(int i) {
            this.index = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return zzkt.equal(getKey(), entry.getKey()) && zzkt.equal(getValue(), entry.getValue());
        }

        public final K getKey() {
            return zzhe.this.zzac(this.index);
        }

        public final V getValue() {
            return zzhe.this.zzad(this.index);
        }

        public final int hashCode() {
            Object key = getKey();
            Object value = getValue();
            int i = 0;
            int hashCode = key != null ? key.hashCode() : 0;
            if (value != null) {
                i = value.hashCode();
            }
            return hashCode ^ i;
        }

        public final V setValue(V v) {
            return zzhe.this.set(this.index, v);
        }
    }

    final class zzb implements Iterator<Map.Entry<K, V>> {
        private boolean zzyg;
        private int zzyh;

        zzb() {
        }

        public final boolean hasNext() {
            return this.zzyh < zzhe.this.a;
        }

        public final /* synthetic */ Object next() {
            int i = this.zzyh;
            if (i != zzhe.this.a) {
                this.zzyh++;
                this.zzyg = false;
                return new zza(i);
            }
            throw new NoSuchElementException();
        }

        public final void remove() {
            int i = this.zzyh - 1;
            if (this.zzyg || i < 0) {
                throw new IllegalArgumentException();
            }
            zzhe.this.remove(i);
            this.zzyh--;
            this.zzyg = true;
        }
    }

    final class zzc extends AbstractSet<Map.Entry<K, V>> {
        zzc() {
        }

        public final Iterator<Map.Entry<K, V>> iterator() {
            return new zzb();
        }

        public final int size() {
            return zzhe.this.a;
        }
    }

    private final V zzae(int i) {
        if (i < 0) {
            return null;
        }
        return this.zzye[i];
    }

    private final V zzaf(int i) {
        int i2 = this.a << 1;
        if (i < 0 || i >= i2) {
            return null;
        }
        V zzae = zzae(i + 1);
        Object[] objArr = this.zzye;
        int i3 = (i2 - i) - 2;
        if (i3 != 0) {
            System.arraycopy(objArr, i + 2, objArr, i, i3);
        }
        this.a--;
        zzb(i2 - 2, (Object) null, (Object) null);
        return zzae;
    }

    private final void zzb(int i, K k, V v) {
        Object[] objArr = this.zzye;
        objArr[i] = k;
        objArr[i + 1] = v;
    }

    private final int zze(Object obj) {
        int i = this.a << 1;
        Object[] objArr = this.zzye;
        int i2 = 0;
        while (i2 < i) {
            Object obj2 = objArr[i2];
            if (obj == null) {
                if (obj2 != null) {
                    i2 += 2;
                }
            } else if (!obj.equals(obj2)) {
                i2 += 2;
            }
            return i2;
        }
        return -2;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzgj */
    public final zzhe<K, V> clone() {
        try {
            zzhe<K, V> zzhe = (zzhe) super.clone();
            Object[] objArr = this.zzye;
            if (objArr != null) {
                int length = objArr.length;
                Object[] objArr2 = new Object[length];
                zzhe.zzye = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, length);
            }
            return zzhe;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void clear() {
        this.a = 0;
        this.zzye = null;
    }

    public final boolean containsKey(Object obj) {
        return -2 != zze(obj);
    }

    public final boolean containsValue(Object obj) {
        int i = this.a << 1;
        Object[] objArr = this.zzye;
        int i2 = 1;
        while (i2 < i) {
            Object obj2 = objArr[i2];
            if (obj == null) {
                if (obj2 != null) {
                    i2 += 2;
                }
            } else if (!obj.equals(obj2)) {
                i2 += 2;
            }
            return true;
        }
        return false;
    }

    public final Set<Map.Entry<K, V>> entrySet() {
        return new zzc();
    }

    public final V get(Object obj) {
        return zzae(zze(obj) + 1);
    }

    public final V put(K k, V v) {
        int zze = zze(k) >> 1;
        if (zze == -1) {
            zze = this.a;
        }
        if (zze >= 0) {
            int i = zze + 1;
            if (i >= 0) {
                Object[] objArr = this.zzye;
                int i2 = i << 1;
                int length = objArr == null ? 0 : objArr.length;
                if (i2 > length) {
                    int i3 = ((length / 2) * 3) + 1;
                    if (i3 % 2 != 0) {
                        i3++;
                    }
                    if (i3 < i2) {
                        i3 = i2;
                    }
                    if (i3 == 0) {
                        this.zzye = null;
                    } else {
                        int i4 = this.a;
                        Object[] objArr2 = this.zzye;
                        if (i4 == 0 || i3 != objArr2.length) {
                            Object[] objArr3 = new Object[i3];
                            this.zzye = objArr3;
                            if (i4 != 0) {
                                System.arraycopy(objArr2, 0, objArr3, 0, i4 << 1);
                            }
                        }
                    }
                }
                int i5 = zze << 1;
                V zzae = zzae(i5 + 1);
                zzb(i5, k, v);
                if (i > this.a) {
                    this.a = i;
                }
                return zzae;
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IndexOutOfBoundsException();
    }

    public final V remove(int i) {
        return zzaf(i << 1);
    }

    public final V remove(Object obj) {
        return zzaf(zze(obj));
    }

    public final V set(int i, V v) {
        int i2 = this.a;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = (i << 1) + 1;
        V zzae = zzae(i3);
        this.zzye[i3] = v;
        return zzae;
    }

    public final int size() {
        return this.a;
    }

    public final K zzac(int i) {
        if (i < 0 || i >= this.a) {
            return null;
        }
        return this.zzye[i << 1];
    }

    public final V zzad(int i) {
        if (i < 0 || i >= this.a) {
            return null;
        }
        return zzae((i << 1) + 1);
    }
}
