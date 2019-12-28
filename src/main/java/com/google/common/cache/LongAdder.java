package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.cache.Striped64;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@GwtCompatible(emulated = true)
final class LongAdder extends Striped64 implements LongAddable, Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.f = 0;
        this.d = null;
        this.e = objectInputStream.readLong();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeLong(sum());
    }

    /* access modifiers changed from: package-private */
    public final long a(long j, long j2) {
        return j + j2;
    }

    public void add(long j) {
        int length;
        Striped64.Cell cell;
        Striped64.Cell[] cellArr = this.d;
        if (cellArr == null) {
            long j2 = this.e;
            if (b(j2, j2 + j)) {
                return;
            }
        }
        int[] iArr = (int[]) a.get();
        boolean z = true;
        if (!(iArr == null || cellArr == null || (length = cellArr.length) < 1 || (cell = cellArr[(length - 1) & iArr[0]]) == null)) {
            long j3 = cell.a;
            z = cell.a(j3, j3 + j);
            if (z) {
                return;
            }
        }
        a(j, iArr, z);
    }

    public void decrement() {
        add(-1);
    }

    public double doubleValue() {
        return (double) sum();
    }

    public float floatValue() {
        return (float) sum();
    }

    public void increment() {
        add(1);
    }

    public int intValue() {
        return (int) sum();
    }

    public long longValue() {
        return sum();
    }

    public void reset() {
        a(0);
    }

    public long sum() {
        long j = this.e;
        Striped64.Cell[] cellArr = this.d;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j += cell.a;
                }
            }
        }
        return j;
    }

    public long sumThenReset() {
        long j = this.e;
        Striped64.Cell[] cellArr = this.d;
        this.e = 0;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j += cell.a;
                    cell.a = 0;
                }
            }
        }
        return j;
    }

    public String toString() {
        return Long.toString(sum());
    }
}
