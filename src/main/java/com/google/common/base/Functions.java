package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible
public final class Functions {

    private static class ConstantFunction<E> implements Function<Object, E>, Serializable {
        private static final long serialVersionUID = 0;
        private final E value;

        public ConstantFunction(@Nullable E e) {
            this.value = e;
        }

        public E apply(@Nullable Object obj) {
            return this.value;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof ConstantFunction) {
                return Objects.equal(this.value, ((ConstantFunction) obj).value);
            }
            return false;
        }

        public int hashCode() {
            E e = this.value;
            if (e == null) {
                return 0;
            }
            return e.hashCode();
        }

        public String toString() {
            return "Functions.constant(" + this.value + ")";
        }
    }

    private static class ForMapWithDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final Map<K, ? extends V> a;
        final V b;

        ForMapWithDefault(Map<K, ? extends V> map, @Nullable V v) {
            this.a = (Map) Preconditions.checkNotNull(map);
            this.b = v;
        }

        public V apply(@Nullable K k) {
            V v = this.a.get(k);
            return (v != null || this.a.containsKey(k)) ? v : this.b;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof ForMapWithDefault)) {
                return false;
            }
            ForMapWithDefault forMapWithDefault = (ForMapWithDefault) obj;
            return this.a.equals(forMapWithDefault.a) && Objects.equal(this.b, forMapWithDefault.b);
        }

        public int hashCode() {
            return Objects.hashCode(this.a, this.b);
        }

        public String toString() {
            return "Functions.forMap(" + this.a + ", defaultValue=" + this.b + ")";
        }
    }

    private static class FunctionComposition<A, B, C> implements Function<A, C>, Serializable {
        private static final long serialVersionUID = 0;
        private final Function<A, ? extends B> f;
        private final Function<B, C> g;

        public FunctionComposition(Function<B, C> function, Function<A, ? extends B> function2) {
            this.g = (Function) Preconditions.checkNotNull(function);
            this.f = (Function) Preconditions.checkNotNull(function2);
        }

        public C apply(@Nullable A a) {
            return this.g.apply(this.f.apply(a));
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof FunctionComposition)) {
                return false;
            }
            FunctionComposition functionComposition = (FunctionComposition) obj;
            return this.f.equals(functionComposition.f) && this.g.equals(functionComposition.g);
        }

        public int hashCode() {
            return this.f.hashCode() ^ this.g.hashCode();
        }

        public String toString() {
            return this.g + "(" + this.f + ")";
        }
    }

    private static class FunctionForMapNoDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final Map<K, V> a;

        FunctionForMapNoDefault(Map<K, V> map) {
            this.a = (Map) Preconditions.checkNotNull(map);
        }

        public V apply(@Nullable K k) {
            V v = this.a.get(k);
            Preconditions.checkArgument(v != null || this.a.containsKey(k), "Key '%s' not present in map", (Object) k);
            return v;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof FunctionForMapNoDefault) {
                return this.a.equals(((FunctionForMapNoDefault) obj).a);
            }
            return false;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return "Functions.forMap(" + this.a + ")";
        }
    }

    private enum IdentityFunction implements Function<Object, Object> {
        INSTANCE;

        @Nullable
        public Object apply(@Nullable Object obj) {
            return obj;
        }

        public String toString() {
            return "Functions.identity()";
        }
    }

    private static class PredicateFunction<T> implements Function<T, Boolean>, Serializable {
        private static final long serialVersionUID = 0;
        private final Predicate<T> predicate;

        private PredicateFunction(Predicate<T> predicate2) {
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate2);
        }

        public Boolean apply(@Nullable T t) {
            return Boolean.valueOf(this.predicate.apply(t));
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof PredicateFunction) {
                return this.predicate.equals(((PredicateFunction) obj).predicate);
            }
            return false;
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        public String toString() {
            return "Functions.forPredicate(" + this.predicate + ")";
        }
    }

    private static class SupplierFunction<T> implements Function<Object, T>, Serializable {
        private static final long serialVersionUID = 0;
        private final Supplier<T> supplier;

        private SupplierFunction(Supplier<T> supplier2) {
            this.supplier = (Supplier) Preconditions.checkNotNull(supplier2);
        }

        public T apply(@Nullable Object obj) {
            return this.supplier.get();
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof SupplierFunction) {
                return this.supplier.equals(((SupplierFunction) obj).supplier);
            }
            return false;
        }

        public int hashCode() {
            return this.supplier.hashCode();
        }

        public String toString() {
            return "Functions.forSupplier(" + this.supplier + ")";
        }
    }

    private enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        public String apply(Object obj) {
            Preconditions.checkNotNull(obj);
            return obj.toString();
        }

        public String toString() {
            return "Functions.toStringFunction()";
        }
    }

    private Functions() {
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> function, Function<A, ? extends B> function2) {
        return new FunctionComposition(function, function2);
    }

    public static <E> Function<Object, E> constant(@Nullable E e) {
        return new ConstantFunction(e);
    }

    public static <K, V> Function<K, V> forMap(Map<K, V> map) {
        return new FunctionForMapNoDefault(map);
    }

    public static <K, V> Function<K, V> forMap(Map<K, ? extends V> map, @Nullable V v) {
        return new ForMapWithDefault(map, v);
    }

    public static <T> Function<T, Boolean> forPredicate(Predicate<T> predicate) {
        return new PredicateFunction(predicate);
    }

    public static <T> Function<Object, T> forSupplier(Supplier<T> supplier) {
        return new SupplierFunction(supplier);
    }

    public static <E> Function<E, E> identity() {
        return IdentityFunction.INSTANCE;
    }

    public static Function<Object, String> toStringFunction() {
        return ToStringFunction.INSTANCE;
    }
}
