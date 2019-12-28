package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

@GwtCompatible
abstract class Cut<C extends Comparable> implements Serializable, Comparable<Cut<C>> {
    private static final long serialVersionUID = 0;
    final C a;

    private static final class AboveAll extends Cut<Comparable<?>> {
        /* access modifiers changed from: private */
        public static final AboveAll INSTANCE = new AboveAll();
        private static final long serialVersionUID = 0;

        private AboveAll() {
            super(null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        /* access modifiers changed from: package-private */
        public BoundType a() {
            throw new AssertionError("this statement should be unreachable");
        }

        /* access modifiers changed from: package-private */
        public Cut<Comparable<?>> a(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        /* access modifiers changed from: package-private */
        public Comparable<?> a(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void a(StringBuilder sb) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public boolean a(Comparable<?> comparable) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public BoundType b() {
            throw new IllegalStateException();
        }

        /* access modifiers changed from: package-private */
        public Cut<Comparable<?>> b(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }

        /* access modifiers changed from: package-private */
        public Comparable<?> b(DiscreteDomain<Comparable<?>> discreteDomain) {
            return discreteDomain.maxValue();
        }

        /* access modifiers changed from: package-private */
        public void b(StringBuilder sb) {
            sb.append("+∞)");
        }

        /* access modifiers changed from: package-private */
        public Comparable<?> c() {
            throw new IllegalStateException("range unbounded on this side");
        }

        public int compareTo(Cut<Comparable<?>> cut) {
            return cut == this ? 0 : 1;
        }

        public String toString() {
            return "+∞";
        }
    }

    private static final class AboveValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        AboveValue(C c) {
            super((Comparable) Preconditions.checkNotNull(c));
        }

        /* access modifiers changed from: package-private */
        public BoundType a() {
            return BoundType.OPEN;
        }

        /* access modifiers changed from: package-private */
        public Cut<C> a(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    C next = discreteDomain.next(this.a);
                    return next == null ? Cut.d() : b(next);
                case OPEN:
                    return this;
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public C a(DiscreteDomain<C> discreteDomain) {
            return discreteDomain.next(this.a);
        }

        /* access modifiers changed from: package-private */
        public void a(StringBuilder sb) {
            sb.append('(');
            sb.append(this.a);
        }

        /* access modifiers changed from: package-private */
        public boolean a(C c) {
            return Range.a(this.a, (Comparable) c) < 0;
        }

        /* access modifiers changed from: package-private */
        public BoundType b() {
            return BoundType.CLOSED;
        }

        /* access modifiers changed from: package-private */
        public Cut<C> b(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    return this;
                case OPEN:
                    C next = discreteDomain.next(this.a);
                    return next == null ? Cut.e() : b(next);
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public C b(DiscreteDomain<C> discreteDomain) {
            return this.a;
        }

        /* access modifiers changed from: package-private */
        public void b(StringBuilder sb) {
            sb.append(this.a);
            sb.append(']');
        }

        /* access modifiers changed from: package-private */
        public Cut<C> c(DiscreteDomain<C> discreteDomain) {
            C a = a(discreteDomain);
            return a != null ? b(a) : Cut.e();
        }

        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return Cut.super.compareTo((Cut) obj);
        }

        public int hashCode() {
            return ~this.a.hashCode();
        }

        public String toString() {
            return "/" + this.a + "\\";
        }
    }

    private static final class BelowAll extends Cut<Comparable<?>> {
        /* access modifiers changed from: private */
        public static final BelowAll INSTANCE = new BelowAll();
        private static final long serialVersionUID = 0;

        private BelowAll() {
            super(null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        /* access modifiers changed from: package-private */
        public BoundType a() {
            throw new IllegalStateException();
        }

        /* access modifiers changed from: package-private */
        public Cut<Comparable<?>> a(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new IllegalStateException();
        }

        /* access modifiers changed from: package-private */
        public Comparable<?> a(DiscreteDomain<Comparable<?>> discreteDomain) {
            return discreteDomain.minValue();
        }

        /* access modifiers changed from: package-private */
        public void a(StringBuilder sb) {
            sb.append("(-∞");
        }

        /* access modifiers changed from: package-private */
        public boolean a(Comparable<?> comparable) {
            return true;
        }

        /* access modifiers changed from: package-private */
        public BoundType b() {
            throw new AssertionError("this statement should be unreachable");
        }

        /* access modifiers changed from: package-private */
        public Cut<Comparable<?>> b(BoundType boundType, DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        /* access modifiers changed from: package-private */
        public Comparable<?> b(DiscreteDomain<Comparable<?>> discreteDomain) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void b(StringBuilder sb) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public Cut<Comparable<?>> c(DiscreteDomain<Comparable<?>> discreteDomain) {
            try {
                return Cut.b(discreteDomain.minValue());
            } catch (NoSuchElementException unused) {
                return this;
            }
        }

        /* access modifiers changed from: package-private */
        public Comparable<?> c() {
            throw new IllegalStateException("range unbounded on this side");
        }

        public int compareTo(Cut<Comparable<?>> cut) {
            return cut == this ? 0 : -1;
        }

        public String toString() {
            return "-∞";
        }
    }

    private static final class BelowValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        BelowValue(C c) {
            super((Comparable) Preconditions.checkNotNull(c));
        }

        /* access modifiers changed from: package-private */
        public BoundType a() {
            return BoundType.CLOSED;
        }

        /* access modifiers changed from: package-private */
        public Cut<C> a(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    return this;
                case OPEN:
                    C previous = discreteDomain.previous(this.a);
                    return previous == null ? Cut.d() : new AboveValue(previous);
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public C a(DiscreteDomain<C> discreteDomain) {
            return this.a;
        }

        /* access modifiers changed from: package-private */
        public void a(StringBuilder sb) {
            sb.append('[');
            sb.append(this.a);
        }

        /* access modifiers changed from: package-private */
        public boolean a(C c) {
            return Range.a(this.a, (Comparable) c) <= 0;
        }

        /* access modifiers changed from: package-private */
        public BoundType b() {
            return BoundType.OPEN;
        }

        /* access modifiers changed from: package-private */
        public Cut<C> b(BoundType boundType, DiscreteDomain<C> discreteDomain) {
            switch (boundType) {
                case CLOSED:
                    C previous = discreteDomain.previous(this.a);
                    return previous == null ? Cut.e() : new AboveValue(previous);
                case OPEN:
                    return this;
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public C b(DiscreteDomain<C> discreteDomain) {
            return discreteDomain.previous(this.a);
        }

        /* access modifiers changed from: package-private */
        public void b(StringBuilder sb) {
            sb.append(this.a);
            sb.append(')');
        }

        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return Cut.super.compareTo((Cut) obj);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return "\\" + this.a + "/";
        }
    }

    Cut(@Nullable C c) {
        this.a = c;
    }

    static <C extends Comparable> Cut<C> b(C c) {
        return new BelowValue(c);
    }

    static <C extends Comparable> Cut<C> c(C c) {
        return new AboveValue(c);
    }

    static <C extends Comparable> Cut<C> d() {
        return BelowAll.INSTANCE;
    }

    static <C extends Comparable> Cut<C> e() {
        return AboveAll.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    public abstract BoundType a();

    /* access modifiers changed from: package-private */
    public abstract Cut<C> a(BoundType boundType, DiscreteDomain<C> discreteDomain);

    /* access modifiers changed from: package-private */
    public abstract C a(DiscreteDomain<C> discreteDomain);

    /* access modifiers changed from: package-private */
    public abstract void a(StringBuilder sb);

    /* access modifiers changed from: package-private */
    public abstract boolean a(C c);

    /* access modifiers changed from: package-private */
    public abstract BoundType b();

    /* access modifiers changed from: package-private */
    public abstract Cut<C> b(BoundType boundType, DiscreteDomain<C> discreteDomain);

    /* access modifiers changed from: package-private */
    public abstract C b(DiscreteDomain<C> discreteDomain);

    /* access modifiers changed from: package-private */
    public abstract void b(StringBuilder sb);

    /* access modifiers changed from: package-private */
    public Cut<C> c(DiscreteDomain<C> discreteDomain) {
        return this;
    }

    /* access modifiers changed from: package-private */
    public C c() {
        return this.a;
    }

    public int compareTo(Cut<C> cut) {
        if (cut == d()) {
            return 1;
        }
        if (cut == e()) {
            return -1;
        }
        int a2 = Range.a((Comparable) this.a, (Comparable) cut.a);
        return a2 != 0 ? a2 : Booleans.compare(this instanceof AboveValue, cut instanceof AboveValue);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Cut)) {
            return false;
        }
        try {
            return compareTo((Cut) obj) == 0;
        } catch (ClassCastException unused) {
            return false;
        }
    }
}
