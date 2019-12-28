package io.flutter.plugin.editing;

import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.InputDeviceCompat;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.systemchannels.TextInputChannel;
import io.flutter.plugin.platform.PlatformViewsController;

public class TextInputPlugin {
    @Nullable
    private TextInputChannel.Configuration configuration;
    @NonNull
    private InputTarget inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
    private boolean isInputConnectionLocked;
    @Nullable
    private InputConnection lastInputConnection;
    @Nullable
    private Editable mEditable;
    @NonNull
    private final InputMethodManager mImm;
    private boolean mRestartInputPending;
    /* access modifiers changed from: private */
    @NonNull
    public final View mView;
    @NonNull
    private PlatformViewsController platformViewsController;
    @NonNull
    private final TextInputChannel textInputChannel;

    private static class InputTarget {
        int id;
        @NonNull
        Type type;

        enum Type {
            NO_TARGET,
            FRAMEWORK_CLIENT,
            PLATFORM_VIEW
        }

        public InputTarget(@NonNull Type type2, int i) {
            this.type = type2;
            this.id = i;
        }
    }

    public TextInputPlugin(View view, @NonNull DartExecutor dartExecutor, @NonNull PlatformViewsController platformViewsController2) {
        this.mView = view;
        this.mImm = (InputMethodManager) view.getContext().getSystemService("input_method");
        this.textInputChannel = new TextInputChannel(dartExecutor);
        this.textInputChannel.setTextInputMethodHandler(new TextInputChannel.TextInputMethodHandler() {
            public void clearClient() {
                TextInputPlugin.this.clearTextInputClient();
            }

            public void hide() {
                TextInputPlugin textInputPlugin = TextInputPlugin.this;
                textInputPlugin.hideTextInput(textInputPlugin.mView);
            }

            public void setClient(int i, TextInputChannel.Configuration configuration) {
                TextInputPlugin.this.setTextInputClient(i, configuration);
            }

            public void setEditingState(TextInputChannel.TextEditState textEditState) {
                TextInputPlugin textInputPlugin = TextInputPlugin.this;
                textInputPlugin.setTextInputEditingState(textInputPlugin.mView, textEditState);
            }

            public void setPlatformViewClient(int i) {
                TextInputPlugin.this.setPlatformViewTextInputClient(i);
            }

            public void show() {
                TextInputPlugin textInputPlugin = TextInputPlugin.this;
                textInputPlugin.showTextInput(textInputPlugin.mView);
            }
        });
        this.platformViewsController = platformViewsController2;
        this.platformViewsController.attachTextInputPlugin(this);
    }

    private void applyStateToSelection(TextInputChannel.TextEditState textEditState) {
        int i = textEditState.selectionStart;
        int i2 = textEditState.selectionEnd;
        if (i < 0 || i > this.mEditable.length() || i2 < 0 || i2 > this.mEditable.length()) {
            Selection.removeSelection(this.mEditable);
        } else {
            Selection.setSelection(this.mEditable, i, i2);
        }
    }

    /* access modifiers changed from: private */
    public void clearTextInputClient() {
        if (this.inputTarget.type != InputTarget.Type.PLATFORM_VIEW) {
            this.inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
            unlockPlatformViewInputConnection();
        }
    }

