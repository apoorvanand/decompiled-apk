package com.google.android.gms.internal.firebase_ml;

import com.facebook.internal.ServerProtocol;
import java.io.EOFException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

final class zzgx extends zzgp {
    private final zzgu zzxf;
    private final zzpl zzxg;
    private List<String> zzxh = new ArrayList();
    private zzgt zzxi;
    private String zzxj;

    zzgx(zzgu zzgu, zzpl zzpl) {
        this.zzxf = zzgu;
        this.zzxg = zzpl;
        zzpl.setLenient(true);
    }

    private final void zzgi() {
        zzky.checkArgument(this.zzxi == zzgt.VALUE_NUMBER_INT || this.zzxi == zzgt.VALUE_NUMBER_FLOAT);
    }

    public final void close() {
        this.zzxg.close();
    }

    public final int getIntValue() {
        zzgi();
        return Integer.parseInt(this.zzxj);
    }

    public final String getText() {
        return this.zzxj;
    }

    public final zzgl zzfq() {
        return this.zzxf;
    }

    public final zzgt zzfr() {
        zzpn zzpn;
        zzgt zzgt;
        if (this.zzxi != null) {
            switch (zzgy.a[this.zzxi.ordinal()]) {
                case 1:
                    this.zzxg.beginArray();
                    break;
                case 2:
                    this.zzxg.beginObject();
                    break;
            }
            this.zzxh.add((Object) null);
        }
        try {
            zzpn = this.zzxg.zzme();
        } catch (EOFException unused) {
            zzpn = zzpn.END_DOCUMENT;
        }
        switch (zzgy.b[zzpn.ordinal()]) {
            case 1:
                this.zzxj = "[";
                zzgt = zzgt.START_ARRAY;
                break;
            case 2:
                this.zzxj = "]";
                this.zzxi = zzgt.END_ARRAY;
                List<String> list = this.zzxh;
                list.remove(list.size() - 1);
                this.zzxg.endArray();
                break;
            case 3:
                this.zzxj = "{";
                zzgt = zzgt.START_OBJECT;
                break;
            case 4:
                this.zzxj = "}";
                this.zzxi = zzgt.END_OBJECT;
                List<String> list2 = this.zzxh;
                list2.remove(list2.size() - 1);
                this.zzxg.endObject();
                break;
            case 5:
                if (!this.zzxg.nextBoolean()) {
                    this.zzxj = "false";
                    zzgt = zzgt.VALUE_FALSE;
                    break;
                } else {
                    this.zzxj = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
                    zzgt = zzgt.VALUE_TRUE;
                    break;
                }
            case 6:
                this.zzxj = "null";
                this.zzxi = zzgt.VALUE_NULL;
                this.zzxg.nextNull();
                break;
            case 7:
                this.zzxj = this.zzxg.nextString();
                zzgt = zzgt.VALUE_STRING;
                break;
            case 8:
                this.zzxj = this.zzxg.nextString();
                if (this.zzxj.indexOf(46) != -1) {
                    zzgt = zzgt.VALUE_NUMBER_FLOAT;
                    break;
                } else {
                    zzgt = zzgt.VALUE_NUMBER_INT;
                    break;
                }
            case 9:
                this.zzxj = this.zzxg.nextName();
                this.zzxi = zzgt.FIELD_NAME;
                List<String> list3 = this.zzxh;
                list3.set(list3.size() - 1, this.zzxj);
                break;
            default:
                this.zzxj = null;
                this.zzxi = null;
                break;
        }
        this.zzxi = zzgt;
        return this.zzxi;
    }

    public final zzgt zzfs() {
        return this.zzxi;
    }

    public final String zzft() {
        if (this.zzxh.isEmpty()) {
            return null;
        }
        List<String> list = this.zzxh;
        return list.get(list.size() - 1);
    }

    public final zzgp zzfu() {
        zzgt zzgt;
        if (this.zzxi != null) {
            switch (zzgy.a[this.zzxi.ordinal()]) {
                case 1:
                    this.zzxg.skipValue();
                    this.zzxj = "]";
                    zzgt = zzgt.END_ARRAY;
                    break;
                case 2:
                    this.zzxg.skipValue();
                    this.zzxj = "}";
                    zzgt = zzgt.END_OBJECT;
                    break;
            }
            this.zzxi = zzgt;
        }
        return this;
    }

    public final byte zzfv() {
        zzgi();
        return Byte.parseByte(this.zzxj);
    }

    public final short zzfw() {
        zzgi();
        return Short.parseShort(this.zzxj);
    }

    public final float zzfx() {
        zzgi();
        return Float.parseFloat(this.zzxj);
    }

    public final long zzfy() {
        zzgi();
        return Long.parseLong(this.zzxj);
    }

    public final double zzfz() {
        zzgi();
        return Double.parseDouble(this.zzxj);
    }

    public final BigInteger zzga() {
        zzgi();
        return new BigInteger(this.zzxj);
    }

    public final BigDecimal zzgb() {
        zzgi();
        return new BigDecimal(this.zzxj);
    }
}
