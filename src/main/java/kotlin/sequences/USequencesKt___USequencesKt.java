package kotlin.sequences;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"sum", "Lkotlin/UInt;", "Lkotlin/sequences/Sequence;", "Lkotlin/UByte;", "sumOfUByte", "(Lkotlin/sequences/Sequence;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, xi = 1, xs = "kotlin/sequences/USequencesKt")
class USequencesKt___USequencesKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@NotNull Sequence<UByte> sequence) {
        Intrinsics.checkParameterIsNotNull(sequence, "$this$sum");
        int i = 0;
        for (UByte r1 : sequence) {
            i = UInt.m88constructorimpl(i + UInt.m88constructorimpl(r1.m62unboximpl() & 255));
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull Sequence<UInt> sequence) {
        Intrinsics.checkParameterIsNotNull(sequence, "$this$sum");
        int i = 0;
        for (UInt r1 : sequence) {
            i = UInt.m88constructorimpl(i + r1.m131unboximpl());
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@NotNull Sequence<ULong> sequence) {
        Intrinsics.checkParameterIsNotNull(sequence, "$this$sum");
        long j = 0;
        for (ULong r2 : sequence) {
            j = ULong.m157constructorimpl(j + r2.m200unboximpl());
        }
        return j;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@NotNull Sequence<UShort> sequence) {
        Intrinsics.checkParameterIsNotNull(sequence, "$this$sum");
        int i = 0;
        for (UShort r1 : sequence) {
            i = UInt.m88constructorimpl(i + UInt.m88constructorimpl(r1.m267unboximpl() & UShort.MAX_VALUE));
        }
        return i;
    }
}