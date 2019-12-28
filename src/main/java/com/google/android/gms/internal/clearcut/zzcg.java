package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import com.google.android.gms.internal.clearcut.zzcg.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzcg<MessageType extends zzcg<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzas<MessageType, BuilderType> {
    private static Map<Object, zzcg<?, ?>> zzjr = new ConcurrentHashMap();
    protected zzey zzjp = zzey.zzea();
    private int zzjq = -1;

    public static abstract class zza<MessageType extends zzcg<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzat<MessageType, BuilderType> {
        protected MessageType a;
        protected boolean b = false;
        private final MessageType zzjs;

        protected zza(MessageType messagetype) {
            this.zzjs = messagetype;
            this.a = (zzcg) messagetype.a(zzg.zzkg, (Object) null, (Object) null);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzea.zzcm().zzp(messagetype).zzc(messagetype, messagetype2);
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (this.b) {
                MessageType messagetype = (zzcg) this.a.a(zzg.zzkg, (Object) null, (Object) null);
                zza(messagetype, this.a);
                this.a = messagetype;
                this.b = false;
            }
        }

        public /* synthetic */ Object clone() {
            zza zza = (zza) ((zzcg) this.zzjs).a(zzg.zzkh, (Object) null, (Object) null);
            zza.a((zzcg) zzbi());
            return zza;
        }

        public final boolean isInitialized() {
            return zzcg.a(this.a, false);
        }

        /* renamed from: zza */
        public final BuilderType a(MessageType messagetype) {
            a();
            zza(this.a, messagetype);
            return this;
        }

        public final /* synthetic */ zzdo zzbe() {
            return this.zzjs;
        }

        /* renamed from: zzbg */
        public MessageType zzbi() {
            if (this.b) {
                return this.a;
            }
            MessageType messagetype = this.a;
            zzea.zzcm().zzp(messagetype).zzc(messagetype);
            this.b = true;
            return this.a;
        }

        public final MessageType zzbh() {
            MessageType messagetype = (zzcg) zzbi();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype.a(zzg.zzkd, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzea.zzcm().zzp(messagetype).zzo(messagetype);
                    if (booleanValue) {
                        messagetype.a(zzg.zzke, (Object) z ? messagetype : null, (Object) null);
                    }
                }
            }
            if (z) {
                return messagetype;
            }
            throw new zzew(messagetype);
        }

        public final /* synthetic */ zzdo zzbj() {
            zzcg zzcg = (zzcg) zzbi();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzcg.a(zzg.zzkd, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzea.zzcm().zzp(zzcg).zzo(zzcg);
                    if (booleanValue) {
                        zzcg.a(zzg.zzke, (Object) z ? zzcg : null, (Object) null);
                    }
                }
            }
            if (z) {
                return zzcg;
            }
            throw new zzew(zzcg);
        }

        public final /* synthetic */ zzat zzt() {
            return (zza) clone();
        }
    }

    public static class zzb<T extends zzcg<T, ?>> extends zzau<T> {
        private T zzjs;

        public zzb(T t) {
            this.zzjs = t;
        }
    }

    public static abstract class zzc<MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzc<MessageType, BuilderType>> extends zza<MessageType, BuilderType> implements zzdq {
        protected zzc(MessageType messagetype) {
            super(messagetype);
        }

        /* access modifiers changed from: protected */
        public final void a() {
            if (this.b) {
                super.a();
                ((zzd) this.a).zzjv = (zzby) ((zzd) this.a).zzjv.clone();
            }
        }

        public final /* synthetic */ zzcg zzbg() {
            return (zzd) zzbi();
        }

        public final /* synthetic */ zzdo zzbi() {
            zzcg zzbg;
            if (this.b) {
                zzbg = this.a;
            } else {
                ((zzd) this.a).zzjv.zzv();
                zzbg = super.zzbi();
            }
            return (zzd) zzbg;
        }
    }

    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzc<MessageType, BuilderType>> extends zzcg<MessageType, BuilderType> implements zzdq {
        protected zzby<zze> zzjv = zzby.zzar();
    }

    static final class zze implements zzca<zze> {
        final int a = 66321687;
        final zzfl b;
        final boolean c;
        final boolean d;
        private final zzck<?> zzjw = null;

        zze(zzck<?> zzck, int i, zzfl zzfl, boolean z, boolean z2) {
            this.b = zzfl;
            this.c = false;
            this.d = false;
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return this.a - ((zze) obj).a;
        }

        public final zzdp zza(zzdp zzdp, zzdo zzdo) {
            return ((zza) zzdp).a((zzcg) zzdo);
        }

        public final zzdv zza(zzdv zzdv, zzdv zzdv2) {
            throw new UnsupportedOperationException();
        }

        public final zzfl zzau() {
            return this.b;
        }

        public final zzfq zzav() {
            return this.b.zzek();
        }

        public final boolean zzaw() {
            return false;
        }

        public final boolean zzax() {
            return false;
        }

        public final int zzc() {
            return this.a;
        }
    }

    public static class zzf<ContainingType extends zzdo, Type> extends zzbr<ContainingType, Type> {
        private final Type zzdu;
        private final ContainingType zzka;
        private final zzdo zzkb;
        private final zze zzkc;

        zzf(ContainingType containingtype, Type type, zzdo zzdo, zze zze, Class cls) {
            if (containingtype == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (zze.b == zzfl.MESSAGE && zzdo == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.zzka = containingtype;
                this.zzdu = type;
                this.zzkb = zzdo;
                this.zzkc = zze;
            }
        }
    }

    /* 'enum' modifier removed */
    public static final class zzg {
        public static final int zzkd = 1;
        public static final int zzke = 2;
        public static final int zzkf = 3;
        public static final int zzkg = 4;
        public static final int zzkh = 5;
        public static final int zzki = 6;
        public static final int zzkj = 7;
        private static final /* synthetic */ int[] zzkk = {zzkd, zzke, zzkf, zzkg, zzkh, zzki, zzkj};
        public static final int zzkl = 1;
        public static final int zzkm = 2;
        private static final /* synthetic */ int[] zzkn = {zzkl, zzkm};
        public static final int zzko = 1;
        public static final int zzkp = 2;
        private static final /* synthetic */ int[] zzkq = {zzko, zzkp};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzkk.clone();
        }
    }

    protected static <T extends zzcg<T, ?>> T a(T t, byte[] bArr) {
        T zza2 = zza(t, bArr);
        if (zza2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza2.a(zzg.zzkd, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzea.zzcm().zzp(zza2).zzo(zza2);
                    if (booleanValue) {
                        zza2.a(zzg.zzke, (Object) z ? zza2 : null, (Object) null);
                    }
                }
            }
            if (!z) {
                throw new zzco(new zzew(zza2).getMessage()).zzg(zza2);
            }
        }
        return zza2;
    }

    static <T extends zzcg<?, ?>> T a(Class<T> cls) {
        T t = (zzcg) zzjr.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzcg) zzjr.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? "Unable to get default instance for: ".concat(valueOf) : new String("Unable to get default instance for: "));
    }

    protected static Object a(zzdo zzdo, String str, Object[] objArr) {
        return new zzec(zzdo, str, objArr);
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

    protected static <T extends zzcg<?, ?>> void a(Class<T> cls, T t) {
        zzjr.put(cls, t);
    }

    protected static final <T extends zzcg<T, ?>> boolean a(T t, boolean z) {
        byte byteValue = ((Byte) t.a(zzg.zzkd, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return zzea.zzcm().zzp(t).zzo(t);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.clearcut.zzch, com.google.android.gms.internal.clearcut.zzcl] */
    protected static zzcl c() {
        return zzch.zzbk();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.clearcut.zzcm, com.google.android.gms.internal.clearcut.zzdc] */
    protected static zzcm d() {
        return zzdc.zzbx();
    }

    protected static <E> zzcn<E> e() {
        return zzeb.zzcn();
    }

    public static <ContainingType extends zzdo, Type> zzf<ContainingType, Type> zza(ContainingType containingtype, Type type, zzdo zzdo, zzck<?> zzck, int i, zzfl zzfl, Class cls) {
        return new zzf(containingtype, type, zzdo, new zze((zzck<?>) null, 66321687, zzfl, false, false), cls);
    }

    private static <T extends zzcg<T, ?>> T zza(T t, byte[] bArr) {
        T t2 = (zzcg) t.a(zzg.zzkg, (Object) null, (Object) null);
        try {
            zzea.zzcm().zzp(t2).zza(t2, bArr, 0, bArr.length, new zzay());
            zzea.zzcm().zzp(t2).zzc(t2);
            if (t2.zzex == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzco) {
                throw ((zzco) e.getCause());
            }
            throw new zzco(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzco.a().zzg(t2);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object a(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        this.zzjq = i;
    }

    /* access modifiers changed from: package-private */
    public final int b() {
        return this.zzjq;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzcg) a(zzg.zzki, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzea.zzcm().zzp(this).equals(this, (zzcg) obj);
    }

    public int hashCode() {
        if (this.zzex != 0) {
            return this.zzex;
        }
        this.zzex = zzea.zzcm().zzp(this).hashCode(this);
        return this.zzex;
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) a(zzg.zzkd, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzo = zzea.zzcm().zzp(this).zzo(this);
        if (booleanValue) {
            a(zzg.zzke, (Object) zzo ? this : null, (Object) null);
        }
        return zzo;
    }

    public String toString() {
        return zzdr.a(this, super.toString());
    }

    public final int zzas() {
        if (this.zzjq == -1) {
            this.zzjq = zzea.zzcm().zzp(this).zzm(this);
        }
        return this.zzjq;
    }

    public final void zzb(zzbn zzbn) {
        zzea.zzcm().zze(getClass()).zza(this, zzbp.zza(zzbn));
    }

    public final /* synthetic */ zzdp zzbc() {
        zza zza2 = (zza) a(zzg.zzkh, (Object) null, (Object) null);
        zza2.a(this);
        return zza2;
    }

    public final /* synthetic */ zzdp zzbd() {
        return (zza) a(zzg.zzkh, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzdo zzbe() {
        return (zzcg) a(zzg.zzki, (Object) null, (Object) null);
    }
}
