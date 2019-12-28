package com.facebook;

import android.util.Log;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GraphResponse {
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_LOG_TAG = "Response";
    public static final String SUCCESS_KEY = "success";
    private static final String TAG = "GraphResponse";
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final JSONObject graphObject;
    private final JSONArray graphObjectArray;
    private final String rawResponse;
    private final GraphRequest request;

    public enum PagingDirection {
        NEXT,
        PREVIOUS
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, FacebookRequestError facebookRequestError) {
        this(graphRequest, httpURLConnection, (String) null, (JSONObject) null, (JSONArray) null, facebookRequestError);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONArray jSONArray) {
        this(graphRequest, httpURLConnection, str, (JSONObject) null, jSONArray, (FacebookRequestError) null);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject) {
        this(graphRequest, httpURLConnection, str, jSONObject, (JSONArray) null, (FacebookRequestError) null);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, FacebookRequestError facebookRequestError) {
        this.request = graphRequest;
        this.connection = httpURLConnection;
        this.rawResponse = str;
        this.graphObject = jSONObject;
        this.graphObjectArray = jSONArray;
        this.error = facebookRequestError;
    }

    static List<GraphResponse> a(InputStream inputStream, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        String readStreamToString = Utility.readStreamToString(inputStream);
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, RESPONSE_LOG_TAG, "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(readStreamToString.length()), readStreamToString);
        return a(readStreamToString, httpURLConnection, graphRequestBatch);
    }

    static List<GraphResponse> a(String str, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        List<GraphResponse> createResponsesFromObject = createResponsesFromObject(httpURLConnection, graphRequestBatch, new JSONTokener(str).nextValue());
        Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", graphRequestBatch.a(), Integer.valueOf(str.length()), createResponsesFromObject);
        return createResponsesFromObject;
    }

    static List<GraphResponse> a(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getResponseCode() >= 400 ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream();
            return a(inputStream, httpURLConnection, graphRequestBatch);
        } catch (FacebookException e) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", e);
            return a((List<GraphRequest>) graphRequestBatch, httpURLConnection, e);
        } catch (Exception e2) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", e2);
            return a((List<GraphRequest>) graphRequestBatch, httpURLConnection, new FacebookException((Throwable) e2));
        } finally {
            Utility.closeQuietly(inputStream);
        }
    }

    static List<GraphResponse> a(List<GraphRequest> list, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new GraphResponse(list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) facebookException)));
        }
        return arrayList;
    }

    private static GraphResponse createResponseFromObject(GraphRequest graphRequest, HttpURLConnection httpURLConnection, Object obj, Object obj2) {
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            FacebookRequestError a = FacebookRequestError.a(jSONObject, obj2, httpURLConnection);
            if (a != null) {
                Log.e(TAG, a.toString());
                if (a.getErrorCode() == 190 && Utility.isCurrentAccessToken(graphRequest.getAccessToken())) {
                    if (a.getSubErrorCode() != 493) {
                        AccessToken.setCurrentAccessToken((AccessToken) null);
                    } else if (!AccessToken.getCurrentAccessToken().isExpired()) {
                        AccessToken.a();
                    }
                }
                return new GraphResponse(graphRequest, httpURLConnection, a);
            }
            Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject, BODY_KEY, NON_JSON_RESPONSE_PROPERTY);
            if (stringPropertyAsJSON instanceof JSONObject) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONObject) stringPropertyAsJSON);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONArray) stringPropertyAsJSON);
            }
            obj = JSONObject.NULL;
        }
        if (obj == JSONObject.NULL) {
            return new GraphResponse(graphRequest, httpURLConnection, obj.toString(), (JSONObject) null);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + obj.getClass().getSimpleName());
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.facebook.GraphResponse> createResponsesFromObject(java.net.HttpURLConnection r7, java.util.List<com.facebook.GraphRequest> r8, java.lang.Object r9) {
        /*
            int r0 = r8.size()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
            r2 = 0
            r3 = 1
            if (r0 != r3) goto L_0x004e
            java.lang.Object r3 = r8.get(r2)
            com.facebook.GraphRequest r3 = (com.facebook.GraphRequest) r3
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0040, IOException -> 0x0034 }
            r4.<init>()     // Catch:{ JSONException -> 0x0040, IOException -> 0x0034 }
            java.lang.String r5 = "body"
            r4.put(r5, r9)     // Catch:{ JSONException -> 0x0040, IOException -> 0x0034 }
            if (r7 == 0) goto L_0x0024
            int r5 = r7.getResponseCode()     // Catch:{ JSONException -> 0x0040, IOException -> 0x0034 }
            goto L_0x0026
        L_0x0024:
            r5 = 200(0xc8, float:2.8E-43)
        L_0x0026:
            java.lang.String r6 = "code"
            r4.put(r6, r5)     // Catch:{ JSONException -> 0x0040, IOException -> 0x0034 }
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0040, IOException -> 0x0034 }
            r5.<init>()     // Catch:{ JSONException -> 0x0040, IOException -> 0x0034 }
            r5.put(r4)     // Catch:{ JSONException -> 0x0040, IOException -> 0x0034 }
            goto L_0x004f
        L_0x0034:
            r4 = move-exception
            com.facebook.GraphResponse r5 = new com.facebook.GraphResponse
            com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
            r6.<init>((java.net.HttpURLConnection) r7, (java.lang.Exception) r4)
            r5.<init>(r3, r7, r6)
            goto L_0x004b
        L_0x0040:
            r4 = move-exception
            com.facebook.GraphResponse r5 = new com.facebook.GraphResponse
            com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
            r6.<init>((java.net.HttpURLConnection) r7, (java.lang.Exception) r4)
            r5.<init>(r3, r7, r6)
        L_0x004b:
            r1.add(r5)
        L_0x004e:
            r5 = r9
        L_0x004f:
            boolean r3 = r5 instanceof org.json.JSONArray
            if (r3 == 0) goto L_0x0091
            org.json.JSONArray r5 = (org.json.JSONArray) r5
            int r3 = r5.length()
            if (r3 != r0) goto L_0x0091
        L_0x005b:
            int r0 = r5.length()
            if (r2 >= r0) goto L_0x0090
            java.lang.Object r0 = r8.get(r2)
            com.facebook.GraphRequest r0 = (com.facebook.GraphRequest) r0
            java.lang.Object r3 = r5.get(r2)     // Catch:{ JSONException -> 0x007f, FacebookException -> 0x0073 }
            com.facebook.GraphResponse r3 = createResponseFromObject(r0, r7, r3, r9)     // Catch:{ JSONException -> 0x007f, FacebookException -> 0x0073 }
            r1.add(r3)     // Catch:{ JSONException -> 0x007f, FacebookException -> 0x0073 }
            goto L_0x008d
        L_0x0073:
            r3 = move-exception
            com.facebook.GraphResponse r4 = new com.facebook.GraphResponse
            com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
            r6.<init>((java.net.HttpURLConnection) r7, (java.lang.Exception) r3)
            r4.<init>(r0, r7, r6)
            goto L_0x008a
        L_0x007f:
            r3 = move-exception
            com.facebook.GraphResponse r4 = new com.facebook.GraphResponse
            com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
            r6.<init>((java.net.HttpURLConnection) r7, (java.lang.Exception) r3)
            r4.<init>(r0, r7, r6)
        L_0x008a:
            r1.add(r4)
        L_0x008d:
            int r2 = r2 + 1
            goto L_0x005b
        L_0x0090:
            return r1
        L_0x0091:
            com.facebook.FacebookException r7 = new com.facebook.FacebookException
            java.lang.String r8 = "Unexpected number of results"
            r7.<init>((java.lang.String) r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphResponse.createResponsesFromObject(java.net.HttpURLConnection, java.util.List, java.lang.Object):java.util.List");
    }

    public final HttpURLConnection getConnection() {
        return this.connection;
    }

    public final FacebookRequestError getError() {
        return this.error;
    }

    public final JSONArray getJSONArray() {
        return this.graphObjectArray;
    }

    public final JSONObject getJSONObject() {
        return this.graphObject;
    }

    public String getRawResponse() {
        return this.rawResponse;
    }

    public GraphRequest getRequest() {
        return this.request;
    }

    public GraphRequest getRequestForPagedResults(PagingDirection pagingDirection) {
        String str;
        JSONObject optJSONObject;
        JSONObject jSONObject = this.graphObject;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("paging")) == null) {
            str = null;
        } else {
            str = optJSONObject.optString(pagingDirection == PagingDirection.NEXT ? "next" : "previous");
        }
        if (Utility.isNullOrEmpty(str)) {
            return null;
        }
        if (str != null && str.equals(this.request.b())) {
            return null;
        }
        try {
            return new GraphRequest(this.request.getAccessToken(), new URL(str));
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    public String toString() {
        String str;
        try {
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.connection != null ? this.connection.getResponseCode() : 200);
            str = String.format(locale, "%d", objArr);
        } catch (IOException unused) {
            str = "unknown";
        }
        return "{Response: " + " responseCode: " + str + ", graphObject: " + this.graphObject + ", error: " + this.error + "}";
    }
}
