package org.modvox.gui.events;

import org.modvox.gui.components.GUIComponent;
import org.modvox.gui.input.InputAction;
import org.modvox.gui.input.Key;
import org.modvox.gui.window.Window;

public class ComponentKeyEvent extends WindowKeyEvent {

    public final GUIComponent guiComponent;

    public ComponentKeyEvent(Window window, GUIComponent guiComponent, Key key, InputAction action) {
        super(window, key, action);
        this.guiComponent = guiComponent;
    }

    public ComponentKeyEvent(GUIComponent guiComponent, WindowKeyEvent windowKeyEvent) {
        this(windowKeyEvent.window, guiComponent, windowKeyEvent.key, windowKeyEvent.action);
    }
}
