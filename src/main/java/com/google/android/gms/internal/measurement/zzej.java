package com.google.android.gms.internal.measurement;

final class zzej {
    private static final Class<?> zzaeq = zztl();

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x000e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.measurement.zzel a() {
        /*
            java.lang.Class<?> r0 = zzaeq
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = "loadGeneratedRegistry"
            com.google.android.gms.internal.measurement.zzel r0 = zzdu(r0)     // Catch:{ Exception -> 0x000b }
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            if (r0 != 0) goto L_0x0012
            com.google.android.gms.internal.measurement.zzel r0 = com.google.android.gms.internal.measurement.zzel.a()
        L_0x0012:
            if (r0 != 0) goto L_0x0018
            com.google.android.gms.internal.measurement.zzel r0 = zztm()
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzej.a():com.google.android.gms.internal.measurement.zzel");
    }

    private static final zzel zzdu(String str) {
        return (zzel) zzaeq.getDeclaredMethod(str, new Class[0]).invoke((Object) null, new Object[0]);
    }

    private static Class<?> zztl() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzel zztm() {
        if (zzaeq != null) {
            try {
                return zzdu("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return zzel.a;
    }
}
