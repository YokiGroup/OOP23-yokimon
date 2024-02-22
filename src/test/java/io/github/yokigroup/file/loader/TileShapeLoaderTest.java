package io.github.yokigroup.file.loader;

import io.github.yokigroup.world.gen.TileShape;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileShapeLoaderTest {
    @Test
    void sizeTest() {
        final TileShapeLoader tileShapeLoader = new TileShapeLoader();
        final Set<TileShape> tileShapeSet = tileShapeLoader.getAll();
        assertEquals(10, tileShapeSet.size());
    }
}
