package tanks;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Pocisk implements ICollidable {

    private int x, y;
    private Image image;
    boolean visible;
    private int width, height;
    private int kierunek;
    private Pojazd strzelajacy;
    private final int MISSILE_SPEED = 2;

    public Pocisk(int x, int y, Pojazd p) {

        /*tu bedzie trzeba zmieniac obrazki wraz ze zmiana kierunku albo zmieniac pozycje sprite'a*/
        kierunek = p.getKierunek();
        strzelajacy = p;
        ImageIcon ii = null;
        switch (kierunek) {
            case 1: {
                ii = new ImageIcon(this.getClass().getResource("pociskGora.png"));
                break;
            }
            case 2: {
                ii = new ImageIcon(this.getClass().getResource("pociskDol.png"));
                break;
            }
            case 3: {
                ii = new ImageIcon(this.getClass().getResource("pociskPrawo.png"));
                break;
            }
            case 4: {
                ii = new ImageIcon(this.getClass().getResource("pociskLewo.png"));
                break;
            }
        }
        image = ii.getImage();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.x = x;
        this.y = y;
    }

    @Override
    public void collide(Object x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getWymiary() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        switch (kierunek) {
            case 1: {
                y -= MISSILE_SPEED;
                if (y < 0) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
            case 2: {
                y += MISSILE_SPEED;
                if (y > Main.FRAME_HEIGHT - this.height) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
            case 3: {
                x += MISSILE_SPEED;
                if (x > Main.FRAME_WIDTH - this.width) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
            case 4: {
                x -= MISSILE_SPEED;
                if (x < 0) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
        }
    }
}
