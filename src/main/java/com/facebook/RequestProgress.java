package com.facebook;

import android.os.Handler;
import com.facebook.GraphRequest;

class RequestProgress {
    private final Handler callbackHandler;
    private long lastReportedProgress;
    private long maxProgress;
    private long progress;
    private final GraphRequest request;
    private final long threshold = FacebookSdk.getOnProgressThreshold();

    RequestProgress(Handler handler, GraphRequest graphRequest) {
        this.request = graphRequest;
        this.callbackHandler = handler;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.progress > this.lastReportedProgress) {
            GraphRequest.Callback callback = this.request.getCallback();
            final long j = this.maxProgress;
            if (j > 0 && (callback instanceof GraphRequest.OnProgressCallback)) {
                final long j2 = this.progress;
                final GraphRequest.OnProgressCallback onProgressCallback = (GraphRequest.OnProgressCallback) callback;
                Handler handler = this.callbackHandler;
                if (handler == null) {
                    onProgressCallback.onProgress(j2, j);
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            onProgressCallback.onProgress(j2, j);
                        }
                    });
                }
                this.lastReportedProgress = this.progress;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(long j) {
        this.progress += j;
        long j2 = this.progress;
        if (j2 >= this.lastReportedProgress + this.threshold || j2 >= this.maxProgress) {
            a();
        }
    }

    /* access modifiers changed from: package-private */
    public void b(long j) {
        this.maxProgress += j;
    }
}
