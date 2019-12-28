package com.tekartik.sqflite.operation;

import com.tekartik.sqflite.SqlCommand;
import io.flutter.plugin.common.MethodChannel;

public class ExecuteOperation extends BaseReadOperation {
    private final SqlCommand command;
    private final Boolean inTransaction;
    private final MethodChannel.Result result;

    public ExecuteOperation(MethodChannel.Result result2, SqlCommand sqlCommand, Boolean bool) {
        this.result = result2;
        this.command = sqlCommand;
        this.inTransaction = bool;
    }

    public void error(String str, String str2, Object obj) {
        this.result.error(str, str2, obj);
    }

    public <T> T getArgument(String str) {
        return null;
    }

    public Boolean getInTransaction() {
        return this.inTransaction;
    }

    public String getMethod() {
        return null;
    }

    /* access modifiers changed from: protected */
    public OperationResult getOperationResult() {
        return null;
    }

    public SqlCommand getSqlCommand() {
        return this.command;
    }

    public void success(Object obj) {
        this.result.success(obj);
    }
}
