package io.github.yokigroup.world;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.file.loader.TileLoader;
import io.github.yokigroup.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameMapBuilderTest {

    @Test
    void gameMapBuilderTest() {
        final MessageHandler messageHandler = new GameMessageHandler();
        final TileLoader loader = new TileLoader();
        final GameMapBuilder builderA = new GameMapBuilderImpl();
        final GameMap mapA = builderA.build(messageHandler);
        assertEquals(new Pair<>(0, 0), mapA.getPlayerTileMapPosition());
        final GameMapBuilder builderB = new GameMapBuilderImpl()
                .changeMapDimensions(new Pair<>(3, 3))
                .putHomeTileAt(new Pair<>(1, 1))
                .changePlayerTileMapPosition(new Pair<>(1, 1));
        final GameMap mapB = builderB.build(messageHandler);
        assertEquals(new Pair<>(1, 1), mapB.getPlayerTileMapPosition());
        assertEquals(loader.getHomeTile().build(messageHandler), mapB.getTileAt(new Pair<>(1, 1)));
    }
}
