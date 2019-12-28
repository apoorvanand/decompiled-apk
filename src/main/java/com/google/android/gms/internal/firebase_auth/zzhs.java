package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;
import com.google.android.gms.internal.firebase_auth.zzhs.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzhs<MessageType extends zzhs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzfx<MessageType, BuilderType> {
    private static Map<Object, zzhs<?, ?>> zzaal = new ConcurrentHashMap();
    protected zzkn zzaaj = zzkn.zzko();
    private int zzaak = -1;

    public static abstract class zza<MessageType extends zzhs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzga<MessageType, BuilderType> {
        protected MessageType a;
        private final MessageType zzaag;
        private boolean zzaai = false;

        protected zza(MessageType messagetype) {
            this.zzaag = messagetype;
            this.a = (zzhs) messagetype.a(zzd.zzaaq, (Object) null, (Object) null);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzjo.zzjv().zzr(messagetype).zzd(messagetype, messagetype2);
        }

        /* access modifiers changed from: protected */
        public final void a() {
            if (this.zzaai) {
                MessageType messagetype = (zzhs) this.a.a(zzd.zzaaq, (Object) null, (Object) null);
                zza(messagetype, this.a);
                this.a = messagetype;
                this.zzaai = false;
            }
        }

        public /* synthetic */ Object clone() {
            zza zza = (zza) ((zzhs) this.zzaag).a(zzd.zzaar, (Object) null, (Object) null);
            zza.a((zzhs) zzig());
            return zza;
        }

        public final boolean isInitialized() {
            return zzhs.a(this.a, false);
        }

        /* renamed from: zza */
        public final BuilderType a(MessageType messagetype) {
            a();
            zza(this.a, messagetype);
            return this;
        }

        public final /* synthetic */ zzga zzfw() {
            return (zza) clone();
        }

        /* renamed from: zzie */
        public MessageType zzig() {
            if (this.zzaai) {
                return this.a;
            }
            this.a.b();
            this.zzaai = true;
            return this.a;
        }

        /* renamed from: zzif */
        public final MessageType zzih() {
            MessageType messagetype = (zzhs) zzig();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzkl(messagetype);
        }

        public final /* synthetic */ zzjc zzii() {
            return this.zzaag;
        }
    }

    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzhs<MessageType, BuilderType> implements zzje {
        protected zzhi<Object> zzaam = zzhi.zzhs();
    }

    public static class zzc<T extends zzhs<T, ?>> extends zzgc<T> {
        private final T zzaag;

        public zzc(T t) {
            this.zzaag = t;
        }

        public final /* synthetic */ Object zza(zzgr zzgr, zzhf zzhf) {
            return zzhs.a(this.zzaag, zzgr, zzhf);
        }
    }

    /* 'enum' modifier removed */
    public static final class zzd {
        public static final int zzaan = 1;
        public static final int zzaao = 2;
        public static final int zzaap = 3;
        public static final int zzaaq = 4;
        public static final int zzaar = 5;
        public static final int zzaas = 6;
        public static final int zzaat = 7;
        private static final /* synthetic */ int[] zzaau = {zzaan, zzaao, zzaap, zzaaq, zzaar, zzaas, zzaat};
        public static final int zzaav = 1;
        public static final int zzaaw = 2;
        private static final /* synthetic */ int[] zzaax = {zzaav, zzaaw};
        public static final int zzaay = 1;
        public static final int zzaaz = 2;
        private static final /* synthetic */ int[] zzaba = {zzaay, zzaaz};

        public static int[] zzip() {
            return (int[]) zzaau.clone();
        }
    }

    public static class zze<ContainingType extends zzjc, Type> extends zzhe<ContainingType, Type> {
    }

    static <T extends zzhs<T, ?>> T a(T t, zzgr zzgr, zzhf zzhf) {
        T t2 = (zzhs) t.a(zzd.zzaaq, (Object) null, (Object) null);
        try {
            zzjo.zzjv().zzr(t2).zza(t2, zzgy.zza(zzgr), zzhf);
            t2.b();
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzic) {
                throw ((zzic) e.getCause());
            }
            throw new zzic(e.getMessage()).zzh(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzic) {
                throw ((zzic) e2.getCause());
            }
            throw e2;
        }
    }

    static <T extends zzhs<?, ?>> T a(Class<T> cls) {
        T t = (zzhs) zzaal.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzhs) zzaal.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzhs) ((zzhs) zzkq.a(cls)).a(zzd.zzaas, (Object) null, (Object) null);
            if (t != null) {
                zzaal.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static Object a(zzjc zzjc, String str, Object[] objArr) {
        return new zzjq(zzjc, str, objArr);
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

    protected static <T extends zzhs<?, ?>> void a(Class<T> cls, T t) {
        zzaal.put(cls, t);
    }

    protected static final <T extends zzhs<T, ?>> boolean a(T t, boolean z) {
        byte byteValue = ((Byte) t.a(zzd.zzaan, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzp = zzjo.zzjv().zzr(t).zzp(t);
        if (z) {
            t.a(zzd.zzaao, (Object) zzp ? t : null, (Object) null);
        }
        return zzp;
    }

    protected static zzhx d() {
        return zzhu.zziq();
    }

    protected static <E> zzhz<E> e() {
        return zzjn.zzju();
    }

    /* access modifiers changed from: package-private */
    public final int a() {
        return this.zzaak;
    }

    /* access modifiers changed from: protected */
    public abstract Object a(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        this.zzaak = i;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        zzjo.zzjv().zzr(this).zzf(this);
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzhs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType c() {
        return (zza) a(zzd.zzaar, (Object) null, (Object) null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzhs) a(zzd.zzaas, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzjo.zzjv().zzr(this).equals(this, (zzhs) obj);
    }

    public int hashCode() {
        if (this.zzvm != 0) {
            return this.zzvm;
        }
        this.zzvm = zzjo.zzjv().zzr(this).hashCode(this);
        return this.zzvm;
    }

    public final boolean isInitialized() {
        return a(this, Boolean.TRUE.booleanValue());
    }

    public String toString() {
        return zzjd.a(this, super.toString());
    }

    public final void zzb(zzha zzha) {
        zzjo.zzjv().zzf(getClass()).zza(this, zzhc.zza(zzha));
    }

    public final /* synthetic */ zzjc zzii() {
        return (zzhs) a(zzd.zzaas, (Object) null, (Object) null);
    }

    public final int zzik() {
        if (this.zzaak == -1) {
            this.zzaak = zzjo.zzjv().zzr(this).zzq(this);
        }
        return this.zzaak;
    }

    public final /* synthetic */ zzjb zzin() {
        zza zza2 = (zza) a(zzd.zzaar, (Object) null, (Object) null);
        zza2.a(this);
        return zza2;
    }

    public final /* synthetic */ zzjb zzio() {
        return (zza) a(zzd.zzaar, (Object) null, (Object) null);
    }
}
