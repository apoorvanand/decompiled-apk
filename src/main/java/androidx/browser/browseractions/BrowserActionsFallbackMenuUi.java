package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.browser.R;
import androidx.core.widget.TextViewCompat;
import java.util.List;

class BrowserActionsFallbackMenuUi implements AdapterView.OnItemClickListener {
    private static final String TAG = "BrowserActionskMenuUi";
    BrowserActionsFallMenuUiListener a;
    private BrowserActionsFallbackMenuDialog mBrowserActionsDialog;
    private final Context mContext;
    private final List<BrowserActionItem> mMenuItems;
    private final Uri mUri;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    interface BrowserActionsFallMenuUiListener {
        void onMenuShown(View view);
    }

    BrowserActionsFallbackMenuUi(Context context, Uri uri, List<BrowserActionItem> list) {
        this.mContext = context;
        this.mUri = uri;
        this.mMenuItems = list;
    }

    private BrowserActionsFallbackMenuView initMenuView(View view) {
        BrowserActionsFallbackMenuView browserActionsFallbackMenuView = (BrowserActionsFallbackMenuView) view.findViewById(R.id.browser_actions_menu_view);
        final TextView textView = (TextView) view.findViewById(R.id.browser_actions_header_text);
        textView.setText(this.mUri.toString());
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView textView;
                TextUtils.TruncateAt truncateAt;
                if (TextViewCompat.getMaxLines(textView) == Integer.MAX_VALUE) {
                    textView.setMaxLines(1);
                    textView = textView;
                    truncateAt = TextUtils.TruncateAt.END;
                } else {
                    textView.setMaxLines(Integer.MAX_VALUE);
                    textView = textView;
                    truncateAt = null;
                }
                textView.setEllipsize(truncateAt);
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.browser_actions_menu_items);
        listView.setAdapter(new BrowserActionsFallbackMenuAdapter(this.mMenuItems, this.mContext));
        listView.setOnItemClickListener(this);
        return browserActionsFallbackMenuView;
    }

    public void displayMenu() {
        final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.browser_actions_context_menu_page, (ViewGroup) null);
        this.mBrowserActionsDialog = new BrowserActionsFallbackMenuDialog(this.mContext, initMenuView(inflate));
        this.mBrowserActionsDialog.setContentView(inflate);
        if (this.a != null) {
            this.mBrowserActionsDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    BrowserActionsFallbackMenuUi.this.a.onMenuShown(inflate);
                }
            });
        }
        this.mBrowserActionsDialog.show();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            this.mMenuItems.get(i).getAction().send();
            this.mBrowserActionsDialog.dismiss();
        } catch (PendingIntent.CanceledException e) {
            Log.e(TAG, "Failed to send custom item action", e);
        }
    }
}
