package com.benjaminabel.vibration;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.util.List;

public class VibrationPlugin implements MethodChannel.MethodCallHandler {
    private static final String CHANNEL = "vibration";
    private final Vibrator vibrator;

    private VibrationPlugin(PluginRegistry.Registrar registrar) {
        this.vibrator = (Vibrator) registrar.context().getSystemService("vibrator");
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodChannel(registrar.messenger(), CHANNEL).setMethodCallHandler(new VibrationPlugin(registrar));
    }

    private void vibrate(long j) {
        if (!this.vibrator.hasVibrator()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.vibrator.vibrate(VibrationEffect.createOneShot(j, -1));
        } else {
            this.vibrator.vibrate(j);
        }
    }

    private void vibrate(List<Integer> list, int i) {
        long[] jArr = new long[list.size()];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = (long) list.get(i2).intValue();
        }
        if (!this.vibrator.hasVibrator()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.vibrator.vibrate(VibrationEffect.createWaveform(jArr, i));
        } else {
            this.vibrator.vibrate(jArr, i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r5, io.flutter.plugin.common.MethodChannel.Result r6) {
        /*
            r4 = this;
            java.lang.String r0 = r5.method
            int r1 = r0.hashCode()
            r2 = -1367724422(0xffffffffae7a2e7a, float:-5.68847E-11)
            if (r1 == r2) goto L_0x002a
            r2 = -314771757(0xffffffffed3cf6d3, float:-3.6550984E27)
            if (r1 == r2) goto L_0x0020
            r2 = 451310959(0x1ae6756f, float:9.5315495E-23)
            if (r1 == r2) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r1 = "vibrate"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
            r0 = 1
            goto L_0x0035
        L_0x0020:
            java.lang.String r1 = "hasVibrator"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
            r0 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r1 = "cancel"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
            r0 = 2
            goto L_0x0035
        L_0x0034:
            r0 = -1
        L_0x0035:
            r1 = 0
            switch(r0) {
                case 0: goto L_0x0075;
                case 1: goto L_0x0046;
                case 2: goto L_0x003d;
                default: goto L_0x0039;
            }
        L_0x0039:
            r6.notImplemented()
            goto L_0x0082
        L_0x003d:
            android.os.Vibrator r5 = r4.vibrator
            r5.cancel()
        L_0x0042:
            r6.success(r1)
            goto L_0x0082
        L_0x0046:
            java.lang.String r0 = "duration"
            java.lang.Object r0 = r5.argument(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.String r2 = "pattern"
            java.lang.Object r2 = r5.argument(r2)
            java.util.List r2 = (java.util.List) r2
            java.lang.String r3 = "repeat"
            java.lang.Object r5 = r5.argument(r3)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r3 = r2.size()
            if (r3 <= 0) goto L_0x0070
            r4.vibrate(r2, r5)
            goto L_0x0042
        L_0x0070:
            long r2 = (long) r0
            r4.vibrate(r2)
            goto L_0x0042
        L_0x0075:
            android.os.Vibrator r5 = r4.vibrator
            boolean r5 = r5.hasVibrator()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r6.success(r5)
        L_0x0082:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.benjaminabel.vibration.VibrationPlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
