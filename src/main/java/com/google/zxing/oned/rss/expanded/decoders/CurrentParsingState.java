package com.google.zxing.oned.rss.expanded.decoders;

final class CurrentParsingState {
    private State encoding = State.NUMERIC;
    private int position = 0;

    private enum State {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    CurrentParsingState() {
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.position;
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        this.position = i;
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        this.position += i;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.encoding == State.ALPHA;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.encoding == State.ISO_IEC_646;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.encoding = State.NUMERIC;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.encoding = State.ALPHA;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.encoding = State.ISO_IEC_646;
    }
}
