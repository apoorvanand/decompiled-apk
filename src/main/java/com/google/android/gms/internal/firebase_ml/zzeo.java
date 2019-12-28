package com.google.android.gms.internal.firebase_ml;

public final class zzeo {
    public static final String VERSION;
    public static final Integer zzsk = 1;
    public static final Integer zzsl = 26;
    private static final Integer zzsm = 0;

    static {
        String valueOf = String.valueOf(zzsk);
        String valueOf2 = String.valueOf(zzsl);
        String valueOf3 = String.valueOf(zzsm);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 11 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append(valueOf);
        sb.append(".");
        sb.append(valueOf2);
        sb.append(".");
        sb.append(valueOf3);
        sb.append("-SNAPSHOT");
        VERSION = sb.toString().toString();
    }
}
