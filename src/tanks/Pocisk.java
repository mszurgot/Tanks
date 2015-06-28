package tanks;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Pocisk implements IKolizyjne {

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

    @Override
    public Rectangle getWymiary() {
        return new Rectangle(x-9, y-10, 20,  20);
    }

    public void move() {
        switch (kierunek) {
            case Pojazd.GORA: {
                y -= missileSpeed;
                if (y < 0) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
            case Pojazd.DOL: {
                y += missileSpeed;
                if (y > Main. getInstance().getFrameWidth() - this.height) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
            case Pojazd.PRAWO: {
                x += missileSpeed;
                if (x > Main. getInstance().getFrameHeight() - this.width) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //strzelajacy.doKolejki(this);
                }
                break;
            }
            case Pojazd.LEWO: {
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
