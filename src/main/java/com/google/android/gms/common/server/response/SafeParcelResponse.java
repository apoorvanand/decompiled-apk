package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@KeepForSdk
@SafeParcelable.Class(creator = "SafeParcelResponseCreator")
@VisibleForTesting
public class SafeParcelResponse extends FastSafeParcelableJsonResponse {
    @KeepForSdk
    public static final Parcelable.Creator<SafeParcelResponse> CREATOR = new zap();
    private final String mClassName;
    @SafeParcelable.VersionField(getter = "getVersionCode", id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getFieldMappingDictionary", id = 3)
    private final zak zapz;
    @SafeParcelable.Field(getter = "getParcel", id = 2)
    private final Parcel zarb;
    private final int zarc;
    private int zard;
    private int zare;

    @SafeParcelable.Constructor
    SafeParcelResponse(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) Parcel parcel, @SafeParcelable.Param(id = 3) zak zak) {
        this.zalf = i;
        this.zarb = (Parcel) Preconditions.checkNotNull(parcel);
        this.zarc = 2;
        this.zapz = zak;
        zak zak2 = this.zapz;
        this.mClassName = zak2 == null ? null : zak2.zact();
        this.zard = 2;
    }

    private SafeParcelResponse(SafeParcelable safeParcelable, zak zak, String str) {
        this.zalf = 1;
        this.zarb = Parcel.obtain();
        safeParcelable.writeToParcel(this.zarb, 0);
        this.zarc = 1;
        this.zapz = (zak) Preconditions.checkNotNull(zak);
        this.mClassName = (String) Preconditions.checkNotNull(str);
        this.zard = 2;
    }

    public SafeParcelResponse(zak zak, String str) {
        this.zalf = 1;
        this.zarb = Parcel.obtain();
        this.zarc = 0;
        this.zapz = (zak) Preconditions.checkNotNull(zak);
        this.mClassName = (String) Preconditions.checkNotNull(str);
        this.zard = 0;
    }

    @KeepForSdk
    public static <T extends FastJsonResponse & SafeParcelable> SafeParcelResponse from(T t) {
        String canonicalName = t.getClass().getCanonicalName();
        zak zak = new zak(t.getClass());
        zaa(zak, t);
        zak.zacs();
        zak.zacr();
        return new SafeParcelResponse((SafeParcelable) t, zak, canonicalName);
    }

    private static void zaa(zak zak, FastJsonResponse fastJsonResponse) {
        Class<?> cls = fastJsonResponse.getClass();
        if (!zak.zaa(cls)) {
            Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
            zak.zaa(cls, fieldMappings);
            for (String str : fieldMappings.keySet()) {
                FastJsonResponse.Field field = fieldMappings.get(str);
                Class<? extends FastJsonResponse> cls2 = field.g;
                if (cls2 != null) {
                    try {
                        zaa(zak, (FastJsonResponse) cls2.newInstance());
                    } catch (InstantiationException e) {
                        String valueOf = String.valueOf(field.g.getCanonicalName());
                        throw new IllegalStateException(valueOf.length() != 0 ? "Could not instantiate an object of type ".concat(valueOf) : new String("Could not instantiate an object of type "), e);
                    } catch (IllegalAccessException e2) {
                        String valueOf2 = String.valueOf(field.g.getCanonicalName());
                        throw new IllegalStateException(valueOf2.length() != 0 ? "Could not access object of type ".concat(valueOf2) : new String("Could not access object of type "), e2);
                    }
                }
            }
        }
    }

