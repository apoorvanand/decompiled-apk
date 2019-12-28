package io.flutter.plugin.common;

import android.util.Log;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.MethodChannel;

public class ErrorLogResult implements MethodChannel.Result {
    private int level;
    private String tag;

    public ErrorLogResult(String str) {
        this(str, 5);
    }

    public ErrorLogResult(String str, int i) {
        this.tag = str;
        this.level = i;
    }

    public void error(String str, @Nullable String str2, @Nullable Object obj) {
        String str3;
        if (obj != null) {
            str3 = " details: " + obj;
        } else {
            str3 = "";
        }
        int i = this.level;
        if (i >= 5) {
            Log.println(i, this.tag, str2 + str3);
        }
    }

    public void notImplemented() {
        int i = this.level;
        if (i >= 5) {
            Log.println(i, this.tag, "method not implemented");
        }
    }

    public void success(@Nullable Object obj) {
    }
}
