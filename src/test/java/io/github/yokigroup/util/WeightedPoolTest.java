package io.github.yokigroup.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightedPoolTest {
    private static final float WEIGHT_1 = 0.25f;
    private static final float WEIGHT_2 = 0.5f;
    private static final float WEIGHT_3 = 0.75f;
    private static final float WEIGHT_4 = 1.0f;
    private static final String ELEMENT_1 = "elt1";
    private static final String ELEMENT_2 = "elt2";
    private static final String ELEMENT_3 = "elt3";
    private static final String ELEMENT_4 = "elt4";
    private WeightedPool<String> pool;

    @BeforeEach
    public void init() {
        pool = new WeightedPoolImpl<>();
        pool.addElement(ELEMENT_1, WEIGHT_1);
        pool.addElement(ELEMENT_2, WEIGHT_2);
    }

    @Test
    void addRandomizedElement() {
        pool.addElement(ELEMENT_3, WEIGHT_3);
        assertEquals(3, pool.size());
        pool.addElement(ELEMENT_4, WEIGHT_4);
        assertEquals(4, pool.size());
    }

    @Test
    void removeElement() {
        pool.addElement(ELEMENT_3, WEIGHT_3);
        assertEquals(3, pool.size());
        pool.removeElement(ELEMENT_1);
        assertEquals(2, pool.size());
        pool.removeElement(ELEMENT_2);
        assertEquals(1, pool.size());
        pool.removeElement(ELEMENT_3);
        assertEquals(0, pool.size());
    }

    @Test
    void testGetRandomizedElement() {
        assertTrue(List.of(ELEMENT_1, ELEMENT_2).contains(pool.getRandomizedElement()));
        assertTrue(List.of(ELEMENT_1, ELEMENT_2).contains(pool.getRandomizedElement()));
        assertTrue(List.of(ELEMENT_1, ELEMENT_2).contains(pool.getRandomizedElement()));
        pool.addElement(ELEMENT_3, WEIGHT_3);
        assertTrue(List.of(ELEMENT_1, ELEMENT_2, ELEMENT_3).contains(pool.getRandomizedElement()));
        assertTrue(List.of(ELEMENT_1, ELEMENT_2, ELEMENT_3).contains(pool.getRandomizedElement()));
    }

    @Test
    void removeRandomizedElement() {
        assertEquals(2, pool.size());
        assertTrue(List.of(ELEMENT_1, ELEMENT_2).contains(pool.removeRandomizedElement()));
        assertEquals(1, pool.size());
        assertTrue(List.of(ELEMENT_1, ELEMENT_2).contains(pool.removeRandomizedElement()));
        assertEquals(0, pool.size());
        pool.addElement(ELEMENT_3, WEIGHT_3);
        assertEquals(1, pool.size());
        assertTrue(List.of(ELEMENT_1, ELEMENT_2, ELEMENT_3).contains(pool.removeRandomizedElement()));
        assertEquals(0, pool.size());
    }
}
