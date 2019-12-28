package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzfy;
import com.google.android.gms.internal.vision.zzfy.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzfy<MessageType extends zzfy<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzec<MessageType, BuilderType> {
    private static Map<Object, zzfy<?, ?>> zzwl = new ConcurrentHashMap();
    protected zzip zzwj = zzip.zzhe();
    private int zzwk = -1;

    public static abstract class zza<MessageType extends zzfy<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzed<MessageType, BuilderType> {
        protected MessageType a;
        protected boolean b = false;
        private final MessageType zzwm;

        protected zza(MessageType messagetype) {
            this.zzwm = messagetype;
            this.a = (zzfy) messagetype.a(zzg.zzxa, (Object) null, (Object) null);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzhs.zzgl().zzs(messagetype).zzc(messagetype, messagetype2);
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (this.b) {
                MessageType messagetype = (zzfy) this.a.a(zzg.zzxa, (Object) null, (Object) null);
                zza(messagetype, this.a);
                this.a = messagetype;
                this.b = false;
            }
        }

        public /* synthetic */ Object clone() {
            zza zza = (zza) ((zzfy) this.zzwm).a(zzg.zzxb, (Object) null, (Object) null);
            zza.a((zzfy) zzff());
            return zza;
        }

        public final boolean isInitialized() {
            return zzfy.a(this.a, false);
        }

        /* renamed from: zza */
        public final BuilderType a(MessageType messagetype) {
            a();
            zza(this.a, messagetype);
            return this;
        }

        public final /* synthetic */ zzed zzcg() {
            return (zza) clone();
        }

        public final /* synthetic */ zzhf zzfb() {
            return this.zzwm;
        }

        /* renamed from: zzfd */
        public MessageType zzff() {
            if (this.b) {
                return this.a;
            }
            MessageType messagetype = this.a;
            zzhs.zzgl().zzs(messagetype).zze(messagetype);
            this.b = true;
            return this.a;
        }

        /* renamed from: zzfe */
        public final MessageType zzfg() {
            MessageType messagetype = (zzfy) zzff();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype.a(zzg.zzwx, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzhs.zzgl().zzs(messagetype).zzr(messagetype);
                    if (booleanValue) {
                        messagetype.a(zzg.zzwy, (Object) z ? messagetype : null, (Object) null);
                    }
                }
            }
            if (z) {
                return messagetype;
            }
            throw new zzin(messagetype);
        }
    }

    public static class zzb<T extends zzfy<T, ?>> extends zzee<T> {
        private final T zzwm;

        public zzb(T t) {
            this.zzwm = t;
        }

        public final /* synthetic */ Object zza(zzez zzez, zzfk zzfk) {
            return zzfy.a(this.zzwm, zzez, zzfk);
        }
    }

    public static abstract class zzc<MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzc<MessageType, BuilderType>> extends zza<MessageType, BuilderType> implements zzhh {
        /* access modifiers changed from: protected */
        public final void a() {
            if (this.b) {
                super.a();
                ((zzd) this.a).zzwp = (zzfp) ((zzd) this.a).zzwp.clone();
            }
        }

        public /* synthetic */ zzfy zzfd() {
            return (zzd) zzff();
        }

        public /* synthetic */ zzhf zzff() {
            zzfy zzfd;
            if (this.b) {
                zzfd = this.a;
            } else {
                ((zzd) this.a).zzwp.zzci();
                zzfd = super.zzff();
            }
            return (zzd) zzfd;
        }
    }

    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzc<MessageType, BuilderType>> extends zzfy<MessageType, BuilderType> implements zzhh {
        protected zzfp<zze> zzwp = zzfp.zzep();

        public final <Type> Type zzc(zzfi<MessageType, Type> zzfi) {
            zzf a = zzfy.zza(zzfi);
            if (a.a == ((zzfy) zzfb())) {
                Type zza = this.zzwp.zza(a.d);
                if (zza == null) {
                    return a.b;
                }
                if (!a.d.d) {
                    return a.a(zza);
                }
                if (a.d.c.zzho() != zzji.ENUM) {
                    return zza;
                }
                Type arrayList = new ArrayList();
                for (Object a2 : (List) zza) {
                    arrayList.add(a.a(a2));
                }
                return arrayList;
            }
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    static final class zze implements zzfr<zze> {
        final zzgc<?> a = null;
        final int b = 202056002;
        final zzjd c;
        final boolean d;
        final boolean e;

        zze(zzgc<?> zzgc, int i, zzjd zzjd, boolean z, boolean z2) {
            this.c = zzjd;
            this.d = true;
            this.e = false;
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return this.b - ((zze) obj).b;
        }

        public final zzhg zza(zzhg zzhg, zzhf zzhf) {
            return ((zza) zzhg).a((zzfy) zzhf);
        }

        public final zzhm zza(zzhm zzhm, zzhm zzhm2) {
            throw new UnsupportedOperationException();
        }

        public final zzjd zzes() {
            return this.c;
        }

        public final zzji zzet() {
            return this.c.zzho();
        }

        public final boolean zzeu() {
            return this.d;
        }

        public final boolean zzev() {
            return this.e;
        }

        public final int zzr() {
            return this.b;
        }
    }

    public static class zzf<ContainingType extends zzhf, Type> extends zzfi<ContainingType, Type> {
        final ContainingType a;
        final Type b;
        final zzhf c;
        final zze d;

        zzf(ContainingType containingtype, Type type, zzhf zzhf, zze zze, Class cls) {
            if (containingtype == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (zze.c == zzjd.MESSAGE && zzhf == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.a = containingtype;
                this.b = type;
                this.c = zzhf;
                this.d = zze;
            }
        }

        /* access modifiers changed from: package-private */
        public final Object a(Object obj) {
            return this.d.c.zzho() == zzji.ENUM ? this.d.a.zzf(((Integer) obj).intValue()) : obj;
        }
    }

    /* 'enum' modifier removed */
    public static final class zzg {
        public static final int zzwx = 1;
        public static final int zzwy = 2;
        public static final int zzwz = 3;
        public static final int zzxa = 4;
        public static final int zzxb = 5;
        public static final int zzxc = 6;
        public static final int zzxd = 7;
        private static final /* synthetic */ int[] zzxe = {zzwx, zzwy, zzwz, zzxa, zzxb, zzxc, zzxd};
        public static final int zzxf = 1;
        public static final int zzxg = 2;
        private static final /* synthetic */ int[] zzxh = {zzxf, zzxg};
        public static final int zzxi = 1;
        public static final int zzxj = 2;
        private static final /* synthetic */ int[] zzxk = {zzxi, zzxj};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzxe.clone();
        }
    }

    static <T extends zzfy<T, ?>> T a(T t, zzez zzez, zzfk zzfk) {
        T t2 = (zzfy) t.a(zzg.zzxa, (Object) null, (Object) null);
        try {
            zzhs.zzgl().zzs(t2).zza(t2, zzfc.zza(zzez), zzfk);
            zzhs.zzgl().zzs(t2).zze(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzgf) {
                throw ((zzgf) e.getCause());
            }
            throw new zzgf(e.getMessage()).zzg(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzgf) {
                throw ((zzgf) e2.getCause());
            }
            throw e2;
        }
    }

    static <T extends zzfy<?, ?>> T a(Class<T> cls) {
        T t = (zzfy) zzwl.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzfy) zzwl.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzfy) ((zzfy) zziu.a(cls)).a(zzg.zzxc, (Object) null, (Object) null);
            if (t != null) {
                zzwl.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static Object a(zzhf zzhf, String str, Object[] objArr) {
        return new zzhu(zzhf, str, objArr);
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

    protected static <T extends zzfy<?, ?>> void a(Class<T> cls, T t) {
        zzwl.put(cls, t);
    }

    protected static final <T extends zzfy<T, ?>> boolean a(T t, boolean z) {
        byte byteValue = ((Byte) t.a(zzg.zzwx, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return zzhs.zzgl().zzs(t).zzr(t);
    }

    protected static <E> zzge<E> c() {
        return zzht.zzgm();
    }

    /* access modifiers changed from: private */
    public static <MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzc<MessageType, BuilderType>, T> zzf<MessageType, T> zza(zzfi<MessageType, T> zzfi) {
        return (zzf) zzfi;
    }

    public static <ContainingType extends zzhf, Type> zzf<ContainingType, Type> zza(ContainingType containingtype, zzhf zzhf, zzgc<?> zzgc, int i, zzjd zzjd, boolean z, Class cls) {
        return new zzf(containingtype, Collections.emptyList(), zzhf, new zze((zzgc<?>) null, 202056002, zzjd, true, false), cls);
    }

    private static <T extends zzfy<T, ?>> T zza(T t, byte[] bArr) {
        T t2 = (zzfy) t.a(zzg.zzxa, (Object) null, (Object) null);
        try {
            zzhs.zzgl().zzs(t2).zza(t2, bArr, 0, bArr.length, new zzei());
            zzhs.zzgl().zzs(t2).zze(t2);
            if (t2.zzri == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzgf) {
                throw ((zzgf) e.getCause());
            }
            throw new zzgf(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzgf.a().zzg(t2);
        }
    }

    private static <T extends zzfy<T, ?>> T zza(T t, byte[] bArr, zzfk zzfk) {
        T a;
        try {
            zzez zzf2 = zzez.zzf(bArr);
            a = a(t, zzf2, zzfk);
            zzf2.zzak(0);
            return a;
        } catch (zzgf e) {
            throw e.zzg(a);
        } catch (zzgf e2) {
            throw e2;
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object a(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        this.zzwk = i;
    }

    /* access modifiers changed from: package-private */
    public final int b() {
        return this.zzwk;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzfy) a(zzg.zzxc, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzhs.zzgl().zzs(this).equals(this, (zzfy) obj);
    }

    public int hashCode() {
        if (this.zzri != 0) {
            return this.zzri;
        }
        this.zzri = zzhs.zzgl().zzs(this).hashCode(this);
        return this.zzri;
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) a(zzg.zzwx, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzr = zzhs.zzgl().zzs(this).zzr(this);
        if (booleanValue) {
            a(zzg.zzwy, (Object) zzr ? this : null, (Object) null);
        }
        return zzr;
    }

    public String toString() {
        return zzhi.a(this, super.toString());
    }

    public final void zzb(zzfe zzfe) {
        zzhs.zzgl().zzf(getClass()).zza(this, zzfg.zza(zzfe));
    }

    public final int zzeq() {
        if (this.zzwk == -1) {
            this.zzwk = zzhs.zzgl().zzs(this).zzp(this);
        }
        return this.zzwk;
    }

    public final /* synthetic */ zzhg zzez() {
        zza zza2 = (zza) a(zzg.zzxb, (Object) null, (Object) null);
        zza2.a(this);
        return zza2;
    }

    public final /* synthetic */ zzhg zzfa() {
        return (zza) a(zzg.zzxb, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzhf zzfb() {
        return (zzfy) a(zzg.zzxc, (Object) null, (Object) null);
    }
}
