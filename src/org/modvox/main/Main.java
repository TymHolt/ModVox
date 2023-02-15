package org.modvox.main;

import org.modvox.gui.events.WindowKeyEvent;
import org.modvox.gui.events.WindowMouseButtonEvent;
import org.modvox.gui.events.WindowRenderEvent;
import org.modvox.gui.window.Window;
import org.modvox.gui.window.WindowEventListener;

public final class Main {

    public static void main(String[] args) {
        Window window = new Window();
        window.center();

        window.addEventListener(new WindowEventListener() {

            @Override
            public void onWindowRenderEvent(WindowRenderEvent event) {

            }

            @Override
            public void onWindowKeyEvent(WindowKeyEvent event) {
                System.out.println("Key Event");
            }

            @Override
            public void onWindowMouseButtonEvent(WindowMouseButtonEvent event) {
                System.out.println("Button Event");
            }
        });

        while(Window.needsUpdate())
            Window.updateAll();

        Window.terminateGLFW();
    }
}