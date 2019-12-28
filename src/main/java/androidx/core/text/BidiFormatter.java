package androidx.core.text;

import android.text.SpannableStringBuilder;
import com.google.common.base.Ascii;
import java.util.Locale;
import kotlin.text.Typography;

public final class BidiFormatter {
    private static final int DEFAULT_FLAGS = 2;
    private static final int DIR_LTR = -1;
    private static final int DIR_RTL = 1;
    private static final int DIR_UNKNOWN = 0;
    private static final String EMPTY_STRING = "";
    private static final int FLAG_STEREO_RESET = 2;
    private static final char LRE = '‪';
    private static final char LRM = '‎';
    private static final String LRM_STRING = Character.toString(LRM);
    private static final char PDF = '‬';
    private static final char RLE = '‫';
    private static final char RLM = '‏';
    private static final String RLM_STRING = Character.toString(RLM);
    static final TextDirectionHeuristicCompat a = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
    static final BidiFormatter b = new BidiFormatter(false, 2, a);
    static final BidiFormatter c = new BidiFormatter(true, 2, a);
    private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;

    public static final class Builder {
        private int mFlags;
        private boolean mIsRtlContext;
        private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

        public Builder() {
            initialize(BidiFormatter.a(Locale.getDefault()));
        }

        public Builder(Locale locale) {
            initialize(BidiFormatter.a(locale));
        }

        public Builder(boolean z) {
            initialize(z);
        }

        private static BidiFormatter getDefaultInstanceFromContext(boolean z) {
            return z ? BidiFormatter.c : BidiFormatter.b;
        }

        private void initialize(boolean z) {
            this.mIsRtlContext = z;
            this.mTextDirectionHeuristicCompat = BidiFormatter.a;
            this.mFlags = 2;
        }

        public BidiFormatter build() {
            return (this.mFlags == 2 && this.mTextDirectionHeuristicCompat == BidiFormatter.a) ? getDefaultInstanceFromContext(this.mIsRtlContext) : new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat);
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
            this.mTextDirectionHeuristicCompat = textDirectionHeuristicCompat;
            return this;
        }

