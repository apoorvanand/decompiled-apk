package com.google.zxing;

public final class FormatException extends ReaderException {
    private static final FormatException INSTANCE;

    static {
        FormatException formatException = new FormatException();
        INSTANCE = formatException;
        formatException.setStackTrace(b);
    }

    private FormatException() {
    }

    private FormatException(Throwable th) {
        super(th);
    }

    public static FormatException getFormatInstance() {
        return a ? new FormatException() : INSTANCE;
    }

    public static FormatException getFormatInstance(Throwable th) {
        return a ? new FormatException(th) : INSTANCE;
    }
}
