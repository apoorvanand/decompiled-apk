package com.google.android.gms.internal.vision;

import java.io.IOException;

public class zzgf extends IOException {
    private zzhf zzxq = null;

    public zzgf(String str) {
        super(str);
    }

    static zzgf a() {
        return new zzgf("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzgf b() {
        return new zzgf("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzgf c() {
        return new zzgf("CodedInputStream encountered a malformed varint.");
    }

    static zzgf d() {
        return new zzgf("Protocol message contained an invalid tag (zero).");
    }

    static zzgf e() {
        return new zzgf("Protocol message end-group tag did not match expected tag.");
    }

    static zzgg f() {
        return new zzgg("Protocol message tag had invalid wire type.");
    }

    static zzgf g() {
        return new zzgf("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    static zzgf h() {
        return new zzgf("Failed to parse the message.");
    }

    static zzgf i() {
        return new zzgf("Protocol message had invalid UTF-8.");
    }

    public final zzgf zzg(zzhf zzhf) {
        this.zzxq = zzhf;
        return this;
    }
}
