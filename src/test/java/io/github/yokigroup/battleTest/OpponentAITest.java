package io.github.yokigroup.battleTest;

import java.util.List;
import java.util.Optional;

import io.github.yokigroup.battle.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OpponentAITest {
    @Test public void testDummyImpl() {
        OpponentAI toTest = new dummyImplOpponentAI();

        Yokimon y1 = new YokimonImpl_dbg("Blue", 8, List.of());
        assertEquals(Optional.empty(), toTest.getMove(y1));

        Attack a1 = new AttackImpl_dbg("Bubble", Attack.color.BLUE);
        Yokimon y2 = new YokimonImpl_dbg("Green", 3, List.of(a1));
        assertEquals(Optional.of(a1), toTest.getMove(y2));
    }
}
