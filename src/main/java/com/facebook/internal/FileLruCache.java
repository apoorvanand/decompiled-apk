package com.facebook.internal;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class FileLruCache {
    private static final String HEADER_CACHEKEY_KEY = "key";
    private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
    static final String a = "FileLruCache";
    /* access modifiers changed from: private */
    public static final AtomicLong bufferIndex = new AtomicLong();
    private final File directory;
    private boolean isTrimInProgress;
    private boolean isTrimPending;
    /* access modifiers changed from: private */
    public AtomicLong lastClearCacheTime = new AtomicLong(0);
    private final Limits limits;
    private final Object lock;
    private final String tag;

    private static class BufferFile {
        private static final String FILE_NAME_PREFIX = "buffer";
        private static final FilenameFilter filterExcludeBufferFiles = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return !str.startsWith(BufferFile.FILE_NAME_PREFIX);
            }
        };
        private static final FilenameFilter filterExcludeNonBufferFiles = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith(BufferFile.FILE_NAME_PREFIX);
            }
        };

        private BufferFile() {
        }

        static FilenameFilter a() {
            return filterExcludeBufferFiles;
        }

        static void a(File file) {
            File[] listFiles = file.listFiles(b());
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        static File b(File file) {
            return new File(file, FILE_NAME_PREFIX + Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString());
        }

        static FilenameFilter b() {
            return filterExcludeNonBufferFiles;
        }
    }

    private static class CloseCallbackOutputStream extends OutputStream {
        final OutputStream a;
        final StreamCloseCallback b;

        CloseCallbackOutputStream(OutputStream outputStream, StreamCloseCallback streamCloseCallback) {
            this.a = outputStream;
            this.b = streamCloseCallback;
        }

        public void close() {
            try {
                this.a.close();
            } finally {
                this.b.onClose();
            }
        }

        public void flush() {
            this.a.flush();
        }

        public void write(int i) {
            this.a.write(i);
        }

        public void write(byte[] bArr) {
            this.a.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) {
            this.a.write(bArr, i, i2);
        }
    }

    private static final class CopyingInputStream extends InputStream {
        final InputStream a;
        final OutputStream b;

        CopyingInputStream(InputStream inputStream, OutputStream outputStream) {
            this.a = inputStream;
            this.b = outputStream;
        }

        public int available() {
            return this.a.available();
        }

        public void close() {
            try {
                this.a.close();
            } finally {
                this.b.close();
            }
        }

        public void mark(int i) {
            throw new UnsupportedOperationException();
        }

        public boolean markSupported() {
            return false;
        }

        public int read() {
            int read = this.a.read();
            if (read >= 0) {
                this.b.write(read);
            }
            return read;
        }

        public int read(byte[] bArr) {
            int read = this.a.read(bArr);
            if (read > 0) {
                this.b.write(bArr, 0, read);
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) {
            int read = this.a.read(bArr, i, i2);
            if (read > 0) {
                this.b.write(bArr, i, read);
            }
            return read;
        }

        public synchronized void reset() {
            throw new UnsupportedOperationException();
        }

        public long skip(long j) {
            int read;
            byte[] bArr = new byte[1024];
            long j2 = 0;
            while (j2 < j && (read = read(bArr, 0, (int) Math.min(j - j2, (long) bArr.length))) >= 0) {
                j2 += (long) read;
            }
            return j2;
        }
    }

    public static final class Limits {
        private int byteCount = 1048576;
        private int fileCount = 1024;

        /* access modifiers changed from: package-private */
        public int a() {
            return this.byteCount;
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return this.fileCount;
        }
    }

    private static final class ModifiedFile implements Comparable<ModifiedFile> {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        private final File file;
        private final long modified;

        ModifiedFile(File file2) {
            this.file = file2;
            this.modified = file2.lastModified();
        }

        /* access modifiers changed from: package-private */
        public File a() {
            return this.file;
        }

        /* access modifiers changed from: package-private */
        public long b() {
            return this.modified;
        }

        public int compareTo(ModifiedFile modifiedFile) {
            if (b() < modifiedFile.b()) {
                return -1;
            }
            if (b() > modifiedFile.b()) {
                return 1;
            }
            return a().compareTo(modifiedFile.a());
        }

        public boolean equals(Object obj) {
            return (obj instanceof ModifiedFile) && compareTo((ModifiedFile) obj) == 0;
        }

        public int hashCode() {
            return ((1073 + this.file.hashCode()) * 37) + ((int) (this.modified % 2147483647L));
        }
    }

    private interface StreamCloseCallback {
        void onClose();
    }

    private static final class StreamHeader {
        private static final int HEADER_VERSION = 0;

        private StreamHeader() {
        }

        static JSONObject a(InputStream inputStream) {
            if (inputStream.read() != 0) {
                return null;
            }
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < 3; i3++) {
                int read = inputStream.read();
                if (read == -1) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.a, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                i2 = (i2 << 8) + (read & 255);
            }
            byte[] bArr = new byte[i2];
            while (i < bArr.length) {
                int read2 = inputStream.read(bArr, i, bArr.length - i);
                if (read2 < 1) {
                    LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                    String str = FileLruCache.a;
                    Logger.log(loggingBehavior, str, "readHeader: stream.read stopped at " + Integer.valueOf(i) + " when expected " + bArr.length);
                    return null;
                }
                i += read2;
            }
            try {
                Object nextValue = new JSONTokener(new String(bArr)).nextValue();
                if (nextValue instanceof JSONObject) {
                    return (JSONObject) nextValue;
                }
                LoggingBehavior loggingBehavior2 = LoggingBehavior.CACHE;
                String str2 = FileLruCache.a;
                Logger.log(loggingBehavior2, str2, "readHeader: expected JSONObject, got " + nextValue.getClass().getCanonicalName());
                return null;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }

        static void a(OutputStream outputStream, JSONObject jSONObject) {
            byte[] bytes = jSONObject.toString().getBytes();
            outputStream.write(0);
            outputStream.write((bytes.length >> 16) & 255);
            outputStream.write((bytes.length >> 8) & 255);
            outputStream.write((bytes.length >> 0) & 255);
            outputStream.write(bytes);
        }
    }

    public FileLruCache(String str, Limits limits2) {
        this.tag = str;
        this.limits = limits2;
        this.directory = new File(FacebookSdk.getCacheDir(), str);
        this.lock = new Object();
        if (this.directory.mkdirs() || this.directory.isDirectory()) {
            BufferFile.a(this.directory);
        }
    }

    private void postTrim() {
        synchronized (this.lock) {
            if (!this.isTrimPending) {
                this.isTrimPending = true;
                FacebookSdk.getExecutor().execute(new Runnable() {
                    public void run() {
                        FileLruCache.this.trim();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void renameToTargetAndTrim(String str, File file) {
        if (!file.renameTo(new File(this.directory, Utility.md5hash(str)))) {
            file.delete();
        }
        postTrim();
    }

    /* access modifiers changed from: private */
    public void trim() {
        long j;
        synchronized (this.lock) {
            this.isTrimPending = false;
            this.isTrimInProgress = true;
        }
        try {
            Logger.log(LoggingBehavior.CACHE, a, "trim started");
            PriorityQueue priorityQueue = new PriorityQueue();
            File[] listFiles = this.directory.listFiles(BufferFile.a());
            long j2 = 0;
            if (listFiles != null) {
                long j3 = 0;
                j = 0;
                for (File file : listFiles) {
                    ModifiedFile modifiedFile = new ModifiedFile(file);
                    priorityQueue.add(modifiedFile);
                    Logger.log(LoggingBehavior.CACHE, a, "  trim considering time=" + Long.valueOf(modifiedFile.b()) + " name=" + modifiedFile.a().getName());
                    j3 += file.length();
                    j++;
                }
                j2 = j3;
            } else {
                j = 0;
            }
            while (true) {
                if (j2 > ((long) this.limits.a()) || j > ((long) this.limits.b())) {
                    File a2 = ((ModifiedFile) priorityQueue.remove()).a();
                    Logger.log(LoggingBehavior.CACHE, a, "  trim removing " + a2.getName());
                    j2 -= a2.length();
                    j--;
                    a2.delete();
                } else {
                    synchronized (this.lock) {
                        this.isTrimInProgress = false;
                        this.lock.notifyAll();
                    }
                    return;
                }
            }
        } catch (Throwable th) {
            synchronized (this.lock) {
                this.isTrimInProgress = false;
                this.lock.notifyAll();
                throw th;
            }
        }
    }

    public void clearCache() {
        final File[] listFiles = this.directory.listFiles(BufferFile.a());
        this.lastClearCacheTime.set(System.currentTimeMillis());
        if (listFiles != null) {
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
            });
        }
    }

    public InputStream get(String str) {
        return get(str, (String) null);
    }

    public InputStream get(String str, String str2) {
        File file = new File(this.directory, Utility.md5hash(str));
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 8192);
            try {
                JSONObject a2 = StreamHeader.a(bufferedInputStream);
                if (a2 == null) {
                    return null;
                }
                String optString = a2.optString(HEADER_CACHEKEY_KEY);
                if (optString != null) {
                    if (optString.equals(str)) {
                        String optString2 = a2.optString(HEADER_CACHE_CONTENT_TAG_KEY, (String) null);
                        if ((str2 != null || optString2 == null) && (str2 == null || str2.equals(optString2))) {
                            long time = new Date().getTime();
                            LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                            String str3 = a;
                            Logger.log(loggingBehavior, str3, "Setting lastModified to " + Long.valueOf(time) + " for " + file.getName());
                            file.setLastModified(time);
                            return bufferedInputStream;
                        }
                        bufferedInputStream.close();
                        return null;
                    }
                }
                bufferedInputStream.close();
                return null;
            } finally {
                bufferedInputStream.close();
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public String getLocation() {
        return this.directory.getPath();
    }

    public InputStream interceptAndPut(String str, InputStream inputStream) {
        return new CopyingInputStream(inputStream, openPutStream(str));
    }

    public OutputStream openPutStream(String str) {
        return openPutStream(str, (String) null);
    }

    public OutputStream openPutStream(String str, String str2) {
        final File b = BufferFile.b(this.directory);
        b.delete();
        if (b.createNewFile()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(b);
                final long currentTimeMillis = System.currentTimeMillis();
                final String str3 = str;
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new CloseCallbackOutputStream(fileOutputStream, new StreamCloseCallback() {
                    public void onClose() {
                        if (currentTimeMillis < FileLruCache.this.lastClearCacheTime.get()) {
                            b.delete();
                        } else {
                            FileLruCache.this.renameToTargetAndTrim(str3, b);
                        }
                    }
                }), 8192);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(HEADER_CACHEKEY_KEY, str);
                    if (!Utility.isNullOrEmpty(str2)) {
                        jSONObject.put(HEADER_CACHE_CONTENT_TAG_KEY, str2);
                    }
                    StreamHeader.a(bufferedOutputStream, jSONObject);
                    return bufferedOutputStream;
                } catch (JSONException e) {
                    LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                    String str4 = a;
                    Logger.log(loggingBehavior, 5, str4, "Error creating JSON header for cache file: " + e);
                    throw new IOException(e.getMessage());
                } catch (Throwable th) {
                    bufferedOutputStream.close();
                    throw th;
                }
            } catch (FileNotFoundException e2) {
                LoggingBehavior loggingBehavior2 = LoggingBehavior.CACHE;
                String str5 = a;
                Logger.log(loggingBehavior2, 5, str5, "Error creating buffer output stream: " + e2);
                throw new IOException(e2.getMessage());
            }
        } else {
            throw new IOException("Could not create file at " + b.getAbsolutePath());
        }
    }

    public String toString() {
        return "{FileLruCache: tag:" + this.tag + " file:" + this.directory.getName() + "}";
    }
}
