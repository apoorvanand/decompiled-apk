package com.facebook;

import android.os.Handler;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

class ProgressNoopOutputStream extends OutputStream implements RequestOutputStream {
    private int batchMax;
    private final Handler callbackHandler;
    private GraphRequest currentRequest;
    private RequestProgress currentRequestProgress;
    private final Map<GraphRequest, RequestProgress> progressMap = new HashMap();

    ProgressNoopOutputStream(Handler handler) {
        this.callbackHandler = handler;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.batchMax;
    }

    /* access modifiers changed from: package-private */
    public void a(long j) {
        if (this.currentRequestProgress == null) {
            this.currentRequestProgress = new RequestProgress(this.callbackHandler, this.currentRequest);
            this.progressMap.put(this.currentRequest, this.currentRequestProgress);
        }
        this.currentRequestProgress.b(j);
        this.batchMax = (int) (((long) this.batchMax) + j);
    }

    /* access modifiers changed from: package-private */
    public Map<GraphRequest, RequestProgress> b() {
        return this.progressMap;
    }

    public void setCurrentRequest(GraphRequest graphRequest) {
        this.currentRequest = graphRequest;
        this.currentRequestProgress = graphRequest != null ? this.progressMap.get(graphRequest) : null;
    }

    public void write(int i) {
        a(1);
    }

    public void write(byte[] bArr) {
        a((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        a((long) i2);
    }
}
