package tanks;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Pojazd implements ICollidable {

    public static final int GORA = 1;
    public static final int DOL = 2;
    public static final int PRAWO = 3;
    public static final int LEWO = 4;
    private String[] imageSrc = {"images/tank2up.png","images/tank2down.png", "images/tank2right.png", "images/tank2left.png"};
    private Image[] imageTab = new Image[4];
    public boolean ruchWPrawo, ruchWLewo, ruchWGore, ruchWDol;
    private int kierunek;
    private int dx, dy, x, y, width, height, gridX, gridY;
    private final static int V = 1;
    private boolean visible;
    private ArrayList missiles;
    private Image image;
            

    public Pojazd() {
        ImageIcon ii;
        for (int i = 0; i < 4; i++) {
            ii = new ImageIcon(this.getClass().getResource(imageSrc[i]));
            imageTab[i] = ii.getImage();
        }
        image = imageTab[0];
        width = 40;
        height = 40;
        kierunek = GORA;
        missiles = new ArrayList();
        visible = true;
        x = Board.getGridValue(1);
        y = Board.getGridValue(1);
        gridX = (x) / 20;
        gridY = (y) / 20;
    }

    // chyba tak zrobimy kolizje
    @Override
    public void collide(Object x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void move() {

        //System.out.println("x:" + x + " y:" + y + " dx:" + dx + " dy:" + dy + " gridX:" + gridX + " gridY:" + gridY + " ruchWPrawo:" + ruchWPrawo + " ruchWLewo:" + ruchWLewo + " ruchWGore:" + ruchWGore + " ruchWDol:" + ruchWDol);

        if (ruchWLewo) {
            if (x > Board.getGridValue(gridX - 1)) {
                dx = -V;
            } else {
                dx = 0;
                ruchWLewo = false;
                gridX = gridX - 1;
            }
        }
        if (ruchWPrawo) {
            if (x < Board.getGridValue(gridX + 1)) {
                dx = V;
            } else {
                dx = 0;
                ruchWPrawo = false;
                gridX = gridX + 1;
            }
        }
        if (ruchWGore) {
            if (y > Board.getGridValue(gridY - 1)) {
                dy = -V;
            } else {
                dy = 0;
                ruchWGore = false;
                gridY = gridY - 1;
            }
        }
        if (ruchWDol) {
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
        }
        if (y < 0) {
            y = 0;
        }
        if (x > (Main.FRAME_WIDTH - this.width)) {
            x = Main.FRAME_WIDTH - this.width;
        }
        if (y > (Main.FRAME_HEIGHT - this.height)) {
            y = Main.FRAME_HEIGHT - this.height;
        }

    }

    public Image getImage() {
        return image;
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

        if (key == KeyEvent.VK_LEFT) {
            kierunek = LEWO;
            ruchWLewo = true;
            ruchWPrawo = false;
            ruchWGore = false;
            ruchWDol = false;
            image = imageTab[3];
        }

        if (key == KeyEvent.VK_RIGHT) {
            kierunek = PRAWO;
            ruchWLewo = false;
            ruchWPrawo = true;
            ruchWGore = false;
            ruchWDol = false;
            image = imageTab[2];
        }

        if (key == KeyEvent.VK_UP) {
            kierunek = GORA;
            ruchWLewo = false;
            ruchWPrawo = false;
            ruchWGore = true;
            ruchWDol = false;
            image = imageTab[0];
        }

        if (key == KeyEvent.VK_DOWN) {
            kierunek = DOL;
            ruchWLewo = false;
            ruchWPrawo = false;
            ruchWGore = false;
            ruchWDol = true;
            image = imageTab[1];
        }
    }

    public void fire() {
        
        switch(kierunek){
            case GORA:{
                missiles.add(new Pocisk(x + 18, y, this));
                break;
            }
            case DOL:{
                missiles.add(new Pocisk(x + 18, y+36, this));
                break;
            }
            case PRAWO:{
                missiles.add(new Pocisk(x +36, y+18, this));
                break;
            }
            case LEWO:{
                missiles.add(new Pocisk(x, y+18, this));
                break;
            }
        }
    }
    /*
     public void keyReleased(KeyEvent e) {
     int key = e.getKeyCode();

     if (key == KeyEvent.VK_LEFT) {
     dx = 0;
     }

     if (key == KeyEvent.VK_RIGHT) {
     dx = 0;
     }

     if (key == KeyEvent.VK_UP) {
     dy = 0;
     }

     if (key == KeyEvent.VK_DOWN) {
     dy = 0;
     }
     }*/
}
