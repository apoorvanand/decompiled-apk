package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class Doubles {
    public static final int BYTES = 8;
    @GwtIncompatible
    static final Pattern a = fpPattern();

    @GwtCompatible
    private static class DoubleArrayAsList extends AbstractList<Double> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        final double[] a;
        final int b;
        final int c;

        DoubleArrayAsList(double[] dArr) {
            this(dArr, 0, dArr.length);
        }

        DoubleArrayAsList(double[] dArr, int i, int i2) {
            this.a = dArr;
            this.b = i;
            this.c = i2;
        }

        /* access modifiers changed from: package-private */
        public double[] a() {
            int size = size();
            double[] dArr = new double[size];
            System.arraycopy(this.a, this.b, dArr, 0, size);
            return dArr;
        }

        public boolean contains(Object obj) {
            return (obj instanceof Double) && Doubles.indexOf(this.a, ((Double) obj).doubleValue(), this.b, this.c) != -1;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DoubleArrayAsList)) {
                return super.equals(obj);
            }
            DoubleArrayAsList doubleArrayAsList = (DoubleArrayAsList) obj;
            int size = size();
            if (doubleArrayAsList.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (this.a[this.b + i] != doubleArrayAsList.a[doubleArrayAsList.b + i]) {
                    return false;
                }
            }
            return true;
        }

        public Double get(int i) {
            Preconditions.checkElementIndex(i, size());
            return Double.valueOf(this.a[this.b + i]);
        }

        public int hashCode() {
            int i = 1;
            for (int i2 = this.b; i2 < this.c; i2++) {
                i = (i * 31) + Doubles.hashCode(this.a[i2]);
            }
            return i;
        }

        public int indexOf(Object obj) {
            int a2;
            if (!(obj instanceof Double) || (a2 = Doubles.indexOf(this.a, ((Double) obj).doubleValue(), this.b, this.c)) < 0) {
                return -1;
            }
            return a2 - this.b;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(Object obj) {
            int b2;
            if (!(obj instanceof Double) || (b2 = Doubles.lastIndexOf(this.a, ((Double) obj).doubleValue(), this.b, this.c)) < 0) {
                return -1;
            }
            return b2 - this.b;
        }

        public Double set(int i, Double d) {
            Preconditions.checkElementIndex(i, size());
            double[] dArr = this.a;
            int i2 = this.b;
            double d2 = dArr[i2 + i];
            dArr[i2 + i] = ((Double) Preconditions.checkNotNull(d)).doubleValue();
            return Double.valueOf(d2);
        }

        public int size() {
            return this.c - this.b;
        }

        public List<Double> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            if (i == i2) {
                return Collections.emptyList();
            }
            double[] dArr = this.a;
            int i3 = this.b;
            return new DoubleArrayAsList(dArr, i + i3, i3 + i2);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 12);
            sb.append('[');
            sb.append(this.a[this.b]);
            int i = this.b;
            while (true) {
                i++;
                if (i < this.c) {
                    sb.append(", ");
                    sb.append(this.a[i]);
                } else {
                    sb.append(']');
                    return sb.toString();
                }
            }
        }
    }

    private static final class DoubleConverter extends Converter<String, Double> implements Serializable {
        static final DoubleConverter a = new DoubleConverter();
        private static final long serialVersionUID = 1;

        private DoubleConverter() {
        }

        private Object readResolve() {
            return a;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Double b(String str) {
            return Double.valueOf(str);
        }

        /* access modifiers changed from: protected */
        public String a(Double d) {
            return d.toString();
        }

        public String toString() {
            return "Doubles.stringConverter()";
        }
    }

    private enum LexicographicalComparator implements Comparator<double[]> {
        INSTANCE;

        public int compare(double[] dArr, double[] dArr2) {
            int min = Math.min(dArr.length, dArr2.length);
            for (int i = 0; i < min; i++) {
                int compare = Double.compare(dArr[i], dArr2[i]);
                if (compare != 0) {
                    return compare;
                }
            }
            return dArr.length - dArr2.length;
        }

        public String toString() {
            return "Doubles.lexicographicalComparator()";
        }
    }

    private Doubles() {
    }

    public static List<Double> asList(double... dArr) {
        return dArr.length == 0 ? Collections.emptyList() : new DoubleArrayAsList(dArr);
    }

    public static int compare(double d, double d2) {
        return Double.compare(d, d2);
    }

    public static double[] concat(double[]... dArr) {
        int i = 0;
        for (double[] length : dArr) {
            i += length.length;
        }
        double[] dArr2 = new double[i];
        int i2 = 0;
        for (double[] dArr3 : dArr) {
            System.arraycopy(dArr3, 0, dArr2, i2, dArr3.length);
            i2 += dArr3.length;
        }
        return dArr2;
    }

    public static boolean contains(double[] dArr, double d) {
        for (double d2 : dArr) {
            if (d2 == d) {
                return true;
            }
        }
        return false;
    }

    public static double[] ensureCapacity(double[] dArr, int i, int i2) {
        boolean z = true;
        Preconditions.checkArgument(i >= 0, "Invalid minLength: %s", i);
        if (i2 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "Invalid padding: %s", i2);
        return dArr.length < i ? Arrays.copyOf(dArr, i + i2) : dArr;
    }

    @GwtIncompatible
    private static Pattern fpPattern() {
        return Pattern.compile("[+-]?(?:NaN|Infinity|" + ("(?:\\d++(?:\\.\\d*+)?|\\.\\d++)" + "(?:[eE][+-]?\\d++)?[fFdD]?") + "|" + ("0[xX]" + "(?:\\p{XDigit}++(?:\\.\\p{XDigit}*+)?|\\.\\p{XDigit}++)" + "[pP][+-]?\\d++[fFdD]?") + ")");
    }

    public static int hashCode(double d) {
        return Double.valueOf(d).hashCode();
    }

    public static int indexOf(double[] dArr, double d) {
        return indexOf(dArr, d, 0, dArr.length);
    }

    /* access modifiers changed from: private */
    public static int indexOf(double[] dArr, double d, int i, int i2) {
        while (i < i2) {
            if (dArr[i] == d) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(double[] dArr, double[] dArr2) {
        Preconditions.checkNotNull(dArr, "array");
        Preconditions.checkNotNull(dArr2, "target");
        if (dArr2.length == 0) {
            return 0;
        }
        int i = 0;
        while (i < (dArr.length - dArr2.length) + 1) {
            int i2 = 0;
            while (i2 < dArr2.length) {
                if (dArr[i + i2] != dArr2[i2]) {
                    i++;
                } else {
                    i2++;
                }
            }
            return i;
        }
        return -1;
    }

    public static boolean isFinite(double d) {
        boolean z = true;
        boolean z2 = Double.NEGATIVE_INFINITY < d;
        if (d >= Double.POSITIVE_INFINITY) {
            z = false;
        }
        return z2 & z;
    }

    public static String join(String str, double... dArr) {
        Preconditions.checkNotNull(str);
        if (dArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(dArr.length * 12);
        sb.append(dArr[0]);
        for (int i = 1; i < dArr.length; i++) {
            sb.append(str);
            sb.append(dArr[i]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(double[] dArr, double d) {
        return lastIndexOf(dArr, d, 0, dArr.length);
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(double[] dArr, double d, int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            if (dArr[i3] == d) {
                return i3;
            }
        }
        return -1;
    }

    public static Comparator<double[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static double max(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0);
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            d = Math.max(d, dArr[i]);
        }
        return d;
    }

    public static double min(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0);
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            d = Math.min(d, dArr[i]);
        }
        return d;
    }

    @Beta
    public static Converter<String, Double> stringConverter() {
        return DoubleConverter.a;
    }

    public static double[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof DoubleArrayAsList) {
            return ((DoubleArrayAsList) collection).a();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = ((Number) Preconditions.checkNotNull(array[i])).doubleValue();
        }
        return dArr;
    }

    @GwtIncompatible
    @CheckForNull
    @Nullable
    @Beta
    public static Double tryParse(String str) {
        if (!a.matcher(str).matches()) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
