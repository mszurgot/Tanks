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
    private int missileSpeed;

    public Pocisk(int x, int y, int speed, Pojazd p) {

        /*tu bedzie trzeba zmieniac obrazki wraz ze zmiana kierunku albo zmieniac pozycje sprite'a*/
        this.kierunek = p.getKierunek();
        this.strzelajacy = p;
        this.missileSpeed = speed;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/bullet.png"));
        this.image = ii.getImage();
        this.visible = true;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
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
                y -= missileSpeed;
                if (y < 0) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
            case 2: {
                y += missileSpeed;
                if (y > Main.FRAME_HEIGHT - this.height) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
            case 3: {
                x += missileSpeed;
                if (x > Main.FRAME_WIDTH - this.width) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
            case 4: {
                x -= missileSpeed;
                if (x < 0) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
        }
    }
}
