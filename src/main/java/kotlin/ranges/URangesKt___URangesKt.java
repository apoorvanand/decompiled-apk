package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;
import kotlin.ranges.UIntProgression;
import kotlin.ranges.ULongProgression;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\n\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001e\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001e\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0004\u001a\u001e\u0010\u000e\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0007\u001a\u001e\u0010\u000e\u001a\u00020\b*\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\n\u001a\u001e\u0010\u000e\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\r\u001a&\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a&\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a$\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a&\u0010\u0014\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a$\u0010\u0014\u001a\u00020\b*\u00020\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a&\u0010\u0014\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u0005H\nø\u0001\u0000¢\u0006\u0002\b*\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0005H\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\b\u0010)\u001a\u0004\u0018\u00010\bH\nø\u0001\u0000¢\u0006\u0002\b4\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a\u001f\u00107\u001a\u000208*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0004ø\u0001\u0000¢\u0006\u0004\b:\u0010;\u001a\u001f\u00107\u001a\u000208*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0004ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a\u001f\u00107\u001a\u00020>*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0004ø\u0001\u0000¢\u0006\u0004\b?\u0010@\u001a\u001f\u00107\u001a\u000208*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0004ø\u0001\u0000¢\u0006\u0004\bA\u0010B\u001a\u0015\u0010C\u001a\u00020\u0005*\u00020%H\bø\u0001\u0000¢\u0006\u0002\u0010D\u001a\u001c\u0010C\u001a\u00020\u0005*\u00020%2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010F\u001a\u0015\u0010C\u001a\u00020\b*\u00020/H\bø\u0001\u0000¢\u0006\u0002\u0010G\u001a\u001c\u0010C\u001a\u00020\b*\u00020/2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010H\u001a\f\u0010I\u001a\u000208*\u000208H\u0007\u001a\f\u0010I\u001a\u00020>*\u00020>H\u0007\u001a\u0015\u0010J\u001a\u000208*\u0002082\u0006\u0010J\u001a\u00020KH\u0004\u001a\u0015\u0010J\u001a\u00020>*\u00020>2\u0006\u0010J\u001a\u00020LH\u0004\u001a\u001f\u0010M\u001a\u00020%*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0004ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a\u001f\u0010M\u001a\u00020%*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0004ø\u0001\u0000¢\u0006\u0004\bP\u0010Q\u001a\u001f\u0010M\u001a\u00020/*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0004ø\u0001\u0000¢\u0006\u0004\bR\u0010S\u001a\u001f\u0010M\u001a\u00020%*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0004ø\u0001\u0000¢\u0006\u0004\bT\u0010U\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, d2 = {"coerceAtLeast", "Lkotlin/UByte;", "minimumValue", "coerceAtLeast-Kr8caGY", "(BB)B", "Lkotlin/UInt;", "coerceAtLeast-J1ME1BU", "(II)I", "Lkotlin/ULong;", "coerceAtLeast-eb3DHEI", "(JJ)J", "Lkotlin/UShort;", "coerceAtLeast-5PvTz6A", "(SS)S", "coerceAtMost", "maximumValue", "coerceAtMost-Kr8caGY", "coerceAtMost-J1ME1BU", "coerceAtMost-eb3DHEI", "coerceAtMost-5PvTz6A", "coerceIn", "coerceIn-b33U2AM", "(BBB)B", "coerceIn-WZ9TVnA", "(III)I", "range", "Lkotlin/ranges/ClosedRange;", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "coerceIn-VKSA0NQ", "(SSS)S", "contains", "", "Lkotlin/ranges/UIntRange;", "value", "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "element", "contains-biwQdVI", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "Lkotlin/ranges/ULongRange;", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-GYNo2lE", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", "downTo", "Lkotlin/ranges/UIntProgression;", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "reversed", "step", "", "", "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, xi = 1, xs = "kotlin/ranges/URangesKt")
class URangesKt___URangesKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtLeast-5PvTz6A  reason: not valid java name */
    public static final short m851coerceAtLeast5PvTz6A(short s, short s2) {
        return Intrinsics.compare((int) s & UShort.MAX_VALUE, (int) 65535 & s2) < 0 ? s2 : s;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtLeast-J1ME1BU  reason: not valid java name */
    public static final int m852coerceAtLeastJ1ME1BU(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2) < 0 ? i2 : i;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtLeast-Kr8caGY  reason: not valid java name */
    public static final byte m853coerceAtLeastKr8caGY(byte b, byte b2) {
        return Intrinsics.compare((int) b & 255, (int) b2 & 255) < 0 ? b2 : b;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtLeast-eb3DHEI  reason: not valid java name */
    public static final long m854coerceAtLeasteb3DHEI(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2) < 0 ? j2 : j;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtMost-5PvTz6A  reason: not valid java name */
    public static final short m855coerceAtMost5PvTz6A(short s, short s2) {
        return Intrinsics.compare((int) s & UShort.MAX_VALUE, (int) 65535 & s2) > 0 ? s2 : s;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtMost-J1ME1BU  reason: not valid java name */
    public static final int m856coerceAtMostJ1ME1BU(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2) > 0 ? i2 : i;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtMost-Kr8caGY  reason: not valid java name */
    public static final byte m857coerceAtMostKr8caGY(byte b, byte b2) {
        return Intrinsics.compare((int) b & 255, (int) b2 & 255) > 0 ? b2 : b;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtMost-eb3DHEI  reason: not valid java name */
    public static final long m858coerceAtMosteb3DHEI(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2) > 0 ? j2 : j;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [kotlin.ranges.ClosedRange<kotlin.ULong>, java.lang.Object, kotlin.ranges.ClosedRange] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @kotlin.ExperimentalUnsignedTypes
    @kotlin.SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-JPwROB0  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long m859coerceInJPwROB0(long r2, @org.jetbrains.annotations.NotNull kotlin.ranges.ClosedRange<kotlin.ULong> r4) {
        /*
            java.lang.String r0 = "range"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            boolean r0 = r4 instanceof kotlin.ranges.ClosedFloatingPointRange
            if (r0 == 0) goto L_0x001a
            kotlin.ULong r2 = kotlin.ULong.m151boximpl(r2)
            kotlin.ranges.ClosedFloatingPointRange r4 = (kotlin.ranges.ClosedFloatingPointRange) r4
            java.lang.Comparable r2 = kotlin.ranges.RangesKt.coerceIn(r2, r4)
            kotlin.ULong r2 = (kotlin.ULong) r2
            long r2 = r2.m200unboximpl()
            return r2
        L_0x001a:
            boolean r0 = r4.isEmpty()
            if (r0 != 0) goto L_0x0051
            java.lang.Comparable r0 = r4.getStart()
            kotlin.ULong r0 = (kotlin.ULong) r0
            long r0 = r0.m200unboximpl()
            int r0 = kotlin.UnsignedKt.ulongCompare(r2, r0)
            if (r0 >= 0) goto L_0x003b
            java.lang.Comparable r2 = r4.getStart()
        L_0x0034:
            kotlin.ULong r2 = (kotlin.ULong) r2
            long r2 = r2.m200unboximpl()
            goto L_0x0050
        L_0x003b:
            java.lang.Comparable r0 = r4.getEndInclusive()
            kotlin.ULong r0 = (kotlin.ULong) r0
            long r0 = r0.m200unboximpl()
            int r0 = kotlin.UnsignedKt.ulongCompare(r2, r0)
            if (r0 <= 0) goto L_0x0050
            java.lang.Comparable r2 = r4.getEndInclusive()
            goto L_0x0034
        L_0x0050:
            return r2
        L_0x0051:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "Cannot coerce value to an empty range: "
            r3.append(r0)
            r3.append(r4)
            r4 = 46
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.URangesKt___URangesKt.m859coerceInJPwROB0(long, kotlin.ranges.ClosedRange):long");
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-VKSA0NQ  reason: not valid java name */
    public static final short m860coerceInVKSA0NQ(short s, short s2, short s3) {
        short s4 = s2 & UShort.MAX_VALUE;
        short s5 = s3 & UShort.MAX_VALUE;
        if (Intrinsics.compare((int) s4, (int) s5) <= 0) {
            short s6 = 65535 & s;
            return Intrinsics.compare((int) s6, (int) s4) < 0 ? s2 : Intrinsics.compare((int) s6, (int) s5) > 0 ? s3 : s;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UShort.m261toStringimpl(s3) + " is less than minimum " + UShort.m261toStringimpl(s2) + '.');
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-WZ9TVnA  reason: not valid java name */
    public static final int m861coerceInWZ9TVnA(int i, int i2, int i3) {
        if (UnsignedKt.uintCompare(i2, i3) <= 0) {
            return UnsignedKt.uintCompare(i, i2) < 0 ? i2 : UnsignedKt.uintCompare(i, i3) > 0 ? i3 : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UInt.m125toStringimpl(i3) + " is less than minimum " + UInt.m125toStringimpl(i2) + '.');
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-b33U2AM  reason: not valid java name */
    public static final byte m862coerceInb33U2AM(byte b, byte b2, byte b3) {
        byte b4 = b2 & 255;
        byte b5 = b3 & 255;
        if (Intrinsics.compare((int) b4, (int) b5) <= 0) {
            byte b6 = b & 255;
            return Intrinsics.compare((int) b6, (int) b4) < 0 ? b2 : Intrinsics.compare((int) b6, (int) b5) > 0 ? b3 : b;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UByte.m56toStringimpl(b3) + " is less than minimum " + UByte.m56toStringimpl(b2) + '.');
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-sambcqE  reason: not valid java name */
    public static final long m863coerceInsambcqE(long j, long j2, long j3) {
        if (UnsignedKt.ulongCompare(j2, j3) <= 0) {
            return UnsignedKt.ulongCompare(j, j2) < 0 ? j2 : UnsignedKt.ulongCompare(j, j3) > 0 ? j3 : j;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ULong.m194toStringimpl(j3) + " is less than minimum " + ULong.m194toStringimpl(j2) + '.');
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [kotlin.ranges.ClosedRange<kotlin.UInt>, java.lang.Object, kotlin.ranges.ClosedRange] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @kotlin.ExperimentalUnsignedTypes
    @kotlin.SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-wuiCnnA  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int m864coerceInwuiCnnA(int r2, @org.jetbrains.annotations.NotNull kotlin.ranges.ClosedRange<kotlin.UInt> r3) {
        /*
            java.lang.String r0 = "range"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            boolean r0 = r3 instanceof kotlin.ranges.ClosedFloatingPointRange
            if (r0 == 0) goto L_0x001a
            kotlin.UInt r2 = kotlin.UInt.m82boximpl(r2)
            kotlin.ranges.ClosedFloatingPointRange r3 = (kotlin.ranges.ClosedFloatingPointRange) r3
            java.lang.Comparable r2 = kotlin.ranges.RangesKt.coerceIn(r2, r3)
            kotlin.UInt r2 = (kotlin.UInt) r2
            int r2 = r2.m131unboximpl()
            return r2
        L_0x001a:
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L_0x0051
            java.lang.Comparable r0 = r3.getStart()
            kotlin.UInt r0 = (kotlin.UInt) r0
            int r0 = r0.m131unboximpl()
            int r0 = kotlin.UnsignedKt.uintCompare(r2, r0)
            if (r0 >= 0) goto L_0x003b
            java.lang.Comparable r2 = r3.getStart()
        L_0x0034:
            kotlin.UInt r2 = (kotlin.UInt) r2
            int r2 = r2.m131unboximpl()
            goto L_0x0050
        L_0x003b:
            java.lang.Comparable r0 = r3.getEndInclusive()
            kotlin.UInt r0 = (kotlin.UInt) r0
            int r0 = r0.m131unboximpl()
            int r0 = kotlin.UnsignedKt.uintCompare(r2, r0)
            if (r0 <= 0) goto L_0x0050
            java.lang.Comparable r2 = r3.getEndInclusive()
            goto L_0x0034
        L_0x0050:
            return r2
        L_0x0051:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Cannot coerce value to an empty range: "
            r0.append(r1)
            r0.append(r3)
            r3 = 46
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.URangesKt___URangesKt.m864coerceInwuiCnnA(int, kotlin.ranges.ClosedRange):int");
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-68kG9v0  reason: not valid java name */
    public static final boolean m865contains68kG9v0(@NotNull UIntRange uIntRange, byte b) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$contains");
        return uIntRange.m848containsWZ4Q5Ns(UInt.m88constructorimpl(b & 255));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: contains-GYNo2lE  reason: not valid java name */
    private static final boolean m866containsGYNo2lE(@NotNull ULongRange uLongRange, ULong uLong) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$contains");
        return uLong != null && uLongRange.m850containsVKZWuLQ(uLong.m200unboximpl());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-Gab390E  reason: not valid java name */
    public static final boolean m867containsGab390E(@NotNull ULongRange uLongRange, int i) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$contains");
        return uLongRange.m850containsVKZWuLQ(ULong.m157constructorimpl(((long) i) & 4294967295L));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-ULb-yJY  reason: not valid java name */
    public static final boolean m868containsULbyJY(@NotNull ULongRange uLongRange, byte b) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$contains");
        return uLongRange.m850containsVKZWuLQ(ULong.m157constructorimpl(((long) b) & 255));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-ZsK3CEQ  reason: not valid java name */
    public static final boolean m869containsZsK3CEQ(@NotNull UIntRange uIntRange, short s) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$contains");
        return uIntRange.m848containsWZ4Q5Ns(UInt.m88constructorimpl(s & UShort.MAX_VALUE));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: contains-biwQdVI  reason: not valid java name */
    private static final boolean m870containsbiwQdVI(@NotNull UIntRange uIntRange, UInt uInt) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$contains");
        return uInt != null && uIntRange.m848containsWZ4Q5Ns(uInt.m131unboximpl());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-fz5IDCE  reason: not valid java name */
    public static final boolean m871containsfz5IDCE(@NotNull UIntRange uIntRange, long j) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$contains");
        return ULong.m157constructorimpl(j >>> 32) == 0 && uIntRange.m848containsWZ4Q5Ns(UInt.m88constructorimpl((int) j));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-uhHAxoY  reason: not valid java name */
    public static final boolean m872containsuhHAxoY(@NotNull ULongRange uLongRange, short s) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$contains");
        return uLongRange.m850containsVKZWuLQ(ULong.m157constructorimpl(((long) s) & 65535));
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-5PvTz6A  reason: not valid java name */
    public static final UIntProgression m873downTo5PvTz6A(short s, short s2) {
        return UIntProgression.Companion.m847fromClosedRangeNkh28Cs(UInt.m88constructorimpl(s & UShort.MAX_VALUE), UInt.m88constructorimpl(s2 & UShort.MAX_VALUE), -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-J1ME1BU  reason: not valid java name */
    public static final UIntProgression m874downToJ1ME1BU(int i, int i2) {
        return UIntProgression.Companion.m847fromClosedRangeNkh28Cs(i, i2, -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-Kr8caGY  reason: not valid java name */
    public static final UIntProgression m875downToKr8caGY(byte b, byte b2) {
        return UIntProgression.Companion.m847fromClosedRangeNkh28Cs(UInt.m88constructorimpl(b & 255), UInt.m88constructorimpl(b2 & 255), -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-eb3DHEI  reason: not valid java name */
    public static final ULongProgression m876downToeb3DHEI(long j, long j2) {
        return ULongProgression.Companion.m849fromClosedRange7ftBX0g(j, j2, -1);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int random(@NotNull UIntRange uIntRange) {
        return URangesKt.random(uIntRange, (Random) Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int random(@NotNull UIntRange uIntRange, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$random");
        Intrinsics.checkParameterIsNotNull(random, "random");
        try {
            return URandomKt.nextUInt(random, uIntRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long random(@NotNull ULongRange uLongRange) {
        return URangesKt.random(uLongRange, (Random) Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long random(@NotNull ULongRange uLongRange, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$random");
        Intrinsics.checkParameterIsNotNull(random, "random");
        try {
            return URandomKt.nextULong(random, uLongRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression reversed(@NotNull UIntProgression uIntProgression) {
        Intrinsics.checkParameterIsNotNull(uIntProgression, "$this$reversed");
        return UIntProgression.Companion.m847fromClosedRangeNkh28Cs(uIntProgression.getLast(), uIntProgression.getFirst(), -uIntProgression.getStep());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongProgression reversed(@NotNull ULongProgression uLongProgression) {
        Intrinsics.checkParameterIsNotNull(uLongProgression, "$this$reversed");
        return ULongProgression.Companion.m849fromClosedRange7ftBX0g(uLongProgression.getLast(), uLongProgression.getFirst(), -uLongProgression.getStep());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression step(@NotNull UIntProgression uIntProgression, int i) {
        Intrinsics.checkParameterIsNotNull(uIntProgression, "$this$step");
        RangesKt.checkStepIsPositive(i > 0, Integer.valueOf(i));
        UIntProgression.Companion companion = UIntProgression.Companion;
        int first = uIntProgression.getFirst();
        int last = uIntProgression.getLast();
        if (uIntProgression.getStep() <= 0) {
            i = -i;
        }
        return companion.m847fromClosedRangeNkh28Cs(first, last, i);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongProgression step(@NotNull ULongProgression uLongProgression, long j) {
        Intrinsics.checkParameterIsNotNull(uLongProgression, "$this$step");
        RangesKt.checkStepIsPositive(j > 0, Long.valueOf(j));
        ULongProgression.Companion companion = ULongProgression.Companion;
        long first = uLongProgression.getFirst();
        long last = uLongProgression.getLast();
        if (uLongProgression.getStep() <= 0) {
            j = -j;
        }
        return companion.m849fromClosedRange7ftBX0g(first, last, j);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-5PvTz6A  reason: not valid java name */
    public static final UIntRange m877until5PvTz6A(short s, short s2) {
        short s3 = s2 & UShort.MAX_VALUE;
        return Intrinsics.compare((int) s3, 0) <= 0 ? UIntRange.Companion.getEMPTY() : new UIntRange(UInt.m88constructorimpl(s & UShort.MAX_VALUE), UInt.m88constructorimpl(UInt.m88constructorimpl(s3) - 1), (DefaultConstructorMarker) null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-J1ME1BU  reason: not valid java name */
    public static final UIntRange m878untilJ1ME1BU(int i, int i2) {
        return UnsignedKt.uintCompare(i2, 0) <= 0 ? UIntRange.Companion.getEMPTY() : new UIntRange(i, UInt.m88constructorimpl(i2 - 1), (DefaultConstructorMarker) null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-Kr8caGY  reason: not valid java name */
    public static final UIntRange m879untilKr8caGY(byte b, byte b2) {
        byte b3 = b2 & 255;
        return Intrinsics.compare((int) b3, 0) <= 0 ? UIntRange.Companion.getEMPTY() : new UIntRange(UInt.m88constructorimpl(b & 255), UInt.m88constructorimpl(UInt.m88constructorimpl(b3) - 1), (DefaultConstructorMarker) null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-eb3DHEI  reason: not valid java name */
    public static final ULongRange m880untileb3DHEI(long j, long j2) {
        if (UnsignedKt.ulongCompare(j2, 0) <= 0) {
            return ULongRange.Companion.getEMPTY();
        }
        return new ULongRange(j, ULong.m157constructorimpl(j2 - ULong.m157constructorimpl(((long) 1) & 4294967295L)), (DefaultConstructorMarker) null);
    }
}
