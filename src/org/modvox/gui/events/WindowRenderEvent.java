package org.modvox.gui.events;

import org.modvox.gui.window.Window;

public class WindowRenderEvent {

    public final Window window;
    public final float deltaTime;

    public WindowRenderEvent(Window window, float deltaTime) {
        this.window = window;
        this.deltaTime = deltaTime;
    }
}
