package io.github.yokigroup.world.entity;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionImplTest {

    @Test
    void isValid() {
    }

    @Test
    void inRadius() {
        Vector2 p1 = new Vector2Impl(0 , 0);
        Vector2 p2 = new Vector2Impl(4 , 0);

        Position pos1 = new PositionImpl(p1);
        Position pos2 = new PositionImpl(p2);

        assertEquals(true, pos1.inRadius(pos2, 4));
    }
}