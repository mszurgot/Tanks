/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks.kafelki;

/**
 *
 * @author Zet
 */
public abstract class Przeszkody extends Kafelek{
    
    /*
    Dziwne zjawisko. Konstruktor w klasie abstrakcyjnej wymuszony przez IDE,
    pewnie dlatego że dziedziczymy po klasie nie abstrakcyjnej;
    */
    public Przeszkody(int gridX, int gridY) {
        super(gridX, gridY);
    }

    
}
