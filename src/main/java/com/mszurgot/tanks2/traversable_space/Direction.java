package com.mszurgot.tanks2.traversable_space;

/**
 * Created by Zet on 30.07.2017.
 */

//public enum Direction {
//    NORTH, SOUTH, EAST, WEST;
//
//    private final static Map<Direction, Direction> oppositeDirections = new HashMap<>();
//
//    static{
//        oppositeDirections.put(NORTH, SOUTH);
//        oppositeDirections.put(SOUTH, NORTH);
//        oppositeDirections.put(EAST, WEST);
//        oppositeDirections.put(WEST, EAST);
//    }
//
//    public static Direction getOppositeDirection(Direction direction){
//        return oppositeDirections.get(direction);
//    }
//}

public class Direction {
    public final static Direction NORTH = new Direction();
    public final static Direction SOUTH = new Direction();
    public final static Direction EAST = new Direction();
    public final static Direction WEST = new Direction();

    private Direction oppositeDirection;

    static {
        NORTH.oppositeDirection = SOUTH;
        SOUTH.oppositeDirection = NORTH;
        EAST.oppositeDirection = WEST;
        WEST.oppositeDirection = EAST;
    }

    private Direction() {
    }

    public Direction getOppositeDirection() {
        return oppositeDirection;
    }
}
