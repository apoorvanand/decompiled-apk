package com.google.zxing.oned;

public abstract class UPCEANWriter extends OneDimensionalCodeWriter {
    public int getDefaultMargin() {
        return UPCEANReader.b.length;
    }
}
