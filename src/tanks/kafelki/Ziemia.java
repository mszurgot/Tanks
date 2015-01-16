/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks.kafelki;

import javax.swing.ImageIcon;

/**
 *
 * @author Zet
 */
public class Ziemia extends Kafelek{

    public Ziemia(int gridX, int gridY) {
        super(gridX, gridY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("ground.png"));
        image = ii.getImage();
    }

    
}
