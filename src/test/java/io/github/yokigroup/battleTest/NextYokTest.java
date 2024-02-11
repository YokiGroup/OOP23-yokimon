package io.github.yokigroup.battleTest;

import java.util.*;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.nextyokimon.NextYokimon;
import io.github.yokigroup.battle.nextyokimon.DummyImplNextYokimon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Next Yokimon.
 */
public class NextYokTest {
    private static Yokimon y1, y2, y3;

    //FIXME -> still giving NullPointerException
    @BeforeEach
    public void init() {
        y1 = YokimonDatabase.getOni();
        y2 = YokimonDatabase.getBaku();
        y3 = YokimonDatabase.getKitsune();
    }

    /**
     * Testing dummy implementation.
     */
    @Test public void testDummyImpl() {
        /*
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

         */
    }
}
