package io.github.yokigroup.file.loader;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class TileShapeLoaderTest {
    @Test
    void sizeTest() {
        TileShapeLoader tileShapeLoader = new TileShapeLoader();
        var tileShapeSet = tileShapeLoader.getAll();
        assertEquals(10, tileShapeSet.size());

    }
}
