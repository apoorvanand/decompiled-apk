package com.google.android.gms.internal.firebase_auth;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzkq {
    static final boolean a = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
    private static final Logger logger = Logger.getLogger(zzkq.class.getName());
    private static final Unsafe zzacw = c();
    private static final boolean zzaer = zzk(Long.TYPE);
    private static final boolean zzaes = zzk(Integer.TYPE);
    private static final zzd zzaet;
    private static final boolean zzaeu = zzkv();
    private static final long zzaev = ((long) zzi(byte[].class));
    private static final long zzaew = ((long) zzi(boolean[].class));
    private static final long zzaex = ((long) zzj(boolean[].class));
    private static final long zzaey = ((long) zzi(int[].class));
    private static final long zzaez = ((long) zzj(int[].class));
    private static final long zzafa = ((long) zzi(long[].class));
    private static final long zzafb = ((long) zzj(long[].class));
    private static final long zzafc = ((long) zzi(float[].class));
    private static final long zzafd = ((long) zzj(float[].class));
    private static final long zzafe = ((long) zzi(double[].class));
    private static final long zzaff = ((long) zzj(double[].class));
    private static final long zzafg = ((long) zzi(Object[].class));
    private static final long zzafh = ((long) zzj(Object[].class));
    private static final long zzafi;
    private static final int zzafj = ((int) (zzaev & 7));
    private static final Class<?> zzvt = zzge.b();
    private static final boolean zzww = zzku();

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzkq.a) {
                zzkq.zzb(obj, j, z);
            } else {
                zzkq.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzkq.a) {
                zzkq.zza(obj, j, b);
            } else {
                zzkq.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            return zzkq.a ? zzkq.zzs(obj, j) : zzkq.zzt(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final byte zzy(Object obj, long j) {
            return zzkq.a ? zzkq.zzq(obj, j) : zzkq.zzr(obj, j);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
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

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzkq.a) {
                zzkq.zzb(obj, j, z);
            } else {
                zzkq.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzkq.a) {
                zzkq.zza(obj, j, b);
            } else {
                zzkq.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            return zzkq.a ? zzkq.zzs(obj, j) : zzkq.zzt(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final byte zzy(Object obj, long j) {
            return zzkq.a ? zzkq.zzq(obj, j) : zzkq.zzr(obj, j);
        }
    }

    static abstract class zzd {
        Unsafe a;

        zzd(Unsafe unsafe) {
            this.a = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public final void zza(Object obj, long j, long j2) {
            this.a.putLong(obj, j, j2);
        }

        public abstract void zza(Object obj, long j, boolean z);

        public final void zzb(Object obj, long j, int i) {
            this.a.putInt(obj, j, i);
        }

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
        r1 = zzaet;
     */
    static {
        /*
            java.lang.Class<com.google.android.gms.internal.firebase_auth.zzkq> r0 = com.google.android.gms.internal.firebase_auth.zzkq.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = c()
            zzacw = r0
            java.lang.Class r0 = com.google.android.gms.internal.firebase_auth.zzge.b()
            zzvt = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzk(r0)
            zzaer = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzk(r0)
            zzaes = r0
            sun.misc.Unsafe r0 = zzacw
            r1 = 0
            if (r0 != 0) goto L_0x002e
            goto L_0x0053
        L_0x002e:
            boolean r0 = com.google.android.gms.internal.firebase_auth.zzge.a()
            if (r0 == 0) goto L_0x004c
            boolean r0 = zzaer
            if (r0 == 0) goto L_0x0040
            com.google.android.gms.internal.firebase_auth.zzkq$zzc r1 = new com.google.android.gms.internal.firebase_auth.zzkq$zzc
            sun.misc.Unsafe r0 = zzacw
            r1.<init>(r0)
            goto L_0x0053
        L_0x0040:
            boolean r0 = zzaes
            if (r0 == 0) goto L_0x0053
            com.google.android.gms.internal.firebase_auth.zzkq$zza r1 = new com.google.android.gms.internal.firebase_auth.zzkq$zza
            sun.misc.Unsafe r0 = zzacw
            r1.<init>(r0)
            goto L_0x0053
        L_0x004c:
            com.google.android.gms.internal.firebase_auth.zzkq$zzb r1 = new com.google.android.gms.internal.firebase_auth.zzkq$zzb
            sun.misc.Unsafe r0 = zzacw
            r1.<init>(r0)
        L_0x0053:
            zzaet = r1
            boolean r0 = zzkv()
            zzaeu = r0
            boolean r0 = zzku()
            zzww = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzaev = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzaew = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzaex = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzaey = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzaez = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzafa = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzafb = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzafc = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzafd = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzafe = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzaff = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzafg = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzafh = r0
            java.lang.reflect.Field r0 = zzkw()
            if (r0 == 0) goto L_0x00e8
            com.google.android.gms.internal.firebase_auth.zzkq$zzd r1 = zzaet
            if (r1 != 0) goto L_0x00e1
            goto L_0x00e8
        L_0x00e1:
            sun.misc.Unsafe r1 = r1.a
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00ea
        L_0x00e8:
            r0 = -1
        L_0x00ea:
            zzafi = r0
            long r0 = zzaev
            r2 = 7
            long r0 = r0 & r2
            int r1 = (int) r0
            zzafj = r1
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00fe
            r0 = 1
            goto L_0x00ff
        L_0x00fe:
            r0 = 0
        L_0x00ff:
            a = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzkq.<clinit>():void");
    }

    private zzkq() {
    }

    static byte a(byte[] bArr, long j) {
        return zzaet.zzy(bArr, zzaev + j);
    }

    static int a(Object obj, long j) {
        return zzaet.zzk(obj, j);
    }

    static <T> T a(Class<T> cls) {
        try {
            return zzacw.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static void a(Object obj, long j, double d) {
        zzaet.zza(obj, j, d);
    }

    static void a(Object obj, long j, float f) {
        zzaet.zza(obj, j, f);
    }

    static void a(Object obj, long j, int i) {
        zzaet.zzb(obj, j, i);
    }

    static void a(Object obj, long j, long j2) {
        zzaet.zza(obj, j, j2);
    }

    static void a(Object obj, long j, Object obj2) {
        zzaet.a.putObject(obj, j, obj2);
    }

    static void a(Object obj, long j, boolean z) {
        zzaet.zza(obj, j, z);
    }

    static void a(byte[] bArr, long j, byte b) {
        zzaet.zze(bArr, zzaev + j, b);
    }

    static boolean a() {
        return zzww;
    }

    static long b(Object obj, long j) {
        return zzaet.zzl(obj, j);
    }

    static boolean b() {
        return zzaeu;
    }

    static Unsafe c() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzks());
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean c(Object obj, long j) {
        return zzaet.zzm(obj, j);
    }

    static float d(Object obj, long j) {
        return zzaet.zzn(obj, j);
    }

    static double e(Object obj, long j) {
        return zzaet.zzo(obj, j);
    }

    static Object f(Object obj, long j) {
        return zzaet.a.getObject(obj, j);
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int a2 = a(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        a(obj, j2, ((255 & b) << i) | (a2 & (~(255 << i))));
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

    private static int zzi(Class<?> cls) {
        if (zzww) {
            return zzaet.a.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzj(Class<?> cls) {
        if (zzww) {
            return zzaet.a.arrayIndexScale(cls);
        }
        return -1;
    }

    private static boolean zzk(Class<?> cls) {
        if (!zzge.a()) {
            return false;
        }
        try {
            Class<?> cls2 = zzvt;
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

    private static boolean zzku() {
        Unsafe unsafe = zzacw;
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
            if (zzge.a()) {
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

    private static boolean zzkv() {
        Unsafe unsafe = zzacw;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzkw() == null) {
                return false;
            }
            if (zzge.a()) {
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

    private static Field zzkw() {
        Field zzb2;
        if (zzge.a() && (zzb2 = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
            return zzb2;
        }
        Field zzb3 = zzb(Buffer.class, "address");
        if (zzb3 == null || zzb3.getType() != Long.TYPE) {
            return null;
        }
        return zzb3;
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (a(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (a(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }
}
