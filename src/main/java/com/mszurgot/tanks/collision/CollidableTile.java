package com.mszurgot.tanks.collision;

import com.mszurgot.tanks.Board;
import com.mszurgot.tanks.components.Tile;

import java.awt.*;

@Deprecated
public abstract class CollidableTile extends Tile implements Collidable {

    private boolean visible = true;

    public CollidableTile(int gridX, int gridY) {
        super(gridX, gridY);
        Board.setBoundsMatrixValue(gridX, gridY, true);
        setVisible(true);
    }

    @Override
    public Rectangle getDimension() {
        return this.isVisible()
                ? new Rectangle(Board.getGridValue(gridX), Board.getGridValue(gridY), 20, 20)
                : new Rectangle(0, 0, 0, 0);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if (visible == false) {
            Board.setBoundsMatrixValue(this.gridX, this.gridY, visible);
            //Board.soutCollisionTable();
            visible = false;
        } else {
            this.visible = true;
            Board.setBoundsMatrixValue(this.gridX, this.gridY, visible);
        }
    }

}
