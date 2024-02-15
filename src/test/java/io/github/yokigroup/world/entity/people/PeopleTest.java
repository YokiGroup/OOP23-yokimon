package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeopleTest {
    private static MessageHandler messageHandler;
    @BeforeEach
    void setUp() {
        MessageHandler messageHandler = new GameMessageHandler();
    }
    @Test
    void toDirection() {
        Position playerPos = new PositionImpl(new Vector2Impl(0, 0));
        Hitbox playerHitBox = new RectangularHitbox(playerPos.getPosition(), new Vector2Impl(1, 1));
        Enemy en = new Enemy(0, playerPos, playerHitBox, Collections.emptyList(), messageHandler);
        Vector2 vector = new Vector2Impl(1, 0);
        en.toDirection(vector);
        assertEquals(People.Direction.RIGHT, en.getDirection());
        Vector2 vector2 = new Vector2Impl(-1, 0);
        en.toDirection(vector2);
        assertEquals(People.Direction.LEFT, en.getDirection());
        Vector2 vector3 = new Vector2Impl(-1, 1);
        en.toDirection(vector3);
        assertEquals(People.Direction.DOWN, en.getDirection());
        Vector2 vector4 = new Vector2Impl(-1, -1);
        en.toDirection(vector4);
        assertEquals(People.Direction.UP, en.getDirection());
    }
}