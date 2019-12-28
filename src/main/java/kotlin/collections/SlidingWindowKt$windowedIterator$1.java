package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
@DebugMetadata(c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4}, l = {33, 39, 46, 52, 55}, m = "invokeSuspend", n = {"gap", "buffer", "skip", "e", "gap", "buffer", "skip", "gap", "buffer", "e", "gap", "buffer", "gap", "buffer"}, s = {"I$0", "L$1", "I$1", "L$2", "I$0", "L$0", "I$1", "I$0", "L$1", "L$2", "I$0", "L$1", "I$0", "L$0"})
final class SlidingWindowKt$windowedIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    Object a;
    Object b;
    Object c;
    Object d;
    int e;
    int f;
    int g;
    final /* synthetic */ int h;
    final /* synthetic */ int i;
    final /* synthetic */ Iterator j;
    final /* synthetic */ boolean k;
    final /* synthetic */ boolean l;
    private SequenceScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SlidingWindowKt$windowedIterator$1(int i2, int i3, Iterator it, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.h = i2;
        this.i = i3;
        this.j = it;
        this.k = z;
        this.l = z2;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.h, this.i, this.j, this.k, this.l, continuation);
        slidingWindowKt$windowedIterator$1.p$ = (SequenceScope) obj;
        return slidingWindowKt$windowedIterator$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SlidingWindowKt$windowedIterator$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0081, code lost:
        if (r1.hasNext() == false) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0083, code lost:
        r7 = r1.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0087, code lost:
        if (r12 <= 0) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0089, code lost:
        r12 = r12 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008c, code lost:
        r3.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0095, code lost:
        if (r3.size() != r0.i) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0097, code lost:
        r0.a = r5;
        r0.e = r4;
        r0.b = r3;
        r0.f = r12;
        r0.c = r7;
        r0.d = r1;
        r0.g = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a9, code lost:
        if (r5.yield(r3, r0) != r6) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ab, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00ae, code lost:
        if (r0.k == false) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b0, code lost:
        r3.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00b4, code lost:
        r3 = new java.util.ArrayList(r0.i);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00bc, code lost:
        r12 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c6, code lost:
        if ((!r3.isEmpty()) == false) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ca, code lost:
        if (r0.l != false) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d2, code lost:
        if (r3.size() != r0.i) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d4, code lost:
        r0.e = r4;
        r0.a = r3;
        r0.f = r12;
        r0.g = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e1, code lost:
        if (r5.yield(r3, r0) != r6) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00e3, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f5, code lost:
        if (r1.hasNext() == false) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f7, code lost:
        r6 = r1.next();
        r3.add(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0102, code lost:
        if (r3.isFull() == false) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0106, code lost:
        if (r12.k == false) goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0108, code lost:
        r7 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x010c, code lost:
        r7 = new java.util.ArrayList(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0116, code lost:
        r12.a = r5;
        r12.e = r4;
        r12.b = r3;
        r12.c = r6;
        r12.d = r1;
        r12.g = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0127, code lost:
        if (r5.yield(r7, r12) != r0) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0129, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x012a, code lost:
        r3.removeFirst(r12.h);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0132, code lost:
        if (r12.l == false) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0134, code lost:
        r1 = r3;
        r3 = r4;
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x013d, code lost:
        if (r1.size() <= r12.h) goto L_0x0167;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0141, code lost:
        if (r12.k == false) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0143, code lost:
        r5 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0147, code lost:
        r5 = new java.util.ArrayList(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0151, code lost:
        r12.a = r4;
        r12.e = r3;
        r12.b = r1;
        r12.g = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x015e, code lost:
        if (r4.yield(r5, r12) != r0) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0160, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0161, code lost:
        r1.removeFirst(r12.h);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x016f, code lost:
        if ((true ^ r1.isEmpty()) == false) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0171, code lost:
        r12.e = r3;
        r12.a = r1;
        r12.g = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x017c, code lost:
        if (r4.yield(r1, r12) != r0) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x017e, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0181, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0043, code lost:
        r0 = r11.e;
        kotlin.ResultKt.throwOnFailure(r12);
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.g
            r2 = 1
            switch(r1) {
                case 0: goto L_0x0062;
                case 1: goto L_0x004a;
                case 2: goto L_0x003d;
                case 3: goto L_0x0027;
                case 4: goto L_0x0017;
                case 5: goto L_0x0012;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0012:
            java.lang.Object r0 = r11.a
            kotlin.collections.RingBuffer r0 = (kotlin.collections.RingBuffer) r0
            goto L_0x0043
        L_0x0017:
            java.lang.Object r1 = r11.b
            kotlin.collections.RingBuffer r1 = (kotlin.collections.RingBuffer) r1
            int r3 = r11.e
            java.lang.Object r4 = r11.a
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r11
            goto L_0x0161
        L_0x0027:
            java.lang.Object r1 = r11.d
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r3 = r11.c
            java.lang.Object r3 = r11.b
            kotlin.collections.RingBuffer r3 = (kotlin.collections.RingBuffer) r3
            int r4 = r11.e
            java.lang.Object r5 = r11.a
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r11
            goto L_0x012a
        L_0x003d:
            int r0 = r11.f
            java.lang.Object r0 = r11.a
            java.util.ArrayList r0 = (java.util.ArrayList) r0
        L_0x0043:
            int r0 = r11.e
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x017f
        L_0x004a:
            java.lang.Object r1 = r11.d
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r3 = r11.c
            int r3 = r11.f
            java.lang.Object r3 = r11.b
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r4 = r11.e
            java.lang.Object r5 = r11.a
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            kotlin.ResultKt.throwOnFailure(r12)
            r6 = r0
            r0 = r11
            goto L_0x00ac
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.sequences.SequenceScope r12 = r11.p$
            int r1 = r11.h
            int r3 = r11.i
            int r1 = r1 - r3
            if (r1 < 0) goto L_0x00e4
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
            r3 = 0
            java.util.Iterator r5 = r11.j
            r6 = r0
            r3 = r4
            r0 = r11
            r4 = r1
            r1 = r5
            r5 = r12
            r12 = 0
        L_0x007d:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x00be
            java.lang.Object r7 = r1.next()
            if (r12 <= 0) goto L_0x008c
            int r12 = r12 + -1
            goto L_0x007d
        L_0x008c:
            r3.add(r7)
            int r8 = r3.size()
            int r9 = r0.i
            if (r8 != r9) goto L_0x007d
            r0.a = r5
            r0.e = r4
            r0.b = r3
            r0.f = r12
            r0.c = r7
            r0.d = r1
            r0.g = r2
            java.lang.Object r12 = r5.yield(r3, r0)
            if (r12 != r6) goto L_0x00ac
            return r6
        L_0x00ac:
            boolean r12 = r0.k
            if (r12 == 0) goto L_0x00b4
            r3.clear()
            goto L_0x00bc
        L_0x00b4:
            java.util.ArrayList r12 = new java.util.ArrayList
            int r3 = r0.i
            r12.<init>(r3)
            r3 = r12
        L_0x00bc:
            r12 = r4
            goto L_0x007d
        L_0x00be:
            r1 = r3
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ r2
            if (r1 == 0) goto L_0x017f
            boolean r1 = r0.l
            if (r1 != 0) goto L_0x00d4
            int r1 = r3.size()
            int r2 = r0.i
            if (r1 != r2) goto L_0x017f
        L_0x00d4:
            r0.e = r4
            r0.a = r3
            r0.f = r12
            r12 = 2
            r0.g = r12
            java.lang.Object r12 = r5.yield(r3, r0)
            if (r12 != r6) goto L_0x017f
            return r6
        L_0x00e4:
            kotlin.collections.RingBuffer r4 = new kotlin.collections.RingBuffer
            r4.<init>(r3)
            java.util.Iterator r3 = r11.j
            r5 = r12
            r12 = r11
            r10 = r4
            r4 = r1
            r1 = r3
            r3 = r10
        L_0x00f1:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x0130
            java.lang.Object r6 = r1.next()
            r3.add(r6)
            boolean r7 = r3.isFull()
            if (r7 == 0) goto L_0x00f1
            boolean r7 = r12.k
            if (r7 == 0) goto L_0x010c
            r7 = r3
            java.util.List r7 = (java.util.List) r7
            goto L_0x0116
        L_0x010c:
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = r3
            java.util.Collection r8 = (java.util.Collection) r8
            r7.<init>(r8)
            java.util.List r7 = (java.util.List) r7
        L_0x0116:
            r12.a = r5
            r12.e = r4
            r12.b = r3
            r12.c = r6
            r12.d = r1
            r6 = 3
            r12.g = r6
            java.lang.Object r6 = r5.yield(r7, r12)
            if (r6 != r0) goto L_0x012a
            return r0
        L_0x012a:
            int r6 = r12.h
            r3.removeFirst(r6)
            goto L_0x00f1
        L_0x0130:
            boolean r1 = r12.l
            if (r1 == 0) goto L_0x017f
            r1 = r3
            r3 = r4
            r4 = r5
        L_0x0137:
            int r5 = r1.size()
            int r6 = r12.h
            if (r5 <= r6) goto L_0x0167
            boolean r5 = r12.k
            if (r5 == 0) goto L_0x0147
            r5 = r1
            java.util.List r5 = (java.util.List) r5
            goto L_0x0151
        L_0x0147:
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = r1
            java.util.Collection r6 = (java.util.Collection) r6
            r5.<init>(r6)
            java.util.List r5 = (java.util.List) r5
        L_0x0151:
            r12.a = r4
            r12.e = r3
            r12.b = r1
            r6 = 4
            r12.g = r6
            java.lang.Object r5 = r4.yield(r5, r12)
            if (r5 != r0) goto L_0x0161
            return r0
        L_0x0161:
            int r5 = r12.h
            r1.removeFirst(r5)
            goto L_0x0137
        L_0x0167:
            r5 = r1
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            r2 = r2 ^ r5
            if (r2 == 0) goto L_0x017f
            r12.e = r3
            r12.a = r1
            r2 = 5
            r12.g = r2
            java.lang.Object r12 = r4.yield(r1, r12)
            if (r12 != r0) goto L_0x017f
            return r0
        L_0x017f:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
