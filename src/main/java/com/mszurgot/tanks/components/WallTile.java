package com.mszurgot.tanks.components;

import com.mszurgot.tanks.collision.CollidableTile;

import javax.swing.ImageIcon;

public class WallTile extends CollidableTile {

    public WallTile(int gridX, int gridY) {
        super(gridX, gridY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/wall-tile.png"));
        image = ii.getImage();
    }

    @Override
    public void collideAction() {
        //TODO
    }
}
