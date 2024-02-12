package io.github.yokigroup.battleTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.FullImplDmgCalculator;
import io.github.yokigroup.battle.opponentai.FullImplOpponentAI;
import io.github.yokigroup.battle.opponentai.OpponentAI;
import io.github.yokigroup.battle.opponentai.DummyImplOpponentAI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Test class for Opponent AI.
 */
public class OpponentAITest {
    private static Yokimon y1, y2, y3, y4;
    private static Attack a1;


    //FIXME -> still giving NullPointerException
    @BeforeEach
    public void init() {
        a1 = YokimonDatabase.strongPunch;
        y1 = null;                          //TODO: INSTANTIATE YOKIMON WITH NO MOVES AVAILABLE
        y2 = null;                          //TODO: INSTANTIATE YOKIMON WITH ONE MOVE AVAILABLE (a1)
        y3 = YokimonDatabase.getNekomata();
        y4 = YokimonDatabase.getOni();
    }

    /**
     * Testing dummy implementation.
     */
    @Test public void testDummyImpl() {
        /*
        OpponentAI toTest = new DummyImplOpponentAI();
=======
//        OpponentAI toTest = new DummyImplOpponentAI(new BasicImplDmgCalculator());

        Attack a1 = new AttackImpl("Bubble", Color.BLACK);
        Attack a2 = new AttackImpl("Leaf", Color.PURPLE);


        /*      //TODO CORREGGI ISTANZIAZIONE YOKIMON
        Yokimon y1 = new YokimonImpl("Charizard", Map.of(), Yokimon.GrowthRate.medium, List.of());
        Yokimon y2 = new YokimonImpl("Squirtle", Map.of(), Yokimon.GrowRate.MEDIUM, List.of(a1));
        Yokimon y3 = new YokimonImpl("Bulbasaur", Map.of(), Yokimon.GrowRate.MEDIUM, List.of(a1, a2));
>>>>>>> feature/yokimon-system

        //empty
        assertEquals(Optional.empty(), toTest.getMove(y1,y2));
        //one attack
        assertEquals(Optional.of(a1), toTest.getMove(y2,y1));
        //multiple attacks
        assertEquals(Optional.of(a1), toTest.getMove(y3,y1));

         */
    }

    //TODO MUST TEST THIS CLASS!!!!!!!!!!
    @Test public void testFullImplBasicDMGCalc() {
        /*
        OpponentAI toTest = new FullImplOpponentAI(new BasicImplDmgCalculator());

        //empty
        assertEquals(Optional.empty(), toTest.getMove(y1,y2));
        //one attack
        assertEquals(Optional.of(a1), toTest.getMove(y2,y1));
        //multiple attacks. BASIC --> the Attack with the biggest POWER value
        assertEquals(Optional.of(YokimonDatabase.shadowBall), toTest.getMove(y3,y4));

         */
    }

    //TODO MUST TEST THIS CLASS!!!!!!!!!!
    @Test public void testFullImplFullDMGCalc() {
        /*
        OpponentAI toTest = new FullImplOpponentAI(new FullImplDmgCalculator());

        //multiple attacks. FULL --> first, the Attack with the biggest POWER value
        assertEquals(Optional.of(YokimonDatabase.curse), toTest.getMove(y3,y4));
        //                           then, a random different one from the list
        assertNotEquals(Optional.of(YokimonDatabase.curse), toTest.getMove(y3,y4));
        //                           then again the most powerful attack
        assertEquals(Optional.of(YokimonDatabase.curse), toTest.getMove(y3,y4));

         */
    }
}