        public Builder stereoReset(boolean z) {
            this.mFlags = z ? this.mFlags | 2 : this.mFlags & -3;
            return this;
        }
    }

    private static class DirectionalityEstimator {
        private static final byte[] DIR_TYPE_CACHE = new byte[DIR_TYPE_CACHE_SIZE];
        private static final int DIR_TYPE_CACHE_SIZE = 1792;
        private int charIndex;
        private final boolean isHtml;
        private char lastChar;
        private final int length;
        private final CharSequence text;

        static {
            for (int i = 0; i < DIR_TYPE_CACHE_SIZE; i++) {
                DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
            }
        }

        DirectionalityEstimator(CharSequence charSequence, boolean z) {
            this.text = charSequence;
            this.isHtml = z;
            this.length = charSequence.length();
        }

        private static byte getCachedDirectionality(char c) {
            return c < DIR_TYPE_CACHE_SIZE ? DIR_TYPE_CACHE[c] : Character.getDirectionality(c);
        }

        private byte skipEntityBackward() {
            char c;
            int i = this.charIndex;
            do {
                int i2 = this.charIndex;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.text;
                int i3 = i2 - 1;
                this.charIndex = i3;
                this.lastChar = charSequence.charAt(i3);
                c = this.lastChar;
                if (c == '&') {
                    return Ascii.FF;
                }
            } while (c != ';');
            this.charIndex = i;
            this.lastChar = ';';
            return Ascii.CR;
        }

        private byte skipEntityForward() {
            char charAt;
            do {
                int i = this.charIndex;
                if (i >= this.length) {
                    return Ascii.FF;
                }
                CharSequence charSequence = this.text;
                this.charIndex = i + 1;
                charAt = charSequence.charAt(i);
                this.lastChar = charAt;
            } while (charAt != ';');
            return Ascii.FF;
        }

        private byte skipTagBackward() {
            char charAt;
            int i = this.charIndex;
            while (true) {
                int i2 = this.charIndex;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.text;
                int i3 = i2 - 1;
                this.charIndex = i3;
                this.lastChar = charSequence.charAt(i3);
                char c = this.lastChar;
                if (c == '<') {
                    return Ascii.FF;
                }
                if (c == '>') {
                    break;
                } else if (c == '\"' || c == '\'') {
                    char c2 = this.lastChar;
                    do {
                        int i4 = this.charIndex;
                        if (i4 <= 0) {
                            break;
                        }
                        CharSequence charSequence2 = this.text;
                        int i5 = i4 - 1;
                        this.charIndex = i5;
                        charAt = charSequence2.charAt(i5);
                        this.lastChar = charAt;
                    } while (charAt != c2);
                }
            }
            this.charIndex = i;
            this.lastChar = Typography.greater;
            return Ascii.CR;
        }

        private byte skipTagForward() {
            char charAt;
            int i = this.charIndex;
            while (true) {
                int i2 = this.charIndex;
                if (i2 < this.length) {
                    CharSequence charSequence = this.text;
                    this.charIndex = i2 + 1;
                    this.lastChar = charSequence.charAt(i2);
                    char c = this.lastChar;
                    if (c == '>') {
                        return Ascii.FF;
                    }
                    if (c == '\"' || c == '\'') {
                        char c2 = this.lastChar;
                        do {
                            int i3 = this.charIndex;
                            if (i3 >= this.length) {
                                break;
                            }
                            CharSequence charSequence2 = this.text;
                            this.charIndex = i3 + 1;
                            charAt = charSequence2.charAt(i3);
                            this.lastChar = charAt;
                        } while (charAt != c2);
                    }
                } else {
                    this.charIndex = i;
                    this.lastChar = Typography.less;
                    return Ascii.CR;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public int a() {
            this.charIndex = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.charIndex < this.length && i == 0) {
                byte c = c();
                if (c != 9) {
                    switch (c) {
                        case 0:
                            if (i3 == 0) {
                                return -1;
                            }
                            break;
                        case 1:
                        case 2:
                            if (i3 == 0) {
                                return 1;
                            }
                            break;
                        default:
                            switch (c) {
                                case 14:
                                case 15:
                                    i3++;
                                    i2 = -1;
                                    continue;
                                case 16:
                                case 17:
                                    i3++;
                                    i2 = 1;
                                    continue;
                                case 18:
                                    i3--;
                                    i2 = 0;
                                    continue;
                                    continue;
                            }
                    }
                    i = i3;
                }
            }
            if (i == 0) {
                return 0;
            }
            if (i2 != 0) {
                return i2;
            }
            while (this.charIndex > 0) {
                switch (d()) {
                    case 14:
                    case 15:
                        if (i == i3) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i == i3) {
                            return 1;
                        }
                        break;
                    case 18:
                        i3++;
                        continue;
                }
                i3--;
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
            r2 = r2 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0035, code lost:
            r1 = r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int b() {
            /*
                r6 = this;
                int r0 = r6.length
                r6.charIndex = r0
                r0 = 0
                r1 = 0
                r2 = 0
            L_0x0007:
                int r3 = r6.charIndex
                if (r3 <= 0) goto L_0x0037
                byte r3 = r6.d()
                r4 = 9
                if (r3 == r4) goto L_0x0007
                r4 = 1
                r5 = -1
                switch(r3) {
                    case 0: goto L_0x0030;
                    case 1: goto L_0x002a;
                    case 2: goto L_0x002a;
                    default: goto L_0x0018;
                }
            L_0x0018:
                switch(r3) {
                    case 14: goto L_0x0024;
                    case 15: goto L_0x0024;
                    case 16: goto L_0x0021;
                    case 17: goto L_0x0021;
                    case 18: goto L_0x001e;
                    default: goto L_0x001b;
                }
            L_0x001b:
                if (r1 != 0) goto L_0x0007
                goto L_0x0035
            L_0x001e:
                int r2 = r2 + 1
                goto L_0x0007
            L_0x0021:
                if (r1 != r2) goto L_0x0027
                return r4
            L_0x0024:
                if (r1 != r2) goto L_0x0027
                return r5
            L_0x0027:
                int r2 = r2 + -1
                goto L_0x0007
            L_0x002a:
                if (r2 != 0) goto L_0x002d
                return r4
            L_0x002d:
                if (r1 != 0) goto L_0x0007
                goto L_0x0035
            L_0x0030:
                if (r2 != 0) goto L_0x0033
                return r5
            L_0x0033:
                if (r1 != 0) goto L_0x0007
            L_0x0035:
                r1 = r2
                goto L_0x0007
            L_0x0037:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.text.BidiFormatter.DirectionalityEstimator.b():int");
        }

        /* access modifiers changed from: package-private */
        public byte c() {
            this.lastChar = this.text.charAt(this.charIndex);
            if (Character.isHighSurrogate(this.lastChar)) {
                int codePointAt = Character.codePointAt(this.text, this.charIndex);
                this.charIndex += Character.charCount(codePointAt);
                return Character.getDirectionality(codePointAt);
            }
            this.charIndex++;
            byte cachedDirectionality = getCachedDirectionality(this.lastChar);
            if (!this.isHtml) {
                return cachedDirectionality;
            }
            char c = this.lastChar;
            return c == '<' ? skipTagForward() : c == '&' ? skipEntityForward() : cachedDirectionality;
        }

        /* access modifiers changed from: package-private */
        public byte d() {
            this.lastChar = this.text.charAt(this.charIndex - 1);
            if (Character.isLowSurrogate(this.lastChar)) {
                int codePointBefore = Character.codePointBefore(this.text, this.charIndex);
                this.charIndex -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.charIndex--;
            byte cachedDirectionality = getCachedDirectionality(this.lastChar);
            if (!this.isHtml) {
                return cachedDirectionality;
            }
            char c = this.lastChar;
            return c == '>' ? skipTagBackward() : c == ';' ? skipEntityBackward() : cachedDirectionality;
        }
    }

    BidiFormatter(boolean z, int i, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.mIsRtlContext = z;
        this.mFlags = i;
        this.mDefaultTextDirectionHeuristicCompat = textDirectionHeuristicCompat;
    }

    static boolean a(Locale locale) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
    }

    private static int getEntryDir(CharSequence charSequence) {
        return new DirectionalityEstimator(charSequence, false).a();
    }

    private static int getExitDir(CharSequence charSequence) {
        return new DirectionalityEstimator(charSequence, false).b();
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static BidiFormatter getInstance(Locale locale) {
        return new Builder(locale).build();
    }

    public static BidiFormatter getInstance(boolean z) {
        return new Builder(z).build();
    }

    private String markAfter(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        return (this.mIsRtlContext || (!isRtl && getExitDir(charSequence) != 1)) ? this.mIsRtlContext ? (!isRtl || getExitDir(charSequence) == -1) ? RLM_STRING : "" : "" : LRM_STRING;
    }

    private String markBefore(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        return (this.mIsRtlContext || (!isRtl && getEntryDir(charSequence) != 1)) ? this.mIsRtlContext ? (!isRtl || getEntryDir(charSequence) == -1) ? RLM_STRING : "" : "" : LRM_STRING;
    }

    public boolean getStereoReset() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isRtl(CharSequence charSequence) {
        return this.mDefaultTextDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
    }

    public boolean isRtl(String str) {
        return isRtl((CharSequence) str);
    }

    public boolean isRtlContext() {
        return this.mIsRtlContext;
    }

    public CharSequence unicodeWrap(CharSequence charSequence) {
        return unicodeWrap(charSequence, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(charSequence, textDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (getStereoReset() && z) {
            spannableStringBuilder.append(markBefore(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        if (isRtl != this.mIsRtlContext) {
            spannableStringBuilder.append(isRtl ? RLE : LRE);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append(PDF);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append(markAfter(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        return spannableStringBuilder;
    }

    public CharSequence unicodeWrap(CharSequence charSequence, boolean z) {
        return unicodeWrap(charSequence, this.mDefaultTextDirectionHeuristicCompat, z);
    }

    public String unicodeWrap(String str) {
        return unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(str, textDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (str == null) {
            return null;
        }
        return unicodeWrap((CharSequence) str, textDirectionHeuristicCompat, z).toString();
    }

    public String unicodeWrap(String str, boolean z) {
        return unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, z);
    }
}
