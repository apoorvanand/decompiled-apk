package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "line", "invoke"}, k = 3, mv = {1, 1, 15})
final class StringsKt__IndentKt$getIndentFunction$2 extends Lambda implements Function1<String, String> {
    final /* synthetic */ String a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StringsKt__IndentKt$getIndentFunction$2(String str) {
        super(1);
        this.a = str;
    }

    @NotNull
    public final String invoke(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "line");
        return this.a + str;
    }
}
