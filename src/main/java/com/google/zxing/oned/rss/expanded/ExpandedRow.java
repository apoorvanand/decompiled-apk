package com.google.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;

final class ExpandedRow {
    private final List<ExpandedPair> pairs;
    private final int rowNumber;
    private final boolean wasReversed;

    ExpandedRow(List<ExpandedPair> list, int i, boolean z) {
        this.pairs = new ArrayList(list);
        this.rowNumber = i;
        this.wasReversed = z;
    }

    /* access modifiers changed from: package-private */
    public List<ExpandedPair> a() {
        return this.pairs;
    }

    /* access modifiers changed from: package-private */
    public boolean a(List<ExpandedPair> list) {
        return this.pairs.equals(list);
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.rowNumber;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandedRow)) {
            return false;
        }
        ExpandedRow expandedRow = (ExpandedRow) obj;
        return this.pairs.equals(expandedRow.a()) && this.wasReversed == expandedRow.wasReversed;
    }

    public int hashCode() {
        return this.pairs.hashCode() ^ Boolean.valueOf(this.wasReversed).hashCode();
    }

    public String toString() {
        return "{ " + this.pairs + " }";
    }
}
