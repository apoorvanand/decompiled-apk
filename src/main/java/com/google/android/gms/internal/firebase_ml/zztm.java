package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;

public class zztm extends IOException {
    private zzum zzbnc = null;

    public zztm(String str) {
        super(str);
    }

    static zztm a() {
        return new zztm("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zztm b() {
        return new zztm("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zztm c() {
        return new zztm("Protocol message contained an invalid tag (zero).");
    }

    static zztn d() {
        return new zztn("Protocol message tag had invalid wire type.");
    }

    static zztm e() {
        return new zztm("Failed to parse the message.");
    }

    static zztm f() {
        return new zztm("Protocol message had invalid UTF-8.");
    }

    public final zztm zzg(zzum zzum) {
        this.zzbnc = zzum;
        return this;
    }
}