    private static void zaa(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"");
                sb.append(JsonUtils.escapeString(obj.toString()));
                sb.append("\"");
                return;
            case 8:
                sb.append("\"");
                sb.append(Base64Utils.encode((byte[]) obj));
                sb.append("\"");
                return;
            case 9:
                sb.append("\"");
                sb.append(Base64Utils.encodeUrlSafe((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                MapUtils.writeStringMapToJson(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                StringBuilder sb2 = new StringBuilder(26);
                sb2.append("Unknown type = ");
                sb2.append(i);
                throw new IllegalArgumentException(sb2.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0101, code lost:
        r3 = a(r5, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0105, code lost:
        zab(r10, r5, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0179, code lost:
        com.google.android.gms.common.util.ArrayUtils.writeArray(r10, (T[]) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0184, code lost:
        r3 = "]";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0222, code lost:
        r10.append(r3);
        r3 = "\"";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0254, code lost:
        r10.append(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zaa(java.lang.StringBuilder r10, java.util.Map<java.lang.String, com.google.android.gms.common.server.response.FastJsonResponse.Field<?, ?>> r11, android.os.Parcel r12) {
        /*
            r9 = this;
            android.util.SparseArray r0 = new android.util.SparseArray
            r0.<init>()
            java.util.Set r11 = r11.entrySet()
            java.util.Iterator r11 = r11.iterator()
        L_0x000d:
            boolean r1 = r11.hasNext()
            if (r1 == 0) goto L_0x0027
            java.lang.Object r1 = r11.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getValue()
            com.google.android.gms.common.server.response.FastJsonResponse$Field r2 = (com.google.android.gms.common.server.response.FastJsonResponse.Field) r2
            int r2 = r2.getSafeParcelableFieldId()
            r0.put(r2, r1)
            goto L_0x000d
        L_0x0027:
            r11 = 123(0x7b, float:1.72E-43)
            r10.append(r11)
            int r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r12)
            r1 = 1
            r2 = 0
            r3 = 0
        L_0x0033:
            int r4 = r12.dataPosition()
            if (r4 >= r11) goto L_0x0262
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r12)
            int r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r4)
            java.lang.Object r5 = r0.get(r5)
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            if (r5 == 0) goto L_0x0033
            if (r3 == 0) goto L_0x0050
            java.lang.String r3 = ","
            r10.append(r3)
        L_0x0050:
            java.lang.Object r3 = r5.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r5 = r5.getValue()
            com.google.android.gms.common.server.response.FastJsonResponse$Field r5 = (com.google.android.gms.common.server.response.FastJsonResponse.Field) r5
            java.lang.String r6 = "\""
            r10.append(r6)
            r10.append(r3)
            java.lang.String r3 = "\":"
            r10.append(r3)
            boolean r3 = r5.zacn()
            if (r3 == 0) goto L_0x010a
            int r3 = r5.c
            switch(r3) {
                case 0: goto L_0x00f9;
                case 1: goto L_0x00f4;
                case 2: goto L_0x00eb;
                case 3: goto L_0x00e2;
                case 4: goto L_0x00d9;
                case 5: goto L_0x00d4;
                case 6: goto L_0x00cb;
                case 7: goto L_0x00c6;
                case 8: goto L_0x00c1;
                case 9: goto L_0x00c1;
                case 10: goto L_0x0097;
                case 11: goto L_0x008f;
                default: goto L_0x0074;
            }
        L_0x0074:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            int r11 = r5.c
            r12 = 36
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r12)
            java.lang.String r12 = "Unknown field out type = "
            r0.append(r12)
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            r10.<init>(r11)
            throw r10
        L_0x008f:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "Method does not accept concrete type."
            r10.<init>(r11)
            throw r10
        L_0x0097:
            android.os.Bundle r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r12, r4)
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.util.Set r6 = r3.keySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x00a8:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00bc
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r8 = r3.getString(r7)
            r4.put(r7, r8)
            goto L_0x00a8
        L_0x00bc:
            java.lang.Object r3 = a(r5, r4)
            goto L_0x0105
        L_0x00c1:
            byte[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r12, r4)
            goto L_0x0101
        L_0x00c6:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r4)
            goto L_0x0101
        L_0x00cb:
            boolean r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r12, r4)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            goto L_0x0101
        L_0x00d4:
            java.math.BigDecimal r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBigDecimal(r12, r4)
            goto L_0x0101
        L_0x00d9:
            double r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDouble(r12, r4)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            goto L_0x0101
        L_0x00e2:
            float r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r12, r4)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x0101
        L_0x00eb:
            long r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r12, r4)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x0101
        L_0x00f4:
            java.math.BigInteger r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBigInteger(r12, r4)
            goto L_0x0101
        L_0x00f9:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r12, r4)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x0101:
            java.lang.Object r3 = a(r5, r3)
        L_0x0105:
            r9.zab(r10, r5, r3)
            goto L_0x025f
        L_0x010a:
            boolean r3 = r5.d
            if (r3 == 0) goto L_0x0188
            java.lang.String r3 = "["
            r10.append(r3)
            int r3 = r5.c
            switch(r3) {
                case 0: goto L_0x017d;
                case 1: goto L_0x0175;
                case 2: goto L_0x016d;
                case 3: goto L_0x0165;
                case 4: goto L_0x015d;
                case 5: goto L_0x0158;
                case 6: goto L_0x0150;
                case 7: goto L_0x0148;
                case 8: goto L_0x0140;
                case 9: goto L_0x0140;
                case 10: goto L_0x0140;
                case 11: goto L_0x0120;
                default: goto L_0x0118;
            }
        L_0x0118:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Unknown field type out."
            r10.<init>(r11)
            throw r10
        L_0x0120:
            android.os.Parcel[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelArray(r12, r4)
            int r4 = r3.length
            r6 = 0
        L_0x0126:
            if (r6 >= r4) goto L_0x0184
            if (r6 <= 0) goto L_0x012f
            java.lang.String r7 = ","
            r10.append(r7)
        L_0x012f:
            r7 = r3[r6]
            r7.setDataPosition(r2)
            java.util.Map r7 = r5.zacq()
            r8 = r3[r6]
            r9.zaa((java.lang.StringBuilder) r10, (java.util.Map<java.lang.String, com.google.android.gms.common.server.response.FastJsonResponse.Field<?, ?>>) r7, (android.os.Parcel) r8)
            int r6 = r6 + 1
            goto L_0x0126
        L_0x0140:
            java.lang.UnsupportedOperationException r10 = new java.lang.UnsupportedOperationException
            java.lang.String r11 = "List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported"
            r10.<init>(r11)
            throw r10
        L_0x0148:
            java.lang.String[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringArray(r12, r4)
            com.google.android.gms.common.util.ArrayUtils.writeStringArray(r10, r3)
            goto L_0x0184
        L_0x0150:
            boolean[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBooleanArray(r12, r4)
            com.google.android.gms.common.util.ArrayUtils.writeArray((java.lang.StringBuilder) r10, (boolean[]) r3)
            goto L_0x0184
        L_0x0158:
            java.math.BigDecimal[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBigDecimalArray(r12, r4)
            goto L_0x0179
        L_0x015d:
            double[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createDoubleArray(r12, r4)
            com.google.android.gms.common.util.ArrayUtils.writeArray((java.lang.StringBuilder) r10, (double[]) r3)
            goto L_0x0184
        L_0x0165:
            float[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createFloatArray(r12, r4)
            com.google.android.gms.common.util.ArrayUtils.writeArray((java.lang.StringBuilder) r10, (float[]) r3)
            goto L_0x0184
        L_0x016d:
            long[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createLongArray(r12, r4)
            com.google.android.gms.common.util.ArrayUtils.writeArray((java.lang.StringBuilder) r10, (long[]) r3)
            goto L_0x0184
        L_0x0175:
            java.math.BigInteger[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBigIntegerArray(r12, r4)
        L_0x0179:
            com.google.android.gms.common.util.ArrayUtils.writeArray((java.lang.StringBuilder) r10, (T[]) r3)
            goto L_0x0184
        L_0x017d:
            int[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createIntArray(r12, r4)
            com.google.android.gms.common.util.ArrayUtils.writeArray((java.lang.StringBuilder) r10, (int[]) r3)
        L_0x0184:
            java.lang.String r3 = "]"
            goto L_0x0227
        L_0x0188:
            int r3 = r5.c
            switch(r3) {
                case 0: goto L_0x0258;
                case 1: goto L_0x0250;
                case 2: goto L_0x0248;
                case 3: goto L_0x0240;
                case 4: goto L_0x0238;
                case 5: goto L_0x0233;
                case 6: goto L_0x022b;
                case 7: goto L_0x0215;
                case 8: goto L_0x0207;
                case 9: goto L_0x01f9;
                case 10: goto L_0x01a5;
                case 11: goto L_0x0195;
                default: goto L_0x018d;
            }
        L_0x018d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Unknown field type out"
            r10.<init>(r11)
            throw r10
        L_0x0195:
            android.os.Parcel r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcel(r12, r4)
            r3.setDataPosition(r2)
            java.util.Map r4 = r5.zacq()
            r9.zaa((java.lang.StringBuilder) r10, (java.util.Map<java.lang.String, com.google.android.gms.common.server.response.FastJsonResponse.Field<?, ?>>) r4, (android.os.Parcel) r3)
            goto L_0x025f
        L_0x01a5:
            android.os.Bundle r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r12, r4)
            java.util.Set r4 = r3.keySet()
            r4.size()
            java.lang.String r5 = "{"
            r10.append(r5)
            java.util.Iterator r4 = r4.iterator()
            r5 = 1
        L_0x01ba:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x01f6
            java.lang.Object r6 = r4.next()
            java.lang.String r6 = (java.lang.String) r6
            if (r5 != 0) goto L_0x01cd
            java.lang.String r5 = ","
            r10.append(r5)
        L_0x01cd:
            java.lang.String r5 = "\""
            r10.append(r5)
            r10.append(r6)
            java.lang.String r5 = "\""
            r10.append(r5)
            java.lang.String r5 = ":"
            r10.append(r5)
            java.lang.String r5 = "\""
            r10.append(r5)
            java.lang.String r5 = r3.getString(r6)
            java.lang.String r5 = com.google.android.gms.common.util.JsonUtils.escapeString(r5)
            r10.append(r5)
            java.lang.String r5 = "\""
            r10.append(r5)
            r5 = 0
            goto L_0x01ba
        L_0x01f6:
            java.lang.String r3 = "}"
            goto L_0x0227
        L_0x01f9:
            byte[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r12, r4)
            java.lang.String r4 = "\""
            r10.append(r4)
            java.lang.String r3 = com.google.android.gms.common.util.Base64Utils.encodeUrlSafe(r3)
            goto L_0x0222
        L_0x0207:
            byte[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r12, r4)
            java.lang.String r4 = "\""
            r10.append(r4)
            java.lang.String r3 = com.google.android.gms.common.util.Base64Utils.encode(r3)
            goto L_0x0222
        L_0x0215:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r4)
            java.lang.String r4 = "\""
            r10.append(r4)
            java.lang.String r3 = com.google.android.gms.common.util.JsonUtils.escapeString(r3)
        L_0x0222:
            r10.append(r3)
            java.lang.String r3 = "\""
        L_0x0227:
            r10.append(r3)
            goto L_0x025f
        L_0x022b:
            boolean r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r12, r4)
            r10.append(r3)
            goto L_0x025f
        L_0x0233:
            java.math.BigDecimal r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBigDecimal(r12, r4)
            goto L_0x0254
        L_0x0238:
            double r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDouble(r12, r4)
            r10.append(r3)
            goto L_0x025f
        L_0x0240:
            float r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r12, r4)
            r10.append(r3)
            goto L_0x025f
        L_0x0248:
            long r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r12, r4)
            r10.append(r3)
            goto L_0x025f
        L_0x0250:
            java.math.BigInteger r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBigInteger(r12, r4)
        L_0x0254:
            r10.append(r3)
            goto L_0x025f
        L_0x0258:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r12, r4)
            r10.append(r3)
        L_0x025f:
            r3 = 1
            goto L_0x0033
        L_0x0262:
            int r0 = r12.dataPosition()
            if (r0 != r11) goto L_0x026e
            r11 = 125(0x7d, float:1.75E-43)
            r10.append(r11)
            return
        L_0x026e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r10 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            r0 = 37
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Overread allowed size end="
            r1.append(r0)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r10.<init>(r11, r12)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.SafeParcelResponse.zaa(java.lang.StringBuilder, java.util.Map, android.os.Parcel):void");
    }

    private final void zab(FastJsonResponse.Field<?, ?> field) {
        if (field.f != -1) {
            Parcel parcel = this.zarb;
            if (parcel != null) {
                switch (this.zard) {
                    case 0:
                        this.zare = SafeParcelWriter.beginObjectHeader(parcel);
                        this.zard = 1;
                        return;
                    case 1:
                        return;
                    case 2:
                        throw new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
                    default:
                        throw new IllegalStateException("Unknown parse state in SafeParcelResponse.");
                }
            } else {
                throw new IllegalStateException("Internal Parcel object is null.");
            }
        } else {
            throw new IllegalStateException("Field does not have a valid safe parcelable field id.");
        }
    }

    private final void zab(StringBuilder sb, FastJsonResponse.Field<?, ?> field, Object obj) {
        if (field.b) {
            ArrayList arrayList = (ArrayList) obj;
            sb.append("[");
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    sb.append(",");
                }
                zaa(sb, field.a, arrayList.get(i));
            }
            sb.append("]");
            return;
        }
        zaa(sb, field.a, obj);
    }

    private final Parcel zacu() {
        switch (this.zard) {
            case 0:
                this.zare = SafeParcelWriter.beginObjectHeader(this.zarb);
                break;
            case 1:
                break;
        }
        SafeParcelWriter.finishObjectHeader(this.zarb, this.zare);
        this.zard = 2;
        return this.zarb;
    }

    /* access modifiers changed from: protected */
    public final void a(FastJsonResponse.Field<?, ?> field, String str, double d) {
        zab(field);
        SafeParcelWriter.writeDouble(this.zarb, field.getSafeParcelableFieldId(), d);
    }

    /* access modifiers changed from: protected */
    public final void a(FastJsonResponse.Field<?, ?> field, String str, float f) {
        zab(field);
        SafeParcelWriter.writeFloat(this.zarb, field.getSafeParcelableFieldId(), f);
    }

    /* access modifiers changed from: protected */
    public void a(FastJsonResponse.Field<?, ?> field, String str, int i) {
        zab(field);
        SafeParcelWriter.writeInt(this.zarb, field.getSafeParcelableFieldId(), i);
    }

    /* access modifiers changed from: protected */
    public void a(FastJsonResponse.Field<?, ?> field, String str, long j) {
        zab(field);
        SafeParcelWriter.writeLong(this.zarb, field.getSafeParcelableFieldId(), j);
    }

    /* access modifiers changed from: protected */
    public void a(FastJsonResponse.Field<?, ?> field, String str, String str2) {
        zab(field);
        SafeParcelWriter.writeString(this.zarb, field.getSafeParcelableFieldId(), str2, true);
    }

    /* access modifiers changed from: protected */
    public final void a(FastJsonResponse.Field<?, ?> field, String str, BigDecimal bigDecimal) {
        zab(field);
        SafeParcelWriter.writeBigDecimal(this.zarb, field.getSafeParcelableFieldId(), bigDecimal, true);
    }

    /* access modifiers changed from: protected */
    public final void a(FastJsonResponse.Field<?, ?> field, String str, BigInteger bigInteger) {
        zab(field);
        SafeParcelWriter.writeBigInteger(this.zarb, field.getSafeParcelableFieldId(), bigInteger, true);
    }

    /* access modifiers changed from: protected */
    public void a(FastJsonResponse.Field<?, ?> field, String str, ArrayList<String> arrayList) {
        zab(field);
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = arrayList.get(i);
        }
        SafeParcelWriter.writeStringArray(this.zarb, field.getSafeParcelableFieldId(), strArr, true);
    }

    /* access modifiers changed from: protected */
    public final void a(FastJsonResponse.Field<?, ?> field, String str, Map<String, String> map) {
        zab(field);
        Bundle bundle = new Bundle();
        for (String next : map.keySet()) {
            bundle.putString(next, map.get(next));
        }
        SafeParcelWriter.writeBundle(this.zarb, field.getSafeParcelableFieldId(), bundle, true);
    }

    /* access modifiers changed from: protected */
    public void a(FastJsonResponse.Field<?, ?> field, String str, boolean z) {
        zab(field);
        SafeParcelWriter.writeBoolean(this.zarb, field.getSafeParcelableFieldId(), z);
    }

    /* access modifiers changed from: protected */
    public void a(FastJsonResponse.Field<?, ?> field, String str, byte[] bArr) {
        zab(field);
        SafeParcelWriter.writeByteArray(this.zarb, field.getSafeParcelableFieldId(), bArr, true);
    }

    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(FastJsonResponse.Field<?, ?> field, String str, ArrayList<T> arrayList) {
        zab(field);
        ArrayList arrayList2 = new ArrayList();
        arrayList.size();
        ArrayList arrayList3 = arrayList;
        int size = arrayList3.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList3.get(i);
            i++;
            arrayList2.add(((SafeParcelResponse) ((FastJsonResponse) obj)).zacu());
        }
        SafeParcelWriter.writeParcelList(this.zarb, field.getSafeParcelableFieldId(), arrayList2, true);
    }

    public <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String str, T t) {
        zab(field);
        SafeParcelWriter.writeParcel(this.zarb, field.getSafeParcelableFieldId(), ((SafeParcelResponse) t).zacu(), true);
    }

    /* access modifiers changed from: protected */
    public final void b(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Integer> arrayList) {
        zab(field);
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = arrayList.get(i).intValue();
        }
        SafeParcelWriter.writeIntArray(this.zarb, field.getSafeParcelableFieldId(), iArr, true);
    }

    /* access modifiers changed from: protected */
    public final void c(FastJsonResponse.Field<?, ?> field, String str, ArrayList<BigInteger> arrayList) {
        zab(field);
        int size = arrayList.size();
        BigInteger[] bigIntegerArr = new BigInteger[size];
        for (int i = 0; i < size; i++) {
            bigIntegerArr[i] = arrayList.get(i);
        }
        SafeParcelWriter.writeBigIntegerArray(this.zarb, field.getSafeParcelableFieldId(), bigIntegerArr, true);
    }

    /* access modifiers changed from: protected */
    public final void d(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Long> arrayList) {
        zab(field);
        int size = arrayList.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = arrayList.get(i).longValue();
        }
        SafeParcelWriter.writeLongArray(this.zarb, field.getSafeParcelableFieldId(), jArr, true);
    }

    /* access modifiers changed from: protected */
    public final void e(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Float> arrayList) {
        zab(field);
        int size = arrayList.size();
        float[] fArr = new float[size];
        for (int i = 0; i < size; i++) {
            fArr[i] = arrayList.get(i).floatValue();
        }
        SafeParcelWriter.writeFloatArray(this.zarb, field.getSafeParcelableFieldId(), fArr, true);
    }

    /* access modifiers changed from: protected */
    public final void f(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Double> arrayList) {
        zab(field);
        int size = arrayList.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = arrayList.get(i).doubleValue();
        }
        SafeParcelWriter.writeDoubleArray(this.zarb, field.getSafeParcelableFieldId(), dArr, true);
    }

    /* access modifiers changed from: protected */
    public final void g(FastJsonResponse.Field<?, ?> field, String str, ArrayList<BigDecimal> arrayList) {
        zab(field);
        int size = arrayList.size();
        BigDecimal[] bigDecimalArr = new BigDecimal[size];
        for (int i = 0; i < size; i++) {
            bigDecimalArr[i] = arrayList.get(i);
        }
        SafeParcelWriter.writeBigDecimalArray(this.zarb, field.getSafeParcelableFieldId(), bigDecimalArr, true);
    }

    public Map<String, FastJsonResponse.Field<?, ?>> getFieldMappings() {
        zak zak = this.zapz;
        if (zak == null) {
            return null;
        }
        return zak.zai(this.mClassName);
    }

    public Object getValueObject(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    /* access modifiers changed from: protected */
    public final void h(FastJsonResponse.Field<?, ?> field, String str, ArrayList<Boolean> arrayList) {
        zab(field);
        int size = arrayList.size();
        boolean[] zArr = new boolean[size];
        for (int i = 0; i < size; i++) {
            zArr[i] = arrayList.get(i).booleanValue();
        }
        SafeParcelWriter.writeBooleanArray(this.zarb, field.getSafeParcelableFieldId(), zArr, true);
    }

    public boolean isPrimitiveFieldSet(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public String toString() {
        Preconditions.checkNotNull(this.zapz, "Cannot convert to JSON on client side.");
        Parcel zacu = zacu();
        zacu.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        zaa(sb, this.zapz.zai(this.mClassName), zacu);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zak zak;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeParcel(parcel, 2, zacu(), false);
        int i2 = this.zarc;
        switch (i2) {
            case 0:
                zak = null;
                break;
            case 1:
            case 2:
                zak = this.zapz;
                break;
            default:
                StringBuilder sb = new StringBuilder(34);
                sb.append("Invalid creation type: ");
                sb.append(i2);
                throw new IllegalStateException(sb.toString());
        }
        SafeParcelWriter.writeParcelable(parcel, 3, zak, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
