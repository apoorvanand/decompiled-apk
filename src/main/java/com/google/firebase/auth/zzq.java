package com.google.firebase.auth;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzq implements Continuation<GetTokenResult, Task<Void>> {
    private final /* synthetic */ FirebaseUser zzjg;

    zzq(FirebaseUser firebaseUser) {
        this.zzjg = firebaseUser;
    }

    public final /* synthetic */ Object then(@NonNull Task task) {
        return FirebaseAuth.getInstance(this.zzjg.zzcu()).zza((ActionCodeSettings) null, ((GetTokenResult) task.getResult()).getToken());
    }
}
