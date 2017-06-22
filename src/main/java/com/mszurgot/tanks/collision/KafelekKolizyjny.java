package com.mszurgot.tanks.collision;

import com.mszurgot.tanks.Board;
import com.mszurgot.tanks.components.Kafelek;

import java.awt.Rectangle;

public abstract class KafelekKolizyjny extends Kafelek implements IKolizyjne {

    private boolean visible;

    public KafelekKolizyjny(int gridX, int gridY) {
        super(gridX, gridY);
        TabKolizjiSingleton.setTabKolizji(gridX, gridY, true);
        setVisible(true);
    }

    @Override
    public Rectangle getWymiary() {
        return this.isVisible()
                ? new Rectangle(Board.getGridValue(gridX), Board.getGridValue(gridY), 20, 20)
                : new Rectangle(0, 0, 0, 0);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if (visible == false) {
            TabKolizjiSingleton.setTabKolizji(this.gridX, this.gridY, visible);
            //Board.soutCollisionTable();
            visible = false;
        } else {
            this.visible = true;
            TabKolizjiSingleton.setTabKolizji(this.gridX, this.gridY, visible);
        }
    }

}
