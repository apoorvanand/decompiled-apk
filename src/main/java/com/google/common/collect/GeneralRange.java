package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Comparator;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
final class GeneralRange<T> implements Serializable {
    private final Comparator<? super T> comparator;
    private final boolean hasLowerBound;
    private final boolean hasUpperBound;
    private final BoundType lowerBoundType;
    @Nullable
    private final T lowerEndpoint;
    private transient GeneralRange<T> reverse;
    private final BoundType upperBoundType;
    @Nullable
    private final T upperEndpoint;

    private GeneralRange(Comparator<? super T> comparator2, boolean z, @Nullable T t, BoundType boundType, boolean z2, @Nullable T t2, BoundType boundType2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        this.hasLowerBound = z;
        this.hasUpperBound = z2;
        this.lowerEndpoint = t;
        this.lowerBoundType = (BoundType) Preconditions.checkNotNull(boundType);
        this.upperEndpoint = t2;
        this.upperBoundType = (BoundType) Preconditions.checkNotNull(boundType2);
        if (z) {
            comparator2.compare(t, t);
        }
        if (z2) {
            comparator2.compare(t2, t2);
        }
        if (z && z2) {
            int compare = comparator2.compare(t, t2);
            boolean z3 = true;
            Preconditions.checkArgument(compare <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", (Object) t, (Object) t2);
            if (compare == 0) {
                Preconditions.checkArgument((boundType != BoundType.OPEN) | (boundType2 == BoundType.OPEN ? false : z3));
            }
        }
    }

    static <T> GeneralRange<T> a(Comparator<? super T> comparator2) {
        return new GeneralRange(comparator2, false, (Object) null, BoundType.OPEN, false, (Object) null, BoundType.OPEN);
    }

    static <T> GeneralRange<T> a(Comparator<? super T> comparator2, @Nullable T t, BoundType boundType) {
        return new GeneralRange(comparator2, true, t, boundType, false, (T) null, BoundType.OPEN);
    }

    static <T> GeneralRange<T> b(Comparator<? super T> comparator2, @Nullable T t, BoundType boundType) {
        return new GeneralRange(comparator2, false, (Object) null, BoundType.OPEN, true, t, boundType);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0047, code lost:
        if (r11.e() == com.google.common.collect.BoundType.OPEN) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0084, code lost:
        if (r11.g() == com.google.common.collect.BoundType.OPEN) goto L_0x005d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008b A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.collect.GeneralRange<T> a(com.google.common.collect.GeneralRange<T> r11) {
        /*
            r10 = this;
            com.google.common.base.Preconditions.checkNotNull(r11)
            java.util.Comparator<? super T> r0 = r10.comparator
            java.util.Comparator<? super T> r1 = r11.comparator
            boolean r0 = r0.equals(r1)
            com.google.common.base.Preconditions.checkArgument(r0)
            boolean r0 = r10.hasLowerBound
            java.lang.Object r1 = r10.d()
            com.google.common.collect.BoundType r2 = r10.e()
            boolean r3 = r10.b()
            if (r3 != 0) goto L_0x0029
            boolean r0 = r11.hasLowerBound
        L_0x0020:
            java.lang.Object r1 = r11.d()
            com.google.common.collect.BoundType r2 = r11.e()
            goto L_0x004a
        L_0x0029:
            boolean r3 = r11.b()
            if (r3 == 0) goto L_0x004a
            java.util.Comparator<? super T> r3 = r10.comparator
            java.lang.Object r4 = r10.d()
            java.lang.Object r5 = r11.d()
            int r3 = r3.compare(r4, r5)
            if (r3 < 0) goto L_0x0020
            if (r3 != 0) goto L_0x004a
            com.google.common.collect.BoundType r3 = r11.e()
            com.google.common.collect.BoundType r4 = com.google.common.collect.BoundType.OPEN
            if (r3 != r4) goto L_0x004a
            goto L_0x0020
        L_0x004a:
            r4 = r0
            boolean r0 = r10.hasUpperBound
            java.lang.Object r3 = r10.f()
            com.google.common.collect.BoundType r5 = r10.g()
            boolean r6 = r10.c()
            if (r6 != 0) goto L_0x0066
            boolean r0 = r11.hasUpperBound
        L_0x005d:
            java.lang.Object r3 = r11.f()
            com.google.common.collect.BoundType r5 = r11.g()
            goto L_0x0087
        L_0x0066:
            boolean r6 = r11.c()
            if (r6 == 0) goto L_0x0087
            java.util.Comparator<? super T> r6 = r10.comparator
            java.lang.Object r7 = r10.f()
            java.lang.Object r8 = r11.f()
            int r6 = r6.compare(r7, r8)
            if (r6 > 0) goto L_0x005d
            if (r6 != 0) goto L_0x0087
            com.google.common.collect.BoundType r6 = r11.g()
            com.google.common.collect.BoundType r7 = com.google.common.collect.BoundType.OPEN
            if (r6 != r7) goto L_0x0087
            goto L_0x005d
        L_0x0087:
            r7 = r0
            r8 = r3
            if (r4 == 0) goto L_0x00a7
            if (r7 == 0) goto L_0x00a7
            java.util.Comparator<? super T> r11 = r10.comparator
            int r11 = r11.compare(r1, r8)
            if (r11 > 0) goto L_0x009f
            if (r11 != 0) goto L_0x00a7
            com.google.common.collect.BoundType r11 = com.google.common.collect.BoundType.OPEN
            if (r2 != r11) goto L_0x00a7
            com.google.common.collect.BoundType r11 = com.google.common.collect.BoundType.OPEN
            if (r5 != r11) goto L_0x00a7
        L_0x009f:
            com.google.common.collect.BoundType r11 = com.google.common.collect.BoundType.OPEN
            com.google.common.collect.BoundType r0 = com.google.common.collect.BoundType.CLOSED
            r6 = r11
            r9 = r0
            r5 = r8
            goto L_0x00aa
        L_0x00a7:
            r6 = r2
            r9 = r5
            r5 = r1
        L_0x00aa:
            com.google.common.collect.GeneralRange r11 = new com.google.common.collect.GeneralRange
            java.util.Comparator<? super T> r3 = r10.comparator
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.GeneralRange.a(com.google.common.collect.GeneralRange):com.google.common.collect.GeneralRange");
    }

    /* access modifiers changed from: package-private */
    public Comparator<? super T> a() {
        return this.comparator;
    }

    /* access modifiers changed from: package-private */
    public boolean a(@Nullable T t) {
        if (!b()) {
            return false;
        }
        int compare = this.comparator.compare(t, d());
        boolean z = true;
        boolean z2 = compare < 0;
        boolean z3 = compare == 0;
        if (e() != BoundType.OPEN) {
            z = false;
        }
        return (z3 & z) | z2;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.hasLowerBound;
    }

    /* access modifiers changed from: package-private */
    public boolean b(@Nullable T t) {
        if (!c()) {
            return false;
        }
        int compare = this.comparator.compare(t, f());
        boolean z = true;
        boolean z2 = compare > 0;
        boolean z3 = compare == 0;
        if (g() != BoundType.OPEN) {
            z = false;
        }
        return (z3 & z) | z2;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.hasUpperBound;
    }

    /* access modifiers changed from: package-private */
    public boolean c(@Nullable T t) {
        return !a(t) && !b(t);
    }

    /* access modifiers changed from: package-private */
    public T d() {
        return this.lowerEndpoint;
    }

    /* access modifiers changed from: package-private */
    public BoundType e() {
        return this.lowerBoundType;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof GeneralRange)) {
            return false;
        }
        GeneralRange generalRange = (GeneralRange) obj;
        return this.comparator.equals(generalRange.comparator) && this.hasLowerBound == generalRange.hasLowerBound && this.hasUpperBound == generalRange.hasUpperBound && e().equals(generalRange.e()) && g().equals(generalRange.g()) && Objects.equal(d(), generalRange.d()) && Objects.equal(f(), generalRange.f());
    }

    /* access modifiers changed from: package-private */
    public T f() {
        return this.upperEndpoint;
    }

    /* access modifiers changed from: package-private */
    public BoundType g() {
        return this.upperBoundType;
    }

    public int hashCode() {
        return Objects.hashCode(this.comparator, d(), e(), f(), g());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.comparator);
        sb.append(":");
        sb.append(this.lowerBoundType == BoundType.CLOSED ? '[' : '(');
        sb.append(this.hasLowerBound ? this.lowerEndpoint : "-∞");
        sb.append(',');
        sb.append(this.hasUpperBound ? this.upperEndpoint : "∞");
        sb.append(this.upperBoundType == BoundType.CLOSED ? ']' : ')');
        return sb.toString();
    }
}
