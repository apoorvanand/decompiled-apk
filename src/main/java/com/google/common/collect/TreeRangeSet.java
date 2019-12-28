package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Nullable;

@GwtIncompatible
@Beta
public class TreeRangeSet<C extends Comparable<?>> extends AbstractRangeSet<C> implements Serializable {
    @VisibleForTesting
    final NavigableMap<Cut<C>, Range<C>> a;
    private transient Set<Range<C>> asDescendingSetOfRanges;
    private transient Set<Range<C>> asRanges;
    private transient RangeSet<C> complement;

    final class AsRanges extends ForwardingCollection<Range<C>> implements Set<Range<C>> {
        final Collection<Range<C>> a;

        AsRanges(Collection<Range<C>> collection) {
            this.a = collection;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public Collection<Range<C>> delegate() {
            return this.a;
        }

        public boolean equals(@Nullable Object obj) {
            return Sets.a((Set<?>) this, obj);
        }

        public int hashCode() {
            return Sets.a(this);
        }
    }

    private final class Complement extends TreeRangeSet<C> {
        Complement() {
            super(new ComplementRangesByLowerBound(TreeRangeSet.this.a));
        }

        public void add(Range<C> range) {
            TreeRangeSet.this.remove(range);
        }

        public RangeSet<C> complement() {
            return TreeRangeSet.this;
        }

        public boolean contains(C c) {
            return !TreeRangeSet.this.contains(c);
        }

        public void remove(Range<C> range) {
            TreeRangeSet.this.add(range);
        }
    }

    private static final class ComplementRangesByLowerBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        /* access modifiers changed from: private */
        public final Range<Cut<C>> complementLowerBoundWindow;
        private final NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound;
        private final NavigableMap<Cut<C>, Range<C>> positiveRangesByUpperBound;

        ComplementRangesByLowerBound(NavigableMap<Cut<C>, Range<C>> navigableMap) {
            this(navigableMap, Range.all());
        }

        private ComplementRangesByLowerBound(NavigableMap<Cut<C>, Range<C>> navigableMap, Range<Cut<C>> range) {
            this.positiveRangesByLowerBound = navigableMap;
            this.positiveRangesByUpperBound = new RangesByUpperBound(navigableMap);
            this.complementLowerBoundWindow = range;
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> range) {
            if (!this.complementLowerBoundWindow.isConnected(range)) {
                return ImmutableSortedMap.of();
            }
            return new ComplementRangesByLowerBound(this.positiveRangesByLowerBound, range.intersection(this.complementLowerBoundWindow));
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> a() {
            Cut<C> cut;
            Cut<C> cut2;
            NavigableMap<Cut<C>, Range<C>> navigableMap;
            final PeekingIterator peekingIterator = Iterators.peekingIterator(this.positiveRangesByUpperBound.headMap(this.complementLowerBoundWindow.hasUpperBound() ? this.complementLowerBoundWindow.upperEndpoint() : Cut.e(), this.complementLowerBoundWindow.hasUpperBound() && this.complementLowerBoundWindow.upperBoundType() == BoundType.CLOSED).descendingMap().values().iterator());
            if (peekingIterator.hasNext()) {
                if (((Range) peekingIterator.peek()).c == Cut.e()) {
                    cut = ((Range) peekingIterator.next()).b;
                    final Cut cut3 = (Cut) MoreObjects.firstNonNull(cut, Cut.e());
                    return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                        Cut<C> a = cut3;

                        /* access modifiers changed from: protected */
                        /* renamed from: b */
                        public Map.Entry<Cut<C>, Range<C>> computeNext() {
                            if (this.a == Cut.d()) {
                                return (Map.Entry) a();
                            }
                            if (peekingIterator.hasNext()) {
                                Range range = (Range) peekingIterator.next();
                                Range<C> a2 = Range.a(range.c, this.a);
                                this.a = range.b;
                                if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.b.a(a2.b)) {
                                    return Maps.immutableEntry(a2.b, a2);
                                }
                            } else if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.b.a(Cut.d())) {
                                Range<C> a3 = Range.a(Cut.d(), (Cut) this.a);
                                this.a = Cut.d();
                                return Maps.immutableEntry(Cut.d(), a3);
                            }
                            return (Map.Entry) a();
                        }
                    };
                }
                navigableMap = this.positiveRangesByLowerBound;
                cut2 = ((Range) peekingIterator.peek()).c;
            } else if (!this.complementLowerBoundWindow.contains(Cut.d()) || this.positiveRangesByLowerBound.containsKey(Cut.d())) {
                return Iterators.a();
            } else {
                navigableMap = this.positiveRangesByLowerBound;
                cut2 = Cut.d();
            }
            cut = navigableMap.higherKey(cut2);
            final Cut cut32 = (Cut) MoreObjects.firstNonNull(cut, Cut.e());
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                Cut<C> a = cut32;

