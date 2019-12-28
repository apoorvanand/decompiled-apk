package com.google.common.io;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public abstract class BaseEncoding {
    private static final BaseEncoding BASE16 = new Base16Encoding("base16()", "0123456789ABCDEF");
    private static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final BaseEncoding BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final BaseEncoding BASE64_URL = new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');

    private static final class Alphabet extends CharMatcher {
        final int a;
        final int b;
        final int c;
        /* access modifiers changed from: private */
        public final char[] chars;
        final int d;
        private final byte[] decodabet;
        private final String name;
        private final boolean[] validPadding;

        Alphabet(String str, char[] cArr) {
            this.name = (String) Preconditions.checkNotNull(str);
            this.chars = (char[]) Preconditions.checkNotNull(cArr);
            try {
                this.b = IntMath.log2(cArr.length, RoundingMode.UNNECESSARY);
                int min = Math.min(8, Integer.lowestOneBit(this.b));
                try {
                    this.c = 8 / min;
                    this.d = this.b / min;
                    this.a = cArr.length - 1;
                    byte[] bArr = new byte[128];
                    Arrays.fill(bArr, (byte) -1);
                    for (int i = 0; i < cArr.length; i++) {
                        char c2 = cArr[i];
                        Preconditions.checkArgument(CharMatcher.ascii().matches(c2), "Non-ASCII character: %s", c2);
                        Preconditions.checkArgument(bArr[c2] == -1, "Duplicate character: %s", c2);
                        bArr[c2] = (byte) i;
                    }
                    this.decodabet = bArr;
                    boolean[] zArr = new boolean[this.c];
                    for (int i2 = 0; i2 < this.d; i2++) {
                        zArr[IntMath.divide(i2 * 8, this.b, RoundingMode.CEILING)] = true;
                    }
                    this.validPadding = zArr;
                } catch (ArithmeticException e) {
                    throw new IllegalArgumentException("Illegal alphabet " + new String(cArr), e);
                }
            } catch (ArithmeticException e2) {
                throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e2);
            }
        }

        private boolean hasLowerCase() {
            for (char isLowerCase : this.chars) {
                if (Ascii.isLowerCase(isLowerCase)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasUpperCase() {
            for (char isUpperCase : this.chars) {
                if (Ascii.isUpperCase(isUpperCase)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public char a(int i) {
            return this.chars[i];
        }

        /* access modifiers changed from: package-private */
        public Alphabet b() {
            if (!hasLowerCase()) {
                return this;
            }
            Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i < cArr2.length) {
                    cArr[i] = Ascii.toUpperCase(cArr2[i]);
                    i++;
                } else {
                    return new Alphabet(this.name + ".upperCase()", cArr);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean b(char c2) {
            return c2 <= 127 && this.decodabet[c2] != -1;
        }

        /* access modifiers changed from: package-private */
        public boolean b(int i) {
            return this.validPadding[i % this.c];
        }

        /* access modifiers changed from: package-private */
        public int c(char c2) {
            Object obj;
            if (c2 <= 127) {
                byte[] bArr = this.decodabet;
                if (bArr[c2] != -1) {
                    return bArr[c2];
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unrecognized character: ");
            if (CharMatcher.invisible().matches(c2)) {
                obj = "0x" + Integer.toHexString(c2);
            } else {
                obj = Character.valueOf(c2);
            }
            sb.append(obj);
            throw new DecodingException(sb.toString());
        }

        /* access modifiers changed from: package-private */
        public Alphabet c() {
            if (!hasUpperCase()) {
                return this;
            }
            Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i < cArr2.length) {
                    cArr[i] = Ascii.toLowerCase(cArr2[i]);
                    i++;
                } else {
                    return new Alphabet(this.name + ".lowerCase()", cArr);
                }
            }
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof Alphabet) {
                return Arrays.equals(this.chars, ((Alphabet) obj).chars);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.chars);
        }

        public boolean matches(char c2) {
            return CharMatcher.ascii().matches(c2) && this.decodabet[c2] != -1;
        }

        public String toString() {
            return this.name;
        }
    }

    static final class Base16Encoding extends StandardBaseEncoding {
        final char[] a;

        private Base16Encoding(Alphabet alphabet) {
            super(alphabet, (Character) null);
            this.a = new char[512];
            Preconditions.checkArgument(alphabet.chars.length == 16);
            for (int i = 0; i < 256; i++) {
                this.a[i] = alphabet.a(i >>> 4);
                this.a[i | 256] = alphabet.a(i & 15);
            }
        }

        Base16Encoding(String str, String str2) {
            this(new Alphabet(str, str2.toCharArray()));
        }

        /* access modifiers changed from: package-private */
        public int a(byte[] bArr, CharSequence charSequence) {
            Preconditions.checkNotNull(bArr);
            if (charSequence.length() % 2 != 1) {
                int i = 0;
                int i2 = 0;
                while (i < charSequence.length()) {
                    bArr[i2] = (byte) ((this.b.c(charSequence.charAt(i)) << 4) | this.b.c(charSequence.charAt(i + 1)));
                    i += 2;
                    i2++;
                }
                return i2;
            }
            throw new DecodingException("Invalid input length " + charSequence.length());
        }

        /* access modifiers changed from: package-private */
        public BaseEncoding a(Alphabet alphabet, @Nullable Character ch) {
            return new Base16Encoding(alphabet);
        }

        /* access modifiers changed from: package-private */
        public void a(Appendable appendable, byte[] bArr, int i, int i2) {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
            for (int i3 = 0; i3 < i2; i3++) {
                byte b = bArr[i + i3] & 255;
                appendable.append(this.a[b]);
                appendable.append(this.a[b | 256]);
            }
        }
    }

    static final class Base64Encoding extends StandardBaseEncoding {
        private Base64Encoding(Alphabet alphabet, @Nullable Character ch) {
            super(alphabet, ch);
            Preconditions.checkArgument(alphabet.chars.length == 64);
        }

        Base64Encoding(String str, String str2, @Nullable Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        /* access modifiers changed from: package-private */
        public int a(byte[] bArr, CharSequence charSequence) {
            Preconditions.checkNotNull(bArr);
            String trimTrailingFrom = a().trimTrailingFrom(charSequence);
            if (this.b.b(trimTrailingFrom.length())) {
                int i = 0;
                int i2 = 0;
                while (i < trimTrailingFrom.length()) {
                    int i3 = i + 1;
                    int i4 = i3 + 1;
                    int c = (this.b.c(trimTrailingFrom.charAt(i)) << 18) | (this.b.c(trimTrailingFrom.charAt(i3)) << 12);
                    int i5 = i2 + 1;
                    bArr[i2] = (byte) (c >>> 16);
                    if (i4 < trimTrailingFrom.length()) {
                        int i6 = i4 + 1;
                        int c2 = c | (this.b.c(trimTrailingFrom.charAt(i4)) << 6);
                        i2 = i5 + 1;
                        bArr[i5] = (byte) ((c2 >>> 8) & 255);
                        if (i6 < trimTrailingFrom.length()) {
                            i4 = i6 + 1;
                            i5 = i2 + 1;
                            bArr[i2] = (byte) ((c2 | this.b.c(trimTrailingFrom.charAt(i6))) & 255);
                        } else {
                            i = i6;
                        }
                    }
                    i2 = i5;
                    i = i4;
                }
                return i2;
            }
            throw new DecodingException("Invalid input length " + trimTrailingFrom.length());
        }

        /* access modifiers changed from: package-private */
        public BaseEncoding a(Alphabet alphabet, @Nullable Character ch) {
            return new Base64Encoding(alphabet, ch);
        }

        /* access modifiers changed from: package-private */
        public void a(Appendable appendable, byte[] bArr, int i, int i2) {
            Preconditions.checkNotNull(appendable);
            int i3 = i + i2;
            Preconditions.checkPositionIndexes(i, i3, bArr.length);
            while (i2 >= 3) {
                int i4 = i + 1;
                int i5 = i4 + 1;
                byte b = ((bArr[i] & 255) << Ascii.DLE) | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
                appendable.append(this.b.a(b >>> Ascii.DC2));
                appendable.append(this.b.a((b >>> Ascii.FF) & 63));
                appendable.append(this.b.a((b >>> 6) & 63));
                appendable.append(this.b.a((int) b & 63));
                i2 -= 3;
                i = i5 + 1;
            }
            if (i < i3) {
                b(appendable, bArr, i, i3 - i);
            }
        }
    }

    public static final class DecodingException extends IOException {
        DecodingException(String str) {
            super(str);
        }
    }

    static final class SeparatedBaseEncoding extends BaseEncoding {
        private final int afterEveryChars;
        private final BaseEncoding delegate;
        private final String separator;
        private final CharMatcher separatorChars;

        SeparatedBaseEncoding(BaseEncoding baseEncoding, String str, int i) {
            this.delegate = (BaseEncoding) Preconditions.checkNotNull(baseEncoding);
            this.separator = (String) Preconditions.checkNotNull(str);
            this.afterEveryChars = i;
            Preconditions.checkArgument(i > 0, "Cannot add a separator after every %s chars", i);
            this.separatorChars = CharMatcher.anyOf(str).precomputed();
        }

        /* access modifiers changed from: package-private */
        public int a(int i) {
            int a = this.delegate.a(i);
            return a + (this.separator.length() * IntMath.divide(Math.max(0, a - 1), this.afterEveryChars, RoundingMode.FLOOR));
        }

        /* access modifiers changed from: package-private */
        public int a(byte[] bArr, CharSequence charSequence) {
            return this.delegate.a(bArr, (CharSequence) this.separatorChars.removeFrom(charSequence));
        }

        /* access modifiers changed from: package-private */
        public CharMatcher a() {
            return this.delegate.a();
        }

        /* access modifiers changed from: package-private */
        public void a(Appendable appendable, byte[] bArr, int i, int i2) {
            this.delegate.a(a(appendable, this.separator, this.afterEveryChars), bArr, i, i2);
        }

        /* access modifiers changed from: package-private */
        public int b(int i) {
            return this.delegate.b(i);
        }

        public boolean canDecode(CharSequence charSequence) {
            return this.delegate.canDecode(this.separatorChars.removeFrom(charSequence));
        }

        @GwtIncompatible
        public InputStream decodingStream(Reader reader) {
            return this.delegate.decodingStream(a(reader, this.separatorChars));
        }

        @GwtIncompatible
        public OutputStream encodingStream(Writer writer) {
            return this.delegate.encodingStream(a(writer, this.separator, this.afterEveryChars));
        }

        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        public String toString() {
            return this.delegate + ".withSeparator(\"" + this.separator + "\", " + this.afterEveryChars + ")";
        }

        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withPadChar(char c) {
            return this.delegate.withPadChar(c).withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withSeparator(String str, int i) {
            throw new UnsupportedOperationException("Already have a separator");
        }
    }

    static class StandardBaseEncoding extends BaseEncoding {
        final Alphabet b;
        @Nullable
        final Character c;
        private transient BaseEncoding lowerCase;
        private transient BaseEncoding upperCase;

        StandardBaseEncoding(Alphabet alphabet, @Nullable Character ch) {
            this.b = (Alphabet) Preconditions.checkNotNull(alphabet);
            Preconditions.checkArgument(ch == null || !alphabet.matches(ch.charValue()), "Padding character %s was already in alphabet", (Object) ch);
            this.c = ch;
        }

        StandardBaseEncoding(String str, String str2, @Nullable Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        /* access modifiers changed from: package-private */
        public int a(int i) {
            return this.b.c * IntMath.divide(i, this.b.d, RoundingMode.CEILING);
        }

        /* access modifiers changed from: package-private */
        public int a(byte[] bArr, CharSequence charSequence) {
            Preconditions.checkNotNull(bArr);
            String trimTrailingFrom = a().trimTrailingFrom(charSequence);
            if (this.b.b(trimTrailingFrom.length())) {
                int i = 0;
                int i2 = 0;
                while (i < trimTrailingFrom.length()) {
                    long j = 0;
                    int i3 = 0;
                    for (int i4 = 0; i4 < this.b.c; i4++) {
                        j <<= this.b.b;
                        if (i + i4 < trimTrailingFrom.length()) {
                            j |= (long) this.b.c(trimTrailingFrom.charAt(i3 + i));
                            i3++;
                        }
                    }
                    int i5 = (this.b.d * 8) - (i3 * this.b.b);
                    int i6 = (this.b.d - 1) * 8;
                    while (i6 >= i5) {
                        bArr[i2] = (byte) ((int) ((j >>> i6) & 255));
                        i6 -= 8;
                        i2++;
                    }
                    i += this.b.c;
                }
                return i2;
            }
            throw new DecodingException("Invalid input length " + trimTrailingFrom.length());
        }

        /* access modifiers changed from: package-private */
        public CharMatcher a() {
            Character ch = this.c;
            return ch == null ? CharMatcher.none() : CharMatcher.is(ch.charValue());
        }

        /* access modifiers changed from: package-private */
        public BaseEncoding a(Alphabet alphabet, @Nullable Character ch) {
            return new StandardBaseEncoding(alphabet, ch);
        }

        /* access modifiers changed from: package-private */
        public void a(Appendable appendable, byte[] bArr, int i, int i2) {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
            int i3 = 0;
            while (i3 < i2) {
                b(appendable, bArr, i + i3, Math.min(this.b.d, i2 - i3));
                i3 += this.b.d;
            }
        }

        /* access modifiers changed from: package-private */
        public int b(int i) {
            return (int) (((((long) this.b.b) * ((long) i)) + 7) / 8);
        }

        /* access modifiers changed from: package-private */
        public void b(Appendable appendable, byte[] bArr, int i, int i2) {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
            int i3 = 0;
            Preconditions.checkArgument(i2 <= this.b.d);
            long j = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                j = (j | ((long) (bArr[i + i4] & 255))) << 8;
            }
            int i5 = ((i2 + 1) * 8) - this.b.b;
            while (i3 < i2 * 8) {
                appendable.append(this.b.a(((int) (j >>> (i5 - i3))) & this.b.a));
                i3 += this.b.b;
            }
            if (this.c != null) {
                while (i3 < this.b.d * 8) {
                    appendable.append(this.c.charValue());
                    i3 += this.b.b;
                }
            }
        }

        public boolean canDecode(CharSequence charSequence) {
            String trimTrailingFrom = a().trimTrailingFrom(charSequence);
            if (!this.b.b(trimTrailingFrom.length())) {
                return false;
            }
            for (int i = 0; i < trimTrailingFrom.length(); i++) {
                if (!this.b.b(trimTrailingFrom.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        @GwtIncompatible
        public InputStream decodingStream(final Reader reader) {
            Preconditions.checkNotNull(reader);
            return new InputStream() {
                int a = 0;
                int b = 0;
                int c = 0;
                boolean d = false;
                final CharMatcher e = StandardBaseEncoding.this.a();

                public void close() {
                    reader.close();
                }

                public int read() {
                    while (true) {
                        int read = reader.read();
                        if (read != -1) {
                            this.c++;
                            char c2 = (char) read;
                            if (this.e.matches(c2)) {
                                if (this.d || (this.c != 1 && StandardBaseEncoding.this.b.b(this.c - 1))) {
                                    this.d = true;
                                }
                            } else if (!this.d) {
                                this.a <<= StandardBaseEncoding.this.b.b;
                                this.a = StandardBaseEncoding.this.b.c(c2) | this.a;
                                this.b += StandardBaseEncoding.this.b.b;
                                int i = this.b;
                                if (i >= 8) {
                                    this.b = i - 8;
                                    return (this.a >> this.b) & 255;
                                }
                            } else {
                                throw new DecodingException("Expected padding character but found '" + c2 + "' at index " + this.c);
                            }
                        } else if (this.d || StandardBaseEncoding.this.b.b(this.c)) {
                            return -1;
                        } else {
                            throw new DecodingException("Invalid input length " + this.c);
                        }
                    }
                    throw new DecodingException("Padding cannot start at index " + this.c);
                }
            };
        }

        @GwtIncompatible
        public OutputStream encodingStream(final Writer writer) {
            Preconditions.checkNotNull(writer);
            return new OutputStream() {
                int a = 0;
                int b = 0;
                int c = 0;

                public void close() {
                    if (this.b > 0) {
                        writer.write(StandardBaseEncoding.this.b.a((this.a << (StandardBaseEncoding.this.b.b - this.b)) & StandardBaseEncoding.this.b.a));
                        this.c++;
                        if (StandardBaseEncoding.this.c != null) {
                            while (this.c % StandardBaseEncoding.this.b.c != 0) {
                                writer.write(StandardBaseEncoding.this.c.charValue());
                                this.c++;
                            }
                        }
                    }
                    writer.close();
                }

                public void flush() {
                    writer.flush();
                }

                public void write(int i) {
                    this.a <<= 8;
                    this.a = (i & 255) | this.a;
                    int i2 = this.b + 8;
                    while (true) {
                        this.b = i2;
                        if (this.b >= StandardBaseEncoding.this.b.b) {
                            writer.write(StandardBaseEncoding.this.b.a((this.a >> (this.b - StandardBaseEncoding.this.b.b)) & StandardBaseEncoding.this.b.a));
                            this.c++;
                            i2 = this.b - StandardBaseEncoding.this.b.b;
                        } else {
                            return;
                        }
                    }
                }
            };
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof StandardBaseEncoding)) {
                return false;
            }
            StandardBaseEncoding standardBaseEncoding = (StandardBaseEncoding) obj;
            return this.b.equals(standardBaseEncoding.b) && Objects.equal(this.c, standardBaseEncoding.c);
        }

        public int hashCode() {
            return this.b.hashCode() ^ Objects.hashCode(this.c);
        }

        public BaseEncoding lowerCase() {
            BaseEncoding baseEncoding = this.lowerCase;
            if (baseEncoding == null) {
                Alphabet c2 = this.b.c();
                baseEncoding = c2 == this.b ? this : a(c2, this.c);
                this.lowerCase = baseEncoding;
            }
            return baseEncoding;
        }

        public BaseEncoding omitPadding() {
            return this.c == null ? this : a(this.b, (Character) null);
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.b.toString());
            if (8 % this.b.b != 0) {
                if (this.c == null) {
                    str = ".omitPadding()";
                } else {
                    sb.append(".withPadChar('");
                    sb.append(this.c);
                    str = "')";
                }
                sb.append(str);
            }
            return sb.toString();
        }

        public BaseEncoding upperCase() {
            BaseEncoding baseEncoding = this.upperCase;
            if (baseEncoding == null) {
                Alphabet b2 = this.b.b();
                baseEncoding = b2 == this.b ? this : a(b2, this.c);
                this.upperCase = baseEncoding;
            }
            return baseEncoding;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
            r0 = r2.c;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.common.io.BaseEncoding withPadChar(char r3) {
            /*
                r2 = this;
                com.google.common.io.BaseEncoding$Alphabet r0 = r2.b
                int r0 = r0.b
                r1 = 8
                int r1 = r1 % r0
                if (r1 == 0) goto L_0x001f
                java.lang.Character r0 = r2.c
                if (r0 == 0) goto L_0x0014
                char r0 = r0.charValue()
                if (r0 != r3) goto L_0x0014
                goto L_0x001f
            L_0x0014:
                com.google.common.io.BaseEncoding$Alphabet r0 = r2.b
                java.lang.Character r3 = java.lang.Character.valueOf(r3)
                com.google.common.io.BaseEncoding r3 = r2.a((com.google.common.io.BaseEncoding.Alphabet) r0, (java.lang.Character) r3)
                return r3
            L_0x001f:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding.StandardBaseEncoding.withPadChar(char):com.google.common.io.BaseEncoding");
        }

        public BaseEncoding withSeparator(String str, int i) {
            Preconditions.checkArgument(a().or(this.b).matchesNoneOf(str), "Separator (%s) cannot contain alphabet or padding characters", (Object) str);
            return new SeparatedBaseEncoding(this, str, i);
        }
    }

    BaseEncoding() {
    }

    @GwtIncompatible
    static Reader a(final Reader reader, final CharMatcher charMatcher) {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(charMatcher);
        return new Reader() {
            public void close() {
                reader.close();
            }

            /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: 
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                */
            public int read() {
                /*
                    r3 = this;
                L_0x0000:
                    java.io.Reader r0 = r1
                    int r0 = r0.read()
                    r1 = -1
                    if (r0 == r1) goto L_0x0012
                    com.google.common.base.CharMatcher r1 = r2
                    char r2 = (char) r0
                    boolean r1 = r1.matches(r2)
                    if (r1 != 0) goto L_0x0000
                L_0x0012:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding.AnonymousClass3.read():int");
            }

            public int read(char[] cArr, int i, int i2) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @GwtIncompatible
    static Writer a(final Writer writer, String str, int i) {
        final Appendable a = a((Appendable) writer, str, i);
        return new Writer() {
            public void close() {
                writer.close();
            }

            public void flush() {
                writer.flush();
            }

            public void write(int i) {
                a.append((char) i);
            }

            public void write(char[] cArr, int i, int i2) {
                throw new UnsupportedOperationException();
            }
        };
    }

    static Appendable a(final Appendable appendable, final String str, final int i) {
        Preconditions.checkNotNull(appendable);
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(i > 0);
        return new Appendable() {
            int a = i;

            public Appendable append(char c2) {
                if (this.a == 0) {
                    appendable.append(str);
                    this.a = i;
                }
                appendable.append(c2);
                this.a--;
                return this;
            }

            public Appendable append(CharSequence charSequence) {
                throw new UnsupportedOperationException();
            }

            public Appendable append(CharSequence charSequence, int i, int i2) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    private static byte[] extract(byte[] bArr, int i) {
        if (i == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    /* access modifiers changed from: package-private */
    public abstract int a(int i);

    /* access modifiers changed from: package-private */
    public abstract int a(byte[] bArr, CharSequence charSequence);

    /* access modifiers changed from: package-private */
    public abstract CharMatcher a();

    /* access modifiers changed from: package-private */
    public abstract void a(Appendable appendable, byte[] bArr, int i, int i2);

    /* access modifiers changed from: package-private */
    public final byte[] a(CharSequence charSequence) {
        String trimTrailingFrom = a().trimTrailingFrom(charSequence);
        byte[] bArr = new byte[b(trimTrailingFrom.length())];
        return extract(bArr, a(bArr, (CharSequence) trimTrailingFrom));
    }

    /* access modifiers changed from: package-private */
    public abstract int b(int i);

    public abstract boolean canDecode(CharSequence charSequence);

    public final byte[] decode(CharSequence charSequence) {
        try {
            return a(charSequence);
        } catch (DecodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @GwtIncompatible
    public final ByteSource decodingSource(final CharSource charSource) {
        Preconditions.checkNotNull(charSource);
        return new ByteSource() {
            public InputStream openStream() {
                return BaseEncoding.this.decodingStream(charSource.openStream());
            }
        };
    }

    @GwtIncompatible
    public abstract InputStream decodingStream(Reader reader);

    public String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public final String encode(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        StringBuilder sb = new StringBuilder(a(i2));
        try {
            a(sb, bArr, i, i2);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    @GwtIncompatible
    public final ByteSink encodingSink(final CharSink charSink) {
        Preconditions.checkNotNull(charSink);
        return new ByteSink() {
            public OutputStream openStream() {
                return BaseEncoding.this.encodingStream(charSink.openStream());
            }
        };
    }

    @GwtIncompatible
    public abstract OutputStream encodingStream(Writer writer);

    public abstract BaseEncoding lowerCase();

    public abstract BaseEncoding omitPadding();

    public abstract BaseEncoding upperCase();

    public abstract BaseEncoding withPadChar(char c);

    public abstract BaseEncoding withSeparator(String str, int i);
}
