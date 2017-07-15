package com.mszurgot.tanks.collision;

import com.mszurgot.tanks.Direction;
import com.mszurgot.tanks.WindowFrame;
import com.mszurgot.tanks.vehicles.Vehicle;

import java.awt.*;
import javax.swing.ImageIcon;

@Deprecated
public class Bullet implements Collidable/*, IDrawable*/ {

    private int x, y;
    private Image image;
    boolean visible;
    private int width, height;
    private Direction direction;
    private Vehicle shooter;
    private int missileSpeed;

    public Bullet(int x, int y, int speed, Vehicle p) {

        /*tu bedzie trzeba zmieniac obrazki wraz ze zmiana kierunku albo zmieniac pozycje sprite'a*/
        this.direction = p.getDirection();
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

    @Override
    public void collideAction() {
        //TODO
    }

    public void move() {
        switch (direction) {
            case NORTH: {
                y -= missileSpeed;
                if (y < 0) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //shooter.doKolejki(this);
                }
                break;
            }
            case SOUTH: {
                y += missileSpeed;
                if (y > WindowFrame.DEFAULT_WINDOW_WIDTH - this.height) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //shooter.doKolejki(this);
                }
                break;
            }
            case EAST: {
                x += missileSpeed;
                if (x > WindowFrame.DEFAULT_WINDOW_HEIGHT - this.width) {
                    visible = false; //tu zrobić obsługę przerzucania obiektu na poczatek kolejki pociskow do uzycia na obiekcie strzelajacego
                    //shooter.doKolejki(this);
                }
                break;
            }
            case WEST: {
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
