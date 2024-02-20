package io.github.yokigroup.world;

import io.github.yokigroup.file.loader.TileLoader;
import io.github.yokigroup.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameMapTest {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private final TileLoader loader = new TileLoader();
    private GameMap map;

    @BeforeEach
    public void init() {
        this.map = new GameMapBuilderImpl()
                .changeMapDimensions(new Pair<>(WIDTH, HEIGHT))
                .putHomeTileAt(new Pair<>(2, 2))
                .changePlayerTileMapPosition(new Pair<>(2, 2))
                .build(null);
    }

    @Test
    void testGetTileAt() {
        final Set<Integer> tileIds = loader.getAll().values().stream()
                .map(a -> a.build(null).getId())
                .collect(Collectors.toSet());
        assertEquals(-1, map.getTileAt(new Pair<>(2, 2)).getId());
        for (int i = 0; i < WIDTH - 1; i++) {
            for (int j = 0; j < HEIGHT - 1; j++) {
                if (i != 2 && j != 2) {
                    assertTrue(tileIds.contains(map.getTileAt(new Pair<>(i, j)).getId()));
                }
            }
        }
    }

    @Test
    void testPlayerPosition() {
        assertEquals(new Pair<>(2, 2), map.getPlayerTileMapPosition());
        assertTrue(map.movePlayerTileMapPosition(Direction.LEFT));
        assertTrue(map.movePlayerTileMapPosition(Direction.RIGHT));
        assertTrue(map.movePlayerTileMapPosition(Direction.RIGHT));
        assertTrue(map.movePlayerTileMapPosition(Direction.LEFT));
    }
}
