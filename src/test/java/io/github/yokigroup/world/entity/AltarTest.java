package io.github.yokigroup.world.entity;

import io.github.yokigroup.battle.YokimonImpl;
import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import io.github.yokigroup.world.entity.people.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.MediaSize;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AltarTest {
    private static YokimonLoader loader;
    private static MessageHandler messageHandler;
    @BeforeEach
    void setUp() {
        loader = new YokimonLoader();
        MessageHandler messageHandler = new GameMessageHandler();
    }
    @Test
    void getState() {

        Position playerPos = new PositionImpl(new Vector2Impl(0, 0));
        Position altarPos = new PositionImpl(new Vector2Impl(2, 0));
        Hitbox altarHitBox = new RectangularHitbox(altarPos.getPosition(), altarPos.getPosition());
        Hitbox playerHitBox = new RectangularHitbox(playerPos.getPosition(), new Vector2Impl(1, 0));
        Entity altar = new Altar(0, altarPos, altarHitBox, loader.load(1), messageHandler);
        Entity player = new Player(1, playerPos, playerHitBox, Collections.emptyList(), messageHandler);



    }

    @Test
    void update() {
    }
}