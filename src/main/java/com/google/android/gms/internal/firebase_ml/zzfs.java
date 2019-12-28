package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;

public class zzfs extends IOException {
    private final String content;
    private final int statusCode;
    private final transient zzfl zzul;
    private final String zzvf;

    public zzfs(zzfr zzfr) {
        this(new zzft(zzfr));
    }

    protected zzfs(zzft zzft) {
        super(zzft.e);
        this.statusCode = zzft.a;
        this.zzvf = zzft.b;
        this.zzul = zzft.c;
        this.content = zzft.d;
    }

    public static StringBuilder zzc(zzfr zzfr) {
        StringBuilder sb = new StringBuilder();
        int statusCode2 = zzfr.getStatusCode();
        if (statusCode2 != 0) {
            sb.append(statusCode2);
        }
        String statusMessage = zzfr.getStatusMessage();
        if (statusMessage != null) {
            if (statusCode2 != 0) {
                sb.append(' ');
            }
            sb.append(statusMessage);
        }
        return sb;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }
}
