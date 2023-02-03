package org.modvox.gui;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public final class ModVoxWindow {

    private static final ArrayList<ModVoxWindow> openWindowList = new ArrayList<>();

    static {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!GLFW.glfwInit())
            throw new IllegalStateException("Could not initialize GLFW");
    }

    public static void terminateGLFW() {
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    public static boolean hasOpenWindows() {
        return openWindowList.size() > 0;
    }

    public static void renderAll() {
        GLFW.glfwPollEvents();

        ArrayList<ModVoxWindow> closedWindowList = new ArrayList<>();

        for(ModVoxWindow modVoxWindow : openWindowList) {
            if(GLFW.glfwWindowShouldClose(modVoxWindow.glfwHandle))
                closedWindowList.add(modVoxWindow);

            modVoxWindow.render();
            GLFW.glfwSwapBuffers(modVoxWindow.glfwHandle);
        }

        for(ModVoxWindow modVoxWindow : closedWindowList)
            openWindowList.remove(modVoxWindow);
    }

    private final long glfwHandle;

    public ModVoxWindow() {
        openWindowList.add(this);

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        glfwHandle = GLFW.glfwCreateWindow(screenSize.width / 2, screenSize.height / 2, "ModVox", 0, 0);

        GLFW.glfwMakeContextCurrent(glfwHandle);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(glfwHandle);
        GL.createCapabilities();
    }

    public void render() {
        GLFW.glfwMakeContextCurrent(glfwHandle);
        GL11.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }
}
