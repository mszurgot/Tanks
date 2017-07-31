package com.mszurgot.tanks2.traversable_space;

import com.mszurgot.tanks2.traversable_space.tiles.ITile;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zet on 30.07.2017.
 */
public class TileNode {
    @NotNull private ITile tile;
    private Coordinates2D coordinates;
    private Map<Direction, TileNode> neighbors = new HashMap<>();


    public TileNode(@NotNull ITile tile, Coordinates2D coordinates) {
        if (tile == null || coordinates.getX() < 0 || coordinates.getY() < 0) throw new IllegalArgumentException();
        this.tile = tile;
        this.coordinates = coordinates;
    }


    public @Nullable TileNode getNeighbor(Direction direction){
        return neighbors.get(direction);
    }

    public void addNeighbor(@NotNull Direction direction, @NotNull TileNode tile){
        neighbors.put(direction, tile);
    }

    public ITile getTile() {
        return tile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TileNode tileNode = (TileNode) o;

        return coordinates.equals(tileNode.coordinates);
    }

    @Override
    public int hashCode() {
        return coordinates.hashCode();
    }

    public Coordinates2D getCoordinates() {
        return coordinates;
    }
}
