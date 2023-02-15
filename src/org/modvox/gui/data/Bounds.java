package org.modvox.gui.data;

public final class Bounds {

    public final int x;
    public final int y;
    public final int width;
    public final int height;

    public Bounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean contains(Position position) {
        return position.x >= x &&
                position.x < x + width &&
                position.y >= y &&
                position.y < y + height;
    }
}
