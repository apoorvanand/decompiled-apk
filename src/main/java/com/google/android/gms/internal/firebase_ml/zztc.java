package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zztc;
import com.google.android.gms.internal.firebase_ml.zztc.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zztc<MessageType extends zztc<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzrl<MessageType, BuilderType> {
    private static Map<Object, zztc<?, ?>> zzbme = new ConcurrentHashMap();
    protected zzvv zzbmc = zzvv.zzru();
    private int zzbmd = -1;

    public static abstract class zza<MessageType extends zztc<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzrm<MessageType, BuilderType> {
        protected MessageType a;
        protected boolean b = false;
        private final MessageType zzbmf;

        protected zza(MessageType messagetype) {
            this.zzbmf = messagetype;
            this.a = (zztc) messagetype.a(zzf.zzbmm, (Object) null, (Object) null);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzuz.zzrc().zzad(messagetype).zze(messagetype, messagetype2);
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (this.b) {
                MessageType messagetype = (zztc) this.a.a(zzf.zzbmm, (Object) null, (Object) null);
                zza(messagetype, this.a);
                this.a = messagetype;
                this.b = false;
            }
        }

        public /* synthetic */ Object clone() {
            zza zza = (zza) ((zztc) this.zzbmf).a(zzf.zzbmn, (Object) null, (Object) null);
            zza.a((zztc) zzpw());
            return zza;
        }

        public final boolean isInitialized() {
            return zztc.a(this.a, false);
        }

        /* renamed from: zza */
        public final BuilderType a(MessageType messagetype) {
            a();
            zza(this.a, messagetype);
            return this;
        }

        public final /* synthetic */ zzrm zzof() {
            return (zza) clone();
        }

        public final /* synthetic */ zzum zzps() {
            return this.zzbmf;
        }

        /* renamed from: zzpu */
        public MessageType zzpw() {
            if (this.b) {
                return this.a;
            }
            this.a.c();
            this.b = true;
            return this.a;
        }

        /* renamed from: zzpv */
        public final MessageType zzpx() {
            MessageType messagetype = (zztc) zzpw();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzvt(messagetype);
        }
    }

    public static class zzb<T extends zztc<T, ?>> extends zzrn<T> {
        private final T zzbmf;

        public zzb(T t) {
            this.zzbmf = t;
        }
    }

    public static abstract class zzc<MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzc<MessageType, BuilderType>> extends zza<MessageType, BuilderType> implements zzuo {
        /* access modifiers changed from: protected */
        public final void a() {
            if (this.b) {
                super.a();
                ((zzd) this.a).zzbmi = (zzsu) ((zzd) this.a).zzbmi.clone();
            }
        }

        public /* synthetic */ zztc zzpu() {
            return (zzd) zzpw();
        }

        public /* synthetic */ zzum zzpw() {
            zztc zzpu;
            if (this.b) {
                zzpu = this.a;
            } else {
                ((zzd) this.a).zzbmi.zzoh();
                zzpu = super.zzpw();
            }
            return (zzd) zzpu;
        }
    }

    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzc<MessageType, BuilderType>> extends zztc<MessageType, BuilderType> implements zzuo {
        protected zzsu<Object> zzbmi = zzsu.zzpd();

        /* access modifiers changed from: package-private */
        public final zzsu<Object> a() {
            if (this.zzbmi.isImmutable()) {
                this.zzbmi = (zzsu) this.zzbmi.clone();
            }
            return this.zzbmi;
        }
    }

    public static class zze<ContainingType extends zzum, Type> extends zzsn<ContainingType, Type> {
    }

    /* 'enum' modifier removed */
    public static final class zzf {
        public static final int zzbmj = 1;
        public static final int zzbmk = 2;
        public static final int zzbml = 3;
        public static final int zzbmm = 4;
        public static final int zzbmn = 5;
        public static final int zzbmo = 6;
        public static final int zzbmp = 7;
        private static final /* synthetic */ int[] zzbmq = {zzbmj, zzbmk, zzbml, zzbmm, zzbmn, zzbmo, zzbmp};
        public static final int zzbmr = 1;
        public static final int zzbms = 2;
        private static final /* synthetic */ int[] zzbmt = {zzbmr, zzbms};
        public static final int zzbmu = 1;
        public static final int zzbmv = 2;
        private static final /* synthetic */ int[] zzbmw = {zzbmu, zzbmv};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzbmq.clone();
        }
    }

    static <T extends zztc<?, ?>> T a(Class<T> cls) {
        T t = (zztc) zzbme.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zztc) zzbme.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zztc) ((zztc) zzwa.a(cls)).a(zzf.zzbmo, (Object) null, (Object) null);
            if (t != null) {
                zzbme.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static zztj a(zztj zztj) {
        int size = zztj.size();
        return zztj.zzcx(size == 0 ? 10 : size << 1);
    }

    protected static <E> zztl<E> a(zztl<E> zztl) {
        int size = zztl.size();
        return zztl.zzcb(size == 0 ? 10 : size << 1);
    }

    protected static Object a(zzum zzum, String str, Object[] objArr) {
        return new zzvb(zzum, str, objArr);
    }

    static Object a(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static <T extends zztc<?, ?>> void a(Class<T> cls, T t) {
        zzbme.put(cls, t);
    }

    protected static final <T extends zztc<T, ?>> boolean a(T t, boolean z) {
        byte byteValue = ((Byte) t.a(zzf.zzbmj, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzac = zzuz.zzrc().zzad(t).zzac(t);
        if (z) {
            t.a(zzf.zzbmk, (Object) zzac ? t : null, (Object) null);
        }
        return zzac;
    }

    protected static zztj e() {
        return zztd.zzpz();
    }

    protected static <E> zztl<E> f() {
        return zzva.zzrd();
    }

    private static <T extends zztc<T, ?>> T zza(T t, byte[] bArr, int i, int i2, zzsp zzsp) {
        T t2 = (zztc) t.a(zzf.zzbmm, (Object) null, (Object) null);
        try {
            zzuz.zzrc().zzad(t2).zza(t2, bArr, 0, i2, new zzrr(zzsp));
            t2.c();
            if (t2.zzbhu == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zztm) {
                throw ((zztm) e.getCause());
            }
            throw new zztm(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zztm.a().zzg(t2);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object a(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        this.zzbmd = i;
    }

    /* access modifiers changed from: package-private */
    public final int b() {
        return this.zzbmd;
    }

    /* access modifiers changed from: protected */
    public final void c() {
        zzuz.zzrc().zzad(this).zzq(this);
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zztc<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType d() {
        return (zza) a(zzf.zzbmn, (Object) null, (Object) null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zztc) a(zzf.zzbmo, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzuz.zzrc().zzad(this).equals(this, (zztc) obj);
    }

    public int hashCode() {
        if (this.zzbhu != 0) {
            return this.zzbhu;
        }
        this.zzbhu = zzuz.zzrc().zzad(this).hashCode(this);
        return this.zzbhu;
    }

    public final boolean isInitialized() {
        return a(this, Boolean.TRUE.booleanValue());
    }

    public String toString() {
        return zzup.a(this, super.toString());
    }

    public final void zzb(zzsj zzsj) {
        zzuz.zzrc().zzl(getClass()).zza(this, zzsl.zza(zzsj));
    }

    public final int zzpe() {
        if (this.zzbmd == -1) {
            this.zzbmd = zzuz.zzrc().zzad(this).zzaa(this);
        }
        return this.zzbmd;
    }

    public final /* synthetic */ zzun zzpq() {
        zza zza2 = (zza) a(zzf.zzbmn, (Object) null, (Object) null);
        zza2.a(this);
        return zza2;
    }

    public final /* synthetic */ zzun zzpr() {
        return (zza) a(zzf.zzbmn, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzum zzps() {
        return (zztc) a(zzf.zzbmo, (Object) null, (Object) null);
    }
}
