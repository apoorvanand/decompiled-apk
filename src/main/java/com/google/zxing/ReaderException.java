package com.google.zxing;

public abstract class ReaderException extends Exception {
    protected static final boolean a = (System.getProperty("surefire.test.class.path") != null);
    protected static final StackTraceElement[] b = new StackTraceElement[0];

    ReaderException() {
    }

    ReaderException(Throwable th) {
        super(th);
    }

    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
