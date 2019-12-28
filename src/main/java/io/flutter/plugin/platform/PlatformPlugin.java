package io.flutter.plugin.platform;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.embedding.engine.systemchannels.PlatformChannel;
import java.util.List;

public class PlatformPlugin {
    public static final int DEFAULT_SYSTEM_UI = 1280;
    private final Activity activity;
    private PlatformChannel.SystemChromeStyle currentTheme;
    private int mEnabledOverlays;
    private final PlatformChannel.PlatformMessageHandler mPlatformMessageHandler = new PlatformChannel.PlatformMessageHandler() {
        public CharSequence getClipboardData(@Nullable PlatformChannel.ClipboardContentFormat clipboardContentFormat) {
            return PlatformPlugin.this.getClipboardData(clipboardContentFormat);
        }

        public void playSystemSound(@NonNull PlatformChannel.SoundType soundType) {
            PlatformPlugin.this.playSystemSound(soundType);
        }

        public void popSystemNavigator() {
            PlatformPlugin.this.popSystemNavigator();
        }

        public void restoreSystemUiOverlays() {
            PlatformPlugin.this.restoreSystemChromeSystemUIOverlays();
        }

        public void setApplicationSwitcherDescription(@NonNull PlatformChannel.AppSwitcherDescription appSwitcherDescription) {
            PlatformPlugin.this.setSystemChromeApplicationSwitcherDescription(appSwitcherDescription);
        }

        public void setClipboardData(@NonNull String str) {
            PlatformPlugin.this.setClipboardData(str);
        }

        public void setPreferredOrientations(int i) {
            PlatformPlugin.this.setSystemChromePreferredOrientations(i);
        }

        public void setSystemUiOverlayStyle(@NonNull PlatformChannel.SystemChromeStyle systemChromeStyle) {
            PlatformPlugin.this.setSystemChromeSystemUIOverlayStyle(systemChromeStyle);
        }

        public void showSystemOverlays(@NonNull List<PlatformChannel.SystemUiOverlay> list) {
            PlatformPlugin.this.setSystemChromeEnabledSystemUIOverlays(list);
        }

        public void vibrateHapticFeedback(@NonNull PlatformChannel.HapticFeedbackType hapticFeedbackType) {
            PlatformPlugin.this.vibrateHapticFeedback(hapticFeedbackType);
        }
    };
    private final PlatformChannel platformChannel;

    public PlatformPlugin(Activity activity2, PlatformChannel platformChannel2) {
        this.activity = activity2;
        this.platformChannel = platformChannel2;
        this.platformChannel.setPlatformMessageHandler(this.mPlatformMessageHandler);
        this.mEnabledOverlays = DEFAULT_SYSTEM_UI;
    }

    /* access modifiers changed from: private */
    public CharSequence getClipboardData(PlatformChannel.ClipboardContentFormat clipboardContentFormat) {
        ClipData primaryClip = ((ClipboardManager) this.activity.getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null) {
            return null;
        }
        if (clipboardContentFormat == null || clipboardContentFormat == PlatformChannel.ClipboardContentFormat.PLAIN_TEXT) {
            return primaryClip.getItemAt(0).coerceToText(this.activity);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void playSystemSound(PlatformChannel.SoundType soundType) {
        if (soundType == PlatformChannel.SoundType.CLICK) {
            this.activity.getWindow().getDecorView().playSoundEffect(0);
        }
    }

    /* access modifiers changed from: private */
    public void popSystemNavigator() {
        this.activity.finish();
    }

    /* access modifiers changed from: private */
    public void restoreSystemChromeSystemUIOverlays() {
        updateSystemUiOverlays();
    }

    /* access modifiers changed from: private */
    public void setClipboardData(String str) {
        ((ClipboardManager) this.activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", str));
    }

    /* access modifiers changed from: private */
    public void setSystemChromeApplicationSwitcherDescription(PlatformChannel.AppSwitcherDescription appSwitcherDescription) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (Build.VERSION.SDK_INT < 28 && Build.VERSION.SDK_INT > 21) {
                this.activity.setTaskDescription(new ActivityManager.TaskDescription(appSwitcherDescription.label, (Bitmap) null, appSwitcherDescription.color));
            }
            if (Build.VERSION.SDK_INT >= 28) {
                this.activity.setTaskDescription(new ActivityManager.TaskDescription(appSwitcherDescription.label, 0, appSwitcherDescription.color));
            }
        }
    }

    /* access modifiers changed from: private */
    public void setSystemChromeEnabledSystemUIOverlays(List<PlatformChannel.SystemUiOverlay> list) {
        int i = list.size() == 0 ? 5894 : 1798;
        for (int i2 = 0; i2 < list.size(); i2++) {
            switch (list.get(i2)) {
                case TOP_OVERLAYS:
                    i &= -5;
                    break;
                case BOTTOM_OVERLAYS:
                    i = i & -513 & -3;
                    break;
            }
        }
        this.mEnabledOverlays = i;
        updateSystemUiOverlays();
    }

    /* access modifiers changed from: private */
    public void setSystemChromePreferredOrientations(int i) {
        this.activity.setRequestedOrientation(i);
    }

    /* access modifiers changed from: private */
    public void setSystemChromeSystemUIOverlayStyle(PlatformChannel.SystemChromeStyle systemChromeStyle) {
        Window window = this.activity.getWindow();
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= 26) {
            if (systemChromeStyle.systemNavigationBarIconBrightness != null) {
                switch (systemChromeStyle.systemNavigationBarIconBrightness) {
                    case DARK:
                        systemUiVisibility |= 16;
                        break;
                    case LIGHT:
                        systemUiVisibility &= -17;
                        break;
                }
            }
            if (systemChromeStyle.systemNavigationBarColor != null) {
                window.setNavigationBarColor(systemChromeStyle.systemNavigationBarColor.intValue());
            }
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (systemChromeStyle.statusBarIconBrightness != null) {
                switch (systemChromeStyle.statusBarIconBrightness) {
                    case DARK:
                        systemUiVisibility |= 8192;
                        break;
                    case LIGHT:
                        systemUiVisibility &= -8193;
                        break;
                }
            }
            if (systemChromeStyle.statusBarColor != null) {
                window.setStatusBarColor(systemChromeStyle.statusBarColor.intValue());
            }
        }
        Integer num = systemChromeStyle.systemNavigationBarDividerColor;
        decorView.setSystemUiVisibility(systemUiVisibility);
        this.currentTheme = systemChromeStyle;
    }

    /* access modifiers changed from: private */
    public void vibrateHapticFeedback(PlatformChannel.HapticFeedbackType hapticFeedbackType) {
        int i;
        View decorView = this.activity.getWindow().getDecorView();
        switch (hapticFeedbackType) {
            case STANDARD:
                i = 0;
                break;
            case LIGHT_IMPACT:
                i = 1;
                break;
            case MEDIUM_IMPACT:
                i = 3;
                break;
            case HEAVY_IMPACT:
                i = 6;
                break;
            case SELECTION_CLICK:
                i = 4;
                break;
            default:
                return;
        }
        decorView.performHapticFeedback(i);
    }

    public void destroy() {
        this.platformChannel.setPlatformMessageHandler((PlatformChannel.PlatformMessageHandler) null);
    }

    public void updateSystemUiOverlays() {
        this.activity.getWindow().getDecorView().setSystemUiVisibility(this.mEnabledOverlays);
        PlatformChannel.SystemChromeStyle systemChromeStyle = this.currentTheme;
        if (systemChromeStyle != null) {
            setSystemChromeSystemUIOverlayStyle(systemChromeStyle);
        }
    }
}
