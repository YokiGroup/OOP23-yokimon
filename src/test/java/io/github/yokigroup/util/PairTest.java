package io.github.yokigroup.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {

    @Test
    void getValues() {
        final Pair<String, String> pair1 = new PairImpl<>("Value 1", "Value 2");
        final Pair<Integer, Integer> pair2 = new PairImpl<>(1, 2);
        final Pair<String, Integer> pair3 = new PairImpl<>("Value 1", 2);
        assertEquals("Value 1", pair1.getX());
        assertEquals("Value 2", pair1.getY());
        assertEquals(1, pair2.getX());
        assertEquals(2, pair2.getY());
        assertEquals("Value 1", pair3.getX());
        assertEquals(2, pair3.getY());
    }
}
