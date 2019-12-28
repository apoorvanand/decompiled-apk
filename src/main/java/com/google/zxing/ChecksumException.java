package com.google.zxing;

public final class ChecksumException extends ReaderException {
    private static final ChecksumException INSTANCE;

    static {
        ChecksumException checksumException = new ChecksumException();
        INSTANCE = checksumException;
        checksumException.setStackTrace(b);
    }

    private ChecksumException() {
    }

    private ChecksumException(Throwable th) {
        super(th);
    }

    public static ChecksumException getChecksumInstance() {
        return a ? new ChecksumException() : INSTANCE;
    }

    public static ChecksumException getChecksumInstance(Throwable th) {
        return a ? new ChecksumException(th) : INSTANCE;
    }
}
