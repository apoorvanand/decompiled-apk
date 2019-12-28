package io.flutter.embedding.engine.dart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import io.flutter.Log;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.plugin.common.BinaryMessenger;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

class DartMessenger implements PlatformMessageHandler, BinaryMessenger {
    private static final String TAG = "DartMessenger";
    @NonNull
    private final FlutterJNI flutterJNI;
    @NonNull
    private final Map<String, BinaryMessenger.BinaryMessageHandler> messageHandlers;
    private int nextReplyId = 1;
    @NonNull
    private final Map<Integer, BinaryMessenger.BinaryReply> pendingReplies;

    private static class Reply implements BinaryMessenger.BinaryReply {
        private final AtomicBoolean done = new AtomicBoolean(false);
        @NonNull
        private final FlutterJNI flutterJNI;
        private final int replyId;

        Reply(@NonNull FlutterJNI flutterJNI2, int i) {
            this.flutterJNI = flutterJNI2;
            this.replyId = i;
        }

        public void reply(@Nullable ByteBuffer byteBuffer) {
            if (this.done.getAndSet(true)) {
                throw new IllegalStateException("Reply already submitted");
            } else if (byteBuffer == null) {
                this.flutterJNI.invokePlatformMessageEmptyResponseCallback(this.replyId);
            } else {
                this.flutterJNI.invokePlatformMessageResponseCallback(this.replyId, byteBuffer, byteBuffer.position());
            }
        }
    }

    DartMessenger(@NonNull FlutterJNI flutterJNI2) {
        this.flutterJNI = flutterJNI2;
        this.messageHandlers = new HashMap();
        this.pendingReplies = new HashMap();
    }

    @UiThread
    public int getPendingChannelResponseCount() {
        return this.pendingReplies.size();
    }

    public void handleMessageFromDart(@NonNull String str, @Nullable byte[] bArr, int i) {
        Log.v(TAG, "Received message from Dart over channel '" + str + "'");
        BinaryMessenger.BinaryMessageHandler binaryMessageHandler = this.messageHandlers.get(str);
        if (binaryMessageHandler != null) {
            try {
                Log.v(TAG, "Deferring to registered handler to process message.");
                binaryMessageHandler.onMessage(bArr == null ? null : ByteBuffer.wrap(bArr), new Reply(this.flutterJNI, i));
            } catch (Exception e) {
                Log.e(TAG, "Uncaught exception in binary message listener", e);
            }
        } else {
            Log.v(TAG, "No registered handler for message. Responding to Dart with empty reply message.");
            this.flutterJNI.invokePlatformMessageEmptyResponseCallback(i);
        }
    }

    public void handlePlatformMessageResponse(int i, @Nullable byte[] bArr) {
        Log.v(TAG, "Received message reply from Dart.");
        BinaryMessenger.BinaryReply remove = this.pendingReplies.remove(Integer.valueOf(i));
        if (remove != null) {
            try {
                Log.v(TAG, "Invoking registered callback for reply from Dart.");
                remove.reply(bArr == null ? null : ByteBuffer.wrap(bArr));
            } catch (Exception e) {
                Log.e(TAG, "Uncaught exception in binary message reply handler", e);
            }
        }
    }

    @UiThread
    public void send(@NonNull String str, @NonNull ByteBuffer byteBuffer) {
        Log.v(TAG, "Sending message over channel '" + str + "'");
        send(str, byteBuffer, (BinaryMessenger.BinaryReply) null);
    }

    public void send(@NonNull String str, @Nullable ByteBuffer byteBuffer, @Nullable BinaryMessenger.BinaryReply binaryReply) {
        int i;
        Log.v(TAG, "Sending message with callback over channel '" + str + "'");
        if (binaryReply != null) {
            i = this.nextReplyId;
            this.nextReplyId = i + 1;
            this.pendingReplies.put(Integer.valueOf(i), binaryReply);
        } else {
            i = 0;
        }
        if (byteBuffer == null) {
            this.flutterJNI.dispatchEmptyPlatformMessage(str, i);
        } else {
            this.flutterJNI.dispatchPlatformMessage(str, byteBuffer, byteBuffer.position(), i);
        }
    }

    public void setMessageHandler(@NonNull String str, @Nullable BinaryMessenger.BinaryMessageHandler binaryMessageHandler) {
        if (binaryMessageHandler == null) {
            Log.v(TAG, "Removing handler for channel '" + str + "'");
            this.messageHandlers.remove(str);
            return;
        }
        Log.v(TAG, "Setting handler for channel '" + str + "'");
        this.messageHandlers.put(str, binaryMessageHandler);
    }
}
