/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import static tanks.Pojazd.LEWO;

/**
 *
 * @author Zet
 */
public class Wrog extends Pojazd{

    public Wrog(int gridX, int gridY, int reload, int missileSpeed) {
        super(gridX, gridY, reload, missileSpeed);
    }
    
    @Override
    protected void initImage(){
        Random rand = new Random(5);
        this.tankNumber = rand.nextInt()+1;
        ImageIcon ii;
        this.imageSrc[0]="images/tank" + tankNumber + "up.png";
        this.imageSrc[1]="images/tank" + tankNumber + "down.png";
        this.imageSrc[2]="images/tank" + tankNumber + "right.png";
        this.imageSrc[3]="images/tank" + tankNumber + "left.png";
        for (int i = 0; i < 4; i++) {
            System.out.println(this.getClass().getResource(imageSrc[i]));
            ii = new ImageIcon(this.getClass().getResource(imageSrc[i]));
            imageTab[i] = ii.getImage();
        }
        displayedImage = imageTab[0];
    };
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Q) {
            fire(this);
        }

        if ((key == KeyEvent.VK_A) && !ruchWGore && !ruchWDol) {
            if (kierunek == LEWO) {
                if (!ruchWLewo) {
                    ruchWLewo = true;
                }
            } else {
                kierunek = LEWO;
                displayedImage = imageTab[3];
            }
        } else if ((key == KeyEvent.VK_D) && !ruchWGore && !ruchWDol) {
            if (kierunek == PRAWO) {
                if (!ruchWPrawo) {
                    ruchWPrawo = true;
                }
            } else {
                kierunek = PRAWO;
                displayedImage = imageTab[2];
            }
        } else if ((key == KeyEvent.VK_W) && !ruchWLewo && !ruchWPrawo) {
            if (kierunek == GORA) {
                if (!ruchWGore) {
                    ruchWGore = true;
                }
            } else {
                kierunek = GORA;
                displayedImage = imageTab[0];
            }
        } else if ((key == KeyEvent.VK_S) && !ruchWLewo && !ruchWPrawo) {
            if (kierunek == DOL) {
                if (!ruchWDol) {
                    ruchWDol = true;
                }
            } else {
                kierunek = DOL;
                displayedImage = imageTab[1];
            }
        }
    }

    
    @Override
    public void makeMove() {   
       // initRandomAIMoves();
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
