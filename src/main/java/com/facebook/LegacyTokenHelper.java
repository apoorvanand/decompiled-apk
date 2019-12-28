package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class LegacyTokenHelper {
    public static final String APPLICATION_ID_KEY = "com.facebook.TokenCachingStrategy.ApplicationId";
    public static final String DECLINED_PERMISSIONS_KEY = "com.facebook.TokenCachingStrategy.DeclinedPermissions";
    public static final String DEFAULT_CACHE_KEY = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    public static final String EXPIRATION_DATE_KEY = "com.facebook.TokenCachingStrategy.ExpirationDate";
    private static final long INVALID_BUNDLE_MILLISECONDS = Long.MIN_VALUE;
    private static final String IS_SSO_KEY = "com.facebook.TokenCachingStrategy.IsSSO";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VALUE_ENUM_TYPE = "enumType";
    private static final String JSON_VALUE_TYPE = "valueType";
    public static final String LAST_REFRESH_DATE_KEY = "com.facebook.TokenCachingStrategy.LastRefreshDate";
    public static final String PERMISSIONS_KEY = "com.facebook.TokenCachingStrategy.Permissions";
    private static final String TAG = "LegacyTokenHelper";
    public static final String TOKEN_KEY = "com.facebook.TokenCachingStrategy.Token";
    public static final String TOKEN_SOURCE_KEY = "com.facebook.TokenCachingStrategy.AccessTokenSource";
    private static final String TYPE_BOOLEAN = "bool";
    private static final String TYPE_BOOLEAN_ARRAY = "bool[]";
    private static final String TYPE_BYTE = "byte";
    private static final String TYPE_BYTE_ARRAY = "byte[]";
    private static final String TYPE_CHAR = "char";
    private static final String TYPE_CHAR_ARRAY = "char[]";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_DOUBLE_ARRAY = "double[]";
    private static final String TYPE_ENUM = "enum";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_FLOAT_ARRAY = "float[]";
    private static final String TYPE_INTEGER = "int";
    private static final String TYPE_INTEGER_ARRAY = "int[]";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_LONG_ARRAY = "long[]";
    private static final String TYPE_SHORT = "short";
    private static final String TYPE_SHORT_ARRAY = "short[]";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_STRING_LIST = "stringList";
    private SharedPreferences cache;
    private String cacheKey;

    public LegacyTokenHelper(Context context) {
        this(context, (String) null);
    }

    public LegacyTokenHelper(Context context, String str) {
        Validate.notNull(context, "context");
        this.cacheKey = Utility.isNullOrEmpty(str) ? DEFAULT_CACHE_KEY : str;
        Context applicationContext = context.getApplicationContext();
        this.cache = (applicationContext != null ? applicationContext : context).getSharedPreferences(this.cacheKey, 0);
    }

    static Date a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        long j = bundle.getLong(str, Long.MIN_VALUE);
        if (j == Long.MIN_VALUE) {
            return null;
        }
        return new Date(j);
    }

    static void a(Bundle bundle, String str, Date date) {
        bundle.putLong(str, date.getTime());
    }

    private void deserializeKey(String str, Bundle bundle) {
        JSONObject jSONObject = new JSONObject(this.cache.getString(str, "{}"));
        String string = jSONObject.getString(JSON_VALUE_TYPE);
        if (string.equals(TYPE_BOOLEAN)) {
            bundle.putBoolean(str, jSONObject.getBoolean("value"));
            return;
        }
        int i = 0;
        if (string.equals(TYPE_BOOLEAN_ARRAY)) {
            JSONArray jSONArray = jSONObject.getJSONArray("value");
            boolean[] zArr = new boolean[jSONArray.length()];
            while (i < zArr.length) {
                zArr[i] = jSONArray.getBoolean(i);
                i++;
            }
            bundle.putBooleanArray(str, zArr);
        } else if (string.equals(TYPE_BYTE)) {
            bundle.putByte(str, (byte) jSONObject.getInt("value"));
        } else if (string.equals(TYPE_BYTE_ARRAY)) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("value");
            byte[] bArr = new byte[jSONArray2.length()];
            while (i < bArr.length) {
                bArr[i] = (byte) jSONArray2.getInt(i);
                i++;
            }
            bundle.putByteArray(str, bArr);
        } else if (string.equals(TYPE_SHORT)) {
            bundle.putShort(str, (short) jSONObject.getInt("value"));
        } else if (string.equals(TYPE_SHORT_ARRAY)) {
            JSONArray jSONArray3 = jSONObject.getJSONArray("value");
            short[] sArr = new short[jSONArray3.length()];
            while (i < sArr.length) {
                sArr[i] = (short) jSONArray3.getInt(i);
                i++;
            }
            bundle.putShortArray(str, sArr);
        } else if (string.equals(TYPE_INTEGER)) {
            bundle.putInt(str, jSONObject.getInt("value"));
        } else if (string.equals(TYPE_INTEGER_ARRAY)) {
            JSONArray jSONArray4 = jSONObject.getJSONArray("value");
            int[] iArr = new int[jSONArray4.length()];
            while (i < iArr.length) {
                iArr[i] = jSONArray4.getInt(i);
                i++;
            }
            bundle.putIntArray(str, iArr);
        } else if (string.equals(TYPE_LONG)) {
            bundle.putLong(str, jSONObject.getLong("value"));
        } else if (string.equals(TYPE_LONG_ARRAY)) {
            JSONArray jSONArray5 = jSONObject.getJSONArray("value");
            long[] jArr = new long[jSONArray5.length()];
            while (i < jArr.length) {
                jArr[i] = jSONArray5.getLong(i);
                i++;
            }
            bundle.putLongArray(str, jArr);
        } else if (string.equals(TYPE_FLOAT)) {
            bundle.putFloat(str, (float) jSONObject.getDouble("value"));
        } else if (string.equals(TYPE_FLOAT_ARRAY)) {
            JSONArray jSONArray6 = jSONObject.getJSONArray("value");
            float[] fArr = new float[jSONArray6.length()];
            while (i < fArr.length) {
                fArr[i] = (float) jSONArray6.getDouble(i);
                i++;
            }
            bundle.putFloatArray(str, fArr);
        } else if (string.equals(TYPE_DOUBLE)) {
            bundle.putDouble(str, jSONObject.getDouble("value"));
        } else if (string.equals(TYPE_DOUBLE_ARRAY)) {
            JSONArray jSONArray7 = jSONObject.getJSONArray("value");
            double[] dArr = new double[jSONArray7.length()];
            while (i < dArr.length) {
                dArr[i] = jSONArray7.getDouble(i);
                i++;
            }
            bundle.putDoubleArray(str, dArr);
        } else if (string.equals(TYPE_CHAR)) {
            String string2 = jSONObject.getString("value");
            if (string2 != null && string2.length() == 1) {
                bundle.putChar(str, string2.charAt(0));
            }
        } else if (string.equals(TYPE_CHAR_ARRAY)) {
            JSONArray jSONArray8 = jSONObject.getJSONArray("value");
            char[] cArr = new char[jSONArray8.length()];
            for (int i2 = 0; i2 < cArr.length; i2++) {
                String string3 = jSONArray8.getString(i2);
                if (string3 != null && string3.length() == 1) {
                    cArr[i2] = string3.charAt(0);
                }
            }
            bundle.putCharArray(str, cArr);
        } else if (string.equals(TYPE_STRING)) {
            bundle.putString(str, jSONObject.getString("value"));
        } else if (string.equals(TYPE_STRING_LIST)) {
            JSONArray jSONArray9 = jSONObject.getJSONArray("value");
            int length = jSONArray9.length();
            ArrayList arrayList = new ArrayList(length);
            while (i < length) {
                Object obj = jSONArray9.get(i);
                arrayList.add(i, obj == JSONObject.NULL ? null : (String) obj);
                i++;
            }
            bundle.putStringArrayList(str, arrayList);
        } else if (string.equals(TYPE_ENUM)) {
            try {
                bundle.putSerializable(str, Enum.valueOf(Class.forName(jSONObject.getString(JSON_VALUE_ENUM_TYPE)), jSONObject.getString("value")));
            } catch (ClassNotFoundException | IllegalArgumentException unused) {
            }
        }
    }

    public static String getApplicationId(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getString(APPLICATION_ID_KEY);
    }

    public static Date getExpirationDate(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return a(bundle, EXPIRATION_DATE_KEY);
    }

    public static long getExpirationMilliseconds(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong(EXPIRATION_DATE_KEY);
    }

    public static Date getLastRefreshDate(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return a(bundle, LAST_REFRESH_DATE_KEY);
    }

    public static long getLastRefreshMilliseconds(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong(LAST_REFRESH_DATE_KEY);
    }

    public static Set<String> getPermissions(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        ArrayList<String> stringArrayList = bundle.getStringArrayList(PERMISSIONS_KEY);
        if (stringArrayList == null) {
            return null;
        }
        return new HashSet(stringArrayList);
    }

    public static AccessTokenSource getSource(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.containsKey(TOKEN_SOURCE_KEY) ? (AccessTokenSource) bundle.getSerializable(TOKEN_SOURCE_KEY) : bundle.getBoolean(IS_SSO_KEY) ? AccessTokenSource.FACEBOOK_APPLICATION_WEB : AccessTokenSource.WEB_VIEW;
    }

    public static String getToken(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getString(TOKEN_KEY);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r1 = r6.getString(TOKEN_KEY);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean hasTokenInformation(android.os.Bundle r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "com.facebook.TokenCachingStrategy.Token"
            java.lang.String r1 = r6.getString(r1)
            if (r1 == 0) goto L_0x0022
            int r1 = r1.length()
            if (r1 != 0) goto L_0x0013
            goto L_0x0022
        L_0x0013:
            java.lang.String r1 = "com.facebook.TokenCachingStrategy.ExpirationDate"
            r2 = 0
            long r4 = r6.getLong(r1, r2)
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x0020
            return r0
        L_0x0020:
            r6 = 1
            return r6
        L_0x0022:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.LegacyTokenHelper.hasTokenInformation(android.os.Bundle):boolean");
    }

    public static void putApplicationId(Bundle bundle, String str) {
        Validate.notNull(bundle, "bundle");
        bundle.putString(APPLICATION_ID_KEY, str);
    }

    public static void putDeclinedPermissions(Bundle bundle, Collection<String> collection) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(collection, "value");
        bundle.putStringArrayList(DECLINED_PERMISSIONS_KEY, new ArrayList(collection));
    }

    public static void putExpirationDate(Bundle bundle, Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        a(bundle, EXPIRATION_DATE_KEY, date);
    }

    public static void putExpirationMilliseconds(Bundle bundle, long j) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong(EXPIRATION_DATE_KEY, j);
    }

    public static void putLastRefreshDate(Bundle bundle, Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        a(bundle, LAST_REFRESH_DATE_KEY, date);
    }

    public static void putLastRefreshMilliseconds(Bundle bundle, long j) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong(LAST_REFRESH_DATE_KEY, j);
    }

    public static void putPermissions(Bundle bundle, Collection<String> collection) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(collection, "value");
        bundle.putStringArrayList(PERMISSIONS_KEY, new ArrayList(collection));
    }

    public static void putSource(Bundle bundle, AccessTokenSource accessTokenSource) {
        Validate.notNull(bundle, "bundle");
        bundle.putSerializable(TOKEN_SOURCE_KEY, accessTokenSource);
    }

    public static void putToken(Bundle bundle, String str) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(str, "value");
        bundle.putString(TOKEN_KEY, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0187  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void serializeKey(java.lang.String r9, android.os.Bundle r10, android.content.SharedPreferences.Editor r11) {
        /*
            r8 = this;
            java.lang.Object r10 = r10.get(r9)
            if (r10 != 0) goto L_0x0007
            return
        L_0x0007:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            boolean r1 = r10 instanceof java.lang.Byte
            r2 = 0
            if (r1 == 0) goto L_0x0020
            java.lang.String r1 = "byte"
            java.lang.String r3 = "value"
            java.lang.Byte r10 = (java.lang.Byte) r10
            int r10 = r10.intValue()
        L_0x001b:
            r0.put(r3, r10)
            goto L_0x0185
        L_0x0020:
            boolean r1 = r10 instanceof java.lang.Short
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = "short"
            java.lang.String r3 = "value"
            java.lang.Short r10 = (java.lang.Short) r10
            int r10 = r10.intValue()
            goto L_0x001b
        L_0x002f:
            boolean r1 = r10 instanceof java.lang.Integer
            if (r1 == 0) goto L_0x003e
            java.lang.String r1 = "int"
            java.lang.String r3 = "value"
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            goto L_0x001b
        L_0x003e:
            boolean r1 = r10 instanceof java.lang.Long
            if (r1 == 0) goto L_0x0051
            java.lang.String r1 = "long"
            java.lang.String r3 = "value"
            java.lang.Long r10 = (java.lang.Long) r10
            long r4 = r10.longValue()
            r0.put(r3, r4)
            goto L_0x0185
        L_0x0051:
            boolean r1 = r10 instanceof java.lang.Float
            if (r1 == 0) goto L_0x0064
            java.lang.String r1 = "float"
            java.lang.String r3 = "value"
            java.lang.Float r10 = (java.lang.Float) r10
            double r4 = r10.doubleValue()
        L_0x005f:
            r0.put(r3, r4)
            goto L_0x0185
        L_0x0064:
            boolean r1 = r10 instanceof java.lang.Double
            if (r1 == 0) goto L_0x0073
            java.lang.String r1 = "double"
            java.lang.String r3 = "value"
            java.lang.Double r10 = (java.lang.Double) r10
            double r4 = r10.doubleValue()
            goto L_0x005f
        L_0x0073:
            boolean r1 = r10 instanceof java.lang.Boolean
            if (r1 == 0) goto L_0x0086
            java.lang.String r1 = "bool"
            java.lang.String r3 = "value"
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            r0.put(r3, r10)
            goto L_0x0185
        L_0x0086:
            boolean r1 = r10 instanceof java.lang.Character
            if (r1 == 0) goto L_0x0097
            java.lang.String r1 = "char"
            java.lang.String r3 = "value"
            java.lang.String r10 = r10.toString()
        L_0x0092:
            r0.put(r3, r10)
            goto L_0x0185
        L_0x0097:
            boolean r1 = r10 instanceof java.lang.String
            if (r1 == 0) goto L_0x00a2
            java.lang.String r1 = "string"
            java.lang.String r3 = "value"
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x0092
        L_0x00a2:
            boolean r1 = r10 instanceof java.lang.Enum
            if (r1 == 0) goto L_0x00bc
            java.lang.String r1 = "enum"
            java.lang.String r3 = "value"
            java.lang.String r4 = r10.toString()
            r0.put(r3, r4)
            java.lang.String r3 = "enumType"
            java.lang.Class r10 = r10.getClass()
            java.lang.String r10 = r10.getName()
            goto L_0x0092
        L_0x00bc:
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>()
            boolean r3 = r10 instanceof byte[]
            r4 = 0
            if (r3 == 0) goto L_0x00da
            java.lang.String r2 = "byte[]"
            byte[] r10 = (byte[]) r10
            int r3 = r10.length
        L_0x00cb:
            if (r4 >= r3) goto L_0x00d5
            byte r5 = r10[r4]
            r1.put(r5)
            int r4 = r4 + 1
            goto L_0x00cb
        L_0x00d5:
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x0185
        L_0x00da:
            boolean r3 = r10 instanceof short[]
            if (r3 == 0) goto L_0x00ed
            java.lang.String r2 = "short[]"
            short[] r10 = (short[]) r10
            int r3 = r10.length
        L_0x00e3:
            if (r4 >= r3) goto L_0x00d5
            short r5 = r10[r4]
            r1.put(r5)
            int r4 = r4 + 1
            goto L_0x00e3
        L_0x00ed:
            boolean r3 = r10 instanceof int[]
            if (r3 == 0) goto L_0x0100
            java.lang.String r2 = "int[]"
            int[] r10 = (int[]) r10
            int r3 = r10.length
        L_0x00f6:
            if (r4 >= r3) goto L_0x00d5
            r5 = r10[r4]
            r1.put(r5)
            int r4 = r4 + 1
            goto L_0x00f6
        L_0x0100:
            boolean r3 = r10 instanceof long[]
            if (r3 == 0) goto L_0x0113
            java.lang.String r2 = "long[]"
            long[] r10 = (long[]) r10
            int r3 = r10.length
        L_0x0109:
            if (r4 >= r3) goto L_0x00d5
            r5 = r10[r4]
            r1.put(r5)
            int r4 = r4 + 1
            goto L_0x0109
        L_0x0113:
            boolean r3 = r10 instanceof float[]
            if (r3 == 0) goto L_0x0127
            java.lang.String r2 = "float[]"
            float[] r10 = (float[]) r10
            int r3 = r10.length
        L_0x011c:
            if (r4 >= r3) goto L_0x00d5
            r5 = r10[r4]
            double r5 = (double) r5
            r1.put(r5)
            int r4 = r4 + 1
            goto L_0x011c
        L_0x0127:
            boolean r3 = r10 instanceof double[]
            if (r3 == 0) goto L_0x013a
            java.lang.String r2 = "double[]"
            double[] r10 = (double[]) r10
            int r3 = r10.length
        L_0x0130:
            if (r4 >= r3) goto L_0x00d5
            r5 = r10[r4]
            r1.put(r5)
            int r4 = r4 + 1
            goto L_0x0130
        L_0x013a:
            boolean r3 = r10 instanceof boolean[]
            if (r3 == 0) goto L_0x014d
            java.lang.String r2 = "bool[]"
            boolean[] r10 = (boolean[]) r10
            int r3 = r10.length
        L_0x0143:
            if (r4 >= r3) goto L_0x00d5
            boolean r5 = r10[r4]
            r1.put(r5)
            int r4 = r4 + 1
            goto L_0x0143
        L_0x014d:
            boolean r3 = r10 instanceof char[]
            if (r3 == 0) goto L_0x0164
            java.lang.String r2 = "char[]"
            char[] r10 = (char[]) r10
            int r3 = r10.length
        L_0x0156:
            if (r4 >= r3) goto L_0x00d5
            char r5 = r10[r4]
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r1.put(r5)
            int r4 = r4 + 1
            goto L_0x0156
        L_0x0164:
            boolean r3 = r10 instanceof java.util.List
            if (r3 == 0) goto L_0x0184
            java.lang.String r2 = "stringList"
            java.util.List r10 = (java.util.List) r10
            java.util.Iterator r10 = r10.iterator()
        L_0x0170:
            boolean r3 = r10.hasNext()
            if (r3 == 0) goto L_0x00d5
            java.lang.Object r3 = r10.next()
            java.lang.String r3 = (java.lang.String) r3
            if (r3 != 0) goto L_0x0180
            java.lang.Object r3 = org.json.JSONObject.NULL
        L_0x0180:
            r1.put(r3)
            goto L_0x0170
        L_0x0184:
            r1 = r2
        L_0x0185:
            if (r1 == 0) goto L_0x019a
            java.lang.String r10 = "valueType"
            r0.put(r10, r1)
            if (r2 == 0) goto L_0x0193
            java.lang.String r10 = "value"
            r0.putOpt(r10, r2)
        L_0x0193:
            java.lang.String r10 = r0.toString()
            r11.putString(r9, r10)
        L_0x019a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.LegacyTokenHelper.serializeKey(java.lang.String, android.os.Bundle, android.content.SharedPreferences$Editor):void");
    }

    public void clear() {
        this.cache.edit().clear().apply();
    }

    public Bundle load() {
        Bundle bundle = new Bundle();
        for (String next : this.cache.getAll().keySet()) {
            try {
                deserializeKey(next, bundle);
            } catch (JSONException e) {
                LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                String str = TAG;
                Logger.log(loggingBehavior, 5, str, "Error reading cached value for key: '" + next + "' -- " + e);
                return null;
            }
        }
        return bundle;
    }

    public void save(Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        SharedPreferences.Editor edit = this.cache.edit();
        for (String str : bundle.keySet()) {
            try {
                serializeKey(str, bundle, edit);
            } catch (JSONException e) {
                LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                String str2 = TAG;
                Logger.log(loggingBehavior, 5, str2, "Error processing value for key: '" + str + "' -- " + e);
                return;
            }
        }
        edit.apply();
    }
}
