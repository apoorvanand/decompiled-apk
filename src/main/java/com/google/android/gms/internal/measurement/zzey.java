package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;
import com.google.android.gms.internal.measurement.zzey.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzey<MessageType extends zzey<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdf<MessageType, BuilderType> {
    private static Map<Object, zzey<?, ?>> zzaib = new ConcurrentHashMap();
    protected zzhs zzahz = zzhs.zzwq();
    private int zzaia = -1;

    public static abstract class zza<MessageType extends zzey<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdh<MessageType, BuilderType> {
        protected MessageType a;
        private final MessageType zzahw;
        private boolean zzahy = false;

        protected zza(MessageType messagetype) {
            this.zzahw = messagetype;
            this.a = (zzey) messagetype.a(zzd.zzaig, (Object) null, (Object) null);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzgt.zzvy().zzw(messagetype).zzc(messagetype, messagetype2);
        }

        /* access modifiers changed from: private */
        /* renamed from: zzb */
        public final BuilderType zza(zzeb zzeb, zzel zzel) {
            a();
            try {
                zzgt.zzvy().zzw(this.a).zza(this.a, zzec.zza(zzeb), zzel);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzel zzel) {
            a();
            try {
                zzgt.zzvy().zzw(this.a).zza(this.a, bArr, 0, i2 + 0, new zzdk(zzel));
                return this;
            } catch (zzfi e) {
                throw e;
            } catch (IndexOutOfBoundsException unused) {
                throw zzfi.a();
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            }
        }

        /* access modifiers changed from: protected */
        public final void a() {
            if (this.zzahy) {
                MessageType messagetype = (zzey) this.a.a(zzd.zzaig, (Object) null, (Object) null);
                zza(messagetype, this.a);
                this.a = messagetype;
                this.zzahy = false;
            }
        }

        public /* synthetic */ Object clone() {
            zza zza = (zza) ((zzey) this.zzahw).a(zzd.zzaih, (Object) null, (Object) null);
            zza.a((zzey) zzuf());
            return zza;
        }

        public final boolean isInitialized() {
            return zzey.a(this.a, false);
        }

        public final /* synthetic */ zzdh zza(byte[] bArr, int i, int i2, zzel zzel) {
            return zzb(bArr, 0, i2, zzel);
        }

        /* renamed from: zza */
        public final BuilderType a(MessageType messagetype) {
            a();
            zza(this.a, messagetype);
            return this;
        }

        public final /* synthetic */ zzdh zzru() {
            return (zza) clone();
        }

        /* renamed from: zzud */
        public MessageType zzuf() {
            if (this.zzahy) {
                return this.a;
            }
            this.a.c();
            this.zzahy = true;
            return this.a;
        }

        /* renamed from: zzue */
        public final MessageType zzug() {
            MessageType messagetype = (zzey) zzuf();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzhq(messagetype);
        }

        public final /* synthetic */ zzgi zzuh() {
            return this.zzahw;
        }
    }

    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzey<MessageType, BuilderType> implements zzgk {
        protected zzeo<Object> zzaic = zzeo.zztr();

        /* access modifiers changed from: package-private */
        public final zzeo<Object> a() {
            if (this.zzaic.isImmutable()) {
                this.zzaic = (zzeo) this.zzaic.clone();
            }
            return this.zzaic;
        }
    }

    public static class zzc<T extends zzey<T, ?>> extends zzdg<T> {
        private final T zzahw;

        public zzc(T t) {
            this.zzahw = t;
        }

        public final /* synthetic */ Object zzc(zzeb zzeb, zzel zzel) {
            return zzey.a(this.zzahw, zzeb, zzel);
        }
    }

    /* 'enum' modifier removed */
    public static final class zzd {
        public static final int zzaid = 1;
        public static final int zzaie = 2;
        public static final int zzaif = 3;
        public static final int zzaig = 4;
        public static final int zzaih = 5;
        public static final int zzaii = 6;
        public static final int zzaij = 7;
        private static final /* synthetic */ int[] zzaik = {zzaid, zzaie, zzaif, zzaig, zzaih, zzaii, zzaij};
        public static final int zzail = 1;
        public static final int zzaim = 2;
        private static final /* synthetic */ int[] zzain = {zzail, zzaim};
        public static final int zzaio = 1;
        public static final int zzaip = 2;
        private static final /* synthetic */ int[] zzaiq = {zzaio, zzaip};

        public static int[] zzur() {
            return (int[]) zzaik.clone();
        }
    }

    public static class zze<ContainingType extends zzgi, Type> extends zzek<ContainingType, Type> {
    }

    static <T extends zzey<T, ?>> T a(T t, zzeb zzeb, zzel zzel) {
        T t2 = (zzey) t.a(zzd.zzaig, (Object) null, (Object) null);
        try {
            zzgt.zzvy().zzw(t2).zza(t2, zzec.zza(zzeb), zzel);
            t2.c();
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzfi) {
                throw ((zzfi) e.getCause());
            }
            throw new zzfi(e.getMessage()).zzg(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzfi) {
                throw ((zzfi) e2.getCause());
            }
            throw e2;
        }
    }

    protected static <T extends zzey<T, ?>> T a(T t, byte[] bArr, zzel zzel) {
        T zza2 = zza(t, bArr, 0, bArr.length, zzel);
        if (zza2 == null || zza2.isInitialized()) {
            return zza2;
        }
        throw new zzfi(new zzhq(zza2).getMessage()).zzg(zza2);
    }

    static <T extends zzey<?, ?>> T a(Class<T> cls) {
        T t = (zzey) zzaib.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzey) zzaib.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzey) ((zzey) zzhv.a(cls)).a(zzd.zzaii, (Object) null, (Object) null);
            if (t != null) {
                zzaib.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <E> zzff<E> a(zzff<E> zzff) {
        int size = zzff.size();
        return zzff.zzap(size == 0 ? 10 : size << 1);
    }

    protected static zzfg a(zzfg zzfg) {
        int size = zzfg.size();
        return zzfg.zzbv(size == 0 ? 10 : size << 1);
    }

    protected static Object a(zzgi zzgi, String str, Object[] objArr) {
        return new zzgv(zzgi, str, objArr);
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

    protected static <T extends zzey<?, ?>> void a(Class<T> cls, T t) {
        zzaib.put(cls, t);
    }

    protected static final <T extends zzey<T, ?>> boolean a(T t, boolean z) {
        byte byteValue = ((Byte) t.a(zzd.zzaid, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzv = zzgt.zzvy().zzw(t).zzv(t);
        if (z) {
            t.a(zzd.zzaie, (Object) zzv ? t : null, (Object) null);
        }
        return zzv;
    }

    protected static zzfd e() {
        return zzfa.zzus();
    }

    protected static zzfg f() {
        return zzfw.zzvk();
    }

    protected static <E> zzff<E> g() {
        return zzgw.zzwb();
    }

    private static <T extends zzey<T, ?>> T zza(T t, byte[] bArr, int i, int i2, zzel zzel) {
        T t2 = (zzey) t.a(zzd.zzaig, (Object) null, (Object) null);
        try {
            zzgt.zzvy().zzw(t2).zza(t2, bArr, 0, i2, new zzdk(zzel));
            t2.c();
            if (t2.zzact == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzfi) {
                throw ((zzfi) e.getCause());
            }
            throw new zzfi(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzfi.a().zzg(t2);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object a(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        this.zzaia = i;
    }

    /* access modifiers changed from: package-private */
    public final int b() {
        return this.zzaia;
    }

    /* access modifiers changed from: protected */
    public final void c() {
        zzgt.zzvy().zzw(this).zzj(this);
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzey<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType d() {
        return (zza) a(zzd.zzaih, (Object) null, (Object) null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzey) a(zzd.zzaii, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzgt.zzvy().zzw(this).equals(this, (zzey) obj);
    }

    public int hashCode() {
        if (this.zzact != 0) {
            return this.zzact;
        }
        this.zzact = zzgt.zzvy().zzw(this).hashCode(this);
        return this.zzact;
    }

    public final boolean isInitialized() {
        return a(this, Boolean.TRUE.booleanValue());
    }

    public String toString() {
        return zzgj.a(this, super.toString());
    }

    public final void zzb(zzee zzee) {
        zzgt.zzvy().zzf(getClass()).zza(this, zzei.zza(zzee));
    }

    public final /* synthetic */ zzgi zzuh() {
        return (zzey) a(zzd.zzaii, (Object) null, (Object) null);
    }

    public final BuilderType zzuj() {
        BuilderType buildertype = (zza) a(zzd.zzaih, (Object) null, (Object) null);
        buildertype.a(this);
        return buildertype;
    }

    public final int zzuk() {
        if (this.zzaia == -1) {
            this.zzaia = zzgt.zzvy().zzw(this).zzt(this);
        }
        return this.zzaia;
    }

    public final /* synthetic */ zzgh zzuo() {
        zza zza2 = (zza) a(zzd.zzaih, (Object) null, (Object) null);
        zza2.a(this);
        return zza2;
    }

    public final /* synthetic */ zzgh zzup() {
        return (zza) a(zzd.zzaih, (Object) null, (Object) null);
    }
}
