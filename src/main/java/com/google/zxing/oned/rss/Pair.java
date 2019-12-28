package com.google.zxing.oned.rss;

final class Pair extends DataCharacter {
    private int count;
    private final FinderPattern finderPattern;

    Pair(int i, int i2, FinderPattern finderPattern2) {
        super(i, i2);
        this.finderPattern = finderPattern2;
    }

    /* access modifiers changed from: package-private */
    public FinderPattern a() {
        return this.finderPattern;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.count;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.count++;
    }
}
