package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;

public final class zaah implements zabd {
    /* access modifiers changed from: private */
    public final zabe zaft;
    private boolean zafu = false;

    public zaah(zabe zabe) {
        this.zaft = zabe;
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        if (this.zafu) {
            this.zafu = false;
            this.zaft.d.e.release();
            disconnect();
        }
    }

    public final void begin() {
    }

    public final void connect() {
        if (this.zafu) {
            this.zafu = false;
            this.zaft.a((zabf) new zaaj(this, this));
        }
    }

    public final boolean disconnect() {
        if (this.zafu) {
            return false;
        }
        if (this.zaft.d.c()) {
            this.zafu = true;
            for (zacm a : this.zaft.d.d) {
                a.a();
            }
            return false;
        }
        this.zaft.a((ConnectionResult) null);
        return true;
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        return execute(t);
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        try {
            this.zaft.d.e.a(t);
            zaaw zaaw = this.zaft.d;
            Api.Client client = zaaw.b.get(t.getClientKey());
            Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
            if (client.isConnected() || !this.zaft.b.containsKey(t.getClientKey())) {
                boolean z = client instanceof SimpleClientAdapter;
                Api.AnyClient anyClient = client;
                if (z) {
                    anyClient = ((SimpleClientAdapter) client).getClient();
                }
                t.run(anyClient);
                return t;
            }
            t.setFailedResult(new Status(17));
            return t;
        } catch (DeadObjectException unused) {
            this.zaft.a((zabf) new zaai(this, this));
        }
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void onConnectionSuspended(int i) {
        this.zaft.a((ConnectionResult) null);
        this.zaft.e.zab(i, this.zafu);
    }

    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }
}
