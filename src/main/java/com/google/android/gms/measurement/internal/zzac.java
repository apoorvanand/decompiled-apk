package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.util.Clock;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class zzac extends zzge {
    private long zzey;
    private String zzez;
    private Boolean zzfa;
    private AccountManager zzfb;
    private Boolean zzfc;
    private long zzfd;

    zzac(zzfj zzfj) {
        super(zzfj);
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        Calendar instance = Calendar.getInstance();
        this.zzey = TimeUnit.MINUTES.convert((long) (instance.get(15) + instance.get(16)), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String lowerCase = locale.getLanguage().toLowerCase(Locale.ENGLISH);
        String lowerCase2 = locale.getCountry().toLowerCase(Locale.ENGLISH);
        StringBuilder sb = new StringBuilder(String.valueOf(lowerCase).length() + 1 + String.valueOf(lowerCase2).length());
        sb.append(lowerCase);
        sb.append("-");
        sb.append(lowerCase2);
        this.zzez = sb.toString();
        return false;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void c() {
        zzo();
        this.zzfc = null;
        this.zzfd = 0;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final long c_() {
        zzo();
        return this.zzfd;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean d() {
        zzo();
        long currentTimeMillis = zzx().currentTimeMillis();
        if (currentTimeMillis - this.zzfd > 86400000) {
            this.zzfc = null;
        }
        Boolean bool = this.zzfc;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.GET_ACCOUNTS") != 0) {
            zzab().zzgo().zzao("Permission error checking for dasher/unicorn accounts");
        } else {
            if (this.zzfb == null) {
                this.zzfb = AccountManager.get(getContext());
            }
            try {
                Account[] result = this.zzfb.getAccountsByTypeAndFeatures("com.google", new String[]{"service_HOSTED"}, (AccountManagerCallback) null, (Handler) null).getResult();
                if (result == null || result.length <= 0) {
                    Account[] result2 = this.zzfb.getAccountsByTypeAndFeatures("com.google", new String[]{"service_uca"}, (AccountManagerCallback) null, (Handler) null).getResult();
                    if (result2 != null && result2.length > 0) {
                        this.zzfc = true;
                        this.zzfd = currentTimeMillis;
                        return true;
                    }
                } else {
                    this.zzfc = true;
                    this.zzfd = currentTimeMillis;
                    return true;
                }
            } catch (AuthenticatorException | OperationCanceledException | IOException e) {
                zzab().zzgl().zza("Exception checking account types", e);
            }
        }
        this.zzfd = currentTimeMillis;
        this.zzfc = false;
        return false;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }

    public final long zzcq() {
        l();
        return this.zzey;
    }

    public final String zzcr() {
        l();
        return this.zzez;
    }

    public final boolean zzj(Context context) {
        if (this.zzfa == null) {
            zzae();
            this.zzfa = false;
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    packageManager.getPackageInfo("com.google.android.gms", 128);
                    this.zzfa = true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return this.zzfa.booleanValue();
    }

    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }
}
