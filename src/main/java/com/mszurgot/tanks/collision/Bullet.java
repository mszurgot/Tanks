package com.mszurgot.tanks.collision;

import com.mszurgot.tanks.WindowFrame;
import com.mszurgot.tanks.vehicles.Vehicle;

import java.awt.*;
import javax.swing.ImageIcon;

public class Bullet implements Collidable/*, IDrawable*/ {

    private int x, y;
    private Image image;
    boolean visible;
    private int width, height;
    private int direction;
    private Vehicle shooter;
    private int missileSpeed;

    public Bullet(int x, int y, int speed, Vehicle p) {

        /*tu bedzie trzeba zmieniac obrazki wraz ze zmiana kierunku albo zmieniac pozycje sprite'a*/
        this.direction = p.getKierunek();
        this.shooter = p;
        this.missileSpeed = speed;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/bullet.png"));
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
    public Rectangle getDimension() {
        return new Rectangle(x-9, y-10, 20,  20);
    }

    public void move() {
        switch (direction) {
            case Vehicle.GORA: {
                y -= missileSpeed;
                if (y < 0) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //shooter.doKolejki(this);
                }
                break;
            }
            case Vehicle.DOL: {
                y += missileSpeed;
                if (y > WindowFrame.DEFAULT_WINDOW_WIDTH - this.height) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //shooter.doKolejki(this);
                }
                break;
            }
            case Vehicle.PRAWO: {
                x += missileSpeed;
                if (x > WindowFrame.DEFAULT_WINDOW_HEIGHT - this.width) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //shooter.doKolejki(this);
                }
                break;
            }
            case Vehicle.LEWO: {
                x -= missileSpeed;
                if (x < 0) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //shooter.doKolejki(this);
                }
                break;
            }
        }
    }
}
