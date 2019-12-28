package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public abstract class zzgm {
    private final void zza(boolean z, Object obj) {
        boolean z2;
        if (obj != null) {
            Class<?> cls = obj.getClass();
            if (zzhl.isNull(obj)) {
                zzfm();
            } else if (obj instanceof String) {
                writeString((String) obj);
            } else {
                boolean z3 = true;
                if (obj instanceof Number) {
                    if (z) {
                        writeString(obj.toString());
                    } else if (obj instanceof BigDecimal) {
                        zza((BigDecimal) obj);
                    } else if (obj instanceof BigInteger) {
                        zza((BigInteger) obj);
                    } else if (obj instanceof Long) {
                        zzd(((Long) obj).longValue());
                    } else if (obj instanceof Float) {
                        float floatValue = ((Number) obj).floatValue();
                        if (Float.isInfinite(floatValue) || Float.isNaN(floatValue)) {
                            z3 = false;
                        }
                        zzky.checkArgument(z3);
                        zzj(floatValue);
                    } else if ((obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                        zzab(((Number) obj).intValue());
                    } else {
                        double doubleValue = ((Number) obj).doubleValue();
                        if (Double.isInfinite(doubleValue) || Double.isNaN(doubleValue)) {
                            z3 = false;
                        }
                        zzky.checkArgument(z3);
                        zza(doubleValue);
                    }
                } else if (obj instanceof Boolean) {
                    writeBoolean(((Boolean) obj).booleanValue());
                } else if (obj instanceof zzhq) {
                    writeString(((zzhq) obj).zzgo());
                } else if ((obj instanceof Iterable) || cls.isArray()) {
                    zzfi();
                    for (Object zza : zzig.zzi(obj)) {
                        zza(z, zza);
                    }
                    zzfj();
                } else if (cls.isEnum()) {
                    String name = zzhr.zza((Enum<?>) (Enum) obj).getName();
                    if (name == null) {
                        zzfm();
                    } else {
                        writeString(name);
                    }
                } else {
                    zzfk();
                    boolean z4 = (obj instanceof Map) && !(obj instanceof zzhs);
                    zzhj zzd = z4 ? null : zzhj.zzd(cls);
                    for (Map.Entry next : zzhl.zzf(obj).entrySet()) {
                        Object value = next.getValue();
                        if (value != null) {
                            String str = (String) next.getKey();
                            if (z4) {
                                z2 = z;
                            } else {
                                zzhr zzal = zzd.zzal(str);
                                Field zzgp = zzal == null ? null : zzal.zzgp();
                                z2 = (zzgp == null || zzgp.getAnnotation(zzgs.class) == null) ? false : true;
                            }
                            zzak(str);
                            zza(z2, value);
                        }
                    }
                    zzfl();
                }
            }
        }
    }

    public abstract void flush();

    public abstract void writeBoolean(boolean z);

    public abstract void writeString(String str);

    public abstract void zza(double d);

    public abstract void zza(BigDecimal bigDecimal);

    public abstract void zza(BigInteger bigInteger);

    public abstract void zzab(int i);

    public abstract void zzak(String str);

    public abstract void zzd(long j);

    public final void zzd(Object obj) {
        zza(false, obj);
    }

    public abstract void zzfi();

    public abstract void zzfj();

    public abstract void zzfk();

    public abstract void zzfl();

    public abstract void zzfm();

    public void zzfn() {
    }

    public abstract void zzj(float f);
}
