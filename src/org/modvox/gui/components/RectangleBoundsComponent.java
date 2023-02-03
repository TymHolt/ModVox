package org.modvox.gui.components;

import org.modvox.gui.data.ContentBounds;
import org.modvox.gui.data.ContentPosition;
import org.modvox.gui.input.InputAction;
import org.modvox.gui.input.Key;
import org.modvox.gui.input.MouseButton;

public class RectangleBoundsComponent implements GUIComponent {

    private final ContentBounds contentBounds;
    private final GUIComponent guiComponent;
    private boolean isFocused;

    public RectangleBoundsComponent(ContentBounds contentBounds, GUIComponent guiComponent) {
        this.contentBounds = contentBounds;
        this.guiComponent = guiComponent;
    }

    @Override
    public void onMouseButtonAction(MouseButton mouseButton, InputAction inputAction, ContentPosition contentPosition) {
        if(contentBounds.contains(contentPosition)) {
            isFocused = true;
            guiComponent.onMouseButtonAction(mouseButton, inputAction, contentPosition);
        } else
            isFocused = false;
    }

    @Override
    public void onKeyAction(Key key, InputAction inputAction) {
        if(isFocused)
            guiComponent.onKeyAction(key, inputAction);
    }
}
