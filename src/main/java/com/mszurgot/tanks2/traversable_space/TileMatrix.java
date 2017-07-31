package com.mszurgot.tanks2.traversable_space;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zet on 30.07.2017.
 */
public class TileMatrix implements ITraversableTileSpace {
    private final Map<Coordinates2D, TileNode> tileNodesByCoordinatesMap;

    private TileMatrix(Map<Coordinates2D, TileNode> tileNodesByCoordinatesMap) {
        this.tileNodesByCoordinatesMap = tileNodesByCoordinatesMap;
    }

    public TileNode getTile(Coordinates2D coordinates) {
        return tileNodesByCoordinatesMap.get(coordinates);
    }


    //*************** BUILDER *****************
    public static class Builder {
        private final static int MAX_NODES_IN_ROW = 200;
        private final static int MAX_NODES_IN_COLUMN = 200;

        private final TileNode[][] tileNodesMatrix;
        private final int nodesInRow;
        private final int nodesInColumn;
        private final int expectedTilesQunatity;
        private int actualTilesQunatity;

        public Builder(int nodesInRow, int nodesInColumn) {
            // validation
            if (nodesInRow < 0 || nodesInColumn < 0) throw new IllegalArgumentException("Only positive values are valid");
            if(nodesInRow > MAX_NODES_IN_ROW) throw new IllegalArgumentException("Too many rows");
            if(nodesInColumn > MAX_NODES_IN_COLUMN) throw new IllegalArgumentException("Too many columns");

            // construct
            tileNodesMatrix = new TileNode[nodesInColumn][nodesInRow];
            this.nodesInRow = nodesInRow;
            this.nodesInColumn = nodesInColumn;
            expectedTilesQunatity = nodesInColumn * nodesInRow;
            actualTilesQunatity = 0;
        }

        public Builder withNode(@NotNull TileNode node){
            if(node == null) throw new IllegalArgumentException("Node and coordinates shouldn't be null");
            Coordinates2D coordinates = node.getCoordinates();
            if(coordinates.getX() > nodesInRow || coordinates.getY() > nodesInColumn) throw new IllegalArgumentException("Out of matrix tile");
            if (tileNodesMatrix[coordinates.getY()][coordinates.getX()] != null) actualTilesQunatity++;
            tileNodesMatrix[coordinates.getY()][coordinates.getX()] = node;
            return this;
        }

        public TileMatrix build(){
            if(actualTilesQunatity != expectedTilesQunatity) throw new IllegalArgumentException("Not enough Tiles to build Matrix");
            return new TileMatrix(makeTileNodesByCoordinatesMapAndConnectAllTilesWithAllItsNeighbors());
        }

        @NotNull
        private Map<Coordinates2D, TileNode> makeTileNodesByCoordinatesMapAndConnectAllTilesWithAllItsNeighbors() {
            Map<Coordinates2D, TileNode> coordinatesToNodesMap = new HashMap<>();
            for (int rowNumber = 0 ; rowNumber <= nodesInRow ; rowNumber++){
                for (int columnNumber = 0 ; columnNumber <= nodesInColumn ; columnNumber++){
                    TileNode tileToPut = tileNodesMatrix[rowNumber][columnNumber];
                    connectTileWithAllItsNeighbors(rowNumber, columnNumber, tileToPut);
                    coordinatesToNodesMap.put(new Coordinates2D(rowNumber, columnNumber), tileToPut);
                }
            }
            return coordinatesToNodesMap;
        }

        private void connectTileWithAllItsNeighbors(int rowNumber, int columnNumber, TileNode tileToPut) {
            if (columnNumber > 0) connectTileWithNeighborTile(tileToPut, tileNodesMatrix[rowNumber][columnNumber - 1], Direction.WEST);
            if (columnNumber < nodesInColumn) connectTileWithNeighborTile(tileToPut, tileNodesMatrix[rowNumber][columnNumber + 1], Direction.EAST);
            if (rowNumber > 0) connectTileWithNeighborTile(tileToPut, tileNodesMatrix[rowNumber - 1][columnNumber], Direction.NORTH);
            if (rowNumber < nodesInRow) connectTileWithNeighborTile(tileToPut, tileNodesMatrix[rowNumber + 1][columnNumber], Direction.SOUTH);
        }

        private void connectTileWithNeighborTile(TileNode tile, TileNode neighborTile, Direction directionOfNeighborTile){
            tile.addNeighbor(directionOfNeighborTile, neighborTile);
            neighborTile.addNeighbor(directionOfNeighborTile.getOppositeDirection(), tile);
        }
    }

}
