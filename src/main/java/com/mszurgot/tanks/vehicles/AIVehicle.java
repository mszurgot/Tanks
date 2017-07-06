/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mszurgot.tanks.vehicles;

import com.mszurgot.tanks.Board;
import com.mszurgot.tanks.Direction;

import javax.swing.*;
import java.util.Random;


/**
 *
 * @author Zet
 */
public class AIVehicle extends Vehicle {
    private final static Direction DEFAULT_RESPAWN_DIRECTION = Direction.SOUTH;

    static final long RESPAWN_TIME = 1000;
    long doRespawnu;
    boolean czyOdlicza;
    int gridXDoRespawnu, gridYDoRespawnu;
    int wylosowana = 0;
    int fireWylosowana = 0;
    boolean poPierwszymOdliczaniu = false;
    boolean odbilSie = true;
    Random random = new Random();

    public AIVehicle(int gridX, int gridY, int reload, int missileSpeed, int ileHP) {
        super(gridX, gridY, reload, missileSpeed, ileHP);
        gridXDoRespawnu = gridX;
        gridYDoRespawnu = gridY;
    }

    public void decDoRespawnu() {

        if (this.doRespawnu > 0) {
            this.doRespawnu--;
        } else {

            this.czyOdlicza = false;
            this.x = Board.getGridValue(gridXDoRespawnu);
            this.y = Board.getGridValue(gridYDoRespawnu);
            this.gridX=gridXDoRespawnu;
            this.gridY=gridYDoRespawnu;
            this.setVisible(true);
            this.hp = 3;
            direction = DEFAULT_RESPAWN_DIRECTION;
            ruchWDol = true;
            System.out.println("respawn("+gridXDoRespawnu+", "+gridYDoRespawnu+")");
        }

    }

    @Override
    protected void initImage() {
        Random rand = new Random();
        this.tankNumber = rand.nextInt(4) + 2;
        ImageIcon ii;
        this.imageSrc[0] = "/images/tank" + this.tankNumber + "up.png";
        this.imageSrc[1] = "/images/tank" + this.tankNumber + "down.png";
        this.imageSrc[2] = "/images/tank" + this.tankNumber + "right.png";
        this.imageSrc[3] = "/images/tank" + this.tankNumber + "left.png";
        for (int i = 0; i < 4; i++) {
            System.out.println(imageSrc[i]);
            ii = new ImageIcon(this.getClass().getResource(imageSrc[i]));
            imageTab[i] = ii.getImage();
        }
        displayedImage = imageTab[0];
    }

    @Override
    public void makeMove() {
        initRandomAIMoves();
        move();
    }

    private void initRandomAIMoves() {
        Random rand = new Random();
        if (poPierwszymOdliczaniu == false) {
            wylosowana = rand.nextInt(40);
            fireWylosowana = random.nextInt(50);
            poPierwszymOdliczaniu = true;
        }
        if (fireWylosowana < 1) {
            this.fire();
        }
        fireWylosowana = random.nextInt(50);
        if (odbilSie) {
            wylosowana = rand.nextInt(50);
        }
        if ((wylosowana <= 10) && !ruchWGore && !ruchWDol && !ruchWPrawo) {
            odbilSie = false;
            direction = Direction.WEST;
            displayedImage = imageTab[Direction.WEST.getId()];
            if (Board.getBoundsMatrixValue(gridX - 1, gridY) == false && Board.getBoundsMatrixValue(gridX - 1, gridY + 1) == false) {
                ruchWLewo = true;
            } else {
                odbilSie = true;
            }

        } else if ((wylosowana <= 20) && !ruchWGore && !ruchWDol && !ruchWLewo) {
            odbilSie = false;
            direction = Direction.EAST;
            displayedImage = imageTab[Direction.EAST.getId()];
            if (Board.getBoundsMatrixValue(gridX + 2, gridY) == false && Board.getBoundsMatrixValue(gridX + 2, gridY + 1) == false) {
                ruchWPrawo = true;
            } else {
                odbilSie = true;
            }
        } else if ((wylosowana <= 30) && !ruchWLewo && !ruchWPrawo && !ruchWDol) {
            odbilSie = false;
            direction = Direction.NORTH;
            displayedImage = imageTab[Direction.NORTH.getId()];
            if (Board.getBoundsMatrixValue(gridX, gridY - 1) == false && Board.getBoundsMatrixValue(gridX + 1, gridY - 1) == false) {
                ruchWGore = true;
            } else {
                odbilSie = true;
            }
        } else if ((wylosowana < 40) && !ruchWLewo && !ruchWPrawo && !ruchWGore) {
            odbilSie = false;
            direction = Direction.SOUTH;
            displayedImage = imageTab[Direction.SOUTH.getId()];
            if (Board.getBoundsMatrixValue(gridX, gridY + 2) == false && Board.getBoundsMatrixValue(gridX + 1, gridY + 2) == false) {
                ruchWDol = true;
            } else {
                odbilSie = true;
            }
        }
    }

    @Override
    protected void delete() {
        System.out.println("usuwa wroga");
        this.setVisible(false);
        this.czyOdlicza = true;
        this.doRespawnu = RESPAWN_TIME;
        decDoRespawnu();
    }

}
