package io.github.yokigroup.file.loader;

import io.github.yokigroup.core.exception.GameInitFailException;
import io.github.yokigroup.world.gen.TileShape;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileShapeLoaderTest {
    @Test
    void sizeTest() {
        final TileShapeLoader tileShapeLoader;
        try {
            tileShapeLoader = new TileShapeLoader();
        } catch (IOException e) {
            throw new GameInitFailException(e);
        }
        final Set<TileShape> tileShapeSet = tileShapeLoader.getAll();
        assertEquals(10, tileShapeSet.size());
    }
}
