package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class DynamiteModule {
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new zzc();
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzb();
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzif = null;
    @GuardedBy("DynamiteModule.class")
    private static zzi zzig = null;
    @GuardedBy("DynamiteModule.class")
    private static zzk zzih = null;
    @GuardedBy("DynamiteModule.class")
    private static String zzii = null;
    @GuardedBy("DynamiteModule.class")
    private static int zzij = -1;
    private static final ThreadLocal<zza> zzik = new ThreadLocal<>();
    private static final VersionPolicy.zza zzil = new zza();
    private static final VersionPolicy zzim = new zzg();
    private final Context zzin;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, zza zza) {
            this(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zza zza) {
            this(str, th);
        }
    }

    public interface VersionPolicy {

        public interface zza {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z);
        }

        public static class zzb {
            public int zzir = 0;
            public int zzis = 0;
            public int zzit = 0;
        }

        zzb zza(Context context, String str, zza zza2);
    }

    private static class zza {
        public Cursor zzio;

        private zza() {
        }

        /* synthetic */ zza(zza zza) {
            this();
        }
    }

    private static class zzb implements VersionPolicy.zza {
        private final int zzip;
        private final int zziq = 0;

        public zzb(int i, int i2) {
            this.zzip = i;
        }

        public final int getLocalVersion(Context context, String str) {
            return this.zzip;
        }

        public final int zza(Context context, String str, boolean z) {
            return 0;
        }
    }

    private DynamiteModule(Context context) {
        this.zzin = (Context) Preconditions.checkNotNull(context);
    }

    @KeepForSdk
    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get((Object) null).equals(str)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf = String.valueOf(declaredField.get((Object) null));
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String valueOf2 = String.valueOf(e.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    @KeepForSdk
    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007e, code lost:
        if (r1.zzio != null) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0097, code lost:
        if (r1.zzio != null) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d5, code lost:
        if (r1.zzio != null) goto L_0x0080;
     */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.dynamite.DynamiteModule load(android.content.Context r10, com.google.android.gms.dynamite.DynamiteModule.VersionPolicy r11, java.lang.String r12) {
        /*
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r0 = zzik
            java.lang.Object r0 = r0.get()
            com.google.android.gms.dynamite.DynamiteModule$zza r0 = (com.google.android.gms.dynamite.DynamiteModule.zza) r0
            com.google.android.gms.dynamite.DynamiteModule$zza r1 = new com.google.android.gms.dynamite.DynamiteModule$zza
            r2 = 0
            r1.<init>(r2)
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r3 = zzik
            r3.set(r1)
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$zza r3 = zzil     // Catch:{ all -> 0x0125 }
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$zzb r3 = r11.zza(r10, r12, r3)     // Catch:{ all -> 0x0125 }
            java.lang.String r4 = "DynamiteModule"
            int r5 = r3.zzir     // Catch:{ all -> 0x0125 }
            int r6 = r3.zzis     // Catch:{ all -> 0x0125 }
            java.lang.String r7 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0125 }
            int r7 = r7.length()     // Catch:{ all -> 0x0125 }
            int r7 = r7 + 68
            java.lang.String r8 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0125 }
            int r8 = r8.length()     // Catch:{ all -> 0x0125 }
            int r7 = r7 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0125 }
            r8.<init>(r7)     // Catch:{ all -> 0x0125 }
            java.lang.String r7 = "Considering local module "
            r8.append(r7)     // Catch:{ all -> 0x0125 }
            r8.append(r12)     // Catch:{ all -> 0x0125 }
            java.lang.String r7 = ":"
            r8.append(r7)     // Catch:{ all -> 0x0125 }
            r8.append(r5)     // Catch:{ all -> 0x0125 }
            java.lang.String r5 = " and remote module "
            r8.append(r5)     // Catch:{ all -> 0x0125 }
            r8.append(r12)     // Catch:{ all -> 0x0125 }
            java.lang.String r5 = ":"
            r8.append(r5)     // Catch:{ all -> 0x0125 }
            r8.append(r6)     // Catch:{ all -> 0x0125 }
            java.lang.String r5 = r8.toString()     // Catch:{ all -> 0x0125 }
            android.util.Log.i(r4, r5)     // Catch:{ all -> 0x0125 }
            int r4 = r3.zzit     // Catch:{ all -> 0x0125 }
            if (r4 == 0) goto L_0x00fb
            int r4 = r3.zzit     // Catch:{ all -> 0x0125 }
            r5 = -1
            if (r4 != r5) goto L_0x006b
            int r4 = r3.zzir     // Catch:{ all -> 0x0125 }
            if (r4 == 0) goto L_0x00fb
        L_0x006b:
            int r4 = r3.zzit     // Catch:{ all -> 0x0125 }
            r6 = 1
            if (r4 != r6) goto L_0x0074
            int r4 = r3.zzis     // Catch:{ all -> 0x0125 }
            if (r4 == 0) goto L_0x00fb
        L_0x0074:
            int r4 = r3.zzit     // Catch:{ all -> 0x0125 }
            if (r4 != r5) goto L_0x008b
            com.google.android.gms.dynamite.DynamiteModule r10 = zze(r10, r12)     // Catch:{ all -> 0x0125 }
            android.database.Cursor r11 = r1.zzio
            if (r11 == 0) goto L_0x0085
        L_0x0080:
            android.database.Cursor r11 = r1.zzio
            r11.close()
        L_0x0085:
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r11 = zzik
            r11.set(r0)
            return r10
        L_0x008b:
            int r4 = r3.zzit     // Catch:{ all -> 0x0125 }
            if (r4 != r6) goto L_0x00e0
            int r4 = r3.zzis     // Catch:{ LoadingException -> 0x009a }
            com.google.android.gms.dynamite.DynamiteModule r10 = zza((android.content.Context) r10, (java.lang.String) r12, (int) r4)     // Catch:{ LoadingException -> 0x009a }
            android.database.Cursor r11 = r1.zzio
            if (r11 == 0) goto L_0x0085
            goto L_0x0080
        L_0x009a:
            r4 = move-exception
            java.lang.String r6 = "DynamiteModule"
            java.lang.String r7 = "Failed to load remote module: "
            java.lang.String r8 = r4.getMessage()     // Catch:{ all -> 0x0125 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0125 }
            int r9 = r8.length()     // Catch:{ all -> 0x0125 }
            if (r9 == 0) goto L_0x00b2
            java.lang.String r7 = r7.concat(r8)     // Catch:{ all -> 0x0125 }
            goto L_0x00b8
        L_0x00b2:
            java.lang.String r8 = new java.lang.String     // Catch:{ all -> 0x0125 }
            r8.<init>(r7)     // Catch:{ all -> 0x0125 }
            r7 = r8
        L_0x00b8:
            android.util.Log.w(r6, r7)     // Catch:{ all -> 0x0125 }
            int r6 = r3.zzir     // Catch:{ all -> 0x0125 }
            if (r6 == 0) goto L_0x00d8
            com.google.android.gms.dynamite.DynamiteModule$zzb r6 = new com.google.android.gms.dynamite.DynamiteModule$zzb     // Catch:{ all -> 0x0125 }
            int r3 = r3.zzir     // Catch:{ all -> 0x0125 }
            r7 = 0
            r6.<init>(r3, r7)     // Catch:{ all -> 0x0125 }
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$zzb r11 = r11.zza(r10, r12, r6)     // Catch:{ all -> 0x0125 }
            int r11 = r11.zzit     // Catch:{ all -> 0x0125 }
            if (r11 != r5) goto L_0x00d8
            com.google.android.gms.dynamite.DynamiteModule r10 = zze(r10, r12)     // Catch:{ all -> 0x0125 }
            android.database.Cursor r11 = r1.zzio
            if (r11 == 0) goto L_0x0085
            goto L_0x0080
        L_0x00d8:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x0125 }
            java.lang.String r11 = "Remote load failed. No local fallback found."
            r10.<init>(r11, r4, r2)     // Catch:{ all -> 0x0125 }
            throw r10     // Catch:{ all -> 0x0125 }
        L_0x00e0:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x0125 }
            int r11 = r3.zzit     // Catch:{ all -> 0x0125 }
            r12 = 47
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0125 }
            r3.<init>(r12)     // Catch:{ all -> 0x0125 }
            java.lang.String r12 = "VersionPolicy returned invalid code:"
            r3.append(r12)     // Catch:{ all -> 0x0125 }
            r3.append(r11)     // Catch:{ all -> 0x0125 }
            java.lang.String r11 = r3.toString()     // Catch:{ all -> 0x0125 }
            r10.<init>((java.lang.String) r11, (com.google.android.gms.dynamite.zza) r2)     // Catch:{ all -> 0x0125 }
            throw r10     // Catch:{ all -> 0x0125 }
        L_0x00fb:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x0125 }
            int r11 = r3.zzir     // Catch:{ all -> 0x0125 }
            int r12 = r3.zzis     // Catch:{ all -> 0x0125 }
            r3 = 91
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0125 }
            r4.<init>(r3)     // Catch:{ all -> 0x0125 }
            java.lang.String r3 = "No acceptable module found. Local version is "
            r4.append(r3)     // Catch:{ all -> 0x0125 }
            r4.append(r11)     // Catch:{ all -> 0x0125 }
            java.lang.String r11 = " and remote version is "
            r4.append(r11)     // Catch:{ all -> 0x0125 }
            r4.append(r12)     // Catch:{ all -> 0x0125 }
            java.lang.String r11 = "."
            r4.append(r11)     // Catch:{ all -> 0x0125 }
            java.lang.String r11 = r4.toString()     // Catch:{ all -> 0x0125 }
            r10.<init>((java.lang.String) r11, (com.google.android.gms.dynamite.zza) r2)     // Catch:{ all -> 0x0125 }
            throw r10     // Catch:{ all -> 0x0125 }
        L_0x0125:
            r10 = move-exception
            android.database.Cursor r11 = r1.zzio
            if (r11 == 0) goto L_0x012f
            android.database.Cursor r11 = r1.zzio
            r11.close()
        L_0x012f:
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r11 = zzik
            r11.set(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.load(android.content.Context, com.google.android.gms.dynamite.DynamiteModule$VersionPolicy, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        	at java.util.ArrayList.get(ArrayList.java:433)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0050=Splitter:B:23:0x0050, B:18:0x0035=Splitter:B:18:0x0035, B:35:0x0079=Splitter:B:35:0x0079} */
    public static int zza(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r0 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r0)     // Catch:{ Throwable -> 0x00ed }
            java.lang.Boolean r1 = zzif     // Catch:{ all -> 0x00ea }
            if (r1 != 0) goto L_0x00b7
            android.content.Context r1 = r8.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x008e, IllegalAccessException -> 0x008c, NoSuchFieldException -> 0x008a }
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x008e, IllegalAccessException -> 0x008c, NoSuchFieldException -> 0x008a }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r2 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r2 = r2.getName()     // Catch:{ ClassNotFoundException -> 0x008e, IllegalAccessException -> 0x008c, NoSuchFieldException -> 0x008a }
            java.lang.Class r1 = r1.loadClass(r2)     // Catch:{ ClassNotFoundException -> 0x008e, IllegalAccessException -> 0x008c, NoSuchFieldException -> 0x008a }
            java.lang.String r2 = "sClassLoader"
            java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ ClassNotFoundException -> 0x008e, IllegalAccessException -> 0x008c, NoSuchFieldException -> 0x008a }
            monitor-enter(r1)     // Catch:{ ClassNotFoundException -> 0x008e, IllegalAccessException -> 0x008c, NoSuchFieldException -> 0x008a }
            r3 = 0
            java.lang.Object r4 = r2.get(r3)     // Catch:{ all -> 0x0087 }
            java.lang.ClassLoader r4 = (java.lang.ClassLoader) r4     // Catch:{ all -> 0x0087 }
            if (r4 == 0) goto L_0x0038
            java.lang.ClassLoader r2 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0087 }
            if (r4 != r2) goto L_0x0032
        L_0x002f:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0087 }
            goto L_0x0084
        L_0x0032:
            zza(r4)     // Catch:{ LoadingException -> 0x0035 }
        L_0x0035:
            java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0087 }
            goto L_0x0084
        L_0x0038:
            java.lang.String r4 = "com.google.android.gms"
            android.content.Context r5 = r8.getApplicationContext()     // Catch:{ all -> 0x0087 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ all -> 0x0087 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0087 }
            if (r4 == 0) goto L_0x0050
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0087 }
            r2.set(r3, r4)     // Catch:{ all -> 0x0087 }
            goto L_0x002f
        L_0x0050:
            int r4 = zzc(r8, r9, r10)     // Catch:{ LoadingException -> 0x007c }
            java.lang.String r5 = zzii     // Catch:{ LoadingException -> 0x007c }
            if (r5 == 0) goto L_0x0079
            java.lang.String r5 = zzii     // Catch:{ LoadingException -> 0x007c }
            boolean r5 = r5.isEmpty()     // Catch:{ LoadingException -> 0x007c }
            if (r5 == 0) goto L_0x0061
            goto L_0x0079
        L_0x0061:
            com.google.android.gms.dynamite.zzh r5 = new com.google.android.gms.dynamite.zzh     // Catch:{ LoadingException -> 0x007c }
            java.lang.String r6 = zzii     // Catch:{ LoadingException -> 0x007c }
            java.lang.ClassLoader r7 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x007c }
            r5.<init>(r6, r7)     // Catch:{ LoadingException -> 0x007c }
            zza(r5)     // Catch:{ LoadingException -> 0x007c }
            r2.set(r3, r5)     // Catch:{ LoadingException -> 0x007c }
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ LoadingException -> 0x007c }
            zzif = r5     // Catch:{ LoadingException -> 0x007c }
            monitor-exit(r1)     // Catch:{ all -> 0x0087 }
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            return r4
        L_0x0079:
            monitor-exit(r1)     // Catch:{ all -> 0x0087 }
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            return r4
        L_0x007c:
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0087 }
            r2.set(r3, r4)     // Catch:{ all -> 0x0087 }
            goto L_0x002f
        L_0x0084:
            monitor-exit(r1)     // Catch:{ all -> 0x0087 }
            r1 = r2
            goto L_0x00b5
        L_0x0087:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0087 }
            throw r2     // Catch:{ ClassNotFoundException -> 0x008e, IllegalAccessException -> 0x008c, NoSuchFieldException -> 0x008a }
        L_0x008a:
            r1 = move-exception
            goto L_0x008f
        L_0x008c:
            r1 = move-exception
            goto L_0x008f
        L_0x008e:
            r1 = move-exception
        L_0x008f:
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00ea }
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00ea }
            int r3 = r3.length()     // Catch:{ all -> 0x00ea }
            int r3 = r3 + 30
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r4.<init>(r3)     // Catch:{ all -> 0x00ea }
            java.lang.String r3 = "Failed to load module via V2: "
            r4.append(r3)     // Catch:{ all -> 0x00ea }
            r4.append(r1)     // Catch:{ all -> 0x00ea }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x00ea }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00ea }
        L_0x00b5:
            zzif = r1     // Catch:{ all -> 0x00ea }
        L_0x00b7:
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            boolean r0 = r1.booleanValue()     // Catch:{ Throwable -> 0x00ed }
            if (r0 == 0) goto L_0x00e5
            int r8 = zzc(r8, r9, r10)     // Catch:{ LoadingException -> 0x00c3 }
            return r8
        L_0x00c3:
            r9 = move-exception
            java.lang.String r10 = "DynamiteModule"
            java.lang.String r0 = "Failed to retrieve remote module version: "
            java.lang.String r9 = r9.getMessage()     // Catch:{ Throwable -> 0x00ed }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Throwable -> 0x00ed }
            int r1 = r9.length()     // Catch:{ Throwable -> 0x00ed }
            if (r1 == 0) goto L_0x00db
            java.lang.String r9 = r0.concat(r9)     // Catch:{ Throwable -> 0x00ed }
            goto L_0x00e0
        L_0x00db:
            java.lang.String r9 = new java.lang.String     // Catch:{ Throwable -> 0x00ed }
            r9.<init>(r0)     // Catch:{ Throwable -> 0x00ed }
        L_0x00e0:
            android.util.Log.w(r10, r9)     // Catch:{ Throwable -> 0x00ed }
            r8 = 0
            return r8
        L_0x00e5:
            int r8 = zzb((android.content.Context) r8, (java.lang.String) r9, (boolean) r10)     // Catch:{ Throwable -> 0x00ed }
            return r8
        L_0x00ea:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            throw r9     // Catch:{ Throwable -> 0x00ed }
        L_0x00ed:
            r9 = move-exception
            com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r8, r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    private static DynamiteModule zza(Context context, String str, int i) {
        Boolean bool;
        IObjectWrapper iObjectWrapper;
        try {
            synchronized (DynamiteModule.class) {
                bool = zzif;
            }
            if (bool == null) {
                throw new LoadingException("Failed to determine which loading route to use.", (zza) null);
            } else if (bool.booleanValue()) {
                return zzb(context, str, i);
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
                sb.append("Selected remote version of ");
                sb.append(str);
                sb.append(", version >= ");
                sb.append(i);
                Log.i("DynamiteModule", sb.toString());
                zzi zzj = zzj(context);
                if (zzj != null) {
                    if (zzj.zzak() >= 2) {
                        iObjectWrapper = zzj.zzb(ObjectWrapper.wrap(context), str, i);
                    } else {
                        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                        iObjectWrapper = zzj.zza(ObjectWrapper.wrap(context), str, i);
                    }
                    if (ObjectWrapper.unwrap(iObjectWrapper) != null) {
                        return new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapper));
                    }
                    throw new LoadingException("Failed to load remote module.", (zza) null);
                }
                throw new LoadingException("Failed to create IDynamiteLoader.", (zza) null);
            }
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, (zza) null);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw new LoadingException("Failed to load remote module.", th, (zza) null);
        }
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    @javax.annotation.concurrent.GuardedBy("DynamiteModule.class")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(java.lang.ClassLoader r3) {
        /*
            r0 = 0
            java.lang.String r1 = "com.google.android.gms.dynamiteloader.DynamiteLoaderV2"
            java.lang.Class r3 = r3.loadClass(r1)     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            r1 = 0
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            java.lang.reflect.Constructor r3 = r3.getConstructor(r2)     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            java.lang.Object r3 = r3.newInstance(r1)     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            android.os.IBinder r3 = (android.os.IBinder) r3     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            if (r3 != 0) goto L_0x001a
            r3 = r0
            goto L_0x002e
        L_0x001a:
            java.lang.String r1 = "com.google.android.gms.dynamite.IDynamiteLoaderV2"
            android.os.IInterface r1 = r3.queryLocalInterface(r1)     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            boolean r2 = r1 instanceof com.google.android.gms.dynamite.zzk     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            if (r2 == 0) goto L_0x0028
            r3 = r1
            com.google.android.gms.dynamite.zzk r3 = (com.google.android.gms.dynamite.zzk) r3     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            goto L_0x002e
        L_0x0028:
            com.google.android.gms.dynamite.zzl r1 = new com.google.android.gms.dynamite.zzl     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            r1.<init>(r3)     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            r3 = r1
        L_0x002e:
            zzih = r3     // Catch:{ ClassNotFoundException -> 0x0039, IllegalAccessException -> 0x0037, InstantiationException -> 0x0035, InvocationTargetException -> 0x0033, NoSuchMethodException -> 0x0031 }
            return
        L_0x0031:
            r3 = move-exception
            goto L_0x003a
        L_0x0033:
            r3 = move-exception
            goto L_0x003a
        L_0x0035:
            r3 = move-exception
            goto L_0x003a
        L_0x0037:
            r3 = move-exception
            goto L_0x003a
        L_0x0039:
            r3 = move-exception
        L_0x003a:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r1 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException
            java.lang.String r2 = "Failed to instantiate dynamite loader"
            r1.<init>(r2, r3, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(java.lang.ClassLoader):void");
    }

    private static Boolean zzaj() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zzij >= 2);
        }
        return valueOf;
    }

    private static int zzb(Context context, String str, boolean z) {
        zzi zzj = zzj(context);
        if (zzj == null) {
            return 0;
        }
        try {
            if (zzj.zzak() >= 2) {
                return zzj.zzb(ObjectWrapper.wrap(context), str, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzj.zza(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    private static DynamiteModule zzb(Context context, String str, int i) {
        zzk zzk;
        IObjectWrapper iObjectWrapper;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            zzk = zzih;
        }
        if (zzk != null) {
            zza zza2 = zzik.get();
            if (zza2 == null || zza2.zzio == null) {
                throw new LoadingException("No result cursor", (zza) null);
            }
            Context applicationContext = context.getApplicationContext();
            Cursor cursor = zza2.zzio;
            ObjectWrapper.wrap(null);
            if (zzaj().booleanValue()) {
                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                iObjectWrapper = zzk.zzb(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                iObjectWrapper = zzk.zza(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
            }
            Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            if (context2 != null) {
                return new DynamiteModule(context2);
            }
            throw new LoadingException("Failed to get module context", (zza) null);
        }
        throw new LoadingException("DynamiteLoaderV2 was not cached.", (zza) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzc(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            if (r10 == 0) goto L_0x000a
            java.lang.String r8 = "api_force_staging"
            goto L_0x000c
        L_0x000a:
            java.lang.String r8 = "api"
        L_0x000c:
            java.lang.String r10 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            int r10 = r10.length()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            int r10 = r10 + 42
            java.lang.String r2 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            int r2 = r2.length()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            int r10 = r10 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r2.<init>(r10)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.String r10 = "content://com.google.android.gms.chimera/"
            r2.append(r10)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r2.append(r8)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.String r8 = "/"
            r2.append(r8)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r2.append(r9)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            java.lang.String r8 = r2.toString()     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            android.net.Uri r2 = android.net.Uri.parse(r8)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00a1, all -> 0x009f }
            if (r8 == 0) goto L_0x0087
            boolean r9 = r8.moveToFirst()     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            if (r9 == 0) goto L_0x0087
            r9 = 0
            int r9 = r8.getInt(r9)     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            if (r9 <= 0) goto L_0x0081
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r10 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r10)     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            r1 = 2
            java.lang.String r1 = r8.getString(r1)     // Catch:{ all -> 0x007e }
            zzii = r1     // Catch:{ all -> 0x007e }
            java.lang.String r1 = "loaderVersion"
            int r1 = r8.getColumnIndex(r1)     // Catch:{ all -> 0x007e }
            if (r1 < 0) goto L_0x006b
            int r1 = r8.getInt(r1)     // Catch:{ all -> 0x007e }
            zzij = r1     // Catch:{ all -> 0x007e }
        L_0x006b:
            monitor-exit(r10)     // Catch:{ all -> 0x007e }
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r10 = zzik     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            java.lang.Object r10 = r10.get()     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            com.google.android.gms.dynamite.DynamiteModule$zza r10 = (com.google.android.gms.dynamite.DynamiteModule.zza) r10     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            if (r10 == 0) goto L_0x0081
            android.database.Cursor r1 = r10.zzio     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            if (r1 != 0) goto L_0x0081
            r10.zzio = r8     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            r8 = r0
            goto L_0x0081
        L_0x007e:
            r9 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x007e }
            throw r9     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
        L_0x0081:
            if (r8 == 0) goto L_0x0086
            r8.close()
        L_0x0086:
            return r9
        L_0x0087:
            java.lang.String r9 = "DynamiteModule"
            java.lang.String r10 = "Failed to retrieve remote module version."
            android.util.Log.w(r9, r10)     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r9 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            java.lang.String r10 = "Failed to connect to dynamite module ContentResolver."
            r9.<init>((java.lang.String) r10, (com.google.android.gms.dynamite.zza) r0)     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
            throw r9     // Catch:{ Exception -> 0x009a, all -> 0x0096 }
        L_0x0096:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto L_0x00b2
        L_0x009a:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto L_0x00a3
        L_0x009f:
            r8 = move-exception
            goto L_0x00b2
        L_0x00a1:
            r8 = move-exception
            r9 = r0
        L_0x00a3:
            boolean r10 = r8 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch:{ all -> 0x00b0 }
            if (r10 == 0) goto L_0x00a8
            throw r8     // Catch:{ all -> 0x00b0 }
        L_0x00a8:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x00b0 }
            java.lang.String r1 = "V2 version check failed"
            r10.<init>(r1, r8, r0)     // Catch:{ all -> 0x00b0 }
            throw r10     // Catch:{ all -> 0x00b0 }
        L_0x00b0:
            r8 = move-exception
            r0 = r9
        L_0x00b2:
            if (r0 == 0) goto L_0x00b7
            r0.close()
        L_0x00b7:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzc(android.content.Context, java.lang.String, boolean):int");
    }

    private static DynamiteModule zze(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    /* JADX WARNING: type inference failed for: r1v7, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.dynamite.zzi zzj(android.content.Context r5) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r0 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r0)
            com.google.android.gms.dynamite.zzi r1 = zzig     // Catch:{ all -> 0x006f }
            if (r1 == 0) goto L_0x000b
            com.google.android.gms.dynamite.zzi r5 = zzig     // Catch:{ all -> 0x006f }
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            return r5
        L_0x000b:
            com.google.android.gms.common.GoogleApiAvailabilityLight r1 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()     // Catch:{ all -> 0x006f }
            int r1 = r1.isGooglePlayServicesAvailable(r5)     // Catch:{ all -> 0x006f }
            r2 = 0
            if (r1 == 0) goto L_0x0018
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            return r2
        L_0x0018:
            java.lang.String r1 = "com.google.android.gms"
            r3 = 3
            android.content.Context r5 = r5.createPackageContext(r1, r3)     // Catch:{ Exception -> 0x004d }
            java.lang.ClassLoader r5 = r5.getClassLoader()     // Catch:{ Exception -> 0x004d }
            java.lang.String r1 = "com.google.android.gms.chimera.container.DynamiteLoaderImpl"
            java.lang.Class r5 = r5.loadClass(r1)     // Catch:{ Exception -> 0x004d }
            java.lang.Object r5 = r5.newInstance()     // Catch:{ Exception -> 0x004d }
            android.os.IBinder r5 = (android.os.IBinder) r5     // Catch:{ Exception -> 0x004d }
            if (r5 != 0) goto L_0x0033
            r5 = r2
            goto L_0x0047
        L_0x0033:
            java.lang.String r1 = "com.google.android.gms.dynamite.IDynamiteLoader"
            android.os.IInterface r1 = r5.queryLocalInterface(r1)     // Catch:{ Exception -> 0x004d }
            boolean r3 = r1 instanceof com.google.android.gms.dynamite.zzi     // Catch:{ Exception -> 0x004d }
            if (r3 == 0) goto L_0x0041
            r5 = r1
            com.google.android.gms.dynamite.zzi r5 = (com.google.android.gms.dynamite.zzi) r5     // Catch:{ Exception -> 0x004d }
            goto L_0x0047
        L_0x0041:
            com.google.android.gms.dynamite.zzj r1 = new com.google.android.gms.dynamite.zzj     // Catch:{ Exception -> 0x004d }
            r1.<init>(r5)     // Catch:{ Exception -> 0x004d }
            r5 = r1
        L_0x0047:
            if (r5 == 0) goto L_0x006d
            zzig = r5     // Catch:{ Exception -> 0x004d }
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            return r5
        L_0x004d:
            r5 = move-exception
            java.lang.String r1 = "DynamiteModule"
            java.lang.String r3 = "Failed to load IDynamiteLoader from GmsCore: "
            java.lang.String r5 = r5.getMessage()     // Catch:{ all -> 0x006f }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x006f }
            int r4 = r5.length()     // Catch:{ all -> 0x006f }
            if (r4 == 0) goto L_0x0065
            java.lang.String r5 = r3.concat(r5)     // Catch:{ all -> 0x006f }
            goto L_0x006a
        L_0x0065:
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x006f }
            r5.<init>(r3)     // Catch:{ all -> 0x006f }
        L_0x006a:
            android.util.Log.e(r1, r5)     // Catch:{ all -> 0x006f }
        L_0x006d:
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            return r2
        L_0x006f:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzj(android.content.Context):com.google.android.gms.dynamite.zzi");
    }

    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzin;
    }

    @KeepForSdk
    public final IBinder instantiate(String str) {
        try {
            return (IBinder) this.zzin.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e, (zza) null);
        }
    }
}