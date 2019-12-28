package io.flutter.embedding.engine.dart;

import android.content.res.AssetManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import io.flutter.Log;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StringCodec;
import io.flutter.view.FlutterCallbackInformation;
import io.flutter.view.FlutterMain;
import java.nio.ByteBuffer;

public class DartExecutor implements BinaryMessenger {
    private static final String TAG = "DartExecutor";
    @NonNull
    private final AssetManager assetManager;
    @NonNull
    private final FlutterJNI flutterJNI;
    private boolean isApplicationRunning = false;
    private final BinaryMessenger.BinaryMessageHandler isolateChannelMessageHandler = new BinaryMessenger.BinaryMessageHandler() {
        public void onMessage(ByteBuffer byteBuffer, BinaryMessenger.BinaryReply binaryReply) {
            String unused = DartExecutor.this.isolateServiceId = StringCodec.INSTANCE.decodeMessage(byteBuffer);
            if (DartExecutor.this.isolateServiceIdListener != null) {
                DartExecutor.this.isolateServiceIdListener.onIsolateServiceIdAvailable(DartExecutor.this.isolateServiceId);
            }
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public String isolateServiceId;
    /* access modifiers changed from: private */
    @Nullable
    public IsolateServiceIdListener isolateServiceIdListener;
    @NonNull
    private final DartMessenger messenger;

    public static class DartCallback {
        public final AssetManager androidAssetManager;
        public final FlutterCallbackInformation callbackHandle;
        public final String pathToBundle;

        public DartCallback(@NonNull AssetManager assetManager, @NonNull String str, @NonNull FlutterCallbackInformation flutterCallbackInformation) {
            this.androidAssetManager = assetManager;
            this.pathToBundle = str;
            this.callbackHandle = flutterCallbackInformation;
        }

        @NonNull
        public String toString() {
            return "DartCallback( bundle path: " + this.pathToBundle + ", library path: " + this.callbackHandle.callbackLibraryPath + ", function: " + this.callbackHandle.callbackName + " )";
        }
    }

    public static class DartEntrypoint {
        @NonNull
        public final String dartEntrypointFunctionName;
        @NonNull
        public final String pathToBundle;

        public DartEntrypoint(@NonNull String str, @NonNull String str2) {
            this.pathToBundle = str;
            this.dartEntrypointFunctionName = str2;
        }

        @NonNull
        public static DartEntrypoint createDefault() {
            return new DartEntrypoint(FlutterMain.findAppBundlePath(), "main");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DartEntrypoint dartEntrypoint = (DartEntrypoint) obj;
            if (!this.pathToBundle.equals(dartEntrypoint.pathToBundle)) {
                return false;
            }
            return this.dartEntrypointFunctionName.equals(dartEntrypoint.dartEntrypointFunctionName);
        }

        public int hashCode() {
            return (this.pathToBundle.hashCode() * 31) + this.dartEntrypointFunctionName.hashCode();
        }

        @NonNull
        public String toString() {
            return "DartEntrypoint( bundle path: " + this.pathToBundle + ", function: " + this.dartEntrypointFunctionName + " )";
        }
    }

    interface IsolateServiceIdListener {
        void onIsolateServiceIdAvailable(@NonNull String str);
    }

    public DartExecutor(@NonNull FlutterJNI flutterJNI2, @NonNull AssetManager assetManager2) {
        this.flutterJNI = flutterJNI2;
        this.assetManager = assetManager2;
        this.messenger = new DartMessenger(flutterJNI2);
        this.messenger.setMessageHandler("flutter/isolate", this.isolateChannelMessageHandler);
    }

    public void executeDartCallback(@NonNull DartCallback dartCallback) {
        if (this.isApplicationRunning) {
            Log.w(TAG, "Attempted to run a DartExecutor that is already running.");
            return;
        }
        Log.v(TAG, "Executing Dart callback: " + dartCallback);
        this.flutterJNI.runBundleAndSnapshotFromLibrary(dartCallback.pathToBundle, dartCallback.callbackHandle.callbackName, dartCallback.callbackHandle.callbackLibraryPath, dartCallback.androidAssetManager);
        this.isApplicationRunning = true;
    }

    public void executeDartEntrypoint(@NonNull DartEntrypoint dartEntrypoint) {
        if (this.isApplicationRunning) {
            Log.w(TAG, "Attempted to run a DartExecutor that is already running.");
            return;
        }
        Log.v(TAG, "Executing Dart entrypoint: " + dartEntrypoint);
        this.flutterJNI.runBundleAndSnapshotFromLibrary(dartEntrypoint.pathToBundle, dartEntrypoint.dartEntrypointFunctionName, (String) null, this.assetManager);
        this.isApplicationRunning = true;
    }

    @Nullable
    public String getIsolateServiceId() {
        return this.isolateServiceId;
    }

    @UiThread
    public int getPendingChannelResponseCount() {
        return this.messenger.getPendingChannelResponseCount();
    }

    public boolean isExecutingDart() {
        return this.isApplicationRunning;
    }

    public void onAttachedToJNI() {
        Log.v(TAG, "Attached to JNI. Registering the platform message handler for this Dart execution context.");
        this.flutterJNI.setPlatformMessageHandler(this.messenger);
    }

    public void onDetachedFromJNI() {
        Log.v(TAG, "Detached from JNI. De-registering the platform message handler for this Dart execution context.");
        this.flutterJNI.setPlatformMessageHandler((PlatformMessageHandler) null);
    }

    @UiThread
    public void send(@NonNull String str, @Nullable ByteBuffer byteBuffer) {
        this.messenger.send(str, byteBuffer, (BinaryMessenger.BinaryReply) null);
    }

    @UiThread
    public void send(@NonNull String str, @Nullable ByteBuffer byteBuffer, @Nullable BinaryMessenger.BinaryReply binaryReply) {
        this.messenger.send(str, byteBuffer, binaryReply);
    }

    public void setIsolateServiceIdListener(@Nullable IsolateServiceIdListener isolateServiceIdListener2) {
        String str;
        this.isolateServiceIdListener = isolateServiceIdListener2;
        IsolateServiceIdListener isolateServiceIdListener3 = this.isolateServiceIdListener;
        if (isolateServiceIdListener3 != null && (str = this.isolateServiceId) != null) {
            isolateServiceIdListener3.onIsolateServiceIdAvailable(str);
        }
    }

    @UiThread
    public void setMessageHandler(@NonNull String str, @Nullable BinaryMessenger.BinaryMessageHandler binaryMessageHandler) {
        this.messenger.setMessageHandler(str, binaryMessageHandler);
    }
}
