package io.github.yokigroup.battleTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import io.github.yokigroup.battle.nextyokimon.NextYokimon;
import io.github.yokigroup.battle.nextyokimon.DummyImplNextYokimon;
import io.github.yokigroup.battle.Yokimon;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import io.github.yokigroup.battle.YokimonImpl;

public class NextYokTest {
    @Test public void testDummyImpl() {
        NextYokimon toTest = new DummyImplNextYokimon();

        Yokimon y1 = new YokimonImpl("Blue");
        Yokimon y2 = new YokimonImpl("Red");
        Yokimon y3 = new YokimonImpl("Yellow");
        List<Yokimon> party1 = new LinkedList<>();

        party1.add(y1); party1.add(y2); party1.add(y3);
        assertEquals(Optional.of(y1), toTest.getNext(party1));

        party1.remove(y1);
        assertEquals(Optional.of(y2), toTest.getNext(party1));

    }
}
