package org.modvox.gui.input;

import org.lwjgl.glfw.GLFW;

public enum InputAction {
    PRESS(GLFW.GLFW_PRESS),
    RELEASE(GLFW.GLFW_RELEASE);

    public final int glfwHandle;

    InputAction(int glfwHandle) {
        this.glfwHandle = glfwHandle;
    }

    public static InputAction fromGLFWHandle(int glfwHandle) {
        for(InputAction action : values())
            if(action.glfwHandle == glfwHandle)
                return action;

        return null;
    }
}
