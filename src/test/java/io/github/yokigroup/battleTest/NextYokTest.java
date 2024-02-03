package io.github.yokigroup.battleTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import io.github.yokigroup.battle.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NextYokTest {
    @Test public void testDummyImpl() {
        NextYokimon toTest = new dummyImplNextYokimon();

        Yokimon y1 = new YokimonImpl_dbg("Blue", 10);
        Yokimon y2 = new YokimonImpl_dbg("Red", 2);
        Yokimon y3 = new YokimonImpl_dbg("Yellow", 8);
        List<Yokimon> party1 = new LinkedList<>();

        party1.add(y1); party1.add(y2); party1.add(y3);
        assertEquals(Optional.of(y1), toTest.getNext(party1));

        party1.remove(y1);
        assertEquals(Optional.of(y2), toTest.getNext(party1));

    }
}