                /* access modifiers changed from: protected */
                /* renamed from: b */
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (this.a == Cut.d()) {
                        return (Map.Entry) a();
                    }
                    if (peekingIterator.hasNext()) {
                        Range range = (Range) peekingIterator.next();
                        Range<C> a2 = Range.a(range.c, this.a);
                        this.a = range.b;
                        if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.b.a(a2.b)) {
                            return Maps.immutableEntry(a2.b, a2);
                        }
                    } else if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.b.a(Cut.d())) {
                        Range<C> a3 = Range.a(Cut.d(), (Cut) this.a);
                        this.a = Cut.d();
                        return Maps.immutableEntry(Cut.d(), a3);
                    }
                    return (Map.Entry) a();
                }
            };
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> b() {
            NavigableMap<Cut<C>, Range<C>> navigableMap;
            final Cut<C> cut;
            if (this.complementLowerBoundWindow.hasLowerBound()) {
                navigableMap = this.positiveRangesByUpperBound.tailMap(this.complementLowerBoundWindow.lowerEndpoint(), this.complementLowerBoundWindow.lowerBoundType() == BoundType.CLOSED);
            } else {
                navigableMap = this.positiveRangesByUpperBound;
            }
            final PeekingIterator peekingIterator = Iterators.peekingIterator(navigableMap.values().iterator());
            if (this.complementLowerBoundWindow.contains(Cut.d()) && (!peekingIterator.hasNext() || ((Range) peekingIterator.peek()).b != Cut.d())) {
                cut = Cut.d();
            } else if (!peekingIterator.hasNext()) {
                return Iterators.a();
            } else {
                cut = ((Range) peekingIterator.next()).c;
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                Cut<C> a = cut;

                /* access modifiers changed from: protected */
                /* renamed from: b */
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    Range<C> range;
                    Cut<C> cut;
                    if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.c.a(this.a) || this.a == Cut.e()) {
                        return (Map.Entry) a();
                    }
                    if (peekingIterator.hasNext()) {
                        Range range2 = (Range) peekingIterator.next();
                        range = Range.a(this.a, range2.b);
                        cut = range2.c;
                    } else {
                        range = Range.a(this.a, (Cut<C>) Cut.e());
                        cut = Cut.e();
                    }
                    this.a = cut;
                    return Maps.immutableEntry(range.b, range);
                }
            };
        }

        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        public boolean containsKey(Object obj) {
            return get(obj) != null;
        }

        @Nullable
        public Range<C> get(Object obj) {
            if (obj instanceof Cut) {
                try {
                    Cut cut = (Cut) obj;
                    Map.Entry firstEntry = tailMap(cut, true).firstEntry();
                    if (firstEntry != null && ((Cut) firstEntry.getKey()).equals(cut)) {
                        return (Range) firstEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
            return subMap(Range.upTo(cut, BoundType.a(z)));
        }

        public int size() {
            return Iterators.size(b());
        }

        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
            return subMap(Range.range(cut, BoundType.a(z), cut2, BoundType.a(z2)));
        }

        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
            return subMap(Range.downTo(cut, BoundType.a(z)));
        }
    }

    @VisibleForTesting
    static final class RangesByUpperBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        private final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        /* access modifiers changed from: private */
        public final Range<Cut<C>> upperBoundWindow;

        RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> navigableMap) {
            this.rangesByLowerBound = navigableMap;
            this.upperBoundWindow = Range.all();
        }

        private RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> navigableMap, Range<Cut<C>> range) {
            this.rangesByLowerBound = navigableMap;
            this.upperBoundWindow = range;
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> range) {
            return range.isConnected(this.upperBoundWindow) ? new RangesByUpperBound(this.rangesByLowerBound, range.intersection(this.upperBoundWindow)) : ImmutableSortedMap.of();
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> a() {
            final PeekingIterator peekingIterator = Iterators.peekingIterator((this.upperBoundWindow.hasUpperBound() ? this.rangesByLowerBound.headMap(this.upperBoundWindow.upperEndpoint(), false) : this.rangesByLowerBound).descendingMap().values().iterator());
            if (peekingIterator.hasNext() && this.upperBoundWindow.c.a(((Range) peekingIterator.peek()).c)) {
                peekingIterator.next();
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                /* access modifiers changed from: protected */
                /* renamed from: b */
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!peekingIterator.hasNext()) {
                        return (Map.Entry) a();
                    }
                    Range range = (Range) peekingIterator.next();
                    return RangesByUpperBound.this.upperBoundWindow.b.a(range.c) ? Maps.immutableEntry(range.c, range) : (Map.Entry) a();
                }
            };
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> b() {
            Map.Entry<K, V> lowerEntry;
            final Iterator it = ((this.upperBoundWindow.hasLowerBound() && (lowerEntry = this.rangesByLowerBound.lowerEntry(this.upperBoundWindow.lowerEndpoint())) != null) ? this.upperBoundWindow.b.a(((Range) lowerEntry.getValue()).c) ? this.rangesByLowerBound.tailMap(lowerEntry.getKey(), true) : this.rangesByLowerBound.tailMap(this.upperBoundWindow.lowerEndpoint(), true) : this.rangesByLowerBound).values().iterator();
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                /* access modifiers changed from: protected */
                /* renamed from: b */
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!it.hasNext()) {
                        return (Map.Entry) a();
                    }
                    Range range = (Range) it.next();
                    return RangesByUpperBound.this.upperBoundWindow.c.a(range.c) ? (Map.Entry) a() : Maps.immutableEntry(range.c, range);
                }
            };
        }

        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        public boolean containsKey(@Nullable Object obj) {
            return get(obj) != null;
        }

        public Range<C> get(@Nullable Object obj) {
            Map.Entry<Cut<C>, Range<C>> lowerEntry;
            if (obj instanceof Cut) {
                try {
                    Cut cut = (Cut) obj;
                    if (this.upperBoundWindow.contains(cut) && (lowerEntry = this.rangesByLowerBound.lowerEntry(cut)) != null && lowerEntry.getValue().c.equals(cut)) {
                        return lowerEntry.getValue();
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
            return subMap(Range.upTo(cut, BoundType.a(z)));
        }

        public boolean isEmpty() {
            return this.upperBoundWindow.equals(Range.all()) ? this.rangesByLowerBound.isEmpty() : !b().hasNext();
        }

        public int size() {
            return this.upperBoundWindow.equals(Range.all()) ? this.rangesByLowerBound.size() : Iterators.size(b());
        }

        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
            return subMap(Range.range(cut, BoundType.a(z), cut2, BoundType.a(z2)));
        }

        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
            return subMap(Range.downTo(cut, BoundType.a(z)));
        }
    }

    private final class SubRangeSet extends TreeRangeSet<C> {
        private final Range<C> restriction;

        SubRangeSet(Range<C> range) {
            super(new SubRangeSetRangesByLowerBound(Range.all(), range, TreeRangeSet.this.a));
            this.restriction = range;
        }

        public void add(Range<C> range) {
            Preconditions.checkArgument(this.restriction.encloses(range), "Cannot add range %s to subRangeSet(%s)", (Object) range, (Object) this.restriction);
            TreeRangeSet.super.add(range);
        }

        public void clear() {
            TreeRangeSet.this.remove(this.restriction);
        }

        public boolean contains(C c) {
            return this.restriction.contains(c) && TreeRangeSet.this.contains(c);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0011, code lost:
            r3 = com.google.common.collect.TreeRangeSet.a(r2.b, r3);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean encloses(com.google.common.collect.Range<C> r3) {
            /*
                r2 = this;
                com.google.common.collect.Range<C> r0 = r2.restriction
                boolean r0 = r0.isEmpty()
                r1 = 0
                if (r0 != 0) goto L_0x0026
                com.google.common.collect.Range<C> r0 = r2.restriction
                boolean r0 = r0.encloses(r3)
                if (r0 == 0) goto L_0x0026
                com.google.common.collect.TreeRangeSet r0 = com.google.common.collect.TreeRangeSet.this
                com.google.common.collect.Range r3 = r0.rangeEnclosing(r3)
                if (r3 == 0) goto L_0x0026
                com.google.common.collect.Range<C> r0 = r2.restriction
                com.google.common.collect.Range r3 = r3.intersection(r0)
                boolean r3 = r3.isEmpty()
                if (r3 != 0) goto L_0x0026
                r1 = 1
            L_0x0026:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.TreeRangeSet.SubRangeSet.encloses(com.google.common.collect.Range):boolean");
        }

        @Nullable
        public Range<C> rangeContaining(C c) {
            Range rangeContaining;
            if (this.restriction.contains(c) && (rangeContaining = TreeRangeSet.this.rangeContaining(c)) != null) {
                return rangeContaining.intersection(this.restriction);
            }
            return null;
        }

        public void remove(Range<C> range) {
            if (range.isConnected(this.restriction)) {
                TreeRangeSet.this.remove(range.intersection(this.restriction));
            }
        }

        public RangeSet<C> subRangeSet(Range<C> range) {
            return range.encloses(this.restriction) ? this : range.isConnected(this.restriction) ? new SubRangeSet(this.restriction.intersection(range)) : ImmutableRangeSet.of();
        }
    }

    private static final class SubRangeSetRangesByLowerBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        /* access modifiers changed from: private */
        public final Range<Cut<C>> lowerBoundWindow;
        private final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        private final NavigableMap<Cut<C>, Range<C>> rangesByUpperBound;
        /* access modifiers changed from: private */
        public final Range<C> restriction;

        private SubRangeSetRangesByLowerBound(Range<Cut<C>> range, Range<C> range2, NavigableMap<Cut<C>, Range<C>> navigableMap) {
            this.lowerBoundWindow = (Range) Preconditions.checkNotNull(range);
            this.restriction = (Range) Preconditions.checkNotNull(range2);
            this.rangesByLowerBound = (NavigableMap) Preconditions.checkNotNull(navigableMap);
            this.rangesByUpperBound = new RangesByUpperBound(navigableMap);
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> range) {
            return !range.isConnected(this.lowerBoundWindow) ? ImmutableSortedMap.of() : new SubRangeSetRangesByLowerBound(this.lowerBoundWindow.intersection(range), this.restriction, this.rangesByLowerBound);
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> a() {
            if (this.restriction.isEmpty()) {
                return Iterators.a();
            }
            Cut cut = (Cut) Ordering.natural().min(this.lowerBoundWindow.c, Cut.b(this.restriction.c));
            final Iterator it = this.rangesByLowerBound.headMap(cut.c(), cut.b() == BoundType.CLOSED).descendingMap().values().iterator();
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                /* access modifiers changed from: protected */
                /* renamed from: b */
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!it.hasNext()) {
                        return (Map.Entry) a();
                    }
                    Range range = (Range) it.next();
                    if (SubRangeSetRangesByLowerBound.this.restriction.b.compareTo(range.c) >= 0) {
                        return (Map.Entry) a();
                    }
                    Range intersection = range.intersection(SubRangeSetRangesByLowerBound.this.restriction);
                    return SubRangeSetRangesByLowerBound.this.lowerBoundWindow.contains(intersection.b) ? Maps.immutableEntry(intersection.b, intersection) : (Map.Entry) a();
                }
            };
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> b() {
            NavigableMap<Cut<C>, Range<C>> navigableMap;
            C c;
            if (this.restriction.isEmpty()) {
                return Iterators.a();
            }
            if (this.lowerBoundWindow.c.a(this.restriction.b)) {
                return Iterators.a();
            }
            boolean z = false;
            if (this.lowerBoundWindow.b.a(this.restriction.b)) {
                navigableMap = this.rangesByUpperBound;
                c = this.restriction.b;
            } else {
                navigableMap = this.rangesByLowerBound;
                c = this.lowerBoundWindow.b.c();
                if (this.lowerBoundWindow.lowerBoundType() == BoundType.CLOSED) {
                    z = true;
                }
            }
            final Iterator it = navigableMap.tailMap(c, z).values().iterator();
            final Cut cut = (Cut) Ordering.natural().min(this.lowerBoundWindow.c, Cut.b(this.restriction.c));
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                /* access modifiers changed from: protected */
                /* renamed from: b */
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!it.hasNext()) {
                        return (Map.Entry) a();
                    }
                    Range range = (Range) it.next();
                    if (cut.a(range.b)) {
                        return (Map.Entry) a();
                    }
                    Range intersection = range.intersection(SubRangeSetRangesByLowerBound.this.restriction);
                    return Maps.immutableEntry(intersection.b, intersection);
                }
            };
        }

        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        public boolean containsKey(@Nullable Object obj) {
            return get(obj) != null;
        }

        @Nullable
        public Range<C> get(@Nullable Object obj) {
            if (obj instanceof Cut) {
                try {
                    Cut cut = (Cut) obj;
                    if (this.lowerBoundWindow.contains(cut) && cut.compareTo(this.restriction.b) >= 0) {
                        if (cut.compareTo(this.restriction.c) < 0) {
                            if (cut.equals(this.restriction.b)) {
                                Range range = (Range) Maps.c(this.rangesByLowerBound.floorEntry(cut));
                                if (range != null && range.c.compareTo(this.restriction.b) > 0) {
                                    return range.intersection(this.restriction);
                                }
                            } else {
                                Range range2 = (Range) this.rangesByLowerBound.get(cut);
                                if (range2 != null) {
                                    return range2.intersection(this.restriction);
                                }
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
            }
            return null;
        }

        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
            return subMap(Range.upTo(cut, BoundType.a(z)));
        }

        public int size() {
            return Iterators.size(b());
        }

        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
            return subMap(Range.range(cut, BoundType.a(z), cut2, BoundType.a(z2)));
        }

        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
            return subMap(Range.downTo(cut, BoundType.a(z)));
        }
    }

    private TreeRangeSet(NavigableMap<Cut<C>, Range<C>> navigableMap) {
        this.a = navigableMap;
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create() {
        return new TreeRangeSet<>(new TreeMap());
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(RangeSet<C> rangeSet) {
        TreeRangeSet<C> create = create();
        create.addAll(rangeSet);
        return create;
    }

    /* access modifiers changed from: private */
    @Nullable
    public Range<C> rangeEnclosing(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.a.floorEntry(range.b);
        if (floorEntry == null || !floorEntry.getValue().encloses(range)) {
            return null;
        }
        return floorEntry.getValue();
    }

    private void replaceRangeWithSameLowerBound(Range<C> range) {
        if (range.isEmpty()) {
            this.a.remove(range.b);
        } else {
            this.a.put(range.b, range);
        }
    }

    public void add(Range<C> range) {
        Preconditions.checkNotNull(range);
        if (!range.isEmpty()) {
            Cut<C> cut = range.b;
            Cut<C> cut2 = range.c;
            Map.Entry<K, V> lowerEntry = this.a.lowerEntry(cut);
            if (lowerEntry != null) {
                Range range2 = (Range) lowerEntry.getValue();
                if (range2.c.compareTo(cut) >= 0) {
                    if (range2.c.compareTo(cut2) >= 0) {
                        cut2 = range2.c;
                    }
                    cut = range2.b;
                }
            }
            Map.Entry<K, V> floorEntry = this.a.floorEntry(cut2);
            if (floorEntry != null) {
                Range range3 = (Range) floorEntry.getValue();
                if (range3.c.compareTo(cut2) >= 0) {
                    cut2 = range3.c;
                }
            }
            this.a.subMap(cut, cut2).clear();
            replaceRangeWithSameLowerBound(Range.a(cut, cut2));
        }
    }

    public /* bridge */ /* synthetic */ void addAll(RangeSet rangeSet) {
        super.addAll(rangeSet);
    }

    public Set<Range<C>> asDescendingSetOfRanges() {
        Set<Range<C>> set = this.asDescendingSetOfRanges;
        if (set != null) {
            return set;
        }
        AsRanges asRanges2 = new AsRanges(this.a.descendingMap().values());
        this.asDescendingSetOfRanges = asRanges2;
        return asRanges2;
    }

    public Set<Range<C>> asRanges() {
        Set<Range<C>> set = this.asRanges;
        if (set != null) {
            return set;
        }
        AsRanges asRanges2 = new AsRanges(this.a.values());
        this.asRanges = asRanges2;
        return asRanges2;
    }

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public RangeSet<C> complement() {
        RangeSet<C> rangeSet = this.complement;
        if (rangeSet != null) {
            return rangeSet;
        }
        Complement complement2 = new Complement();
        this.complement = complement2;
        return complement2;
    }

    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return super.contains(comparable);
    }

    public boolean encloses(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.a.floorEntry(range.b);
        return floorEntry != null && floorEntry.getValue().encloses(range);
    }

    public /* bridge */ /* synthetic */ boolean enclosesAll(RangeSet rangeSet) {
        return super.enclosesAll(rangeSet);
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean intersects(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry<Cut<C>, Range<C>> ceilingEntry = this.a.ceilingEntry(range.b);
        if (ceilingEntry != null && ceilingEntry.getValue().isConnected(range) && !ceilingEntry.getValue().intersection(range).isEmpty()) {
            return true;
        }
        Map.Entry<Cut<C>, Range<C>> lowerEntry = this.a.lowerEntry(range.b);
        return lowerEntry != null && lowerEntry.getValue().isConnected(range) && !lowerEntry.getValue().intersection(range).isEmpty();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Nullable
    public Range<C> rangeContaining(C c) {
        Preconditions.checkNotNull(c);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.a.floorEntry(Cut.b(c));
        if (floorEntry == null || !floorEntry.getValue().contains(c)) {
            return null;
        }
        return floorEntry.getValue();
    }

    public void remove(Range<C> range) {
        Preconditions.checkNotNull(range);
        if (!range.isEmpty()) {
            Map.Entry<Cut<C>, Range<C>> lowerEntry = this.a.lowerEntry(range.b);
            if (lowerEntry != null) {
                Range value = lowerEntry.getValue();
                if (value.c.compareTo(range.b) >= 0) {
                    if (range.hasUpperBound() && value.c.compareTo(range.c) >= 0) {
                        replaceRangeWithSameLowerBound(Range.a(range.c, value.c));
                    }
                    replaceRangeWithSameLowerBound(Range.a(value.b, range.b));
                }
            }
            Map.Entry<Cut<C>, Range<C>> floorEntry = this.a.floorEntry(range.c);
            if (floorEntry != null) {
                Range value2 = floorEntry.getValue();
                if (range.hasUpperBound() && value2.c.compareTo(range.c) >= 0) {
                    replaceRangeWithSameLowerBound(Range.a(range.c, value2.c));
                }
            }
            this.a.subMap(range.b, range.c).clear();
        }
    }

    public /* bridge */ /* synthetic */ void removeAll(RangeSet rangeSet) {
        super.removeAll(rangeSet);
    }

    public Range<C> span() {
        Map.Entry<Cut<C>, Range<C>> firstEntry = this.a.firstEntry();
        Map.Entry<Cut<C>, Range<C>> lastEntry = this.a.lastEntry();
        if (firstEntry != null) {
            return Range.a(firstEntry.getValue().b, lastEntry.getValue().c);
        }
        throw new NoSuchElementException();
    }

    public RangeSet<C> subRangeSet(Range<C> range) {
        return range.equals(Range.all()) ? this : new SubRangeSet(range);
    }
}
