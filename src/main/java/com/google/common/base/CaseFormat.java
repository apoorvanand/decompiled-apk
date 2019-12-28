package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import javax.annotation.Nullable;

@GwtCompatible
public enum CaseFormat {
    LOWER_HYPHEN(CharMatcher.is('-'), "-") {
        /* access modifiers changed from: package-private */
        public String a(CaseFormat caseFormat, String str) {
            return caseFormat == LOWER_UNDERSCORE ? str.replace('-', '_') : caseFormat == UPPER_UNDERSCORE ? Ascii.toUpperCase(str.replace('-', '_')) : CaseFormat.super.a(caseFormat, str);
        }

        /* access modifiers changed from: package-private */
        public String a(String str) {
            return Ascii.toLowerCase(str);
        }
    },
    LOWER_UNDERSCORE(CharMatcher.is('_'), "_") {
        /* access modifiers changed from: package-private */
        public String a(CaseFormat caseFormat, String str) {
            return caseFormat == LOWER_HYPHEN ? str.replace('_', '-') : caseFormat == UPPER_UNDERSCORE ? Ascii.toUpperCase(str) : CaseFormat.super.a(caseFormat, str);
        }

        /* access modifiers changed from: package-private */
        public String a(String str) {
            return Ascii.toLowerCase(str);
        }
    },
    LOWER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
        /* access modifiers changed from: package-private */
        public String a(String str) {
            return CaseFormat.firstCharOnlyToUpper(str);
        }
    },
    UPPER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
        /* access modifiers changed from: package-private */
        public String a(String str) {
            return CaseFormat.firstCharOnlyToUpper(str);
        }
    },
    UPPER_UNDERSCORE(CharMatcher.is('_'), "_") {
        /* access modifiers changed from: package-private */
        public String a(CaseFormat caseFormat, String str) {
            return caseFormat == LOWER_HYPHEN ? Ascii.toLowerCase(str.replace('_', '-')) : caseFormat == LOWER_UNDERSCORE ? Ascii.toLowerCase(str) : CaseFormat.super.a(caseFormat, str);
        }

        /* access modifiers changed from: package-private */
        public String a(String str) {
            return Ascii.toUpperCase(str);
        }
    };
    
    private final CharMatcher wordBoundary;
    private final String wordSeparator;

    private static final class StringConverter extends Converter<String, String> implements Serializable {
        private static final long serialVersionUID = 0;
        private final CaseFormat sourceFormat;
        private final CaseFormat targetFormat;

        StringConverter(CaseFormat caseFormat, CaseFormat caseFormat2) {
            this.sourceFormat = (CaseFormat) Preconditions.checkNotNull(caseFormat);
            this.targetFormat = (CaseFormat) Preconditions.checkNotNull(caseFormat2);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String b(String str) {
            return this.sourceFormat.to(this.targetFormat, str);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public String a(String str) {
            return this.targetFormat.to(this.sourceFormat, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof StringConverter)) {
                return false;
            }
            StringConverter stringConverter = (StringConverter) obj;
            return this.sourceFormat.equals(stringConverter.sourceFormat) && this.targetFormat.equals(stringConverter.targetFormat);
        }

        public int hashCode() {
            return this.sourceFormat.hashCode() ^ this.targetFormat.hashCode();
        }

        public String toString() {
            return this.sourceFormat + ".converterTo(" + this.targetFormat + ")";
        }
    }

    private CaseFormat(CharMatcher charMatcher, String str) {
        this.wordBoundary = charMatcher;
        this.wordSeparator = str;
    }

    /* access modifiers changed from: private */
    public static String firstCharOnlyToUpper(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return Ascii.toUpperCase(str.charAt(0)) + Ascii.toLowerCase(str.substring(1));
    }

    private String normalizeFirstWord(String str) {
        return this == LOWER_CAMEL ? Ascii.toLowerCase(str) : a(str);
    }

    /* access modifiers changed from: package-private */
    public String a(CaseFormat caseFormat, String str) {
        String str2;
        int i = 0;
        StringBuilder sb = null;
        int i2 = -1;
        while (true) {
            i2 = this.wordBoundary.indexIn(str, i2 + 1);
            if (i2 == -1) {
                break;
            }
            if (i == 0) {
                sb = new StringBuilder(str.length() + (this.wordSeparator.length() * 4));
                str2 = caseFormat.normalizeFirstWord(str.substring(i, i2));
            } else {
                str2 = caseFormat.a(str.substring(i, i2));
            }
            sb.append(str2);
            sb.append(caseFormat.wordSeparator);
            i = this.wordSeparator.length() + i2;
        }
        if (i == 0) {
            return caseFormat.normalizeFirstWord(str);
        }
        sb.append(caseFormat.a(str.substring(i)));
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public abstract String a(String str);

    public Converter<String, String> converterTo(CaseFormat caseFormat) {
        return new StringConverter(this, caseFormat);
    }

    public final String to(CaseFormat caseFormat, String str) {
        Preconditions.checkNotNull(caseFormat);
        Preconditions.checkNotNull(str);
        return caseFormat == this ? str : a(caseFormat, str);
    }
}
