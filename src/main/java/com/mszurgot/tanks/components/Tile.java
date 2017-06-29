package com.mszurgot.tanks.components;

import java.awt.Image;

public abstract class Tile {
    protected  Image image;
    protected int gridX, gridY;

    public Tile(int gridX, int gridY) {
         this.gridX = gridX;
         this.gridY = gridY;
    }

    public Image getImage() {
        return image;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

}
