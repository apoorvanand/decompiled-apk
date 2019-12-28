package com.google.zxing.oned.rss.expanded.decoders;

final class BlockParsedResult {
    private final DecodedInformation decodedInformation;
    private final boolean finished;

    BlockParsedResult(DecodedInformation decodedInformation2, boolean z) {
        this.finished = z;
        this.decodedInformation = decodedInformation2;
    }

    BlockParsedResult(boolean z) {
        this((DecodedInformation) null, z);
    }

    /* access modifiers changed from: package-private */
    public DecodedInformation a() {
        return this.decodedInformation;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.finished;
    }
}
