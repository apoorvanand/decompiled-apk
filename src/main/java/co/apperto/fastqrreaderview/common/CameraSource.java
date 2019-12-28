package co.apperto.fastqrreaderview.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.images.Size;
import io.flutter.plugin.platform.PlatformPlugin;
import java.io.IOException;
import java.lang.Thread;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressLint({"MissingPermission"})
public class CameraSource {
    private static final float ASPECT_RATIO_TOLERANCE = 0.01f;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_FRONT = 1;
    private static final int DUMMY_TEXTURE_NAME = 100;
    private static final String TAG = "MIDemoApp:CameraSource";
    protected Activity a;
    protected int b = 0;
    /* access modifiers changed from: private */
    public final Map<byte[], ByteBuffer> bytesToByteBuffer = new IdentityHashMap();
    /* access modifiers changed from: private */
    public Camera camera;
    private SurfaceTexture dummySurfaceTexture;
    /* access modifiers changed from: private */
    public VisionImageProcessor frameProcessor;
    /* access modifiers changed from: private */
    public Size previewSize;
    /* access modifiers changed from: private */
    public final FrameProcessingRunnable processingRunnable;
    /* access modifiers changed from: private */
    public Thread processingThread;
    /* access modifiers changed from: private */
    public final Object processorLock = new Object();
    private final boolean requestedAutoFocus = true;
    private final float requestedFps = 20.0f;
    private final int requestedPreviewHeight = 960;
    private final int requestedPreviewWidth = PlatformPlugin.DEFAULT_SYSTEM_UI;
    /* access modifiers changed from: private */
    public int rotation;
    private boolean usingSurfaceTexture;

    private class CameraPreviewCallback implements Camera.PreviewCallback {
        private CameraPreviewCallback() {
        }

