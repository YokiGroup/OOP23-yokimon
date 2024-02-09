package io.github.yokigroup.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightedPoolTest {
    private final float weight1 = 0.25f;
    private final float weight2 = 0.5f;
    private final float weight3 = 0.75f;
    private final float weight4 = 1.0f;
    private WeightedPool<String> pool;

    @BeforeEach
    public void init() {
        pool = new WeightedPoolImpl<>();
        pool.addElement("elt1", weight1);
        pool.addElement("elt2", weight2);
    }

    @Test
    void addRandomizedElement() {
        pool.addElement("elt3", weight3);
        assertEquals(3, pool.size());
        pool.addElement("elt4", weight4);
        assertEquals(4, pool.size());
    }

    @Test
    void removeElement() {
        pool.addElement("elt3", weight3);
        assertEquals(3, pool.size());
        pool.removeElement("elt1");
        assertEquals(2, pool.size());
        pool.removeElement("elt2");
        assertEquals(1, pool.size());
        pool.removeElement("elt3");
        assertEquals(0, pool.size());
    }

    @Test
    void getRandomizedElement() {
        assertTrue(List.of("elt1", "elt2").contains(pool.getRandomizedElement()));
        assertTrue(List.of("elt1", "elt2").contains(pool.getRandomizedElement()));
        assertTrue(List.of("elt1", "elt2").contains(pool.getRandomizedElement()));
        pool.addElement("elt3", weight3);
        assertTrue(List.of("elt1", "elt2", "elt3").contains(pool.getRandomizedElement()));
        assertTrue(List.of("elt1", "elt2", "elt3").contains(pool.getRandomizedElement()));
    }

    @Test
    void removeRandomizedElement() {
        assertEquals(2, pool.size());
        assertTrue(List.of("elt1", "elt2").contains(pool.removeRandomizedElement()));
        assertEquals(1, pool.size());
        assertTrue(List.of("elt1", "elt2").contains(pool.removeRandomizedElement()));
        assertEquals(0, pool.size());
        pool.addElement("elt3", weight3);
        assertEquals(1, pool.size());
        assertTrue(List.of("elt1", "elt2", "elt3").contains(pool.removeRandomizedElement()));
        assertEquals(0, pool.size());
    }
}
