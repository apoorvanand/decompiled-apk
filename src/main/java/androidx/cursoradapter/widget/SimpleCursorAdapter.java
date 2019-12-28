package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RestrictTo;

public class SimpleCursorAdapter extends ResourceCursorAdapter {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected int[] j;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected int[] k;
    String[] l;
    private CursorToStringConverter mCursorToStringConverter;
    private int mStringConversionColumn = -1;
    private ViewBinder mViewBinder;

    public interface CursorToStringConverter {
        CharSequence convertToString(Cursor cursor);
    }

    public interface ViewBinder {
        boolean setViewValue(View view, Cursor cursor, int i);
    }

    @Deprecated
    public SimpleCursorAdapter(Context context, int i, Cursor cursor, String[] strArr, int[] iArr) {
        super(context, i, cursor);
        this.k = iArr;
        this.l = strArr;
        findColumns(cursor, strArr);
    }

    public SimpleCursorAdapter(Context context, int i, Cursor cursor, String[] strArr, int[] iArr, int i2) {
        super(context, i, cursor, i2);
        this.k = iArr;
        this.l = strArr;
        findColumns(cursor, strArr);
    }

    private void findColumns(Cursor cursor, String[] strArr) {
        if (cursor != null) {
            int length = strArr.length;
            int[] iArr = this.j;
            if (iArr == null || iArr.length != length) {
                this.j = new int[length];
            }
            for (int i = 0; i < length; i++) {
                this.j[i] = cursor.getColumnIndexOrThrow(strArr[i]);
            }
            return;
        }
        this.j = null;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ViewBinder viewBinder = this.mViewBinder;
        int[] iArr = this.k;
        int length = iArr.length;
        int[] iArr2 = this.j;
        for (int i = 0; i < length; i++) {
            View findViewById = view.findViewById(iArr[i]);
            if (findViewById != null) {
                if (viewBinder != null ? viewBinder.setViewValue(findViewById, cursor, iArr2[i]) : false) {
                    continue;
                } else {
                    String string = cursor.getString(iArr2[i]);
                    if (string == null) {
                        string = "";
                    }
                    if (findViewById instanceof TextView) {
                        setViewText((TextView) findViewById, string);
                    } else if (findViewById instanceof ImageView) {
                        setViewImage((ImageView) findViewById, string);
                    } else {
                        throw new IllegalStateException(findViewById.getClass().getName() + " is not a " + " view that can be bounds by this SimpleCursorAdapter");
                    }
                }
            }
        }
    }

    public void changeCursorAndColumns(Cursor cursor, String[] strArr, int[] iArr) {
        this.l = strArr;
        this.k = iArr;
        findColumns(cursor, this.l);
        super.changeCursor(cursor);
    }

    public CharSequence convertToString(Cursor cursor) {
        CursorToStringConverter cursorToStringConverter = this.mCursorToStringConverter;
        if (cursorToStringConverter != null) {
            return cursorToStringConverter.convertToString(cursor);
        }
        int i = this.mStringConversionColumn;
        return i > -1 ? cursor.getString(i) : super.convertToString(cursor);
    }

    public CursorToStringConverter getCursorToStringConverter() {
        return this.mCursorToStringConverter;
    }

    public int getStringConversionColumn() {
        return this.mStringConversionColumn;
    }

    public ViewBinder getViewBinder() {
        return this.mViewBinder;
    }

    public void setCursorToStringConverter(CursorToStringConverter cursorToStringConverter) {
        this.mCursorToStringConverter = cursorToStringConverter;
    }

    public void setStringConversionColumn(int i) {
        this.mStringConversionColumn = i;
    }

    public void setViewBinder(ViewBinder viewBinder) {
        this.mViewBinder = viewBinder;
    }

    public void setViewImage(ImageView imageView, String str) {
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    public void setViewText(TextView textView, String str) {
        textView.setText(str);
    }

    public Cursor swapCursor(Cursor cursor) {
        findColumns(cursor, this.l);
        return super.swapCursor(cursor);
    }
}
