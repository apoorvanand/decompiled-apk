package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/collections/CharIterator;", "invoke"}, k = 3, mv = {1, 1, 15})
final class ArraysKt___ArraysKt$withIndex$9 extends Lambda implements Function0<CharIterator> {
    final /* synthetic */ char[] a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArraysKt___ArraysKt$withIndex$9(char[] cArr) {
        super(0);
        this.a = cArr;
    }

    @NotNull
    public final CharIterator invoke() {
        return ArrayIteratorsKt.iterator(this.a);
    }
}
