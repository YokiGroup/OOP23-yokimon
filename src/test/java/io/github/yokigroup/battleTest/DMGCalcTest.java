package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.DmgCalculator;
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

    private static final int EXP_VAL_BASIC1 = 58;
    private static final int EXP_VAL_BASIC2 = 91;
    private static final int EXP_VAL_MULT1 = 48;
    private static final int EXP_VAL_MULT2 = 109;
    private static final int ATK_VALUE1 = 150;
    private static final int DEF_VALUE1 = 80;
    private static final int ATK_VALUE2 = 120;
    private static final int DEF_VALUE2 = 90;
    private static final int PWR_VALUE1 = 35;
    private static final int PWR_VALUE2 = 55;
    private static final int HP_VALUE1 = 200;
    private static final int HP_VALUE2 = 300;
    private static final int SPD_VALUE1 = 30;
    private static final int SPD_VALUE2 = 20;
    private static Yokimon y1, y2;
    private static Attack a1, a2;


    /**
     * Instantiates variables for test.
     */

    //FIXME -> fix yokimon constructor or get actual complete yokimons as test variables
    @BeforeEach
    public void init() {

        /*
        Map<Yokimon.Stats, Integer> map1Stats = new HashMap<>();
        map1Stats.put(Yokimon.Stats.ATK, ATK_VALUE1);
        map1Stats.put(Yokimon.Stats.DEF, DEF_VALUE1);
        map1Stats.put(Yokimon.Stats.HP, HP_VALUE1);
        map1Stats.put(Yokimon.Stats.SPD, SPD_VALUE1);

        Map<Yokimon.Stats, Integer> map2Stats = new HashMap<>();
        map2Stats.put(Yokimon.Stats.ATK, ATK_VALUE2);
        map2Stats.put(Yokimon.Stats.DEF, DEF_VALUE2);
        map2Stats.put(Yokimon.Stats.HP, HP_VALUE2);
        map1Stats.put(Yokimon.Stats.SPD, SPD_VALUE2);

        a1 = new AttackImpl("Bubble", Color.BLACK, PWR_VALUE1, null);
        a2 = new AttackImpl("Leaf", Color.PURPLE, PWR_VALUE2, null);

        Map<Integer, Attack> map1Learnables = new HashMap<>();
        map1Learnables.put(YokimonImpl.DEFAULT_LEVEL, a1);

        y1 = new YokimonImpl("Caterpie", Color.PURPLE, map1Stats, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map1Learnables);

        y2 = new YokimonImpl("Squirtle", Color.BLACK, map2Stats, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map1Learnables);

         */
    }

    /**
     * Testing basic implementation.
     */
    @Test public void testBasicImpl() {
        /*
        DmgCalculator toTest = new BasicImplDmgCalculator();

        assertEquals(EXP_VAL_BASIC1, toTest.getDMG(y1, y2, a1));
        assertEquals(EXP_VAL_BASIC2, toTest.getDMG(y1, y2, a2));

         */

    }

    /**
     * Testing multiplier implementation.
     */
    @Test public void testMultiplierImpl() {
        /*
        DmgCalculator toTest = new MultiplierDmgCalculator();

        assertEquals(EXP_VAL_MULT1, toTest.getDMG(y1, y2, a1));
        assertEquals(EXP_VAL_MULT2, toTest.getDMG(y1, y2, a2));

         */

    }
}
