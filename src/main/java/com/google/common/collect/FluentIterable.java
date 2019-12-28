package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public abstract class FluentIterable<E> implements Iterable<E> {
    private final Optional<Iterable<E>> iterableDelegate;

    private static class FromIterableFunction<E> implements Function<Iterable<E>, FluentIterable<E>> {
        private FromIterableFunction() {
        }

        public FluentIterable<E> apply(Iterable<E> iterable) {
            return FluentIterable.from(iterable);
        }
    }

    protected FluentIterable() {
        this.iterableDelegate = Optional.absent();
    }

    FluentIterable(Iterable<E> iterable) {
        Preconditions.checkNotNull(iterable);
        this.iterableDelegate = Optional.fromNullable(this == iterable ? null : iterable);
    }

    @Beta
    public static <T> FluentIterable<T> concat(final Iterable<? extends Iterable<? extends T>> iterable) {
        Preconditions.checkNotNull(iterable);
        return new FluentIterable<T>() {
            public Iterator<T> iterator() {
                return Iterators.concat(Iterables.transform(iterable, Iterables.a()).iterator());
            }
        };
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2) {
        return concat(ImmutableList.of(iterable, iterable2));
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3) {
        return concat(ImmutableList.of(iterable, iterable2, iterable3));
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3, Iterable<? extends T> iterable4) {
        return concat(ImmutableList.of(iterable, iterable2, iterable3, iterable4));
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T>... iterableArr) {
        return concat(ImmutableList.copyOf((E[]) iterableArr));
    }

    @Deprecated
    public static <E> FluentIterable<E> from(FluentIterable<E> fluentIterable) {
        return (FluentIterable) Preconditions.checkNotNull(fluentIterable);
    }

    public static <E> FluentIterable<E> from(final Iterable<E> iterable) {
        return iterable instanceof FluentIterable ? (FluentIterable) iterable : new FluentIterable<E>(iterable) {
            public Iterator<E> iterator() {
                return iterable.iterator();
            }
        };
    }

    @Beta
    public static <E> FluentIterable<E> from(E[] eArr) {
        return from(Arrays.asList(eArr));
    }

    private Iterable<E> getDelegate() {
        return this.iterableDelegate.or(this);
    }

    @Beta
    public static <E> FluentIterable<E> of() {
        return from(ImmutableList.of());
    }

    @Beta
    public static <E> FluentIterable<E> of(@Nullable E e, E... eArr) {
        return from(Lists.asList(e, eArr));
    }

    @Deprecated
    @Beta
    public static <E> FluentIterable<E> of(E[] eArr) {
        return from(Lists.newArrayList(eArr));
    }

    public final boolean allMatch(Predicate<? super E> predicate) {
        return Iterables.all(getDelegate(), predicate);
    }

    public final boolean anyMatch(Predicate<? super E> predicate) {
        return Iterables.any(getDelegate(), predicate);
    }

    @Beta
    public final FluentIterable<E> append(Iterable<? extends E> iterable) {
        return from(concat(getDelegate(), iterable));
    }

    @Beta
    public final FluentIterable<E> append(E... eArr) {
        return from(concat(getDelegate(), Arrays.asList(eArr)));
    }

    public final boolean contains(@Nullable Object obj) {
        return Iterables.contains(getDelegate(), obj);
    }

    @CanIgnoreReturnValue
    public final <C extends Collection<? super E>> C copyInto(C c) {
        Preconditions.checkNotNull(c);
        Iterable<Object> delegate = getDelegate();
        if (delegate instanceof Collection) {
            c.addAll(Collections2.a(delegate));
        } else {
            for (Object add : delegate) {
                c.add(add);
            }
        }
        return c;
    }

    public final FluentIterable<E> cycle() {
        return from(Iterables.cycle(getDelegate()));
    }

    public final FluentIterable<E> filter(Predicate<? super E> predicate) {
        return from(Iterables.filter(getDelegate(), predicate));
    }

    @GwtIncompatible
    public final <T> FluentIterable<T> filter(Class<T> cls) {
        return from(Iterables.filter((Iterable<?>) getDelegate(), cls));
    }

    public final Optional<E> first() {
        Iterator it = getDelegate().iterator();
        return it.hasNext() ? Optional.of(it.next()) : Optional.absent();
    }

    public final Optional<E> firstMatch(Predicate<? super E> predicate) {
        return Iterables.tryFind(getDelegate(), predicate);
    }

    public final E get(int i) {
        return Iterables.get(getDelegate(), i);
    }

    public final <K> ImmutableListMultimap<K, E> index(Function<? super E, K> function) {
        return Multimaps.index(getDelegate(), function);
    }

    public final boolean isEmpty() {
        return !getDelegate().iterator().hasNext();
    }

    @Beta
    public final String join(Joiner joiner) {
        return joiner.join((Iterable<?>) this);
    }

    public final Optional<E> last() {
        Object next;
        Object last;
        Iterable delegate = getDelegate();
        if (delegate instanceof List) {
            List list = (List) delegate;
            if (list.isEmpty()) {
                return Optional.absent();
            }
            last = list.get(list.size() - 1);
        } else {
            Iterator it = delegate.iterator();
            if (!it.hasNext()) {
                return Optional.absent();
            }
            if (delegate instanceof SortedSet) {
                last = ((SortedSet) delegate).last();
            } else {
                do {
                    next = it.next();
                } while (it.hasNext());
                return Optional.of(next);
            }
        }
        return Optional.of(last);
    }

    public final FluentIterable<E> limit(int i) {
        return from(Iterables.limit(getDelegate(), i));
    }

    public final int size() {
        return Iterables.size(getDelegate());
    }

    public final FluentIterable<E> skip(int i) {
        return from(Iterables.skip(getDelegate(), i));
    }

    @GwtIncompatible
    public final E[] toArray(Class<E> cls) {
        return Iterables.toArray(getDelegate(), cls);
    }

    public final ImmutableList<E> toList() {
        return ImmutableList.copyOf(getDelegate());
    }

    public final <V> ImmutableMap<E, V> toMap(Function<? super E, V> function) {
        return Maps.toMap(getDelegate(), function);
    }

    public final ImmutableMultiset<E> toMultiset() {
        return ImmutableMultiset.copyOf(getDelegate());
    }

    public final ImmutableSet<E> toSet() {
        return ImmutableSet.copyOf(getDelegate());
    }

    public final ImmutableList<E> toSortedList(Comparator<? super E> comparator) {
        return Ordering.from(comparator).immutableSortedCopy(getDelegate());
    }

    public final ImmutableSortedSet<E> toSortedSet(Comparator<? super E> comparator) {
        return ImmutableSortedSet.copyOf(comparator, getDelegate());
    }

    public String toString() {
        return Iterables.toString(getDelegate());
    }

    public final <T> FluentIterable<T> transform(Function<? super E, T> function) {
        return from(Iterables.transform(getDelegate(), function));
    }

    public <T> FluentIterable<T> transformAndConcat(Function<? super E, ? extends Iterable<? extends T>> function) {
        return from(concat(transform(function)));
    }

    public final <K> ImmutableMap<K, E> uniqueIndex(Function<? super E, K> function) {
        return Maps.uniqueIndex(getDelegate(), function);
    }
}
