package io.github.yokigroup.world.gen;

import io.github.yokigroup.world.gen.TileShape.TileDirections;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileImpl;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileShapeTest {

    @Test
    void getTiles() {
        final Set<TileDirections> tileShapeSet = Set.of(TileDirections.UP, TileDirections.DOWN);
        final TileShape tileShape = new TileShapeImpl(Set.of(new TileImpl(), new TileImpl()), tileShapeSet);
        final WeightedPool<Tile> tileSet = tileShape.getTiles();
        assertEquals(2, tileSet.size());
        tileSet.removeRandomizedElement();
        tileSet.removeRandomizedElement();
        assertEquals(2, tileShape.getTiles().size());
    }

    @Test
    void getPossibleDirections() {
        final Set<TileDirections> tileShapeSet1 = Set.of(TileDirections.UP, TileDirections.DOWN);
        final Set<TileDirections> tileShapeSet2 = Set.of(TileDirections.UP, TileDirections.RIGHT);
        final Set<TileDirections> tileShapeSet3 = Set.of(TileDirections.UP, TileDirections.RIGHT, TileDirections.DOWN);
        final TileShape tileShape1 = new TileShapeImpl(Set.of(), tileShapeSet1);
        final TileShape tileShape2 = new TileShapeImpl(Set.of(), tileShapeSet2);
        final TileShape tileShape3 = new TileShapeImpl(Set.of(), tileShapeSet3);
        assertEquals(tileShapeSet1, tileShape1.getPossibleDirections());
        assertEquals(tileShapeSet2, tileShape2.getPossibleDirections());
        assertEquals(tileShapeSet3, tileShape3.getPossibleDirections());
    }
}
