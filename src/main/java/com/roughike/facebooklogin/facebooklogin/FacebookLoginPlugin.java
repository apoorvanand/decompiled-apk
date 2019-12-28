package com.roughike.facebooklogin.facebooklogin;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.util.Collection;
import java.util.List;

public class FacebookLoginPlugin implements MethodChannel.MethodCallHandler {
    private static final String ARG_LOGIN_BEHAVIOR = "behavior";
    private static final String ARG_PERMISSIONS = "permissions";
    private static final String CHANNEL_NAME = "com.roughike/flutter_facebook_login";
    private static final String ERROR_UNKNOWN_LOGIN_BEHAVIOR = "unknown_login_behavior";
    private static final String LOGIN_BEHAVIOR_NATIVE_ONLY = "nativeOnly";
    private static final String LOGIN_BEHAVIOR_NATIVE_WITH_FALLBACK = "nativeWithFallback";
    private static final String LOGIN_BEHAVIOR_WEB_ONLY = "webOnly";
    private static final String LOGIN_BEHAVIOR_WEB_VIEW_ONLY = "webViewOnly";
    private static final String METHOD_GET_CURRENT_ACCESS_TOKEN = "getCurrentAccessToken";
    private static final String METHOD_LOG_IN_WITH_PUBLISH_PERMISSIONS = "loginWithPublishPermissions";
    private static final String METHOD_LOG_IN_WITH_READ_PERMISSIONS = "loginWithReadPermissions";
    private static final String METHOD_LOG_OUT = "logOut";
    private final FacebookSignInDelegate delegate;

    public static final class FacebookSignInDelegate {
        private final CallbackManager callbackManager = CallbackManager.Factory.create();
        private final LoginManager loginManager = LoginManager.getInstance();
        private final PluginRegistry.Registrar registrar;
        private final FacebookLoginResultDelegate resultDelegate = new FacebookLoginResultDelegate(this.callbackManager);

        public FacebookSignInDelegate(PluginRegistry.Registrar registrar2) {
            this.registrar = registrar2;
            this.loginManager.registerCallback(this.callbackManager, this.resultDelegate);
            registrar2.addActivityResultListener(this.resultDelegate);
        }

        public void getCurrentAccessToken(MethodChannel.Result result) {
            result.success(FacebookLoginResults.a(AccessToken.getCurrentAccessToken()));
        }

        public void logInWithPublishPermissions(LoginBehavior loginBehavior, List<String> list, MethodChannel.Result result) {
            this.resultDelegate.a(FacebookLoginPlugin.METHOD_LOG_IN_WITH_PUBLISH_PERMISSIONS, result);
            this.loginManager.setLoginBehavior(loginBehavior);
            this.loginManager.logInWithPublishPermissions(this.registrar.activity(), (Collection<String>) list);
        }

        public void logInWithReadPermissions(LoginBehavior loginBehavior, List<String> list, MethodChannel.Result result) {
            this.resultDelegate.a(FacebookLoginPlugin.METHOD_LOG_IN_WITH_READ_PERMISSIONS, result);
            this.loginManager.setLoginBehavior(loginBehavior);
            this.loginManager.logInWithReadPermissions(this.registrar.activity(), (Collection<String>) list);
        }

        public void logOut(MethodChannel.Result result) {
            this.loginManager.logOut();
            result.success((Object) null);
        }
    }

