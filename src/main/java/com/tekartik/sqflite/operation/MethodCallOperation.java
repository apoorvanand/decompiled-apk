package com.tekartik.sqflite.operation;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class MethodCallOperation extends BaseOperation {
    final MethodCall a;
    final Result b;

    class Result implements OperationResult {
        final MethodChannel.Result a;

        Result(MethodChannel.Result result) {
            this.a = result;
        }

        public void error(String str, String str2, Object obj) {
            this.a.error(str, str2, obj);
        }

        public void success(Object obj) {
            this.a.success(obj);
        }
    }

    public MethodCallOperation(MethodCall methodCall, MethodChannel.Result result) {
        this.a = methodCall;
        this.b = new Result(result);
    }

    public <T> T getArgument(String str) {
        return this.a.argument(str);
    }

    public String getMethod() {
        return this.a.method;
    }

    public OperationResult getOperationResult() {
        return this.b;
    }
}
