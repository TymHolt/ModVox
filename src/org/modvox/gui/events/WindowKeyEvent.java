package org.modvox.gui.events;

import org.modvox.gui.input.InputAction;
import org.modvox.gui.input.Key;
import org.modvox.gui.window.Window;

public class WindowKeyEvent {

    public final Window window;
    public final Key key;
    public final InputAction action;

    public WindowKeyEvent(Window window, Key key, InputAction action) {
        this.window = window;
        this.key = key;
        this.action = action;
    }
}
