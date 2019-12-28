package io.flutter.embedding.engine.systemchannels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.Log;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.MethodChannel;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class TextInputChannel {
    private static final String TAG = "TextInputChannel";
    @NonNull
    public final MethodChannel channel;
    private final MethodChannel.MethodCallHandler parsingMethodHandler = new MethodChannel.MethodCallHandler() {
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e7, code lost:
            r7.success((java.lang.Object) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onMethodCall(@androidx.annotation.NonNull io.flutter.plugin.common.MethodCall r6, @androidx.annotation.NonNull io.flutter.plugin.common.MethodChannel.Result r7) {
            /*
                r5 = this;
                io.flutter.embedding.engine.systemchannels.TextInputChannel r0 = io.flutter.embedding.engine.systemchannels.TextInputChannel.this
                io.flutter.embedding.engine.systemchannels.TextInputChannel$TextInputMethodHandler r0 = r0.textInputMethodHandler
                if (r0 != 0) goto L_0x0009
                return
            L_0x0009:
                java.lang.String r0 = r6.method
                java.lang.Object r6 = r6.arguments
                java.lang.String r1 = "TextInputChannel"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Received '"
                r2.append(r3)
                r2.append(r0)
                java.lang.String r3 = "' message."
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                io.flutter.Log.v(r1, r2)
                r1 = -1
                int r2 = r0.hashCode()
                r3 = 0
                r4 = 1
                switch(r2) {
                    case -1779068172: goto L_0x0065;
                    case -1015421462: goto L_0x005b;
                    case -37561188: goto L_0x0051;
                    case 270476819: goto L_0x0047;
                    case 270803918: goto L_0x003d;
                    case 1904427655: goto L_0x0033;
                    default: goto L_0x0032;
                }
            L_0x0032:
                goto L_0x006e
            L_0x0033:
                java.lang.String r2 = "TextInput.clearClient"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x006e
                r1 = 5
                goto L_0x006e
            L_0x003d:
                java.lang.String r2 = "TextInput.show"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x006e
                r1 = 0
                goto L_0x006e
            L_0x0047:
                java.lang.String r2 = "TextInput.hide"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x006e
                r1 = 1
                goto L_0x006e
            L_0x0051:
                java.lang.String r2 = "TextInput.setClient"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x006e
                r1 = 2
                goto L_0x006e
            L_0x005b:
                java.lang.String r2 = "TextInput.setEditingState"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x006e
                r1 = 4
                goto L_0x006e
            L_0x0065:
                java.lang.String r2 = "TextInput.setPlatformViewClient"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x006e
                r1 = 3
            L_0x006e:
                r0 = 0
                switch(r1) {
                    case 0: goto L_0x00de;
                    case 1: goto L_0x00d4;
                    case 2: goto L_0x00ac;
                    case 3: goto L_0x009c;
                    case 4: goto L_0x0081;
                    case 5: goto L_0x0077;
                    default: goto L_0x0072;
                }
            L_0x0072:
                r7.notImplemented()
                goto L_0x00ea
            L_0x0077:
                io.flutter.embedding.engine.systemchannels.TextInputChannel r6 = io.flutter.embedding.engine.systemchannels.TextInputChannel.this
                io.flutter.embedding.engine.systemchannels.TextInputChannel$TextInputMethodHandler r6 = r6.textInputMethodHandler
                r6.clearClient()
                goto L_0x00e7
            L_0x0081:
                org.json.JSONObject r6 = (org.json.JSONObject) r6     // Catch:{ JSONException -> 0x0094 }
                io.flutter.embedding.engine.systemchannels.TextInputChannel r1 = io.flutter.embedding.engine.systemchannels.TextInputChannel.this     // Catch:{ JSONException -> 0x0094 }
                io.flutter.embedding.engine.systemchannels.TextInputChannel$TextInputMethodHandler r1 = r1.textInputMethodHandler     // Catch:{ JSONException -> 0x0094 }
                io.flutter.embedding.engine.systemchannels.TextInputChannel$TextEditState r6 = io.flutter.embedding.engine.systemchannels.TextInputChannel.TextEditState.fromJson(r6)     // Catch:{ JSONException -> 0x0094 }
                r1.setEditingState(r6)     // Catch:{ JSONException -> 0x0094 }
                r7.success(r0)     // Catch:{ JSONException -> 0x0094 }
                goto L_0x00ea
            L_0x0094:
                r6 = move-exception
                java.lang.String r1 = "error"
                java.lang.String r6 = r6.getMessage()
                goto L_0x00d0
            L_0x009c:
                java.lang.Integer r6 = (java.lang.Integer) r6
                int r6 = r6.intValue()
                io.flutter.embedding.engine.systemchannels.TextInputChannel r7 = io.flutter.embedding.engine.systemchannels.TextInputChannel.this
                io.flutter.embedding.engine.systemchannels.TextInputChannel$TextInputMethodHandler r7 = r7.textInputMethodHandler
                r7.setPlatformViewClient(r6)
                goto L_0x00ea
            L_0x00ac:
                org.json.JSONArray r6 = (org.json.JSONArray) r6     // Catch:{ JSONException -> 0x00c9, NoSuchFieldException -> 0x00c7 }
                int r1 = r6.getInt(r3)     // Catch:{ JSONException -> 0x00c9, NoSuchFieldException -> 0x00c7 }
                org.json.JSONObject r6 = r6.getJSONObject(r4)     // Catch:{ JSONException -> 0x00c9, NoSuchFieldException -> 0x00c7 }
                io.flutter.embedding.engine.systemchannels.TextInputChannel r2 = io.flutter.embedding.engine.systemchannels.TextInputChannel.this     // Catch:{ JSONException -> 0x00c9, NoSuchFieldException -> 0x00c7 }
                io.flutter.embedding.engine.systemchannels.TextInputChannel$TextInputMethodHandler r2 = r2.textInputMethodHandler     // Catch:{ JSONException -> 0x00c9, NoSuchFieldException -> 0x00c7 }
                io.flutter.embedding.engine.systemchannels.TextInputChannel$Configuration r6 = io.flutter.embedding.engine.systemchannels.TextInputChannel.Configuration.fromJson(r6)     // Catch:{ JSONException -> 0x00c9, NoSuchFieldException -> 0x00c7 }
                r2.setClient(r1, r6)     // Catch:{ JSONException -> 0x00c9, NoSuchFieldException -> 0x00c7 }
                r7.success(r0)     // Catch:{ JSONException -> 0x00c9, NoSuchFieldException -> 0x00c7 }
                goto L_0x00ea
            L_0x00c7:
                r6 = move-exception
                goto L_0x00ca
            L_0x00c9:
                r6 = move-exception
            L_0x00ca:
                java.lang.String r1 = "error"
                java.lang.String r6 = r6.getMessage()
            L_0x00d0:
                r7.error(r1, r6, r0)
                goto L_0x00ea
            L_0x00d4:
                io.flutter.embedding.engine.systemchannels.TextInputChannel r6 = io.flutter.embedding.engine.systemchannels.TextInputChannel.this
                io.flutter.embedding.engine.systemchannels.TextInputChannel$TextInputMethodHandler r6 = r6.textInputMethodHandler
                r6.hide()
                goto L_0x00e7
            L_0x00de:
                io.flutter.embedding.engine.systemchannels.TextInputChannel r6 = io.flutter.embedding.engine.systemchannels.TextInputChannel.this
                io.flutter.embedding.engine.systemchannels.TextInputChannel$TextInputMethodHandler r6 = r6.textInputMethodHandler
                r6.show()
            L_0x00e7:
                r7.success(r0)
            L_0x00ea:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.engine.systemchannels.TextInputChannel.AnonymousClass1.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public TextInputMethodHandler textInputMethodHandler;

    public static class Configuration {
        @Nullable
        public final String actionLabel;
        public final boolean autocorrect;
        @Nullable
        public final Integer inputAction;
        @NonNull
        public final InputType inputType;
        public final boolean obscureText;
        @NonNull
        public final TextCapitalization textCapitalization;

        public Configuration(boolean z, boolean z2, @NonNull TextCapitalization textCapitalization2, @NonNull InputType inputType2, @Nullable Integer num, @Nullable String str) {
            this.obscureText = z;
            this.autocorrect = z2;
            this.textCapitalization = textCapitalization2;
            this.inputType = inputType2;
            this.inputAction = num;
            this.actionLabel = str;
        }

        public static Configuration fromJson(@NonNull JSONObject jSONObject) {
            String string = jSONObject.getString("inputAction");
            if (string != null) {
                return new Configuration(jSONObject.optBoolean("obscureText"), jSONObject.optBoolean("autocorrect", true), TextCapitalization.fromValue(jSONObject.getString("textCapitalization")), InputType.fromJson(jSONObject.getJSONObject("inputType")), inputActionFromTextInputAction(string), jSONObject.isNull("actionLabel") ? null : jSONObject.getString("actionLabel"));
            }
            throw new JSONException("Configuration JSON missing 'inputAction' property.");
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        @androidx.annotation.NonNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static java.lang.Integer inputActionFromTextInputAction(@androidx.annotation.NonNull java.lang.String r9) {
            /*
                int r0 = r9.hashCode()
                r1 = 7
                r2 = 5
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 6
                r7 = 1
                r8 = 0
                switch(r0) {
                    case -810971940: goto L_0x0061;
                    case -737377923: goto L_0x0057;
                    case -737089298: goto L_0x004d;
                    case -737080013: goto L_0x0043;
                    case -736940669: goto L_0x0039;
                    case 469250275: goto L_0x002f;
                    case 1241689507: goto L_0x0025;
                    case 1539450297: goto L_0x001b;
                    case 2110497650: goto L_0x0010;
                    default: goto L_0x000f;
                }
            L_0x000f:
                goto L_0x006b
            L_0x0010:
                java.lang.String r0 = "TextInputAction.previous"
                boolean r9 = r9.equals(r0)
                if (r9 == 0) goto L_0x006b
                r9 = 8
                goto L_0x006c
            L_0x001b:
                java.lang.String r0 = "TextInputAction.newline"
                boolean r9 = r9.equals(r0)
                if (r9 == 0) goto L_0x006b
                r9 = 0
                goto L_0x006c
            L_0x0025:
                java.lang.String r0 = "TextInputAction.go"
                boolean r9 = r9.equals(r0)
                if (r9 == 0) goto L_0x006b
                r9 = 4
                goto L_0x006c
            L_0x002f:
                java.lang.String r0 = "TextInputAction.search"
                boolean r9 = r9.equals(r0)
                if (r9 == 0) goto L_0x006b
                r9 = 5
                goto L_0x006c
            L_0x0039:
                java.lang.String r0 = "TextInputAction.send"
                boolean r9 = r9.equals(r0)
                if (r9 == 0) goto L_0x006b
                r9 = 6
                goto L_0x006c
            L_0x0043:
                java.lang.String r0 = "TextInputAction.none"
                boolean r9 = r9.equals(r0)
                if (r9 == 0) goto L_0x006b
                r9 = 1
                goto L_0x006c
            L_0x004d:
                java.lang.String r0 = "TextInputAction.next"
                boolean r9 = r9.equals(r0)
                if (r9 == 0) goto L_0x006b
                r9 = 7
                goto L_0x006c
            L_0x0057:
                java.lang.String r0 = "TextInputAction.done"
                boolean r9 = r9.equals(r0)
                if (r9 == 0) goto L_0x006b
                r9 = 3
                goto L_0x006c
            L_0x0061:
                java.lang.String r0 = "TextInputAction.unspecified"
                boolean r9 = r9.equals(r0)
                if (r9 == 0) goto L_0x006b
                r9 = 2
                goto L_0x006c
            L_0x006b:
                r9 = -1
            L_0x006c:
                switch(r9) {
                    case 0: goto L_0x009c;
                    case 1: goto L_0x0097;
                    case 2: goto L_0x0092;
                    case 3: goto L_0x008d;
                    case 4: goto L_0x0088;
                    case 5: goto L_0x0083;
                    case 6: goto L_0x007e;
                    case 7: goto L_0x0079;
                    case 8: goto L_0x0074;
                    default: goto L_0x006f;
                }
            L_0x006f:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r8)
                return r9
            L_0x0074:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r1)
                return r9
            L_0x0079:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r2)
                return r9
            L_0x007e:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r3)
                return r9
            L_0x0083:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r4)
                return r9
            L_0x0088:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r5)
                return r9
            L_0x008d:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r6)
                return r9
            L_0x0092:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r8)
                return r9
            L_0x0097:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r7)
                return r9
            L_0x009c:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r7)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.engine.systemchannels.TextInputChannel.Configuration.inputActionFromTextInputAction(java.lang.String):java.lang.Integer");
        }
    }

    public static class InputType {
        public final boolean isDecimal;
        public final boolean isSigned;
        @NonNull
        public final TextInputType type;

        public InputType(@NonNull TextInputType textInputType, boolean z, boolean z2) {
            this.type = textInputType;
            this.isSigned = z;
            this.isDecimal = z2;
        }

        @NonNull
        public static InputType fromJson(@NonNull JSONObject jSONObject) {
            return new InputType(TextInputType.fromValue(jSONObject.getString("name")), jSONObject.optBoolean("signed", false), jSONObject.optBoolean("decimal", false));
        }
    }

    public enum TextCapitalization {
        CHARACTERS("TextCapitalization.characters"),
        WORDS("TextCapitalization.words"),
        SENTENCES("TextCapitalization.sentences"),
        NONE("TextCapitalization.none");
        
        @NonNull
        private final String encodedName;

        private TextCapitalization(String str) {
            this.encodedName = str;
        }

        static TextCapitalization fromValue(@NonNull String str) {
            for (TextCapitalization textCapitalization : values()) {
                if (textCapitalization.encodedName.equals(str)) {
                    return textCapitalization;
                }
            }
            throw new NoSuchFieldException("No such TextCapitalization: " + str);
        }
    }

    public static class TextEditState {
        public final int selectionEnd;
        public final int selectionStart;
        @NonNull
        public final String text;

        public TextEditState(@NonNull String str, int i, int i2) {
            this.text = str;
            this.selectionStart = i;
            this.selectionEnd = i2;
        }

        public static TextEditState fromJson(@NonNull JSONObject jSONObject) {
            return new TextEditState(jSONObject.getString("text"), jSONObject.getInt("selectionBase"), jSONObject.getInt("selectionExtent"));
        }
    }

    public interface TextInputMethodHandler {
        void clearClient();

        void hide();

        void setClient(int i, @NonNull Configuration configuration);

        void setEditingState(@NonNull TextEditState textEditState);

        void setPlatformViewClient(int i);

        void show();
    }

    public enum TextInputType {
        TEXT("TextInputType.text"),
        DATETIME("TextInputType.datetime"),
        NUMBER("TextInputType.number"),
        PHONE("TextInputType.phone"),
        MULTILINE("TextInputType.multiline"),
        EMAIL_ADDRESS("TextInputType.emailAddress"),
        URL("TextInputType.url"),
        VISIBLE_PASSWORD("TextInputType.visiblePassword");
        
        @NonNull
        private final String encodedName;

        private TextInputType(String str) {
            this.encodedName = str;
        }

        static TextInputType fromValue(@NonNull String str) {
            for (TextInputType textInputType : values()) {
                if (textInputType.encodedName.equals(str)) {
                    return textInputType;
                }
            }
            throw new NoSuchFieldException("No such TextInputType: " + str);
        }
    }

    public TextInputChannel(@NonNull DartExecutor dartExecutor) {
        this.channel = new MethodChannel(dartExecutor, "flutter/textinput", JSONMethodCodec.INSTANCE);
        this.channel.setMethodCallHandler(this.parsingMethodHandler);
    }

    public void done(int i) {
        Log.v(TAG, "Sending 'done' message.");
        this.channel.invokeMethod("TextInputClient.performAction", Arrays.asList(new Serializable[]{Integer.valueOf(i), "TextInputAction.done"}));
    }

    public void go(int i) {
        Log.v(TAG, "Sending 'go' message.");
        this.channel.invokeMethod("TextInputClient.performAction", Arrays.asList(new Serializable[]{Integer.valueOf(i), "TextInputAction.go"}));
    }

    public void newline(int i) {
        Log.v(TAG, "Sending 'newline' message.");
        this.channel.invokeMethod("TextInputClient.performAction", Arrays.asList(new Serializable[]{Integer.valueOf(i), "TextInputAction.newline"}));
    }

    public void next(int i) {
        Log.v(TAG, "Sending 'next' message.");
        this.channel.invokeMethod("TextInputClient.performAction", Arrays.asList(new Serializable[]{Integer.valueOf(i), "TextInputAction.next"}));
    }

    public void previous(int i) {
        Log.v(TAG, "Sending 'previous' message.");
        this.channel.invokeMethod("TextInputClient.performAction", Arrays.asList(new Serializable[]{Integer.valueOf(i), "TextInputAction.previous"}));
    }

    public void search(int i) {
        Log.v(TAG, "Sending 'search' message.");
        this.channel.invokeMethod("TextInputClient.performAction", Arrays.asList(new Serializable[]{Integer.valueOf(i), "TextInputAction.search"}));
    }

    public void send(int i) {
        Log.v(TAG, "Sending 'send' message.");
        this.channel.invokeMethod("TextInputClient.performAction", Arrays.asList(new Serializable[]{Integer.valueOf(i), "TextInputAction.send"}));
    }

    public void setTextInputMethodHandler(@Nullable TextInputMethodHandler textInputMethodHandler2) {
        this.textInputMethodHandler = textInputMethodHandler2;
    }

    public void unspecifiedAction(int i) {
        Log.v(TAG, "Sending 'unspecified' message.");
        this.channel.invokeMethod("TextInputClient.performAction", Arrays.asList(new Serializable[]{Integer.valueOf(i), "TextInputAction.unspecified"}));
    }

    public void updateEditingState(int i, String str, int i2, int i3, int i4, int i5) {
        Log.v(TAG, "Sending message to update editing state: \nText: " + str + "\nSelection start: " + i2 + "\nSelection end: " + i3 + "\nComposing start: " + i4 + "\nComposing end: " + i5);
        HashMap hashMap = new HashMap();
        hashMap.put("text", str);
        hashMap.put("selectionBase", Integer.valueOf(i2));
        hashMap.put("selectionExtent", Integer.valueOf(i3));
        hashMap.put("composingBase", Integer.valueOf(i4));
        hashMap.put("composingExtent", Integer.valueOf(i5));
        this.channel.invokeMethod("TextInputClient.updateEditingState", Arrays.asList(new Serializable[]{Integer.valueOf(i), hashMap}));
    }
}
