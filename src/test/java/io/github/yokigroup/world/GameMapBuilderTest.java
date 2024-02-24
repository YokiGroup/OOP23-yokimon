package io.github.yokigroup.world;

import io.github.yokigroup.core.exception.GameInitFailException;
import io.github.yokigroup.file.loader.TileLoader;
import io.github.yokigroup.util.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameMapBuilderTest {

    @Test
    void gameMapBuilderTest() {
        final TileLoader loader;
        try {
            loader = new TileLoader();
        } catch (IOException e) {
            throw new GameInitFailException(e);
        }
        final GameMapBuilder builderA = new GameMapBuilderImpl();
        final GameMap mapA = builderA.build(null);
        assertEquals(new Pair<>(0, 0), mapA.getPlayerTileMapPosition());
        final GameMapBuilder builderB = new GameMapBuilderImpl()
                .changeMapDimensions(new Pair<>(3, 3))
                .putHomeTileAt(new Pair<>(1, 1))
                .changePlayerTileMapPosition(new Pair<>(1, 1));
        final GameMap mapB = builderB.build(null);
        assertEquals(new Pair<>(1, 1), mapB.getPlayerTileMapPosition());
        assertEquals(loader.getHomeTile().build(null), mapB.getTileAt(new Pair<>(1, 1)));
    }
}
