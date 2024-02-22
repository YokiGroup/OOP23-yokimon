package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private static final Vector2 V_P = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2,
            (double) GameMap.TILE_DIMENSIONS.y() / 2);

    @Test
    void move() {
        final MessageHandler handler = new GameMessageHandler();
        final int scaleTot = 5;
        final double scaleCompensate = 0.1;
        handler.handle(PlayerCharacterSubmodule.class, play -> {
            for (int i = 1; i < scaleTot; i++) {
                final double scalable = -50;
                play.movePlayerBy(new Vector2Impl(scalable, 0.00).scale(scaleCompensate));
                assertEquals(V_P.getX() + scalable * i, play.getPosition().getPosition().getX());
                assertEquals(V_P.getY(), play.getPosition().getPosition().getY());
            }
        });

    }
}