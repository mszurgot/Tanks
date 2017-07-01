package com.mszurgot.tanks.vehicles;

import com.mszurgot.tanks.Board;
import com.mszurgot.tanks.Direction;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player extends Vehicle {

    public Player(int gridX, int gridY, int reload, int missileSpeed, int ileHP) {
        super(gridX, gridY, reload, missileSpeed, ileHP);
        this.tankNumber = 1;
        initImage();
    }
    
    @Override
    protected void initImage() {
        ImageIcon ii;
        this.imageSrc[0]="/images/tank" + this.tankNumber + "up.png";
        this.imageSrc[1]="/images/tank" + this.tankNumber + "down.png";
        this.imageSrc[2]="/images/tank" + this.tankNumber + "right.png";
        this.imageSrc[3]="/images/tank" + this.tankNumber + "left.png";
        for (int i = 0; i < 4; i++) {
            ii = new ImageIcon(this.getClass().getResource(imageSrc[i]).getPath());
            
            imageTab[i] = ii.getImage();
        }
        displayedImage = imageTab[0];
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
        System.out.println(direction);//TODO do usunięcia

        if ((key == KeyEvent.VK_LEFT) && !ruchWGore && !ruchWDol) {
            if (direction == Direction.WEST) {
                if (!ruchWLewo && Board.getBoundsMatrixValue(gridX - 1, gridY)==false && Board.getBoundsMatrixValue(gridX -1, gridY + 1)==false) {
                    ruchWLewo = true;

                }
            } else {
                direction = Direction.WEST;
                displayedImage = imageTab[Direction.WEST.getId()];
            }
        } else if ((key == KeyEvent.VK_RIGHT) && !ruchWGore && !ruchWDol ) {
            if (direction == Direction.EAST) {
                if (!ruchWPrawo && Board.getBoundsMatrixValue(gridX +2, gridY)==false && Board.getBoundsMatrixValue(gridX + 2, gridY + 1)==false) {
                    ruchWPrawo = true;
                }
            } else {
                direction = Direction.EAST;
                displayedImage = imageTab[Direction.EAST.getId()];
            }
        } else if ((key == KeyEvent.VK_UP) && !ruchWLewo && !ruchWPrawo ) {
            if (direction == Direction.NORTH) {
                if (!ruchWGore && Board.getBoundsMatrixValue(gridX, gridY - 1)==false && Board.getBoundsMatrixValue(gridX +1, gridY - 1)==false) {
                    ruchWGore = true;
                }
            } else {
                direction = Direction.NORTH;
                displayedImage = imageTab[Direction.NORTH.getId()];
            }
        } else if ((key == KeyEvent.VK_DOWN) && !ruchWLewo && !ruchWPrawo ) {
            if (direction == Direction.SOUTH) {
                if (!ruchWDol && Board.getBoundsMatrixValue(gridX, gridY + 2)==false && Board.getBoundsMatrixValue(gridX +1, gridY + 2)==false) {
                    ruchWDol = true;
                }
            } else {
                direction = Direction.SOUTH;
                displayedImage = imageTab[Direction.SOUTH.getId()];
            }
        }
    }

    @Override
    public void makeMove() {
        move();
    }

    @Override
    protected void delete() {
        setVisible(false);
        Board.rozgrywkaTrwa=false;
    }


}
