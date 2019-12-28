package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.converter.zaa;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@KeepForSdk
public abstract class FastJsonResponse {

    @ShowFirstParty
    @SafeParcelable.Class(creator = "FieldCreator")
    @VisibleForTesting
    @KeepForSdk
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zai CREATOR = new zai();
        @SafeParcelable.Field(getter = "getTypeIn", id = 2)
        protected final int a;
        @SafeParcelable.Field(getter = "isTypeInArray", id = 3)
        protected final boolean b;
        @SafeParcelable.Field(getter = "getTypeOut", id = 4)
        protected final int c;
        @SafeParcelable.Field(getter = "isTypeOutArray", id = 5)
        protected final boolean d;
        @SafeParcelable.Field(getter = "getOutputFieldName", id = 6)
        protected final String e;
        @SafeParcelable.Field(getter = "getSafeParcelableFieldId", id = 7)
        protected final int f;
        protected final Class<? extends FastJsonResponse> g;
        @SafeParcelable.VersionField(getter = "getVersionCode", id = 1)
        private final int zalf;
        @SafeParcelable.Field(getter = "getConcreteTypeName", id = 8)
        private final String zapy;
        private zak zapz;
        /* access modifiers changed from: private */
        @SafeParcelable.Field(getter = "getWrappedConverter", id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        public FieldConverter<I, O> zaqa;

        @SafeParcelable.Constructor
        Field(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) int i4, @SafeParcelable.Param(id = 8) String str2, @SafeParcelable.Param(id = 9) zaa zaa) {
            this.zalf = i;
            this.a = i2;
            this.b = z;
            this.c = i3;
            this.d = z2;
            this.e = str;
            this.f = i4;
            if (str2 == null) {
                this.g = null;
                this.zapy = null;
            } else {
                this.g = SafeParcelResponse.class;
                this.zapy = str2;
            }
            if (zaa == null) {
                this.zaqa = null;
            } else {
                this.zaqa = zaa.zaci();
            }
        }

