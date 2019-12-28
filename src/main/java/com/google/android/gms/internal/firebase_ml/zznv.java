package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseLocalModelSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public final class zznv {
    private MappedByteBuffer zzaqb;
    private final FirebaseLocalModelSource zzaqc;
    private final Context zzv;

    public zznv(@NonNull Context context, @NonNull FirebaseLocalModelSource firebaseLocalModelSource) {
        this.zzv = context;
        this.zzaqc = firebaseLocalModelSource;
    }

    @WorkerThread
    @Nullable
    public final MappedByteBuffer load() {
        Preconditions.checkNotNull(this.zzv, "Context can not be null");
        Preconditions.checkNotNull(this.zzaqc, "Model source can not be null");
        MappedByteBuffer mappedByteBuffer = this.zzaqb;
        if (mappedByteBuffer != null) {
            return mappedByteBuffer;
        }
        if (this.zzaqc.getFilePath() != null) {
            try {
                FileChannel channel = new RandomAccessFile(this.zzaqc.getFilePath(), "r").getChannel();
                this.zzaqb = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                return this.zzaqb;
            } catch (IOException e) {
                String valueOf = String.valueOf(this.zzaqc.getFilePath());
                throw new FirebaseMLException(valueOf.length() != 0 ? "Can not open the local file: ".concat(valueOf) : new String("Can not open the local file: "), 14, e);
            }
        } else if (this.zzaqc.getAssetFilePath() == null) {
            return null;
        } else {
            String assetFilePath = this.zzaqc.getAssetFilePath();
            try {
                AssetFileDescriptor openFd = this.zzv.getAssets().openFd(assetFilePath);
                this.zzaqb = new FileInputStream(openFd.getFileDescriptor()).getChannel().map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength());
                return this.zzaqb;
            } catch (IOException e2) {
                StringBuilder sb = new StringBuilder(String.valueOf(assetFilePath).length() + 184);
                sb.append("Can not load the file from asset: ");
                sb.append(assetFilePath);
                sb.append(". Please double check your assetfile name and ensure it's not compressed. See documentation for details how touse aaptOptions to skip file compression");
                throw new FirebaseMLException(sb.toString(), 14, e2);
            }
        }
    }
}