        public void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.processingRunnable.a(bArr, camera);
        }
    }

    private class FrameProcessingRunnable implements Runnable {
        static final /* synthetic */ boolean a = (!CameraSource.class.desiredAssertionStatus());
        private boolean active = true;
        private final Object lock = new Object();
        private ByteBuffer pendingFrameData;

        FrameProcessingRunnable() {
        }

        /* access modifiers changed from: package-private */
        @SuppressLint({"Assert"})
        public void a() {
            if (!a && CameraSource.this.processingThread.getState() != Thread.State.TERMINATED) {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            synchronized (this.lock) {
                this.active = z;
                this.lock.notifyAll();
            }
        }

        /* access modifiers changed from: package-private */
        public void a(byte[] bArr, Camera camera) {
            synchronized (this.lock) {
                if (this.pendingFrameData != null) {
                    camera.addCallbackBuffer(this.pendingFrameData.array());
                    this.pendingFrameData = null;
                }
                if (!CameraSource.this.bytesToByteBuffer.containsKey(bArr)) {
                    Log.d(CameraSource.TAG, "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.pendingFrameData = (ByteBuffer) CameraSource.this.bytesToByteBuffer.get(bArr);
                this.lock.notifyAll();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r0 = co.apperto.fastqrreaderview.common.CameraSource.d(r5.b);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x002d, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            co.apperto.fastqrreaderview.common.CameraSource.g(r5.b).process(r1, new co.apperto.fastqrreaderview.common.FrameMetadata.Builder().setWidth(co.apperto.fastqrreaderview.common.CameraSource.f(r5.b).getWidth()).setHeight(co.apperto.fastqrreaderview.common.CameraSource.f(r5.b).getHeight()).setRotation(co.apperto.fastqrreaderview.common.CameraSource.e(r5.b)).setCameraFacing(r5.b.b).build());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x006e, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0073, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0075, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            android.util.Log.e(co.apperto.fastqrreaderview.common.CameraSource.TAG, "Exception thrown from receiver.", r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x008c, code lost:
            co.apperto.fastqrreaderview.common.CameraSource.h(r5.b).addCallbackBuffer(r1.array());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0099, code lost:
            throw r0;
         */
        @android.annotation.SuppressLint({"InlinedApi"})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r5 = this;
            L_0x0000:
                java.lang.Object r0 = r5.lock
                monitor-enter(r0)
            L_0x0003:
                boolean r1 = r5.active     // Catch:{ all -> 0x009a }
                if (r1 == 0) goto L_0x001b
                java.nio.ByteBuffer r1 = r5.pendingFrameData     // Catch:{ all -> 0x009a }
                if (r1 != 0) goto L_0x001b
                java.lang.Object r1 = r5.lock     // Catch:{ InterruptedException -> 0x0011 }
                r1.wait()     // Catch:{ InterruptedException -> 0x0011 }
                goto L_0x0003
            L_0x0011:
                r1 = move-exception
                java.lang.String r2 = "MIDemoApp:CameraSource"
                java.lang.String r3 = "Frame processing loop terminated."
                android.util.Log.d(r2, r3, r1)     // Catch:{ all -> 0x009a }
                monitor-exit(r0)     // Catch:{ all -> 0x009a }
                return
            L_0x001b:
                boolean r1 = r5.active     // Catch:{ all -> 0x009a }
                if (r1 != 0) goto L_0x0021
                monitor-exit(r0)     // Catch:{ all -> 0x009a }
                return
            L_0x0021:
                java.nio.ByteBuffer r1 = r5.pendingFrameData     // Catch:{ all -> 0x009a }
                r2 = 0
                r5.pendingFrameData = r2     // Catch:{ all -> 0x009a }
                monitor-exit(r0)     // Catch:{ all -> 0x009a }
                co.apperto.fastqrreaderview.common.CameraSource r0 = co.apperto.fastqrreaderview.common.CameraSource.this     // Catch:{ Throwable -> 0x0075 }
                java.lang.Object r0 = r0.processorLock     // Catch:{ Throwable -> 0x0075 }
                monitor-enter(r0)     // Catch:{ Throwable -> 0x0075 }
                co.apperto.fastqrreaderview.common.CameraSource r2 = co.apperto.fastqrreaderview.common.CameraSource.this     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.VisionImageProcessor r2 = r2.frameProcessor     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.FrameMetadata$Builder r3 = new co.apperto.fastqrreaderview.common.FrameMetadata$Builder     // Catch:{ all -> 0x0070 }
                r3.<init>()     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.CameraSource r4 = co.apperto.fastqrreaderview.common.CameraSource.this     // Catch:{ all -> 0x0070 }
                com.google.android.gms.common.images.Size r4 = r4.previewSize     // Catch:{ all -> 0x0070 }
                int r4 = r4.getWidth()     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.FrameMetadata$Builder r3 = r3.setWidth(r4)     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.CameraSource r4 = co.apperto.fastqrreaderview.common.CameraSource.this     // Catch:{ all -> 0x0070 }
                com.google.android.gms.common.images.Size r4 = r4.previewSize     // Catch:{ all -> 0x0070 }
                int r4 = r4.getHeight()     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.FrameMetadata$Builder r3 = r3.setHeight(r4)     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.CameraSource r4 = co.apperto.fastqrreaderview.common.CameraSource.this     // Catch:{ all -> 0x0070 }
                int r4 = r4.rotation     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.FrameMetadata$Builder r3 = r3.setRotation(r4)     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.CameraSource r4 = co.apperto.fastqrreaderview.common.CameraSource.this     // Catch:{ all -> 0x0070 }
                int r4 = r4.b     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.FrameMetadata$Builder r3 = r3.setCameraFacing(r4)     // Catch:{ all -> 0x0070 }
                co.apperto.fastqrreaderview.common.FrameMetadata r3 = r3.build()     // Catch:{ all -> 0x0070 }
                r2.process((java.nio.ByteBuffer) r1, (co.apperto.fastqrreaderview.common.FrameMetadata) r3)     // Catch:{ all -> 0x0070 }
                monitor-exit(r0)     // Catch:{ all -> 0x0070 }
                goto L_0x007d
            L_0x0070:
                r2 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0070 }
                throw r2     // Catch:{ Throwable -> 0x0075 }
            L_0x0073:
                r0 = move-exception
                goto L_0x008c
            L_0x0075:
                r0 = move-exception
                java.lang.String r2 = "MIDemoApp:CameraSource"
                java.lang.String r3 = "Exception thrown from receiver."
                android.util.Log.e(r2, r3, r0)     // Catch:{ all -> 0x0073 }
            L_0x007d:
                co.apperto.fastqrreaderview.common.CameraSource r0 = co.apperto.fastqrreaderview.common.CameraSource.this
                android.hardware.Camera r0 = r0.camera
                byte[] r1 = r1.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x008c:
                co.apperto.fastqrreaderview.common.CameraSource r2 = co.apperto.fastqrreaderview.common.CameraSource.this
                android.hardware.Camera r2 = r2.camera
                byte[] r1 = r1.array()
                r2.addCallbackBuffer(r1)
                throw r0
            L_0x009a:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x009a }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: co.apperto.fastqrreaderview.common.CameraSource.FrameProcessingRunnable.run():void");
        }
    }

    private static class SizePair {
        private Size picture;
        private final Size preview;

        SizePair(Camera.Size size, @Nullable Camera.Size size2) {
            this.preview = new Size(size.width, size.height);
            if (size2 != null) {
                this.picture = new Size(size2.width, size2.height);
            }
        }

        /* access modifiers changed from: package-private */
        public Size a() {
            return this.preview;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public Size b() {
            return this.picture;
        }
    }

    public CameraSource(Activity activity) {
        this.a = activity;
        this.processingRunnable = new FrameProcessingRunnable();
    }

    private void cleanScreen() {
    }

    @SuppressLint({"InlinedApi"})
    private Camera createCamera() {
        int idForRequestedCamera = getIdForRequestedCamera(this.b);
        if (idForRequestedCamera != -1) {
            Camera open = Camera.open(idForRequestedCamera);
            SizePair selectSizePair = selectSizePair(open, PlatformPlugin.DEFAULT_SYSTEM_UI, 960);
            if (selectSizePair != null) {
                Size b2 = selectSizePair.b();
                this.previewSize = selectSizePair.a();
                int[] selectPreviewFpsRange = selectPreviewFpsRange(open, 20.0f);
                if (selectPreviewFpsRange != null) {
                    Camera.Parameters parameters = open.getParameters();
                    if (b2 != null) {
                        parameters.setPictureSize(b2.getWidth(), b2.getHeight());
                    }
                    parameters.setPreviewSize(this.previewSize.getWidth(), this.previewSize.getHeight());
                    parameters.setPreviewFpsRange(selectPreviewFpsRange[0], selectPreviewFpsRange[1]);
                    parameters.setPreviewFormat(17);
                    setRotation(open, parameters, idForRequestedCamera);
                    if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                        parameters.setFocusMode("continuous-video");
                    } else {
                        Log.i(TAG, "Camera auto focus is not supported on this device.");
                    }
                    open.setParameters(parameters);
                    open.setPreviewCallbackWithBuffer(new CameraPreviewCallback());
                    open.addCallbackBuffer(createPreviewBuffer(this.previewSize));
                    open.addCallbackBuffer(createPreviewBuffer(this.previewSize));
                    open.addCallbackBuffer(createPreviewBuffer(this.previewSize));
                    open.addCallbackBuffer(createPreviewBuffer(this.previewSize));
                    return open;
                }
                throw new IOException("Could not find suitable preview frames per second range.");
            }
            throw new IOException("Could not find suitable preview size.");
        }
        throw new IOException("Could not find requested camera.");
    }

    @SuppressLint({"InlinedApi"})
    private byte[] createPreviewBuffer(Size size) {
        byte[] bArr = new byte[(((int) Math.ceil(((double) ((((long) size.getHeight()) * ((long) size.getWidth())) * ((long) ImageFormat.getBitsPerPixel(17)))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.bytesToByteBuffer.put(bArr, wrap);
        return bArr;
    }

    private static List<SizePair> generateValidPreviewSizeList(Camera camera2) {
        Camera.Parameters parameters = camera2.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        ArrayList arrayList = new ArrayList();
        for (Camera.Size next : supportedPreviewSizes) {
            float f = ((float) next.width) / ((float) next.height);
            Iterator<Camera.Size> it = supportedPictureSizes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Camera.Size next2 = it.next();
                if (Math.abs(f - (((float) next2.width) / ((float) next2.height))) < ASPECT_RATIO_TOLERANCE) {
                    arrayList.add(new SizePair(next, next2));
                    break;
                }
            }
        }
        if (arrayList.size() == 0) {
            Log.w(TAG, "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (Camera.Size sizePair : supportedPreviewSizes) {
                arrayList.add(new SizePair(sizePair, (Camera.Size) null));
            }
        }
        return arrayList;
    }

    private static int getIdForRequestedCamera(int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i2 = 0; i2 < Camera.getNumberOfCameras(); i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == i) {
                return i2;
            }
        }
        return -1;
    }

    @SuppressLint({"InlinedApi"})
    private static int[] selectPreviewFpsRange(Camera camera2, float f) {
        int i = (int) (f * 1000.0f);
        int[] iArr = null;
        int i2 = Integer.MAX_VALUE;
        for (int[] next : camera2.getParameters().getSupportedPreviewFpsRange()) {
            int abs = Math.abs(i - next[0]) + Math.abs(i - next[1]);
            if (abs < i2) {
                iArr = next;
                i2 = abs;
            }
        }
        return iArr;
    }

    private static SizePair selectSizePair(Camera camera2, int i, int i2) {
        SizePair sizePair = null;
        int i3 = Integer.MAX_VALUE;
        for (SizePair next : generateValidPreviewSizeList(camera2)) {
            Size a2 = next.a();
            int abs = Math.abs(a2.getWidth() - i) + Math.abs(a2.getHeight() - i2);
            if (abs < i3) {
                sizePair = next;
                i3 = abs;
            }
        }
        return sizePair;
    }

    private void setRotation(Camera camera2, Camera.Parameters parameters, int i) {
        int i2;
        int i3;
        int rotation2 = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay().getRotation();
        int i4 = 0;
        switch (rotation2) {
            case 0:
                break;
            case 1:
                i4 = 90;
                break;
            case 2:
                i4 = 180;
                break;
            case 3:
                i4 = 270;
                break;
            default:
                Log.e(TAG, "Bad rotation value: " + rotation2);
                break;
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            i2 = (cameraInfo.orientation + i4) % 360;
            i3 = (360 - i2) % 360;
        } else {
            i2 = ((cameraInfo.orientation - i4) + 360) % 360;
            i3 = i2;
        }
        this.rotation = i2 / 90;
        camera2.setDisplayOrientation(i3);
        parameters.setRotation(i2);
    }

    public int getCameraFacing() {
        return this.b;
    }

    public Size getPreviewSize() {
        return this.previewSize;
    }

    public void release() {
        synchronized (this.processorLock) {
            stop();
            this.processingRunnable.a();
            cleanScreen();
            if (this.frameProcessor != null) {
                this.frameProcessor.stop();
            }
        }
    }

    public synchronized void setFacing(int i) {
        if (i == 0 || i == 1) {
            this.b = i;
        } else {
            throw new IllegalArgumentException("Invalid camera: " + i);
        }
    }

    public void setMachineLearningFrameProcessor(VisionImageProcessor visionImageProcessor) {
        synchronized (this.processorLock) {
            cleanScreen();
            if (this.frameProcessor != null) {
                this.frameProcessor.stop();
            }
            this.frameProcessor = visionImageProcessor;
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    @SuppressLint({"MissingPermission"})
    public synchronized CameraSource start() {
        if (this.camera != null) {
            return this;
        }
        this.camera = createCamera();
        this.dummySurfaceTexture = new SurfaceTexture(100);
        this.camera.setPreviewTexture(this.dummySurfaceTexture);
        this.usingSurfaceTexture = true;
        this.camera.startPreview();
        this.processingThread = new Thread(this.processingRunnable);
        this.processingRunnable.a(true);
        this.processingThread.start();
        return this;
    }

    @RequiresPermission("android.permission.CAMERA")
    public synchronized CameraSource start(SurfaceTexture surfaceTexture) {
        if (this.camera != null) {
            return this;
        }
        this.camera = createCamera();
        this.camera.setPreviewTexture(surfaceTexture);
        this.camera.startPreview();
        this.processingThread = new Thread(this.processingRunnable);
        this.processingRunnable.a(true);
        this.processingThread.start();
        this.usingSurfaceTexture = false;
        return this;
    }

    public synchronized void stop() {
        this.processingRunnable.a(false);
        if (this.processingThread != null) {
            try {
                this.processingThread.join();
            } catch (InterruptedException unused) {
                Log.d(TAG, "Frame processing thread interrupted on release.");
            }
            this.processingThread = null;
        }
        if (this.camera != null) {
            this.camera.stopPreview();
            this.camera.setPreviewCallbackWithBuffer((Camera.PreviewCallback) null);
            try {
                if (this.usingSurfaceTexture) {
                    this.camera.setPreviewTexture((SurfaceTexture) null);
                } else {
                    this.camera.setPreviewDisplay((SurfaceHolder) null);
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to clear camera preview: " + e);
            }
            this.camera.release();
            this.camera = null;
        }
        this.bytesToByteBuffer.clear();
    }

    @RequiresPermission("android.permission.CAMERA")
    @SuppressLint({"MissingPermission"})
    public void toggleFlash() {
        String str;
        Camera.Parameters parameters = this.camera.getParameters();
        if (parameters.getFlashMode() != "on") {
            if (parameters.getFlashMode() == "off" || parameters.getFlashMode().equals("off")) {
                str = "torch";
                parameters.setFlashMode(str);
                this.camera.setParameters(parameters);
                this.camera.startPreview();
            } else if (!parameters.getFlashMode().equals("torch")) {
                if (parameters.getFlashMode() == "auto") {
                    str = "on";
                } else if (parameters.getFlashMode() != "torch") {
                    str = "auto";
                }
                parameters.setFlashMode(str);
                this.camera.setParameters(parameters);
                this.camera.startPreview();
            }
        }
        str = "off";
        parameters.setFlashMode(str);
        this.camera.setParameters(parameters);
        this.camera.startPreview();
    }
}
