package com.facebook.login;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class GetTokenLoginMethodHandler extends LoginMethodHandler {
    public static final Parcelable.Creator<GetTokenLoginMethodHandler> CREATOR = new Parcelable.Creator() {
        public GetTokenLoginMethodHandler createFromParcel(Parcel parcel) {
            return new GetTokenLoginMethodHandler(parcel);
        }

        public GetTokenLoginMethodHandler[] newArray(int i) {
            return new GetTokenLoginMethodHandler[i];
        }
    };
    private GetTokenClient getTokenClient;

    GetTokenLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    GetTokenLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "get_token";
    }

    /* access modifiers changed from: package-private */
    public void a(LoginClient.Request request, Bundle bundle) {
        GetTokenClient getTokenClient2 = this.getTokenClient;
        if (getTokenClient2 != null) {
            getTokenClient2.setCompletedListener((PlatformServiceClient.CompletedListener) null);
        }
        this.getTokenClient = null;
        this.b.i();
        if (bundle != null) {
            ArrayList<String> stringArrayList = bundle.getStringArrayList(NativeProtocol.EXTRA_PERMISSIONS);
            Set<String> a = request.a();
            if (stringArrayList == null || (a != null && !stringArrayList.containsAll(a))) {
                HashSet hashSet = new HashSet();
                for (String next : a) {
                    if (!stringArrayList.contains(next)) {
                        hashSet.add(next);
                    }
                }
                if (!hashSet.isEmpty()) {
                    a("new_permissions", TextUtils.join(",", hashSet));
                }
                request.a((Set<String>) hashSet);
            } else {
                c(request, bundle);
                return;
            }
        }
        this.b.f();
    }

    /* access modifiers changed from: package-private */
    public boolean a(final LoginClient.Request request) {
        this.getTokenClient = new GetTokenClient(this.b.a(), request.d());
        if (!this.getTokenClient.start()) {
            return false;
        }
        this.b.h();
        this.getTokenClient.setCompletedListener(new PlatformServiceClient.CompletedListener() {
            public void completed(Bundle bundle) {
                GetTokenLoginMethodHandler.this.a(request, bundle);
            }
        });
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        GetTokenClient getTokenClient2 = this.getTokenClient;
        if (getTokenClient2 != null) {
            getTokenClient2.cancel();
            this.getTokenClient.setCompletedListener((PlatformServiceClient.CompletedListener) null);
            this.getTokenClient = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void b(LoginClient.Request request, Bundle bundle) {
        this.b.a(LoginClient.Result.a(this.b.getPendingRequest(), a(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE, request.d())));
    }

    /* access modifiers changed from: package-private */
    public void c(final LoginClient.Request request, final Bundle bundle) {
        String string = bundle.getString(NativeProtocol.EXTRA_USER_ID);
        if (string == null || string.isEmpty()) {
            this.b.h();
            Utility.getGraphMeRequestWithCacheAsync(bundle.getString(NativeProtocol.EXTRA_ACCESS_TOKEN), new Utility.GraphMeRequestWithCacheCallback() {
                public void onFailure(FacebookException facebookException) {
                    GetTokenLoginMethodHandler.this.b.b(LoginClient.Result.a(GetTokenLoginMethodHandler.this.b.getPendingRequest(), "Caught exception", facebookException.getMessage()));
                }

                public void onSuccess(JSONObject jSONObject) {
                    try {
                        bundle.putString(NativeProtocol.EXTRA_USER_ID, jSONObject.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
                        GetTokenLoginMethodHandler.this.b(request, bundle);
                    } catch (JSONException e) {
                        GetTokenLoginMethodHandler.this.b.b(LoginClient.Result.a(GetTokenLoginMethodHandler.this.b.getPendingRequest(), "Caught exception", e.getMessage()));
                    }
                }
            });
            return;
        }
        b(request, bundle);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
