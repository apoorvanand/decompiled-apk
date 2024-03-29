package com.google.zxing.client.result;

public abstract class ParsedResult {
    private final ParsedResultType type;

    protected ParsedResult(ParsedResultType parsedResultType) {
        this.type = parsedResultType;
    }

    public static void maybeAppend(String str, StringBuilder sb) {
        if (str != null && !str.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(10);
            }
            sb.append(str);
        }
    }

    public static void maybeAppend(String[] strArr, StringBuilder sb) {
        if (strArr != null) {
            for (String maybeAppend : strArr) {
                maybeAppend(maybeAppend, sb);
            }
        }
    }

    public abstract String getDisplayResult();

    public final ParsedResultType getType() {
        return this.type;
    }

    public final String toString() {
        return getDisplayResult();
    }
}
