package io.flutter.plugins.urllauncher;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.urllauncher.UrlLauncher;
import java.util.Map;

final class MethodCallHandlerImpl implements MethodChannel.MethodCallHandler {
    private static final String TAG = "MethodCallHandlerImpl";
    @Nullable
    private MethodChannel channel;
    private final UrlLauncher urlLauncher;

    MethodCallHandlerImpl(UrlLauncher urlLauncher2) {
        this.urlLauncher = urlLauncher2;
    }

    private static Bundle extractBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (String next : map.keySet()) {
            bundle.putString(next, map.get(next));
        }
        return bundle;
    }

    private void onCanLaunch(MethodChannel.Result result, String str) {
        result.success(Boolean.valueOf(this.urlLauncher.canLaunch(str)));
    }

    private void onCloseWebView(MethodChannel.Result result) {
        this.urlLauncher.closeWebView();
        result.success((Object) null);
    }

    private void onLaunch(MethodCall methodCall, MethodChannel.Result result, String str) {
        boolean booleanValue = ((Boolean) methodCall.argument("useWebView")).booleanValue();
        boolean booleanValue2 = ((Boolean) methodCall.argument("enableJavaScript")).booleanValue();
        boolean booleanValue3 = ((Boolean) methodCall.argument("enableDomStorage")).booleanValue();
        if (this.urlLauncher.launch(str, extractBundle((Map) methodCall.argument("headers")), booleanValue, booleanValue2, booleanValue3) == UrlLauncher.LaunchStatus.NO_ACTIVITY) {
            result.error("NO_ACTIVITY", "Launching a URL requires a foreground activity.", (Object) null);
        } else {
            result.success(true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r5, io.flutter.plugin.common.MethodChannel.Result r6) {
        /*
            r4 = this;
            java.lang.String r0 = "url"
            java.lang.Object r0 = r5.argument(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = r5.method
            int r2 = r1.hashCode()
            r3 = -1109843021(0xffffffffbdd923b3, float:-0.10602512)
            if (r2 == r3) goto L_0x0032
            r3 = -185306205(0xfffffffff4f473a3, float:-1.5493968E32)
            if (r2 == r3) goto L_0x0028
            r3 = -121617663(0xfffffffff8c04301, float:-3.119625E34)
            if (r2 == r3) goto L_0x001e
            goto L_0x003c
        L_0x001e:
            java.lang.String r2 = "closeWebView"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x003c
            r1 = 2
            goto L_0x003d
        L_0x0028:
            java.lang.String r2 = "canLaunch"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x003c
            r1 = 0
            goto L_0x003d
        L_0x0032:
            java.lang.String r2 = "launch"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x003c
            r1 = 1
            goto L_0x003d
        L_0x003c:
            r1 = -1
        L_0x003d:
            switch(r1) {
                case 0: goto L_0x004c;
                case 1: goto L_0x0048;
                case 2: goto L_0x0044;
                default: goto L_0x0040;
            }
        L_0x0040:
            r6.notImplemented()
            goto L_0x004f
        L_0x0044:
            r4.onCloseWebView(r6)
            goto L_0x004f
        L_0x0048:
            r4.onLaunch(r5, r6, r0)
            goto L_0x004f
        L_0x004c:
            r4.onCanLaunch(r6, r0)
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.urllauncher.MethodCallHandlerImpl.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }

    /* access modifiers changed from: package-private */
    public void startListening(BinaryMessenger binaryMessenger) {
        if (this.channel != null) {
            Log.wtf(TAG, "Setting a method call handler before the last was disposed.");
            stopListening();
        }
        this.channel = new MethodChannel(binaryMessenger, "plugins.flutter.io/url_launcher");
        this.channel.setMethodCallHandler(this);
    }

    /* access modifiers changed from: package-private */
    public void stopListening() {
        MethodChannel methodChannel = this.channel;
        if (methodChannel == null) {
            Log.d(TAG, "Tried to stop listening when no MethodChannel had been initialized.");
            return;
        }
        methodChannel.setMethodCallHandler((MethodChannel.MethodCallHandler) null);
        this.channel = null;
    }
}
