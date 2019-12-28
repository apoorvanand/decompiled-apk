package com.google.firebase.ml.vision.label;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.LinkedList;
import java.util.List;

final class zzb implements Continuation<List<FirebaseVisionImageLabel>, List<FirebaseVisionImageLabel>> {
    private final /* synthetic */ FirebaseVisionImageLabeler zzawr;

    zzb(FirebaseVisionImageLabeler firebaseVisionImageLabeler) {
        this.zzawr = firebaseVisionImageLabeler;
    }

    public final /* synthetic */ Object then(@NonNull Task task) {
        LinkedList linkedList = new LinkedList();
        for (FirebaseVisionImageLabel firebaseVisionImageLabel : (List) task.getResult()) {
            if (Float.compare(firebaseVisionImageLabel.getConfidence(), this.zzawr.zzawn.getConfidenceThreshold()) >= 0) {
                linkedList.add(firebaseVisionImageLabel);
            }
        }
        return linkedList;
    }
}
