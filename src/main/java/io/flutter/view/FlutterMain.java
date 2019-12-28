package io.flutter.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.util.PathUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FlutterMain {
    private static final String AOT_SHARED_LIBRARY_NAME = "aot-shared-library-name";
    private static final String DEFAULT_AOT_SHARED_LIBRARY_NAME = "libapp.so";
    private static final String DEFAULT_FLUTTER_ASSETS_DIR = "flutter_assets";
    private static final String DEFAULT_ISOLATE_SNAPSHOT_DATA = "isolate_snapshot_data";
    private static final String DEFAULT_KERNEL_BLOB = "kernel_blob.bin";
    private static final String DEFAULT_LIBRARY = "libflutter.so";
    private static final String DEFAULT_VM_SNAPSHOT_DATA = "vm_snapshot_data";
    private static final String FLUTTER_ASSETS_DIR_KEY = "flutter-assets-dir";
    private static final String ISOLATE_SNAPSHOT_DATA_KEY = "isolate-snapshot-data";
    public static final String PUBLIC_AOT_SHARED_LIBRARY_NAME = (FlutterMain.class.getName() + '.' + AOT_SHARED_LIBRARY_NAME);
    public static final String PUBLIC_FLUTTER_ASSETS_DIR_KEY = (FlutterMain.class.getName() + '.' + FLUTTER_ASSETS_DIR_KEY);
    public static final String PUBLIC_ISOLATE_SNAPSHOT_DATA_KEY = (FlutterMain.class.getName() + '.' + ISOLATE_SNAPSHOT_DATA_KEY);
    public static final String PUBLIC_VM_SNAPSHOT_DATA_KEY = (FlutterMain.class.getName() + '.' + VM_SNAPSHOT_DATA_KEY);
    private static final String SNAPSHOT_ASSET_PATH_KEY = "snapshot-asset-path";
    private static final String TAG = "FlutterMain";
    private static final String VM_SNAPSHOT_DATA_KEY = "vm-snapshot-data";
    private static boolean isRunningInRobolectricTest = false;
    private static String sAotSharedLibraryName = DEFAULT_AOT_SHARED_LIBRARY_NAME;
    private static String sFlutterAssetsDir = DEFAULT_FLUTTER_ASSETS_DIR;
    private static boolean sInitialized = false;
    private static String sIsolateSnapshotData = DEFAULT_ISOLATE_SNAPSHOT_DATA;
    /* access modifiers changed from: private */
    @Nullable
    public static ResourceExtractor sResourceExtractor;
    @Nullable
    private static Settings sSettings;
    private static String sVmSnapshotData = DEFAULT_VM_SNAPSHOT_DATA;

    public static class Settings {
        private String logTag;

        @Nullable
        public String getLogTag() {
            return this.logTag;
        }

        public void setLogTag(String str) {
            this.logTag = str;
        }
    }

    public static void ensureInitializationComplete(@NonNull Context context, @Nullable String[] strArr) {
        if (!isRunningInRobolectricTest) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("ensureInitializationComplete must be called on the main thread");
            } else if (sSettings == null) {
                throw new IllegalStateException("ensureInitializationComplete must be called after startInitialization");
            } else if (!sInitialized) {
                try {
                    if (sResourceExtractor != null) {
                        sResourceExtractor.waitForCompletion();
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("--icu-symbol-prefix=_binary_icudtl_dat");
                    ApplicationInfo applicationInfo = getApplicationInfo(context);
                    arrayList.add("--icu-native-lib-path=" + applicationInfo.nativeLibraryDir + File.separator + DEFAULT_LIBRARY);
                    if (strArr != null) {
                        Collections.addAll(arrayList, strArr);
                    }
                    arrayList.add("--aot-shared-library-name=" + sAotSharedLibraryName);
                    arrayList.add("--aot-shared-library-name=" + applicationInfo.nativeLibraryDir + File.separator + sAotSharedLibraryName);
                    StringBuilder sb = new StringBuilder();
                    sb.append("--cache-dir-path=");
                    sb.append(PathUtils.getCacheDirectory(context));
                    arrayList.add(sb.toString());
                    if (sSettings.getLogTag() != null) {
                        arrayList.add("--log-tag=" + sSettings.getLogTag());
                    }
                    FlutterJNI.nativeInit(context, (String[]) arrayList.toArray(new String[0]), (String) null, PathUtils.getFilesDir(context), PathUtils.getCacheDirectory(context));
                    sInitialized = true;
                } catch (Exception e) {
                    Log.e(TAG, "Flutter initialization failed.", e);
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void ensureInitializationCompleteAsync(@NonNull final Context context, @Nullable final String[] strArr, @NonNull final Handler handler, @NonNull final Runnable runnable) {
        if (!isRunningInRobolectricTest) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("ensureInitializationComplete must be called on the main thread");
            } else if (sSettings == null) {
                throw new IllegalStateException("ensureInitializationComplete must be called after startInitialization");
            } else if (!sInitialized) {
                new Thread(new Runnable() {
                    public void run() {
                        if (FlutterMain.sResourceExtractor != null) {
                            FlutterMain.sResourceExtractor.waitForCompletion();
                        }
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                FlutterMain.ensureInitializationComplete(context.getApplicationContext(), strArr);
                                handler.post(runnable);
                            }
                        });
                    }
                }).start();
            }
        }
    }

    @NonNull
    public static String findAppBundlePath() {
        return sFlutterAssetsDir;
    }

    @Deprecated
    @Nullable
    public static String findAppBundlePath(@NonNull Context context) {
        return sFlutterAssetsDir;
    }

    @NonNull
    private static String fromFlutterAssets(@NonNull String str) {
        return sFlutterAssetsDir + File.separator + str;
    }

    @NonNull
    private static ApplicationInfo getApplicationInfo(@NonNull Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public static String getLookupKeyForAsset(@NonNull String str) {
        return fromFlutterAssets(str);
    }

    @NonNull
    public static String getLookupKeyForAsset(@NonNull String str, @NonNull String str2) {
        return getLookupKeyForAsset("packages" + File.separator + str2 + File.separator + str);
    }

    private static void initConfig(@NonNull Context context) {
        Bundle bundle = getApplicationInfo(context).metaData;
        if (bundle != null) {
            sAotSharedLibraryName = bundle.getString(PUBLIC_AOT_SHARED_LIBRARY_NAME, DEFAULT_AOT_SHARED_LIBRARY_NAME);
            sFlutterAssetsDir = bundle.getString(PUBLIC_FLUTTER_ASSETS_DIR_KEY, DEFAULT_FLUTTER_ASSETS_DIR);
            sVmSnapshotData = bundle.getString(PUBLIC_VM_SNAPSHOT_DATA_KEY, DEFAULT_VM_SNAPSHOT_DATA);
            sIsolateSnapshotData = bundle.getString(PUBLIC_ISOLATE_SNAPSHOT_DATA_KEY, DEFAULT_ISOLATE_SNAPSHOT_DATA);
        }
    }

    private static void initResources(@NonNull Context context) {
        new ResourceCleaner(context).start();
    }

    @VisibleForTesting
    public static void setIsRunningInRobolectricTest(boolean z) {
        isRunningInRobolectricTest = z;
    }

    public static void startInitialization(@NonNull Context context) {
        if (!isRunningInRobolectricTest) {
            startInitialization(context, new Settings());
        }
    }

    public static void startInitialization(@NonNull Context context, @NonNull Settings settings) {
        if (!isRunningInRobolectricTest) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("startInitialization must be called on the main thread");
            } else if (sSettings == null) {
                sSettings = settings;
                long uptimeMillis = SystemClock.uptimeMillis();
                initConfig(context);
                initResources(context);
                System.loadLibrary("flutter");
                VsyncWaiter.getInstance((WindowManager) context.getSystemService("window")).init();
                FlutterJNI.nativeRecordStartTimestamp(SystemClock.uptimeMillis() - uptimeMillis);
            }
        }
    }
}
