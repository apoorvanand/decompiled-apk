package io.flutter.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import java.io.File;
import java.io.FilenameFilter;

class ResourceCleaner {
    private static final long DELAY_MS = 5000;
    private static final String TAG = "ResourceCleaner";
    private final Context mContext;

    private static class CleanTask extends AsyncTask<Void, Void, Void> {
        private final File[] mFilesToDelete;

        CleanTask(File[] fileArr) {
            this.mFilesToDelete = fileArr;
        }

        private void deleteRecursively(File file) {
            if (file.isDirectory()) {
                for (File deleteRecursively : file.listFiles()) {
                    deleteRecursively(deleteRecursively);
                }
            }
            file.delete();
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            for (File file : this.mFilesToDelete) {
                if (file.exists()) {
                    deleteRecursively(file);
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean hasFilesToDelete() {
            File[] fileArr = this.mFilesToDelete;
            return fileArr != null && fileArr.length > 0;
        }
    }

    ResourceCleaner(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public void start() {
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir != null) {
            final CleanTask cleanTask = new CleanTask(cacheDir.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.startsWith(ResourcePaths.TEMPORARY_RESOURCE_PREFIX);
                }
            }));
            if (cleanTask.hasFilesToDelete()) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        cleanTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    }
                }, DELAY_MS);
            }
        }
    }
}
