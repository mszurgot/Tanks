package com.mszurgot.tanks;

import java.awt.Rectangle;

public abstract class KafelekKolizyjny extends Kafelek implements IKolizyjne {

    private boolean visible;

    public KafelekKolizyjny(int gridX, int gridY) {
        super(gridX, gridY);
        TabKolizjiSingleton.getInstance().setTabKolizji(gridX, gridY, true);
        setVisible(true);
    }

    @Override
    public Rectangle getWymiary() {
        if (this.isVisible()) {
            return new Rectangle(Board.getGridValue(gridX), Board.getGridValue(gridY), 20, 20);
        } else {
            return new Rectangle(0, 0, 0, 0);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    void setVisible(boolean visible) {
        if (visible == false) {
            TabKolizjiSingleton.getInstance().setTabKolizji(this.gridX, this.gridY, visible);
            //Board.soutCollisionTable();
            visible = false;
        } else {
            this.visible = true;
            TabKolizjiSingleton.getInstance().setTabKolizji(this.gridX, this.gridY, visible);
        }
    }

}
