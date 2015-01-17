package tanks;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Pojazd implements ICollidable {

    private static final int GORA = 1;
    private static final int DOL = 2;
    private static final int PRAWO = 3;
    private static final int LEWO = 4;
    private int tankNumber = 3;
    private String[] imageSrc = {"images/tank"+tankNumber+"up.png", "images/tank"+tankNumber+"down.png", "images/tank"+tankNumber+"right.png", "images/tank"+tankNumber+"left.png"};
    private Image[] imageTab = new Image[4];
    private boolean ruchWPrawo, ruchWLewo, ruchWGore, ruchWDol;
    private int kierunek;
    private int dx, dy, x, y, width, height, gridX, gridY;
    private final static int V = 3;
    private boolean visible;
    private ArrayList missiles;
    private int missileSpeed;
    private Image displayedImage;
    private int reloadTimer;
    private int reloadTime;

    public Pojazd(int gridX, int gridY, int reload, int missileSpeed) {

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

    // chyba tak zrobimy kolizje
    @Override
    public void collide(Object x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void move() {

        //System.out.println("x:" + x + " y:" + y + " dx:" + dx + " dy:" + dy + " gridX:" + gridX + " gridY:" + gridY + " ruchWPrawo:" + ruchWPrawo + " ruchWLewo:" + ruchWLewo + " ruchWGore:" + ruchWGore + " ruchWDol:" + ruchWDol);
        if (ruchWLewo) {
            if (x > Board.getGridValue(gridX - 1)) {//dodac sprawdzenie warunku czy wcisniety przycisk?
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

    public Rectangle getWymiary() {
        return new Rectangle(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
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

    public void fire() {
        if (reloadTimer <= 0) {
            switch (kierunek) {
                case GORA: {
                    missiles.add(new Pocisk(x + 18, y, missileSpeed, this));
                    reloadTimer = reloadTime;
                    break;
                }
                case DOL: {
                    missiles.add(new Pocisk(x + 18, y + 36, missileSpeed, this));
                    reloadTimer = reloadTime;
                    break;
                }
                case PRAWO: {
                    missiles.add(new Pocisk(x + 36, y + 18, missileSpeed, this));
                    reloadTimer = reloadTime;
                    break;
                }
                case LEWO: {
                    missiles.add(new Pocisk(x, y + 18, missileSpeed, this));
                    reloadTimer = reloadTime;
                    break;
                }
            }
        }
    }
}
