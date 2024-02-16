package io.github.yokigroup.world;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.file.loader.TileLoader;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameMapTest {
    private final TileLoader loader = new TileLoader();
    private MessageHandler messageHandler;
    private GameMap map;

    @BeforeEach
    public void init() {
        this.messageHandler = new GameMessageHandler();
        this.map = new GameMapBuilderImpl()
                .changeMapDimensions(new Pair<>(3, 3))
                .putHomeTileAt(new Pair<>(1, 1))
                .changePlayerTileMapPosition(new Pair<>(1, 1))
                .build(messageHandler);
    }

    @Test
    void testGetTileAt() {
        final Set<Integer> tileIds = loader.getAll().values().stream()
                .map(a -> a.build(messageHandler).getId())
                .collect(Collectors.toSet());
        assertEquals(loader.getHomeTile().build(messageHandler), map.getTileAt(new Pair<>(1, 1)));
        assertTrue(tileIds.contains(map.getTileAt(new Pair<>(1, 2)).getId()));
        assertTrue(tileIds.contains(map.getTileAt(new Pair<>(0, 3)).getId()));
    }

    @Test
    void testPlayerPosition() {
        assertEquals(new Pair<>(1, 1), map.getPlayerTileMapPosition());
        assertTrue(map.movePlayerTileMapPosition(Direction.UP));
        assertTrue(map.movePlayerTileMapPosition(Direction.DOWN));
    }
}
