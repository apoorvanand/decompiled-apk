package com.facebook.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public final class NativeAppCallAttachmentStore {
    private static final String TAG = "com.facebook.internal.NativeAppCallAttachmentStore";
    private static File attachmentsDirectory;

    public static final class Attachment {
        /* access modifiers changed from: private */
        public final String attachmentName;
        private final String attachmentUrl;
        /* access modifiers changed from: private */
        public Bitmap bitmap;
        /* access modifiers changed from: private */
        public final UUID callId;
        /* access modifiers changed from: private */
        public boolean isContentUri;
        /* access modifiers changed from: private */
        public Uri originalUri;
        /* access modifiers changed from: private */
        public boolean shouldCreateFile;

        /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0064  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0079  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Attachment(java.util.UUID r4, android.graphics.Bitmap r5, android.net.Uri r6) {
            /*
                r3 = this;
                r3.<init>()
                r3.callId = r4
                r3.bitmap = r5
                r3.originalUri = r6
                r0 = 1
                if (r6 == 0) goto L_0x005a
                java.lang.String r5 = r6.getScheme()
                java.lang.String r1 = "content"
                boolean r1 = r1.equalsIgnoreCase(r5)
                if (r1 == 0) goto L_0x002f
                r3.isContentUri = r0
                java.lang.String r5 = r6.getAuthority()
                if (r5 == 0) goto L_0x002d
                java.lang.String r5 = r6.getAuthority()
                java.lang.String r6 = "media"
                boolean r5 = r5.startsWith(r6)
                if (r5 != 0) goto L_0x002d
                goto L_0x005c
            L_0x002d:
                r0 = 0
                goto L_0x005c
            L_0x002f:
                java.lang.String r1 = "file"
                java.lang.String r2 = r6.getScheme()
                boolean r1 = r1.equalsIgnoreCase(r2)
                if (r1 == 0) goto L_0x003c
                goto L_0x005c
            L_0x003c:
                boolean r6 = com.facebook.internal.Utility.isWebUri(r6)
                if (r6 == 0) goto L_0x0043
                goto L_0x005e
            L_0x0043:
                com.facebook.FacebookException r4 = new com.facebook.FacebookException
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r0 = "Unsupported scheme for media Uri : "
                r6.append(r0)
                r6.append(r5)
                java.lang.String r5 = r6.toString()
                r4.<init>((java.lang.String) r5)
                throw r4
            L_0x005a:
                if (r5 == 0) goto L_0x0086
            L_0x005c:
                r3.shouldCreateFile = r0
            L_0x005e:
                boolean r5 = r3.shouldCreateFile
                if (r5 != 0) goto L_0x0064
                r5 = 0
                goto L_0x006c
            L_0x0064:
                java.util.UUID r5 = java.util.UUID.randomUUID()
                java.lang.String r5 = r5.toString()
            L_0x006c:
                r3.attachmentName = r5
                boolean r5 = r3.shouldCreateFile
                if (r5 != 0) goto L_0x0079
                android.net.Uri r4 = r3.originalUri
                java.lang.String r4 = r4.toString()
                goto L_0x0083
            L_0x0079:
                java.lang.String r5 = com.facebook.FacebookSdk.getApplicationId()
                java.lang.String r6 = r3.attachmentName
                java.lang.String r4 = com.facebook.FacebookContentProvider.getAttachmentUrl(r5, r4, r6)
            L_0x0083:
                r3.attachmentUrl = r4
                return
            L_0x0086:
                com.facebook.FacebookException r4 = new com.facebook.FacebookException
                java.lang.String r5 = "Cannot share media without a bitmap or Uri set"
                r4.<init>((java.lang.String) r5)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.NativeAppCallAttachmentStore.Attachment.<init>(java.util.UUID, android.graphics.Bitmap, android.net.Uri):void");
        }

        public String getAttachmentUrl() {
            return this.attachmentUrl;
        }

        public Uri getOriginalUri() {
            return this.originalUri;
        }
    }

    private NativeAppCallAttachmentStore() {
    }

    static synchronized File a() {
        File file;
        synchronized (NativeAppCallAttachmentStore.class) {
            if (attachmentsDirectory == null) {
                attachmentsDirectory = new File(FacebookSdk.getApplicationContext().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
            }
            file = attachmentsDirectory;
        }
        return file;
    }

    static File a(UUID uuid, String str, boolean z) {
        File a = a(uuid, z);
        if (a == null) {
            return null;
        }
        try {
            return new File(a, URLEncoder.encode(str, "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    static File a(UUID uuid, boolean z) {
        File file = attachmentsDirectory;
        if (file == null) {
            return null;
        }
        File file2 = new File(file, uuid.toString());
        if (z && !file2.exists()) {
            file2.mkdirs();
        }
        return file2;
    }

    public static void addAttachments(Collection<Attachment> collection) {
        if (collection != null && collection.size() != 0) {
            if (attachmentsDirectory == null) {
                cleanupAllAttachments();
            }
            b();
            ArrayList<File> arrayList = new ArrayList<>();
            try {
                for (Attachment next : collection) {
                    if (next.shouldCreateFile) {
                        File a = a(next.callId, next.attachmentName, true);
                        arrayList.add(a);
                        if (next.bitmap != null) {
                            processAttachmentBitmap(next.bitmap, a);
                        } else if (next.originalUri != null) {
                            processAttachmentFile(next.originalUri, next.isContentUri, a);
                        }
                    }
                }
            } catch (IOException e) {
                String str = TAG;
                Log.e(str, "Got unexpected exception:" + e);
                for (File delete : arrayList) {
                    try {
                        delete.delete();
                    } catch (Exception unused) {
                    }
                }
                throw new FacebookException((Throwable) e);
            }
        }
    }

    static File b() {
        File a = a();
        a.mkdirs();
        return a;
    }

    public static void cleanupAllAttachments() {
        Utility.deleteDirectory(a());
    }

    public static void cleanupAttachmentsForCall(UUID uuid) {
        File a = a(uuid, false);
        if (a != null) {
            Utility.deleteDirectory(a);
        }
    }

    public static Attachment createAttachment(UUID uuid, Bitmap bitmap) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(bitmap, "attachmentBitmap");
        return new Attachment(uuid, bitmap, (Uri) null);
    }

    public static Attachment createAttachment(UUID uuid, Uri uri) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(uri, "attachmentUri");
        return new Attachment(uuid, (Bitmap) null, uri);
    }

    public static File openAttachment(UUID uuid, String str) {
        if (Utility.isNullOrEmpty(str) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return a(uuid, str, false);
        } catch (IOException unused) {
            throw new FileNotFoundException();
        }
    }

    private static void processAttachmentBitmap(Bitmap bitmap, File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } finally {
            Utility.closeQuietly(fileOutputStream);
        }
    }

    private static void processAttachmentFile(Uri uri, boolean z, File file) {
        InputStream inputStream;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        if (!z) {
            try {
                inputStream = new FileInputStream(uri.getPath());
            } catch (Throwable th) {
                Utility.closeQuietly(fileOutputStream);
                throw th;
            }
        } else {
            inputStream = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(uri);
        }
        Utility.copyAndCloseInputStream(inputStream, fileOutputStream);
        Utility.closeQuietly(fileOutputStream);
    }
}
