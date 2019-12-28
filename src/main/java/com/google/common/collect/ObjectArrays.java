package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Array;
import java.util.Collection;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class ObjectArrays {
    static final Object[] a = new Object[0];

    private ObjectArrays() {
    }

    @CanIgnoreReturnValue
    static Object a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + i);
    }

    static Object[] a(Collection<?> collection) {
        return fillArray(collection, new Object[collection.size()]);
    }

    static <T> T[] a(Collection<?> collection, T[] tArr) {
        int size = collection.size();
        if (tArr.length < size) {
            tArr = newArray(tArr, size);
        }
        fillArray(collection, tArr);
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    @CanIgnoreReturnValue
    static Object[] a(Object... objArr) {
        return b(objArr, objArr.length);
    }

    static <T> T[] a(T[] tArr, int i) {
        T[] newArray = newArray(tArr, i);
        System.arraycopy(tArr, 0, newArray, 0, Math.min(tArr.length, i));
        return newArray;
    }

    @CanIgnoreReturnValue
    static Object[] b(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            a(objArr[i2], i2);
        }
        return objArr;
    }

    public static <T> T[] concat(@Nullable T t, T[] tArr) {
        T[] newArray = newArray(tArr, tArr.length + 1);
        newArray[0] = t;
        System.arraycopy(tArr, 0, newArray, 1, tArr.length);
        return newArray;
    }

    public static <T> T[] concat(T[] tArr, @Nullable T t) {
        T[] a2 = a(tArr, tArr.length + 1);
        a2[tArr.length] = t;
        return a2;
    }

    @GwtIncompatible
    public static <T> T[] concat(T[] tArr, T[] tArr2, Class<T> cls) {
        T[] newArray = newArray(cls, tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, newArray, 0, tArr.length);
        System.arraycopy(tArr2, 0, newArray, tArr.length, tArr2.length);
        return newArray;
    }

    @CanIgnoreReturnValue
    private static Object[] fillArray(Iterable<?> iterable, Object[] objArr) {
        int i = 0;
        for (Object obj : iterable) {
            objArr[i] = obj;
            i++;
        }
        return objArr;
    }

    @GwtIncompatible
    public static <T> T[] newArray(Class<T> cls, int i) {
        return (Object[]) Array.newInstance(cls, i);
    }

    public static <T> T[] newArray(T[] tArr, int i) {
        return Platform.a(tArr, i);
    }
}
