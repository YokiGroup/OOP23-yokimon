package io.github.yokigroup.world.tile;

import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TileBuilderTest {

    @Test
    void tileBuilderTest() {
        final Hitbox circleA = new CircularHitbox(1.0d);
        final Hitbox circleB = new CircularHitbox(3.0d);
        final Hitbox rectA = new RectangularHitbox(new Vector2Impl(1.0d, 3.2d));
        final TileBuilder builderA = new TileBuilderImpl(0, "");
        final Tile tileA = builderA.build(null);
        assertEquals(0, tileA.getId());
        assertTrue(tileA.getHitboxes().isEmpty());
        assertTrue(tileA.getEntities().isEmpty());
        assertTrue(tileA.getAdjacencies().isEmpty());
        final TileBuilder builderB = new TileBuilderImpl(1, "")
                .addAdjacency(Direction.DOWN)
                .addAllAdjacencies(Set.of(Direction.UP, Direction.RIGHT))
                .addHitbox(circleA)
                .addAllHitboxes(Set.of(circleB, rectA))
                .addEntity(TileBuilder.EntityType.ENEMY, new PositionImpl(new Vector2Impl(2.0d, 5.0d)));
        final Tile tileB = builderB.build(null);
        assertEquals(1, tileB.getId());
        assertEquals(3, tileB.getHitboxes().size());
        assertEquals(1, tileB.getEntities().size());
        assertEquals(3, tileB.getAdjacencies().size());
    }
}
