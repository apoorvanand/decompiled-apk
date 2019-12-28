package androidx.fragment.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;

public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    int a = 0;
    int b = 0;
    boolean c = true;
    boolean d = true;
    int e = -1;
    Dialog f;
    boolean g;
    boolean h;
    boolean i;

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (!this.h) {
            this.h = true;
            this.i = false;
            Dialog dialog = this.f;
            if (dialog != null) {
                dialog.dismiss();
            }
            this.g = true;
            if (this.e >= 0) {
                getFragmentManager().popBackStack(this.e, 1);
                this.e = -1;
                return;
            }
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.remove(this);
            if (z) {
                beginTransaction.commitAllowingStateLoss();
            } else {
                beginTransaction.commit();
            }
        }
    }

    public void dismiss() {
        a(false);
    }

    public void dismissAllowingStateLoss() {
        a(true);
    }

    public Dialog getDialog() {
        return this.f;
    }

    public boolean getShowsDialog() {
        return this.d;
    }

    @StyleRes
    public int getTheme() {
        return this.b;
    }

    public boolean isCancelable() {
        return this.c;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        Bundle bundle2;
        super.onActivityCreated(bundle);
        if (this.d) {
            View view = getView();
            if (view != null) {
                if (view.getParent() == null) {
                    this.f.setContentView(view);
                } else {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                this.f.setOwnerActivity(activity);
            }
            this.f.setCancelable(this.c);
            this.f.setOnCancelListener(this);
            this.f.setOnDismissListener(this);
            if (bundle != null && (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) != null) {
                this.f.onRestoreInstanceState(bundle2);
            }
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (!this.i) {
            this.h = false;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.d = this.mContainerId == 0;
        if (bundle != null) {
            this.a = bundle.getInt(SAVED_STYLE, 0);
            this.b = bundle.getInt(SAVED_THEME, 0);
            this.c = bundle.getBoolean(SAVED_CANCELABLE, true);
            this.d = bundle.getBoolean(SAVED_SHOWS_DIALOG, this.d);
            this.e = bundle.getInt(SAVED_BACK_STACK_ID, -1);
        }
    }

    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        return new Dialog(getActivity(), getTheme());
    }

    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.f;
        if (dialog != null) {
            this.g = true;
            dialog.dismiss();
            this.f = null;
        }
    }

    public void onDetach() {
        super.onDetach();
        if (!this.i && !this.h) {
            this.h = true;
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.g) {
            a(true);
        }
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle) {
        Context b2;
        if (!this.d) {
            return super.onGetLayoutInflater(bundle);
        }
        this.f = onCreateDialog(bundle);
        Dialog dialog = this.f;
        if (dialog != null) {
            setupDialog(dialog, this.a);
            b2 = this.f.getContext();
        } else {
            b2 = this.mHost.b();
        }
        return (LayoutInflater) b2.getSystemService("layout_inflater");
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        Bundle onSaveInstanceState;
        super.onSaveInstanceState(bundle);
        Dialog dialog = this.f;
        if (!(dialog == null || (onSaveInstanceState = dialog.onSaveInstanceState()) == null)) {
            bundle.putBundle(SAVED_DIALOG_STATE_TAG, onSaveInstanceState);
        }
        int i2 = this.a;
        if (i2 != 0) {
            bundle.putInt(SAVED_STYLE, i2);
        }
        int i3 = this.b;
        if (i3 != 0) {
            bundle.putInt(SAVED_THEME, i3);
        }
        boolean z = this.c;
        if (!z) {
            bundle.putBoolean(SAVED_CANCELABLE, z);
        }
        boolean z2 = this.d;
        if (!z2) {
            bundle.putBoolean(SAVED_SHOWS_DIALOG, z2);
        }
        int i4 = this.e;
        if (i4 != -1) {
            bundle.putInt(SAVED_BACK_STACK_ID, i4);
        }
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = this.f;
        if (dialog != null) {
            this.g = false;
            dialog.show();
        }
    }

    public void onStop() {
        super.onStop();
        Dialog dialog = this.f;
        if (dialog != null) {
            dialog.hide();
        }
    }

    public void setCancelable(boolean z) {
        this.c = z;
        Dialog dialog = this.f;
        if (dialog != null) {
            dialog.setCancelable(z);
        }
    }

    public void setShowsDialog(boolean z) {
        this.d = z;
    }

    public void setStyle(int i2, @StyleRes int i3) {
        this.a = i2;
        int i4 = this.a;
        if (i4 == 2 || i4 == 3) {
            this.b = 16973913;
        }
        if (i3 != 0) {
            this.b = i3;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setupDialog(Dialog dialog, int i2) {
        switch (i2) {
            case 1:
            case 2:
                break;
            case 3:
                dialog.getWindow().addFlags(24);
                break;
            default:
                return;
        }
        dialog.requestWindowFeature(1);
    }

    public int show(FragmentTransaction fragmentTransaction, String str) {
        this.h = false;
        this.i = true;
        fragmentTransaction.add((Fragment) this, str);
        this.g = false;
        this.e = fragmentTransaction.commit();
        return this.e;
    }

    public void show(FragmentManager fragmentManager, String str) {
        this.h = false;
        this.i = true;
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add((Fragment) this, str);
        beginTransaction.commit();
    }

    public void showNow(FragmentManager fragmentManager, String str) {
        this.h = false;
        this.i = true;
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add((Fragment) this, str);
        beginTransaction.commitNow();
    }
}
