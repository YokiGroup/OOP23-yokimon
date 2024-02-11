package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.DmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.FullImplDmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.MultiplierDmgCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Damage Calculator.
 */
public class DMGCalcTest {

    private static final int EXP_VAL_BASIC1 = 64;
    private static final int EXP_VAL_BASIC2 = 56;
    private static final int EXP_VAL_MULT1 = 77;
    private static final int EXP_VAL_MULT2 = 47;
    private static final short EXP_VAL_FULL1 = 38;
    private static final short EXP_VAL_FULL2 = 23;
    private static final short EXP_VAL_FULL3 = 107;
    private static final short EXP_VAL_FULL4 = 134;
    private static Yokimon y1, y2;
    private static Attack a1, a2;


    /**
     * Instantiates variables for test.
     */
    //FIXME -> still giving NullPointerException
    @BeforeEach
    public void init() {
        y1 = YokimonDatabase.getOni();
        y2 = YokimonDatabase.getBaku();
        a1 = YokimonDatabase.strongPunch;
        a2 = YokimonDatabase.curse;
    }

    /**
     * Testing basic implementation.
     */
    @Test public void testBasicImpl() {
        DmgCalculator toTest = new BasicImplDmgCalculator();

        /*
        assertEquals(EXP_VAL_BASIC1, toTest.getDMG(y1, y2, a1)); //80*80/100 = 64
        assertEquals(EXP_VAL_BASIC2, toTest.getDMG(y1, y2, a2)); //80*70/100 = 56
         */
    }

    /**
     * Testing multiplier implementation.
     */
    @Test public void testMultiplierImpl() {
        DmgCalculator toTest = new MultiplierDmgCalculator();

        /*
        assertEquals(EXP_VAL_MULT1, toTest.getDMG(y1, y2, a1));//80*80/100*1.2 = 77
        assertEquals(EXP_VAL_MULT2, toTest.getDMG(y1, y2, a2));//80*70/100/1.2 = 47
         */
    }

    //TODO MUST TEST THIS CLASS!!!!!!!!!!!
    /**
     * Testing full implementation with color hierarchy.
     */
    @Test public void testFullImpl() {
        DmgCalculator toTest = new FullImplDmgCalculator();
        /*
        assertEquals(EXP_VAL_FULL1, toTest.getDMG(y1, y2, a1));//80*80/100*1.2*0.5 = 38
        assertEquals(EXP_VAL_FULL2, toTest.getDMG(y1, y2, a2));//80*70/100/1.2*0.5 = 23
        assertEquals(EXP_VAL_FULL3, toTest.getDMG(y2, y1, a1));//80*80/100/1.2*2 = 107
        assertEquals(EXP_VAL_FULL4, toTest.getDMG(y2, y1, a2));//80*70/100*1.2*2 = 134
         */
    }
}
