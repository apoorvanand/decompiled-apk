package io.flutter.plugin.common;

import java.nio.ByteBuffer;

public final class BinaryCodec implements MessageCodec<ByteBuffer> {
    public static final BinaryCodec INSTANCE = new BinaryCodec();

    private BinaryCodec() {
    }

    public ByteBuffer decodeMessage(ByteBuffer byteBuffer) {
        return byteBuffer;
    }

    public ByteBuffer encodeMessage(ByteBuffer byteBuffer) {
        return byteBuffer;
    }
}