    private FacebookLoginPlugin(PluginRegistry.Registrar registrar) {
        this.delegate = new FacebookSignInDelegate(registrar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.login.LoginBehavior loginBehaviorFromString(java.lang.String r4, io.flutter.plugin.common.MethodChannel.Result r5) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -1533374619(0xffffffffa49a8f65, float:-6.7029774E-17)
            if (r0 == r1) goto L_0x0037
            r1 = -1190920093(0xffffffffb9040063, float:-1.2588645E-4)
            if (r0 == r1) goto L_0x002d
            r1 = 462081919(0x1b8acf7f, float:2.2964276E-22)
            if (r0 == r1) goto L_0x0023
            r1 = 1223267616(0x48e99520, float:478377.0)
            if (r0 == r1) goto L_0x0019
            goto L_0x0041
        L_0x0019:
            java.lang.String r0 = "webOnly"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0041
            r0 = 2
            goto L_0x0042
        L_0x0023:
            java.lang.String r0 = "nativeWithFallback"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0041
            r0 = 0
            goto L_0x0042
        L_0x002d:
            java.lang.String r0 = "nativeOnly"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0041
            r0 = 1
            goto L_0x0042
        L_0x0037:
            java.lang.String r0 = "webViewOnly"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0041
            r0 = 3
            goto L_0x0042
        L_0x0041:
            r0 = -1
        L_0x0042:
            switch(r0) {
                case 0: goto L_0x0066;
                case 1: goto L_0x0063;
                case 2: goto L_0x0060;
                case 3: goto L_0x005d;
                default: goto L_0x0045;
            }
        L_0x0045:
            java.lang.String r0 = "unknown_login_behavior"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "setLoginBehavior called with unknown login behavior: "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r1 = 0
            r5.error(r0, r4, r1)
            return r1
        L_0x005d:
            com.facebook.login.LoginBehavior r4 = com.facebook.login.LoginBehavior.WEB_VIEW_ONLY
            return r4
        L_0x0060:
            com.facebook.login.LoginBehavior r4 = com.facebook.login.LoginBehavior.WEB_ONLY
            return r4
        L_0x0063:
            com.facebook.login.LoginBehavior r4 = com.facebook.login.LoginBehavior.NATIVE_ONLY
            return r4
        L_0x0066:
            com.facebook.login.LoginBehavior r4 = com.facebook.login.LoginBehavior.NATIVE_WITH_FALLBACK
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.roughike.facebooklogin.facebooklogin.FacebookLoginPlugin.loginBehaviorFromString(java.lang.String, io.flutter.plugin.common.MethodChannel$Result):com.facebook.login.LoginBehavior");
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodChannel(registrar.messenger(), CHANNEL_NAME).setMethodCallHandler(new FacebookLoginPlugin(registrar));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r4, io.flutter.plugin.common.MethodChannel.Result r5) {
        /*
            r3 = this;
            java.lang.String r0 = r4.method
            int r1 = r0.hashCode()
            r2 = -1499256398(0xffffffffa6a329b2, float:-1.1321699E-15)
            if (r1 == r2) goto L_0x0039
            r2 = -1097360022(0xffffffffbe979d6a, float:-0.29612285)
            if (r1 == r2) goto L_0x002f
            r2 = -349647324(0xffffffffeb28ce24, float:-2.0407301E26)
            if (r1 == r2) goto L_0x0025
            r2 = -268070593(0xfffffffff005913f, float:-1.653484E29)
            if (r1 == r2) goto L_0x001b
            goto L_0x0043
        L_0x001b:
            java.lang.String r1 = "loginWithReadPermissions"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = 0
            goto L_0x0044
        L_0x0025:
            java.lang.String r1 = "loginWithPublishPermissions"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = 1
            goto L_0x0044
        L_0x002f:
            java.lang.String r1 = "logOut"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = 2
            goto L_0x0044
        L_0x0039:
            java.lang.String r1 = "getCurrentAccessToken"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = 3
            goto L_0x0044
        L_0x0043:
            r0 = -1
        L_0x0044:
            switch(r0) {
                case 0: goto L_0x0071;
                case 1: goto L_0x0057;
                case 2: goto L_0x0051;
                case 3: goto L_0x004b;
                default: goto L_0x0047;
            }
        L_0x0047:
            r5.notImplemented()
            goto L_0x008a
        L_0x004b:
            com.roughike.facebooklogin.facebooklogin.FacebookLoginPlugin$FacebookSignInDelegate r4 = r3.delegate
            r4.getCurrentAccessToken(r5)
            goto L_0x008a
        L_0x0051:
            com.roughike.facebooklogin.facebooklogin.FacebookLoginPlugin$FacebookSignInDelegate r4 = r3.delegate
            r4.logOut(r5)
            goto L_0x008a
        L_0x0057:
            java.lang.String r0 = "behavior"
            java.lang.Object r0 = r4.argument(r0)
            java.lang.String r0 = (java.lang.String) r0
            com.facebook.login.LoginBehavior r0 = r3.loginBehaviorFromString(r0, r5)
            java.lang.String r1 = "permissions"
            java.lang.Object r4 = r4.argument(r1)
            java.util.List r4 = (java.util.List) r4
            com.roughike.facebooklogin.facebooklogin.FacebookLoginPlugin$FacebookSignInDelegate r1 = r3.delegate
            r1.logInWithPublishPermissions(r0, r4, r5)
            goto L_0x008a
        L_0x0071:
            java.lang.String r0 = "behavior"
            java.lang.Object r0 = r4.argument(r0)
            java.lang.String r0 = (java.lang.String) r0
            com.facebook.login.LoginBehavior r0 = r3.loginBehaviorFromString(r0, r5)
            java.lang.String r1 = "permissions"
            java.lang.Object r4 = r4.argument(r1)
            java.util.List r4 = (java.util.List) r4
            com.roughike.facebooklogin.facebooklogin.FacebookLoginPlugin$FacebookSignInDelegate r1 = r3.delegate
            r1.logInWithReadPermissions(r0, r4, r5)
        L_0x008a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.roughike.facebooklogin.facebooklogin.FacebookLoginPlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
