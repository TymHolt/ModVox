package org.modvox.gui.components;

import org.modvox.gui.data.Bounds;
import org.modvox.gui.events.*;
import org.modvox.gui.window.WindowEventListener;

public abstract class GUIComponent implements WindowEventListener {

    private Bounds bounds;
    private boolean focused = false;

    public GUIComponent(Bounds bounds) {
        this.bounds = bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Bounds getBounds() {
        return bounds;
    }

    @Override
    public void onWindowKeyEvent(WindowKeyEvent event) {
        if(focused)
            onComponentKeyEvent(new ComponentKeyEvent(this, event));
    }

    public abstract void onComponentKeyEvent(ComponentKeyEvent event);

    @Override
    public void onWindowMouseButtonEvent(WindowMouseButtonEvent event) {
        if(bounds.contains(event.position)) {
            focused = true;
            onComponentMouseButtonEvent(new ComponentMouseButtonEvent(this, event));
        } else
            focused = false;
    }

    public abstract void onComponentMouseButtonEvent(ComponentMouseButtonEvent event);
}
