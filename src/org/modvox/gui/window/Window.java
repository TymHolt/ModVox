package org.modvox.gui.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.modvox.data.StageableChangeList;
import org.modvox.gui.data.Position;
import org.modvox.gui.events.WindowKeyEvent;
import org.modvox.gui.events.WindowMouseButtonEvent;
import org.modvox.gui.events.WindowRenderEvent;
import org.modvox.gui.input.InputAction;
import org.modvox.gui.input.Key;
import org.modvox.gui.input.MouseButton;

import java.awt.*;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

public class Window {

    private static StageableChangeList<Window> openedWindowList = new StageableChangeList<>();

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
        return openedWindowList.size() > 0;
    }

    public static boolean needsUpdate() {
        return hasOpenWindows() || openedWindowList.hasStagedChanges();
    }

    public static void updateAll() {
        GLFW.glfwPollEvents();

        for(Window window : openedWindowList) {
            if(GLFW.glfwWindowShouldClose(window.glfwHandle))
                openedWindowList.stageRemove(window);

            window.update();
            window.render();
        }

        openedWindowList.executeStagedChanges();
    }

    private static Dimension getScreenDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    private final StageableChangeList<WindowEventListener> eventListenerList = new StageableChangeList<>();
    private final long glfwHandle;
    private long lastRenderTime = System.currentTimeMillis();

    public Window(int x, int y, int width, int height, String title, boolean resizeable) {
        openedWindowList.stageAdd(this);

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, resizeable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        glfwHandle = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        GLFW.glfwMakeContextCurrent(glfwHandle);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(glfwHandle);
        GL.createCapabilities();

        setPosition(x, y);

        GLFW.glfwSetKeyCallback(glfwHandle, new WindowKeyCallback(this));
        GLFW.glfwSetMouseButtonCallback(glfwHandle, new WindowMouseButtonCallback(this));
    }

    public Window() {
        this(0, 0, getScreenDimension().width / 2, getScreenDimension().height / 2, "Window", true);
    }

    public void addEventListener(WindowEventListener listener) {
        eventListenerList.stageAdd(listener);
    }

    public void removeEventListener(WindowEventListener listener) {
        eventListenerList.stageRemove(listener);
    }

    public void center() {
        Dimension screenDimension = getScreenDimension();
        Dimension windowDimension = getWindowDimension();
        setPosition((screenDimension.width - windowDimension.width) / 2, (screenDimension.height - windowDimension.height) / 2);
    }

    public void setTitle(String title) {
        GLFW.glfwSetWindowTitle(glfwHandle, title);
    }

    public void setPosition(int x, int y) {
        GLFW.glfwSetWindowPos(glfwHandle, x, y);
    }

    public Dimension getWindowDimension() {
        int width;
        int height;

        try(MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer widthBuffer = stack.mallocInt(1);
            IntBuffer heightBuffer = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(glfwHandle, widthBuffer, heightBuffer);

            width = widthBuffer.get(0);
            height = heightBuffer.get(0);
        }

        return new Dimension(width, height);
    }

    public Dimension getFramebufferSize() {
        int width;
        int height;

        try(MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer widthBuffer = stack.mallocInt(1);
            IntBuffer heightBuffer = stack.mallocInt(1);

            GLFW.glfwGetFramebufferSize(glfwHandle, widthBuffer, heightBuffer);

            width = widthBuffer.get(0);
            height = heightBuffer.get(0);
        }

        return new Dimension(width, height);
    }

    public Position getMousePosition() {
        int x;
        int y;

        try(MemoryStack stack = MemoryStack.stackPush()) {
            DoubleBuffer xBuffer = stack.mallocDouble(1);
            DoubleBuffer yBuffer = stack.mallocDouble(1);

            GLFW.glfwGetCursorPos(glfwHandle, xBuffer, yBuffer);

            x = (int) xBuffer.get(0);
            y = (int) yBuffer.get(0);
        }

        return new Position(x, y);
    }

    public void render() {
        GLFW.glfwMakeContextCurrent(glfwHandle);

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        long currentTime = System.currentTimeMillis();
        long deltaTimeAbsolute = currentTime - lastRenderTime;
        lastRenderTime = currentTime;

        for(WindowEventListener listener : eventListenerList)
            listener.onWindowRenderEvent(new WindowRenderEvent(this, (float) deltaTimeAbsolute / 1000.0f));

        GLFW.glfwSwapBuffers(glfwHandle);
    }

    public void update() {
        eventListenerList.executeStagedChanges();
    }

    private class WindowKeyCallback extends GLFWKeyCallback {

        final Window window;

        WindowKeyCallback(Window window) {
            super();
            this.window = window;
        }

        @Override
        public void invoke(long glfwWindow, int glfwKey, int glfwScancode, int glfwAction, int glfwMods) {
            Key key = Key.fromGLFWHandle(glfwKey);
            InputAction action = InputAction.fromGLFWHandle(glfwAction);

            if(key != null && action != null)
                for(WindowEventListener listener : eventListenerList)
                    listener.onWindowKeyEvent(new WindowKeyEvent(window, key, action));
        }
    }

    private class WindowMouseButtonCallback extends GLFWMouseButtonCallback {

        final Window window;

        WindowMouseButtonCallback(Window window) {
            super();
            this.window = window;
        }

        @Override
        public void invoke(long glfwWindow, int glfwButton, int glfwAction, int glfwMods) {
            Position position = window.getMousePosition();
            MouseButton button = MouseButton.fromGLFWHandle(glfwButton);
            InputAction action = InputAction.fromGLFWHandle(glfwAction);

            if(button != null && action != null)
                for(WindowEventListener listener : eventListenerList)
                    listener.onWindowMouseButtonEvent(new WindowMouseButtonEvent(window, button, action, position));
        }
    }
}
