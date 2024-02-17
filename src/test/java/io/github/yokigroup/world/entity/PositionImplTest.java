package io.github.yokigroup.world.entity;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositionImplTest {
    private static MessageHandler messageHandler;
    private static final double POSITION = 400;
    @BeforeEach
    void setUp() {
        MessageHandler messageHandler = new GameMessageHandler();
    }
    @Test
    void isValid() {

        Vector2 v = new Vector2Impl(POSITION, 0);
        Position altarPos = new PositionImpl(v);

        Tile tile = new TileBuilderImpl(0)
                .addEntity(TileBuilder.EntityType.ALTAR, altarPos).build(messageHandler);
        for (Entity entity : tile.getEntities()) {
            assertEquals(entity.getPos(), altarPos);
            entity.setPos(new PositionImpl(v.scale(POSITION)));
            assertTrue(entity.getPos().isValid());
            assertEquals(v, entity.getPos().getPosition());

        }
    }

    @Test
    void inRadius() {
        Vector2 p1 = new Vector2Impl(0, 0);
        Vector2 p2 = new Vector2Impl(4, 0);

        Position pos1 = new PositionImpl(p1);
        Position pos2 = new PositionImpl(p2);

        assertTrue(pos1.inRadius(pos2, 4));
    }
}
