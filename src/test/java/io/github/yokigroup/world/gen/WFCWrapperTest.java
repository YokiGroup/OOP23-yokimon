package io.github.yokigroup.world.gen;

import io.github.yokigroup.file.loader.TileShapeLoader;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.tile.TileBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WFCWrapperTest {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final TileShapeLoader TILE_SHAPE_LOADER = new TileShapeLoader();
    private Set<TileBuilder> allTiles;
    private WFCWrapper wfcWrapper;

    @BeforeEach
    public void init() {
        final Set<TileShape> allShapes = TILE_SHAPE_LOADER.getAll();
        allTiles = allShapes.stream()
                .map(s -> s.getTiles().getEntries())
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
        this.wfcWrapper = new WFCWrapperImpl(new Pair<>(WIDTH, HEIGHT), allShapes);
    }

    @Test
    void runWFCTest() {
        this.wfcWrapper.runWFC();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final Pair<Integer, Integer> pos = new Pair<>(i, j);
                assertTrue(allTiles.contains(wfcWrapper.getTileAt(pos)));
            }
        }
    }

    @Test
    void setStaticTile() {
        final Pair<Integer, Integer> staticPos = new Pair<>(2, 2);
        final Set<Direction> staticTile = Set.of(Direction.UP, Direction.LEFT);
        this.wfcWrapper.setStaticTile(staticPos, staticTile);
        this.wfcWrapper.runWFC();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final Pair<Integer, Integer> pos = new Pair<>(i, j);
                assertTrue(allTiles.contains(wfcWrapper.getTileAt(pos)));
            }
        }
        assertEquals(staticTile, wfcWrapper.getTileAt(staticPos).build(null).getAdjacencies());
    }
}
