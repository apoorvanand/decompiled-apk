package com.razorpay;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

final class f$_G$ extends O_$B_ {
    private static String D$_X_ = "3.0.5";
    static boolean G__G_ = true;
    static int Q_$2$ = 15;
    static String R$$r_ = "checkout";
    static String a_$P$ = "1.5.2";
    private static String b__J_ = "2HujvzmUo2nuRLLqhIHIV4sCEmRw9FIc";
    private static f$_G$ d__1_;
    private ArrayList<String> B$$W$ = new ArrayList<>();
    private boolean E$_6$;
    private boolean E$_j$;
    private String J$_0_;
    private boolean L__R$;
    private String O_$B_;
    private boolean Y$_o$;
    private boolean c__C_;
    private int f$_G$;
    private String g__v_;
    private boolean l_$w$;
    private String l__d$;
    private Map<String, String> r$_Y_ = new HashMap();

    private f$_G$() {
    }

    static void a_$P$(Context context, String str) {
        if (d__1_ == null) {
            f$_G$ f__g_ = new f$_G$();
            d__1_ = f__g_;
            J$$A_.d__1_(f__g_);
        }
        if (d__1_.G__G_()) {
            HashMap hashMap = new HashMap();
            hashMap.put("AuthKey", b__J_);
            hashMap.put("Content-type", "application/json");
            String str2 = null;
            String string = Q__v$.G__G_(context).getString("rzp_config_version", (String) null);
            if (string == null) {
                string = D$_X_;
            }
            hashMap.put("CurrentSettingVersion", string);
            if (d__1_ == null) {
                f$_G$ f__g_2 = new f$_G$();
                d__1_ = f__g_2;
                J$$A_.d__1_(f__g_2);
            }
            Uri.Builder buildUpon = Uri.parse(d__1_.Q_$2$()).buildUpon();
            StringBuilder sb = new StringBuilder("android_");
            sb.append(R$$r_);
            Uri.Builder appendQueryParameter = buildUpon.appendQueryParameter("tenant", sb.toString()).appendQueryParameter("sdk_version", a_$P$).appendQueryParameter("sdk_type", R$$r_).appendQueryParameter("magic_enabled", String.valueOf(G__G_)).appendQueryParameter("sdk_version_code", String.valueOf(Q_$2$)).appendQueryParameter("app_version", BuildConfig.VERSION_NAME);
            String string2 = Q__v$.G__G_(context).getString("rzp_config_version", (String) null);
            if (string2 == null) {
                string2 = D$_X_;
            }
            Matcher matcher = Pattern.compile("^(\\d+\\.)(\\d+\\.)(\\d+)$").matcher(string2);
            if (matcher.find()) {
                str2 = matcher.replaceFirst("$1$2*");
            }
            Uri.Builder appendQueryParameter2 = appendQueryParameter.appendQueryParameter(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, str2);
            appendQueryParameter2.appendQueryParameter("merchant_key_id", str).appendQueryParameter("android_version", Build.VERSION.RELEASE).appendQueryParameter("device_id", BaseUtils.getDeviceId(context)).appendQueryParameter("device_manufacturer", Build.MANUFACTURER).appendQueryParameter("device_model", Build.MODEL).appendQueryParameter("network_type", BaseUtils.getDataNetworkType(context).d__1_()).appendQueryParameter("cellular_network_type", BaseUtils.getCellularNetworkType(context)).appendQueryParameter("cellular_network_provider", BaseUtils.getCellularNetworkProviderName(context)).appendQueryParameter("app_package_name", context.getApplicationContext().getPackageName()).appendQueryParameter("build_type", BaseUtils.getAppBuildType(context)).appendQueryParameter("magic_version_code", String.valueOf(B_$q$.d__1_.intValue())).appendQueryParameter("rzpassist_version_code", String.valueOf(B_$q$.R$$r_.intValue())).appendQueryParameter("webview_user_agent", BaseUtils.getWebViewUserAgent(context).toString());
            b_$A$.a_$P$(appendQueryParameter2.build().toString(), hashMap, new Callback(context) {
                private /* synthetic */ Context Q_$2$;

                public final void run(
/*
Method generation error in method: com.razorpay.O_$B_.2.run(com.razorpay.t_$J_):void, dex: classes2.dex
                jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.razorpay.O_$B_.2.run(com.razorpay.t_$J_):void, class status: UNLOADED
                	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1257)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1257)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                
*/
            });
        }
    }

    public static f$_G$ f$_G$() {
        if (d__1_ == null) {
            f$_G$ f__g_ = new f$_G$();
            d__1_ = f__g_;
            J$$A_.d__1_(f__g_);
        }
        return d__1_;
    }

    public final int B_$q$() {
        return this.f$_G$;
    }

    public final String C__D$() {
        return this.J$_0_;
    }

    public final void G__G_(JSONObject jSONObject) {
        try {
            this.B$$W$ = BaseUtils.jsonStringArrayToArrayList((JSONArray) BaseUtils.getJsonValue("checkout.append_keys", jSONObject, (Object) new JSONArray()));
            JSONObject jSONObject2 = (JSONObject) BaseUtils.getJsonValue("checkout.url_config", jSONObject, (Object) new JSONObject());
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.r$_Y_.put(next, jSONObject2.getString(next));
            }
            this.E$_j$ = ((Boolean) BaseUtils.getJsonValue("card_saving.broadcast_receiver_flow", jSONObject, (Object) Boolean.FALSE)).booleanValue();
            this.c__C_ = ((Boolean) BaseUtils.getJsonValue("card_saving.shared_preferences_flow", jSONObject, (Object) Boolean.FALSE)).booleanValue();
            this.E$_6$ = ((Boolean) BaseUtils.getJsonValue("card_saving.local", jSONObject, (Object) Boolean.FALSE)).booleanValue();
            this.O_$B_ = (String) BaseUtils.getJsonValue("native_loader.color", jSONObject, (Object) "");
            this.l_$w$ = ((Boolean) BaseUtils.getJsonValue("native_loader.enable", jSONObject, (Object) "")).booleanValue();
            this.Y$_o$ = ((Boolean) BaseUtils.getJsonValue("retry.enabled", jSONObject, (Object) Boolean.TRUE)).booleanValue();
            this.f$_G$ = ((Integer) BaseUtils.getJsonValue("retry.max_count", jSONObject, (Object) -1)).intValue();
            this.J$_0_ = (String) BaseUtils.getJsonValue("back_button.alert_message", jSONObject, (Object) "");
            this.L__R$ = ((Boolean) BaseUtils.getJsonValue("back_button.enable", jSONObject, (Object) Boolean.FALSE)).booleanValue();
            this.l__d$ = (String) BaseUtils.getJsonValue("back_button.positive_text", jSONObject, (Object) "");
            this.g__v_ = (String) BaseUtils.getJsonValue("back_button.negative_text", jSONObject, (Object) "");
        } catch (Exception e) {
            e.getMessage();
        }
        super.G__G_(jSONObject);
    }

    public final boolean H$_a_() {
        return this.E$_j$;
    }

    public final String I$_e_() {
        return this.g__v_;
    }

    public final boolean J$$A_() {
        return this.l_$w$;
    }

    public final boolean K$$z$() {
        return this.Y$_o$;
    }

    public final boolean L$$C_() {
        return this.c__C_;
    }

    public final boolean O__Y_() {
        return this.E$_6$;
    }

    public final boolean Q$$U_() {
        return this.L__R$;
    }

    public final ArrayList<String> T__j$() {
        return this.B$$W$;
    }

    public final Map<String, String> Y_$B$() {
        return this.r$_Y_;
    }

    public final String h__y_() {
        return this.O_$B_;
    }

    public final String r_$Z$() {
        return this.l__d$;
    }
}
