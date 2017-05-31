/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mszurgot.tanks.components;

import com.mszurgot.tanks.collision.KafelekKolizyjny;

import javax.swing.ImageIcon;

public class Zelazo extends KafelekKolizyjny {

    public Zelazo(int gridX, int gridY) {
        super(gridX, gridY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/zelazo.png"));
        image = ii.getImage();
    }
 
}