        private Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, FieldConverter<I, O> fieldConverter) {
            this.zalf = 1;
            this.a = i;
            this.b = z;
            this.c = i2;
            this.d = z2;
            this.e = str;
            this.f = i3;
            this.g = cls;
            this.zapy = cls == null ? null : cls.getCanonicalName();
            this.zaqa = fieldConverter;
        }

        @KeepForSdk
        @VisibleForTesting
        public static Field<byte[], byte[]> forBase64(String str, int i) {
            return new Field(8, false, 8, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<Boolean, Boolean> forBoolean(String str, int i) {
            return new Field(6, false, 6, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(String str, int i, Class<T> cls) {
            return new Field(11, false, 11, false, str, i, cls, (FieldConverter) null);
        }

        @KeepForSdk
        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(String str, int i, Class<T> cls) {
            return new Field(11, true, 11, true, str, i, cls, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<Double, Double> forDouble(String str, int i) {
            return new Field(4, false, 4, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<Float, Float> forFloat(String str, int i) {
            return new Field(3, false, 3, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        @VisibleForTesting
        public static Field<Integer, Integer> forInteger(String str, int i) {
            return new Field(0, false, 0, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<Long, Long> forLong(String str, int i) {
            return new Field(2, false, 2, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<String, String> forString(String str, int i) {
            return new Field(7, false, 7, false, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field<ArrayList<String>, ArrayList<String>> forStrings(String str, int i) {
            return new Field(7, true, 7, true, str, i, (Class<? extends FastJsonResponse>) null, (FieldConverter) null);
        }

        @KeepForSdk
        public static Field withConverter(String str, int i, FieldConverter<?, ?> fieldConverter, boolean z) {
            return new Field(fieldConverter.zacj(), z, fieldConverter.zack(), false, str, i, (Class<? extends FastJsonResponse>) null, fieldConverter);
        }

        private final String zacm() {
            String str = this.zapy;
            if (str == null) {
                return null;
            }
            return str;
        }

        private final zaa zaco() {
            FieldConverter<I, O> fieldConverter = this.zaqa;
            if (fieldConverter == null) {
                return null;
            }
            return zaa.zaa(fieldConverter);
        }

        public final O convert(I i) {
            return this.zaqa.convert(i);
        }

        public final I convertBack(O o) {
            return this.zaqa.convertBack(o);
        }

        @KeepForSdk
        public int getSafeParcelableFieldId() {
            return this.f;
        }

        public String toString() {
            Objects.ToStringHelper add = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zalf)).add("typeIn", Integer.valueOf(this.a)).add("typeInArray", Boolean.valueOf(this.b)).add("typeOut", Integer.valueOf(this.c)).add("typeOutArray", Boolean.valueOf(this.d)).add("outputFieldName", this.e).add("safeParcelFieldId", Integer.valueOf(this.f)).add("concreteTypeName", zacm());
            Class<? extends FastJsonResponse> cls = this.g;
            if (cls != null) {
                add.add("concreteType.class", cls.getCanonicalName());
            }
            FieldConverter<I, O> fieldConverter = this.zaqa;
            if (fieldConverter != null) {
                add.add("converterName", fieldConverter.getClass().getCanonicalName());
            }
            return add.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.zalf);
            SafeParcelWriter.writeInt(parcel, 2, this.a);
            SafeParcelWriter.writeBoolean(parcel, 3, this.b);
            SafeParcelWriter.writeInt(parcel, 4, this.c);
            SafeParcelWriter.writeBoolean(parcel, 5, this.d);
            SafeParcelWriter.writeString(parcel, 6, this.e, false);
            SafeParcelWriter.writeInt(parcel, 7, getSafeParcelableFieldId());
            SafeParcelWriter.writeString(parcel, 8, zacm(), false);
            SafeParcelWriter.writeParcelable(parcel, 9, zaco(), i, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        public final void zaa(zak zak) {
            this.zapz = zak;
        }

        public final Field<I, O> zacl() {
            return new Field(this.zalf, this.a, this.b, this.c, this.d, this.e, this.f, this.zapy, zaco());
        }

        public final boolean zacn() {
            return this.zaqa != null;
        }

        public final FastJsonResponse zacp() {
            Class<? extends FastJsonResponse> cls = this.g;
            if (cls != SafeParcelResponse.class) {
                return (FastJsonResponse) cls.newInstance();
            }
            Preconditions.checkNotNull(this.zapz, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
            return new SafeParcelResponse(this.zapz, this.zapy);
        }

        public final Map<String, Field<?, ?>> zacq() {
            Preconditions.checkNotNull(this.zapy);
            Preconditions.checkNotNull(this.zapz);
            return this.zapz.zai(this.zapy);
        }
    }

    @ShowFirstParty
    public interface FieldConverter<I, O> {
        O convert(I i);

        I convertBack(O o);

        int zacj();

        int zack();
    }

    protected static <O, I> I a(Field<I, O> field, Object obj) {
        return field.zaqa != null ? field.convertBack(obj) : obj;
    }

    private final <I, O> void zaa(Field<I, O> field, I i) {
        String str = field.e;
        O convert = field.convert(i);
        switch (field.c) {
            case 0:
                if (zaa(str, convert)) {
                    a((Field<?, ?>) field, str, ((Integer) convert).intValue());
                    return;
                }
                return;
            case 1:
                a((Field<?, ?>) field, str, (BigInteger) convert);
                return;
            case 2:
                if (zaa(str, convert)) {
                    a((Field<?, ?>) field, str, ((Long) convert).longValue());
                    return;
                }
                return;
            case 4:
                if (zaa(str, convert)) {
                    a((Field<?, ?>) field, str, ((Double) convert).doubleValue());
                    return;
                }
                return;
            case 5:
                a((Field<?, ?>) field, str, (BigDecimal) convert);
                return;
            case 6:
                if (zaa(str, convert)) {
                    a((Field<?, ?>) field, str, ((Boolean) convert).booleanValue());
                    return;
                }
                return;
            case 7:
                a((Field<?, ?>) field, str, (String) convert);
                return;
            case 8:
            case 9:
                if (zaa(str, convert)) {
                    a((Field<?, ?>) field, str, (byte[]) convert);
                    return;
                }
                return;
            default:
                int i2 = field.c;
                StringBuilder sb = new StringBuilder(44);
                sb.append("Unsupported type for conversion: ");
                sb.append(i2);
                throw new IllegalStateException(sb.toString());
        }
    }

    private static void zaa(StringBuilder sb, Field field, Object obj) {
        String str;
        if (field.a == 11) {
            str = ((FastJsonResponse) field.g.cast(obj)).toString();
        } else if (field.a == 7) {
            sb.append("\"");
            sb.append(JsonUtils.escapeString((String) obj));
            str = "\"";
        } else {
            sb.append(obj);
            return;
        }
        sb.append(str);
    }

    private static <O> boolean zaa(String str, O o) {
        if (o != null) {
            return true;
        }
        if (!Log.isLoggable("FastJsonResponse", 6)) {
            return false;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 58);
        sb.append("Output field (");
        sb.append(str);
        sb.append(") has a null value, but expected a primitive");
        Log.e("FastJsonResponse", sb.toString());
        return false;
    }

    /* access modifiers changed from: protected */
    public void a(Field<?, ?> field, String str, double d) {
        throw new UnsupportedOperationException("Double not supported");
    }

    /* access modifiers changed from: protected */
    public void a(Field<?, ?> field, String str, float f) {
        throw new UnsupportedOperationException("Float not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void a(Field<?, ?> field, String str, int i) {
        throw new UnsupportedOperationException("Integer not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void a(Field<?, ?> field, String str, long j) {
        throw new UnsupportedOperationException("Long not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void a(Field<?, ?> field, String str, String str2) {
        throw new UnsupportedOperationException("String not supported");
    }

    /* access modifiers changed from: protected */
    public void a(Field<?, ?> field, String str, BigDecimal bigDecimal) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }

    /* access modifiers changed from: protected */
    public void a(Field<?, ?> field, String str, BigInteger bigInteger) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void a(Field<?, ?> field, String str, ArrayList<String> arrayList) {
        throw new UnsupportedOperationException("String list not supported");
    }

    /* access modifiers changed from: protected */
    public void a(Field<?, ?> field, String str, Map<String, String> map) {
        throw new UnsupportedOperationException("String map not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void a(Field<?, ?> field, String str, boolean z) {
        throw new UnsupportedOperationException("Boolean not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void a(Field<?, ?> field, String str, byte[] bArr) {
        throw new UnsupportedOperationException("byte[] not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean a(Field field) {
        if (field.c != 11) {
            return isPrimitiveFieldSet(field.e);
        }
        if (field.d) {
            String str = field.e;
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        String str2 = field.e;
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String str, ArrayList<T> arrayList) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String str, T t) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Object b(Field field) {
        String str = field.e;
        if (field.g == null) {
            return getValueObject(field.e);
        }
        Preconditions.checkState(getValueObject(field.e) == null, "Concrete field shouldn't be value object: %s", field.e);
        boolean z = field.d;
        try {
            char upperCase = Character.toUpperCase(str.charAt(0));
            String substring = str.substring(1);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 4);
            sb.append("get");
            sb.append(upperCase);
            sb.append(substring);
            return getClass().getMethod(sb.toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void b(Field<?, ?> field, String str, ArrayList<Integer> arrayList) {
        throw new UnsupportedOperationException("Integer list not supported");
    }

    /* access modifiers changed from: protected */
    public void c(Field<?, ?> field, String str, ArrayList<BigInteger> arrayList) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }

    /* access modifiers changed from: protected */
    public void d(Field<?, ?> field, String str, ArrayList<Long> arrayList) {
        throw new UnsupportedOperationException("Long list not supported");
    }

    /* access modifiers changed from: protected */
    public void e(Field<?, ?> field, String str, ArrayList<Float> arrayList) {
        throw new UnsupportedOperationException("Float list not supported");
    }

    /* access modifiers changed from: protected */
    public void f(Field<?, ?> field, String str, ArrayList<Double> arrayList) {
        throw new UnsupportedOperationException("Double list not supported");
    }

    /* access modifiers changed from: protected */
    public void g(Field<?, ?> field, String str, ArrayList<BigDecimal> arrayList) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }

    @KeepForSdk
    public abstract Map<String, Field<?, ?>> getFieldMappings();

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract Object getValueObject(String str);

    /* access modifiers changed from: protected */
    public void h(Field<?, ?> field, String str, ArrayList<Boolean> arrayList) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract boolean isPrimitiveFieldSet(String str);

    @KeepForSdk
    public String toString() {
        String str;
        String str2;
        Map<String, Field<?, ?>> fieldMappings = getFieldMappings();
        StringBuilder sb = new StringBuilder(100);
        for (String next : fieldMappings.keySet()) {
            Field field = fieldMappings.get(next);
            if (a(field)) {
                Object a = a(field, b(field));
                sb.append(sb.length() == 0 ? "{" : ",");
                sb.append("\"");
                sb.append(next);
                sb.append("\":");
                if (a == null) {
                    str2 = "null";
                } else {
                    switch (field.c) {
                        case 8:
                            sb.append("\"");
                            str = Base64Utils.encode((byte[]) a);
                            break;
                        case 9:
                            sb.append("\"");
                            str = Base64Utils.encodeUrlSafe((byte[]) a);
                            break;
                        case 10:
                            MapUtils.writeStringMapToJson(sb, (HashMap) a);
                            continue;
                        default:
                            if (field.b) {
                                ArrayList arrayList = (ArrayList) a;
                                sb.append("[");
                                int size = arrayList.size();
                                for (int i = 0; i < size; i++) {
                                    if (i > 0) {
                                        sb.append(",");
                                    }
                                    Object obj = arrayList.get(i);
                                    if (obj != null) {
                                        zaa(sb, field, obj);
                                    }
                                }
                                str2 = "]";
                                break;
                            } else {
                                zaa(sb, field, a);
                                continue;
                            }
                    }
                    sb.append(str);
                    str2 = "\"";
                }
                sb.append(str2);
            }
        }
        sb.append(sb.length() > 0 ? "}" : "{}");
        return sb.toString();
    }

    public final <O> void zaa(Field<Double, O> field, double d) {
        if (field.zaqa != null) {
            zaa(field, Double.valueOf(d));
        } else {
            a((Field<?, ?>) field, field.e, d);
        }
    }

    public final <O> void zaa(Field<Float, O> field, float f) {
        if (field.zaqa != null) {
            zaa(field, Float.valueOf(f));
        } else {
            a((Field<?, ?>) field, field.e, f);
        }
    }

    public final <O> void zaa(Field<Integer, O> field, int i) {
        if (field.zaqa != null) {
            zaa(field, Integer.valueOf(i));
        } else {
            a((Field<?, ?>) field, field.e, i);
        }
    }

    public final <O> void zaa(Field<Long, O> field, long j) {
        if (field.zaqa != null) {
            zaa(field, Long.valueOf(j));
        } else {
            a((Field<?, ?>) field, field.e, j);
        }
    }

    public final <O> void zaa(Field<String, O> field, String str) {
        if (field.zaqa != null) {
            zaa(field, str);
        } else {
            a((Field<?, ?>) field, field.e, str);
        }
    }

    public final <O> void zaa(Field<BigDecimal, O> field, BigDecimal bigDecimal) {
        if (field.zaqa != null) {
            zaa(field, bigDecimal);
        } else {
            a((Field<?, ?>) field, field.e, bigDecimal);
        }
    }

    public final <O> void zaa(Field<BigInteger, O> field, BigInteger bigInteger) {
        if (field.zaqa != null) {
            zaa(field, bigInteger);
        } else {
            a((Field<?, ?>) field, field.e, bigInteger);
        }
    }

    public final <O> void zaa(Field<ArrayList<Integer>, O> field, ArrayList<Integer> arrayList) {
        if (field.zaqa != null) {
            zaa(field, arrayList);
        } else {
            b(field, field.e, arrayList);
        }
    }

    public final <O> void zaa(Field<Map<String, String>, O> field, Map<String, String> map) {
        if (field.zaqa != null) {
            zaa(field, map);
        } else {
            a((Field<?, ?>) field, field.e, map);
        }
    }

    public final <O> void zaa(Field<Boolean, O> field, boolean z) {
        if (field.zaqa != null) {
            zaa(field, Boolean.valueOf(z));
        } else {
            a((Field<?, ?>) field, field.e, z);
        }
    }

    public final <O> void zaa(Field<byte[], O> field, byte[] bArr) {
        if (field.zaqa != null) {
            zaa(field, bArr);
        } else {
            a((Field<?, ?>) field, field.e, bArr);
        }
    }

    public final <O> void zab(Field<ArrayList<BigInteger>, O> field, ArrayList<BigInteger> arrayList) {
        if (field.zaqa != null) {
            zaa(field, arrayList);
        } else {
            c(field, field.e, arrayList);
        }
    }

    public final <O> void zac(Field<ArrayList<Long>, O> field, ArrayList<Long> arrayList) {
        if (field.zaqa != null) {
            zaa(field, arrayList);
        } else {
            d(field, field.e, arrayList);
        }
    }

    public final <O> void zad(Field<ArrayList<Float>, O> field, ArrayList<Float> arrayList) {
        if (field.zaqa != null) {
            zaa(field, arrayList);
        } else {
            e(field, field.e, arrayList);
        }
    }

    public final <O> void zae(Field<ArrayList<Double>, O> field, ArrayList<Double> arrayList) {
        if (field.zaqa != null) {
            zaa(field, arrayList);
        } else {
            f(field, field.e, arrayList);
        }
    }

    public final <O> void zaf(Field<ArrayList<BigDecimal>, O> field, ArrayList<BigDecimal> arrayList) {
        if (field.zaqa != null) {
            zaa(field, arrayList);
        } else {
            g(field, field.e, arrayList);
        }
    }

    public final <O> void zag(Field<ArrayList<Boolean>, O> field, ArrayList<Boolean> arrayList) {
        if (field.zaqa != null) {
            zaa(field, arrayList);
        } else {
            h(field, field.e, arrayList);
        }
    }

    public final <O> void zah(Field<ArrayList<String>, O> field, ArrayList<String> arrayList) {
        if (field.zaqa != null) {
            zaa(field, arrayList);
        } else {
            a((Field<?, ?>) field, field.e, arrayList);
        }
    }
}
