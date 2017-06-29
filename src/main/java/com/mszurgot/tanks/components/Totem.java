/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mszurgot.tanks.components;

import com.mszurgot.tanks.Board;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Zet
 */
public class Totem extends WallTile {

    public Totem(int gridX, int gridY) {
        super(gridX, gridY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/totem.png"));
        image = ii.getImage();
    }
    
    @Override
    public Rectangle getDimension(){
        return new Rectangle(Board.getGridValue(gridX), Board.getGridValue(gridY), 40, 40);
    }
}
