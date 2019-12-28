package io.flutter.plugins.share;

import android.content.Intent;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.util.Map;

public class SharePlugin implements MethodChannel.MethodCallHandler {
    private static final String CHANNEL = "plugins.flutter.io/share";
    private final PluginRegistry.Registrar mRegistrar;

    private SharePlugin(PluginRegistry.Registrar registrar) {
        this.mRegistrar = registrar;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodChannel(registrar.messenger(), CHANNEL).setMethodCallHandler(new SharePlugin(registrar));
    }

    private void share(String str, String str2) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Non-empty text expected");
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.setType("text/plain");
        Intent createChooser = Intent.createChooser(intent, (CharSequence) null);
        if (this.mRegistrar.activity() != null) {
            this.mRegistrar.activity().startActivity(createChooser);
            return;
        }
        createChooser.addFlags(268435456);
        this.mRegistrar.context().startActivity(createChooser);
    }

    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (!methodCall.method.equals("share")) {
            result.notImplemented();
        } else if (methodCall.arguments instanceof Map) {
            share((String) methodCall.argument("text"), (String) methodCall.argument("subject"));
            result.success((Object) null);
        } else {
            throw new IllegalArgumentException("Map argument expected");
        }
    }
}
