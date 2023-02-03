package org.modvox.gui.data;

public final class ContentBounds {

    public final int x;
    public final int y;
    public final int width;
    public final int height;

    public ContentBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean contains(ContentPosition contentPosition) {
        return contentPosition.x >= x &&
                contentPosition.x < x + width &&
                contentPosition.y >= y &&
                contentPosition.y < y + height;
    }
}
