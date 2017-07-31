package com.mszurgot.tanks2.traversable_space.tiles;

import com.mszurgot.tanks2.traversable_space.Direction;
import com.mszurgot.tanks2.traversable_space.Coordinates2D;
import com.mszurgot.tanks2.traversable_space.TileMatrix;
import com.mszurgot.tanks2.traversable_space.TileNode;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Zet on 30.07.2017.
 */
public class TileMatrixTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void TileMatrix_ThrowIllegalArgumentException_WhenUsedNegativeValueInConstructor(){
        //given
        int tilesInRow = -3; //not valid
        int tilesInColumn = 3;
        expectedException.expect(IllegalArgumentException.class);

        //when
        new TileMatrix.Builder(tilesInRow, tilesInColumn);

        //then
        fail("Expected exception not thrown");
    }

    @Test
    public void TileMatrix_ReturnTileMatrix_WhenProvidedEnoughTilesToBuilder(){
        //given
        int tilesInRow = 3;
        int tilesInColumn = 3;
        expectedException.expect(IllegalArgumentException.class);
        ITile dummyTile = new ITile(){};

        //when
        TileMatrix tileMatrix = new TileMatrix.Builder(tilesInRow, tilesInColumn)
                .withNode(new TileNode(dummyTile, new Coordinates2D(0, 0)))
                .withNode(new TileNode(dummyTile, new Coordinates2D(0, 1)))
                .withNode(new TileNode(dummyTile, new Coordinates2D(0, 2)))
                .withNode(new TileNode(dummyTile, new Coordinates2D(1, 0)))
                .withNode(new TileNode(dummyTile, new Coordinates2D(1, 1)))
                .withNode(new TileNode(dummyTile, new Coordinates2D(1, 2)))
                .withNode(new TileNode(dummyTile, new Coordinates2D(2, 0)))
                .withNode(new TileNode(dummyTile, new Coordinates2D(2, 1)))
                .withNode(new TileNode(dummyTile, new Coordinates2D(2, 2)))
                .build();

        //then
        assertNotNull(tileMatrix);
        assertSame(tileMatrix.getTile(new Coordinates2D(0, 0)).getNeighbor(Direction.SOUTH), tileMatrix.getTile(new Coordinates2D(1, 0)));
        assertSame(tileMatrix.getTile(new Coordinates2D(0, 1)).getNeighbor(Direction.NORTH), tileMatrix.getTile(new Coordinates2D(0, 0)));

    }

}