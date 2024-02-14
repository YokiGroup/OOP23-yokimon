package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.opponentai.OpponentAI;
import io.github.yokigroup.battle.opponentai.DummyImplOpponentAI;
import io.github.yokigroup.battle.opponentai.FullImplOpponentAI;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Test class for Opponent AI.
 */
public final class OpponentAITest {
    private static Yokimon y1, y2;
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
    @Test public void testDummyImpl() {
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

        assertEquals(Optional.of(attackLoader.load(4)).get().getName().toLowerCase(),
                        toTest.getMove(y2, y1).get().getName().toLowerCase());
        assertEquals(Optional.of(attackLoader.load(4)).get().getName().toLowerCase(),
                toTest.getMove(y2, y1).get().getName().toLowerCase());

    }

    /**
     * Testing the full implementation (FullImplOpponentAI) with a BasicImplDmgCalculator.
     */
    @Test public void testFullImplBasicDMGCalc() {
        OpponentAI toTest = new FullImplOpponentAI(new BasicImplDmgCalculator());
        y1.levelUP(15);

        //multiple attacks. BASIC --> the Attack with the biggest POWER value

        //                            first, the Attack with the biggest POWER value
        assertEquals(Optional.of(attackLoader.load(1)).get().getName().toLowerCase(),
                toTest.getMove(y2, y1).get().getName().toLowerCase());
        //                              then, another -random- one
        assertNotEquals(Optional.of(attackLoader.load(1)).get().getName().toLowerCase(),
                toTest.getMove(y2, y1).get().getName().toLowerCase());
        //                              then again the best one
        assertEquals(Optional.of(attackLoader.load(1)).get().getName().toLowerCase(),
                toTest.getMove(y2, y1).get().getName().toLowerCase());
    }

}
