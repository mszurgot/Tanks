/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

/**
 *
 * @author Zet
 */
public class Wróg extends Pojazd{

    @Override
    public void makeMove() {   
        initRandomAIMoves();
        move();
    }
    private void initRandomAIMoves(){
         /*tu powinna być cała implementacja AI 
        W innej klasie dziedziczącej po Pojazd gracz inicjuje ruchy przy pomocy klawiszy
        zmieniajac wartosci ruchWPrawo, ruchWLewo, ruchWGore, ruchWDol, dx, dy oraz zmieniając
        wartosc kierunek stałymi GORA, DOL, PRAWO, LEWO a ponadto inicjuje strzal.
        W tej klasie wszystkie te akcje będą musiały być generowane przez AI w tym miejscu przed wykonaniem move.
        Ta metoda jest wywolywana co takt.
        */
    
    }
    
}
