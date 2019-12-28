package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2", f = "_Sequences.kt", i = {0, 0, 0}, l = {1697}, m = "invokeSuspend", n = {"iterator", "current", "next"}, s = {"L$1", "L$2", "L$3"})
final class SequencesKt___SequencesKt$zipWithNext$2 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    Object a;
    Object b;
    Object c;
    Object d;
    int e;
    final /* synthetic */ Sequence f;
    final /* synthetic */ Function2 g;
    private SequenceScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$zipWithNext$2(Sequence sequence, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.f = sequence;
        this.g = function2;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SequencesKt___SequencesKt$zipWithNext$2 sequencesKt___SequencesKt$zipWithNext$2 = new SequencesKt___SequencesKt$zipWithNext$2(this.f, this.g, continuation);
        sequencesKt___SequencesKt$zipWithNext$2.p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$zipWithNext$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SequencesKt___SequencesKt$zipWithNext$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        SequencesKt___SequencesKt$zipWithNext$2 sequencesKt___SequencesKt$zipWithNext$2;
        SequenceScope sequenceScope;
        Iterator it;
        Object obj2;
        Object obj3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.e) {
            case 0:
                ResultKt.throwOnFailure(obj);
                SequenceScope sequenceScope2 = this.p$;
                Iterator it2 = this.f.iterator();
                if (it2.hasNext()) {
                    Object next = it2.next();
                    sequenceScope = sequenceScope2;
                    sequencesKt___SequencesKt$zipWithNext$2 = this;
                    Iterator it3 = it2;
                    obj2 = coroutine_suspended;
                    obj3 = next;
                    it = it3;
                    break;
                } else {
                    return Unit.INSTANCE;
                }
            case 1:
                Object obj4 = this.d;
                Object obj5 = this.c;
                it = (Iterator) this.b;
                sequenceScope = (SequenceScope) this.a;
                ResultKt.throwOnFailure(obj);
                sequencesKt___SequencesKt$zipWithNext$2 = this;
                Object obj6 = obj4;
                obj2 = coroutine_suspended;
                obj3 = obj6;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            Object next2 = it.next();
            Object invoke = sequencesKt___SequencesKt$zipWithNext$2.g.invoke(obj3, next2);
            sequencesKt___SequencesKt$zipWithNext$2.a = sequenceScope;
            sequencesKt___SequencesKt$zipWithNext$2.b = it;
            sequencesKt___SequencesKt$zipWithNext$2.c = obj3;
            sequencesKt___SequencesKt$zipWithNext$2.d = next2;
            sequencesKt___SequencesKt$zipWithNext$2.e = 1;
            if (sequenceScope.yield(invoke, sequencesKt___SequencesKt$zipWithNext$2) == obj2) {
                return obj2;
            }
            obj3 = next2;
        }
        return Unit.INSTANCE;
    }
}
