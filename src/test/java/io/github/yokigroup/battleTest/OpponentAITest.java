package io.github.yokigroup.battleTest;

import java.util.List;
import java.util.Optional;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.AttackImpl;
import io.github.yokigroup.battle.OpponentAI.OpponentAI;
import io.github.yokigroup.battle.OpponentAI.dummyImplOpponentAI;
import io.github.yokigroup.battle.fight.Yokimon;
import io.github.yokigroup.battle.fight.YokimonImpl_dbg;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OpponentAITest {
    @Test public void testDummyImpl() {
        OpponentAI toTest = new dummyImplOpponentAI();

        Attack a1 = new AttackImpl("Bubble", Attack.color.BLUE);
        Attack a2 = new AttackImpl("Leaf", Attack.color.GREEN);

        Yokimon y1 = new YokimonImpl_dbg("Blue", 8, List.of());
        Yokimon y2 = new YokimonImpl_dbg("Green", 3, List.of(a1));
        Yokimon y3 = new YokimonImpl_dbg("BandG", 2, List.of(a1,a2));

        //empty
        assertEquals(Optional.empty(), toTest.getMove(y1));
        //one attack
        assertEquals(Optional.of(a1), toTest.getMove(y2));
        //multiple attacks
        assertEquals(Optional.of(a1), toTest.getMove(y3));
    }
}
