package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GraphRequestBatch extends AbstractList<GraphRequest> {
    private static AtomicInteger idGenerator = new AtomicInteger();
    private String batchApplicationId;
    private Handler callbackHandler;
    private List<Callback> callbacks;
    private final String id;
    private List<GraphRequest> requests;
    private int timeoutInMilliseconds;

    public interface Callback {
        void onBatchCompleted(GraphRequestBatch graphRequestBatch);
    }

    public interface OnProgressCallback extends Callback {
        void onBatchProgress(GraphRequestBatch graphRequestBatch, long j, long j2);
    }

    public GraphRequestBatch() {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList();
    }

    public GraphRequestBatch(GraphRequestBatch graphRequestBatch) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList(graphRequestBatch);
        this.callbackHandler = graphRequestBatch.callbackHandler;
        this.timeoutInMilliseconds = graphRequestBatch.timeoutInMilliseconds;
        this.callbacks = new ArrayList(graphRequestBatch.callbacks);
    }

    public GraphRequestBatch(Collection<GraphRequest> collection) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList(collection);
    }

    public GraphRequestBatch(GraphRequest... graphRequestArr) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = Arrays.asList(graphRequestArr);
    }

    /* access modifiers changed from: package-private */
    public final String a() {
        return this.id;
    }

    /* access modifiers changed from: package-private */
    public final void a(Handler handler) {
        this.callbackHandler = handler;
    }

    public final void add(int i, GraphRequest graphRequest) {
        this.requests.add(i, graphRequest);
    }

    public final boolean add(GraphRequest graphRequest) {
        return this.requests.add(graphRequest);
    }

    public void addCallback(Callback callback) {
        if (!this.callbacks.contains(callback)) {
            this.callbacks.add(callback);
        }
    }

    /* access modifiers changed from: package-private */
    public final Handler b() {
        return this.callbackHandler;
    }

    /* access modifiers changed from: package-private */
    public final List<GraphRequest> c() {
        return this.requests;
    }

    public final void clear() {
        this.requests.clear();
    }

    /* access modifiers changed from: package-private */
    public final List<Callback> d() {
        return this.callbacks;
    }

    /* access modifiers changed from: package-private */
    public List<GraphResponse> e() {
        return GraphRequest.executeBatchAndWait(this);
    }

    public final List<GraphResponse> executeAndWait() {
        return e();
    }

    public final GraphRequestAsyncTask executeAsync() {
        return f();
    }

    /* access modifiers changed from: package-private */
    public GraphRequestAsyncTask f() {
        return GraphRequest.executeBatchAsync(this);
    }

    public final GraphRequest get(int i) {
        return this.requests.get(i);
    }

    public final String getBatchApplicationId() {
        return this.batchApplicationId;
    }

    public int getTimeout() {
        return this.timeoutInMilliseconds;
    }

    public final GraphRequest remove(int i) {
        return this.requests.remove(i);
    }

    public void removeCallback(Callback callback) {
        this.callbacks.remove(callback);
    }

    public final GraphRequest set(int i, GraphRequest graphRequest) {
        return this.requests.set(i, graphRequest);
    }

    public final void setBatchApplicationId(String str) {
        this.batchApplicationId = str;
    }

    public void setTimeout(int i) {
        if (i >= 0) {
            this.timeoutInMilliseconds = i;
            return;
        }
        throw new IllegalArgumentException("Argument timeoutInMilliseconds must be >= 0.");
    }

    public final int size() {
        return this.requests.size();
    }
}
