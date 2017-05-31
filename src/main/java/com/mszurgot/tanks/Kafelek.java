package com.mszurgot.tanks;

import java.awt.Image;

public abstract class Kafelek{
    protected  Image image;
    protected int gridX, gridY;

    public Kafelek(int gridX, int gridY) {
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
