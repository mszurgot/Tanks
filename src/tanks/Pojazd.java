package tanks;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Zet
 */
public abstract class Pojazd implements IKolizyjne {

    protected static final int GORA = 0;
    protected static final int DOL = 1;
    protected static final int PRAWO = 2;
    protected static final int LEWO = 3;
    protected static final int V = 1;
    protected int hp =3;
    protected int tankNumber;
    protected String[] imageSrc = new String[4];
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
    protected int maxHP;
    protected boolean visible;
    protected ArrayList<Pocisk> pociski;
    protected int missileSpeed;
    protected Image displayedImage;
    protected int reloadTimer;
    protected int reloadTime;

    public Pojazd(int gridX, int gridY, int reload, int missileSpeed, int ileHP) {
        tankNumber = 3;
        initImage();
        width = 40;
        height = 40;
        kierunek = GORA;
        pociski = new ArrayList();
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
        } else if (x > (Main. getInstance().getFrameWidth() - this.width)) {
            x = Main. getInstance().getFrameWidth() - this.width;
        }
        if (y < 0) {
            y = 0;
        } else if (y > (Main. getInstance().getFrameHeight() - this.height)) {
            y = Main. getInstance().getFrameHeight() - this.height;
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

    public ArrayList<Pocisk> getPociski() {
        return pociski;
    }
    
    public void deletePociski(ArrayList<Pocisk> t){
        pociski.removeAll(t);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public Rectangle getWymiary() {
        if(kierunek == GORA || kierunek == DOL)
        return new Rectangle(x+10, y, 20, 40);
        else return new Rectangle(x, y+10, 40, 20);
            
    }

    public void fire(Pojazd p) {
        if (reloadTimer <= 0) {
            switch (kierunek) {
                case GORA: {
                    pociski.add(new Pocisk(x + 18, y, missileSpeed, p));
                    reloadTimer = reloadTime;
                    break;
                }
                case DOL: {
                    pociski.add(new Pocisk(x + 18, y + 36, missileSpeed, p));
                    reloadTimer = reloadTime;
                    break;
                }
                case PRAWO: {
                    pociski.add(new Pocisk(x + 36, y + 18, missileSpeed, p));
                    reloadTimer = reloadTime;
                    break;
                }
                case LEWO: {
                    pociski.add(new Pocisk(x, y + 18, missileSpeed, p));
                    reloadTimer = reloadTime;
                    break;
                }
            }
        }
    }

    protected abstract void delete();

}
