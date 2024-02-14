package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import io.github.yokigroup.world.gen.TileShape;
import io.github.yokigroup.world.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TileShapeLoaderTest {
    private TileShape loadedTileShape;
    private Tile loadedTile;

    @BeforeEach
    void setUp() {
        TileShapeLoader tileShapeLoader = new TileShapeLoader();
        var tileShapeSet = tileShapeLoader.getAll();

        assertEquals(2, tileShapeSet.size());
        for(var t: tileShapeSet) {
            if(t.getTiles().size() == 2) loadedTileShape = t;
        }
        assertNotNull(loadedTileShape);

        assertEquals(2, loadedTileShape.getTiles().size());
        for(var t: loadedTileShape.getTiles().getEntries()){
            if(t.getHitboxes().size() == 2) loadedTile = t;
        }
        assertNotNull(loadedTile);
    }

    @Test
    void spawnsTest() {
        Set<Vector2> spawns = Set.of(new Vector2Impl(10.0, 30.0));
        assertEquals(spawns, loadedTile.getEntitySpawnLocations());
    }

    @Test
    void hitboxesTest() {
        Hitbox rectHitbox = new RectangularHitbox(
                new Vector2Impl(10.0, 5.0),
                new Vector2Impl(5.0, 5.0)
        );
        Hitbox circleHitbox = new CircularHitbox(
                new Vector2Impl(30.0, 5.0),
                5.0
        );
        assertEquals(
                Set.of(rectHitbox, circleHitbox),
                loadedTile.getHitboxes()
        );
    }

    @Test
    void possibleDirectionsTest() {
        assertEquals(
                Set.of(TileShape.TileDirections.DOWN, TileShape.TileDirections.LEFT),
                loadedTileShape.getPossibleDirections()
        );
    }
}