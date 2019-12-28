package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/text/MatchResult;", "invoke"}, k = 3, mv = {1, 1, 15})
final class Regex$findAll$1 extends Lambda implements Function0<MatchResult> {
    final /* synthetic */ Regex a;
    final /* synthetic */ CharSequence b;
    final /* synthetic */ int c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Regex$findAll$1(Regex regex, CharSequence charSequence, int i) {
        super(0);
        this.a = regex;
        this.b = charSequence;
        this.c = i;
    }

    @Nullable
    public final MatchResult invoke() {
        return this.a.find(this.b, this.c);
    }
}
