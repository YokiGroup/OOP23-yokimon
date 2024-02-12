package io.github.yokigroup.world.entity;

import io.github.yokigroup.util.MutablePair;
import io.github.yokigroup.util.MutablePairImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionImplTest {

    @Test
    void isValid() {
    }

    @Test
    void inRadius() {
        MutablePair p2 = new MutablePairImpl(0, 1);
        MutablePair p1 = new MutablePairImpl(-1, -2);

        Position pos1 = new PositionImpl(p1);
        Position pos2 = new PositionImpl(p2);

        assertEquals(true, pos1.inRadius(pos2, 4));
    }
}