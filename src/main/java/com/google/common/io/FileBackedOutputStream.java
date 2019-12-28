package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@GwtIncompatible
@Beta
public final class FileBackedOutputStream extends OutputStream {
    private File file;
    private final int fileThreshold;
    private MemoryOutput memory;
    private OutputStream out;
    private final boolean resetOnFinalize;
    private final ByteSource source;

    private static class MemoryOutput extends ByteArrayOutputStream {
        private MemoryOutput() {
        }

        /* access modifiers changed from: package-private */
        public byte[] a() {
            return this.buf;
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return this.count;
        }
    }

    public FileBackedOutputStream(int i) {
        this(i, false);
    }

    public FileBackedOutputStream(int i, boolean z) {
        this.fileThreshold = i;
        this.resetOnFinalize = z;
        this.memory = new MemoryOutput();
        this.out = this.memory;
        this.source = z ? new ByteSource() {
            /* access modifiers changed from: protected */
            public void finalize() {
                try {
                    FileBackedOutputStream.this.reset();
                } catch (Throwable th) {
                    th.printStackTrace(System.err);
                }
            }

            public InputStream openStream() {
                return FileBackedOutputStream.this.openInputStream();
            }
        } : new ByteSource() {
            public InputStream openStream() {
                return FileBackedOutputStream.this.openInputStream();
            }
        };
    }

    /* access modifiers changed from: private */
    public synchronized InputStream openInputStream() {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        return new ByteArrayInputStream(this.memory.a(), 0, this.memory.b());
    }

    private void update(int i) {
        if (this.file == null && this.memory.b() + i > this.fileThreshold) {
            File createTempFile = File.createTempFile("FileBackedOutputStream", (String) null);
            if (this.resetOnFinalize) {
                createTempFile.deleteOnExit();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            fileOutputStream.write(this.memory.a(), 0, this.memory.b());
            fileOutputStream.flush();
            this.out = fileOutputStream;
            this.file = createTempFile;
            this.memory = null;
        }
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    public synchronized void close() {
        this.out.close();
    }

    public synchronized void flush() {
        this.out.flush();
    }

    public synchronized void reset() {
        try {
            close();
            if (this.memory == null) {
                this.memory = new MemoryOutput();
            } else {
                this.memory.reset();
            }
            this.out = this.memory;
            if (this.file != null) {
                File file2 = this.file;
                this.file = null;
                if (!file2.delete()) {
                    throw new IOException("Could not delete: " + file2);
                }
            }
        } catch (Throwable th) {
            if (this.memory == null) {
                this.memory = new MemoryOutput();
            } else {
                this.memory.reset();
            }
            this.out = this.memory;
            if (this.file != null) {
                File file3 = this.file;
                this.file = null;
                if (!file3.delete()) {
                    throw new IOException("Could not delete: " + file3);
                }
            }
            throw th;
        }
    }

    public synchronized void write(int i) {
        update(1);
        this.out.write(i);
    }

    public synchronized void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        update(i2);
        this.out.write(bArr, i, i2);
    }
}
