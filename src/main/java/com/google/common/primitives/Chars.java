package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.Nullable;
import kotlin.jvm.internal.CharCompanionObject;

@GwtCompatible(emulated = true)
public final class Chars {
    public static final int BYTES = 2;

    @GwtCompatible
    private static class CharArrayAsList extends AbstractList<Character> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        final char[] a;
        final int b;
        final int c;

        CharArrayAsList(char[] cArr) {
            this(cArr, 0, cArr.length);
        }

        CharArrayAsList(char[] cArr, int i, int i2) {
            this.a = cArr;
            this.b = i;
            this.c = i2;
        }

        /* access modifiers changed from: package-private */
        public char[] a() {
            int size = size();
            char[] cArr = new char[size];
            System.arraycopy(this.a, this.b, cArr, 0, size);
            return cArr;
        }

        public boolean contains(Object obj) {
            return (obj instanceof Character) && Chars.indexOf(this.a, ((Character) obj).charValue(), this.b, this.c) != -1;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CharArrayAsList)) {
                return super.equals(obj);
            }
            CharArrayAsList charArrayAsList = (CharArrayAsList) obj;
            int size = size();
            if (charArrayAsList.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (this.a[this.b + i] != charArrayAsList.a[charArrayAsList.b + i]) {
                    return false;
                }
            }
            return true;
        }

        public Character get(int i) {
            Preconditions.checkElementIndex(i, size());
            return Character.valueOf(this.a[this.b + i]);
        }

        public int hashCode() {
            int i = 1;
            for (int i2 = this.b; i2 < this.c; i2++) {
                i = (i * 31) + Chars.hashCode(this.a[i2]);
            }
            return i;
        }

        public int indexOf(Object obj) {
            int a2;
            if (!(obj instanceof Character) || (a2 = Chars.indexOf(this.a, ((Character) obj).charValue(), this.b, this.c)) < 0) {
                return -1;
            }
            return a2 - this.b;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(Object obj) {
            int b2;
            if (!(obj instanceof Character) || (b2 = Chars.lastIndexOf(this.a, ((Character) obj).charValue(), this.b, this.c)) < 0) {
                return -1;
            }
            return b2 - this.b;
        }

        public Character set(int i, Character ch) {
            Preconditions.checkElementIndex(i, size());
            char[] cArr = this.a;
            int i2 = this.b;
            char c2 = cArr[i2 + i];
            cArr[i2 + i] = ((Character) Preconditions.checkNotNull(ch)).charValue();
            return Character.valueOf(c2);
        }

        public int size() {
            return this.c - this.b;
        }

        public List<Character> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            if (i == i2) {
                return Collections.emptyList();
            }
            char[] cArr = this.a;
            int i3 = this.b;
            return new CharArrayAsList(cArr, i + i3, i3 + i2);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 3);
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

    private enum LexicographicalComparator implements Comparator<char[]> {
        INSTANCE;

        public int compare(char[] cArr, char[] cArr2) {
            int min = Math.min(cArr.length, cArr2.length);
            for (int i = 0; i < min; i++) {
                int compare = Chars.compare(cArr[i], cArr2[i]);
                if (compare != 0) {
                    return compare;
                }
            }
            return cArr.length - cArr2.length;
        }

        public String toString() {
            return "Chars.lexicographicalComparator()";
        }
    }

    private Chars() {
    }

    public static List<Character> asList(char... cArr) {
        return cArr.length == 0 ? Collections.emptyList() : new CharArrayAsList(cArr);
    }

    public static char checkedCast(long j) {
        char c = (char) ((int) j);
        if (((long) c) == j) {
            return c;
        }
        throw new IllegalArgumentException("Out of range: " + j);
    }

    public static int compare(char c, char c2) {
        return c - c2;
    }

    public static char[] concat(char[]... cArr) {
        int i = 0;
        for (char[] length : cArr) {
            i += length.length;
        }
        char[] cArr2 = new char[i];
        int i2 = 0;
        for (char[] cArr3 : cArr) {
            System.arraycopy(cArr3, 0, cArr2, i2, cArr3.length);
            i2 += cArr3.length;
        }
        return cArr2;
    }

    public static boolean contains(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public static char[] ensureCapacity(char[] cArr, int i, int i2) {
        boolean z = true;
        Preconditions.checkArgument(i >= 0, "Invalid minLength: %s", i);
        if (i2 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "Invalid padding: %s", i2);
        return cArr.length < i ? Arrays.copyOf(cArr, i + i2) : cArr;
    }

    @GwtIncompatible
    public static char fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    @GwtIncompatible
    public static char fromBytes(byte b, byte b2) {
        return (char) ((b << 8) | (b2 & 255));
    }

    public static int hashCode(char c) {
        return c;
    }

    public static int indexOf(char[] cArr, char c) {
        return indexOf(cArr, c, 0, cArr.length);
    }

    /* access modifiers changed from: private */
    public static int indexOf(char[] cArr, char c, int i, int i2) {
        while (i < i2) {
            if (cArr[i] == c) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(char[] cArr, char[] cArr2) {
        Preconditions.checkNotNull(cArr, "array");
        Preconditions.checkNotNull(cArr2, "target");
        if (cArr2.length == 0) {
            return 0;
        }
        int i = 0;
        while (i < (cArr.length - cArr2.length) + 1) {
            int i2 = 0;
            while (i2 < cArr2.length) {
                if (cArr[i + i2] != cArr2[i2]) {
                    i++;
                } else {
                    i2++;
                }
            }
            return i;
        }
        return -1;
    }

    public static String join(String str, char... cArr) {
        Preconditions.checkNotNull(str);
        int length = cArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder((str.length() * (length - 1)) + length);
        sb.append(cArr[0]);
        for (int i = 1; i < length; i++) {
            sb.append(str);
            sb.append(cArr[i]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(char[] cArr, char c) {
        return lastIndexOf(cArr, c, 0, cArr.length);
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(char[] cArr, char c, int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            if (cArr[i3] == c) {
                return i3;
            }
        }
        return -1;
    }

    public static Comparator<char[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static char max(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c = cArr[0];
        for (int i = 1; i < cArr.length; i++) {
            if (cArr[i] > c) {
                c = cArr[i];
            }
        }
        return c;
    }

    public static char min(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c = cArr[0];
        for (int i = 1; i < cArr.length; i++) {
            if (cArr[i] < c) {
                c = cArr[i];
            }
        }
        return c;
    }

    public static char saturatedCast(long j) {
        if (j > 65535) {
            return CharCompanionObject.MAX_VALUE;
        }
        if (j < 0) {
            return 0;
        }
        return (char) ((int) j);
    }

    public static char[] toArray(Collection<Character> collection) {
        if (collection instanceof CharArrayAsList) {
            return ((CharArrayAsList) collection).a();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = ((Character) Preconditions.checkNotNull(array[i])).charValue();
        }
        return cArr;
    }

    @GwtIncompatible
    public static byte[] toByteArray(char c) {
        return new byte[]{(byte) (c >> 8), (byte) c};
    }
}
