package org.modvox.gui.components;

import org.modvox.data.Color;
import org.modvox.gui.data.ContentPosition;
import org.modvox.gui.input.InputAction;
import org.modvox.gui.input.Key;
import org.modvox.gui.input.MouseButton;

public class ColorPickerComponent implements GUIComponent {

    private final Listener listener;

    public ColorPickerComponent(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onMouseButtonAction(MouseButton mouseButton, InputAction inputAction, ContentPosition contentPosition) {

    }

    @Override
    public void onKeyAction(Key key, InputAction inputAction) {

    }

    public interface Listener {

        void onColorPicked(ColorPickerComponent component, Color color);
    }
}
