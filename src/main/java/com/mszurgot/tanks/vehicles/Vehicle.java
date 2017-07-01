package com.mszurgot.tanks.vehicles;

import com.mszurgot.tanks.Board;
import com.mszurgot.tanks.Direction;
import com.mszurgot.tanks.collision.Collidable;
import com.mszurgot.tanks.collision.Bullet;
import com.mszurgot.tanks.WindowFrame;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import static com.mszurgot.tanks.Direction.NORTH;
import static com.mszurgot.tanks.Direction.SOUTH;

/**
 *
 * @author Zet
 */
public abstract class Vehicle implements Collidable/*, IDrawable*/ {
    private final static int DEFAULT_VEHICLE_WIDTH = 40;
    private final static int DEFAULT_VEHICLE_HEIGHT = 40;

//    public static final int GORA = 0;
//    public static final int DOL = 1;
//    public static final int PRAWO = 2;
//    public static final int LEWO = 3;
    protected static final int V = 1;
    protected int hp = 3;
    protected int tankNumber;
    protected String[] imageSrc = new String[4];
    protected Image[] imageTab = new Image[4];// TODO obrazki wyciagane sa po id - zmienic jakos
    protected boolean ruchWPrawo;
    protected boolean ruchWLewo;
    protected boolean ruchWGore;
    protected boolean ruchWDol;
    protected boolean czyKolizjaRuchu;
    protected Direction direction;
    protected int dx;
    protected int dy;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int gridX;
    protected int gridY;
    protected int maxHP;
    protected boolean visible;
    protected ArrayList<Bullet> bullets;
    protected int missileSpeed;
    protected Image displayedImage;
    protected int reloadTimer;
    protected int reloadTime;



    public Vehicle(int gridX, int gridY, int reload, int missileSpeed, int ileHP) {
        tankNumber = 3;
        initImage();
        width = DEFAULT_VEHICLE_WIDTH;
        height = DEFAULT_VEHICLE_HEIGHT;
        direction = NORTH;
        bullets = new ArrayList();
        visible = true;
        this.x = Board.getGridValue(gridX);
        this.y = Board.getGridValue(gridY);
        this.gridX = gridX;
        this.gridY = gridY;
        this.reloadTime = reload;
        this.missileSpeed = missileSpeed;
        this.hp=ileHP;
        this.maxHP = ileHP;
    }

    protected void initImage() {
    }

    public void moveBullets(){
        Iterator<Bullet> ms = this.getBullets().iterator();
        while (ms.hasNext()){
            Bullet m = ms.next();
            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove();
            }
        }
    }

    public void collideAction() {
        //TODO
    }

    public void move() {
        //System.out.println("x:" + x + " y:" + y + " dx:" + dx + " dy:" + dy + " gridX:" + gridX + " gridY:" + gridY + " ruchWPrawo:" + ruchWPrawo + " ruchWLewo:" + ruchWLewo + " ruchWGore:" + ruchWGore + " ruchWDol:" + ruchWDol);
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
        czyKolizjaRuchu = false;
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
        } else if (x > (WindowFrame.DEFAULT_WINDOW_WIDTH - this.width)) {
            x = WindowFrame.DEFAULT_WINDOW_WIDTH - this.width;
        }
        if (y < 0) {
            y = 0;
        } else if (y > (WindowFrame.DEFAULT_WINDOW_HEIGHT - this.height)) {
            y = WindowFrame.DEFAULT_WINDOW_HEIGHT - this.height;
        }
    }

    public abstract void makeMove();
    
    public void decHP() {
        --hp;
        System.out.println(this.getClass().getSimpleName()+" decHP");
        if (hp < 1) {
            delete();
            //setVisible(false);
        }

    }

    public void decReloadTimer() {
        --reloadTimer;
    }

    public Image getImage() {
        return displayedImage;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    
    public void deletePociski(ArrayList<Bullet> t){
        bullets.removeAll(t);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public Rectangle getDimension() {
        if(direction == NORTH || direction == SOUTH)
        return new Rectangle(x+10, y, 20, 40);
        else return new Rectangle(x, y+10, 40, 20);
            
    }

    public void fire() {
        if (reloadTimer <= 0) {
            switch (direction) {
                case NORTH: {
                    bullets.add(new Bullet(x + 18, y, missileSpeed, this));
                    break;
                }
                case SOUTH: {
                    bullets.add(new Bullet(x + 18, y + 36, missileSpeed, this));
                    break;
                }
                case EAST: {
                    bullets.add(new Bullet(x + 36, y + 18, missileSpeed, this));
                    break;
                }
                case WEST: {
                    bullets.add(new Bullet(x, y + 18, missileSpeed, this));
                    break;
                }
            }
            reloadTimer = reloadTime;

        }
    }

    protected abstract void delete();



}
