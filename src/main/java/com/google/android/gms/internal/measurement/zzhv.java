package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzhv {
    static final long a = ((long) zzi(byte[].class));
    static final boolean b = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
    private static final Logger logger = Logger.getLogger(zzhv.class.getName());
    private static final Class<?> zzacx = zzdi.b();
    private static final boolean zzaec = zzww();
    private static final Unsafe zzaki = c();
    private static final boolean zzame = zzk(Long.TYPE);
    private static final boolean zzamf = zzk(Integer.TYPE);
    private static final zzd zzamg;
    private static final boolean zzamh = zzwx();
    private static final long zzamj = ((long) zzi(boolean[].class));
    private static final long zzamk = ((long) zzj(boolean[].class));
    private static final long zzaml = ((long) zzi(int[].class));
    private static final long zzamm = ((long) zzj(int[].class));
    private static final long zzamn = ((long) zzi(long[].class));
    private static final long zzamo = ((long) zzj(long[].class));
    private static final long zzamp = ((long) zzi(float[].class));
    private static final long zzamq = ((long) zzj(float[].class));
    private static final long zzamr = ((long) zzi(double[].class));
    private static final long zzams = ((long) zzj(double[].class));
    private static final long zzamt = ((long) zzi(Object[].class));
    private static final long zzamu = ((long) zzj(Object[].class));
    private static final long zzamv;
    private static final int zzamw = ((int) (a & 7));

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte((int) (j & -1), b);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzhv.b) {
                zzhv.zzb(obj, j, z);
            } else {
                zzhv.zzc(obj, j, z);
            }
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray((int) (j2 & -1), bArr, (int) j, (int) j3);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzhv.b) {
                zzhv.zza(obj, j, b);
            } else {
                zzhv.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            return zzhv.b ? zzhv.zzs(obj, j) : zzhv.zzt(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final byte zzy(Object obj, long j) {
            return zzhv.b ? zzhv.zzq(obj, j) : zzhv.zzr(obj, j);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            this.a.putByte(j, b);
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

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            this.a.copyMemory(bArr, zzhv.a + j, (Object) null, j2, j3);
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

        public final void zza(long j, byte b) {
            Memory.pokeByte(j, b);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzhv.b) {
                zzhv.zzb(obj, j, z);
            } else {
                zzhv.zzc(obj, j, z);
            }
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray(j2, bArr, (int) j, (int) j3);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzhv.b) {
                zzhv.zza(obj, j, b);
            } else {
                zzhv.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            return zzhv.b ? zzhv.zzs(obj, j) : zzhv.zzt(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final byte zzy(Object obj, long j) {
            return zzhv.b ? zzhv.zzq(obj, j) : zzhv.zzr(obj, j);
        }
    }

    static abstract class zzd {
        Unsafe a;

        zzd(Unsafe unsafe) {
            this.a = unsafe;
        }

        public abstract void zza(long j, byte b);

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public final void zza(Object obj, long j, long j2) {
            this.a.putLong(obj, j, j2);
        }

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zza(byte[] bArr, long j, long j2, long j3);

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
        r1 = zzamg;
     */
    static {
        /*
            java.lang.Class<com.google.android.gms.internal.measurement.zzhv> r0 = com.google.android.gms.internal.measurement.zzhv.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = c()
            zzaki = r0
            java.lang.Class r0 = com.google.android.gms.internal.measurement.zzdi.b()
            zzacx = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzk(r0)
            zzame = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzk(r0)
            zzamf = r0
            sun.misc.Unsafe r0 = zzaki
            r1 = 0
            if (r0 != 0) goto L_0x002e
            goto L_0x0053
        L_0x002e:
            boolean r0 = com.google.android.gms.internal.measurement.zzdi.a()
            if (r0 == 0) goto L_0x004c
            boolean r0 = zzame
            if (r0 == 0) goto L_0x0040
            com.google.android.gms.internal.measurement.zzhv$zzc r1 = new com.google.android.gms.internal.measurement.zzhv$zzc
            sun.misc.Unsafe r0 = zzaki
            r1.<init>(r0)
            goto L_0x0053
        L_0x0040:
            boolean r0 = zzamf
            if (r0 == 0) goto L_0x0053
            com.google.android.gms.internal.measurement.zzhv$zza r1 = new com.google.android.gms.internal.measurement.zzhv$zza
            sun.misc.Unsafe r0 = zzaki
            r1.<init>(r0)
            goto L_0x0053
        L_0x004c:
            com.google.android.gms.internal.measurement.zzhv$zzb r1 = new com.google.android.gms.internal.measurement.zzhv$zzb
            sun.misc.Unsafe r0 = zzaki
            r1.<init>(r0)
        L_0x0053:
            zzamg = r1
            boolean r0 = zzwx()
            zzamh = r0
            boolean r0 = zzww()
            zzaec = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            a = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzamj = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzamk = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzaml = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzamm = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzamn = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzamo = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzamp = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzamq = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzamr = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzams = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzamt = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzamu = r0
            java.lang.reflect.Field r0 = zzwy()
            if (r0 == 0) goto L_0x00e8
            com.google.android.gms.internal.measurement.zzhv$zzd r1 = zzamg
            if (r1 != 0) goto L_0x00e1
            goto L_0x00e8
        L_0x00e1:
            sun.misc.Unsafe r1 = r1.a
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00ea
        L_0x00e8:
            r0 = -1
        L_0x00ea:
            zzamv = r0
            long r0 = a
            r2 = 7
            long r0 = r0 & r2
            int r1 = (int) r0
            zzamw = r1
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00fe
            r0 = 1
            goto L_0x00ff
        L_0x00fe:
            r0 = 0
        L_0x00ff:
            b = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhv.<clinit>():void");
    }

    private zzhv() {
    }

    static byte a(byte[] bArr, long j) {
        return zzamg.zzy(bArr, a + j);
    }

    static int a(Object obj, long j) {
        return zzamg.zzk(obj, j);
    }

    static long a(ByteBuffer byteBuffer) {
        return zzamg.zzl(byteBuffer, zzamv);
    }

    static <T> T a(Class<T> cls) {
        try {
            return zzaki.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static void a(long j, byte b2) {
        zzamg.zza(j, b2);
    }

    static void a(Object obj, long j, double d) {
        zzamg.zza(obj, j, d);
    }

    static void a(Object obj, long j, float f) {
        zzamg.zza(obj, j, f);
    }

    static void a(Object obj, long j, int i) {
        zzamg.zzb(obj, j, i);
    }

    static void a(Object obj, long j, long j2) {
        zzamg.zza(obj, j, j2);
    }

    static void a(Object obj, long j, Object obj2) {
        zzamg.a.putObject(obj, j, obj2);
    }

    static void a(Object obj, long j, boolean z) {
        zzamg.zza(obj, j, z);
    }

    static void a(byte[] bArr, long j, byte b2) {
        zzamg.zze(bArr, a + j, b2);
    }

    static void a(byte[] bArr, long j, long j2, long j3) {
        zzamg.zza(bArr, j, j2, j3);
    }

    static boolean a() {
        return zzaec;
    }

    static long b(Object obj, long j) {
        return zzamg.zzl(obj, j);
    }

    static boolean b() {
        return zzamh;
    }

    static Unsafe c() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzhx());
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean c(Object obj, long j) {
        return zzamg.zzm(obj, j);
    }

    static float d(Object obj, long j) {
        return zzamg.zzn(obj, j);
    }

    static double e(Object obj, long j) {
        return zzamg.zzo(obj, j);
    }

    static Object f(Object obj, long j) {
        return zzamg.a.getObject(obj, j);
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b2) {
        long j2 = -4 & j;
        int a2 = a(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        a(obj, j2, ((255 & b2) << i) | (a2 & (~(255 << i))));
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b2) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        a(obj, j2, ((255 & b2) << i) | (a(obj, j2) & (~(255 << i))));
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
        if (zzaec) {
            return zzamg.a.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzj(Class<?> cls) {
        if (zzaec) {
            return zzamg.a.arrayIndexScale(cls);
        }
        return -1;
    }

    private static boolean zzk(Class<?> cls) {
        if (!zzdi.a()) {
            return false;
        }
        try {
            Class<?> cls2 = zzacx;
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

    private static boolean zzww() {
        Unsafe unsafe = zzaki;
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
            if (zzdi.a()) {
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

    private static boolean zzwx() {
        Unsafe unsafe = zzaki;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzwy() == null) {
                return false;
            }
            if (zzdi.a()) {
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

    private static Field zzwy() {
        Field zzb2;
        if (zzdi.a() && (zzb2 = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
            return zzb2;
        }
        Field zzb3 = zzb(Buffer.class, "address");
        if (zzb3 == null || zzb3.getType() != Long.TYPE) {
            return null;
        }
        return zzb3;
    }
}
