package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\b\u001a&\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\b\u001a5\u0010\n\u001a\u0002H\u000b\"\f\b\u0000\u0010\u000b*\u00060\fj\u0002`\r*\u0002H\u000b2\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00100\u000f\"\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00120\u000f\"\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0014\u001a9\u0010\u0015\u001a\u00020\b\"\u0004\b\u0000\u0010\u000b*\u00060\fj\u0002`\r2\u0006\u0010\u0016\u001a\u0002H\u000b2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0005H\u0000¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"buildString", "", "capacity", "", "builderAction", "Lkotlin/Function1;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "append", "T", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "appendElement", "element", "transform", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, xi = 1, xs = "kotlin/text/StringsKt")
class StringsKt__StringBuilderKt extends StringsKt__StringBuilderJVMKt {
    @NotNull
    public static final <T extends Appendable> T append(@NotNull T t, @NotNull CharSequence... charSequenceArr) {
        Intrinsics.checkParameterIsNotNull(t, "$this$append");
        Intrinsics.checkParameterIsNotNull(charSequenceArr, "value");
        for (CharSequence append : charSequenceArr) {
            t.append(append);
        }
        return t;
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder sb, @NotNull Object... objArr) {
        Intrinsics.checkParameterIsNotNull(sb, "$this$append");
        Intrinsics.checkParameterIsNotNull(objArr, "value");
        for (Object append : objArr) {
            sb.append(append);
        }
        return sb;
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder sb, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(sb, "$this$append");
        Intrinsics.checkParameterIsNotNull(strArr, "value");
        for (String append : strArr) {
            sb.append(append);
        }
        return sb;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [kotlin.jvm.functions.Function1<? super T, ? extends java.lang.CharSequence>, kotlin.jvm.functions.Function1] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> void appendElement(@org.jetbrains.annotations.NotNull java.lang.Appendable r1, T r2, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function1<? super T, ? extends java.lang.CharSequence> r3) {
        /*
            java.lang.String r0 = "$this$appendElement"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r0)
            if (r3 == 0) goto L_0x0011
            java.lang.Object r2 = r3.invoke(r2)
        L_0x000b:
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.append(r2)
            goto L_0x002d
        L_0x0011:
            if (r2 == 0) goto L_0x0016
            boolean r3 = r2 instanceof java.lang.CharSequence
            goto L_0x0017
        L_0x0016:
            r3 = 1
        L_0x0017:
            if (r3 == 0) goto L_0x001a
            goto L_0x000b
        L_0x001a:
            boolean r3 = r2 instanceof java.lang.Character
            if (r3 == 0) goto L_0x0028
            java.lang.Character r2 = (java.lang.Character) r2
            char r2 = r2.charValue()
            r1.append(r2)
            goto L_0x002d
        L_0x0028:
            java.lang.String r2 = java.lang.String.valueOf(r2)
            goto L_0x000b
        L_0x002d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringBuilderKt.appendElement(java.lang.Appendable, java.lang.Object, kotlin.jvm.functions.Function1):void");
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String buildString(int i, Function1<? super StringBuilder, Unit> function1) {
        StringBuilder sb = new StringBuilder(i);
        function1.invoke(sb);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    @InlineOnly
    private static final String buildString(Function1<? super StringBuilder, Unit> function1) {
        StringBuilder sb = new StringBuilder();
        function1.invoke(sb);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
