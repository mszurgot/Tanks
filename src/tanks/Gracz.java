package tanks;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Gracz extends Pojazd {

    public Gracz(int gridX, int gridY, int reload, int missileSpeed) {
        super(gridX, gridY, reload, missileSpeed);
        this.tankNumber = 1;
        initImage();
    }
    
    @Override
    protected void initImage() {
        ImageIcon ii;
        this.imageSrc[0]="images/tank" + this.tankNumber + "up.png";
        this.imageSrc[1]="images/tank" + this.tankNumber + "down.png";
        this.imageSrc[2]="images/tank" + this.tankNumber + "right.png";
        this.imageSrc[3]="images/tank" + this.tankNumber + "left.png";
        for (int i = 0; i < 4; i++) {
            ii = new ImageIcon(this.getClass().getResource(imageSrc[i]).getPath());
            
            imageTab[i] = ii.getImage();
        }
        displayedImage = imageTab[0];
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire(this);
        }

        if ((key == KeyEvent.VK_LEFT) && !ruchWGore ) {
            if (kierunek == LEWO) {
                if (!ruchWLewo && !ruchWDol && TabKolizjiSingleton.getInstance().getTabKolizji(gridX - 1, gridY)==false && TabKolizjiSingleton.getInstance().getTabKolizji(gridX -1, gridY + 1)==false) {
                    ruchWLewo = true;
                }
            } else {
                kierunek = LEWO;
                displayedImage = imageTab[3];
            }
        } else if ((key == KeyEvent.VK_RIGHT) && !ruchWGore && !ruchWDol ) {
            if (kierunek == PRAWO) {
                if (!ruchWPrawo && TabKolizjiSingleton.getInstance().getTabKolizji(gridX +2, gridY)==false && TabKolizjiSingleton.getInstance().getTabKolizji(gridX + 2, gridY + 1)==false) {
                    ruchWPrawo = true;
                }
            } else {
                kierunek = PRAWO;
                displayedImage = imageTab[2];
            }
        } else if ((key == KeyEvent.VK_UP) && !ruchWLewo && !ruchWPrawo ) {
            if (kierunek == GORA) {
                if (!ruchWGore && TabKolizjiSingleton.getInstance().getTabKolizji(gridX, gridY - 1)==false && TabKolizjiSingleton.getInstance().getTabKolizji(gridX +1, gridY - 1)==false) {
                    ruchWGore = true;
                }
            } else {
                kierunek = GORA;
                displayedImage = imageTab[0];
            }
        } else if ((key == KeyEvent.VK_DOWN) && !ruchWLewo && !ruchWPrawo ) {
            if (kierunek == DOL) {
                if (!ruchWDol && TabKolizjiSingleton.getInstance().getTabKolizji(gridX, gridY + 2)==false && TabKolizjiSingleton.getInstance().getTabKolizji(gridX +1, gridY + 2)==false) {
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
