package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzwa {
    private static final Logger logger = Logger.getLogger(zzwa.class.getName());
    private static final Class<?> zzbhy = zzrp.b();
    private static final boolean zzbiu = zzsa();
    private static final Unsafe zzbor = c();
    private static final boolean zzbqo = zzq(Long.TYPE);
    private static final boolean zzbqp = zzq(Integer.TYPE);
    private static final zzd zzbqq;
    private static final boolean zzbqr = zzsb();
    private static final long zzbqs = ((long) zzo(byte[].class));
    private static final long zzbqt = ((long) zzo(boolean[].class));
    private static final long zzbqu = ((long) zzp(boolean[].class));
    private static final long zzbqv = ((long) zzo(int[].class));
    private static final long zzbqw = ((long) zzp(int[].class));
    private static final long zzbqx = ((long) zzo(long[].class));
    private static final long zzbqy = ((long) zzp(long[].class));
    private static final long zzbqz = ((long) zzo(float[].class));
    private static final long zzbra = ((long) zzp(float[].class));
    private static final long zzbrb = ((long) zzo(double[].class));
    private static final long zzbrc = ((long) zzp(double[].class));
    private static final long zzbrd = ((long) zzo(Object[].class));
    private static final long zzbre = ((long) zzp(Object[].class));
    private static final long zzbrf;
    /* access modifiers changed from: private */
    public static final boolean zzbrg = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzwa.zzbrg) {
                zzwa.zzb(obj, j, z);
            } else {
                zzwa.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzwa.zzbrg) {
                zzwa.zza(obj, j, b);
            } else {
                zzwa.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            return zzwa.zzbrg ? zzwa.zzs(obj, j) : zzwa.zzt(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final byte zzy(Object obj, long j) {
            return zzwa.zzbrg ? zzwa.zzq(obj, j) : zzwa.zzr(obj, j);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzwa.zzbrg) {
                zzwa.zzb(obj, j, z);
            } else {
                zzwa.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzwa.zzbrg) {
                zzwa.zza(obj, j, b);
            } else {
                zzwa.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            return zzwa.zzbrg ? zzwa.zzs(obj, j) : zzwa.zzt(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final byte zzy(Object obj, long j) {
            return zzwa.zzbrg ? zzwa.zzq(obj, j) : zzwa.zzr(obj, j);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            this.a.putDouble(obj, j, d);
        }

        public final void zza(Object obj, long j, float f) {
            this.a.putFloat(obj, j, f);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.a.putBoolean(obj, j, z);
        }

        public final void zze(Object obj, long j, byte b) {
            this.a.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.a.getBoolean(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return this.a.getFloat(obj, j);
        }

        public final double zzo(Object obj, long j) {
            return this.a.getDouble(obj, j);
        }

        public final byte zzy(Object obj, long j) {
            return this.a.getByte(obj, j);
        }
    }

    static abstract class zzd {
        Unsafe a;

        zzd(Unsafe unsafe) {
            this.a = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public final void zza(Object obj, long j, int i) {
            this.a.putInt(obj, j, i);
        }

        public final void zza(Object obj, long j, long j2) {
            this.a.putLong(obj, j, j2);
        }

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public final int zzk(Object obj, long j) {
            return this.a.getInt(obj, j);
        }

        public final long zzl(Object obj, long j) {
            return this.a.getLong(obj, j);
        }

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00dc, code lost:
        r1 = zzbqq;
     */
    static {
        /*
            java.lang.Class<com.google.android.gms.internal.firebase_ml.zzwa> r0 = com.google.android.gms.internal.firebase_ml.zzwa.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = c()
            zzbor = r0
            java.lang.Class r0 = com.google.android.gms.internal.firebase_ml.zzrp.b()
            zzbhy = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzq(r0)
            zzbqo = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzq(r0)
            zzbqp = r0
            sun.misc.Unsafe r0 = zzbor
            r1 = 0
            if (r0 != 0) goto L_0x002e
            goto L_0x0053
        L_0x002e:
            boolean r0 = com.google.android.gms.internal.firebase_ml.zzrp.a()
            if (r0 == 0) goto L_0x004c
            boolean r0 = zzbqo
            if (r0 == 0) goto L_0x0040
            com.google.android.gms.internal.firebase_ml.zzwa$zzb r1 = new com.google.android.gms.internal.firebase_ml.zzwa$zzb
            sun.misc.Unsafe r0 = zzbor
            r1.<init>(r0)
            goto L_0x0053
        L_0x0040:
            boolean r0 = zzbqp
            if (r0 == 0) goto L_0x0053
            com.google.android.gms.internal.firebase_ml.zzwa$zza r1 = new com.google.android.gms.internal.firebase_ml.zzwa$zza
            sun.misc.Unsafe r0 = zzbor
            r1.<init>(r0)
            goto L_0x0053
        L_0x004c:
            com.google.android.gms.internal.firebase_ml.zzwa$zzc r1 = new com.google.android.gms.internal.firebase_ml.zzwa$zzc
            sun.misc.Unsafe r0 = zzbor
            r1.<init>(r0)
        L_0x0053:
            zzbqq = r1
            boolean r0 = zzsb()
            zzbqr = r0
            boolean r0 = zzsa()
            zzbiu = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzo(r0)
            long r0 = (long) r0
            zzbqs = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzo(r0)
            long r0 = (long) r0
            zzbqt = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzp(r0)
            long r0 = (long) r0
            zzbqu = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzo(r0)
            long r0 = (long) r0
            zzbqv = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzp(r0)
            long r0 = (long) r0
            zzbqw = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzo(r0)
            long r0 = (long) r0
            zzbqx = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzp(r0)
            long r0 = (long) r0
            zzbqy = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzo(r0)
            long r0 = (long) r0
            zzbqz = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzp(r0)
            long r0 = (long) r0
            zzbra = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzo(r0)
            long r0 = (long) r0
            zzbrb = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzp(r0)
            long r0 = (long) r0
            zzbrc = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzo(r0)
            long r0 = (long) r0
            zzbrd = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzp(r0)
            long r0 = (long) r0
            zzbre = r0
            java.lang.reflect.Field r0 = zzsc()
            if (r0 == 0) goto L_0x00e8
            com.google.android.gms.internal.firebase_ml.zzwa$zzd r1 = zzbqq
            if (r1 != 0) goto L_0x00e1
            goto L_0x00e8
        L_0x00e1:
            sun.misc.Unsafe r1 = r1.a
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00ea
        L_0x00e8:
            r0 = -1
        L_0x00ea:
            zzbrf = r0
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00f6
            r0 = 1
            goto L_0x00f7
        L_0x00f6:
            r0 = 0
        L_0x00f7:
            zzbrg = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzwa.<clinit>():void");
    }

    private zzwa() {
    }

    static byte a(byte[] bArr, long j) {
        return zzbqq.zzy(bArr, zzbqs + j);
    }

    static int a(Object obj, long j) {
        return zzbqq.zzk(obj, j);
    }

    static <T> T a(Class<T> cls) {
        try {
            return zzbor.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static void a(Object obj, long j, double d) {
        zzbqq.zza(obj, j, d);
    }

    static void a(Object obj, long j, float f) {
        zzbqq.zza(obj, j, f);
    }

    static void a(Object obj, long j, int i) {
        zzbqq.zza(obj, j, i);
    }

    static void a(Object obj, long j, long j2) {
        zzbqq.zza(obj, j, j2);
    }

    static void a(Object obj, long j, Object obj2) {
        zzbqq.a.putObject(obj, j, obj2);
    }

    static void a(Object obj, long j, boolean z) {
        zzbqq.zza(obj, j, z);
    }

    static void a(byte[] bArr, long j, byte b) {
        zzbqq.zze(bArr, zzbqs + j, b);
    }

    static boolean a() {
        return zzbiu;
    }

    static long b(Object obj, long j) {
        return zzbqq.zzl(obj, j);
    }

    static boolean b() {
        return zzbqr;
    }

    static Unsafe c() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzwb());
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean c(Object obj, long j) {
        return zzbqq.zzm(obj, j);
    }

    static float d(Object obj, long j) {
        return zzbqq.zzn(obj, j);
    }

    static double e(Object obj, long j) {
        return zzbqq.zzo(obj, j);
    }

    static Object f(Object obj, long j) {
        return zzbqq.a.getObject(obj, j);
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int a = a(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        a(obj, j2, ((255 & b) << i) | (a & (~(255 << i))));
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        a(obj, j2, ((255 & b) << i) | (a(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : 0);
    }

    private static int zzo(Class<?> cls) {
        if (zzbiu) {
            return zzbqq.a.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzp(Class<?> cls) {
        if (zzbiu) {
            return zzbqq.a.arrayIndexScale(cls);
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (a(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)));
    }

    private static boolean zzq(Class<?> cls) {
        if (!zzrp.a()) {
            return false;
        }
        try {
            Class<?> cls2 = zzbhy;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (a(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    private static boolean zzsa() {
        Unsafe unsafe = zzbor;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzrp.a()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzsb() {
        Unsafe unsafe = zzbor;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzsc() == null) {
                return false;
            }
            if (zzrp.a()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static Field zzsc() {
        Field zzb2;
        if (zzrp.a() && (zzb2 = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
            return zzb2;
        }
        Field zzb3 = zzb(Buffer.class, "address");
        if (zzb3 == null || zzb3.getType() != Long.TYPE) {
            return null;
        }
        return zzb3;
    }

    /* access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }
}
