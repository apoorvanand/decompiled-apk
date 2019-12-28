package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Beta
public final class HashingInputStream extends FilterInputStream {
    private final Hasher hasher;

    public HashingInputStream(HashFunction hashFunction, InputStream inputStream) {
        super((InputStream) Preconditions.checkNotNull(inputStream));
        this.hasher = (Hasher) Preconditions.checkNotNull(hashFunction.newHasher());
    }

    public HashCode hash() {
        return this.hasher.hash();
    }

    public void mark(int i) {
    }

    public boolean markSupported() {
        return false;
    }

    @CanIgnoreReturnValue
    public int read() {
        int read = this.in.read();
        if (read != -1) {
            this.hasher.putByte((byte) read);
        }
        return read;
    }

    @CanIgnoreReturnValue
    public int read(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            this.hasher.putBytes(bArr, i, read);
        }
        return read;
    }

    public void reset() {
        throw new IOException("reset not supported");
    }
}