package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

@GwtIncompatible
@Beta
public final class Closer implements Closeable {
    private static final Suppressor SUPPRESSOR = (SuppressingSuppressor.a() ? SuppressingSuppressor.a : LoggingSuppressor.a);
    @VisibleForTesting
    final Suppressor a;
    private final Deque<Closeable> stack = new ArrayDeque(4);
    private Throwable thrown;

    @VisibleForTesting
    static final class LoggingSuppressor implements Suppressor {
        static final LoggingSuppressor a = new LoggingSuppressor();

        LoggingSuppressor() {
        }

        public void suppress(Closeable closeable, Throwable th, Throwable th2) {
            Logger logger = Closeables.a;
            Level level = Level.WARNING;
            logger.log(level, "Suppressing exception thrown when closing " + closeable, th2);
        }
    }

    @VisibleForTesting
    static final class SuppressingSuppressor implements Suppressor {
        static final SuppressingSuppressor a = new SuppressingSuppressor();
        static final Method b = getAddSuppressed();

        SuppressingSuppressor() {
        }

        static boolean a() {
            return b != null;
        }

        private static Method getAddSuppressed() {
            try {
                return Throwable.class.getMethod("addSuppressed", new Class[]{Throwable.class});
            } catch (Throwable unused) {
                return null;
            }
        }

        public void suppress(Closeable closeable, Throwable th, Throwable th2) {
            if (th != th2) {
                try {
                    b.invoke(th, new Object[]{th2});
                } catch (Throwable unused) {
                    LoggingSuppressor.a.suppress(closeable, th, th2);
                }
            }
        }
    }

    @VisibleForTesting
    interface Suppressor {
        void suppress(Closeable closeable, Throwable th, Throwable th2);
    }

    @VisibleForTesting
    Closer(Suppressor suppressor) {
        this.a = (Suppressor) Preconditions.checkNotNull(suppressor);
    }

    public static Closer create() {
        return new Closer(SUPPRESSOR);
    }

    public void close() {
        Throwable th = this.thrown;
        while (!this.stack.isEmpty()) {
            Closeable removeFirst = this.stack.removeFirst();
            try {
                removeFirst.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    this.a.suppress(removeFirst, th, th2);
                }
            }
        }
        if (this.thrown == null && th != null) {
            Throwables.propagateIfPossible(th, IOException.class);
            throw new AssertionError(th);
        }
    }

    @CanIgnoreReturnValue
    public <C extends Closeable> C register(@Nullable C c) {
        if (c != null) {
            this.stack.addFirst(c);
        }
        return c;
    }

    public RuntimeException rethrow(Throwable th) {
        Preconditions.checkNotNull(th);
        this.thrown = th;
        Throwables.propagateIfPossible(th, IOException.class);
        throw new RuntimeException(th);
    }

    public <X extends Exception> RuntimeException rethrow(Throwable th, Class<X> cls) {
        Preconditions.checkNotNull(th);
        this.thrown = th;
        Throwables.propagateIfPossible(th, IOException.class);
        Throwables.propagateIfPossible(th, cls);
        throw new RuntimeException(th);
    }

    public <X1 extends Exception, X2 extends Exception> RuntimeException rethrow(Throwable th, Class<X1> cls, Class<X2> cls2) {
        Preconditions.checkNotNull(th);
        this.thrown = th;
        Throwables.propagateIfPossible(th, IOException.class);
        Throwables.propagateIfPossible(th, cls, cls2);
        throw new RuntimeException(th);
    }
}
