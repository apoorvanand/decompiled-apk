package com.google.common.io;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;

@GwtIncompatible
@Beta
public final class ByteStreams {
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() {
        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }

        public void write(int i) {
        }

        public void write(byte[] bArr) {
            Preconditions.checkNotNull(bArr);
        }

        public void write(byte[] bArr, int i, int i2) {
            Preconditions.checkNotNull(bArr);
        }
    };
    private static final int ZERO_COPY_CHUNK_SIZE = 524288;

    private static class ByteArrayDataInputStream implements ByteArrayDataInput {
        final DataInput a;

        ByteArrayDataInputStream(ByteArrayInputStream byteArrayInputStream) {
            this.a = new DataInputStream(byteArrayInputStream);
        }

        public boolean readBoolean() {
            try {
                return this.a.readBoolean();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public byte readByte() {
            try {
                return this.a.readByte();
            } catch (EOFException e) {
                throw new IllegalStateException(e);
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }

        public char readChar() {
            try {
                return this.a.readChar();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public double readDouble() {
            try {
                return this.a.readDouble();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public float readFloat() {
            try {
                return this.a.readFloat();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public void readFully(byte[] bArr) {
            try {
                this.a.readFully(bArr);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public void readFully(byte[] bArr, int i, int i2) {
            try {
                this.a.readFully(bArr, i, i2);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public int readInt() {
            try {
                return this.a.readInt();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String readLine() {
            try {
                return this.a.readLine();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public long readLong() {
            try {
                return this.a.readLong();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public short readShort() {
            try {
                return this.a.readShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String readUTF() {
            try {
                return this.a.readUTF();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public int readUnsignedByte() {
            try {
                return this.a.readUnsignedByte();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public int readUnsignedShort() {
            try {
                return this.a.readUnsignedShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public int skipBytes(int i) {
            try {
                return this.a.skipBytes(i);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private static class ByteArrayDataOutputStream implements ByteArrayDataOutput {
        final DataOutput a;
        final ByteArrayOutputStream b;

        ByteArrayDataOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
            this.b = byteArrayOutputStream;
            this.a = new DataOutputStream(byteArrayOutputStream);
        }

        public byte[] toByteArray() {
            return this.b.toByteArray();
        }

        public void write(int i) {
            try {
                this.a.write(i);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void write(byte[] bArr) {
            try {
                this.a.write(bArr);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void write(byte[] bArr, int i, int i2) {
            try {
                this.a.write(bArr, i, i2);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeBoolean(boolean z) {
            try {
                this.a.writeBoolean(z);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeByte(int i) {
            try {
                this.a.writeByte(i);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeBytes(String str) {
            try {
                this.a.writeBytes(str);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeChar(int i) {
            try {
                this.a.writeChar(i);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeChars(String str) {
            try {
                this.a.writeChars(str);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeDouble(double d) {
            try {
                this.a.writeDouble(d);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeFloat(float f) {
            try {
                this.a.writeFloat(f);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeInt(int i) {
            try {
                this.a.writeInt(i);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeLong(long j) {
            try {
                this.a.writeLong(j);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeShort(int i) {
            try {
                this.a.writeShort(i);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        public void writeUTF(String str) {
            try {
                this.a.writeUTF(str);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    private static final class FastByteArrayOutputStream extends ByteArrayOutputStream {
        private FastByteArrayOutputStream() {
        }

        /* access modifiers changed from: package-private */
        public void a(byte[] bArr, int i) {
            System.arraycopy(this.buf, 0, bArr, i, this.count);
        }
    }

    private static final class LimitedInputStream extends FilterInputStream {
        private long left;
        private long mark = -1;

        LimitedInputStream(InputStream inputStream, long j) {
            super(inputStream);
            Preconditions.checkNotNull(inputStream);
            Preconditions.checkArgument(j >= 0, "limit must be non-negative");
            this.left = j;
        }

        public int available() {
            return (int) Math.min((long) this.in.available(), this.left);
        }

        public synchronized void mark(int i) {
            this.in.mark(i);
            this.mark = this.left;
        }

        public int read() {
            if (this.left == 0) {
                return -1;
            }
            int read = this.in.read();
            if (read != -1) {
                this.left--;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) {
            long j = this.left;
            if (j == 0) {
                return -1;
            }
            int read = this.in.read(bArr, i, (int) Math.min((long) i2, j));
            if (read != -1) {
                this.left -= (long) read;
            }
            return read;
        }

        public synchronized void reset() {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            } else if (this.mark != -1) {
                this.in.reset();
                this.left = this.mark;
            } else {
                throw new IOException("Mark not set");
            }
        }

        public long skip(long j) {
            long skip = this.in.skip(Math.min(j, this.left));
            this.left -= skip;
            return skip;
        }
    }

    private ByteStreams() {
    }

    static long a(InputStream inputStream, long j) {
        byte[] a = a();
        long j2 = 0;
        while (j2 < j) {
            long j3 = j - j2;
            long skipSafely = skipSafely(inputStream, j3);
            if (skipSafely == 0) {
                skipSafely = (long) inputStream.read(a, 0, (int) Math.min(j3, (long) a.length));
                if (skipSafely == -1) {
                    break;
                }
            }
            j2 += skipSafely;
        }
        return j2;
    }

    static byte[] a() {
        return new byte[8192];
    }

    static byte[] a(InputStream inputStream, int i) {
        byte[] bArr = new byte[i];
        int i2 = i;
        while (i2 > 0) {
            int i3 = i - i2;
            int read = inputStream.read(bArr, i3, i2);
            if (read == -1) {
                return Arrays.copyOf(bArr, i3);
            }
            i2 -= read;
        }
        int read2 = inputStream.read();
        if (read2 == -1) {
            return bArr;
        }
        FastByteArrayOutputStream fastByteArrayOutputStream = new FastByteArrayOutputStream();
        fastByteArrayOutputStream.write(read2);
        copy(inputStream, (OutputStream) fastByteArrayOutputStream);
        byte[] bArr2 = new byte[(bArr.length + fastByteArrayOutputStream.size())];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        fastByteArrayOutputStream.a(bArr2, bArr.length);
        return bArr2;
    }

    @CanIgnoreReturnValue
    public static long copy(InputStream inputStream, OutputStream outputStream) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(outputStream);
        byte[] a = a();
        long j = 0;
        while (true) {
            int read = inputStream.read(a);
            if (read == -1) {
                return j;
            }
            outputStream.write(a, 0, read);
            j += (long) read;
        }
    }

    @CanIgnoreReturnValue
    public static long copy(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel) {
        Preconditions.checkNotNull(readableByteChannel);
        Preconditions.checkNotNull(writableByteChannel);
        long j = 0;
        if (readableByteChannel instanceof FileChannel) {
            FileChannel fileChannel = (FileChannel) readableByteChannel;
            long position = fileChannel.position();
            long j2 = position;
            while (true) {
                long transferTo = fileChannel.transferTo(j2, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, writableByteChannel);
                j2 += transferTo;
                fileChannel.position(j2);
                if (transferTo <= 0 && j2 >= fileChannel.size()) {
                    return j2 - position;
                }
            }
        } else {
            ByteBuffer wrap = ByteBuffer.wrap(a());
            while (readableByteChannel.read(wrap) != -1) {
                wrap.flip();
                while (wrap.hasRemaining()) {
                    j += (long) writableByteChannel.write(wrap);
                }
                wrap.clear();
            }
            return j;
        }
    }

    @CanIgnoreReturnValue
    public static long exhaust(InputStream inputStream) {
        byte[] a = a();
        long j = 0;
        while (true) {
            long read = (long) inputStream.read(a);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    public static InputStream limit(InputStream inputStream, long j) {
        return new LimitedInputStream(inputStream, j);
    }

    public static ByteArrayDataInput newDataInput(ByteArrayInputStream byteArrayInputStream) {
        return new ByteArrayDataInputStream((ByteArrayInputStream) Preconditions.checkNotNull(byteArrayInputStream));
    }

    public static ByteArrayDataInput newDataInput(byte[] bArr) {
        return newDataInput(new ByteArrayInputStream(bArr));
    }

    public static ByteArrayDataInput newDataInput(byte[] bArr, int i) {
        Preconditions.checkPositionIndex(i, bArr.length);
        return newDataInput(new ByteArrayInputStream(bArr, i, bArr.length - i));
    }

    public static ByteArrayDataOutput newDataOutput() {
        return newDataOutput(new ByteArrayOutputStream());
    }

    public static ByteArrayDataOutput newDataOutput(int i) {
        if (i >= 0) {
            return newDataOutput(new ByteArrayOutputStream(i));
        }
        throw new IllegalArgumentException(String.format("Invalid size: %s", new Object[]{Integer.valueOf(i)}));
    }

    public static ByteArrayDataOutput newDataOutput(ByteArrayOutputStream byteArrayOutputStream) {
        return new ByteArrayDataOutputStream((ByteArrayOutputStream) Preconditions.checkNotNull(byteArrayOutputStream));
    }

    public static OutputStream nullOutputStream() {
        return NULL_OUTPUT_STREAM;
    }

    @CanIgnoreReturnValue
    public static int read(InputStream inputStream, byte[] bArr, int i, int i2) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(bArr);
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                int read = inputStream.read(bArr, i + i3, i2 - i3);
                if (read == -1) {
                    break;
                }
                i3 += read;
            }
            return i3;
        }
        throw new IndexOutOfBoundsException("len is negative");
    }

    @CanIgnoreReturnValue
    public static <T> T readBytes(InputStream inputStream, ByteProcessor<T> byteProcessor) {
        int read;
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(byteProcessor);
        byte[] a = a();
        do {
            read = inputStream.read(a);
            if (read == -1 || !byteProcessor.processBytes(a, 0, read)) {
            }
            read = inputStream.read(a);
            break;
        } while (!byteProcessor.processBytes(a, 0, read));
        return byteProcessor.getResult();
    }

    public static void readFully(InputStream inputStream, byte[] bArr) {
        readFully(inputStream, bArr, 0, bArr.length);
    }

    public static void readFully(InputStream inputStream, byte[] bArr, int i, int i2) {
        int read = read(inputStream, bArr, i, i2);
        if (read != i2) {
            throw new EOFException("reached end of stream after reading " + read + " bytes; " + i2 + " bytes expected");
        }
    }

    public static void skipFully(InputStream inputStream, long j) {
        long a = a(inputStream, j);
        if (a < j) {
            throw new EOFException("reached end of stream after skipping " + a + " bytes; " + j + " bytes expected");
        }
    }

    private static long skipSafely(InputStream inputStream, long j) {
        int available = inputStream.available();
        if (available == 0) {
            return 0;
        }
        return inputStream.skip(Math.min((long) available, j));
    }

    public static byte[] toByteArray(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(32, inputStream.available()));
        copy(inputStream, (OutputStream) byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
