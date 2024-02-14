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

    private static final int EXP_VAL_BASIC1 = 20;
    private static final int EXP_VAL_BASIC2 = 17;
    private static final int EXP_VAL_MULT1 = 24;
    private static final int EXP_VAL_MULT2 = 14;
    private static final short EXP_VAL_FULL1 = 12;
    private static final short EXP_VAL_FULL2 = 7;
    private static final short EXP_VAL_FULL3 = 33;
    private static final short EXP_VAL_FULL4 = 42;
    private static Yokimon y1, y2;
    private static Attack a1, a2;
    private final YokimonLoader yokimonLoader = new YokimonLoader();
    private final AttackLoader attackLoader = new AttackLoader();


    /**
     * Instantiates variables for test.
     */
    @BeforeEach
    void init() {
        y1 = yokimonLoader.load(1);
        y2 = yokimonLoader.load(3);
        a1 = attackLoader.load(2);
        a2 = attackLoader.load(3);
    }

    /**
     * Testing basic implementation.
     */
    @Test
    void testBasicImpl() {
        final DmgCalculator toTest = new BasicImplDmgCalculator();

        assertEquals(EXP_VAL_BASIC1, toTest.getDMG(y1, y2, a1)); //7*80/7*4 = 20
        assertEquals(EXP_VAL_BASIC2, toTest.getDMG(y1, y2, a2)); //7*70/7*4 = 17

    }

    /**
     * Testing multiplier implementation.
     */
    @Test
    void testMultiplierImpl() {
        final DmgCalculator toTest = new MultiplierDmgCalculator();

        assertEquals(EXP_VAL_MULT1, toTest.getDMG(y1, y2, a1)); //80*1.2/4 = 24
        assertEquals(EXP_VAL_MULT2, toTest.getDMG(y1, y2, a2)); //70/1.2/4 = 14
    }

    /**
     * Testing full implementation with color hierarchy.
     */
    @Test
    void testFullImpl() {
        final DmgCalculator toTest = new FullImplDmgCalculator();

        assertEquals(EXP_VAL_FULL1, toTest.getDMG(y1, y2, a1)); //80*1.2*0.5 = 48/4 = 12
        assertEquals(EXP_VAL_FULL2, toTest.getDMG(y1, y2, a2)); //70/1.2*0.5 = 29/4 = 7
        assertEquals(EXP_VAL_FULL3, toTest.getDMG(y2, y1, a1)); //80/1.2*2 = 133/4 = 33
        assertEquals(EXP_VAL_FULL4, toTest.getDMG(y2, y1, a2)); //70*1.2*2 = 168/4 = 42
    }
}
