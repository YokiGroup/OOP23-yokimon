package io.github.yokigroup.battletest;

import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.opponentai.OpponentAI;
import io.github.yokigroup.battle.opponentai.DummyImplOpponentAI;
import io.github.yokigroup.battle.opponentai.FullImplOpponentAI;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Test class for Opponent AI.
 */

final class OpponentAITest {
    private static final int RAND_LEVEL = 15;
    private static final int ATK_ID1 = 18;
    private static final int ATK_ID2 = 13;
    private Yokimon y1, y2;
    private final YokimonLoader yokimonLoader = new YokimonLoader();
    private final AttackLoader attackLoader = new AttackLoader();

    /**
     * Initialises some Yokimons meant for testing.
     */
    @BeforeEach
    public void init() {
        y1 = yokimonLoader.load(1);
        y2 = yokimonLoader.load(2);
    }

    /**
     * Testing dummy implementation.
     */
    @Test
    void testDummyImpl() {
        final OpponentAI toTest = new DummyImplOpponentAI();

        assertEquals(attackLoader.load(ATK_ID1), toTest.getMove(y2, y1).get());
        assertEquals(attackLoader.load(ATK_ID1), toTest.getMove(y2, y1).get());
    }

    /**
     * Testing the full implementation (FullImplOpponentAI) with a BasicImplDmgCalculator.
     */
    @Test
    void testFullImplBasicDMGCalc() {
        final OpponentAI toTest = new FullImplOpponentAI(new BasicImplDmgCalculator());
        y1.levelUP(RAND_LEVEL);

        //multiple attacks. BASIC --> the Attack with the biggest POWER value

        //                            first, the Attack with the biggest POWER value

        assertEquals(attackLoader.load(ATK_ID2), toTest.getMove(y2, y1).get());
        //                              then, another -random- one
        assertNotEquals(attackLoader.load(ATK_ID2), toTest.getMove(y2, y1).get());
        //                              then again the best one
        assertEquals(attackLoader.load(ATK_ID2), toTest.getMove(y2, y1).get());
    }

}
