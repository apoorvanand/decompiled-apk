package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

@GwtIncompatible
final class FuturesGetChecked {
    private static final Ordering<Constructor<?>> WITH_STRING_PARAM_FIRST = Ordering.natural().onResultOf(new Function<Constructor<?>, Boolean>() {
        public Boolean apply(Constructor<?> constructor) {
            return Boolean.valueOf(Arrays.asList(constructor.getParameterTypes()).contains(String.class));
        }
    }).reverse();

    @VisibleForTesting
    interface GetCheckedTypeValidator {
        void validateClass(Class<? extends Exception> cls);
    }

    @VisibleForTesting
    static class GetCheckedTypeValidatorHolder {
        static final String a = (GetCheckedTypeValidatorHolder.class.getName() + "$ClassValueValidator");
        static final GetCheckedTypeValidator b = a();

        @IgnoreJRERequirement
        enum ClassValueValidator implements GetCheckedTypeValidator {
            INSTANCE;
            
            private static final ClassValue<Boolean> isValidClass = null;

            static {
                isValidClass = new ClassValue<Boolean>() {
                };
            }

            public void validateClass(Class<? extends Exception> cls) {
                isValidClass.get(cls);
            }
        }

        enum WeakSetValidator implements GetCheckedTypeValidator {
            INSTANCE;
            
            private static final Set<WeakReference<Class<? extends Exception>>> validClasses = null;

            static {
                validClasses = new CopyOnWriteArraySet();
            }

            public void validateClass(Class<? extends Exception> cls) {
                for (WeakReference<Class<? extends Exception>> weakReference : validClasses) {
                    if (cls.equals(weakReference.get())) {
                        return;
                    }
                }
                FuturesGetChecked.b(cls);
                if (validClasses.size() > 1000) {
                    validClasses.clear();
                }
                validClasses.add(new WeakReference(cls));
            }
        }

        GetCheckedTypeValidatorHolder() {
        }

        static GetCheckedTypeValidator a() {
            try {
                return (GetCheckedTypeValidator) Class.forName(a).getEnumConstants()[0];
            } catch (Throwable unused) {
                return FuturesGetChecked.a();
            }
        }
    }

    private FuturesGetChecked() {
    }

    @VisibleForTesting
    static GetCheckedTypeValidator a() {
        return GetCheckedTypeValidatorHolder.WeakSetValidator.INSTANCE;
    }

    @CanIgnoreReturnValue
    @VisibleForTesting
    static <V, X extends Exception> V a(GetCheckedTypeValidator getCheckedTypeValidator, Future<V> future, Class<X> cls) {
        getCheckedTypeValidator.validateClass(cls);
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(cls, e);
        } catch (ExecutionException e2) {
            wrapAndThrowExceptionOrError(e2.getCause(), cls);
            throw new AssertionError();
        }
    }

    @CanIgnoreReturnValue
    static <V, X extends Exception> V a(Future<V> future, Class<X> cls) {
        return a(bestGetCheckedTypeValidator(), future, cls);
    }

    @CanIgnoreReturnValue
    static <V, X extends Exception> V a(Future<V> future, Class<X> cls, long j, TimeUnit timeUnit) {
        bestGetCheckedTypeValidator().validateClass(cls);
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(cls, e);
        } catch (TimeoutException e2) {
            throw newWithCause(cls, e2);
        } catch (ExecutionException e3) {
            wrapAndThrowExceptionOrError(e3.getCause(), cls);
            throw new AssertionError();
        }
    }

    @VisibleForTesting
    static boolean a(Class<? extends Exception> cls) {
        return !RuntimeException.class.isAssignableFrom(cls);
    }

    @VisibleForTesting
    static void b(Class<? extends Exception> cls) {
        Preconditions.checkArgument(a(cls), "Futures.getChecked exception type (%s) must not be a RuntimeException", (Object) cls);
        Preconditions.checkArgument(hasConstructorUsableByGetChecked(cls), "Futures.getChecked exception type (%s) must be an accessible class with an accessible constructor whose parameters (if any) must be of type String and/or Throwable", (Object) cls);
    }

    private static GetCheckedTypeValidator bestGetCheckedTypeValidator() {
        return GetCheckedTypeValidatorHolder.b;
    }

    private static boolean hasConstructorUsableByGetChecked(Class<? extends Exception> cls) {
        try {
            newWithCause(cls, new Exception());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Nullable
    private static <X> X newFromConstructor(Constructor<X> constructor, Throwable th) {
        Class[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class cls = parameterTypes[i];
            if (cls.equals(String.class)) {
                objArr[i] = th.toString();
            } else if (!cls.equals(Throwable.class)) {
                return null;
            } else {
                objArr[i] = th;
            }
        }
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    private static <X extends Exception> X newWithCause(Class<X> cls, Throwable th) {
        for (Constructor newFromConstructor : preferringStrings(Arrays.asList(cls.getConstructors()))) {
            X x = (Exception) newFromConstructor(newFromConstructor, th);
            if (x != null) {
                if (x.getCause() == null) {
                    x.initCause(th);
                }
                return x;
            }
        }
        throw new IllegalArgumentException("No appropriate constructor for exception of type " + cls + " in response to chained exception", th);
    }

    private static <X extends Exception> List<Constructor<X>> preferringStrings(List<Constructor<X>> list) {
        return WITH_STRING_PARAM_FIRST.sortedCopy(list);
    }

    private static <X extends Exception> void wrapAndThrowExceptionOrError(Throwable th, Class<X> cls) {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        } else if (th instanceof RuntimeException) {
            throw new UncheckedExecutionException(th);
        } else {
            throw newWithCause(cls, th);
        }
    }
}
