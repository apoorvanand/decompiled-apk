package io.flutter.plugins.firebase.core;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.util.HashMap;
import java.util.Map;

public class FirebaseCorePlugin implements MethodChannel.MethodCallHandler {
    private final Context context;

    private FirebaseCorePlugin(Context context2) {
        this.context = context2;
    }

    private Map<String, Object> asMap(FirebaseApp firebaseApp) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", firebaseApp.getName());
        FirebaseOptions options = firebaseApp.getOptions();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("googleAppID", options.getApplicationId());
        hashMap2.put("GCMSenderID", options.getGcmSenderId());
        hashMap2.put("APIKey", options.getApiKey());
        hashMap2.put("databaseURL", options.getDatabaseUrl());
        hashMap2.put("storageBucket", options.getStorageBucket());
        hashMap2.put("projectID", options.getProjectId());
        hashMap.put(Constant.METHOD_OPTIONS, hashMap2);
        return hashMap;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodChannel(registrar.messenger(), "plugins.flutter.io/firebase_core").setMethodCallHandler(new FirebaseCorePlugin(registrar.context()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r5, io.flutter.plugin.common.MethodChannel.Result r6) {
        /*
            r4 = this;
            java.lang.String r0 = r5.method
            int r1 = r0.hashCode()
            r2 = -2051749503(0xffffffff85b4c981, float:-1.700114E-35)
            if (r1 == r2) goto L_0x002a
            r2 = -1323159523(0xffffffffb122301d, float:-2.3601452E-9)
            if (r1 == r2) goto L_0x0020
            r2 = 2031988394(0x791daeaa, float:5.1170826E34)
            if (r1 == r2) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r1 = "FirebaseApp#allApps"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
            r0 = 1
            goto L_0x0035
        L_0x0020:
            java.lang.String r1 = "FirebaseApp#configure"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
            r0 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r1 = "FirebaseApp#appNamed"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
            r0 = 2
            goto L_0x0035
        L_0x0034:
            r0 = -1
        L_0x0035:
            r1 = 0
            switch(r0) {
                case 0: goto L_0x0078;
                case 1: goto L_0x0051;
                case 2: goto L_0x003e;
                default: goto L_0x0039;
            }
        L_0x0039:
            r6.notImplemented()
            goto L_0x00e7
        L_0x003e:
            java.lang.Object r5 = r5.arguments()
            java.lang.String r5 = (java.lang.String) r5
            com.google.firebase.FirebaseApp r5 = com.google.firebase.FirebaseApp.getInstance(r5)     // Catch:{ IllegalStateException -> 0x00e4 }
            java.util.Map r5 = r4.asMap(r5)     // Catch:{ IllegalStateException -> 0x00e4 }
            r6.success(r5)     // Catch:{ IllegalStateException -> 0x00e4 }
            goto L_0x00e7
        L_0x0051:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            android.content.Context r0 = r4.context
            java.util.List r0 = com.google.firebase.FirebaseApp.getApps(r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x0060:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0074
            java.lang.Object r1 = r0.next()
            com.google.firebase.FirebaseApp r1 = (com.google.firebase.FirebaseApp) r1
            java.util.Map r1 = r4.asMap(r1)
            r5.add(r1)
            goto L_0x0060
        L_0x0074:
            r6.success(r5)
            goto L_0x00e7
        L_0x0078:
            java.lang.Object r5 = r5.arguments()
            java.util.Map r5 = (java.util.Map) r5
            java.lang.String r0 = "name"
            java.lang.Object r0 = r5.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = "options"
            java.lang.Object r5 = r5.get(r2)
            java.util.Map r5 = (java.util.Map) r5
            com.google.firebase.FirebaseOptions$Builder r2 = new com.google.firebase.FirebaseOptions$Builder
            r2.<init>()
            java.lang.String r3 = "APIKey"
            java.lang.Object r3 = r5.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            com.google.firebase.FirebaseOptions$Builder r2 = r2.setApiKey(r3)
            java.lang.String r3 = "googleAppID"
            java.lang.Object r3 = r5.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            com.google.firebase.FirebaseOptions$Builder r2 = r2.setApplicationId(r3)
            java.lang.String r3 = "databaseURL"
            java.lang.Object r3 = r5.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            com.google.firebase.FirebaseOptions$Builder r2 = r2.setDatabaseUrl(r3)
            java.lang.String r3 = "GCMSenderID"
            java.lang.Object r3 = r5.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            com.google.firebase.FirebaseOptions$Builder r2 = r2.setGcmSenderId(r3)
            java.lang.String r3 = "projectID"
            java.lang.Object r3 = r5.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            com.google.firebase.FirebaseOptions$Builder r2 = r2.setProjectId(r3)
            java.lang.String r3 = "storageBucket"
            java.lang.Object r5 = r5.get(r3)
            java.lang.String r5 = (java.lang.String) r5
            com.google.firebase.FirebaseOptions$Builder r5 = r2.setStorageBucket(r5)
            com.google.firebase.FirebaseOptions r5 = r5.build()
            android.content.Context r2 = r4.context
            com.google.firebase.FirebaseApp.initializeApp(r2, r5, r0)
        L_0x00e4:
            r6.success(r1)
        L_0x00e7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.firebase.core.FirebaseCorePlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
