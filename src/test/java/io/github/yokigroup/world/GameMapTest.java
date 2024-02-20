package io.github.yokigroup.world;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.file.loader.TileLoader;
import io.github.yokigroup.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameMapTest {
    private final TileLoader loader = new TileLoader();
    private GameMap map;

    @BeforeEach
    public void init() {
        this.map = new GameMapBuilderImpl()
                .changeMapDimensions(new Pair<>(5, 5))
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
        assertTrue(tileIds.contains(map.getTileAt(new Pair<>(1, 2)).getId()));
        assertTrue(tileIds.contains(map.getTileAt(new Pair<>(2, 0)).getId()));
    }

    @Test
    void testPlayerPosition() {
        assertEquals(new Pair<>(2, 2), map.getPlayerTileMapPosition());
        assertTrue(map.movePlayerTileMapPosition(Direction.LEFT));
        assertTrue(map.movePlayerTileMapPosition(Direction.RIGHT));
    }
}
