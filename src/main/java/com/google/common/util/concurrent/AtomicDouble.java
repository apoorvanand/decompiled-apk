package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

@GwtIncompatible
public class AtomicDouble extends Number implements Serializable {
    private static final long serialVersionUID = 0;
    private static final AtomicLongFieldUpdater<AtomicDouble> updater = AtomicLongFieldUpdater.newUpdater(AtomicDouble.class, "value");
    private volatile transient long value;

    public AtomicDouble() {
    }

    public AtomicDouble(double d) {
        this.value = Double.doubleToRawLongBits(d);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        set(objectInputStream.readDouble());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeDouble(get());
    }

    @CanIgnoreReturnValue
    public final double addAndGet(double d) {
        long j;
        double longBitsToDouble;
        do {
            j = this.value;
            longBitsToDouble = Double.longBitsToDouble(j) + d;
        } while (!updater.compareAndSet(this, j, Double.doubleToRawLongBits(longBitsToDouble)));
        return longBitsToDouble;
    }

    public final boolean compareAndSet(double d, double d2) {
        return updater.compareAndSet(this, Double.doubleToRawLongBits(d), Double.doubleToRawLongBits(d2));
    }

    public double doubleValue() {
        return get();
    }

    public float floatValue() {
        return (float) get();
    }

    public final double get() {
        return Double.longBitsToDouble(this.value);
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(double d) {
        long j;
        double longBitsToDouble;
        do {
            j = this.value;
            longBitsToDouble = Double.longBitsToDouble(j);
        } while (!updater.compareAndSet(this, j, Double.doubleToRawLongBits(longBitsToDouble + d)));
        return longBitsToDouble;
    }

    public final double getAndSet(double d) {
        return Double.longBitsToDouble(updater.getAndSet(this, Double.doubleToRawLongBits(d)));
    }

    public int intValue() {
        return (int) get();
    }

    public final void lazySet(double d) {
        set(d);
    }

    public long longValue() {
        return (long) get();
    }

    public final void set(double d) {
        this.value = Double.doubleToRawLongBits(d);
    }

    public String toString() {
        return Double.toString(get());
    }

    public final boolean weakCompareAndSet(double d, double d2) {
        return updater.weakCompareAndSet(this, Double.doubleToRawLongBits(d), Double.doubleToRawLongBits(d2));
    }
}
