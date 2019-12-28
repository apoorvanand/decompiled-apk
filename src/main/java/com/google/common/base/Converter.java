package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;
    @LazyInit
    private transient Converter<B, A> reverse;

    private static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> a;
        final Converter<B, C> b;

        ConverterComposition(Converter<A, B> converter, Converter<B, C> converter2) {
            this.a = converter;
            this.b = converter2;
        }

        /* access modifiers changed from: protected */
        public A a(C c) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        public C b(A a2) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public C c(@Nullable A a2) {
            return this.b.c(this.a.c(a2));
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public A d(@Nullable C c) {
            return this.a.d(this.b.d(c));
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof ConverterComposition)) {
                return false;
            }
            ConverterComposition converterComposition = (ConverterComposition) obj;
            return this.a.equals(converterComposition.a) && this.b.equals(converterComposition.b);
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }

        public String toString() {
            return this.a + ".andThen(" + this.b + ")";
        }
    }

    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        private final Function<? super B, ? extends A> backwardFunction;
        private final Function<? super A, ? extends B> forwardFunction;

        private FunctionBasedConverter(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
            this.forwardFunction = (Function) Preconditions.checkNotNull(function);
            this.backwardFunction = (Function) Preconditions.checkNotNull(function2);
        }

        /* access modifiers changed from: protected */
        public A a(B b) {
            return this.backwardFunction.apply(b);
        }

        /* access modifiers changed from: protected */
        public B b(A a) {
            return this.forwardFunction.apply(a);
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof FunctionBasedConverter)) {
                return false;
            }
            FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter) obj;
            return this.forwardFunction.equals(functionBasedConverter.forwardFunction) && this.backwardFunction.equals(functionBasedConverter.backwardFunction);
        }

        public int hashCode() {
            return (this.forwardFunction.hashCode() * 31) + this.backwardFunction.hashCode();
        }

        public String toString() {
            return "Converter.from(" + this.forwardFunction + ", " + this.backwardFunction + ")";
        }
    }

    private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
        static final IdentityConverter a = new IdentityConverter();
        private static final long serialVersionUID = 0;

        private IdentityConverter() {
        }

        private Object readResolve() {
            return a;
        }

        /* access modifiers changed from: package-private */
        public <S> Converter<T, S> a(Converter<T, S> converter) {
            return (Converter) Preconditions.checkNotNull(converter, "otherConverter");
        }

        /* access modifiers changed from: protected */
        public T a(T t) {
            return t;
        }

        /* access modifiers changed from: protected */
        public T b(T t) {
            return t;
        }

        public IdentityConverter<T> reverse() {
            return this;
        }

        public String toString() {
            return "Converter.identity()";
        }
    }

    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> a;

        ReverseConverter(Converter<A, B> converter) {
            this.a = converter;
        }

        /* access modifiers changed from: protected */
        public B a(A a2) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        public A b(B b) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public A c(@Nullable B b) {
            return this.a.d(b);
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public B d(@Nullable A a2) {
            return this.a.c(a2);
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof ReverseConverter) {
                return this.a.equals(((ReverseConverter) obj).a);
            }
            return false;
        }

        public int hashCode() {
            return ~this.a.hashCode();
        }

        public Converter<A, B> reverse() {
            return this.a;
        }

        public String toString() {
            return this.a + ".reverse()";
        }
    }

    protected Converter() {
        this(true);
    }

    Converter(boolean z) {
        this.handleNullAutomatically = z;
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
        return new FunctionBasedConverter(function, function2);
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.a;
    }

    /* access modifiers changed from: package-private */
    public <C> Converter<A, C> a(Converter<B, C> converter) {
        return new ConverterComposition(this, (Converter) Preconditions.checkNotNull(converter));
    }

    /* access modifiers changed from: protected */
    public abstract A a(B b);

    public final <C> Converter<A, C> andThen(Converter<B, C> converter) {
        return a(converter);
    }

    @CanIgnoreReturnValue
    @Deprecated
    @Nullable
    public final B apply(@Nullable A a) {
        return convert(a);
    }

    /* access modifiers changed from: protected */
    public abstract B b(A a);

    /* access modifiers changed from: package-private */
    @Nullable
    public B c(@Nullable A a) {
        if (!this.handleNullAutomatically) {
            return b(a);
        }
        if (a == null) {
            return null;
        }
        return Preconditions.checkNotNull(b(a));
    }

    @CanIgnoreReturnValue
    @Nullable
    public final B convert(@Nullable A a) {
        return c(a);
    }

    @CanIgnoreReturnValue
    public Iterable<B> convertAll(final Iterable<? extends A> iterable) {
        Preconditions.checkNotNull(iterable, "fromIterable");
        return new Iterable<B>() {
            public Iterator<B> iterator() {
                return new Iterator<B>() {
                    private final Iterator<? extends A> fromIterator = iterable.iterator();

                    public boolean hasNext() {
                        return this.fromIterator.hasNext();
                    }

                    public B next() {
                        return Converter.this.convert(this.fromIterator.next());
                    }

                    public void remove() {
                        this.fromIterator.remove();
                    }
                };
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public A d(@Nullable B b) {
        if (!this.handleNullAutomatically) {
            return a(b);
        }
        if (b == null) {
            return null;
        }
        return Preconditions.checkNotNull(a(b));
    }

    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @CanIgnoreReturnValue
    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter != null) {
            return converter;
        }
        ReverseConverter reverseConverter = new ReverseConverter(this);
        this.reverse = reverseConverter;
        return reverseConverter;
    }
}
