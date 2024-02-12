package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.opponentai.*;
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
public class OpponentAITest {
    private static Yokimon y1, y2;
    private final YokimonLoader yokimonLoader = new YokimonLoader();
    private final AttackLoader attackLoader = new AttackLoader();

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

        assertEquals(Optional.of(attackLoader.load(4)).get().getName().toLowerCase(),
                        toTest.getMove(y2, y1).get().getName().toLowerCase());
        assertEquals(Optional.of(attackLoader.load(4)).get().getName().toLowerCase(),
                toTest.getMove(y2, y1).get().getName().toLowerCase());

    }

    //FIXME --> ON THE SECOND RUN IT DOESN'T CHANGE THE ATTACK, IT ALWAYS CHOOSES THE BEST ONE
    //NOTE: IN ORDER TO TEST YOU NEED TO MODIFY THE JSON FILE SO THEY HAVE MORE ATTACKS AVAILABLE
    @Test public void testFullImplBasicDMGCalc() {
        OpponentAI toTest = new FullImplOpponentAI(new BasicImplDmgCalculator());

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
