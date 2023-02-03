package org.modvox.main;

import org.modvox.gui.ModVoxWindow;

public final class Main {

    public static void main(String[] args) {
        new ModVoxWindow();

        while(ModVoxWindow.hasOpenWindows())
            ModVoxWindow.renderAll();

        ModVoxWindow.terminateGLFW();
    }
}