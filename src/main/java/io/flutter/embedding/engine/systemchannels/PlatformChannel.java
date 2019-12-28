package io.flutter.embedding.engine.systemchannels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PlatformChannel {
    private static final String TAG = "PlatformChannel";
    @NonNull
    public final MethodChannel channel;
    private final MethodChannel.MethodCallHandler parsingMethodCallHandler = new MethodChannel.MethodCallHandler() {
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b1, code lost:
            r6.success((java.lang.Object) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
            return;
         */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x00e2 A[Catch:{ JSONException -> 0x01be }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onMethodCall(@androidx.annotation.NonNull io.flutter.plugin.common.MethodCall r5, @androidx.annotation.NonNull io.flutter.plugin.common.MethodChannel.Result r6) {
            /*
                r4 = this;
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r0 = r0.platformMessageHandler
                if (r0 != 0) goto L_0x0009
                return
            L_0x0009:
                java.lang.String r0 = r5.method
                java.lang.Object r5 = r5.arguments
                java.lang.String r1 = "PlatformChannel"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Received '"
                r2.append(r3)
                r2.append(r0)
                java.lang.String r3 = "' message."
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                io.flutter.Log.v(r1, r2)
                r1 = -1
                r2 = 0
                int r3 = r0.hashCode()     // Catch:{ JSONException -> 0x01be }
                switch(r3) {
                    case -766342101: goto L_0x008f;
                    case -720677196: goto L_0x0084;
                    case -548468504: goto L_0x007a;
                    case -247230243: goto L_0x0070;
                    case -215273374: goto L_0x0066;
                    case 241845679: goto L_0x005c;
                    case 1390477857: goto L_0x0052;
                    case 1514180520: goto L_0x0047;
                    case 1674312266: goto L_0x003d;
                    case 2119655719: goto L_0x0033;
                    default: goto L_0x0031;
                }     // Catch:{ JSONException -> 0x01be }
            L_0x0031:
                goto L_0x0098
            L_0x0033:
                java.lang.String r3 = "SystemChrome.setPreferredOrientations"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 2
                goto L_0x0098
            L_0x003d:
                java.lang.String r3 = "SystemChrome.setEnabledSystemUIOverlays"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 4
                goto L_0x0098
            L_0x0047:
                java.lang.String r3 = "Clipboard.getData"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 8
                goto L_0x0098
            L_0x0052:
                java.lang.String r3 = "SystemChrome.setSystemUIOverlayStyle"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 6
                goto L_0x0098
            L_0x005c:
                java.lang.String r3 = "SystemChrome.restoreSystemUIOverlays"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 5
                goto L_0x0098
            L_0x0066:
                java.lang.String r3 = "SystemSound.play"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 0
                goto L_0x0098
            L_0x0070:
                java.lang.String r3 = "HapticFeedback.vibrate"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 1
                goto L_0x0098
            L_0x007a:
                java.lang.String r3 = "SystemChrome.setApplicationSwitcherDescription"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 3
                goto L_0x0098
            L_0x0084:
                java.lang.String r3 = "Clipboard.setData"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 9
                goto L_0x0098
            L_0x008f:
                java.lang.String r3 = "SystemNavigator.pop"
                boolean r0 = r0.equals(r3)     // Catch:{ JSONException -> 0x01be }
                if (r0 == 0) goto L_0x0098
                r1 = 7
            L_0x0098:
                switch(r1) {
                    case 0: goto L_0x01a2;
                    case 1: goto L_0x0186;
                    case 2: goto L_0x0167;
                    case 3: goto L_0x0149;
                    case 4: goto L_0x0129;
                    case 5: goto L_0x011f;
                    case 6: goto L_0x00fb;
                    case 7: goto L_0x00f1;
                    case 8: goto L_0x00b6;
                    case 9: goto L_0x00a0;
                    default: goto L_0x009b;
                }     // Catch:{ JSONException -> 0x01be }
            L_0x009b:
                r6.notImplemented()     // Catch:{ JSONException -> 0x01be }
                goto L_0x01d9
            L_0x00a0:
                org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ JSONException -> 0x01be }
                java.lang.String r0 = "text"
                java.lang.String r5 = r5.getString(r0)     // Catch:{ JSONException -> 0x01be }
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x01be }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r0 = r0.platformMessageHandler     // Catch:{ JSONException -> 0x01be }
                r0.setClipboardData(r5)     // Catch:{ JSONException -> 0x01be }
            L_0x00b1:
                r6.success(r2)     // Catch:{ JSONException -> 0x01be }
                goto L_0x01d9
            L_0x00b6:
                java.lang.String r5 = (java.lang.String) r5     // Catch:{ JSONException -> 0x01be }
                if (r5 == 0) goto L_0x00d5
                io.flutter.embedding.engine.systemchannels.PlatformChannel$ClipboardContentFormat r5 = io.flutter.embedding.engine.systemchannels.PlatformChannel.ClipboardContentFormat.fromValue(r5)     // Catch:{ NoSuchFieldException -> 0x00bf }
                goto L_0x00d6
            L_0x00bf:
                java.lang.String r0 = "error"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x01be }
                r1.<init>()     // Catch:{ JSONException -> 0x01be }
                java.lang.String r3 = "No such clipboard content format: "
                r1.append(r3)     // Catch:{ JSONException -> 0x01be }
                r1.append(r5)     // Catch:{ JSONException -> 0x01be }
                java.lang.String r5 = r1.toString()     // Catch:{ JSONException -> 0x01be }
                r6.error(r0, r5, r2)     // Catch:{ JSONException -> 0x01be }
            L_0x00d5:
                r5 = r2
            L_0x00d6:
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x01be }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r0 = r0.platformMessageHandler     // Catch:{ JSONException -> 0x01be }
                java.lang.CharSequence r5 = r0.getClipboardData(r5)     // Catch:{ JSONException -> 0x01be }
                if (r5 == 0) goto L_0x00b1
                org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x01be }
                r0.<init>()     // Catch:{ JSONException -> 0x01be }
                java.lang.String r1 = "text"
                r0.put(r1, r5)     // Catch:{ JSONException -> 0x01be }
                r6.success(r0)     // Catch:{ JSONException -> 0x01be }
                goto L_0x01d9
            L_0x00f1:
                io.flutter.embedding.engine.systemchannels.PlatformChannel r5 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x01be }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r5 = r5.platformMessageHandler     // Catch:{ JSONException -> 0x01be }
                r5.popSystemNavigator()     // Catch:{ JSONException -> 0x01be }
                goto L_0x00b1
            L_0x00fb:
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x0113, NoSuchFieldException -> 0x0111 }
                org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ JSONException -> 0x0113, NoSuchFieldException -> 0x0111 }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$SystemChromeStyle r5 = r0.decodeSystemChromeStyle(r5)     // Catch:{ JSONException -> 0x0113, NoSuchFieldException -> 0x0111 }
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x0113, NoSuchFieldException -> 0x0111 }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r0 = r0.platformMessageHandler     // Catch:{ JSONException -> 0x0113, NoSuchFieldException -> 0x0111 }
                r0.setSystemUiOverlayStyle(r5)     // Catch:{ JSONException -> 0x0113, NoSuchFieldException -> 0x0111 }
                r6.success(r2)     // Catch:{ JSONException -> 0x0113, NoSuchFieldException -> 0x0111 }
                goto L_0x01d9
            L_0x0111:
                r5 = move-exception
                goto L_0x0114
            L_0x0113:
                r5 = move-exception
            L_0x0114:
                java.lang.String r0 = "error"
                java.lang.String r5 = r5.getMessage()     // Catch:{ JSONException -> 0x01be }
            L_0x011a:
                r6.error(r0, r5, r2)     // Catch:{ JSONException -> 0x01be }
                goto L_0x01d9
            L_0x011f:
                io.flutter.embedding.engine.systemchannels.PlatformChannel r5 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x01be }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r5 = r5.platformMessageHandler     // Catch:{ JSONException -> 0x01be }
                r5.restoreSystemUiOverlays()     // Catch:{ JSONException -> 0x01be }
                goto L_0x00b1
            L_0x0129:
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x0141, NoSuchFieldException -> 0x013f }
                org.json.JSONArray r5 = (org.json.JSONArray) r5     // Catch:{ JSONException -> 0x0141, NoSuchFieldException -> 0x013f }
                java.util.List r5 = r0.decodeSystemUiOverlays(r5)     // Catch:{ JSONException -> 0x0141, NoSuchFieldException -> 0x013f }
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x0141, NoSuchFieldException -> 0x013f }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r0 = r0.platformMessageHandler     // Catch:{ JSONException -> 0x0141, NoSuchFieldException -> 0x013f }
                r0.showSystemOverlays(r5)     // Catch:{ JSONException -> 0x0141, NoSuchFieldException -> 0x013f }
                r6.success(r2)     // Catch:{ JSONException -> 0x0141, NoSuchFieldException -> 0x013f }
                goto L_0x01d9
            L_0x013f:
                r5 = move-exception
                goto L_0x0142
            L_0x0141:
                r5 = move-exception
            L_0x0142:
                java.lang.String r0 = "error"
                java.lang.String r5 = r5.getMessage()     // Catch:{ JSONException -> 0x01be }
                goto L_0x011a
            L_0x0149:
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x015f }
                org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ JSONException -> 0x015f }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$AppSwitcherDescription r5 = r0.decodeAppSwitcherDescription(r5)     // Catch:{ JSONException -> 0x015f }
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x015f }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r0 = r0.platformMessageHandler     // Catch:{ JSONException -> 0x015f }
                r0.setApplicationSwitcherDescription(r5)     // Catch:{ JSONException -> 0x015f }
                r6.success(r2)     // Catch:{ JSONException -> 0x015f }
                goto L_0x01d9
            L_0x015f:
                r5 = move-exception
                java.lang.String r0 = "error"
                java.lang.String r5 = r5.getMessage()     // Catch:{ JSONException -> 0x01be }
                goto L_0x011a
            L_0x0167:
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x017e, NoSuchFieldException -> 0x017c }
                org.json.JSONArray r5 = (org.json.JSONArray) r5     // Catch:{ JSONException -> 0x017e, NoSuchFieldException -> 0x017c }
                int r5 = r0.decodeOrientations(r5)     // Catch:{ JSONException -> 0x017e, NoSuchFieldException -> 0x017c }
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ JSONException -> 0x017e, NoSuchFieldException -> 0x017c }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r0 = r0.platformMessageHandler     // Catch:{ JSONException -> 0x017e, NoSuchFieldException -> 0x017c }
                r0.setPreferredOrientations(r5)     // Catch:{ JSONException -> 0x017e, NoSuchFieldException -> 0x017c }
                r6.success(r2)     // Catch:{ JSONException -> 0x017e, NoSuchFieldException -> 0x017c }
                goto L_0x01d9
            L_0x017c:
                r5 = move-exception
                goto L_0x017f
            L_0x017e:
                r5 = move-exception
            L_0x017f:
                java.lang.String r0 = "error"
                java.lang.String r5 = r5.getMessage()     // Catch:{ JSONException -> 0x01be }
                goto L_0x011a
            L_0x0186:
                java.lang.String r5 = (java.lang.String) r5     // Catch:{ NoSuchFieldException -> 0x0199 }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$HapticFeedbackType r5 = io.flutter.embedding.engine.systemchannels.PlatformChannel.HapticFeedbackType.fromValue(r5)     // Catch:{ NoSuchFieldException -> 0x0199 }
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ NoSuchFieldException -> 0x0199 }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r0 = r0.platformMessageHandler     // Catch:{ NoSuchFieldException -> 0x0199 }
                r0.vibrateHapticFeedback(r5)     // Catch:{ NoSuchFieldException -> 0x0199 }
                r6.success(r2)     // Catch:{ NoSuchFieldException -> 0x0199 }
                goto L_0x01d9
            L_0x0199:
                r5 = move-exception
                java.lang.String r0 = "error"
                java.lang.String r5 = r5.getMessage()     // Catch:{ JSONException -> 0x01be }
                goto L_0x011a
            L_0x01a2:
                java.lang.String r5 = (java.lang.String) r5     // Catch:{ NoSuchFieldException -> 0x01b5 }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$SoundType r5 = io.flutter.embedding.engine.systemchannels.PlatformChannel.SoundType.fromValue(r5)     // Catch:{ NoSuchFieldException -> 0x01b5 }
                io.flutter.embedding.engine.systemchannels.PlatformChannel r0 = io.flutter.embedding.engine.systemchannels.PlatformChannel.this     // Catch:{ NoSuchFieldException -> 0x01b5 }
                io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler r0 = r0.platformMessageHandler     // Catch:{ NoSuchFieldException -> 0x01b5 }
                r0.playSystemSound(r5)     // Catch:{ NoSuchFieldException -> 0x01b5 }
                r6.success(r2)     // Catch:{ NoSuchFieldException -> 0x01b5 }
                goto L_0x01d9
            L_0x01b5:
                r5 = move-exception
                java.lang.String r0 = "error"
                java.lang.String r5 = r5.getMessage()     // Catch:{ JSONException -> 0x01be }
                goto L_0x011a
            L_0x01be:
                r5 = move-exception
                java.lang.String r0 = "error"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r3 = "JSON error: "
                r1.append(r3)
                java.lang.String r5 = r5.getMessage()
                r1.append(r5)
                java.lang.String r5 = r1.toString()
                r6.error(r0, r5, r2)
            L_0x01d9:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.engine.systemchannels.PlatformChannel.AnonymousClass1.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public PlatformMessageHandler platformMessageHandler;

    public static class AppSwitcherDescription {
        public final int color;
        @NonNull
        public final String label;

        public AppSwitcherDescription(int i, @NonNull String str) {
            this.color = i;
            this.label = str;
        }
    }

    public enum Brightness {
        LIGHT("Brightness.light"),
        DARK("Brightness.dark");
        
        @NonNull
        private String encodedName;

        private Brightness(String str) {
            this.encodedName = str;
        }

        @NonNull
        static Brightness fromValue(@NonNull String str) {
            for (Brightness brightness : values()) {
                if (brightness.encodedName.equals(str)) {
                    return brightness;
                }
            }
            throw new NoSuchFieldException("No such Brightness: " + str);
        }
    }

    public enum ClipboardContentFormat {
        PLAIN_TEXT("text/plain");
        
        @NonNull
        private String encodedName;

        private ClipboardContentFormat(String str) {
            this.encodedName = str;
        }

        @NonNull
        static ClipboardContentFormat fromValue(@NonNull String str) {
            for (ClipboardContentFormat clipboardContentFormat : values()) {
                if (clipboardContentFormat.encodedName.equals(str)) {
                    return clipboardContentFormat;
                }
            }
            throw new NoSuchFieldException("No such ClipboardContentFormat: " + str);
        }
    }

    public enum DeviceOrientation {
        PORTRAIT_UP("DeviceOrientation.portraitUp"),
        PORTRAIT_DOWN("DeviceOrientation.portraitDown"),
        LANDSCAPE_LEFT("DeviceOrientation.landscapeLeft"),
        LANDSCAPE_RIGHT("DeviceOrientation.landscapeRight");
        
        @NonNull
        private String encodedName;

        private DeviceOrientation(String str) {
            this.encodedName = str;
        }

        @NonNull
        static DeviceOrientation fromValue(@NonNull String str) {
            for (DeviceOrientation deviceOrientation : values()) {
                if (deviceOrientation.encodedName.equals(str)) {
                    return deviceOrientation;
                }
            }
            throw new NoSuchFieldException("No such DeviceOrientation: " + str);
        }
    }

    public enum HapticFeedbackType {
        STANDARD((String) null),
        LIGHT_IMPACT("HapticFeedbackType.lightImpact"),
        MEDIUM_IMPACT("HapticFeedbackType.mediumImpact"),
        HEAVY_IMPACT("HapticFeedbackType.heavyImpact"),
        SELECTION_CLICK("HapticFeedbackType.selectionClick");
        
        @Nullable
        private final String encodedName;

        private HapticFeedbackType(String str) {
            this.encodedName = str;
        }

        @NonNull
        static HapticFeedbackType fromValue(@Nullable String str) {
            String str2;
            for (HapticFeedbackType hapticFeedbackType : values()) {
                if ((hapticFeedbackType.encodedName == null && str == null) || ((str2 = hapticFeedbackType.encodedName) != null && str2.equals(str))) {
                    return hapticFeedbackType;
                }
            }
            throw new NoSuchFieldException("No such HapticFeedbackType: " + str);
        }
    }

    public interface PlatformMessageHandler {
        @Nullable
        CharSequence getClipboardData(@Nullable ClipboardContentFormat clipboardContentFormat);

        void playSystemSound(@NonNull SoundType soundType);

        void popSystemNavigator();

        void restoreSystemUiOverlays();

        void setApplicationSwitcherDescription(@NonNull AppSwitcherDescription appSwitcherDescription);

        void setClipboardData(@NonNull String str);

        void setPreferredOrientations(int i);

        void setSystemUiOverlayStyle(@NonNull SystemChromeStyle systemChromeStyle);

        void showSystemOverlays(@NonNull List<SystemUiOverlay> list);

        void vibrateHapticFeedback(@NonNull HapticFeedbackType hapticFeedbackType);
    }

    public enum SoundType {
        CLICK("SystemSoundType.click");
        
        @NonNull
        private final String encodedName;

        private SoundType(String str) {
            this.encodedName = str;
        }

        @NonNull
        static SoundType fromValue(@NonNull String str) {
            for (SoundType soundType : values()) {
                if (soundType.encodedName.equals(str)) {
                    return soundType;
                }
            }
            throw new NoSuchFieldException("No such SoundType: " + str);
        }
    }

    public static class SystemChromeStyle {
        @Nullable
        public final Integer statusBarColor;
        @Nullable
        public final Brightness statusBarIconBrightness;
        @Nullable
        public final Integer systemNavigationBarColor;
        @Nullable
        public final Integer systemNavigationBarDividerColor;
        @Nullable
        public final Brightness systemNavigationBarIconBrightness;

        public SystemChromeStyle(@Nullable Integer num, @Nullable Brightness brightness, @Nullable Integer num2, @Nullable Brightness brightness2, @Nullable Integer num3) {
            this.statusBarColor = num;
            this.statusBarIconBrightness = brightness;
            this.systemNavigationBarColor = num2;
            this.systemNavigationBarIconBrightness = brightness2;
            this.systemNavigationBarDividerColor = num3;
        }
    }

    public enum SystemUiOverlay {
        TOP_OVERLAYS("SystemUiOverlay.top"),
        BOTTOM_OVERLAYS("SystemUiOverlay.bottom");
        
        @NonNull
        private String encodedName;

        private SystemUiOverlay(String str) {
            this.encodedName = str;
        }

        @NonNull
        static SystemUiOverlay fromValue(@NonNull String str) {
            for (SystemUiOverlay systemUiOverlay : values()) {
                if (systemUiOverlay.encodedName.equals(str)) {
                    return systemUiOverlay;
                }
            }
            throw new NoSuchFieldException("No such SystemUiOverlay: " + str);
        }
    }

    public PlatformChannel(@NonNull DartExecutor dartExecutor) {
        this.channel = new MethodChannel(dartExecutor, "flutter/platform", JSONMethodCodec.INSTANCE);
        this.channel.setMethodCallHandler(this.parsingMethodCallHandler);
    }

    /* access modifiers changed from: private */
    @NonNull
    public AppSwitcherDescription decodeAppSwitcherDescription(@NonNull JSONObject jSONObject) {
        int i = jSONObject.getInt("primaryColor");
        if (i != 0) {
            i |= ViewCompat.MEASURED_STATE_MASK;
        }
        return new AppSwitcherDescription(i, jSONObject.getString("label"));
    }

    /* access modifiers changed from: private */
    public int decodeOrientations(@NonNull JSONArray jSONArray) {
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < jSONArray.length(); i++) {
            switch (DeviceOrientation.fromValue(jSONArray.getString(i))) {
                case PORTRAIT_UP:
                    z |= true;
                    break;
                case PORTRAIT_DOWN:
                    z |= true;
                    break;
                case LANDSCAPE_LEFT:
                    z |= true;
                    break;
                case LANDSCAPE_RIGHT:
                    z |= true;
                    break;
            }
            if (!z2) {
                z2 = z;
            }
        }
        switch (z) {
            case false:
                return -1;
            case true:
                return 1;
            case true:
                return 0;
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
                if (z2) {
                    return 9;
                }
                if (z2) {
                    return 8;
                }
                switch (z2) {
                    case true:
                        return 1;
                    case true:
                        return 0;
                }
            case true:
                return 9;
            case true:
                return 12;
            case true:
                return 8;
            case true:
                return 11;
            case true:
                return 2;
            case true:
                return 13;
        }
        return 1;
    }

    /* access modifiers changed from: private */
    @NonNull
    public SystemChromeStyle decodeSystemChromeStyle(@NonNull JSONObject jSONObject) {
        Integer num = null;
        Brightness fromValue = !jSONObject.isNull("systemNavigationBarIconBrightness") ? Brightness.fromValue(jSONObject.getString("systemNavigationBarIconBrightness")) : null;
        Integer valueOf = !jSONObject.isNull("systemNavigationBarColor") ? Integer.valueOf(jSONObject.getInt("systemNavigationBarColor")) : null;
        Brightness fromValue2 = !jSONObject.isNull("statusBarIconBrightness") ? Brightness.fromValue(jSONObject.getString("statusBarIconBrightness")) : null;
        Integer valueOf2 = !jSONObject.isNull("statusBarColor") ? Integer.valueOf(jSONObject.getInt("statusBarColor")) : null;
        if (!jSONObject.isNull("systemNavigationBarDividerColor")) {
            num = Integer.valueOf(jSONObject.getInt("systemNavigationBarDividerColor"));
        }
        return new SystemChromeStyle(valueOf2, fromValue2, valueOf, fromValue, num);
    }

    /* access modifiers changed from: private */
    @NonNull
    public List<SystemUiOverlay> decodeSystemUiOverlays(@NonNull JSONArray jSONArray) {
        SystemUiOverlay systemUiOverlay;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            switch (SystemUiOverlay.fromValue(jSONArray.getString(i))) {
                case TOP_OVERLAYS:
                    systemUiOverlay = SystemUiOverlay.TOP_OVERLAYS;
                    break;
                case BOTTOM_OVERLAYS:
                    systemUiOverlay = SystemUiOverlay.BOTTOM_OVERLAYS;
                    break;
            }
            arrayList.add(systemUiOverlay);
        }
        return arrayList;
    }

    public void setPlatformMessageHandler(@Nullable PlatformMessageHandler platformMessageHandler2) {
        this.platformMessageHandler = platformMessageHandler2;
    }
}
