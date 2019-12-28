package com.tekartik.sqflite.operation;

import com.tekartik.sqflite.Constant;
import com.tekartik.sqflite.SqlCommand;
import java.util.List;

public abstract class BaseReadOperation implements Operation {
    private Boolean getBoolean(String str) {
        Object argument = getArgument(str);
        if (argument instanceof Boolean) {
            return (Boolean) argument;
        }
        return null;
    }

    private String getSql() {
        return (String) getArgument(Constant.PARAM_SQL);
    }

    private List<Object> getSqlArguments() {
        return (List) getArgument(Constant.PARAM_SQL_ARGUMENTS);
    }

    public boolean getContinueOnError() {
        return Boolean.TRUE.equals(getArgument(Constant.PARAM_CONTINUE_OR_ERROR));
    }

    public Boolean getInTransaction() {
        return getBoolean(Constant.PARAM_IN_TRANSACTION);
    }

    public boolean getNoResult() {
        return Boolean.TRUE.equals(getArgument(Constant.PARAM_NO_RESULT));
    }

    /* access modifiers changed from: protected */
    public abstract OperationResult getOperationResult();

    public SqlCommand getSqlCommand() {
        return new SqlCommand(getSql(), getSqlArguments());
    }
}
