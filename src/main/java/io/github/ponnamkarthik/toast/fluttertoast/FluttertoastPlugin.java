package io.github.ponnamkarthik.toast.fluttertoast;

import android.content.Context;
import android.widget.Toast;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class FluttertoastPlugin implements MethodChannel.MethodCallHandler {
    private Context mContext;
    private Toast mToast;

    private FluttertoastPlugin(Context context) {
        this.mContext = context;
    }

    private float dp2px(float f) {
        return (f * this.mContext.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodChannel(registrar.messenger(), "PonnamKarthik/fluttertoast").setMethodCallHandler(new FluttertoastPlugin(registrar.context()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r13, io.flutter.plugin.common.MethodChannel.Result r14) {
        /*
            r12 = this;
            java.lang.String r0 = r13.method
            int r1 = r0.hashCode()
            r2 = -1913642710(0xffffffff8df0212a, float:-1.4799126E-30)
            r3 = -1
            r4 = 1
            r5 = 0
            if (r1 == r2) goto L_0x001e
            r2 = -1367724422(0xffffffffae7a2e7a, float:-5.68847E-11)
            if (r1 == r2) goto L_0x0014
            goto L_0x0028
        L_0x0014:
            java.lang.String r1 = "cancel"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            r0 = 1
            goto L_0x0029
        L_0x001e:
            java.lang.String r1 = "showToast"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            r0 = 0
            goto L_0x0029
        L_0x0028:
            r0 = -1
        L_0x0029:
            switch(r0) {
                case 0: goto L_0x0041;
                case 1: goto L_0x0031;
                default: goto L_0x002c;
            }
        L_0x002c:
            r14.notImplemented()
            goto L_0x0128
        L_0x0031:
            android.widget.Toast r13 = r12.mToast
            if (r13 == 0) goto L_0x0038
            r13.cancel()
        L_0x0038:
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r4)
            r14.success(r13)
            goto L_0x0128
        L_0x0041:
            java.lang.String r0 = "msg"
            java.lang.Object r0 = r13.argument(r0)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "length"
            java.lang.Object r1 = r13.argument(r1)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "gravity"
            java.lang.Object r2 = r13.argument(r2)
            java.lang.String r2 = r2.toString()
            java.lang.String r6 = "bgcolor"
            java.lang.Object r6 = r13.argument(r6)
            java.lang.Number r6 = (java.lang.Number) r6
            java.lang.String r7 = "textcolor"
            java.lang.Object r7 = r13.argument(r7)
            java.lang.Number r7 = (java.lang.Number) r7
            java.lang.String r8 = "fontSize"
            java.lang.Object r13 = r13.argument(r8)
            java.lang.Number r13 = (java.lang.Number) r13
            int r8 = r2.hashCode()
            r9 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            if (r8 == r9) goto L_0x0090
            r9 = 115029(0x1c155, float:1.6119E-40)
            if (r8 == r9) goto L_0x0086
            goto L_0x0099
        L_0x0086:
            java.lang.String r8 = "top"
            boolean r2 = r2.equals(r8)
            if (r2 == 0) goto L_0x0099
            r3 = 0
            goto L_0x0099
        L_0x0090:
            java.lang.String r8 = "center"
            boolean r2 = r2.equals(r8)
            if (r2 == 0) goto L_0x0099
            r3 = 1
        L_0x0099:
            r2 = 17
            r8 = 48
            switch(r3) {
                case 0: goto L_0x00a6;
                case 1: goto L_0x00a3;
                default: goto L_0x00a0;
            }
        L_0x00a0:
            r3 = 80
            goto L_0x00a8
        L_0x00a3:
            r3 = 17
            goto L_0x00a8
        L_0x00a6:
            r3 = 48
        L_0x00a8:
            java.lang.String r9 = "long"
            boolean r1 = r1.equals(r9)
            if (r6 == 0) goto L_0x010a
            if (r7 == 0) goto L_0x010a
            if (r13 == 0) goto L_0x010a
            android.content.Context r9 = r12.mContext
            java.lang.String r10 = "layout_inflater"
            java.lang.Object r9 = r9.getSystemService(r10)
            android.view.LayoutInflater r9 = (android.view.LayoutInflater) r9
            int r10 = io.github.ponnamkarthik.toast.fluttertoast.R.layout.toast_custom
            r11 = 0
            android.view.View r9 = r9.inflate(r10, r11)
            int r10 = io.github.ponnamkarthik.toast.fluttertoast.R.id.text
            android.view.View r10 = r9.findViewById(r10)
            android.widget.TextView r10 = (android.widget.TextView) r10
            r10.setText(r0)
            android.graphics.drawable.GradientDrawable r0 = new android.graphics.drawable.GradientDrawable
            r0.<init>()
            int r6 = r6.intValue()
            r0.setColor(r6)
            r6 = 1082130432(0x40800000, float:4.0)
            float r6 = r12.dp2px(r6)
            r0.setCornerRadius(r6)
            r10.setBackground(r0)
            float r13 = r13.floatValue()
            r10.setTextSize(r13)
            int r13 = r7.intValue()
            r10.setTextColor(r13)
            android.widget.Toast r13 = new android.widget.Toast
            android.content.Context r0 = r12.mContext
            r13.<init>(r0)
            r12.mToast = r13
            android.widget.Toast r13 = r12.mToast
            r13.setDuration(r1)
            android.widget.Toast r13 = r12.mToast
            r13.setView(r9)
            goto L_0x0112
        L_0x010a:
            android.content.Context r13 = r12.mContext
            android.widget.Toast r13 = android.widget.Toast.makeText(r13, r0, r1)
            r12.mToast = r13
        L_0x0112:
            if (r3 != r2) goto L_0x011a
            android.widget.Toast r13 = r12.mToast
            r13.setGravity(r3, r5, r5)
            goto L_0x0121
        L_0x011a:
            r13 = 100
            android.widget.Toast r0 = r12.mToast
            r0.setGravity(r3, r5, r13)
        L_0x0121:
            android.widget.Toast r13 = r12.mToast
            r13.show()
            goto L_0x0038
        L_0x0128:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.ponnamkarthik.toast.fluttertoast.FluttertoastPlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
