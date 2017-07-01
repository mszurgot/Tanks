package com.mszurgot.tanks;

/**
 * Created by Zet on 01.07.2017.
 */
public enum Direction{
    NORTH(0), SOUTH(1), EAST(2), WEST(3);

    private int id;

    Direction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
