package org.modvox.gui.events;

import org.modvox.gui.components.GUIComponent;
import org.modvox.gui.data.Position;
import org.modvox.gui.input.InputAction;
import org.modvox.gui.input.MouseButton;
import org.modvox.gui.window.Window;

public class ComponentMouseButtonEvent extends WindowMouseButtonEvent {

    public final GUIComponent component;

    public ComponentMouseButtonEvent(Window window, GUIComponent component, MouseButton button, InputAction action,
                                     Position position) {
        super(window, button, action, position);
        this.component = component;
    }

    public ComponentMouseButtonEvent(GUIComponent component, WindowMouseButtonEvent event) {
        this(event.window, component, event.button, event.action, event.position);
    }
}
