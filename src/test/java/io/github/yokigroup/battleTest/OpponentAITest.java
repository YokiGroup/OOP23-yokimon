package io.github.yokigroup.battleTest;

import java.util.List;
import java.util.Optional;

import io.github.yokigroup.battle.OpponentAI;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl_dbg;
import io.github.yokigroup.battle.dummyImplOpponentAI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OpponentAITest {
    @Test public void testDummyImpl() {
        OpponentAI toTest = new dummyImplOpponentAI();

        Yokimon y1 = new YokimonImpl_dbg("Blue", 8, List.of());
        assertEquals(Optional.empty(), toTest.getMove(y1));
    }
}
