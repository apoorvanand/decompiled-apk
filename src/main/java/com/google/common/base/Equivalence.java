package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class Equivalence<T> {

    static final class Equals extends Equivalence<Object> implements Serializable {
        static final Equals a = new Equals();
        private static final long serialVersionUID = 1;

        Equals() {
        }

        private Object readResolve() {
            return a;
        }

        /* access modifiers changed from: protected */
        public int a(Object obj) {
            return obj.hashCode();
        }

        /* access modifiers changed from: protected */
        public boolean a(Object obj, Object obj2) {
            return obj.equals(obj2);
        }
    }

    private static final class EquivalentToPredicate<T> implements Predicate<T>, Serializable {
        private static final long serialVersionUID = 0;
        private final Equivalence<T> equivalence;
        @Nullable
        private final T target;

        EquivalentToPredicate(Equivalence<T> equivalence2, @Nullable T t) {
            this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence2);
            this.target = t;
        }

        public boolean apply(@Nullable T t) {
            return this.equivalence.equivalent(t, this.target);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EquivalentToPredicate)) {
                return false;
            }
            EquivalentToPredicate equivalentToPredicate = (EquivalentToPredicate) obj;
            return this.equivalence.equals(equivalentToPredicate.equivalence) && Objects.equal(this.target, equivalentToPredicate.target);
        }

        public int hashCode() {
            return Objects.hashCode(this.equivalence, this.target);
        }

        public String toString() {
            return this.equivalence + ".equivalentTo(" + this.target + ")";
        }
    }

    static final class Identity extends Equivalence<Object> implements Serializable {
        static final Identity a = new Identity();
        private static final long serialVersionUID = 1;

        Identity() {
        }

        private Object readResolve() {
            return a;
        }

        /* access modifiers changed from: protected */
        public int a(Object obj) {
            return System.identityHashCode(obj);
        }

        /* access modifiers changed from: protected */
        public boolean a(Object obj, Object obj2) {
            return false;
        }
    }

    public static final class Wrapper<T> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Equivalence<? super T> equivalence;
        @Nullable
        private final T reference;

        private Wrapper(Equivalence<? super T> equivalence2, @Nullable T t) {
            this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence2);
            this.reference = t;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Wrapper)) {
                return false;
            }
            Wrapper wrapper = (Wrapper) obj;
            if (this.equivalence.equals(wrapper.equivalence)) {
                return this.equivalence.equivalent(this.reference, wrapper.reference);
            }
            return false;
        }

        @Nullable
        public T get() {
            return this.reference;
        }

        public int hashCode() {
            return this.equivalence.hash(this.reference);
        }

        public String toString() {
            return this.equivalence + ".wrap(" + this.reference + ")";
        }
    }

    protected Equivalence() {
    }

    public static Equivalence<Object> equals() {
        return Equals.a;
    }

    public static Equivalence<Object> identity() {
        return Identity.a;
    }

    /* access modifiers changed from: protected */
    public abstract int a(T t);

    /* access modifiers changed from: protected */
    public abstract boolean a(T t, T t2);

    public final boolean equivalent(@Nullable T t, @Nullable T t2) {
        if (t == t2) {
            return true;
        }
        if (t == null || t2 == null) {
            return false;
        }
        return a(t, t2);
    }

    public final Predicate<T> equivalentTo(@Nullable T t) {
        return new EquivalentToPredicate(this, t);
    }

    public final int hash(@Nullable T t) {
        if (t == null) {
            return 0;
        }
        return a(t);
    }

    public final <F> Equivalence<F> onResultOf(Function<F, ? extends T> function) {
        return new FunctionalEquivalence(function, this);
    }

    @GwtCompatible(serializable = true)
    public final <S extends T> Equivalence<Iterable<S>> pairwise() {
        return new PairwiseEquivalence(this);
    }

    public final <S extends T> Wrapper<S> wrap(@Nullable S s) {
        return new Wrapper<>(s);
    }
}
