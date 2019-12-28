package io.flutter.embedding.android;

import android.os.Build;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AndroidTouchProcessor {
    private static final int BYTES_PER_FIELD = 8;
    private static final int POINTER_DATA_FIELD_COUNT = 24;
    private static final int POINTER_DATA_FLAG_BATCHED = 1;
    @NonNull
    private final FlutterRenderer renderer;

    private @interface PointerChange {
        public static final int ADD = 1;
        public static final int CANCEL = 0;
        public static final int DOWN = 4;
        public static final int HOVER = 3;
        public static final int MOVE = 5;
        public static final int REMOVE = 2;
        public static final int UP = 6;
    }

    private @interface PointerDeviceKind {
        public static final int INVERTED_STYLUS = 3;
        public static final int MOUSE = 1;
        public static final int STYLUS = 2;
        public static final int TOUCH = 0;
        public static final int UNKNOWN = 4;
    }

    private @interface PointerSignalKind {
        public static final int NONE = 0;
        public static final int SCROLL = 1;
        public static final int UNKNOWN = 2;
    }

    public AndroidTouchProcessor(@NonNull FlutterRenderer flutterRenderer) {
        this.renderer = flutterRenderer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0109  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addPointerForIndex(android.view.MotionEvent r17, int r18, int r19, int r20, java.nio.ByteBuffer r21) {
        /*
            r16 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r21
            r4 = -1
            if (r2 != r4) goto L_0x000c
            return
        L_0x000c:
            int r4 = r17.getToolType(r18)
            r5 = r16
            int r4 = r5.getPointerDeviceTypeForToolType(r4)
            int r6 = r17.getActionMasked()
            r7 = 8
            r8 = 1
            if (r6 != r7) goto L_0x0021
            r6 = 1
            goto L_0x0022
        L_0x0021:
            r6 = 0
        L_0x0022:
            long r9 = r17.getEventTime()
            r11 = 1000(0x3e8, double:4.94E-321)
            long r9 = r9 * r11
            r3.putLong(r9)
            long r9 = (long) r2
            r3.putLong(r9)
            long r9 = (long) r4
            r3.putLong(r9)
            long r9 = (long) r6
            r3.putLong(r9)
            int r2 = r17.getPointerId(r18)
            long r9 = (long) r2
            r3.putLong(r9)
            float r2 = r17.getX(r18)
            double r9 = (double) r2
            r3.putDouble(r9)
            float r2 = r17.getY(r18)
            double r9 = (double) r2
            r3.putDouble(r9)
            r9 = 0
            r2 = 2
            if (r4 != r8) goto L_0x0061
            int r11 = r17.getButtonState()
            r11 = r11 & 31
        L_0x005c:
            long r11 = (long) r11
            r3.putLong(r11)
            goto L_0x006f
        L_0x0061:
            if (r4 != r2) goto L_0x006c
            int r11 = r17.getButtonState()
            int r11 = r11 >> 4
            r11 = r11 & 15
            goto L_0x005c
        L_0x006c:
            r3.putLong(r9)
        L_0x006f:
            r3.putLong(r9)
            float r9 = r17.getPressure(r18)
            double r9 = (double) r9
            r3.putDouble(r9)
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            android.view.InputDevice r11 = r17.getDevice()
            r12 = 0
            if (r11 == 0) goto L_0x0099
            android.view.InputDevice r11 = r17.getDevice()
            android.view.InputDevice$MotionRange r11 = r11.getMotionRange(r2)
            if (r11 == 0) goto L_0x0099
            float r9 = r11.getMin()
            double r9 = (double) r9
            float r11 = r11.getMax()
            double r14 = (double) r11
            goto L_0x009b
        L_0x0099:
            r14 = r9
            r9 = r12
        L_0x009b:
            r3.putDouble(r9)
            r3.putDouble(r14)
            if (r4 != r2) goto L_0x00ae
            r9 = 24
            float r9 = r0.getAxisValue(r9, r1)
            double r9 = (double) r9
            r3.putDouble(r9)
            goto L_0x00b1
        L_0x00ae:
            r3.putDouble(r12)
        L_0x00b1:
            r3.putDouble(r12)
            float r9 = r17.getSize(r18)
            double r9 = (double) r9
            r3.putDouble(r9)
            float r9 = r17.getToolMajor(r18)
            double r9 = (double) r9
            r3.putDouble(r9)
            float r9 = r17.getToolMinor(r18)
            double r9 = (double) r9
            r3.putDouble(r9)
            r3.putDouble(r12)
            r3.putDouble(r12)
            float r7 = r0.getAxisValue(r7, r1)
            double r9 = (double) r7
            r3.putDouble(r9)
            if (r4 != r2) goto L_0x00e7
            r2 = 25
            float r1 = r0.getAxisValue(r2, r1)
            double r1 = (double) r1
            r3.putDouble(r1)
            goto L_0x00ea
        L_0x00e7:
            r3.putDouble(r12)
        L_0x00ea:
            r1 = r20
            long r1 = (long) r1
            r3.putLong(r1)
            if (r6 != r8) goto L_0x0109
            r1 = 10
            float r1 = r0.getAxisValue(r1)
            float r1 = -r1
            double r1 = (double) r1
            r3.putDouble(r1)
            r1 = 9
            float r0 = r0.getAxisValue(r1)
            float r0 = -r0
            double r0 = (double) r0
            r3.putDouble(r0)
            goto L_0x010f
        L_0x0109:
            r3.putDouble(r12)
            r3.putDouble(r12)
        L_0x010f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.android.AndroidTouchProcessor.addPointerForIndex(android.view.MotionEvent, int, int, int, java.nio.ByteBuffer):void");
    }

    @PointerChange
    private int getPointerChangeForAction(int i) {
        if (i == 0) {
            return 4;
        }
        if (i == 1) {
            return 6;
        }
        if (i == 5) {
            return 4;
        }
        if (i == 6) {
            return 6;
        }
        if (i == 2) {
            return 5;
        }
        if (i == 7) {
            return 3;
        }
        if (i == 3) {
            return 0;
        }
        return i == 8 ? 3 : -1;
    }

    @PointerDeviceKind
    private int getPointerDeviceTypeForToolType(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                return 2;
            case 3:
                return 1;
            case 4:
                return 3;
            default:
                return 4;
        }
    }

    public boolean onGenericMotionEvent(@NonNull MotionEvent motionEvent) {
        boolean z = Build.VERSION.SDK_INT >= 18 && motionEvent.isFromSource(2);
        boolean z2 = motionEvent.getActionMasked() == 7 || motionEvent.getActionMasked() == 8;
        if (!z || !z2) {
            return false;
        }
        int pointerChangeForAction = getPointerChangeForAction(motionEvent.getActionMasked());
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(motionEvent.getPointerCount() * 24 * 8);
        allocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        addPointerForIndex(motionEvent, motionEvent.getActionIndex(), pointerChangeForAction, 0, allocateDirect);
        if (allocateDirect.position() % 192 == 0) {
            this.renderer.dispatchPointerDataPacket(allocateDirect, allocateDirect.position());
            return true;
        }
        throw new AssertionError("Packet position is not on field boundary.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(@androidx.annotation.NonNull android.view.MotionEvent r12) {
        /*
            r11 = this;
            int r0 = r12.getPointerCount()
            int r1 = r0 * 24
            int r1 = r1 * 8
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocateDirect(r1)
            java.nio.ByteOrder r2 = java.nio.ByteOrder.LITTLE_ENDIAN
            r1.order(r2)
            int r2 = r12.getActionMasked()
            int r3 = r12.getActionMasked()
            int r8 = r11.getPointerChangeForAction(r3)
            r3 = 0
            r9 = 1
            if (r2 == 0) goto L_0x0027
            r4 = 5
            if (r2 != r4) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            r4 = 0
            goto L_0x0028
        L_0x0027:
            r4 = 1
        L_0x0028:
            if (r4 != 0) goto L_0x0031
            if (r2 == r9) goto L_0x002f
            r5 = 6
            if (r2 != r5) goto L_0x0031
        L_0x002f:
            r2 = 1
            goto L_0x0032
        L_0x0031:
            r2 = 0
        L_0x0032:
            if (r4 == 0) goto L_0x0041
        L_0x0034:
            int r4 = r12.getActionIndex()
            r6 = 0
            r2 = r11
            r3 = r12
            r5 = r8
            r7 = r1
            r2.addPointerForIndex(r3, r4, r5, r6, r7)
            goto L_0x006d
        L_0x0041:
            if (r2 == 0) goto L_0x005e
            r10 = 0
        L_0x0044:
            if (r10 >= r0) goto L_0x0034
            int r2 = r12.getActionIndex()
            if (r10 == r2) goto L_0x005b
            int r2 = r12.getToolType(r10)
            if (r2 != r9) goto L_0x005b
            r5 = 5
            r6 = 1
            r2 = r11
            r3 = r12
            r4 = r10
            r7 = r1
            r2.addPointerForIndex(r3, r4, r5, r6, r7)
        L_0x005b:
            int r10 = r10 + 1
            goto L_0x0044
        L_0x005e:
            r10 = 0
        L_0x005f:
            if (r10 >= r0) goto L_0x006d
            r6 = 0
            r2 = r11
            r3 = r12
            r4 = r10
            r5 = r8
            r7 = r1
            r2.addPointerForIndex(r3, r4, r5, r6, r7)
            int r10 = r10 + 1
            goto L_0x005f
        L_0x006d:
            int r12 = r1.position()
            int r12 = r12 % 192
            if (r12 != 0) goto L_0x007f
            io.flutter.embedding.engine.renderer.FlutterRenderer r12 = r11.renderer
            int r0 = r1.position()
            r12.dispatchPointerDataPacket(r1, r0)
            return r9
        L_0x007f:
            java.lang.AssertionError r12 = new java.lang.AssertionError
            java.lang.String r0 = "Packet position is not on field boundary"
            r12.<init>(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.android.AndroidTouchProcessor.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
