package com.google.android.gms.internal.firebase_ml;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

final class zzgg extends FilterInputStream {
    private long zzwf = 0;
    private final /* synthetic */ zzgf zzwg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzgg(zzgf zzgf, InputStream inputStream) {
        super(inputStream);
        this.zzwg = zzgf;
    }

    private final void zzfg() {
        long contentLength = this.zzwg.getContentLength();
        if (contentLength != -1) {
            long j = this.zzwf;
            if (j != 0 && j < contentLength) {
                StringBuilder sb = new StringBuilder(102);
                sb.append("Connection closed prematurely: bytesRead = ");
                sb.append(j);
                sb.append(", Content-Length = ");
                sb.append(contentLength);
                throw new IOException(sb.toString());
            }
        }
    }

    public final int read() {
        int read = this.in.read();
        if (read == -1) {
            zzfg();
        } else {
            this.zzwf++;
        }
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        if (read == -1) {
            zzfg();
        } else {
            this.zzwf += (long) read;
        }
        return read;
    }

    public final long skip(long j) {
        long skip = this.in.skip(j);
        this.zzwf += skip;
        return skip;
    }
}
