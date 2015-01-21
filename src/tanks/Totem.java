/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Rectangle;

/**
 *
 * @author Zet
 */
public class Totem extends Mur{

    public Totem(int gridX, int gridY) {
        super(gridX, gridY);
    }
    
    @Override
    public Rectangle getWymiary(){
        return new Rectangle(Board.getGridValue(gridX), Board.getGridValue(gridY), 40, 40);
    }
}
