package tanks;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Gracz extends Pojazd {


    public Gracz(int gridX, int gridY, int reload, int missileSpeed) {

        ImageIcon ii;
        for (int i = 0; i < 4; i++) {
            ii = new ImageIcon(this.getClass().getResource(imageSrc[i]));
            imageTab[i] = ii.getImage();
        }
        width = 40;
        height = 40;
        kierunek = GORA;
        displayedImage = imageTab[0];
        missiles = new ArrayList();
        visible = true;
        this.x = Board.getGridValue(gridX);
        this.y = Board.getGridValue(gridY);
        this.gridX = gridX;
        this.gridY = gridY;
        this.reloadTime = reload;
        this.missileSpeed = missileSpeed;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire(this);
        }

        if ((key == KeyEvent.VK_LEFT) && !ruchWGore && !ruchWDol) {
            if (kierunek == LEWO) {
                if (!ruchWLewo) {
                    ruchWLewo = true;
                }
            } else {
                kierunek = LEWO;
                displayedImage = imageTab[3];
            }
        } else if ((key == KeyEvent.VK_RIGHT) && !ruchWGore && !ruchWDol) {
            if (kierunek == PRAWO) {
                if (!ruchWPrawo) {
                    ruchWPrawo = true;
                }
            } else {
                kierunek = PRAWO;
                displayedImage = imageTab[2];
            }
        } else if ((key == KeyEvent.VK_UP) && !ruchWLewo && !ruchWPrawo) {
            if (kierunek == GORA) {
                if (!ruchWGore) {
                    ruchWGore = true;
                }
            } else {
                kierunek = GORA;
                displayedImage = imageTab[0];
            }
        } else if ((key == KeyEvent.VK_DOWN) && !ruchWLewo && !ruchWPrawo) {
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
        move();
    }

}
