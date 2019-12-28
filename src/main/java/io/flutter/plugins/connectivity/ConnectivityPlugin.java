package io.flutter.plugins.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class ConnectivityPlugin implements EventChannel.StreamHandler, MethodChannel.MethodCallHandler {
    private final ConnectivityManager manager;
    private BroadcastReceiver receiver;
    private final PluginRegistry.Registrar registrar;

    private ConnectivityPlugin(PluginRegistry.Registrar registrar2) {
        this.registrar = registrar2;
        this.manager = (ConnectivityManager) registrar2.context().getApplicationContext().getSystemService("connectivity");
    }

    /* access modifiers changed from: private */
    public String checkNetworkType() {
        return getNetworkType(this.manager);
    }

    private BroadcastReceiver createReceiver(final EventChannel.EventSink eventSink) {
        return new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                eventSink.success(ConnectivityPlugin.this.checkNetworkType());
            }
        };
    }

    private String getNetworkType(ConnectivityManager connectivityManager) {
        if (Build.VERSION.SDK_INT >= 23) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null) {
                return "none";
            }
            if (networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(3)) {
                return "wifi";
            }
            if (networkCapabilities.hasTransport(0)) {
                return "mobile";
            }
        }
        return getNetworkTypeLegacy(connectivityManager);
    }

    private String getNetworkTypeLegacy(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "none";
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
            case 4:
            case 5:
                return "mobile";
            case 1:
            case 6:
            case 9:
                return "wifi";
            default:
                return "none";
        }
    }

    private WifiInfo getWifiInfo() {
        WifiManager wifiManager = (WifiManager) this.registrar.context().getApplicationContext().getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        return wifiManager.getConnectionInfo();
    }

    private void handleBSSID(MethodCall methodCall, MethodChannel.Result result) {
        WifiInfo wifiInfo = getWifiInfo();
        result.success(wifiInfo != null ? wifiInfo.getBSSID() : null);
    }

    private void handleCheck(MethodCall methodCall, MethodChannel.Result result) {
        result.success(checkNetworkType());
    }

    private void handleWifiIPAddress(MethodCall methodCall, MethodChannel.Result result) {
        WifiManager wifiManager = (WifiManager) this.registrar.context().getApplicationContext().getSystemService("wifi");
        String str = null;
        WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
        int ipAddress = connectionInfo != null ? connectionInfo.getIpAddress() : 0;
        if (ipAddress != 0) {
            str = String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf(ipAddress & 255), Integer.valueOf((ipAddress >> 8) & 255), Integer.valueOf((ipAddress >> 16) & 255), Integer.valueOf((ipAddress >> 24) & 255)});
        }
        result.success(str);
    }

    private void handleWifiName(MethodCall methodCall, MethodChannel.Result result) {
        WifiInfo wifiInfo = getWifiInfo();
        String ssid = wifiInfo != null ? wifiInfo.getSSID() : null;
        if (ssid != null) {
            ssid = ssid.replaceAll("\"", "");
        }
        result.success(ssid);
    }

    public static void registerWith(PluginRegistry.Registrar registrar2) {
        MethodChannel methodChannel = new MethodChannel(registrar2.messenger(), "plugins.flutter.io/connectivity");
        EventChannel eventChannel = new EventChannel(registrar2.messenger(), "plugins.flutter.io/connectivity_status");
        ConnectivityPlugin connectivityPlugin = new ConnectivityPlugin(registrar2);
        methodChannel.setMethodCallHandler(connectivityPlugin);
        eventChannel.setStreamHandler(connectivityPlugin);
    }

    public void onCancel(Object obj) {
        this.registrar.context().unregisterReceiver(this.receiver);
        this.receiver = null;
    }

    public void onListen(Object obj, EventChannel.EventSink eventSink) {
        this.receiver = createReceiver(eventSink);
        this.registrar.context().registerReceiver(this.receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r4, io.flutter.plugin.common.MethodChannel.Result r5) {
        /*
            r3 = this;
            java.lang.String r0 = r4.method
            int r1 = r0.hashCode()
            r2 = -1340798144(0xffffffffb0150b40, float:-5.422187E-10)
            if (r1 == r2) goto L_0x0039
            r2 = 94627080(0x5a3e508, float:1.5412579E-35)
            if (r1 == r2) goto L_0x002f
            r2 = 1373405384(0x51dc80c8, float:1.18381674E11)
            if (r1 == r2) goto L_0x0025
            r2 = 1756715352(0x68b55958, float:6.8511715E24)
            if (r1 == r2) goto L_0x001b
            goto L_0x0043
        L_0x001b:
            java.lang.String r1 = "wifiIPAddress"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = 3
            goto L_0x0044
        L_0x0025:
            java.lang.String r1 = "wifiBSSID"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = 2
            goto L_0x0044
        L_0x002f:
            java.lang.String r1 = "check"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = 0
            goto L_0x0044
        L_0x0039:
            java.lang.String r1 = "wifiName"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = 1
            goto L_0x0044
        L_0x0043:
            r0 = -1
        L_0x0044:
            switch(r0) {
                case 0: goto L_0x0057;
                case 1: goto L_0x0053;
                case 2: goto L_0x004f;
                case 3: goto L_0x004b;
                default: goto L_0x0047;
            }
        L_0x0047:
            r5.notImplemented()
            goto L_0x005a
        L_0x004b:
            r3.handleWifiIPAddress(r4, r5)
            goto L_0x005a
        L_0x004f:
            r3.handleBSSID(r4, r5)
            goto L_0x005a
        L_0x0053:
            r3.handleWifiName(r4, r5)
            goto L_0x005a
        L_0x0057:
            r3.handleCheck(r4, r5)
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.connectivity.ConnectivityPlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
