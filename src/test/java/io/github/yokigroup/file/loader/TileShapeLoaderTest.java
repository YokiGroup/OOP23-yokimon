package io.github.yokigroup.file.loader;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.gen.TileShape;
import io.github.yokigroup.world.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TileShapeLoaderTest {
    private static final double VEC2_X1 = 10.0;
    private static final double VEC2_Y1 = 30.0;
    private static final double VEC2_X2 = 10.0;
    private static final double VEC2_Y2 = 5.0;
    private static final double VEC2_X3 = 5.0;
    private static final double VEC2_Y3 = 5.0;
    private static final double VEC2_X4 = 30.0;
    private static final double VEC2_Y4 = 5.0;
    private static final double RAD = 5.0;

    private TileShape loadedTileShape;
    private Tile loadedTile;

    @BeforeEach
    void setUp() {
        MessageHandler handler = new GameMessageHandler();
        TileShapeLoader tileShapeLoader = new TileShapeLoader();
        var tileShapeSet = tileShapeLoader.getAll();

        //FIXME
        assertEquals(2, tileShapeSet.size());
        for (var t: tileShapeSet) {
            if (t.getTiles().size() == 2) {
                loadedTileShape = t;
            }
        }
        assertNotNull(loadedTileShape);

        assertEquals(2, loadedTileShape.getTiles().size());
        for (var t: loadedTileShape.getTiles().getEntries()) {
            Tile builtTile = t.build(handler);
            if (builtTile.getHitboxes().size() == 2) {
                loadedTile = builtTile;
            }
        }
        assertNotNull(loadedTile);
    }

    @Test
    void spawnsTest() {
        Set<Vector2> spawns = Set.of(new Vector2Impl(VEC2_X1, VEC2_Y1));
        assertEquals(spawns, loadedTile.getEntities().stream().map(Entity::getPos).collect(Collectors.toSet()));
    }

    @Test
    void hitboxesTest() {
        Hitbox rectHitbox = new RectangularHitbox(
                new Vector2Impl(VEC2_X2, VEC2_Y2),
                new Vector2Impl(VEC2_X3, VEC2_Y3)
        );
        Hitbox circleHitbox = new CircularHitbox(
                new Vector2Impl(VEC2_X4, VEC2_Y4),
                RAD
        );
        assertEquals(
                Set.of(rectHitbox, circleHitbox),
                loadedTile.getHitboxes()
        );
    }

    @Test
    void possibleDirectionsTest() {
        assertEquals(
                Set.of(Direction.DOWN, Direction.LEFT),
                loadedTileShape.getPossibleDirections()
        );
    }
}
