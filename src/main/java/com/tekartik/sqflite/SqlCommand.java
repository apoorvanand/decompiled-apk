package com.tekartik.sqflite;

import android.util.Log;
import com.tekartik.sqflite.dev.Debug;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlCommand {
    private final List<Object> rawArguments;
    private final String sql;

    public SqlCommand(String str, List<Object> list) {
        this.sql = str;
        this.rawArguments = list == null ? new ArrayList<>() : list;
    }

    private static Map<String, Object> fixMap(Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            Object value = next.getValue();
            hashMap.put(toString(next.getKey()), value instanceof Map ? fixMap((Map) value) : toString(value));
        }
        return hashMap;
    }

    private String[] getQuerySqlArguments(List<Object> list) {
        return (String[]) getStringQuerySqlArguments(list).toArray(new String[0]);
    }

    private Object[] getSqlArguments(List<Object> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Object value : list) {
                arrayList.add(toValue(value));
            }
        }
        return arrayList.toArray(new Object[0]);
    }

    private List<String> getStringQuerySqlArguments(List<Object> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Object sqlCommand : list) {
                arrayList.add(toString(sqlCommand));
            }
        }
        return arrayList;
    }

    private static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof byte[])) {
            return obj instanceof Map ? fixMap((Map) obj).toString() : obj.toString();
        }
        ArrayList arrayList = new ArrayList();
        for (byte valueOf : (byte[]) obj) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList.toString();
    }

    private static Object toValue(Object obj) {
        if (obj == null) {
            return null;
        }
        if (Debug.EXTRA_LOGV) {
            Log.d(Constant.TAG, "arg " + obj.getClass().getCanonicalName() + " " + toString(obj));
        }
        if (obj instanceof List) {
            List list = (List) obj;
            byte[] bArr = new byte[list.size()];
            for (int i = 0; i < list.size(); i++) {
                bArr[i] = (byte) ((Integer) list.get(i)).intValue();
            }
            obj = bArr;
        }
        if (Debug.EXTRA_LOGV) {
            Log.d(Constant.TAG, "arg " + obj.getClass().getCanonicalName() + " " + toString(obj));
        }
        return obj;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SqlCommand)) {
            return false;
        }
        SqlCommand sqlCommand = (SqlCommand) obj;
        String str = this.sql;
        if (str != null) {
            if (!str.equals(sqlCommand.sql)) {
                return false;
            }
        } else if (sqlCommand.sql != null) {
            return false;
        }
        if (this.rawArguments.size() != sqlCommand.rawArguments.size()) {
            return false;
        }
        for (int i = 0; i < this.rawArguments.size(); i++) {
            if (!(this.rawArguments.get(i) instanceof byte[]) || !(sqlCommand.rawArguments.get(i) instanceof byte[])) {
                if (!this.rawArguments.get(i).equals(sqlCommand.rawArguments.get(i))) {
                    return false;
                }
            } else if (!Arrays.equals((byte[]) this.rawArguments.get(i), (byte[]) sqlCommand.rawArguments.get(i))) {
                return false;
            }
        }
        return true;
    }

    public String[] getQuerySqlArguments() {
        return getQuerySqlArguments(this.rawArguments);
    }

    public List<Object> getRawSqlArguments() {
        return this.rawArguments;
    }

    public String getSql() {
        return this.sql;
    }

    public Object[] getSqlArguments() {
        return getSqlArguments(this.rawArguments);
    }

    public int hashCode() {
        String str = this.sql;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public SqlCommand sanitizeForQuery() {
        if (this.rawArguments.size() == 0) {
            return this;
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        int length = this.sql.length();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = this.sql.charAt(i3);
            if (charAt == '?') {
                int i4 = i3 + 1;
                if (i4 < length && Character.isDigit(this.sql.charAt(i4))) {
                    return this;
                }
                i++;
                if (i2 >= this.rawArguments.size()) {
                    return this;
                }
                int i5 = i2 + 1;
                Object obj = this.rawArguments.get(i2);
                if ((obj instanceof Integer) || (obj instanceof Long)) {
                    sb.append(obj.toString());
                    i2 = i5;
                } else {
                    arrayList.add(obj);
                    i2 = i5;
                }
            }
            sb.append(charAt);
        }
        return i != this.rawArguments.size() ? this : new SqlCommand(sb.toString(), arrayList);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.sql);
        List<Object> list = this.rawArguments;
        if (list == null || list.isEmpty()) {
            str = "";
        } else {
            str = " " + getStringQuerySqlArguments(this.rawArguments);
        }
        sb.append(str);
        return sb.toString();
    }
}
