package com.razorpay;

import android.os.Bundle;
import java.util.HashMap;

abstract class E$_6$ extends b__J_ {
    E$_6$() {
    }

    public void onCreate(Bundle bundle) {
        HashMap<String, String> allPluginsFromManifest = BaseUtils.getAllPluginsFromManifest(this);
        if (allPluginsFromManifest == null || allPluginsFromManifest.size() == 0) {
            this.presenter = new r_$Z$(this, this);
            this.checkoutBridgeObject = new c__C_((Y$_o$) this.presenter, 1);
            super.onCreate(bundle);
            return;
        }
        this.presenter = new PluginOtpElfCheckoutPresenterImpl(this, this, allPluginsFromManifest);
        this.checkoutBridgeObject = new PluginCheckoutBridge((PluginCheckoutInteractor) this.presenter);
        super.onCreate(bundle);
        for (String loadClass : allPluginsFromManifest.values()) {
            try {
                RzpPluginCompatibilityResponse isCompatible = ((RzpPlugin) RzpPlugin.class.getClassLoader().loadClass(loadClass).newInstance()).isCompatible(f$_G$.R$$r_, f$_G$.Q_$2$, f$_G$.a_$P$);
                if (!isCompatible.isCompatible()) {
                    destroy(7, isCompatible.getErrorMessage());
                    return;
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
