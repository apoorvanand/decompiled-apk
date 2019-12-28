package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import java.util.concurrent.ConcurrentMap;

@GwtIncompatible
@Beta
public final class Interners {

    private static class InternerFunction<E> implements Function<E, E> {
        private final Interner<E> interner;

        public InternerFunction(Interner<E> interner2) {
            this.interner = interner2;
        }

        public E apply(E e) {
            return this.interner.intern(e);
        }

        public boolean equals(Object obj) {
            if (obj instanceof InternerFunction) {
                return this.interner.equals(((InternerFunction) obj).interner);
            }
            return false;
        }

        public int hashCode() {
            return this.interner.hashCode();
        }
    }

    private static class WeakInterner<E> implements Interner<E> {
        private final MapMakerInternalMap<E, Dummy, ?, ?> map;

        private enum Dummy {
            VALUE
        }

        private WeakInterner() {
            this.map = new MapMaker().weakKeys().a(Equivalence.equals()).f();
        }

        public E intern(E e) {
            E key;
            do {
                MapMakerInternalMap.InternalEntry b = this.map.b((Object) e);
                if (b != null && (key = b.getKey()) != null) {
                    return key;
                }
            } while (this.map.putIfAbsent(e, Dummy.VALUE) != null);
            return e;
        }
    }

    private Interners() {
    }

    public static <E> Function<E, E> asFunction(Interner<E> interner) {
        return new InternerFunction((Interner) Preconditions.checkNotNull(interner));
    }

    public static <E> Interner<E> newStrongInterner() {
        final ConcurrentMap makeMap = new MapMaker().makeMap();
        return new Interner<E>() {
            public E intern(E e) {
                E putIfAbsent = makeMap.putIfAbsent(Preconditions.checkNotNull(e), e);
                return putIfAbsent == null ? e : putIfAbsent;
            }
        };
    }

    @GwtIncompatible("java.lang.ref.WeakReference")
    public static <E> Interner<E> newWeakInterner() {
        return new WeakInterner();
    }
}