package com.sidlatau.flutteremailsender;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001e2\u00020\u00012\u00020\u0002:\u0001\u001eB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J+\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\n0\fj\b\u0012\u0004\u0012\u00020\n`\rH\u0002¢\u0006\u0002\u0010\u000eJ\"\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0007H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/sidlatau/flutteremailsender/FlutterEmailSenderPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "Lio/flutter/plugin/common/PluginRegistry$ActivityResultListener;", "registrar", "Lio/flutter/plugin/common/PluginRegistry$Registrar;", "(Lio/flutter/plugin/common/PluginRegistry$Registrar;)V", "channelResult", "Lio/flutter/plugin/common/MethodChannel$Result;", "listArrayToArray", "", "", "r", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)[Ljava/lang/String;", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onMethodCall", "", "call", "Lio/flutter/plugin/common/MethodCall;", "result", "sendEmail", "options", "callback", "Companion", "flutter_email_sender_release"}, k = 1, mv = {1, 1, 15})
public final class FlutterEmailSenderPlugin implements MethodChannel.MethodCallHandler, PluginRegistry.ActivityResultListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private MethodChannel.Result channelResult;
    private final PluginRegistry.Registrar registrar;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/sidlatau/flutteremailsender/FlutterEmailSenderPlugin$Companion;", "", "()V", "registerWith", "", "registrar", "Lio/flutter/plugin/common/PluginRegistry$Registrar;", "flutter_email_sender_release"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void registerWith(@NotNull PluginRegistry.Registrar registrar) {
            Intrinsics.checkParameterIsNotNull(registrar, "registrar");
            MethodChannel methodChannel = new MethodChannel(registrar.messenger(), "flutter_email_sender");
            FlutterEmailSenderPlugin flutterEmailSenderPlugin = new FlutterEmailSenderPlugin(registrar);
            registrar.addActivityResultListener(flutterEmailSenderPlugin);
            methodChannel.setMethodCallHandler(flutterEmailSenderPlugin);
        }
    }

    public FlutterEmailSenderPlugin(@NotNull PluginRegistry.Registrar registrar2) {
        Intrinsics.checkParameterIsNotNull(registrar2, "registrar");
        this.registrar = registrar2;
    }

    private final String[] listArrayToArray(ArrayList<String> arrayList) {
        Object[] array = arrayList.toArray(new String[arrayList.size()]);
        Intrinsics.checkExpressionValueIsNotNull(array, "r.toArray(arrayOfNulls<String>(r.size))");
        return (String[]) array;
    }

    @JvmStatic
    public static final void registerWith(@NotNull PluginRegistry.Registrar registrar2) {
        Companion.registerWith(registrar2);
    }

    private final void sendEmail(MethodCall methodCall, MethodChannel.Result result) {
        String str;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        String str2;
        Activity activity = this.registrar.activity();
        if (activity == null) {
            result.error("error", "Activity == null!", (Object) null);
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("vnd.android.cursor.dir/email");
        if (methodCall.hasArgument("subject")) {
            intent.putExtra("android.intent.extra.SUBJECT", (String) methodCall.argument("subject"));
        }
        if (methodCall.hasArgument("body") && (str2 = (String) methodCall.argument("body")) != null) {
            intent.putExtra("android.intent.extra.TEXT", str2);
        }
        if (methodCall.hasArgument("recipients") && (arrayList3 = (ArrayList) methodCall.argument("recipients")) != null) {
            intent.putExtra("android.intent.extra.EMAIL", listArrayToArray(arrayList3));
        }
        if (methodCall.hasArgument("cc") && (arrayList2 = (ArrayList) methodCall.argument("cc")) != null) {
            intent.putExtra("android.intent.extra.CC", listArrayToArray(arrayList2));
        }
        if (methodCall.hasArgument("bcc") && (arrayList = (ArrayList) methodCall.argument("bcc")) != null) {
            intent.putExtra("android.intent.extra.BCC", listArrayToArray(arrayList));
        }
        if (methodCall.hasArgument("attachment_path") && (str = (String) methodCall.argument("attachment_path")) != null) {
            intent.addFlags(1);
            File file = new File(str);
            StringBuilder sb = new StringBuilder();
            Context context = this.registrar.context();
            Intrinsics.checkExpressionValueIsNotNull(context, "registrar.context()");
            sb.append(context.getPackageName());
            sb.append(".file_provider");
            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, sb.toString(), file));
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
            activity.startActivityForResult(intent, 607);
        } else {
            result.error("not_available", "No email clients found!", (Object) null);
        }
    }

    public boolean onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i != 607) {
            return false;
        }
        MethodChannel.Result result = this.channelResult;
        if (result == null) {
            return true;
        }
        result.success((Object) null);
        return true;
    }

    public void onMethodCall(@NotNull MethodCall methodCall, @NotNull MethodChannel.Result result) {
        Intrinsics.checkParameterIsNotNull(methodCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(result, Constant.PARAM_RESULT);
        this.channelResult = result;
        if (Intrinsics.areEqual((Object) methodCall.method, (Object) "send")) {
            sendEmail(methodCall, result);
        } else {
            result.notImplemented();
        }
    }
}
