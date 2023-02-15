package org.modvox.gui.window;

import org.modvox.gui.events.WindowKeyEvent;
import org.modvox.gui.events.WindowMouseButtonEvent;
import org.modvox.gui.events.WindowRenderEvent;

public interface WindowEventListener {

    void onWindowRenderEvent(WindowRenderEvent event);
    void onWindowKeyEvent(WindowKeyEvent event);
    void onWindowMouseButtonEvent(WindowMouseButtonEvent event);
}
