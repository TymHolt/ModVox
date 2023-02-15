package org.modvox.gui.input;

import org.lwjgl.glfw.GLFW;

public enum MouseButton {
    LEFT(GLFW.GLFW_MOUSE_BUTTON_LEFT),
    RIGHT(GLFW.GLFW_MOUSE_BUTTON_RIGHT),
    MIDDLE(GLFW.GLFW_MOUSE_BUTTON_MIDDLE),
    BUTTON_4(GLFW.GLFW_MOUSE_BUTTON_4),
    BUTTON_5(GLFW.GLFW_MOUSE_BUTTON_5),
    BUTTON_6(GLFW.GLFW_MOUSE_BUTTON_6),
    BUTTON_7(GLFW.GLFW_MOUSE_BUTTON_7),
    BUTTON_8(GLFW.GLFW_MOUSE_BUTTON_8);

    public final int glfwHandle;

    MouseButton(int glfwHandle) {
        this.glfwHandle = glfwHandle;
    }

    public static MouseButton fromGLFWHandle(int glfwHandle) {
        for(MouseButton button : values())
            if(button.glfwHandle == glfwHandle)
                return button;

        return null;
    }
}
