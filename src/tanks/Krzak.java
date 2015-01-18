/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import javax.swing.ImageIcon;

/**
 *
 * @author Zet
 */
public class Krzak extends Kafelek{
    
    public Krzak(int gridX, int gridY) {
        super(gridX, gridY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/bush.png"));
        image = ii.getImage();
    }
    
}