    /* access modifiers changed from: private */
    public void hideTextInput(View view) {
        this.mImm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    private static int inputTypeFromTextInputType(TextInputChannel.InputType inputType, boolean z, boolean z2, TextInputChannel.TextCapitalization textCapitalization) {
        if (inputType.type == TextInputChannel.TextInputType.DATETIME) {
            return 4;
        }
        if (inputType.type == TextInputChannel.TextInputType.NUMBER) {
            int i = 2;
            if (inputType.isSigned) {
                i = InputDeviceCompat.SOURCE_TOUCHSCREEN;
            }
            return inputType.isDecimal ? i | 8192 : i;
        } else if (inputType.type == TextInputChannel.TextInputType.PHONE) {
            return 3;
        } else {
            int i2 = 1;
            if (inputType.type == TextInputChannel.TextInputType.MULTILINE) {
                i2 = 131073;
            } else if (inputType.type == TextInputChannel.TextInputType.EMAIL_ADDRESS) {
                i2 = 33;
            } else if (inputType.type == TextInputChannel.TextInputType.URL) {
                i2 = 17;
            } else if (inputType.type == TextInputChannel.TextInputType.VISIBLE_PASSWORD) {
                i2 = 145;
            }
            if (z) {
                i2 = 524288 | i2 | 128;
            } else if (z2) {
                i2 |= 32768;
            }
            return textCapitalization == TextInputChannel.TextCapitalization.CHARACTERS ? i2 | 4096 : textCapitalization == TextInputChannel.TextCapitalization.WORDS ? i2 | 8192 : textCapitalization == TextInputChannel.TextCapitalization.SENTENCES ? i2 | 16384 : i2;
        }
    }

    /* access modifiers changed from: private */
    public void setPlatformViewTextInputClient(int i) {
        this.mView.requestFocus();
        this.inputTarget = new InputTarget(InputTarget.Type.PLATFORM_VIEW, i);
        this.mImm.restartInput(this.mView);
        this.mRestartInputPending = false;
    }

    /* access modifiers changed from: private */
    public void setTextInputClient(int i, TextInputChannel.Configuration configuration2) {
        this.inputTarget = new InputTarget(InputTarget.Type.FRAMEWORK_CLIENT, i);
        this.configuration = configuration2;
        this.mEditable = Editable.Factory.getInstance().newEditable("");
        this.mRestartInputPending = true;
        unlockPlatformViewInputConnection();
    }

    /* access modifiers changed from: private */
    public void setTextInputEditingState(View view, TextInputChannel.TextEditState textEditState) {
        if (this.mRestartInputPending || !textEditState.text.equals(this.mEditable.toString())) {
            Editable editable = this.mEditable;
            editable.replace(0, editable.length(), textEditState.text);
            applyStateToSelection(textEditState);
            this.mImm.restartInput(view);
            this.mRestartInputPending = false;
            return;
        }
        applyStateToSelection(textEditState);
        this.mImm.updateSelection(this.mView, Math.max(Selection.getSelectionStart(this.mEditable), 0), Math.max(Selection.getSelectionEnd(this.mEditable), 0), BaseInputConnection.getComposingSpanStart(this.mEditable), BaseInputConnection.getComposingSpanEnd(this.mEditable));
    }

    /* access modifiers changed from: private */
    public void showTextInput(View view) {
        view.requestFocus();
        this.mImm.showSoftInput(view, 0);
    }

    public void clearPlatformViewClient(int i) {
        if (this.inputTarget.type == InputTarget.Type.PLATFORM_VIEW && this.inputTarget.id == i) {
            this.inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
            hideTextInput(this.mView);
            this.mImm.restartInput(this.mView);
            this.mRestartInputPending = false;
        }
    }

    public InputConnection createInputConnection(View view, EditorInfo editorInfo) {
        if (this.inputTarget.type == InputTarget.Type.NO_TARGET) {
            this.lastInputConnection = null;
            return null;
        } else if (this.inputTarget.type != InputTarget.Type.PLATFORM_VIEW) {
            editorInfo.inputType = inputTypeFromTextInputType(this.configuration.inputType, this.configuration.obscureText, this.configuration.autocorrect, this.configuration.textCapitalization);
            editorInfo.imeOptions = 33554432;
            int intValue = this.configuration.inputAction == null ? (131072 & editorInfo.inputType) != 0 ? 1 : 6 : this.configuration.inputAction.intValue();
            if (this.configuration.actionLabel != null) {
                editorInfo.actionLabel = this.configuration.actionLabel;
                editorInfo.actionId = intValue;
            }
            editorInfo.imeOptions = intValue | editorInfo.imeOptions;
            InputConnectionAdaptor inputConnectionAdaptor = new InputConnectionAdaptor(view, this.inputTarget.id, this.textInputChannel, this.mEditable);
            editorInfo.initialSelStart = Selection.getSelectionStart(this.mEditable);
            editorInfo.initialSelEnd = Selection.getSelectionEnd(this.mEditable);
            this.lastInputConnection = inputConnectionAdaptor;
            return this.lastInputConnection;
        } else if (this.isInputConnectionLocked) {
            return this.lastInputConnection;
        } else {
            this.lastInputConnection = this.platformViewsController.getPlatformViewById(Integer.valueOf(this.inputTarget.id)).onCreateInputConnection(editorInfo);
            return this.lastInputConnection;
        }
    }

    public void destroy() {
        this.platformViewsController.detachTextInputPlugin();
    }

    @NonNull
    public InputMethodManager getInputMethodManager() {
        return this.mImm;
    }

    @Nullable
    public InputConnection getLastInputConnection() {
        return this.lastInputConnection;
    }

    public void lockPlatformViewInputConnection() {
        if (this.inputTarget.type == InputTarget.Type.PLATFORM_VIEW) {
            this.isInputConnectionLocked = true;
        }
    }

    public void unlockPlatformViewInputConnection() {
        this.isInputConnectionLocked = false;
    }
}
