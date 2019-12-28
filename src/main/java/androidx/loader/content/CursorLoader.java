package androidx.loader.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContentResolverCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader extends AsyncTaskLoader<Cursor> {
    final Loader<Cursor>.ForceLoadContentObserver f = new Loader.ForceLoadContentObserver();
    Uri g;
    String[] h;
    String i;
    String[] j;
    String k;
    Cursor l;
    CancellationSignal m;

    public CursorLoader(@NonNull Context context) {
        super(context);
    }

    public CursorLoader(@NonNull Context context, @NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        super(context);
        this.g = uri;
        this.h = strArr;
        this.i = str;
        this.j = strArr2;
        this.k = str2;
    }

    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            if (this.m != null) {
                this.m.cancel();
            }
        }
    }

    public void deliverResult(Cursor cursor) {
        if (!isReset()) {
            Cursor cursor2 = this.l;
            this.l = cursor;
            if (isStarted()) {
                super.deliverResult(cursor);
            }
            if (cursor2 != null && cursor2 != cursor && !cursor2.isClosed()) {
                cursor2.close();
            }
        } else if (cursor != null) {
            cursor.close();
        }
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.g);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.h));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.i);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.j));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.k);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.l);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.u);
    }

    /* access modifiers changed from: protected */
    public void e() {
        Cursor cursor = this.l;
        if (cursor != null) {
            deliverResult(cursor);
        }
        if (takeContentChanged() || this.l == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void f() {
        cancelLoad();
    }

    /* access modifiers changed from: protected */
    public void g() {
        super.g();
        f();
        Cursor cursor = this.l;
        if (cursor != null && !cursor.isClosed()) {
            this.l.close();
        }
        this.l = null;
    }

    @Nullable
    public String[] getProjection() {
        return this.h;
    }

    @Nullable
    public String getSelection() {
        return this.i;
    }

    @Nullable
    public String[] getSelectionArgs() {
        return this.j;
    }

    @Nullable
    public String getSortOrder() {
        return this.k;
    }

    @NonNull
    public Uri getUri() {
        return this.g;
    }

    public Cursor loadInBackground() {
        Cursor query;
        synchronized (this) {
            if (!isLoadInBackgroundCanceled()) {
                this.m = new CancellationSignal();
            } else {
                throw new OperationCanceledException();
            }
        }
        try {
            query = ContentResolverCompat.query(getContext().getContentResolver(), this.g, this.h, this.i, this.j, this.k, this.m);
            if (query != null) {
                query.getCount();
                query.registerContentObserver(this.f);
            }
            synchronized (this) {
                this.m = null;
            }
            return query;
        } catch (RuntimeException e) {
            query.close();
            throw e;
        } catch (Throwable th) {
            synchronized (this) {
                this.m = null;
                throw th;
            }
        }
    }

    public void onCanceled(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public void setProjection(@Nullable String[] strArr) {
        this.h = strArr;
    }

    public void setSelection(@Nullable String str) {
        this.i = str;
    }

    public void setSelectionArgs(@Nullable String[] strArr) {
        this.j = strArr;
    }

    public void setSortOrder(@Nullable String str) {
        this.k = str;
    }

    public void setUri(@NonNull Uri uri) {
        this.g = uri;
    }
}
