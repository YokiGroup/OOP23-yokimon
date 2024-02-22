package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeopleTest {
    private static final MessageHandler messageHandler = new GameMessageHandler();
    @Test
    void setDirection() {
        final Position playerPos = new PositionImpl(new Vector2Impl(0, 0));
        final Enemy en = new Enemy(playerPos, messageHandler);
        final Vector2 vector = new Vector2Impl(1, 0);
        en.setDirection(vector);
        assertEquals(People.Direction.RIGHT, en.getDirection());
        final Vector2 vector2 = new Vector2Impl(-1, 0);
        en.setDirection(vector2);
        assertEquals(People.Direction.LEFT, en.getDirection());
    }
}
