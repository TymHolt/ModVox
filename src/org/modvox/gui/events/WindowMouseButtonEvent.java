package org.modvox.gui.events;

import org.modvox.gui.data.Position;
import org.modvox.gui.input.InputAction;
import org.modvox.gui.input.MouseButton;
import org.modvox.gui.window.Window;

public class WindowMouseButtonEvent {

    public final Window window;
    public final MouseButton button;
    public final InputAction action;
    public final Position position;

    public WindowMouseButtonEvent(Window window, MouseButton button, InputAction action, Position position) {
        this.window = window;
        this.button = button;
        this.action = action;
        this.position = position;
    }
}
