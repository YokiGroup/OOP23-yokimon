package io.github.yokigroup.battleTest;

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
public final class NextYokTest {
    private static Yokimon y1, y2, y3;
    private final YokimonLoader yokimonLoader = new YokimonLoader();

    /**
     * Initialises some Yokimons meant for testing.
     */
    @BeforeEach
    public void init() {
        y1 = yokimonLoader.load(1);
        y2 = yokimonLoader.load(2);
        y3 = yokimonLoader.load(3);
    }

    /**
     * Testing dummy implementation.
     */
    @Test public void testDummyImpl() {
        NextYokimon toTest = new DummyImplNextYokimon();

        List<Yokimon> party1 = new LinkedList<>();

        party1.add(y1); party1.add(y2); party1.add(y3);
        assertEquals(Optional.of(y1), toTest.getNext(party1));

        party1.remove(y1);
        assertEquals(Optional.of(y2), toTest.getNext(party1));

        party1.remove(y2);
        assertEquals(Optional.of(y3), toTest.getNext(party1));

        party1.remove(y3);
        assertEquals(Optional.empty(), toTest.getNext(party1));

    }
}
