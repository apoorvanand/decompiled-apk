package io.flutter.plugins.pathprovider;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.util.PathUtils;
import java.io.File;

public class PathProviderPlugin implements MethodChannel.MethodCallHandler {
    private final PluginRegistry.Registrar mRegistrar;

    private PathProviderPlugin(PluginRegistry.Registrar registrar) {
        this.mRegistrar = registrar;
    }

    private String getApplicationSupportDirectory() {
        return PathUtils.getFilesDir(this.mRegistrar.context());
    }

    private String getPathProviderApplicationDocumentsDirectory() {
        return PathUtils.getDataDirectory(this.mRegistrar.context());
    }

    private String getPathProviderStorageDirectory() {
        File externalFilesDir = this.mRegistrar.context().getExternalFilesDir((String) null);
        if (externalFilesDir == null) {
            return null;
        }
        return externalFilesDir.getAbsolutePath();
    }

    private String getPathProviderTemporaryDirectory() {
        return this.mRegistrar.context().getCacheDir().getPath();
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodChannel(registrar.messenger(), "plugins.flutter.io/path_provider").setMethodCallHandler(new PathProviderPlugin(registrar));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r3, io.flutter.plugin.common.MethodChannel.Result r4) {
        /*
            r2 = this;
            java.lang.String r3 = r3.method
            int r0 = r3.hashCode()
            r1 = -1832373352(0xffffffff92c83398, float:-1.2634493E-27)
            if (r0 == r1) goto L_0x0039
            r1 = 1200320591(0x478b704f, float:71392.62)
            if (r0 == r1) goto L_0x002f
            r1 = 1252916648(0x4aadfda8, float:5701332.0)
            if (r0 == r1) goto L_0x0025
            r1 = 1711844626(0x6608ad12, float:1.613586E23)
            if (r0 == r1) goto L_0x001b
            goto L_0x0043
        L_0x001b:
            java.lang.String r0 = "getTemporaryDirectory"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0043
            r3 = 0
            goto L_0x0044
        L_0x0025:
            java.lang.String r0 = "getStorageDirectory"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0043
            r3 = 2
            goto L_0x0044
        L_0x002f:
            java.lang.String r0 = "getApplicationDocumentsDirectory"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0043
            r3 = 1
            goto L_0x0044
        L_0x0039:
            java.lang.String r0 = "getApplicationSupportDirectory"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0043
            r3 = 3
            goto L_0x0044
        L_0x0043:
            r3 = -1
        L_0x0044:
            switch(r3) {
                case 0: goto L_0x005a;
                case 1: goto L_0x0055;
                case 2: goto L_0x0050;
                case 3: goto L_0x004b;
                default: goto L_0x0047;
            }
        L_0x0047:
            r4.notImplemented()
            goto L_0x0061
        L_0x004b:
            java.lang.String r3 = r2.getApplicationSupportDirectory()
            goto L_0x005e
        L_0x0050:
            java.lang.String r3 = r2.getPathProviderStorageDirectory()
            goto L_0x005e
        L_0x0055:
            java.lang.String r3 = r2.getPathProviderApplicationDocumentsDirectory()
            goto L_0x005e
        L_0x005a:
            java.lang.String r3 = r2.getPathProviderTemporaryDirectory()
        L_0x005e:
            r4.success(r3)
        L_0x0061:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.pathprovider.PathProviderPlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
