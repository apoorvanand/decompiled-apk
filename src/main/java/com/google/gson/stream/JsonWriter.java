package com.google.gson.stream;

import com.facebook.internal.ServerProtocol;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class JsonWriter implements Closeable, Flushable {
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    private static final String[] REPLACEMENT_CHARS = new String[128];
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private int[] stack = new int[32];
    private int stackSize = 0;

    static {
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        String[] strArr = REPLACEMENT_CHARS;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        HTML_SAFE_REPLACEMENT_CHARS = (String[]) strArr.clone();
        String[] strArr2 = HTML_SAFE_REPLACEMENT_CHARS;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public JsonWriter(Writer writer) {
        push(6);
        this.separator = ":";
        this.serializeNulls = true;
        if (writer != null) {
            this.out = writer;
            return;
        }
        throw new NullPointerException("out == null");
    }

    private void beforeName() {
        int peek = peek();
        if (peek == 5) {
            this.out.write(44);
        } else if (peek != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        newline();
        replaceTop(4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        newline();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        r0 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        replaceTop(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void beforeValue() {
        /*
            r2 = this;
            int r0 = r2.peek()
            switch(r0) {
                case 1: goto L_0x0032;
                case 2: goto L_0x002a;
                case 3: goto L_0x0007;
                case 4: goto L_0x0021;
                case 5: goto L_0x0007;
                case 6: goto L_0x001c;
                case 7: goto L_0x000f;
                default: goto L_0x0007;
            }
        L_0x0007:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Nesting problem."
            r0.<init>(r1)
            throw r0
        L_0x000f:
            boolean r0 = r2.lenient
            if (r0 == 0) goto L_0x0014
            goto L_0x001c
        L_0x0014:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "JSON must have only one top-level value."
            r0.<init>(r1)
            throw r0
        L_0x001c:
            r0 = 7
        L_0x001d:
            r2.replaceTop(r0)
            goto L_0x0039
        L_0x0021:
            java.io.Writer r0 = r2.out
            java.lang.String r1 = r2.separator
            r0.append(r1)
            r0 = 5
            goto L_0x001d
        L_0x002a:
            java.io.Writer r0 = r2.out
            r1 = 44
            r0.append(r1)
            goto L_0x0036
        L_0x0032:
            r0 = 2
            r2.replaceTop(r0)
        L_0x0036:
            r2.newline()
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonWriter.beforeValue():void");
    }

    private JsonWriter close(int i, int i2, String str) {
        int peek = peek();
        if (peek != i2 && peek != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName == null) {
            this.stackSize--;
            if (peek == i2) {
                newline();
            }
            this.out.write(str);
            return this;
        } else {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
    }

    private void newline() {
        if (this.indent != null) {
            this.out.write("\n");
            int i = this.stackSize;
            for (int i2 = 1; i2 < i; i2++) {
                this.out.write(this.indent);
            }
        }
    }

    private JsonWriter open(int i, String str) {
        beforeValue();
        push(i);
        this.out.write(str);
        return this;
    }

    private int peek() {
        int i = this.stackSize;
        if (i != 0) {
            return this.stack[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void push(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.stack;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(i2 * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.stack = iArr2;
        }
        int[] iArr3 = this.stack;
        int i3 = this.stackSize;
        this.stackSize = i3 + 1;
        iArr3[i3] = i;
    }

    private void replaceTop(int i) {
        this.stack[this.stackSize - 1] = i;
    }

    private void string(String str) {
        String str2;
        String[] strArr = this.htmlSafe ? HTML_SAFE_REPLACEMENT_CHARS : REPLACEMENT_CHARS;
        this.out.write("\"");
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            }
            if (i < i2) {
                this.out.write(str, i, i2 - i);
            }
            this.out.write(str2);
            i = i2 + 1;
        }
        if (i < length) {
            this.out.write(str, i, length - i);
        }
        this.out.write("\"");
    }

    private void writeDeferredName() {
        if (this.deferredName != null) {
            beforeName();
            string(this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter beginArray() {
        writeDeferredName();
        return open(1, "[");
    }

    public JsonWriter beginObject() {
        writeDeferredName();
        return open(3, "{");
    }

    public void close() {
        this.out.close();
        int i = this.stackSize;
        if (i > 1 || (i == 1 && this.stack[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    public JsonWriter endArray() {
        return close(1, 2, "]");
    }

    public JsonWriter endObject() {
        return close(3, 5, "}");
    }

    public void flush() {
        if (this.stackSize != 0) {
            this.out.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public JsonWriter jsonValue(String str) {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.out.append(str);
        return this;
    }

    public JsonWriter name(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.deferredName != null) {
            throw new IllegalStateException();
        } else if (this.stackSize != 0) {
            this.deferredName = str;
            return this;
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    public JsonWriter nullValue() {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        beforeValue();
        this.out.write("null");
        return this;
    }

    public final void setHtmlSafe(boolean z) {
        this.htmlSafe = z;
    }

    public final void setIndent(String str) {
        String str2;
        if (str.length() == 0) {
            this.indent = null;
            str2 = ":";
        } else {
            this.indent = str;
            str2 = ": ";
        }
        this.separator = str2;
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public final void setSerializeNulls(boolean z) {
        this.serializeNulls = z;
    }

    public JsonWriter value(double d) {
        writeDeferredName();
        if (this.lenient || (!Double.isNaN(d) && !Double.isInfinite(d))) {
            beforeValue();
            this.out.append(Double.toString(d));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
    }

    public JsonWriter value(long j) {
        writeDeferredName();
        beforeValue();
        this.out.write(Long.toString(j));
        return this;
    }

    public JsonWriter value(Boolean bool) {
        if (bool == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.out.write(bool.booleanValue() ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        return this;
    }

    public JsonWriter value(Number number) {
        if (number == null) {
            return nullValue();
        }
        writeDeferredName();
        String obj = number.toString();
        if (this.lenient || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            beforeValue();
            this.out.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public JsonWriter value(String str) {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        string(str);
        return this;
    }

    public JsonWriter value(boolean z) {
        writeDeferredName();
        beforeValue();
        this.out.write(z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        return this;
    }
}
