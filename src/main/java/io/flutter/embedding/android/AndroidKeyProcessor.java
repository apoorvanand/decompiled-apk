package io.flutter.embedding.android;

import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.embedding.engine.systemchannels.KeyEventChannel;
import io.flutter.plugin.editing.TextInputPlugin;

public class AndroidKeyProcessor {
    private int combiningCharacter;
    @NonNull
    private final KeyEventChannel keyEventChannel;
    @NonNull
    private final TextInputPlugin textInputPlugin;

    public AndroidKeyProcessor(@NonNull KeyEventChannel keyEventChannel2, @NonNull TextInputPlugin textInputPlugin2) {
        this.keyEventChannel = keyEventChannel2;
        this.textInputPlugin = textInputPlugin2;
    }

    @Nullable
    private Character applyCombiningCharacterToBaseCharacter(int i) {
        if (i == 0) {
            return null;
        }
        Character valueOf = Character.valueOf((char) i);
        if ((Integer.MIN_VALUE & i) != 0) {
            int i2 = i & Integer.MAX_VALUE;
            int i3 = this.combiningCharacter;
            if (i3 != 0) {
                i2 = KeyCharacterMap.getDeadChar(i3, i2);
            }
            this.combiningCharacter = i2;
        } else {
            int i4 = this.combiningCharacter;
            if (i4 != 0) {
                int deadChar = KeyCharacterMap.getDeadChar(i4, i);
                if (deadChar > 0) {
                    valueOf = Character.valueOf((char) deadChar);
                }
                this.combiningCharacter = 0;
            }
        }
        return valueOf;
    }

    public void onKeyDown(@NonNull KeyEvent keyEvent) {
        if (this.textInputPlugin.getLastInputConnection() != null && this.textInputPlugin.getInputMethodManager().isAcceptingText()) {
            this.textInputPlugin.getLastInputConnection().sendKeyEvent(keyEvent);
        }
        this.keyEventChannel.keyDown(new KeyEventChannel.FlutterKeyEvent(keyEvent, applyCombiningCharacterToBaseCharacter(keyEvent.getUnicodeChar())));
    }

    public void onKeyUp(@NonNull KeyEvent keyEvent) {
        this.keyEventChannel.keyUp(new KeyEventChannel.FlutterKeyEvent(keyEvent, applyCombiningCharacterToBaseCharacter(keyEvent.getUnicodeChar())));
    }
}
