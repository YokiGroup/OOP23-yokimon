package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
