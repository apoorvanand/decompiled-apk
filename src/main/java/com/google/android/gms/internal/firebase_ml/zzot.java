package com.google.android.gms.internal.firebase_ml;

import android.os.SystemClock;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public final class zzot {
    private static final GmsLogger zzaoe = new GmsLogger("StreamingFormatChecker", "");
    private final LinkedList<Long> zzavf = new LinkedList<>();
    private long zzavg = -1;

    public final void zzb(zzoy zzoy) {
        if (zzoy.zzaux.getBitmap() != null) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zzavf.add(Long.valueOf(elapsedRealtime));
            if (this.zzavf.size() > 5) {
                this.zzavf.removeFirst();
            }
            if (this.zzavf.size() == 5 && elapsedRealtime - this.zzavf.peekFirst().longValue() < 5000) {
                long j = this.zzavg;
                if (j == -1 || elapsedRealtime - j >= TimeUnit.SECONDS.toMillis(5)) {
                    this.zzavg = elapsedRealtime;
                    zzaoe.w("StreamingFormatChecker", "ML Kit has detected that you seem to pass camera frames to the detector as a Bitmap object. This is inefficient. Please use YUV_420_888 format for camera2 API or NV21 format for (legacy) camera API and directly pass down the byte array to ML Kit.");
                }
            }
        }
    }
}
