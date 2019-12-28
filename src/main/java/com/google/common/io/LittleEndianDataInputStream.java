package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.InputStream;

@GwtIncompatible
@Beta
public final class LittleEndianDataInputStream extends FilterInputStream implements DataInput {
    public LittleEndianDataInputStream(InputStream inputStream) {
        super((InputStream) Preconditions.checkNotNull(inputStream));
    }

    private byte readAndCheckByte() {
        int read = this.in.read();
        if (-1 != read) {
            return (byte) read;
        }
        throw new EOFException();
    }

    @CanIgnoreReturnValue
    public boolean readBoolean() {
        return readUnsignedByte() != 0;
    }

    @CanIgnoreReturnValue
    public byte readByte() {
        return (byte) readUnsignedByte();
    }

    @CanIgnoreReturnValue
    public char readChar() {
        return (char) readUnsignedShort();
    }

    @CanIgnoreReturnValue
    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @CanIgnoreReturnValue
    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public void readFully(byte[] bArr) {
        ByteStreams.readFully(this, bArr);
    }

    public void readFully(byte[] bArr, int i, int i2) {
        ByteStreams.readFully(this, bArr, i, i2);
    }

    @CanIgnoreReturnValue
    public int readInt() {
        byte readAndCheckByte = readAndCheckByte();
        byte readAndCheckByte2 = readAndCheckByte();
        return Ints.fromBytes(readAndCheckByte(), readAndCheckByte(), readAndCheckByte2, readAndCheckByte);
    }

    @CanIgnoreReturnValue
    public String readLine() {
        throw new UnsupportedOperationException("readLine is not supported");
    }

    @CanIgnoreReturnValue
    public long readLong() {
        byte readAndCheckByte = readAndCheckByte();
        byte readAndCheckByte2 = readAndCheckByte();
        byte readAndCheckByte3 = readAndCheckByte();
        byte readAndCheckByte4 = readAndCheckByte();
        byte readAndCheckByte5 = readAndCheckByte();
        byte readAndCheckByte6 = readAndCheckByte();
        return Longs.fromBytes(readAndCheckByte(), readAndCheckByte(), readAndCheckByte6, readAndCheckByte5, readAndCheckByte4, readAndCheckByte3, readAndCheckByte2, readAndCheckByte);
    }

    @CanIgnoreReturnValue
    public short readShort() {
        return (short) readUnsignedShort();
    }

    @CanIgnoreReturnValue
    public String readUTF() {
        return new DataInputStream(this.in).readUTF();
    }

    @CanIgnoreReturnValue
    public int readUnsignedByte() {
        int read = this.in.read();
        if (read >= 0) {
            return read;
        }
        throw new EOFException();
    }

    @CanIgnoreReturnValue
    public int readUnsignedShort() {
        return Ints.fromBytes((byte) 0, (byte) 0, readAndCheckByte(), readAndCheckByte());
    }

    public int skipBytes(int i) {
        return (int) this.in.skip((long) i);
    }
}
