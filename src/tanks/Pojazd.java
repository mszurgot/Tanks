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
    
    private int kierunek;
    private String pojazdURL = "pojazd.png";
    private int dx;
    private int v = 1;
    private int dy;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;
    private ArrayList missiles;

    public Pojazd() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(pojazdURL));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        kierunek = GORA;
        missiles = new ArrayList();
        visible = true;
        x = 250;
        y = 250;
    }
    
    // chyba tak zrobimy kolizje
    @Override
    public void collide(Object x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 0) x = 0;
        if (y < 0)  y = 0;
        if (x > (Main.FRAME_WIDTH - this.width))  x = Main.FRAME_WIDTH - this.width;
        if (y > (Main.FRAME_HEIGHT - this.height))  y = Main.FRAME_HEIGHT - this.height;
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

    public Image getImage() {
        return image;
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
            dx = -v;
            kierunek = LEWO;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = v;
            kierunek = PRAWO;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -v;
            kierunek = GORA;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = v;
            kierunek = DOL;
        }
    }

    public void fire() {
        missiles.add(new Pocisk(x + width, y + height, this));
    }

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
    }
}
