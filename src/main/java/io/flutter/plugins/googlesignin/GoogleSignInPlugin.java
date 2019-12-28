package io.flutter.plugins.googlesignin;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.googlesignin.BackgroundTaskRunner;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GoogleSignInPlugin implements MethodChannel.MethodCallHandler {
    private static final String CHANNEL_NAME = "plugins.flutter.io/google_sign_in";
    private static final String METHOD_CLEAR_AUTH_CACHE = "clearAuthCache";
    private static final String METHOD_DISCONNECT = "disconnect";
    private static final String METHOD_GET_TOKENS = "getTokens";
    private static final String METHOD_INIT = "init";
    private static final String METHOD_IS_SIGNED_IN = "isSignedIn";
    private static final String METHOD_SIGN_IN = "signIn";
    private static final String METHOD_SIGN_IN_SILENTLY = "signInSilently";
    private static final String METHOD_SIGN_OUT = "signOut";
    private final IDelegate delegate;

    public static final class Delegate implements PluginRegistry.ActivityResultListener, IDelegate {
        private static final String DEFAULT_GAMES_SIGN_IN = "SignInOption.games";
        private static final String DEFAULT_SIGN_IN = "SignInOption.standard";
        private static final String ERROR_FAILURE_TO_RECOVER_AUTH = "failed_to_recover_auth";
        private static final String ERROR_REASON_EXCEPTION = "exception";
        private static final String ERROR_REASON_SIGN_IN_CANCELED = "sign_in_canceled";
        private static final String ERROR_REASON_SIGN_IN_FAILED = "sign_in_failed";
        private static final String ERROR_REASON_SIGN_IN_REQUIRED = "sign_in_required";
        private static final String ERROR_REASON_STATUS = "status";
        private static final String ERROR_USER_RECOVERABLE_AUTH = "user_recoverable_auth";
        private static final int REQUEST_CODE_RECOVER_AUTH = 53294;
        private static final int REQUEST_CODE_SIGNIN = 53293;
        private final BackgroundTaskRunner backgroundTaskRunner = new BackgroundTaskRunner(1);
        /* access modifiers changed from: private */
        public PendingOperation pendingOperation;
        /* access modifiers changed from: private */
        public final PluginRegistry.Registrar registrar;
        /* access modifiers changed from: private */
        public List<String> requestedScopes;
        private GoogleSignInClient signInClient;

        private static class PendingOperation {
            final Object data;
            final String method;
            final MethodChannel.Result result;

            PendingOperation(String str, MethodChannel.Result result2, Object obj) {
                this.method = str;
                this.result = result2;
                this.data = obj;
            }
        }

        public Delegate(PluginRegistry.Registrar registrar2) {
            this.registrar = registrar2;
            registrar2.addActivityResultListener(this);
        }

        private void checkAndSetPendingOperation(String str, MethodChannel.Result result) {
            checkAndSetPendingOperation(str, result, (Object) null);
        }

        /* access modifiers changed from: private */
        public void checkAndSetPendingOperation(String str, MethodChannel.Result result, Object obj) {
            if (this.pendingOperation == null) {
                this.pendingOperation = new PendingOperation(str, result, obj);
                return;
            }
            throw new IllegalStateException("Concurrent operations detected: " + this.pendingOperation.method + ", " + str);
        }

        private String errorCodeForStatus(int i) {
            return i == 12501 ? ERROR_REASON_SIGN_IN_CANCELED : i == 4 ? ERROR_REASON_SIGN_IN_REQUIRED : ERROR_REASON_SIGN_IN_FAILED;
        }

        /* access modifiers changed from: private */
        public void finishWithError(String str, String str2) {
            this.pendingOperation.result.error(str, str2, (Object) null);
            this.pendingOperation = null;
        }

        /* access modifiers changed from: private */
        public void finishWithSuccess(Object obj) {
            this.pendingOperation.result.success(obj);
            this.pendingOperation = null;
        }

        private void onSignInAccount(GoogleSignInAccount googleSignInAccount) {
            HashMap hashMap = new HashMap();
            hashMap.put("email", googleSignInAccount.getEmail());
            hashMap.put(ShareConstants.WEB_DIALOG_PARAM_ID, googleSignInAccount.getId());
            hashMap.put("idToken", googleSignInAccount.getIdToken());
            hashMap.put("displayName", googleSignInAccount.getDisplayName());
            if (googleSignInAccount.getPhotoUrl() != null) {
                hashMap.put("photoUrl", googleSignInAccount.getPhotoUrl().toString());
            }
            finishWithSuccess(hashMap);
        }

        /* access modifiers changed from: private */
        public void onSignInResult(Task<GoogleSignInAccount> task) {
            String str;
            String str2;
            try {
                onSignInAccount(task.getResult(ApiException.class));
                return;
            } catch (ApiException e) {
                str2 = errorCodeForStatus(e.getStatusCode());
                str = e.toString();
            } catch (RuntimeExecutionException e2) {
                str2 = ERROR_REASON_EXCEPTION;
                str = e2.toString();
            }
            finishWithError(str2, str);
        }

        public void clearAuthCache(final MethodChannel.Result result, final String str) {
            this.backgroundTaskRunner.runInBackground(new Callable<Void>() {
                public Void call() {
                    GoogleAuthUtil.clearToken(Delegate.this.registrar.context(), str);
                    return null;
                }
            }, new BackgroundTaskRunner.Callback<Void>() {
                public void run(Future<Void> future) {
                    try {
                        result.success(future.get());
                    } catch (ExecutionException e) {
                        result.error(Delegate.ERROR_REASON_EXCEPTION, e.getCause().getMessage(), (Object) null);
                    } catch (InterruptedException e2) {
                        result.error(Delegate.ERROR_REASON_EXCEPTION, e2.getMessage(), (Object) null);
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        public void disconnect(MethodChannel.Result result) {
            checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_DISCONNECT, result);
            this.signInClient.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        Delegate.this.finishWithSuccess((Object) null);
                    } else {
                        Delegate.this.finishWithError("status", "Failed to disconnect.");
                    }
                }
            });
        }

        public void getTokens(final MethodChannel.Result result, final String str, final boolean z) {
            if (str == null) {
                result.error(ERROR_REASON_EXCEPTION, "Email is null", (Object) null);
                return;
            }
            this.backgroundTaskRunner.runInBackground(new Callable<String>() {
                public String call() {
                    Account account = new Account(str, "com.google");
                    return GoogleAuthUtil.getToken(Delegate.this.registrar.context(), account, "oauth2:" + Joiner.on(' ').join((Iterable<?>) Delegate.this.requestedScopes));
                }
            }, new BackgroundTaskRunner.Callback<String>() {
                public void run(Future<String> future) {
                    MethodChannel.Result result;
                    String str;
                    String message;
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put("accessToken", future.get());
                        result.success(hashMap);
                    } catch (ExecutionException e) {
                        if (!(e.getCause() instanceof UserRecoverableAuthException)) {
                            result = result;
                            str = Delegate.ERROR_REASON_EXCEPTION;
                            message = e.getCause().getMessage();
                        } else if (!z || Delegate.this.pendingOperation != null) {
                            result = result;
                            str = Delegate.ERROR_USER_RECOVERABLE_AUTH;
                            message = e.getLocalizedMessage();
                        } else {
                            Activity activity = Delegate.this.registrar.activity();
                            if (activity == null) {
                                result = result;
                                str = Delegate.ERROR_USER_RECOVERABLE_AUTH;
                                message = "Cannot recover auth because app is not in foreground. " + e.getLocalizedMessage();
                            } else {
                                Delegate.this.checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_GET_TOKENS, result, str);
                                activity.startActivityForResult(((UserRecoverableAuthException) e.getCause()).getIntent(), Delegate.REQUEST_CODE_RECOVER_AUTH);
                                return;
                            }
                        }
                        result.error(str, message, (Object) null);
                    } catch (InterruptedException e2) {
                        result.error(Delegate.ERROR_REASON_EXCEPTION, e2.getMessage(), (Object) null);
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        public void init(MethodChannel.Result result, String str, List<String> list, String str2) {
            GoogleSignInOptions.Builder builder;
            char c = 65535;
            try {
                int hashCode = str.hashCode();
                if (hashCode != 849126666) {
                    if (hashCode == 2056100820) {
                        if (str.equals(DEFAULT_SIGN_IN)) {
                            c = 1;
                        }
                    }
                } else if (str.equals(DEFAULT_GAMES_SIGN_IN)) {
                    c = 0;
                }
                switch (c) {
                    case 0:
                        builder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
                        break;
                    case 1:
                        builder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail();
                        break;
                    default:
                        throw new IllegalStateException("Unknown signInOption");
                }
                int identifier = this.registrar.context().getResources().getIdentifier("default_web_client_id", "string", this.registrar.context().getPackageName());
                if (identifier != 0) {
                    builder.requestIdToken(this.registrar.context().getString(identifier));
                }
                for (String scope : list) {
                    builder.requestScopes(new Scope(scope), new Scope[0]);
                }
                if (!Strings.isNullOrEmpty(str2)) {
                    builder.setHostedDomain(str2);
                }
                this.requestedScopes = list;
                this.signInClient = GoogleSignIn.getClient(this.registrar.context(), builder.build());
                result.success((Object) null);
            } catch (Exception e) {
                result.error(ERROR_REASON_EXCEPTION, e.getMessage(), (Object) null);
            }
        }

        public void isSignedIn(MethodChannel.Result result) {
            result.success(Boolean.valueOf(GoogleSignIn.getLastSignedInAccount(this.registrar.context()) != null));
        }

        public boolean onActivityResult(int i, int i2, Intent intent) {
            PendingOperation pendingOperation2 = this.pendingOperation;
            if (pendingOperation2 == null) {
                return false;
            }
            switch (i) {
                case REQUEST_CODE_SIGNIN /*53293*/:
                    if (intent != null) {
                        onSignInResult(GoogleSignIn.getSignedInAccountFromIntent(intent));
                    } else {
                        finishWithError(ERROR_REASON_SIGN_IN_FAILED, "Signin failed");
                    }
                    return true;
                case REQUEST_CODE_RECOVER_AUTH /*53294*/:
                    if (i2 == -1) {
                        this.pendingOperation = null;
                        getTokens(pendingOperation2.result, (String) this.pendingOperation.data, false);
                    } else {
                        finishWithError(ERROR_FAILURE_TO_RECOVER_AUTH, "Failed attempt to recover authentication");
                    }
                    return true;
                default:
                    return false;
            }
        }

        public void signIn(MethodChannel.Result result) {
            if (this.registrar.activity() != null) {
                checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_SIGN_IN, result);
                this.registrar.activity().startActivityForResult(this.signInClient.getSignInIntent(), REQUEST_CODE_SIGNIN);
                return;
            }
            throw new IllegalStateException("signIn needs a foreground activity");
        }

        public void signInSilently(MethodChannel.Result result) {
            checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_SIGN_IN_SILENTLY, result);
            Task<GoogleSignInAccount> silentSignIn = this.signInClient.silentSignIn();
            if (silentSignIn.isSuccessful()) {
                onSignInAccount(silentSignIn.getResult());
            } else {
                silentSignIn.addOnCompleteListener(new OnCompleteListener<GoogleSignInAccount>() {
                    public void onComplete(Task<GoogleSignInAccount> task) {
                        Delegate.this.onSignInResult(task);
                    }
                });
            }
        }

        public void signOut(MethodChannel.Result result) {
            checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_SIGN_OUT, result);
            this.signInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        Delegate.this.finishWithSuccess((Object) null);
                    } else {
                        Delegate.this.finishWithError("status", "Failed to signout.");
                    }
                }
            });
        }
    }

    public interface IDelegate {
        void clearAuthCache(MethodChannel.Result result, String str);

        void disconnect(MethodChannel.Result result);

        void getTokens(MethodChannel.Result result, String str, boolean z);

        void init(MethodChannel.Result result, String str, List<String> list, String str2);

        void isSignedIn(MethodChannel.Result result);

        void signIn(MethodChannel.Result result);

        void signInSilently(MethodChannel.Result result);

        void signOut(MethodChannel.Result result);
    }

    private GoogleSignInPlugin(PluginRegistry.Registrar registrar) {
        this.delegate = new Delegate(registrar);
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodChannel(registrar.messenger(), CHANNEL_NAME).setMethodCallHandler(new GoogleSignInPlugin(registrar));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r4, io.flutter.plugin.common.MethodChannel.Result r5) {
        /*
            r3 = this;
            java.lang.String r0 = r4.method
            int r1 = r0.hashCode()
            switch(r1) {
                case -902468670: goto L_0x0050;
                case -638267772: goto L_0x0046;
                case -481441621: goto L_0x003c;
                case 3237136: goto L_0x0032;
                case 24140525: goto L_0x0028;
                case 530405532: goto L_0x001e;
                case 827828368: goto L_0x0014;
                case 2088248401: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x005a
        L_0x000a:
            java.lang.String r1 = "signOut"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005a
            r0 = 4
            goto L_0x005b
        L_0x0014:
            java.lang.String r1 = "getTokens"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005a
            r0 = 3
            goto L_0x005b
        L_0x001e:
            java.lang.String r1 = "disconnect"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005a
            r0 = 6
            goto L_0x005b
        L_0x0028:
            java.lang.String r1 = "clearAuthCache"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005a
            r0 = 5
            goto L_0x005b
        L_0x0032:
            java.lang.String r1 = "init"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005a
            r0 = 0
            goto L_0x005b
        L_0x003c:
            java.lang.String r1 = "isSignedIn"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005a
            r0 = 7
            goto L_0x005b
        L_0x0046:
            java.lang.String r1 = "signInSilently"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005a
            r0 = 1
            goto L_0x005b
        L_0x0050:
            java.lang.String r1 = "signIn"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005a
            r0 = 2
            goto L_0x005b
        L_0x005a:
            r0 = -1
        L_0x005b:
            switch(r0) {
                case 0: goto L_0x00a8;
                case 1: goto L_0x00a2;
                case 2: goto L_0x009c;
                case 3: goto L_0x0082;
                case 4: goto L_0x007c;
                case 5: goto L_0x006e;
                case 6: goto L_0x0068;
                case 7: goto L_0x0062;
                default: goto L_0x005e;
            }
        L_0x005e:
            r5.notImplemented()
            goto L_0x00c5
        L_0x0062:
            io.flutter.plugins.googlesignin.GoogleSignInPlugin$IDelegate r4 = r3.delegate
            r4.isSignedIn(r5)
            goto L_0x00c5
        L_0x0068:
            io.flutter.plugins.googlesignin.GoogleSignInPlugin$IDelegate r4 = r3.delegate
            r4.disconnect(r5)
            goto L_0x00c5
        L_0x006e:
            java.lang.String r0 = "token"
            java.lang.Object r4 = r4.argument(r0)
            java.lang.String r4 = (java.lang.String) r4
            io.flutter.plugins.googlesignin.GoogleSignInPlugin$IDelegate r0 = r3.delegate
            r0.clearAuthCache(r5, r4)
            goto L_0x00c5
        L_0x007c:
            io.flutter.plugins.googlesignin.GoogleSignInPlugin$IDelegate r4 = r3.delegate
            r4.signOut(r5)
            goto L_0x00c5
        L_0x0082:
            java.lang.String r0 = "email"
            java.lang.Object r0 = r4.argument(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "shouldRecoverAuth"
            java.lang.Object r4 = r4.argument(r1)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            io.flutter.plugins.googlesignin.GoogleSignInPlugin$IDelegate r1 = r3.delegate
            r1.getTokens(r5, r0, r4)
            goto L_0x00c5
        L_0x009c:
            io.flutter.plugins.googlesignin.GoogleSignInPlugin$IDelegate r4 = r3.delegate
            r4.signIn(r5)
            goto L_0x00c5
        L_0x00a2:
            io.flutter.plugins.googlesignin.GoogleSignInPlugin$IDelegate r4 = r3.delegate
            r4.signInSilently(r5)
            goto L_0x00c5
        L_0x00a8:
            java.lang.String r0 = "signInOption"
            java.lang.Object r0 = r4.argument(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "scopes"
            java.lang.Object r1 = r4.argument(r1)
            java.util.List r1 = (java.util.List) r1
            java.lang.String r2 = "hostedDomain"
            java.lang.Object r4 = r4.argument(r2)
            java.lang.String r4 = (java.lang.String) r4
            io.flutter.plugins.googlesignin.GoogleSignInPlugin$IDelegate r2 = r3.delegate
            r2.init(r5, r0, r1, r4)
        L_0x00c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.googlesignin.GoogleSignInPlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
