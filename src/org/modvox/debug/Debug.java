package org.modvox.debug;

public final class Debug {

    public static void print(String message) {
        System.out.println(message);
    }

    public static void print(Exception exception) {
        exception.printStackTrace(System.out);
    }
}
