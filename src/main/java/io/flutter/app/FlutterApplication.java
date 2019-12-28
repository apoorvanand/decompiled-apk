package io.flutter.app;

import android.app.Activity;
import android.app.Application;
import androidx.annotation.CallSuper;
import io.flutter.view.FlutterMain;

public class FlutterApplication extends Application {
    private Activity mCurrentActivity = null;

    public Activity getCurrentActivity() {
        return this.mCurrentActivity;
    }

    @CallSuper
    public void onCreate() {
        super.onCreate();
        FlutterMain.startInitialization(this);
    }

    public void setCurrentActivity(Activity activity) {
        this.mCurrentActivity = activity;
    }
}
