package com.tekartik.sqflite.operation;

import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchOperation extends BaseOperation {
    final Map<String, Object> a;
    final BatchOperationResult b = new BatchOperationResult();
    final boolean c;

    public class BatchOperationResult implements OperationResult {
        Object a;
        String b;
        String c;
        Object d;

        public BatchOperationResult() {
        }

        public void error(String str, String str2, Object obj) {
            this.b = str;
            this.c = str2;
            this.d = obj;
        }

        public void success(Object obj) {
            this.a = obj;
        }
    }

    public BatchOperation(Map<String, Object> map, boolean z) {
        this.a = map;
        this.c = z;
    }

    public <T> T getArgument(String str) {
        return this.a.get(str);
    }

    public String getMethod() {
        return (String) this.a.get("method");
    }

    public boolean getNoResult() {
        return this.c;
    }

    public Map<String, Object> getOperationError() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(Constant.PARAM_ERROR_CODE, this.b.b);
        hashMap2.put("message", this.b.c);
        hashMap2.put("data", this.b.d);
        hashMap.put("error", hashMap2);
        return hashMap;
    }

    public OperationResult getOperationResult() {
        return this.b;
    }

    public Map<String, Object> getOperationSuccessResult() {
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.PARAM_RESULT, this.b.a);
        return hashMap;
    }

    public void handleError(MethodChannel.Result result) {
        result.error(this.b.b, this.b.c, this.b.d);
    }

    public void handleErrorContinue(List<Map<String, Object>> list) {
        if (!getNoResult()) {
            list.add(getOperationError());
        }
    }

    public void handleSuccess(List<Map<String, Object>> list) {
        if (!getNoResult()) {
            list.add(getOperationSuccessResult());
        }
    }
}
