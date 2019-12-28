package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import kotlin.jvm.internal.CharCompanionObject;

@GwtCompatible
@Beta
public final class Escapers {
    private static final Escaper NULL_ESCAPER = new CharEscaper() {
        /* access modifiers changed from: protected */
        public char[] a(char c) {
            return null;
        }

        public String escape(String str) {
            return (String) Preconditions.checkNotNull(str);
        }
    };

    @Beta
    public static final class Builder {
        private final Map<Character, String> replacementMap;
        private char safeMax;
        private char safeMin;
        /* access modifiers changed from: private */
        public String unsafeReplacement;

        private Builder() {
            this.replacementMap = new HashMap();
            this.safeMin = 0;
            this.safeMax = CharCompanionObject.MAX_VALUE;
            this.unsafeReplacement = null;
        }

        @CanIgnoreReturnValue
        public Builder addEscape(char c, String str) {
            Preconditions.checkNotNull(str);
            this.replacementMap.put(Character.valueOf(c), str);
            return this;
        }

        public Escaper build() {
            return new ArrayBasedCharEscaper(this.replacementMap, this.safeMin, this.safeMax) {
                private final char[] replacementChars;

                {
                    this.replacementChars = Builder.this.unsafeReplacement != null ? Builder.this.unsafeReplacement.toCharArray() : null;
                }

                /* access modifiers changed from: protected */
                public char[] b(char c) {
                    return this.replacementChars;
                }
            };
        }

        @CanIgnoreReturnValue
        public Builder setSafeRange(char c, char c2) {
            this.safeMin = c;
            this.safeMax = c2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUnsafeReplacement(@Nullable String str) {
            this.unsafeReplacement = str;
            return this;
        }
    }

    private Escapers() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static String computeReplacement(CharEscaper charEscaper, char c) {
        return stringOrNull(charEscaper.a(c));
    }

    public static String computeReplacement(UnicodeEscaper unicodeEscaper, int i) {
        return stringOrNull(unicodeEscaper.a(i));
    }

    public static Escaper nullEscaper() {
        return NULL_ESCAPER;
    }

    private static String stringOrNull(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    private static UnicodeEscaper wrap(final CharEscaper charEscaper) {
        return new UnicodeEscaper() {
            /* access modifiers changed from: protected */
            public char[] a(int i) {
                if (i < 65536) {
                    return charEscaper.a((char) i);
                }
                char[] cArr = new char[2];
                Character.toChars(i, cArr, 0);
                char[] a2 = charEscaper.a(cArr[0]);
                char[] a3 = charEscaper.a(cArr[1]);
                if (a2 == null && a3 == null) {
                    return null;
                }
                int length = a2 != null ? a2.length : 1;
                char[] cArr2 = new char[((a3 != null ? a3.length : 1) + length)];
                if (a2 != null) {
                    for (int i2 = 0; i2 < a2.length; i2++) {
                        cArr2[i2] = a2[i2];
                    }
                } else {
                    cArr2[0] = cArr[0];
                }
                if (a3 != null) {
                    for (int i3 = 0; i3 < a3.length; i3++) {
                        cArr2[length + i3] = a3[i3];
                    }
                } else {
                    cArr2[length] = cArr[1];
                }
                return cArr2;
            }
        };
    }
}
