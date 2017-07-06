/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mszurgot.tanks.components;

import com.mszurgot.tanks.collision.CollidableTile;

import javax.swing.*;

public class SteelTile extends CollidableTile {

    public SteelTile(int gridX, int gridY) {
        super(gridX, gridY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/steel-tile.png"));
        image = ii.getImage();
    }

    @Override
    public void collideAction() {
        //TODO
    }
}
