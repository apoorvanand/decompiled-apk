package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.tekartik.sqflite.Constant;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public final class FacebookRequestError implements Parcelable {
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    public static final Parcelable.Creator<FacebookRequestError> CREATOR = new Parcelable.Creator<FacebookRequestError>() {
        public FacebookRequestError createFromParcel(Parcel parcel) {
            return new FacebookRequestError(parcel);
        }

        public FacebookRequestError[] newArray(int i) {
            return new FacebookRequestError[i];
        }
    };
    private static final String ERROR_CODE_FIELD_KEY = "code";
    private static final String ERROR_CODE_KEY = "error_code";
    private static final String ERROR_IS_TRANSIENT_KEY = "is_transient";
    private static final String ERROR_KEY = "error";
    private static final String ERROR_MESSAGE_FIELD_KEY = "message";
    private static final String ERROR_MSG_KEY = "error_msg";
    private static final String ERROR_REASON_KEY = "error_reason";
    private static final String ERROR_SUB_CODE_KEY = "error_subcode";
    private static final String ERROR_TYPE_FIELD_KEY = "type";
    private static final String ERROR_USER_MSG_KEY = "error_user_msg";
    private static final String ERROR_USER_TITLE_KEY = "error_user_title";
    public static final int INVALID_ERROR_CODE = -1;
    public static final int INVALID_HTTP_STATUS_CODE = -1;
    static final Range a = new Range(200, 299);
    private final Object batchRequestResult;
    private final Category category;
    private final HttpURLConnection connection;
    private final int errorCode;
    private final String errorMessage;
    private final String errorRecoveryMessage;
    private final String errorType;
    private final String errorUserMessage;
    private final String errorUserTitle;
    private final FacebookException exception;
    private final JSONObject requestResult;
    private final JSONObject requestResultBody;
    private final int requestStatusCode;
    private final int subErrorCode;

    public enum Category {
        LOGIN_RECOVERABLE,
        OTHER,
        TRANSIENT
    }

    private static class Range {
        private final int end;
        private final int start;

        private Range(int i, int i2) {
            this.start = i;
            this.end = i2;
        }

        /* access modifiers changed from: package-private */
        public boolean a(int i) {
            return this.start <= i && i <= this.end;
        }
    }

    private FacebookRequestError(int i, int i2, int i3, String str, String str2, String str3, String str4, boolean z, JSONObject jSONObject, JSONObject jSONObject2, Object obj, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        boolean z2;
        this.requestStatusCode = i;
        this.errorCode = i2;
        this.subErrorCode = i3;
        this.errorType = str;
        this.errorMessage = str2;
        this.requestResultBody = jSONObject;
        this.requestResult = jSONObject2;
        this.batchRequestResult = obj;
        this.connection = httpURLConnection;
        this.errorUserTitle = str3;
        this.errorUserMessage = str4;
        if (facebookException != null) {
            this.exception = facebookException;
            z2 = true;
        } else {
            this.exception = new FacebookServiceException(this, str2);
            z2 = false;
        }
        FacebookRequestErrorClassification a2 = a();
        this.category = z2 ? Category.OTHER : a2.classify(i2, i3, z);
        this.errorRecoveryMessage = a2.getRecoveryMessage(this.category);
    }

    public FacebookRequestError(int i, String str, String str2) {
        this(-1, i, -1, str, str2, (String) null, (String) null, false, (JSONObject) null, (JSONObject) null, (Object) null, (HttpURLConnection) null, (FacebookException) null);
    }

    private FacebookRequestError(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), false, (JSONObject) null, (JSONObject) null, (Object) null, (HttpURLConnection) null, (FacebookException) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    FacebookRequestError(java.net.HttpURLConnection r17, java.lang.Exception r18) {
        /*
            r16 = this;
            r0 = r18
            boolean r1 = r0 instanceof com.facebook.FacebookException
            if (r1 == 0) goto L_0x000a
            com.facebook.FacebookException r0 = (com.facebook.FacebookException) r0
            r15 = r0
            goto L_0x0010
        L_0x000a:
            com.facebook.FacebookException r1 = new com.facebook.FacebookException
            r1.<init>((java.lang.Throwable) r0)
            r15 = r1
        L_0x0010:
            r3 = -1
            r4 = -1
            r5 = -1
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r2 = r16
            r14 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookRequestError.<init>(java.net.HttpURLConnection, java.lang.Exception):void");
    }

    static FacebookRequestError a(JSONObject jSONObject, Object obj, HttpURLConnection httpURLConnection) {
        String str;
        boolean z;
        String str2;
        String str3;
        String str4;
        int i;
        JSONObject jSONObject2 = jSONObject;
        try {
            if (jSONObject2.has(Constant.PARAM_ERROR_CODE)) {
                int i2 = jSONObject2.getInt(Constant.PARAM_ERROR_CODE);
                Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject2, BODY_KEY, GraphResponse.NON_JSON_RESPONSE_PROPERTY);
                if (stringPropertyAsJSON != null && (stringPropertyAsJSON instanceof JSONObject)) {
                    JSONObject jSONObject3 = (JSONObject) stringPropertyAsJSON;
                    boolean z2 = true;
                    int i3 = -1;
                    if (jSONObject3.has("error")) {
                        JSONObject jSONObject4 = (JSONObject) Utility.getStringPropertyAsJSON(jSONObject3, "error", (String) null);
                        str4 = jSONObject4.optString("type", (String) null);
                        str3 = jSONObject4.optString("message", (String) null);
                        int optInt = jSONObject4.optInt(Constant.PARAM_ERROR_CODE, -1);
                        int optInt2 = jSONObject4.optInt("error_subcode", -1);
                        String optString = jSONObject4.optString(ERROR_USER_MSG_KEY, (String) null);
                        str = jSONObject4.optString(ERROR_USER_TITLE_KEY, (String) null);
                        i = optInt2;
                        i3 = optInt;
                        str2 = optString;
                        z = jSONObject4.optBoolean(ERROR_IS_TRANSIENT_KEY, false);
                    } else {
                        if (!jSONObject3.has("error_code") && !jSONObject3.has(ERROR_MSG_KEY)) {
                            if (!jSONObject3.has(ERROR_REASON_KEY)) {
                                str4 = null;
                                str3 = null;
                                str2 = null;
                                str = null;
                                z2 = false;
                                i = -1;
                                z = false;
                            }
                        }
                        String optString2 = jSONObject3.optString(ERROR_REASON_KEY, (String) null);
                        String optString3 = jSONObject3.optString(ERROR_MSG_KEY, (String) null);
                        int optInt3 = jSONObject3.optInt("error_code", -1);
                        i = jSONObject3.optInt("error_subcode", -1);
                        i3 = optInt3;
                        str2 = null;
                        str = null;
                        z = false;
                        str3 = optString3;
                        str4 = optString2;
                    }
                    if (z2) {
                        return new FacebookRequestError(i2, i3, i, str4, str3, str, str2, z, jSONObject3, jSONObject, obj, httpURLConnection, (FacebookException) null);
                    }
                }
                if (!a.a(i2)) {
                    return new FacebookRequestError(i2, -1, -1, (String) null, (String) null, (String) null, (String) null, false, jSONObject2.has(BODY_KEY) ? (JSONObject) Utility.getStringPropertyAsJSON(jSONObject2, BODY_KEY, GraphResponse.NON_JSON_RESPONSE_PROPERTY) : null, jSONObject, obj, httpURLConnection, (FacebookException) null);
                }
            }
        } catch (JSONException unused) {
        }
        return null;
    }

    static synchronized FacebookRequestErrorClassification a() {
        synchronized (FacebookRequestError.class) {
            FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            if (appSettingsWithoutQuery == null) {
                FacebookRequestErrorClassification defaultErrorClassification = FacebookRequestErrorClassification.getDefaultErrorClassification();
                return defaultErrorClassification;
            }
            FacebookRequestErrorClassification errorClassification = appSettingsWithoutQuery.getErrorClassification();
            return errorClassification;
        }
    }

    public int describeContents() {
        return 0;
    }

    public Object getBatchRequestResult() {
        return this.batchRequestResult;
    }

    public Category getCategory() {
        return this.category;
    }

    public HttpURLConnection getConnection() {
        return this.connection;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        String str = this.errorMessage;
        return str != null ? str : this.exception.getLocalizedMessage();
    }

    public String getErrorRecoveryMessage() {
        return this.errorRecoveryMessage;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public String getErrorUserMessage() {
        return this.errorUserMessage;
    }

    public String getErrorUserTitle() {
        return this.errorUserTitle;
    }

    public FacebookException getException() {
        return this.exception;
    }

    public JSONObject getRequestResult() {
        return this.requestResult;
    }

    public JSONObject getRequestResultBody() {
        return this.requestResultBody;
    }

    public int getRequestStatusCode() {
        return this.requestStatusCode;
    }

    public int getSubErrorCode() {
        return this.subErrorCode;
    }

    public String toString() {
        return "{HttpStatus: " + this.requestStatusCode + ", errorCode: " + this.errorCode + ", subErrorCode: " + this.subErrorCode + ", errorType: " + this.errorType + ", errorMessage: " + getErrorMessage() + "}";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.requestStatusCode);
        parcel.writeInt(this.errorCode);
        parcel.writeInt(this.subErrorCode);
        parcel.writeString(this.errorType);
        parcel.writeString(this.errorMessage);
        parcel.writeString(this.errorUserTitle);
        parcel.writeString(this.errorUserMessage);
    }
}
