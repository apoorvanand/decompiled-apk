package co.apperto.fastqrreaderview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import co.apperto.fastqrreaderview.common.CameraSource;
import co.apperto.fastqrreaderview.common.CameraSourcePreview;
import co.apperto.fastqrreaderview.java.barcodescanning.BarcodeScanningProcessor;
import co.apperto.fastqrreaderview.java.barcodescanning.OnCodeScanned;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.view.FlutterView;
import io.flutter.view.TextureRegistry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class FastQrReaderViewPlugin implements MethodChannel.MethodCallHandler, PluginRegistry.RequestPermissionsResultListener {
    private static final int CAMERA_REQUEST_ID = 513469796;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray() {
        {
            append(0, 0);
            append(1, 90);
            append(2, 180);
            append(3, 270);
        }
    };
    private static final int REQUEST_PERMISSION = 47;
    private static final String TAG = "FastQrReaderViewPlugin";
    /* access modifiers changed from: private */
    public static CameraManager cameraManager;
    /* access modifiers changed from: private */
    public static MethodChannel channel;
    /* access modifiers changed from: private */
    public Activity activity;
    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
            if (activity == FastQrReaderViewPlugin.this.activity && FastQrReaderViewPlugin.this.camera != null && FastQrReaderViewPlugin.this.camera.preview != null) {
                FastQrReaderViewPlugin.this.camera.preview.stop();
            }
        }

        public void onActivityResumed(Activity activity) {
            if (FastQrReaderViewPlugin.this.requestingPermission) {
                boolean unused = FastQrReaderViewPlugin.this.requestingPermission = false;
            } else if (activity == FastQrReaderViewPlugin.this.activity && FastQrReaderViewPlugin.this.camera != null) {
                FastQrReaderViewPlugin.this.camera.startCameraSource();
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
            if (activity == FastQrReaderViewPlugin.this.activity && FastQrReaderViewPlugin.this.camera != null) {
                if (FastQrReaderViewPlugin.this.camera.preview != null) {
                    FastQrReaderViewPlugin.this.camera.preview.stop();
                }
                if (FastQrReaderViewPlugin.this.camera.cameraSource != null) {
                    FastQrReaderViewPlugin.this.camera.cameraSource.release();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public QrReader camera;
    /* access modifiers changed from: private */
    public Runnable cameraPermissionContinuation;
    private MethodChannel.Result permissionResult;
    /* access modifiers changed from: private */
    public PluginRegistry.Registrar registrar;
    /* access modifiers changed from: private */
    public boolean requestingPermission;
    /* access modifiers changed from: private */
    public final FlutterView view;

    private class CameraRequestPermissionsListener implements PluginRegistry.RequestPermissionsResultListener {
        private CameraRequestPermissionsListener() {
        }

        public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
            if (i != FastQrReaderViewPlugin.CAMERA_REQUEST_ID) {
                return false;
            }
            FastQrReaderViewPlugin.this.cameraPermissionContinuation.run();
            return true;
        }
    }

    private static class CompareSizesByArea implements Comparator<Size> {
        private CompareSizesByArea() {
        }

        public int compare(Size size, Size size2) {
            return Long.signum((((long) size.getWidth()) * ((long) size.getHeight())) - (((long) size2.getWidth()) * ((long) size2.getHeight())));
        }
    }

    private class QrReader {
        private static final int PERMISSION_REQUESTS = 1;
        BarcodeScanningProcessor a;
        ArrayList<Integer> b;
        private String cameraName;
        /* access modifiers changed from: private */
        public CameraSource cameraSource = null;
        private Size captureSize;
        /* access modifiers changed from: private */
        public EventChannel.EventSink eventSink;
        private boolean isFrontFacing;
        /* access modifiers changed from: private */
        public CameraSourcePreview preview;
        private Size previewSize;
        /* access modifiers changed from: private */
        public boolean scanning;
        private int sensorOrientation;
        private final TextureRegistry.SurfaceTextureEntry textureEntry;
        private Size videoSize;

        QrReader(String str, String str2, ArrayList<String> arrayList, final MethodChannel.Result result) {
            String str3;
            String str4;
            Size size;
            HashMap hashMap = new HashMap();
            hashMap.put("codabar", 8);
            hashMap.put("code39", 2);
            hashMap.put("code93", 4);
            hashMap.put("code128", 1);
            hashMap.put("ean8", 64);
            hashMap.put("ean13", 32);
            hashMap.put("itf", 128);
            hashMap.put("upca", 512);
            hashMap.put("upce", 1024);
            hashMap.put("aztec", 4096);
            hashMap.put("datamatrix", 16);
            hashMap.put("pdf417", 2048);
            hashMap.put("qr", 256);
            this.b = new ArrayList<>();
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (hashMap.get(next) != null) {
                    this.b.add(hashMap.get(next));
                }
            }
            this.textureEntry = FastQrReaderViewPlugin.this.view.createSurfaceTexture();
            char c2 = 65535;
            try {
                int hashCode = str2.hashCode();
                if (hashCode != -1078030475) {
                    if (hashCode != 107348) {
                        if (hashCode == 3202466) {
                            if (str2.equals("high")) {
                                c2 = 0;
                            }
                        }
                    } else if (str2.equals("low")) {
                        c2 = 2;
                    }
                } else if (str2.equals(FirebaseAnalytics.Param.MEDIUM)) {
                    c2 = 1;
                }
                switch (c2) {
                    case 0:
                        size = new Size(1024, 768);
                        break;
                    case 1:
                        size = new Size(640, 480);
                        break;
                    case 2:
                        size = new Size(320, 240);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown preset: " + str2);
                }
                CameraCharacteristics cameraCharacteristics = FastQrReaderViewPlugin.cameraManager.getCameraCharacteristics(str);
                StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                this.sensorOrientation = ((Integer) cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                this.isFrontFacing = ((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
                computeBestCaptureSize(streamConfigurationMap);
                computeBestPreviewAndRecordingSize(streamConfigurationMap, size, this.captureSize);
                if (FastQrReaderViewPlugin.this.cameraPermissionContinuation != null) {
                    result.error("cameraPermission", "Camera permission request ongoing", (Object) null);
                }
                Runnable unused = FastQrReaderViewPlugin.this.cameraPermissionContinuation = new Runnable(FastQrReaderViewPlugin.this) {
                    public void run() {
                        Runnable unused = FastQrReaderViewPlugin.this.cameraPermissionContinuation = null;
                        if (!QrReader.this.hasCameraPermission()) {
                            result.error("cameraPermission", "MediaRecorderCamera permission not granted", (Object) null);
                        } else {
                            QrReader.this.open(result);
                        }
                    }
                };
                boolean unused2 = FastQrReaderViewPlugin.this.requestingPermission = false;
                if (hasCameraPermission()) {
                    FastQrReaderViewPlugin.this.cameraPermissionContinuation.run();
                } else if (Build.VERSION.SDK_INT >= 23) {
                    boolean unused3 = FastQrReaderViewPlugin.this.requestingPermission = true;
                    FastQrReaderViewPlugin.this.registrar.activity().requestPermissions(new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}, FastQrReaderViewPlugin.CAMERA_REQUEST_ID);
                }
            } catch (CameraAccessException e) {
                str3 = "CameraAccess";
                str4 = e.getMessage();
                result.error(str3, str4, (Object) null);
            } catch (IllegalArgumentException e2) {
                str3 = "IllegalArgumentException";
                str4 = e2.getMessage();
                result.error(str3, str4, (Object) null);
            }
        }

        /* access modifiers changed from: private */
        public void close() {
            CameraSourcePreview cameraSourcePreview = this.preview;
            if (cameraSourcePreview != null) {
                cameraSourcePreview.stop();
            }
            CameraSource cameraSource2 = this.cameraSource;
            if (cameraSource2 != null) {
                cameraSource2.release();
            }
            QrReader unused = FastQrReaderViewPlugin.this.camera = null;
        }

        private void computeBestCaptureSize(StreamConfigurationMap streamConfigurationMap) {
            this.captureSize = (Size) Collections.max(Arrays.asList(streamConfigurationMap.getOutputSizes(35)), new CompareSizesByArea());
        }

        private void computeBestPreviewAndRecordingSize(StreamConfigurationMap streamConfigurationMap, Size size, Size size2) {
            Size size3;
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(SurfaceTexture.class);
            float width = ((float) size2.getWidth()) / ((float) size2.getHeight());
            ArrayList arrayList = new ArrayList();
            for (Size size4 : outputSizes) {
                if (((float) size4.getWidth()) / ((float) size4.getHeight()) == width && size.getWidth() < size4.getWidth() && size.getHeight() < size4.getHeight()) {
                    arrayList.add(size4);
                }
            }
            Collections.sort(arrayList, new CompareSizesByArea());
            if (arrayList.isEmpty()) {
                this.previewSize = outputSizes[0];
                size3 = outputSizes[0];
            } else {
                this.previewSize = (Size) arrayList.get(0);
                this.videoSize = (Size) arrayList.get(0);
                int size5 = arrayList.size() - 1;
                while (size5 >= 0) {
                    if (((Size) arrayList.get(size5)).getHeight() <= 1080) {
                        size3 = (Size) arrayList.get(size5);
                    } else {
                        size5--;
                    }
                }
                return;
            }
            this.videoSize = size3;
        }

        /* access modifiers changed from: private */
        public void dispose() {
            this.textureEntry.release();
            CameraSourcePreview cameraSourcePreview = this.preview;
            if (cameraSourcePreview != null) {
                cameraSourcePreview.stop();
            }
            CameraSource cameraSource2 = this.cameraSource;
            if (cameraSource2 != null) {
                cameraSource2.release();
            }
        }

        /* access modifiers changed from: private */
        public boolean hasCameraPermission() {
            return Build.VERSION.SDK_INT < 23 || FastQrReaderViewPlugin.this.activity.checkSelfPermission("android.permission.CAMERA") == 0;
        }

        /* access modifiers changed from: private */
        @SuppressLint({"MissingPermission"})
        public void open(@Nullable MethodChannel.Result result) {
            if (hasCameraPermission()) {
                this.cameraSource = new CameraSource(FastQrReaderViewPlugin.this.activity);
                this.cameraSource.setFacing(this.isFrontFacing ? 1 : 0);
                this.a = new BarcodeScanningProcessor(this.b);
                this.a.callback = new OnCodeScanned() {
                    public void onCodeScanned(FirebaseVisionBarcode firebaseVisionBarcode) {
                        if (FastQrReaderViewPlugin.this.camera.scanning) {
                            Log.w(FastQrReaderViewPlugin.TAG, "onSuccess: " + firebaseVisionBarcode.getRawValue());
                            FastQrReaderViewPlugin.channel.invokeMethod("updateCode", firebaseVisionBarcode.getRawValue());
                            FastQrReaderViewPlugin.this.stopScanning();
                        }
                    }
                };
                this.cameraSource.setMachineLearningFrameProcessor(this.a);
                this.preview = new CameraSourcePreview(FastQrReaderViewPlugin.this.activity, (AttributeSet) null, this.textureEntry.surfaceTexture());
                startCameraSource();
                registerEventChannel();
                HashMap hashMap = new HashMap();
                hashMap.put("textureId", Long.valueOf(this.textureEntry.id()));
                hashMap.put("previewWidth", Integer.valueOf(this.cameraSource.getPreviewSize().getWidth()));
                hashMap.put("previewHeight", Integer.valueOf(this.cameraSource.getPreviewSize().getHeight()));
                result.success(hashMap);
            } else if (result != null) {
                result.error("cameraPermission", "Camera permission not granted", (Object) null);
            }
        }

        private void registerEventChannel() {
            BinaryMessenger messenger = FastQrReaderViewPlugin.this.registrar.messenger();
            new EventChannel(messenger, "fast_qr_reader_view/cameraEvents" + this.textureEntry.id()).setStreamHandler(new EventChannel.StreamHandler() {
                public void onCancel(Object obj) {
                    EventChannel.EventSink unused = QrReader.this.eventSink = null;
                }

                public void onListen(Object obj, EventChannel.EventSink eventSink) {
                    EventChannel.EventSink unused = QrReader.this.eventSink = eventSink;
                }
            });
        }

        private void sendErrorEvent(String str) {
            if (this.eventSink != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("eventType", "error");
                hashMap.put("errorDescription", str);
                this.eventSink.success(hashMap);
            }
        }

        /* access modifiers changed from: private */
        public void startCameraSource() {
            CameraSource cameraSource2 = this.cameraSource;
            if (cameraSource2 != null) {
                try {
                    if (this.preview == null) {
                        Log.d(FastQrReaderViewPlugin.TAG, "resume: Preview is null");
                    } else {
                        this.preview.start(cameraSource2);
                    }
                } catch (IOException e) {
                    Log.e(FastQrReaderViewPlugin.TAG, "Unable to start camera source.", e);
                    this.cameraSource.release();
                    this.cameraSource = null;
                }
            }
        }
    }

    private FastQrReaderViewPlugin(PluginRegistry.Registrar registrar2, FlutterView flutterView, Activity activity2) {
        this.registrar = registrar2;
        this.view = flutterView;
        this.activity = activity2;
        registrar2.addRequestPermissionsResultListener(new CameraRequestPermissionsListener());
    }

    private void openSettings() {
        Activity activity2 = this.registrar.activity();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + activity2.getPackageName()));
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(268435456);
        activity2.startActivity(intent);
    }

    public static void registerWith(PluginRegistry.Registrar registrar2) {
        channel = new MethodChannel(registrar2.messenger(), "fast_qr_reader_view");
        cameraManager = (CameraManager) registrar2.activity().getSystemService("camera");
        channel.setMethodCallHandler(new FastQrReaderViewPlugin(registrar2, registrar2.view(), registrar2.activity()));
        FastQrReaderViewPlugin fastQrReaderViewPlugin = new FastQrReaderViewPlugin(registrar2, registrar2.view(), registrar2.activity());
        channel.setMethodCallHandler(fastQrReaderViewPlugin);
        registrar2.addRequestPermissionsResultListener(fastQrReaderViewPlugin);
    }

    /* access modifiers changed from: private */
    public void stopScanning() {
        boolean unused = this.camera.scanning = false;
        this.camera.a.shouldThrottle.set(true);
    }

    private void toggleFlash() {
        this.camera.cameraSource.toggleFlash();
    }

    /* access modifiers changed from: package-private */
    public void a(@NonNull MethodChannel.Result result) {
        boolean unused = this.camera.scanning = true;
        this.camera.a.shouldThrottle.set(false);
        result.success((Object) null);
    }

    /* access modifiers changed from: package-private */
    public void b(@NonNull MethodChannel.Result result) {
        stopScanning();
        result.success((Object) null);
    }

    /* access modifiers changed from: package-private */
    public void c(@NonNull MethodChannel.Result result) {
        toggleFlash();
        result.success((Object) null);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0099, code lost:
        c(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x015c, code lost:
        r10.success((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r9, io.flutter.plugin.common.MethodChannel.Result r10) {
        /*
            r8 = this;
            java.lang.String r0 = r9.method
            int r1 = r0.hashCode()
            r2 = 0
            switch(r1) {
                case -2062998829: goto L_0x0068;
                case -2037208347: goto L_0x005e;
                case -762609869: goto L_0x0054;
                case -668845828: goto L_0x0049;
                case 3237136: goto L_0x003f;
                case 686218487: goto L_0x0035;
                case 746581438: goto L_0x002b;
                case 871091088: goto L_0x0021;
                case 1434631203: goto L_0x0017;
                case 1671767583: goto L_0x000c;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0072
        L_0x000c:
            java.lang.String r1 = "dispose"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 9
            goto L_0x0073
        L_0x0017:
            java.lang.String r1 = "settings"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 7
            goto L_0x0073
        L_0x0021:
            java.lang.String r1 = "initialize"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 2
            goto L_0x0073
        L_0x002b:
            java.lang.String r1 = "requestPermission"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 6
            goto L_0x0073
        L_0x0035:
            java.lang.String r1 = "checkPermission"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 5
            goto L_0x0073
        L_0x003f:
            java.lang.String r1 = "init"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 0
            goto L_0x0073
        L_0x0049:
            java.lang.String r1 = "toggleFlash"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 8
            goto L_0x0073
        L_0x0054:
            java.lang.String r1 = "startScanning"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 3
            goto L_0x0073
        L_0x005e:
            java.lang.String r1 = "availableCameras"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 1
            goto L_0x0073
        L_0x0068:
            java.lang.String r1 = "stopScanning"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0072
            r0 = 4
            goto L_0x0073
        L_0x0072:
            r0 = -1
        L_0x0073:
            r1 = 0
            switch(r0) {
                case 0: goto L_0x0155;
                case 1: goto L_0x00fe;
                case 2: goto L_0x00d1;
                case 3: goto L_0x00cc;
                case 4: goto L_0x00c7;
                case 5: goto L_0x00b3;
                case 6: goto L_0x009e;
                case 7: goto L_0x0096;
                case 8: goto L_0x0099;
                case 9: goto L_0x007c;
                default: goto L_0x0077;
            }
        L_0x0077:
            r10.notImplemented()
            goto L_0x015f
        L_0x007c:
            co.apperto.fastqrreaderview.FastQrReaderViewPlugin$QrReader r9 = r8.camera
            if (r9 == 0) goto L_0x0083
            r9.dispose()
        L_0x0083:
            android.app.Activity r9 = r8.activity
            if (r9 == 0) goto L_0x015c
            android.app.Application$ActivityLifecycleCallbacks r0 = r8.activityLifecycleCallbacks
            if (r0 == 0) goto L_0x015c
            android.app.Application r9 = r9.getApplication()
            android.app.Application$ActivityLifecycleCallbacks r0 = r8.activityLifecycleCallbacks
            r9.unregisterActivityLifecycleCallbacks(r0)
            goto L_0x015c
        L_0x0096:
            r8.openSettings()
        L_0x0099:
            r8.c((io.flutter.plugin.common.MethodChannel.Result) r10)
            goto L_0x015f
        L_0x009e:
            r8.permissionResult = r10
            io.flutter.plugin.common.PluginRegistry$Registrar r9 = r8.registrar
            android.app.Activity r9 = r9.activity()
            java.lang.String r10 = "android.permission.CAMERA"
            java.lang.String[] r10 = new java.lang.String[]{r10}
            r0 = 47
            androidx.core.app.ActivityCompat.requestPermissions(r9, r10, r0)
            goto L_0x015f
        L_0x00b3:
            android.app.Activity r9 = r8.activity
            java.lang.String r0 = "android.permission.CAMERA"
            int r9 = androidx.core.content.ContextCompat.checkSelfPermission(r9, r0)
            if (r9 != 0) goto L_0x00c0
            java.lang.String r9 = "granted"
            goto L_0x00c2
        L_0x00c0:
            java.lang.String r9 = "denied"
        L_0x00c2:
            r10.success(r9)
            goto L_0x015f
        L_0x00c7:
            r8.b((io.flutter.plugin.common.MethodChannel.Result) r10)
            goto L_0x015f
        L_0x00cc:
            r8.a((io.flutter.plugin.common.MethodChannel.Result) r10)
            goto L_0x015f
        L_0x00d1:
            java.lang.String r0 = "cameraName"
            java.lang.Object r0 = r9.argument(r0)
            r3 = r0
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r0 = "resolutionPreset"
            java.lang.Object r0 = r9.argument(r0)
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r0 = "codeFormats"
            java.lang.Object r9 = r9.argument(r0)
            r5 = r9
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            co.apperto.fastqrreaderview.FastQrReaderViewPlugin$QrReader r9 = r8.camera
            if (r9 == 0) goto L_0x00f3
            r9.close()
        L_0x00f3:
            co.apperto.fastqrreaderview.FastQrReaderViewPlugin$QrReader r9 = new co.apperto.fastqrreaderview.FastQrReaderViewPlugin$QrReader
            r1 = r9
            r2 = r8
            r6 = r10
            r1.<init>(r3, r4, r5, r6)
            r8.camera = r9
            goto L_0x015f
        L_0x00fe:
            android.hardware.camera2.CameraManager r9 = cameraManager     // Catch:{ CameraAccessException -> 0x014a }
            java.lang.String[] r9 = r9.getCameraIdList()     // Catch:{ CameraAccessException -> 0x014a }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ CameraAccessException -> 0x014a }
            r0.<init>()     // Catch:{ CameraAccessException -> 0x014a }
            int r3 = r9.length     // Catch:{ CameraAccessException -> 0x014a }
        L_0x010a:
            if (r2 >= r3) goto L_0x0146
            r4 = r9[r2]     // Catch:{ CameraAccessException -> 0x014a }
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ CameraAccessException -> 0x014a }
            r5.<init>()     // Catch:{ CameraAccessException -> 0x014a }
            android.hardware.camera2.CameraManager r6 = cameraManager     // Catch:{ CameraAccessException -> 0x014a }
            android.hardware.camera2.CameraCharacteristics r6 = r6.getCameraCharacteristics(r4)     // Catch:{ CameraAccessException -> 0x014a }
            java.lang.String r7 = "name"
            r5.put(r7, r4)     // Catch:{ CameraAccessException -> 0x014a }
            android.hardware.camera2.CameraCharacteristics$Key r4 = android.hardware.camera2.CameraCharacteristics.LENS_FACING     // Catch:{ CameraAccessException -> 0x014a }
            java.lang.Object r4 = r6.get(r4)     // Catch:{ CameraAccessException -> 0x014a }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ CameraAccessException -> 0x014a }
            int r4 = r4.intValue()     // Catch:{ CameraAccessException -> 0x014a }
            switch(r4) {
                case 0: goto L_0x013b;
                case 1: goto L_0x0136;
                case 2: goto L_0x012e;
                default: goto L_0x012d;
            }     // Catch:{ CameraAccessException -> 0x014a }
        L_0x012d:
            goto L_0x0140
        L_0x012e:
            java.lang.String r4 = "lensFacing"
            java.lang.String r6 = "external"
        L_0x0132:
            r5.put(r4, r6)     // Catch:{ CameraAccessException -> 0x014a }
            goto L_0x0140
        L_0x0136:
            java.lang.String r4 = "lensFacing"
            java.lang.String r6 = "back"
            goto L_0x0132
        L_0x013b:
            java.lang.String r4 = "lensFacing"
            java.lang.String r6 = "front"
            goto L_0x0132
        L_0x0140:
            r0.add(r5)     // Catch:{ CameraAccessException -> 0x014a }
            int r2 = r2 + 1
            goto L_0x010a
        L_0x0146:
            r10.success(r0)     // Catch:{ CameraAccessException -> 0x014a }
            goto L_0x015f
        L_0x014a:
            r9 = move-exception
            java.lang.String r0 = "cameraAccess"
            java.lang.String r9 = r9.getMessage()
            r10.error(r0, r9, r1)
            goto L_0x015f
        L_0x0155:
            co.apperto.fastqrreaderview.FastQrReaderViewPlugin$QrReader r9 = r8.camera
            if (r9 == 0) goto L_0x015c
            r9.close()
        L_0x015c:
            r10.success(r1)
        L_0x015f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: co.apperto.fastqrreaderview.FastQrReaderViewPlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }

    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        String str;
        MethodChannel.Result result;
        if (i != 47) {
            return false;
        }
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            String str2 = strArr[i2];
            if (iArr[i2] == -1) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this.activity, str2)) {
                    result = this.permissionResult;
                    str = "dismissedForever";
                } else {
                    result = this.permissionResult;
                    str = "denied";
                }
            } else if (iArr[i2] == 0) {
                result = this.permissionResult;
                str = "granted";
            } else {
                result = this.permissionResult;
                str = "unknown";
            }
            result.success(str);
        }
        return true;
    }
}
