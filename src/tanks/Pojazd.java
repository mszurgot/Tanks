package tanks;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Zet
 */

public abstract class Pojazd implements IKolizyjne {
    protected static final int GORA = 1;
    protected static final int DOL = 2;
    protected static final int PRAWO = 3;
    protected static final int LEWO = 4;
    protected static final int V = 1;
    protected int tankNumber = 3;
    protected String[] imageSrc = {"images/tank" + tankNumber + "up.png", "images/tank" + tankNumber + "down.png", "images/tank" + tankNumber + "right.png", "images/tank" + tankNumber + "left.png"};
    protected Image[] imageTab = new Image[4];
    protected boolean ruchWPrawo;
    protected boolean ruchWLewo;
    protected boolean ruchWGore;
    protected boolean ruchWDol;
    protected boolean czyKolizjaRuchu;
    protected int kierunek;
    protected int dx;
    protected int dy;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int gridX;
    protected int gridY;
    protected boolean visible;
    protected ArrayList<Pocisk> missiles;
    protected int missileSpeed;
    protected Image displayedImage;
    protected int reloadTimer;
    protected int reloadTime;

        public Pojazd(int gridX, int gridY, int reload, int missileSpeed){

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

    public void move() {
        System.out.println("x:" + x + " y:" + y + " dx:" + dx + " dy:" + dy + " gridX:" + gridX + " gridY:" + gridY + " ruchWPrawo:" + ruchWPrawo + " ruchWLewo:" + ruchWLewo + " ruchWGore:" + ruchWGore + " ruchWDol:" + ruchWDol);
        if (ruchWLewo) {
            if (x > Board.getGridValue(gridX - 1)) {
                //dodac sprawdzenie warunku czy wcisniety przycisk?
                dx = -V;
            } else {
                dx = 0;
                ruchWLewo = false;
                gridX = gridX - 1;
            }
        } else if (ruchWPrawo) {
            if (x < Board.getGridValue(gridX + 1)) {
                dx = V;
            } else {
                dx = 0;
                ruchWPrawo = false;
                gridX = gridX + 1;
            }
        } else if (ruchWGore) {
            if (y > Board.getGridValue(gridY - 1)) {
                dy = -V;
            } else {
                dy = 0;
                ruchWGore = false;
                gridY = gridY - 1;
            }
        } else if (ruchWDol) {
            if (y < Board.getGridValue(gridY + 1)) {
                dy = V;
            } else {
                dy = 0;
                ruchWDol = false;
                gridY = gridY + 1;
            }
        }
        czyKolizjaRuchu=false;
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
        } else if (x > (Main.FRAME_WIDTH - this.width)) {
            x = Main.FRAME_WIDTH - this.width;
        }
        if (y < 0) {
            y = 0;
        } else if (y > (Main.FRAME_HEIGHT - this.height)) {
            y = Main.FRAME_HEIGHT - this.height;
        }
    }
    
    public abstract void makeMove();
    
    @Override
    public void kolizja(IKolizyjne p){
    
    ////////////
    
    }

    public void decReloadTimer() {
        --reloadTimer;
    }

    public Image getImage() {
        return displayedImage;
    }

    public int getKierunek() {
        return kierunek;
    }

    public void setKierunek(int kierunek) {
        this.kierunek = kierunek;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList getMissiles() {
        return missiles;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public Rectangle getWymiary() {
        return new Rectangle(x, y, width, height);
    }

    public void fire(Pojazd p) {
        if (reloadTimer <= 0) {
            switch (kierunek) {
                case GORA:
                    {
                        missiles.add(new Pocisk(x + 18, y, missileSpeed, p));
                        reloadTimer = reloadTime;
                        break;
                    }
                case DOL:
                    {
                        missiles.add(new Pocisk(x + 18, y + 36, missileSpeed, p));
                        reloadTimer = reloadTime;
                        break;
                    }
                case PRAWO:
                    {
                        missiles.add(new Pocisk(x + 36, y + 18, missileSpeed, p));
                        reloadTimer = reloadTime;
                        break;
                    }
                case LEWO:
                    {
                        missiles.add(new Pocisk(x, y + 18, missileSpeed, p));
                        reloadTimer = reloadTime;
                        break;
                    }
            }
        }
    }
    
}
