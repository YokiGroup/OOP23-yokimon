package io.github.yokigroup.battleTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
//import io.github.yokigroup.battle.opponentai.OpponentAI;
//import io.github.yokigroup.battle.opponentai.DummyImplOpponentAI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Opponent AI.
 */
public class OpponentAITest {

    /**
     * Testing dummy implementation.
     */
    @Test public void testDummyImpl() {
//        OpponentAI toTest = new DummyImplOpponentAI(new BasicImplDmgCalculator());

        Attack a1 = new AttackImpl("Bubble", Color.BLACK);
        Attack a2 = new AttackImpl("Leaf", Color.PURPLE);


        /*      //TODO CORREGGI ISTANZIAZIONE YOKIMON
        Yokimon y1 = new YokimonImpl("Charizard", Map.of(), Yokimon.GrowthRate.medium, List.of());
        Yokimon y2 = new YokimonImpl("Squirtle", Map.of(), Yokimon.GrowRate.MEDIUM, List.of(a1));
        Yokimon y3 = new YokimonImpl("Bulbasaur", Map.of(), Yokimon.GrowRate.MEDIUM, List.of(a1, a2));

        //empty
        assertEquals(Optional.empty(), toTest.getMove(y1));
        //one attack
        assertEquals(Optional.of(a1), toTest.getMove(y2));
        //multiple attacks
        assertEquals(Optional.of(a1), toTest.getMove(y3));
         */
    }
}
