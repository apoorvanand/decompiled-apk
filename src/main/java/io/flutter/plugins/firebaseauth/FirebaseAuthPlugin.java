package io.flutter.plugins.firebaseauth;

import android.net.Uri;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.gson.Gson;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FirebaseAuthPlugin implements MethodChannel.MethodCallHandler {
    private final SparseArray<FirebaseAuth.AuthStateListener> authStateListeners = new SparseArray<>();
    /* access modifiers changed from: private */
    public final MethodChannel channel;
    private final SparseArray<PhoneAuthProvider.ForceResendingToken> forceResendingTokens = new SparseArray<>();
    private int nextHandle = 0;
    private final PluginRegistry.Registrar registrar;

    private class GetSignInMethodsCompleteListener implements OnCompleteListener<SignInMethodQueryResult> {
        private final MethodChannel.Result result;

        GetSignInMethodsCompleteListener(MethodChannel.Result result2) {
            this.result = result2;
        }

        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
            if (!task.isSuccessful() || task.getResult() == null) {
                FirebaseAuthPlugin.this.reportException(this.result, task.getException());
                return;
            }
            this.result.success(task.getResult().getSignInMethods());
        }
    }

    private class SignInCompleteListener implements OnCompleteListener<AuthResult> {
        private final MethodChannel.Result result;

        SignInCompleteListener(MethodChannel.Result result2) {
            this.result = result2;
        }

        public void onComplete(@NonNull Task<AuthResult> task) {
            if (!task.isSuccessful() || task.getResult() == null) {
                FirebaseAuthPlugin.this.reportException(this.result, task.getException());
                return;
            }
            this.result.success(Collections.unmodifiableMap(FirebaseAuthPlugin.this.mapFromUser(task.getResult().getUser())));
        }
    }

    private class TaskVoidCompleteListener implements OnCompleteListener<Void> {
        private final MethodChannel.Result result;

        TaskVoidCompleteListener(MethodChannel.Result result2) {
            this.result = result2;
        }

        public void onComplete(@NonNull Task<Void> task) {
            if (!task.isSuccessful()) {
                FirebaseAuthPlugin.this.reportException(this.result, task.getException());
            } else {
                this.result.success((Object) null);
            }
        }
    }

    private FirebaseAuthPlugin(PluginRegistry.Registrar registrar2, MethodChannel methodChannel) {
        this.registrar = registrar2;
        this.channel = methodChannel;
        FirebaseApp.initializeApp(registrar2.context());
    }

    private FirebaseAuth getAuth(MethodCall methodCall) {
        return FirebaseAuth.getInstance(FirebaseApp.getInstance((String) ((Map) methodCall.arguments()).get("app")));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.firebase.auth.AuthCredential getCredential(java.util.Map<java.lang.String, java.lang.Object> r3) {
        /*
            r2 = this;
            java.lang.String r0 = "data"
            java.lang.Object r0 = r3.get(r0)
            java.util.Map r0 = (java.util.Map) r0
            java.lang.String r1 = "provider"
            java.lang.Object r3 = r3.get(r1)
            java.lang.String r3 = (java.lang.String) r3
            int r1 = r3.hashCode()
            switch(r1) {
                case -1830313082: goto L_0x004a;
                case -1536293812: goto L_0x0040;
                case -364826023: goto L_0x0036;
                case 106642798: goto L_0x002c;
                case 1216985755: goto L_0x0022;
                case 1985010934: goto L_0x0018;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x0054
        L_0x0018:
            java.lang.String r1 = "github.com"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0054
            r3 = 4
            goto L_0x0055
        L_0x0022:
            java.lang.String r1 = "password"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0054
            r3 = 0
            goto L_0x0055
        L_0x002c:
            java.lang.String r1 = "phone"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0054
            r3 = 5
            goto L_0x0055
        L_0x0036:
            java.lang.String r1 = "facebook.com"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0054
            r3 = 2
            goto L_0x0055
        L_0x0040:
            java.lang.String r1 = "google.com"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0054
            r3 = 1
            goto L_0x0055
        L_0x004a:
            java.lang.String r1 = "twitter.com"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0054
            r3 = 3
            goto L_0x0055
        L_0x0054:
            r3 = -1
        L_0x0055:
            switch(r3) {
                case 0: goto L_0x00d3;
                case 1: goto L_0x00be;
                case 2: goto L_0x00b1;
                case 3: goto L_0x009c;
                case 4: goto L_0x008f;
                case 5: goto L_0x005b;
                default: goto L_0x0058;
            }
        L_0x0058:
            r3 = 0
            goto L_0x00fc
        L_0x005b:
            java.lang.String r3 = "verificationId"
            boolean r3 = r0.containsKey(r3)
            if (r3 == 0) goto L_0x0079
            java.lang.String r3 = "verificationId"
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r1 = "smsCode"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            com.google.firebase.auth.PhoneAuthCredential r3 = com.google.firebase.auth.PhoneAuthProvider.getCredential(r3, r0)
            goto L_0x00fc
        L_0x0079:
            com.google.gson.Gson r3 = new com.google.gson.Gson
            r3.<init>()
            java.lang.String r1 = "jsonObject"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Class<com.google.firebase.auth.PhoneAuthCredential> r1 = com.google.firebase.auth.PhoneAuthCredential.class
            java.lang.Object r3 = r3.fromJson((java.lang.String) r0, r1)
            com.google.firebase.auth.AuthCredential r3 = (com.google.firebase.auth.AuthCredential) r3
            goto L_0x00fc
        L_0x008f:
            java.lang.String r3 = "token"
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            com.google.firebase.auth.AuthCredential r3 = com.google.firebase.auth.GithubAuthProvider.getCredential(r3)
            goto L_0x00fc
        L_0x009c:
            java.lang.String r3 = "authToken"
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r1 = "authTokenSecret"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            com.google.firebase.auth.AuthCredential r3 = com.google.firebase.auth.TwitterAuthProvider.getCredential(r3, r0)
            goto L_0x00fc
        L_0x00b1:
            java.lang.String r3 = "accessToken"
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            com.google.firebase.auth.AuthCredential r3 = com.google.firebase.auth.FacebookAuthProvider.getCredential(r3)
            goto L_0x00fc
        L_0x00be:
            java.lang.String r3 = "idToken"
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r1 = "accessToken"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            com.google.firebase.auth.AuthCredential r3 = com.google.firebase.auth.GoogleAuthProvider.getCredential(r3, r0)
            goto L_0x00fc
        L_0x00d3:
            java.lang.String r3 = "email"
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r1 = "password"
            boolean r1 = r0.containsKey(r1)
            if (r1 == 0) goto L_0x00f0
            java.lang.String r1 = "password"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            com.google.firebase.auth.AuthCredential r3 = com.google.firebase.auth.EmailAuthProvider.getCredential(r3, r0)
            goto L_0x00fc
        L_0x00f0:
            java.lang.String r1 = "link"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            com.google.firebase.auth.AuthCredential r3 = com.google.firebase.auth.EmailAuthProvider.getCredentialWithLink(r3, r0)
        L_0x00fc:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.firebaseauth.FirebaseAuthPlugin.getCredential(java.util.Map):com.google.firebase.auth.AuthCredential");
    }

    /* access modifiers changed from: private */
    public Map<String, Object> getVerifyPhoneNumberExceptionMap(FirebaseException firebaseException) {
        String str = "verifyPhoneNumberError";
        if (firebaseException instanceof FirebaseAuthInvalidCredentialsException) {
            str = "invalidCredential";
        } else if (firebaseException instanceof FirebaseAuthException) {
            str = "firebaseAuth";
        } else if (firebaseException instanceof FirebaseTooManyRequestsException) {
            str = "quotaExceeded";
        } else if (firebaseException instanceof FirebaseApiNotAvailableException) {
            str = "apiNotAvailable";
        }
        HashMap hashMap = new HashMap();
        hashMap.put(Constant.PARAM_ERROR_CODE, str);
        hashMap.put("message", firebaseException.getMessage());
        return hashMap;
    }

    private void handleCreateUserWithEmailAndPassword(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        Map map = (Map) methodCall.arguments();
        firebaseAuth.createUserWithEmailAndPassword((String) map.get("email"), (String) map.get("password")).addOnCompleteListener(new SignInCompleteListener(result));
    }

    private void handleCurrentUser(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        result.success(currentUser == null ? null : mapFromUser(currentUser));
    }

    private void handleDelete(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
        } else {
            currentUser.delete().addOnCompleteListener(new TaskVoidCompleteListener(result));
        }
    }

    private void handleFetchSignInMethodsForEmail(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        firebaseAuth.fetchSignInMethodsForEmail((String) ((Map) methodCall.arguments()).get("email")).addOnCompleteListener(new GetSignInMethodsCompleteListener(result));
    }

    private void handleGetToken(MethodCall methodCall, final MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
        } else {
            currentUser.getIdToken(((Boolean) ((Map) methodCall.arguments()).get("refresh")).booleanValue()).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                public void onComplete(@NonNull Task<GetTokenResult> task) {
                    if (!task.isSuccessful() || task.getResult() == null) {
                        FirebaseAuthPlugin.this.reportException(result, task.getException());
                        return;
                    }
                    result.success(task.getResult().getToken());
                }
            });
        }
    }

    private void handleIsSignInWithEmailLink(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        result.success(Boolean.valueOf(firebaseAuth.isSignInWithEmailLink((String) ((Map) methodCall.arguments()).get("link"))));
    }

    private void handleLinkWithCredential(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
        } else {
            currentUser.linkWithCredential(getCredential((Map) methodCall.arguments)).addOnCompleteListener(new SignInCompleteListener(result));
        }
    }

    private void handleReauthenticateWithCredential(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
        } else {
            currentUser.reauthenticate(getCredential((Map) methodCall.arguments())).addOnCompleteListener(new TaskVoidCompleteListener(result));
        }
    }

    private void handleReload(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
        } else {
            currentUser.reload().addOnCompleteListener(new TaskVoidCompleteListener(result));
        }
    }

    private void handleSendEmailVerification(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
        } else {
            currentUser.sendEmailVerification().addOnCompleteListener(new TaskVoidCompleteListener(result));
        }
    }

    private void handleSendLinkToEmail(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        Map map = (Map) methodCall.arguments();
        firebaseAuth.sendSignInLinkToEmail(map.get("email").toString(), ActionCodeSettings.newBuilder().setUrl(map.get("url").toString()).setHandleCodeInApp(((Boolean) map.get("handleCodeInApp")).booleanValue()).setIOSBundleId(map.get("iOSBundleID").toString()).setAndroidPackageName(map.get("androidPackageName").toString(), ((Boolean) map.get("androidInstallIfNotAvailable")).booleanValue(), map.get("androidMinimumVersion").toString()).build()).addOnCompleteListener(new TaskVoidCompleteListener(result));
    }

    private void handleSendPasswordResetEmail(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        firebaseAuth.sendPasswordResetEmail((String) ((Map) methodCall.arguments()).get("email")).addOnCompleteListener(new TaskVoidCompleteListener(result));
    }

    private void handleSetLanguageCode(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        firebaseAuth.setLanguageCode((String) ((Map) methodCall.arguments()).get("language"));
        result.success((Object) null);
    }

    private void handleSignInAnonymously(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInAnonymously().addOnCompleteListener(new SignInCompleteListener(result));
    }

    private void handleSignInWithCredential(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithCredential(getCredential((Map) methodCall.arguments())).addOnCompleteListener(new SignInCompleteListener(result));
    }

    private void handleSignInWithCustomToken(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithCustomToken((String) ((Map) methodCall.arguments()).get("token")).addOnCompleteListener(new SignInCompleteListener(result));
    }

    private void handleSignInWithEmailAndLink(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        Map map = (Map) methodCall.arguments();
        firebaseAuth.signInWithEmailLink((String) map.get("email"), (String) map.get("link")).addOnCompleteListener(new SignInCompleteListener(result));
    }

    private void handleSignInWithPhoneNumber(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        Map map = (Map) methodCall.arguments();
        firebaseAuth.signInWithCredential(PhoneAuthProvider.getCredential((String) map.get("verificationId"), (String) map.get("smsCode"))).addOnCompleteListener(new SignInCompleteListener(result));
    }

    private void handleSignOut(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        firebaseAuth.signOut();
        result.success((Object) null);
    }

    private void handleStartListeningAuthState(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        final int i = this.nextHandle;
        this.nextHandle = i + 1;
        AnonymousClass3 r0 = new FirebaseAuth.AuthStateListener() {
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Map access$300 = FirebaseAuthPlugin.this.mapFromUser(firebaseAuth.getCurrentUser());
                HashMap hashMap = new HashMap();
                hashMap.put(ShareConstants.WEB_DIALOG_PARAM_ID, Integer.valueOf(i));
                if (access$300 != null) {
                    hashMap.put("user", access$300);
                }
                FirebaseAuthPlugin.this.channel.invokeMethod("onAuthStateChanged", Collections.unmodifiableMap(hashMap));
            }
        };
        firebaseAuth.addAuthStateListener(r0);
        this.authStateListeners.append(i, r0);
        result.success(Integer.valueOf(i));
    }

    private void handleStopListeningAuthState(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        Integer num = (Integer) ((Map) methodCall.arguments()).get(ShareConstants.WEB_DIALOG_PARAM_ID);
        FirebaseAuth.AuthStateListener authStateListener = this.authStateListeners.get(num.intValue());
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
            this.authStateListeners.remove(num.intValue());
            result.success((Object) null);
            return;
        }
        reportException(result, new FirebaseAuthException("ERROR_LISTENER_NOT_FOUND", String.format(Locale.US, "Listener with identifier '%d' not found.", new Object[]{num})));
    }

    private void handleUnlinkFromProvider(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
        } else {
            currentUser.unlink((String) ((Map) methodCall.arguments()).get("provider")).addOnCompleteListener(new SignInCompleteListener(result));
        }
    }

    private void handleUpdateEmail(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
        } else {
            currentUser.updateEmail((String) ((Map) methodCall.arguments()).get("email")).addOnCompleteListener(new TaskVoidCompleteListener(result));
        }
    }

    private void handleUpdatePassword(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
        } else {
            currentUser.updatePassword((String) ((Map) methodCall.arguments()).get("password")).addOnCompleteListener(new TaskVoidCompleteListener(result));
        }
    }

    private void handleUpdatePhoneNumber(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        firebaseAuth.getCurrentUser().updatePhoneNumber((PhoneAuthCredential) getCredential((Map) methodCall.arguments)).addOnCompleteListener(new TaskVoidCompleteListener(result));
    }

    private void handleUpdateProfile(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            markUserRequired(result);
            return;
        }
        Map map = (Map) methodCall.arguments();
        UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
        if (map.containsKey("displayName")) {
            builder.setDisplayName((String) map.get("displayName"));
        }
        if (map.containsKey("photoUrl")) {
            builder.setPhotoUri(Uri.parse((String) map.get("photoUrl")));
        }
        currentUser.updateProfile(builder.build()).addOnCompleteListener(new TaskVoidCompleteListener(result));
    }

    private void handleVerifyPhoneNumber(MethodCall methodCall, MethodChannel.Result result, FirebaseAuth firebaseAuth) {
        Map map = (Map) methodCall.arguments();
        final int intValue = ((Integer) map.get("handle")).intValue();
        String str = (String) map.get("phoneNumber");
        int intValue2 = ((Integer) map.get("timeout")).intValue();
        AnonymousClass1 r8 = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            public void onCodeAutoRetrievalTimeOut(String str) {
                HashMap hashMap = new HashMap();
                hashMap.put("handle", Integer.valueOf(intValue));
                hashMap.put("verificationId", str);
                FirebaseAuthPlugin.this.channel.invokeMethod("phoneCodeAutoRetrievalTimeout", hashMap);
            }

            public void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                HashMap hashMap = new HashMap();
                hashMap.put("handle", Integer.valueOf(intValue));
                hashMap.put("verificationId", str);
                hashMap.put("forceResendingToken", Integer.valueOf(forceResendingToken.hashCode()));
                FirebaseAuthPlugin.this.channel.invokeMethod("phoneCodeSent", hashMap);
            }

            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                HashMap hashMap = new HashMap();
                hashMap.put("handle", Integer.valueOf(intValue));
                hashMap.put("phoneAuthCredential", new Gson().toJson((Object) phoneAuthCredential));
                FirebaseAuthPlugin.this.channel.invokeMethod("phoneVerificationCompleted", hashMap);
            }

            public void onVerificationFailed(FirebaseException firebaseException) {
                HashMap hashMap = new HashMap();
                hashMap.put("handle", Integer.valueOf(intValue));
                hashMap.put("exception", FirebaseAuthPlugin.this.getVerifyPhoneNumberExceptionMap(firebaseException));
                FirebaseAuthPlugin.this.channel.invokeMethod("phoneVerificationFailed", hashMap);
            }
        };
        if (methodCall.argument("forceResendingToken") != null) {
            int intValue3 = ((Integer) map.get("forceResendingToken")).intValue();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(str, (long) intValue2, TimeUnit.MILLISECONDS, this.registrar.activity(), (PhoneAuthProvider.OnVerificationStateChangedCallbacks) r8, this.forceResendingTokens.get(intValue3));
        } else {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(str, (long) intValue2, TimeUnit.MILLISECONDS, this.registrar.activity(), (PhoneAuthProvider.OnVerificationStateChangedCallbacks) r8);
        }
        result.success((Object) null);
    }

    /* access modifiers changed from: private */
    public Map<String, Object> mapFromUser(FirebaseUser firebaseUser) {
        if (firebaseUser == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (UserInfo userInfoToMap : firebaseUser.getProviderData()) {
            arrayList.add(Collections.unmodifiableMap(userInfoToMap(userInfoToMap)));
        }
        Map<String, Object> userInfoToMap2 = userInfoToMap(firebaseUser);
        FirebaseUserMetadata metadata = firebaseUser.getMetadata();
        if (metadata != null) {
            userInfoToMap2.put("creationTimestamp", Long.valueOf(metadata.getCreationTimestamp()));
            userInfoToMap2.put("lastSignInTimestamp", Long.valueOf(metadata.getLastSignInTimestamp()));
        }
        userInfoToMap2.put("isAnonymous", Boolean.valueOf(firebaseUser.isAnonymous()));
        userInfoToMap2.put("isEmailVerified", Boolean.valueOf(firebaseUser.isEmailVerified()));
        userInfoToMap2.put("providerData", Collections.unmodifiableList(arrayList));
        return Collections.unmodifiableMap(userInfoToMap2);
    }

    private void markUserRequired(MethodChannel.Result result) {
        result.error("USER_REQUIRED", "Please authenticate with Firebase first", (Object) null);
    }

    public static void registerWith(PluginRegistry.Registrar registrar2) {
        MethodChannel methodChannel = new MethodChannel(registrar2.messenger(), "plugins.flutter.io/firebase_auth");
        methodChannel.setMethodCallHandler(new FirebaseAuthPlugin(registrar2, methodChannel));
    }

    /* access modifiers changed from: private */
    public void reportException(MethodChannel.Result result, @Nullable Exception exc) {
        if (exc != null) {
            result.error(exc instanceof FirebaseAuthException ? ((FirebaseAuthException) exc).getErrorCode() : exc instanceof FirebaseApiNotAvailableException ? "ERROR_API_NOT_AVAILABLE" : exc instanceof FirebaseTooManyRequestsException ? "ERROR_TOO_MANY_REQUESTS" : exc instanceof FirebaseNetworkException ? "ERROR_NETWORK_REQUEST_FAILED" : exc.getClass().getSimpleName(), exc.getMessage(), (Object) null);
        } else {
            result.error("ERROR_UNKNOWN", "An unknown error occurred.", (Object) null);
        }
    }

    private Map<String, Object> userInfoToMap(UserInfo userInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put("providerId", userInfo.getProviderId());
        hashMap.put("uid", userInfo.getUid());
        if (userInfo.getDisplayName() != null) {
            hashMap.put("displayName", userInfo.getDisplayName());
        }
        if (userInfo.getPhotoUrl() != null) {
            hashMap.put("photoUrl", userInfo.getPhotoUrl().toString());
        }
        if (userInfo.getEmail() != null) {
            hashMap.put("email", userInfo.getEmail());
        }
        if (userInfo.getPhoneNumber() != null) {
            hashMap.put("phoneNumber", userInfo.getPhoneNumber());
        }
        return hashMap;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r3, io.flutter.plugin.common.MethodChannel.Result r4) {
        /*
            r2 = this;
            java.lang.String r0 = r3.method
            int r1 = r0.hashCode()
            switch(r1) {
                case -2136551058: goto L_0x0132;
                case -1805376352: goto L_0x0127;
                case -1690218529: goto L_0x011d;
                case -1677720546: goto L_0x0112;
                case -1615597208: goto L_0x0107;
                case -1393452349: goto L_0x00fd;
                case -1335458389: goto L_0x00f2;
                case -1312951447: goto L_0x00e8;
                case -1251930800: goto L_0x00dd;
                case -1164195432: goto L_0x00d3;
                case -934641255: goto L_0x00c7;
                case -597776144: goto L_0x00bb;
                case -597673901: goto L_0x00af;
                case -188796385: goto L_0x00a3;
                case -44475089: goto L_0x0097;
                case 89997872: goto L_0x008c;
                case 444913383: goto L_0x0080;
                case 601274596: goto L_0x0075;
                case 660450368: goto L_0x0069;
                case 890664037: goto L_0x005d;
                case 1011244229: goto L_0x0051;
                case 1255319951: goto L_0x0045;
                case 1546957911: goto L_0x0039;
                case 1980982628: goto L_0x002e;
                case 2087157380: goto L_0x0022;
                case 2088248401: goto L_0x0016;
                case 2096764669: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x013d
        L_0x000b:
            java.lang.String r1 = "signInWithEmailAndLink"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 7
            goto L_0x013e
        L_0x0016:
            java.lang.String r1 = "signOut"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 13
            goto L_0x013e
        L_0x0022:
            java.lang.String r1 = "updatePassword"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 20
            goto L_0x013e
        L_0x002e:
            java.lang.String r1 = "isSignInWithEmailLink"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 6
            goto L_0x013e
        L_0x0039:
            java.lang.String r1 = "linkWithCredential"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 16
            goto L_0x013e
        L_0x0045:
            java.lang.String r1 = "signInWithPhoneNumber"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 25
            goto L_0x013e
        L_0x0051:
            java.lang.String r1 = "updatePhoneNumberCredential"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 19
            goto L_0x013e
        L_0x005d:
            java.lang.String r1 = "reauthenticateWithCredential"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 15
            goto L_0x013e
        L_0x0069:
            java.lang.String r1 = "signInWithCustomToken"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 12
            goto L_0x013e
        L_0x0075:
            java.lang.String r1 = "currentUser"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 0
            goto L_0x013e
        L_0x0080:
            java.lang.String r1 = "setLanguageCode"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 26
            goto L_0x013e
        L_0x008c:
            java.lang.String r1 = "sendPasswordResetEmail"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 4
            goto L_0x013e
        L_0x0097:
            java.lang.String r1 = "sendEmailVerification"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 8
            goto L_0x013e
        L_0x00a3:
            java.lang.String r1 = "signInWithCredential"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 11
            goto L_0x013e
        L_0x00af:
            java.lang.String r1 = "updateEmail"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 18
            goto L_0x013e
        L_0x00bb:
            java.lang.String r1 = "stopListeningAuthState"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 23
            goto L_0x013e
        L_0x00c7:
            java.lang.String r1 = "reload"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 9
            goto L_0x013e
        L_0x00d3:
            java.lang.String r1 = "signInAnonymously"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 1
            goto L_0x013e
        L_0x00dd:
            java.lang.String r1 = "startListeningAuthState"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 22
            goto L_0x013e
        L_0x00e8:
            java.lang.String r1 = "fetchSignInMethodsForEmail"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 3
            goto L_0x013e
        L_0x00f2:
            java.lang.String r1 = "delete"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 10
            goto L_0x013e
        L_0x00fd:
            java.lang.String r1 = "createUserWithEmailAndPassword"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 2
            goto L_0x013e
        L_0x0107:
            java.lang.String r1 = "getIdToken"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 14
            goto L_0x013e
        L_0x0112:
            java.lang.String r1 = "verifyPhoneNumber"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 24
            goto L_0x013e
        L_0x011d:
            java.lang.String r1 = "sendLinkToEmail"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 5
            goto L_0x013e
        L_0x0127:
            java.lang.String r1 = "updateProfile"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 21
            goto L_0x013e
        L_0x0132:
            java.lang.String r1 = "unlinkFromProvider"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x013d
            r0 = 17
            goto L_0x013e
        L_0x013d:
            r0 = -1
        L_0x013e:
            switch(r0) {
                case 0: goto L_0x0224;
                case 1: goto L_0x021c;
                case 2: goto L_0x0214;
                case 3: goto L_0x020c;
                case 4: goto L_0x0204;
                case 5: goto L_0x01fc;
                case 6: goto L_0x01f4;
                case 7: goto L_0x01ec;
                case 8: goto L_0x01e4;
                case 9: goto L_0x01dc;
                case 10: goto L_0x01d4;
                case 11: goto L_0x01cc;
                case 12: goto L_0x01c4;
                case 13: goto L_0x01bb;
                case 14: goto L_0x01b2;
                case 15: goto L_0x01a9;
                case 16: goto L_0x01a0;
                case 17: goto L_0x0197;
                case 18: goto L_0x018e;
                case 19: goto L_0x0185;
                case 20: goto L_0x017c;
                case 21: goto L_0x0173;
                case 22: goto L_0x016a;
                case 23: goto L_0x0161;
                case 24: goto L_0x0158;
                case 25: goto L_0x014f;
                case 26: goto L_0x0146;
                default: goto L_0x0141;
            }
        L_0x0141:
            r4.notImplemented()
            goto L_0x022b
        L_0x0146:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSetLanguageCode(r3, r4, r0)
            goto L_0x022b
        L_0x014f:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSignInWithPhoneNumber(r3, r4, r0)
            goto L_0x022b
        L_0x0158:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleVerifyPhoneNumber(r3, r4, r0)
            goto L_0x022b
        L_0x0161:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleStopListeningAuthState(r3, r4, r0)
            goto L_0x022b
        L_0x016a:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleStartListeningAuthState(r3, r4, r0)
            goto L_0x022b
        L_0x0173:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleUpdateProfile(r3, r4, r0)
            goto L_0x022b
        L_0x017c:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleUpdatePassword(r3, r4, r0)
            goto L_0x022b
        L_0x0185:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleUpdatePhoneNumber(r3, r4, r0)
            goto L_0x022b
        L_0x018e:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleUpdateEmail(r3, r4, r0)
            goto L_0x022b
        L_0x0197:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleUnlinkFromProvider(r3, r4, r0)
            goto L_0x022b
        L_0x01a0:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleLinkWithCredential(r3, r4, r0)
            goto L_0x022b
        L_0x01a9:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleReauthenticateWithCredential(r3, r4, r0)
            goto L_0x022b
        L_0x01b2:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleGetToken(r3, r4, r0)
            goto L_0x022b
        L_0x01bb:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSignOut(r3, r4, r0)
            goto L_0x022b
        L_0x01c4:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSignInWithCustomToken(r3, r4, r0)
            goto L_0x022b
        L_0x01cc:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSignInWithCredential(r3, r4, r0)
            goto L_0x022b
        L_0x01d4:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleDelete(r3, r4, r0)
            goto L_0x022b
        L_0x01dc:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleReload(r3, r4, r0)
            goto L_0x022b
        L_0x01e4:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSendEmailVerification(r3, r4, r0)
            goto L_0x022b
        L_0x01ec:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSignInWithEmailAndLink(r3, r4, r0)
            goto L_0x022b
        L_0x01f4:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleIsSignInWithEmailLink(r3, r4, r0)
            goto L_0x022b
        L_0x01fc:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSendLinkToEmail(r3, r4, r0)
            goto L_0x022b
        L_0x0204:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSendPasswordResetEmail(r3, r4, r0)
            goto L_0x022b
        L_0x020c:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleFetchSignInMethodsForEmail(r3, r4, r0)
            goto L_0x022b
        L_0x0214:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleCreateUserWithEmailAndPassword(r3, r4, r0)
            goto L_0x022b
        L_0x021c:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleSignInAnonymously(r3, r4, r0)
            goto L_0x022b
        L_0x0224:
            com.google.firebase.auth.FirebaseAuth r0 = r2.getAuth(r3)
            r2.handleCurrentUser(r3, r4, r0)
        L_0x022b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.firebaseauth.FirebaseAuthPlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
