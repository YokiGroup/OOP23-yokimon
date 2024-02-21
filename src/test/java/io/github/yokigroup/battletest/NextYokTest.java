package io.github.yokigroup.battletest;

import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.nextyokimon.DummyImplNextYokimon;
import io.github.yokigroup.battle.nextyokimon.NextYokimon;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Next Yokimon.
 */
final class NextYokTest {
    private Yokimon tengu, oni, nekomata;
    private final YokimonLoader yokimonLoader = new YokimonLoader();

    /**
     * Initialises some Yokimons meant for testing.
     */
    @BeforeEach
    void init() {
        tengu = yokimonLoader.load(1);
        oni = yokimonLoader.load(2);
        nekomata = yokimonLoader.load(3);
    }

    /**
     * Testing dummy implementation.
     */
    @Test
    void testDummyImpl() {
        final NextYokimon toTest = new DummyImplNextYokimon();

        final List<Yokimon> party1 = new LinkedList<>();

        party1.add(tengu); party1.add(oni); party1.add(nekomata);
        assertEquals(Optional.of(tengu), toTest.getNext(party1));

        party1.remove(tengu);
        assertEquals(Optional.of(oni), toTest.getNext(party1));

        party1.remove(oni);
        assertEquals(Optional.of(nekomata), toTest.getNext(party1));

        party1.remove(nekomata);
        assertEquals(Optional.empty(), toTest.getNext(party1));

    }
}
