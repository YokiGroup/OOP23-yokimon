package io.github.yokigroup.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeightedPoolTest {

    @Test
    void addRandomizedElement() {
        WeightedPool<String> pool = new WeightedPoolImpl<>();
        pool.addElement("elt1", 0.7f);
        pool.addElement("elt2", 0.1f);
        pool.addElement("elt3", 0.2f);
        assertEquals(3, pool.size());
        pool.addElement("elt4", 0.3f);
        assertEquals(4, pool.size());
    }

    @Test
    void removeElement() {
        WeightedPool<String> pool = new WeightedPoolImpl<>();
        pool.addElement("elt1", 0.7f);
        pool.addElement("elt2", 0.1f);
        pool.addElement("elt3", 0.2f);
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
        WeightedPool<String> pool = new WeightedPoolImpl<>();
        pool.addElement("elt1", 0.7f);
        pool.addElement("elt2", 0.1f);
        assertTrue(List.of("elt1", "elt2").contains(pool.getRandomizedElement()));
        assertTrue(List.of("elt1", "elt2").contains(pool.getRandomizedElement()));
        assertTrue(List.of("elt1", "elt2").contains(pool.getRandomizedElement()));
        pool.addElement("elt3", 0.2f);
        assertTrue(List.of("elt1", "elt2", "elt3").contains(pool.getRandomizedElement()));
        assertTrue(List.of("elt1", "elt2", "elt3").contains(pool.getRandomizedElement()));
    }

    @Test
    void removeRandomizedElement() {
        WeightedPool<String> pool = new WeightedPoolImpl<>();
        pool.addElement("elt1", 0.7f);
        pool.addElement("elt2", 0.1f);
        assertEquals(2, pool.size());
        assertTrue(List.of("elt1", "elt2").contains(pool.removeRandomizedElement()));
        assertEquals(1, pool.size());
        assertTrue(List.of("elt1", "elt2").contains(pool.removeRandomizedElement()));
        assertEquals(0, pool.size());
        pool.addElement("elt3", 0.2f);
        assertEquals(1, pool.size());
        assertTrue(List.of("elt1", "elt2", "elt3").contains(pool.removeRandomizedElement()));
        assertEquals(0, pool.size());
    }
}