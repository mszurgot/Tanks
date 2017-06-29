package com.mszurgot.tanks.components;

import javax.swing.ImageIcon;

public class BushTile extends Tile {
    
    public BushTile(int gridX, int gridY) {
        super(gridX, gridY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/bush-tile.png"));
        image = ii.getImage();
    }
    
}
