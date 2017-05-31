package com.mszurgot.tanks.components;

import javax.swing.ImageIcon;

public class Krzak extends Kafelek{
    
    public Krzak(int gridX, int gridY) {
        super(gridX, gridY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/bush.png"));
        image = ii.getImage();
    }
    
}
