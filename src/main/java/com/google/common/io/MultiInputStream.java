package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtIncompatible
final class MultiInputStream extends InputStream {
    private InputStream in;
    private Iterator<? extends ByteSource> it;

    public MultiInputStream(Iterator<? extends ByteSource> it2) {
        this.it = (Iterator) Preconditions.checkNotNull(it2);
        advance();
    }

    private void advance() {
        close();
        if (this.it.hasNext()) {
            this.in = ((ByteSource) this.it.next()).openStream();
        }
    }

    public int available() {
        InputStream inputStream = this.in;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    public void close() {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    public boolean markSupported() {
        return false;
    }

    public int read() {
        InputStream inputStream = this.in;
        if (inputStream == null) {
            return -1;
        }
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        advance();
        return read();
    }

    public int read(@Nullable byte[] bArr, int i, int i2) {
        InputStream inputStream = this.in;
        if (inputStream == null) {
            return -1;
        }
        int read = inputStream.read(bArr, i, i2);
        if (read != -1) {
            return read;
        }
        advance();
        return read(bArr, i, i2);
    }

    public long skip(long j) {
        InputStream inputStream = this.in;
        if (inputStream == null || j <= 0) {
            return 0;
        }
        long skip = inputStream.skip(j);
        if (skip != 0) {
            return skip;
        }
        if (read() == -1) {
            return 0;
        }
        return this.in.skip(j - 1) + 1;
    }
}
