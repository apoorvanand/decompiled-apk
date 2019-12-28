package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

public class zzic extends IOException {
    private zzjc zzabf = null;

    public zzic(String str) {
        super(str);
    }

    static zzic a() {
        return new zzic("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzic b() {
        return new zzic("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzic c() {
        return new zzic("CodedInputStream encountered a malformed varint.");
    }

    static zzic d() {
        return new zzic("Protocol message contained an invalid tag (zero).");
    }

    static zzic e() {
        return new zzic("Protocol message end-group tag did not match expected tag.");
    }

    static zzib f() {
        return new zzib("Protocol message tag had invalid wire type.");
    }

    static zzic g() {
        return new zzic("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    static zzic h() {
        return new zzic("Failed to parse the message.");
    }

    static zzic i() {
        return new zzic("Protocol message had invalid UTF-8.");
    }

    public final zzic zzh(zzjc zzjc) {
        this.zzabf = zzjc;
        return this;
    }
}
