package org.modvox.gui.components;

import org.modvox.gui.data.ContentPosition;
import org.modvox.gui.input.InputAction;
import org.modvox.gui.input.Key;
import org.modvox.gui.input.MouseButton;

public interface GUIComponent {

    void onMouseButtonAction(MouseButton mouseButton, InputAction inputAction, ContentPosition contentPosition);
    void onKeyAction(Key key, InputAction inputAction);
}
