package com.google.android.gms.internal.measurement;

public final class zzjh implements zzdb<zzjk> {
    private static zzjh zzapo = new zzjh();
    private final zzdb<zzjk> zzapj;

    public zzjh() {
        this(zzda.zzg(new zzjj()));
    }

    private zzjh(zzdb<zzjk> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public static boolean zzxj() {
        return ((zzjk) zzapo.get()).zzxj();
    }

    public static boolean zzxk() {
        return ((zzjk) zzapo.get()).zzxk();
    }

    public static boolean zzxl() {
        return ((zzjk) zzapo.get()).zzxl();
    }

    public final /* synthetic */ Object get() {
        return this.zzapj.get();
    }
}
