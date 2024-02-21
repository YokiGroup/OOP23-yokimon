package io.github.yokigroup.battletest;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.DmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.FullImplDmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.MultiplierDmgCalculator;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Damage Calculator.
 */
class DMGCalcTest {

    private static final int EXP_VAL_BASIC1 = 22;
    private static final int EXP_VAL_BASIC2 = 20;
    private static final int EXP_VAL_MULT1 = 22;
    private static final int EXP_VAL_MULT2 = 20;
    private static final short EXP_VAL_FULL1 = 22;
    private static final short EXP_VAL_FULL2 = 20;
    private static final short EXP_VAL_FULL3 = 26;
    private static final short EXP_VAL_FULL4 = 23;
    private Yokimon y1, y2;
    private Attack a1, a2;
    private final YokimonLoader yokimonLoader = new YokimonLoader();
    private final AttackLoader attackLoader = new AttackLoader();


    /**
     * Instantiates variables for test.
     */
    @BeforeEach
     void init() {
        y1 = yokimonLoader.load(1);
        y2 = yokimonLoader.load(2);
        a1 = attackLoader.load(1);
        a2 = attackLoader.load(2);
    }

    /**
     * Testing basic implementation.
     */
    @Test
    void testBasicImpl() {
        final DmgCalculator toTest = new BasicImplDmgCalculator();
        assertEquals(EXP_VAL_BASIC1, toTest.getDMG(y1, y2, a1));
        assertEquals(EXP_VAL_BASIC2, toTest.getDMG(y1, y2, a2));

    }

    /**
     * Testing multiplier implementation.
     */
    @Test
    void testMultiplierImpl() {
        final DmgCalculator toTest = new MultiplierDmgCalculator();
        assertEquals(EXP_VAL_MULT1, toTest.getDMG(y1, y2, a1));
        assertEquals(EXP_VAL_MULT2, toTest.getDMG(y1, y2, a2));
    }

    /**
     * Testing full implementation with color hierarchy.
     */
    @Test
    void testFullImpl() {
        final DmgCalculator toTest = new FullImplDmgCalculator();

        assertEquals(EXP_VAL_FULL1, toTest.getDMG(y1, y2, a1));
        assertEquals(EXP_VAL_FULL2, toTest.getDMG(y1, y2, a2));
        assertEquals(EXP_VAL_FULL3, toTest.getDMG(y2, y1, a1));
        assertEquals(EXP_VAL_FULL4, toTest.getDMG(y2, y1, a2));
    }
}
