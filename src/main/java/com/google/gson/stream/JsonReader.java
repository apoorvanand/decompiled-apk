package com.google.gson.stream;

import com.facebook.internal.ServerProtocol;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import kotlin.text.Typography;

public class JsonReader implements Closeable {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    int a = 0;
    private final char[] buffer = new char[1024];
    private final Reader in;
    private boolean lenient = false;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    private int[] pathIndices;
    private String[] pathNames;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos = 0;
    private int[] stack = new int[32];
    private int stackSize = 0;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
            public void promoteNameToValue(JsonReader jsonReader) {
                int i;
                if (jsonReader instanceof JsonTreeReader) {
                    ((JsonTreeReader) jsonReader).promoteNameToValue();
                    return;
                }
                int i2 = jsonReader.a;
                if (i2 == 0) {
                    i2 = jsonReader.a();
                }
                if (i2 == 13) {
                    i = 9;
                } else if (i2 == 12) {
                    i = 8;
                } else if (i2 == 14) {
                    i = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + jsonReader.peek() + jsonReader.b());
                }
                jsonReader.a = i;
            }
        };
    }

    public JsonReader(Reader reader) {
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (reader != null) {
            this.in = reader;
            return;
        }
        throw new NullPointerException("in == null");
    }

    private void checkLenient() {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void consumeNonExecutePrefix() {
        nextNonWhitespace(true);
        this.pos--;
        int i = this.pos;
        char[] cArr = NON_EXECUTE_PREFIX;
        if (i + cArr.length <= this.limit || fillBuffer(cArr.length)) {
            int i2 = 0;
            while (true) {
                char[] cArr2 = NON_EXECUTE_PREFIX;
                if (i2 >= cArr2.length) {
                    this.pos += cArr2.length;
                    return;
                } else if (this.buffer[this.pos + i2] == cArr2[i2]) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    private boolean fillBuffer(int i) {
        int i2;
        char[] cArr = this.buffer;
        int i3 = this.lineStart;
        int i4 = this.pos;
        this.lineStart = i3 - i4;
        int i5 = this.limit;
        if (i5 != i4) {
            this.limit = i5 - i4;
            System.arraycopy(cArr, i4, cArr, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.in;
            int i6 = this.limit;
            int read = reader.read(cArr, i6, cArr.length - i6);
            if (read == -1) {
                return false;
            }
            this.limit += read;
            if (this.lineNumber == 0 && (i2 = this.lineStart) == 0 && this.limit > 0 && cArr[0] == 65279) {
                this.pos++;
                this.lineStart = i2 + 1;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    private boolean isLiteral(char c) {
        switch (c) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                return false;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                checkLenient();
                return false;
            default:
                return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        if (r1 != '/') goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        r7.pos = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0058, code lost:
        if (r4 != r2) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        r7.pos--;
        r2 = fillBuffer(2);
        r7.pos++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0068, code lost:
        if (r2 != false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006a, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006b, code lost:
        checkLenient();
        r2 = r7.pos;
        r3 = r0[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0074, code lost:
        if (r3 == '*') goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0076, code lost:
        if (r3 == '/') goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0078, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0079, code lost:
        r7.pos = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0081, code lost:
        r7.pos = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008b, code lost:
        if (skipTo("*/") == false) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0098, code lost:
        throw syntaxError("Unterminated comment");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009b, code lost:
        if (r1 != '#') goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009d, code lost:
        r7.pos = r4;
        checkLenient();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a3, code lost:
        r7.pos = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a5, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int nextNonWhitespace(boolean r8) {
        /*
            r7 = this;
            char[] r0 = r7.buffer
        L_0x0002:
            int r1 = r7.pos
        L_0x0004:
            int r2 = r7.limit
        L_0x0006:
            r3 = 1
            if (r1 != r2) goto L_0x0034
            r7.pos = r1
            boolean r1 = r7.fillBuffer(r3)
            if (r1 != 0) goto L_0x0030
            if (r8 != 0) goto L_0x0015
            r8 = -1
            return r8
        L_0x0015:
            java.io.EOFException r8 = new java.io.EOFException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "End of input"
            r0.append(r1)
            java.lang.String r1 = r7.b()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        L_0x0030:
            int r1 = r7.pos
            int r2 = r7.limit
        L_0x0034:
            int r4 = r1 + 1
            char r1 = r0[r1]
            r5 = 10
            if (r1 != r5) goto L_0x0044
            int r1 = r7.lineNumber
            int r1 = r1 + r3
            r7.lineNumber = r1
            r7.lineStart = r4
            goto L_0x00a6
        L_0x0044:
            r5 = 32
            if (r1 == r5) goto L_0x00a6
            r5 = 13
            if (r1 == r5) goto L_0x00a6
            r5 = 9
            if (r1 != r5) goto L_0x0051
            goto L_0x00a6
        L_0x0051:
            r5 = 47
            if (r1 != r5) goto L_0x0099
            r7.pos = r4
            r6 = 2
            if (r4 != r2) goto L_0x006b
            int r2 = r7.pos
            int r2 = r2 - r3
            r7.pos = r2
            boolean r2 = r7.fillBuffer(r6)
            int r4 = r7.pos
            int r4 = r4 + r3
            r7.pos = r4
            if (r2 != 0) goto L_0x006b
            return r1
        L_0x006b:
            r7.checkLenient()
            int r2 = r7.pos
            char r3 = r0[r2]
            r4 = 42
            if (r3 == r4) goto L_0x0081
            if (r3 == r5) goto L_0x0079
            return r1
        L_0x0079:
            int r2 = r2 + 1
            r7.pos = r2
        L_0x007d:
            r7.skipToEndOfLine()
            goto L_0x0002
        L_0x0081:
            int r2 = r2 + 1
            r7.pos = r2
            java.lang.String r1 = "*/"
            boolean r1 = r7.skipTo(r1)
            if (r1 == 0) goto L_0x0092
            int r1 = r7.pos
            int r1 = r1 + r6
            goto L_0x0004
        L_0x0092:
            java.lang.String r8 = "Unterminated comment"
            java.io.IOException r8 = r7.syntaxError(r8)
            throw r8
        L_0x0099:
            r2 = 35
            if (r1 != r2) goto L_0x00a3
            r7.pos = r4
            r7.checkLenient()
            goto L_0x007d
        L_0x00a3:
            r7.pos = r4
            return r1
        L_0x00a6:
            r1 = r4
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.nextNonWhitespace(boolean):int");
    }

    private String nextQuotedValue(char c) {
        char[] cArr = this.buffer;
        StringBuilder sb = null;
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            while (true) {
                if (i < i2) {
                    int i4 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.pos = i4;
                        int i5 = (i4 - i3) - 1;
                        if (sb == null) {
                            return new String(cArr, i3, i5);
                        }
                        sb.append(cArr, i3, i5);
                        return sb.toString();
                    } else if (c2 == '\\') {
                        this.pos = i4;
                        int i6 = (i4 - i3) - 1;
                        if (sb == null) {
                            sb = new StringBuilder(Math.max((i6 + 1) * 2, 16));
                        }
                        sb.append(cArr, i3, i6);
                        sb.append(readEscapeCharacter());
                    } else {
                        if (c2 == 10) {
                            this.lineNumber++;
                            this.lineStart = i4;
                        }
                        i = i4;
                    }
                } else {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max((i - i3) * 2, 16));
                    }
                    sb.append(cArr, i3, i - i3);
                    this.pos = i;
                    if (!fillBuffer(1)) {
                        throw syntaxError("Unterminated string");
                    }
                }
            }
        }
    }

    private String nextUnquotedValue() {
        int i;
        String str;
        int i2 = 0;
        StringBuilder sb = null;
        while (true) {
            i = 0;
            while (true) {
                int i3 = this.pos;
                if (i3 + i < this.limit) {
                    switch (this.buffer[i3 + i]) {
                        case 9:
                        case 10:
                        case 12:
                        case 13:
                        case ' ':
                        case ',':
                        case ':':
                        case '[':
                        case ']':
                        case '{':
                        case '}':
                            break;
                        case '#':
                        case '/':
                        case ';':
                        case '=':
                        case '\\':
                            checkLenient();
                            break;
                        default:
                            i++;
                            break;
                    }
                } else if (i >= this.buffer.length) {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max(i, 16));
                    }
                    sb.append(this.buffer, this.pos, i);
                    this.pos += i;
                    if (!fillBuffer(1)) {
                    }
                } else if (fillBuffer(i + 1)) {
                }
            }
        }
        i2 = i;
        if (sb == null) {
            str = new String(this.buffer, this.pos, i2);
        } else {
            sb.append(this.buffer, this.pos, i2);
            str = sb.toString();
        }
        this.pos += i2;
        return str;
    }

    private int peekKeyword() {
        int i;
        String str;
        String str2;
        char c = this.buffer[this.pos];
        if (c == 't' || c == 'T') {
            str2 = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
            str = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str2 = "false";
            str = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str2 = "null";
            str = "NULL";
            i = 7;
        }
        int length = str2.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.pos + i2 >= this.limit && !fillBuffer(i2 + 1)) {
                return 0;
            }
            char c2 = this.buffer[this.pos + i2];
            if (c2 != str2.charAt(i2) && c2 != str.charAt(i2)) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.a = i;
        return i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int peekNumber() {
        /*
            r18 = this;
            r0 = r18
            char[] r1 = r0.buffer
            int r2 = r0.pos
            int r3 = r0.limit
            r6 = 1
            r7 = 0
            r8 = r3
            r3 = 0
            r9 = 0
            r10 = 1
            r11 = 0
            r13 = 0
        L_0x0011:
            int r14 = r2 + r3
            r15 = 2
            if (r14 != r8) goto L_0x0028
            int r2 = r1.length
            if (r3 != r2) goto L_0x001a
            return r7
        L_0x001a:
            int r2 = r3 + 1
            boolean r2 = r0.fillBuffer(r2)
            if (r2 != 0) goto L_0x0024
            goto L_0x0095
        L_0x0024:
            int r2 = r0.pos
            int r8 = r0.limit
        L_0x0028:
            int r14 = r2 + r3
            char r14 = r1[r14]
            r7 = 43
            r4 = 3
            r5 = 5
            if (r14 == r7) goto L_0x00ec
            r7 = 69
            if (r14 == r7) goto L_0x00e0
            r7 = 101(0x65, float:1.42E-43)
            if (r14 == r7) goto L_0x00e0
            switch(r14) {
                case 45: goto L_0x00d3;
                case 46: goto L_0x00cb;
                default: goto L_0x003d;
            }
        L_0x003d:
            r7 = 48
            if (r14 < r7) goto L_0x008f
            r7 = 57
            if (r14 <= r7) goto L_0x0046
            goto L_0x008f
        L_0x0046:
            if (r9 == r6) goto L_0x0084
            if (r9 != 0) goto L_0x004b
            goto L_0x0084
        L_0x004b:
            if (r9 != r15) goto L_0x0074
            r16 = 0
            int r4 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1))
            if (r4 != 0) goto L_0x0055
            r4 = 0
            return r4
        L_0x0055:
            r4 = 10
            long r4 = r4 * r11
            int r14 = r14 + -48
            long r14 = (long) r14
            long r4 = r4 - r14
            r14 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r7 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r7 > 0) goto L_0x006f
            if (r7 != 0) goto L_0x006d
            int r7 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r7 >= 0) goto L_0x006d
            goto L_0x006f
        L_0x006d:
            r7 = 0
            goto L_0x0070
        L_0x006f:
            r7 = 1
        L_0x0070:
            r7 = r7 & r10
            r11 = r4
            r10 = r7
            goto L_0x007f
        L_0x0074:
            if (r9 != r4) goto L_0x0079
            r7 = 0
            r9 = 4
            goto L_0x008b
        L_0x0079:
            if (r9 == r5) goto L_0x0081
            r4 = 6
            if (r9 != r4) goto L_0x007f
            goto L_0x0081
        L_0x007f:
            r7 = 0
            goto L_0x008b
        L_0x0081:
            r7 = 0
            r9 = 7
            goto L_0x008b
        L_0x0084:
            int r14 = r14 + -48
            int r4 = -r14
            long r4 = (long) r4
            r11 = r4
            r7 = 0
            r9 = 2
        L_0x008b:
            r16 = 0
            goto L_0x00f3
        L_0x008f:
            boolean r1 = r0.isLiteral(r14)
            if (r1 != 0) goto L_0x00c9
        L_0x0095:
            if (r9 != r15) goto L_0x00b9
            if (r10 == 0) goto L_0x00b9
            r1 = -9223372036854775808
            int r4 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r4 != 0) goto L_0x00a1
            if (r13 == 0) goto L_0x00b9
        L_0x00a1:
            r16 = 0
            int r1 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1))
            if (r1 != 0) goto L_0x00a9
            if (r13 != 0) goto L_0x00b9
        L_0x00a9:
            if (r13 == 0) goto L_0x00ac
            goto L_0x00ad
        L_0x00ac:
            long r11 = -r11
        L_0x00ad:
            r0.peekedLong = r11
            int r1 = r0.pos
            int r1 = r1 + r3
            r0.pos = r1
            r1 = 15
        L_0x00b6:
            r0.a = r1
            return r1
        L_0x00b9:
            if (r9 == r15) goto L_0x00c4
            r1 = 4
            if (r9 == r1) goto L_0x00c4
            r1 = 7
            if (r9 != r1) goto L_0x00c2
            goto L_0x00c4
        L_0x00c2:
            r7 = 0
            return r7
        L_0x00c4:
            r0.peekedNumberLength = r3
            r1 = 16
            goto L_0x00b6
        L_0x00c9:
            r7 = 0
            return r7
        L_0x00cb:
            r7 = 0
            r16 = 0
            if (r9 != r15) goto L_0x00d2
            r9 = 3
            goto L_0x00f3
        L_0x00d2:
            return r7
        L_0x00d3:
            r4 = 6
            r7 = 0
            r16 = 0
            if (r9 != 0) goto L_0x00dc
            r9 = 1
            r13 = 1
            goto L_0x00f3
        L_0x00dc:
            if (r9 != r5) goto L_0x00df
            goto L_0x00f2
        L_0x00df:
            return r7
        L_0x00e0:
            r7 = 0
            r16 = 0
            if (r9 == r15) goto L_0x00ea
            r4 = 4
            if (r9 != r4) goto L_0x00e9
            goto L_0x00ea
        L_0x00e9:
            return r7
        L_0x00ea:
            r9 = 5
            goto L_0x00f3
        L_0x00ec:
            r4 = 6
            r7 = 0
            r16 = 0
            if (r9 != r5) goto L_0x00f7
        L_0x00f2:
            r9 = 6
        L_0x00f3:
            int r3 = r3 + 1
            goto L_0x0011
        L_0x00f7:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.peekNumber():int");
    }

    private void push(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.stack;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(i2 * 2)];
            int[] iArr3 = new int[(i2 * 2)];
            String[] strArr = new String[(i2 * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.pathIndices, 0, iArr3, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, strArr, 0, this.stackSize);
            this.stack = iArr2;
            this.pathIndices = iArr3;
            this.pathNames = strArr;
        }
        int[] iArr4 = this.stack;
        int i3 = this.stackSize;
        this.stackSize = i3 + 1;
        iArr4[i3] = i;
    }

    private char readEscapeCharacter() {
        int i;
        int i2;
        if (this.pos != this.limit || fillBuffer(1)) {
            char[] cArr = this.buffer;
            int i3 = this.pos;
            this.pos = i3 + 1;
            char c = cArr[i3];
            if (c == 10) {
                this.lineNumber++;
                this.lineStart = this.pos;
            } else if (!(c == '\"' || c == '\'' || c == '/' || c == '\\')) {
                if (c == 'b') {
                    return 8;
                }
                if (c == 'f') {
                    return 12;
                }
                if (c == 'n') {
                    return 10;
                }
                if (c == 'r') {
                    return 13;
                }
                switch (c) {
                    case 't':
                        return 9;
                    case 'u':
                        if (this.pos + 4 <= this.limit || fillBuffer(4)) {
                            char c2 = 0;
                            int i4 = this.pos;
                            int i5 = i4 + 4;
                            while (i4 < i5) {
                                char c3 = this.buffer[i4];
                                char c4 = (char) (c2 << 4);
                                if (c3 < '0' || c3 > '9') {
                                    if (c3 >= 'a' && c3 <= 'f') {
                                        i = c3 - 'a';
                                    } else if (c3 < 'A' || c3 > 'F') {
                                        throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                                    } else {
                                        i = c3 - 'A';
                                    }
                                    i2 = i + 10;
                                } else {
                                    i2 = c3 - '0';
                                }
                                c2 = (char) (c4 + i2);
                                i4++;
                            }
                            this.pos += 4;
                            return c2;
                        }
                        throw syntaxError("Unterminated escape sequence");
                    default:
                        throw syntaxError("Invalid escape sequence");
                }
            }
            return c;
        }
        throw syntaxError("Unterminated escape sequence");
    }

    private void skipQuotedValue(char c) {
        char[] cArr = this.buffer;
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.pos = i3;
                        return;
                    } else if (c2 == '\\') {
                        this.pos = i3;
                        readEscapeCharacter();
                        break;
                    } else {
                        if (c2 == 10) {
                            this.lineNumber++;
                            this.lineStart = i3;
                        }
                        i = i3;
                    }
                } else {
                    this.pos = i;
                    if (!fillBuffer(1)) {
                        throw syntaxError("Unterminated string");
                    }
                }
            }
        }
    }

    private boolean skipTo(String str) {
        int length = str.length();
        while (true) {
            int i = 0;
            if (this.pos + length > this.limit && !fillBuffer(length)) {
                return false;
            }
            char[] cArr = this.buffer;
            int i2 = this.pos;
            if (cArr[i2] == 10) {
                this.lineNumber++;
                this.lineStart = i2 + 1;
            } else {
                while (i < length) {
                    if (this.buffer[this.pos + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    private void skipToEndOfLine() {
        char c;
        do {
            if (this.pos < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i = this.pos;
                this.pos = i + 1;
                c = cArr[i];
                if (c == 10) {
                    this.lineNumber++;
                    this.lineStart = this.pos;
                    return;
                }
            } else {
                return;
            }
        } while (c != 13);
    }

    private void skipUnquotedValue() {
        do {
            int i = 0;
            while (true) {
                int i2 = this.pos;
                if (i2 + i < this.limit) {
                    switch (this.buffer[i2 + i]) {
                        case 9:
                        case 10:
                        case 12:
                        case 13:
                        case ' ':
                        case ',':
                        case ':':
                        case '[':
                        case ']':
                        case '{':
                        case '}':
                            break;
                        case '#':
                        case '/':
                        case ';':
                        case '=':
                        case '\\':
                            checkLenient();
                            break;
                        default:
                            i++;
                    }
                } else {
                    this.pos = i2 + i;
                }
            }
            this.pos += i;
            return;
        } while (fillBuffer(1));
    }

    private IOException syntaxError(String str) {
        throw new MalformedJsonException(str + b());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x010c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a() {
        /*
            r15 = this;
            int[] r0 = r15.stack
            int r1 = r15.stackSize
            int r2 = r1 + -1
            r2 = r0[r2]
            r3 = 8
            r4 = 39
            r5 = 34
            r6 = 93
            r7 = 3
            r8 = 7
            r9 = 59
            r10 = 44
            r11 = 4
            r12 = 2
            r13 = 1
            if (r2 != r13) goto L_0x0020
            int r1 = r1 - r13
            r0[r1] = r12
            goto L_0x00a2
        L_0x0020:
            if (r2 != r12) goto L_0x003a
            int r0 = r15.nextNonWhitespace(r13)
            if (r0 == r10) goto L_0x00a2
            if (r0 == r9) goto L_0x0036
            if (r0 != r6) goto L_0x002f
            r15.a = r11
            return r11
        L_0x002f:
            java.lang.String r0 = "Unterminated array"
            java.io.IOException r0 = r15.syntaxError(r0)
            throw r0
        L_0x0036:
            r15.checkLenient()
            goto L_0x00a2
        L_0x003a:
            r14 = 5
            if (r2 == r7) goto L_0x0117
            if (r2 != r14) goto L_0x0041
            goto L_0x0117
        L_0x0041:
            if (r2 != r11) goto L_0x0076
            int r1 = r1 - r13
            r0[r1] = r14
            int r0 = r15.nextNonWhitespace(r13)
            r1 = 58
            if (r0 == r1) goto L_0x00a2
            r1 = 61
            if (r0 != r1) goto L_0x006f
            r15.checkLenient()
            int r0 = r15.pos
            int r1 = r15.limit
            if (r0 < r1) goto L_0x0061
            boolean r0 = r15.fillBuffer(r13)
            if (r0 == 0) goto L_0x00a2
        L_0x0061:
            char[] r0 = r15.buffer
            int r1 = r15.pos
            char r0 = r0[r1]
            r14 = 62
            if (r0 != r14) goto L_0x00a2
            int r1 = r1 + r13
            r15.pos = r1
            goto L_0x00a2
        L_0x006f:
            java.lang.String r0 = "Expected ':'"
            java.io.IOException r0 = r15.syntaxError(r0)
            throw r0
        L_0x0076:
            r0 = 6
            if (r2 != r0) goto L_0x0088
            boolean r0 = r15.lenient
            if (r0 == 0) goto L_0x0080
            r15.consumeNonExecutePrefix()
        L_0x0080:
            int[] r0 = r15.stack
            int r1 = r15.stackSize
            int r1 = r1 - r13
            r0[r1] = r8
            goto L_0x00a2
        L_0x0088:
            if (r2 != r8) goto L_0x00a0
            r0 = 0
            int r0 = r15.nextNonWhitespace(r0)
            r1 = -1
            if (r0 != r1) goto L_0x0097
            r0 = 17
        L_0x0094:
            r15.a = r0
            return r0
        L_0x0097:
            r15.checkLenient()
            int r0 = r15.pos
            int r0 = r0 - r13
            r15.pos = r0
            goto L_0x00a2
        L_0x00a0:
            if (r2 == r3) goto L_0x010f
        L_0x00a2:
            int r0 = r15.nextNonWhitespace(r13)
            if (r0 == r5) goto L_0x010c
            if (r0 == r4) goto L_0x0106
            if (r0 == r10) goto L_0x00ef
            if (r0 == r9) goto L_0x00ef
            r1 = 91
            if (r0 == r1) goto L_0x00ec
            if (r0 == r6) goto L_0x00e7
            r1 = 123(0x7b, float:1.72E-43)
            if (r0 == r1) goto L_0x00e4
            int r0 = r15.pos
            int r0 = r0 - r13
            r15.pos = r0
            int r0 = r15.peekKeyword()
            if (r0 == 0) goto L_0x00c4
            return r0
        L_0x00c4:
            int r0 = r15.peekNumber()
            if (r0 == 0) goto L_0x00cb
            return r0
        L_0x00cb:
            char[] r0 = r15.buffer
            int r1 = r15.pos
            char r0 = r0[r1]
            boolean r0 = r15.isLiteral(r0)
            if (r0 == 0) goto L_0x00dd
            r15.checkLenient()
            r0 = 10
            goto L_0x0094
        L_0x00dd:
            java.lang.String r0 = "Expected value"
            java.io.IOException r0 = r15.syntaxError(r0)
            throw r0
        L_0x00e4:
            r15.a = r13
            return r13
        L_0x00e7:
            if (r2 != r13) goto L_0x00ef
            r15.a = r11
            return r11
        L_0x00ec:
            r15.a = r7
            return r7
        L_0x00ef:
            if (r2 == r13) goto L_0x00fb
            if (r2 != r12) goto L_0x00f4
            goto L_0x00fb
        L_0x00f4:
            java.lang.String r0 = "Unexpected value"
            java.io.IOException r0 = r15.syntaxError(r0)
            throw r0
        L_0x00fb:
            r15.checkLenient()
            int r0 = r15.pos
            int r0 = r0 - r13
            r15.pos = r0
            r15.a = r8
            return r8
        L_0x0106:
            r15.checkLenient()
            r15.a = r3
            return r3
        L_0x010c:
            r0 = 9
            goto L_0x0094
        L_0x010f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "JsonReader is closed"
            r0.<init>(r1)
            throw r0
        L_0x0117:
            int[] r0 = r15.stack
            int r1 = r15.stackSize
            int r1 = r1 - r13
            r0[r1] = r11
            r0 = 125(0x7d, float:1.75E-43)
            if (r2 != r14) goto L_0x0139
            int r1 = r15.nextNonWhitespace(r13)
            if (r1 == r10) goto L_0x0139
            if (r1 == r9) goto L_0x0136
            if (r1 != r0) goto L_0x012f
            r15.a = r12
            return r12
        L_0x012f:
            java.lang.String r0 = "Unterminated object"
            java.io.IOException r0 = r15.syntaxError(r0)
            throw r0
        L_0x0136:
            r15.checkLenient()
        L_0x0139:
            int r1 = r15.nextNonWhitespace(r13)
            if (r1 == r5) goto L_0x0170
            if (r1 == r4) goto L_0x0169
            if (r1 == r0) goto L_0x015d
            r15.checkLenient()
            int r0 = r15.pos
            int r0 = r0 - r13
            r15.pos = r0
            char r0 = (char) r1
            boolean r0 = r15.isLiteral(r0)
            if (r0 == 0) goto L_0x0156
            r0 = 14
            goto L_0x0094
        L_0x0156:
            java.lang.String r0 = "Expected name"
            java.io.IOException r0 = r15.syntaxError(r0)
            throw r0
        L_0x015d:
            if (r2 == r14) goto L_0x0162
            r15.a = r12
            return r12
        L_0x0162:
            java.lang.String r0 = "Expected name"
            java.io.IOException r0 = r15.syntaxError(r0)
            throw r0
        L_0x0169:
            r15.checkLenient()
            r0 = 12
            goto L_0x0094
        L_0x0170:
            r0 = 13
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.a():int");
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return " at line " + (this.lineNumber + 1) + " column " + ((this.pos - this.lineStart) + 1) + " path " + getPath();
    }

    public void beginArray() {
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + b());
    }

    public void beginObject() {
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 1) {
            push(3);
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + b());
    }

    public void close() {
        this.a = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    public void endArray() {
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 4) {
            this.stackSize--;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + peek() + b());
    }

    public void endObject() {
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 2) {
            this.stackSize--;
            String[] strArr = this.pathNames;
            int i2 = this.stackSize;
            strArr[i2] = null;
            int[] iArr = this.pathIndices;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + peek() + b());
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.dollar);
        int i = this.stackSize;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.stack[i2]) {
                case 1:
                case 2:
                    sb.append('[');
                    sb.append(this.pathIndices[i2]);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append('.');
                    String[] strArr = this.pathNames;
                    if (strArr[i2] == null) {
                        break;
                    } else {
                        sb.append(strArr[i2]);
                        break;
                    }
            }
        }
        return sb.toString();
    }

    public boolean hasNext() {
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public boolean nextBoolean() {
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 5) {
            this.a = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.a = 0;
            int[] iArr2 = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + peek() + b());
        }
    }

    public double nextDouble() {
        String str;
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 15) {
            this.a = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.peekedLong;
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            if (i == 8 || i == 9) {
                str = nextQuotedValue(i == 8 ? '\'' : Typography.quote);
            } else if (i == 10) {
                str = nextUnquotedValue();
            } else if (i != 11) {
                throw new IllegalStateException("Expected a double but was " + peek() + b());
            }
            this.peekedString = str;
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        if (this.lenient || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.peekedString = null;
            this.a = 0;
            int[] iArr2 = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        throw new MalformedJsonException("JSON forbids NaN and infinities: " + parseDouble + b());
    }

    public int nextInt() {
        String nextQuotedValue;
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 15) {
            long j = this.peekedLong;
            int i2 = (int) j;
            if (j == ((long) i2)) {
                this.a = 0;
                int[] iArr = this.pathIndices;
                int i3 = this.stackSize - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.peekedLong + b());
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                nextQuotedValue = nextUnquotedValue();
            } else {
                nextQuotedValue = nextQuotedValue(i == 8 ? '\'' : Typography.quote);
            }
            this.peekedString = nextQuotedValue;
            try {
                int parseInt = Integer.parseInt(this.peekedString);
                this.a = 0;
                int[] iArr2 = this.pathIndices;
                int i4 = this.stackSize - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + peek() + b());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        int i5 = (int) parseDouble;
        if (((double) i5) == parseDouble) {
            this.peekedString = null;
            this.a = 0;
            int[] iArr3 = this.pathIndices;
            int i6 = this.stackSize - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return i5;
        }
        throw new NumberFormatException("Expected an int but was " + this.peekedString + b());
    }

    public long nextLong() {
        String nextQuotedValue;
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 15) {
            this.a = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.peekedLong;
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                nextQuotedValue = nextUnquotedValue();
            } else {
                nextQuotedValue = nextQuotedValue(i == 8 ? '\'' : Typography.quote);
            }
            this.peekedString = nextQuotedValue;
            try {
                long parseLong = Long.parseLong(this.peekedString);
                this.a = 0;
                int[] iArr2 = this.pathIndices;
                int i3 = this.stackSize - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + peek() + b());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        long j = (long) parseDouble;
        if (((double) j) == parseDouble) {
            this.peekedString = null;
            this.a = 0;
            int[] iArr3 = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr3[i4] = iArr3[i4] + 1;
            return j;
        }
        throw new NumberFormatException("Expected a long but was " + this.peekedString + b());
    }

    public String nextName() {
        String str;
        char c;
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 14) {
            str = nextUnquotedValue();
        } else {
            if (i == 12) {
                c = '\'';
            } else if (i == 13) {
                c = Typography.quote;
            } else {
                throw new IllegalStateException("Expected a name but was " + peek() + b());
            }
            str = nextQuotedValue(c);
        }
        this.a = 0;
        this.pathNames[this.stackSize - 1] = str;
        return str;
    }

    public void nextNull() {
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 7) {
            this.a = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + peek() + b());
    }

    public String nextString() {
        String str;
        char c;
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        if (i == 10) {
            str = nextUnquotedValue();
        } else {
            if (i == 8) {
                c = '\'';
            } else if (i == 9) {
                c = Typography.quote;
            } else if (i == 11) {
                str = this.peekedString;
                this.peekedString = null;
            } else if (i == 15) {
                str = Long.toString(this.peekedLong);
            } else if (i == 16) {
                str = new String(this.buffer, this.pos, this.peekedNumberLength);
                this.pos += this.peekedNumberLength;
            } else {
                throw new IllegalStateException("Expected a string but was " + peek() + b());
            }
            str = nextQuotedValue(c);
        }
        this.a = 0;
        int[] iArr = this.pathIndices;
        int i2 = this.stackSize - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public JsonToken peek() {
        int i = this.a;
        if (i == 0) {
            i = a();
        }
        switch (i) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public void skipValue() {
        char c;
        int i = 0;
        do {
            int i2 = this.a;
            if (i2 == 0) {
                i2 = a();
            }
            if (i2 == 3) {
                push(1);
            } else if (i2 == 1) {
                push(3);
            } else if (i2 == 4 || i2 == 2) {
                this.stackSize--;
                i--;
                this.a = 0;
            } else if (i2 == 14 || i2 == 10) {
                skipUnquotedValue();
                this.a = 0;
            } else {
                if (i2 == 8 || i2 == 12) {
                    c = '\'';
                } else if (i2 == 9 || i2 == 13) {
                    c = Typography.quote;
                } else {
                    if (i2 == 16) {
                        this.pos += this.peekedNumberLength;
                    }
                    this.a = 0;
                }
                skipQuotedValue(c);
                this.a = 0;
            }
            i++;
            this.a = 0;
        } while (i != 0);
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.pathNames[i3 - 1] = "null";
    }

    public String toString() {
        return getClass().getSimpleName() + b();
    }
}
