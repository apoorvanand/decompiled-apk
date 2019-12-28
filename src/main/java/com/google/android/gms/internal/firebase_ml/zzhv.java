package com.google.android.gms.internal.firebase_ml;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzhv extends ByteArrayOutputStream {
    private boolean closed;
    private final Logger logger;
    private int zzaad;
    private final int zzaae;
    private final Level zzaaf;

    public zzhv(Logger logger2, Level level, int i) {
        this.logger = (Logger) zzky.checkNotNull(logger2);
        this.zzaaf = (Level) zzky.checkNotNull(level);
        zzky.checkArgument(i >= 0);
        this.zzaae = i;
    }

    private static void zza(StringBuilder sb, int i) {
        String str;
        if (i == 1) {
            str = "1 byte";
        } else {
            sb.append(NumberFormat.getInstance().format((long) i));
            str = " bytes";
        }
        sb.append(str);
    }

    public final synchronized void close() {
        if (!this.closed) {
            if (this.zzaad != 0) {
                StringBuilder sb = new StringBuilder("Total: ");
                zza(sb, this.zzaad);
                if (this.count != 0 && this.count < this.zzaad) {
                    sb.append(" (logging first ");
                    zza(sb, this.count);
                    sb.append(")");
                }
                this.logger.logp(Level.CONFIG, "com.google.api.client.util.LoggingByteArrayOutputStream", "close", sb.toString());
                if (this.count != 0) {
                    this.logger.logp(this.zzaaf, "com.google.api.client.util.LoggingByteArrayOutputStream", "close", toString("UTF-8").replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", " "));
                }
            }
            this.closed = true;
        }
    }

    public final synchronized void write(int i) {
        zzky.checkArgument(!this.closed);
        this.zzaad++;
        if (this.count < this.zzaae) {
            super.write(i);
        }
    }

    public final synchronized void write(byte[] bArr, int i, int i2) {
        zzky.checkArgument(!this.closed);
        this.zzaad += i2;
        if (this.count < this.zzaae) {
            int i3 = this.count + i2;
            if (i3 > this.zzaae) {
                i2 += this.zzaae - i3;
            }
            super.write(bArr, i, i2);
        }
    }
}
