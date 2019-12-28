package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "R", "index", "", "invoke", "(I)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
final class StringsKt___StringsKt$windowedSequence$2 extends Lambda implements Function1<Integer, R> {
    final /* synthetic */ CharSequence a;
    final /* synthetic */ Function1 b;
    final /* synthetic */ int c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StringsKt___StringsKt$windowedSequence$2(CharSequence charSequence, Function1 function1, int i) {
        super(1);
        this.a = charSequence;
        this.b = function1;
        this.c = i;
    }

    public final R invoke(int i) {
        Function1 function1 = this.b;
        CharSequence charSequence = this.a;
        return function1.invoke(charSequence.subSequence(i, RangesKt.coerceAtMost(this.c + i, charSequence.length())));
    }

    public /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
