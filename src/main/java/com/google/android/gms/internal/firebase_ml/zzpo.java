package com.google.android.gms.internal.firebase_ml;

import com.facebook.internal.ServerProtocol;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public final class zzpo implements Closeable, Flushable {
    private static final String[] zzayp = new String[128];
    private static final String[] zzayq;
    private final Writer out;
    private String separator;
    private boolean zzaxs;
    private int[] zzaya = new int[32];
    private int zzayb = 0;
    private String zzayr;
    private String zzays;
    private boolean zzayt;

    static {
        for (int i = 0; i <= 31; i++) {
            zzayp[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        String[] strArr = zzayp;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        zzayq = strArr2;
        strArr2[60] = "\\u003c";
        String[] strArr3 = zzayq;
        strArr3[62] = "\\u003e";
        strArr3[38] = "\\u0026";
        strArr3[61] = "\\u003d";
        strArr3[39] = "\\u0027";
    }

    public zzpo(Writer writer) {
        zzbj(6);
        this.separator = ":";
        this.zzayt = true;
        this.out = writer;
    }

    private final int peek() {
        int i = this.zzayb;
        if (i != 0) {
            return this.zzaya[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private final zzpo zza(int i, String str) {
        zzms();
        zzbj(i);
        this.out.write(str);
        return this;
    }

    private final zzpo zzb(int i, int i2, String str) {
        int peek = peek();
        if (peek != i2 && peek != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.zzays == null) {
            this.zzayb--;
            if (peek == i2) {
                zzmr();
            }
            this.out.write(str);
            return this;
        } else {
            throw new IllegalStateException("Dangling name: " + this.zzays);
        }
    }

    private final void zzbj(int i) {
        int i2 = this.zzayb;
        int[] iArr = this.zzaya;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(i2 << 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.zzaya = iArr2;
        }
        int[] iArr3 = this.zzaya;
        int i3 = this.zzayb;
        this.zzayb = i3 + 1;
        iArr3[i3] = i;
    }

    private final void zzbl(int i) {
        this.zzaya[this.zzayb - 1] = i;
    }

    private final void zzcg(String str) {
        String str2;
        String[] strArr = zzayp;
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

    private final void zzmp() {
        if (this.zzays != null) {
            int peek = peek();
            if (peek == 5) {
                this.out.write(44);
            } else if (peek != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            zzmr();
            zzbl(4);
            zzcg(this.zzays);
            this.zzays = null;
        }
    }

    private final void zzmr() {
        if (this.zzayr != null) {
            this.out.write("\n");
            int i = this.zzayb;
            for (int i2 = 1; i2 < i; i2++) {
                this.out.write(this.zzayr);
            }
        }
    }

    private final void zzms() {
        switch (peek()) {
            case 1:
                zzbl(2);
                zzmr();
                return;
            case 2:
                this.out.append(',');
                zzmr();
                return;
            case 4:
                this.out.append(this.separator);
                zzbl(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.zzaxs) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        zzbl(7);
    }

    public final void close() {
        this.out.close();
        int i = this.zzayb;
        if (i > 1 || (i == 1 && this.zzaya[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.zzayb = 0;
    }

    public final void flush() {
        if (this.zzayb != 0) {
            this.out.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void setIndent(String str) {
        String str2;
        if (str.length() == 0) {
            this.zzayr = null;
            str2 = ":";
        } else {
            this.zzayr = str;
            str2 = ": ";
        }
        this.separator = str2;
    }

    public final void setLenient(boolean z) {
        this.zzaxs = true;
    }

    public final zzpo zza(Number number) {
        if (number == null) {
            return zzmq();
        }
        zzmp();
        String obj = number.toString();
        if (this.zzaxs || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            zzms();
            this.out.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public final zzpo zzaf(boolean z) {
        zzmp();
        zzms();
        this.out.write(z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        return this;
    }

    public final zzpo zzb(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
        zzmp();
        zzms();
        this.out.append(Double.toString(d));
        return this;
    }

    public final zzpo zzce(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.zzays != null) {
            throw new IllegalStateException();
        } else if (this.zzayb != 0) {
            this.zzays = str;
            return this;
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    public final zzpo zzcf(String str) {
        if (str == null) {
            return zzmq();
        }
        zzmp();
        zzms();
        zzcg(str);
        return this;
    }

    public final zzpo zzl(long j) {
        zzmp();
        zzms();
        this.out.write(Long.toString(j));
        return this;
    }

    public final zzpo zzml() {
        zzmp();
        return zza(1, "[");
    }

    public final zzpo zzmm() {
        return zzb(1, 2, "]");
    }

    public final zzpo zzmn() {
        zzmp();
        return zza(3, "{");
    }

    public final zzpo zzmo() {
        return zzb(3, 5, "}");
    }

    public final zzpo zzmq() {
        if (this.zzays != null) {
            if (this.zzayt) {
                zzmp();
            } else {
                this.zzays = null;
                return this;
            }
        }
        zzms();
        this.out.write("null");
        return this;
    }
}
