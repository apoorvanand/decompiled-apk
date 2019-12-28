package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
@Beta
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    protected UnicodeEscaper() {
    }

    protected static int b(CharSequence charSequence, int i, int i2) {
        Preconditions.checkNotNull(charSequence);
        if (i < i2) {
            int i3 = i + 1;
            char charAt = charSequence.charAt(i);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected low surrogate character '");
                sb.append(charAt);
                sb.append("' with value ");
                sb.append(charAt);
                sb.append(" at index ");
                sb.append(i3 - 1);
                sb.append(" in '");
                sb.append(charSequence);
                sb.append("'");
                throw new IllegalArgumentException(sb.toString());
            } else if (i3 == i2) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i3);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + charAt2 + "' with value " + charAt2 + " at index " + i3 + " in '" + charSequence + "'");
            }
        } else {
            throw new IndexOutOfBoundsException("Index exceeds specified range");
        }
    }

    private static char[] growBuffer(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }

    /* access modifiers changed from: protected */
    public int a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int b = b(charSequence, i, i2);
            if (b < 0 || a(b) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(b) ? 2 : 1;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final String a(String str, int i) {
        int i2;
        int length = str.length();
        char[] a = Platform.a();
        int i3 = 0;
        int i4 = 0;
        while (i < length) {
            int b = b(str, i, length);
            if (b >= 0) {
                char[] a2 = a(b);
                int i5 = (Character.isSupplementaryCodePoint(b) ? 2 : 1) + i;
                if (a2 != null) {
                    int i6 = i - i3;
                    int i7 = i4 + i6;
                    int length2 = a2.length + i7;
                    if (a.length < length2) {
                        a = growBuffer(a, i4, length2 + (length - i) + 32);
                    }
                    if (i6 > 0) {
                        str.getChars(i3, i, a, i4);
                        i4 = i7;
                    }
                    if (a2.length > 0) {
                        System.arraycopy(a2, 0, a, i4, a2.length);
                        i4 += a2.length;
                    }
                    i3 = i5;
                }
                i = a(str, i5, length);
            } else {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
        }
        int i8 = length - i3;
        if (i8 > 0) {
            i2 = i8 + i4;
            if (a.length < i2) {
                a = growBuffer(a, i4, i2);
            }
            str.getChars(i3, length, a, i4);
        } else {
            i2 = i4;
        }
        return new String(a, 0, i2);
    }

    /* access modifiers changed from: protected */
    public abstract char[] a(int i);

    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        int a = a(str, 0, length);
        return a == length ? str : a(str, a);
    }
}
