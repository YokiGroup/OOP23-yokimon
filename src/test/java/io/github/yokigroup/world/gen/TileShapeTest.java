package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileShapeTest {
    private Set<Direction> tileShapeSet1;
    private Set<Direction> tileShapeSet2;
    private Set<Direction> tileShapeSet3;
    private TileShape tileShape1;
    private TileShape tileShape2;
    private TileShape tileShape3;

    @BeforeEach
    public void init() {
        tileShapeSet1 = Set.of(Direction.UP, Direction.DOWN);
        tileShapeSet2 = Set.of(Direction.UP, Direction.RIGHT);
        tileShapeSet3 = Set.of(Direction.UP, Direction.RIGHT, Direction.DOWN);
        tileShape1 = new TileShapeImpl(Set.of(), tileShapeSet1);
        tileShape2 = new TileShapeImpl(Set.of(), tileShapeSet2);
        tileShape3 = new TileShapeImpl(Set.of(), tileShapeSet3);
    }

    @Test
    void testGetTiles() {
        final Tile tile1 = new TileBuilderImpl().build(0);
        final Tile tile2 = new TileBuilderImpl().build(1);
        tileShape1 = new TileShapeImpl(Set.of(tile1, tile2), tileShapeSet1);
        final WeightedPool<Tile> tileSet = tileShape1.getTiles();
        assertEquals(2, tileSet.size());
        tileSet.removeRandomizedElement();
        tileSet.removeRandomizedElement();
        assertEquals(2, tileShape1.getTiles().size());
    }

    @Test
    void testGetPossibleDirections() {
        assertEquals(tileShapeSet1, tileShape1.getPossibleDirections());
        assertEquals(tileShapeSet2, tileShape2.getPossibleDirections());
        assertEquals(tileShapeSet3, tileShape3.getPossibleDirections());
    }
}
