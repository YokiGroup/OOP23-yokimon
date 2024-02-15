package io.github.yokigroup.world.entity;

import io.github.yokigroup.battle.YokimonImpl;
import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AltarTest {
    private static YokimonLoader loader;

    @BeforeEach
    void setUp() {
        loader = new YokimonLoader();
    }
    @Test
    void getState() {
        Position playerPos = new PositionImpl(new Vector2Impl(0, 0));
        Position altarPos = new PositionImpl(new Vector2Impl(2, 0));
        Hitbox altarHitBox = new RectangularHitbox(altarPos.getPosition(), altarPos.getPosition());
        Hitbox playerHitBox = new RectangularHitbox(playerPos.getPosition(), new Vector2Impl(1, 0));
        //Entity altar = new Altar(0, altarPos, altarHitBox, loader.load(1), );
    }

    @Test
    void update() {
    }
}