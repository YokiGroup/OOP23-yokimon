package io.github.yokigroup.battleTest;

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
public class DMGCalcTest {

    private static final int EXP_VAL_BASIC1 = 80;
    private static final int EXP_VAL_BASIC2 = 70;
    private static final int EXP_VAL_MULT1 = 96;
    private static final int EXP_VAL_MULT2 = 58;
    private static final short EXP_VAL_FULL1 = 48;
    private static final short EXP_VAL_FULL2 = 29;
    private static final short EXP_VAL_FULL3 = 133;
    private static final short EXP_VAL_FULL4 = 168;
    private static Yokimon y1, y2;
    private static Attack a1, a2;
    private final YokimonLoader yokimonLoader = new YokimonLoader();
    private final AttackLoader attackLoader = new AttackLoader();


    /**
     * Instantiates variables for test.
     */
    @BeforeEach
    public void init() {
        y1 = yokimonLoader.load(1);
        y2 = yokimonLoader.load(3);
        a1 = attackLoader.load(2);
        a2 = attackLoader.load(3);
    }

    /**
     * Testing basic implementation.
     */
    @Test public void testBasicImpl() {
        DmgCalculator toTest = new BasicImplDmgCalculator();

        assertEquals(EXP_VAL_BASIC1, toTest.getDMG(y1, y2, a1)); //7*80/7 = 80
        assertEquals(EXP_VAL_BASIC2, toTest.getDMG(y1, y2, a2)); //7*70/7 = 70

    }

    /**
     * Testing multiplier implementation.
     */
    @Test public void testMultiplierImpl() {
        DmgCalculator toTest = new MultiplierDmgCalculator();

        assertEquals(EXP_VAL_MULT1, toTest.getDMG(y1, y2, a1)); //80*1.2 = 96
        assertEquals(EXP_VAL_MULT2, toTest.getDMG(y1, y2, a2)); //70/1.2 = 58
    }

    /**
     * Testing full implementation with color hierarchy.
     */
    @Test public void testFullImpl() {
        DmgCalculator toTest = new FullImplDmgCalculator();

        assertEquals(EXP_VAL_FULL1, toTest.getDMG(y1, y2, a1)); //80*1.2*0.5 = 48
        assertEquals(EXP_VAL_FULL2, toTest.getDMG(y1, y2, a2)); //70/1.2*0.5 = 29
        assertEquals(EXP_VAL_FULL3, toTest.getDMG(y2, y1, a1)); //80/1.2*2 = 133
        assertEquals(EXP_VAL_FULL4, toTest.getDMG(y2, y1, a2)); //70*1.2*2 = 168
    }
}
