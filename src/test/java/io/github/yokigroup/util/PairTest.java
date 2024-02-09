package io.github.yokigroup.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {

    private Pair<String, String> pair1;
    private Pair<Integer, Integer> pair2;
    private Pair<String, Integer> pair3;

    @BeforeEach
    public void init() {
        pair1 = new PairImpl<>("Value 1", "Value 2");
        pair2 = new PairImpl<>(1, 2);
        pair3 = new PairImpl<>("Value 1", 2);
    }

    @Test
    void getValues() {
        assertEquals("Value 1", pair1.getX());
        assertEquals("Value 2", pair1.getY());
        assertEquals(1, pair2.getX());
        assertEquals(2, pair2.getY());
        assertEquals("Value 1", pair3.getX());
        assertEquals(2, pair3.getY());
    }
}
