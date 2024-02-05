package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileImpl;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TileShapeTest {

    @Test
    void getTiles() {
        final TileShape tileShape = new TileShapeImpl(Set.of(new TileImpl(), new TileImpl()), Set.of(TileShape.TileDirections.UP, TileShape.TileDirections.DOWN));
        final WeightedPool<Tile> tileSet = tileShape.getTiles();
        assertEquals(2, tileSet.size());
        tileSet.removeRandomizedElement();
        tileSet.removeRandomizedElement();
        assertEquals(2, tileShape.getTiles().size());
    }

    @Test
    void getPossibleDirections() {
        final TileShape tileShape1 = new TileShapeImpl(Set.of(), Set.of(TileShape.TileDirections.UP, TileShape.TileDirections.DOWN));
        final TileShape tileShape2 = new TileShapeImpl(Set.of(), Set.of(TileShape.TileDirections.UP, TileShape.TileDirections.RIGHT));
        final TileShape tileShape3 = new TileShapeImpl(Set.of(), Set.of(TileShape.TileDirections.UP, TileShape.TileDirections.RIGHT, TileShape.TileDirections.DOWN));
        assertEquals(Set.of(TileShape.TileDirections.UP, TileShape.TileDirections.DOWN), tileShape1.getPossibleDirections());
        assertEquals(Set.of(TileShape.TileDirections.UP, TileShape.TileDirections.RIGHT), tileShape2.getPossibleDirections());
        assertEquals(Set.of(TileShape.TileDirections.UP, TileShape.TileDirections.RIGHT, TileShape.TileDirections.DOWN), tileShape3.getPossibleDirections());
    }
}