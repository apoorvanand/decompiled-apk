package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Collection;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    static final RegularImmutableMultiset<Object> a = new RegularImmutableMultiset<>(ImmutableList.of());
    @LazyInit
    private transient ImmutableSet<E> elementSet;
    /* access modifiers changed from: private */
    public final transient Multisets.ImmutableEntry<E>[] entries;
    private final transient int hashCode;
    private final transient Multisets.ImmutableEntry<E>[] hashTable;
    private final transient int size;

    private final class ElementSet extends ImmutableSet.Indexed<E> {
        private ElementSet() {
        }

        /* access modifiers changed from: package-private */
        public E a(int i) {
            return RegularImmutableMultiset.this.entries[i].getElement();
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public boolean contains(@Nullable Object obj) {
            return RegularImmutableMultiset.this.contains(obj);
        }

        public int size() {
            return RegularImmutableMultiset.this.entries.length;
        }
    }

    private static final class NonTerminalEntry<E> extends Multisets.ImmutableEntry<E> {
        private final Multisets.ImmutableEntry<E> nextInBucket;

        NonTerminalEntry(E e, int i, Multisets.ImmutableEntry<E> immutableEntry) {
            super(e, i);
            this.nextInBucket = immutableEntry;
        }

        public Multisets.ImmutableEntry<E> nextInBucket() {
            return this.nextInBucket;
        }
    }

    RegularImmutableMultiset(Collection<? extends Multiset.Entry<? extends E>> collection) {
        Multisets.ImmutableEntry<E> immutableEntry;
        int size2 = collection.size();
        Multisets.ImmutableEntry<E>[] immutableEntryArr = new Multisets.ImmutableEntry[size2];
        if (size2 == 0) {
            this.entries = immutableEntryArr;
            this.hashTable = null;
            this.size = 0;
            this.hashCode = 0;
            this.elementSet = ImmutableSet.of();
            return;
        }
        int a2 = Hashing.a(size2, 1.0d);
        int i = a2 - 1;
        Multisets.ImmutableEntry<E>[] immutableEntryArr2 = new Multisets.ImmutableEntry[a2];
        long j = 0;
        int i2 = 0;
        int i3 = 0;
        for (Multiset.Entry entry : collection) {
            Object checkNotNull = Preconditions.checkNotNull(entry.getElement());
            int count = entry.getCount();
            int hashCode2 = checkNotNull.hashCode();
            int a3 = Hashing.a(hashCode2) & i;
            Multisets.ImmutableEntry<E> immutableEntry2 = immutableEntryArr2[a3];
            if (immutableEntry2 == null) {
                immutableEntry = (entry instanceof Multisets.ImmutableEntry) && !(entry instanceof NonTerminalEntry) ? (Multisets.ImmutableEntry) entry : new Multisets.ImmutableEntry<>(checkNotNull, count);
            } else {
                immutableEntry = new NonTerminalEntry<>(checkNotNull, count, immutableEntry2);
            }
            i2 += hashCode2 ^ count;
            immutableEntryArr[i3] = immutableEntry;
            immutableEntryArr2[a3] = immutableEntry;
            j += (long) count;
            i3++;
        }
        this.entries = immutableEntryArr;
        this.hashTable = immutableEntryArr2;
        this.size = Ints.saturatedCast(j);
        this.hashCode = i2;
    }

    /* access modifiers changed from: package-private */
    public Multiset.Entry<E> a(int i) {
        return this.entries[i];
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public int count(@Nullable Object obj) {
        Multisets.ImmutableEntry<E>[] immutableEntryArr = this.hashTable;
        if (!(obj == null || immutableEntryArr == null)) {
            for (Multisets.ImmutableEntry<E> immutableEntry = immutableEntryArr[Hashing.a(obj) & (immutableEntryArr.length - 1)]; immutableEntry != null; immutableEntry = immutableEntry.nextInBucket()) {
                if (Objects.equal(obj, immutableEntry.getElement())) {
                    return immutableEntry.getCount();
                }
            }
        }
        return 0;
    }

    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> immutableSet = this.elementSet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ElementSet elementSet2 = new ElementSet();
        this.elementSet = elementSet2;
        return elementSet2;
    }

    public int hashCode() {
        return this.hashCode;
    }

    public int size() {
        return this.size;
    }
}
