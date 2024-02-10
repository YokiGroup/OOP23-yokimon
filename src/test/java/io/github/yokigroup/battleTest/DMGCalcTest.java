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
    private static Yokimon y1, y2;
    private static Attack a1, a2;


    /**
     * Instantiates variables for test.
     */

    //TODO PERCHE' CAVOLO CONTINUA A DARMI NULL LA MAPPA DELLE LEARNABLE MOVES???????'
    @BeforeEach
    public void init() {

        Map<Yokimon.Stats, Integer> map1 = new HashMap<>();
        map1.put(Yokimon.Stats.ATK, ATK_VALUE1);
        map1.put(Yokimon.Stats.DEF, DEF_VALUE1);

        Map<Yokimon.Stats, Integer> map2 = new HashMap<>();
        map2.put(Yokimon.Stats.ATK, ATK_VALUE2);
        map2.put(Yokimon.Stats.DEF, DEF_VALUE2);

        Map<Integer, Attack> map3learnablemoves = new HashMap<>();
        map3learnablemoves.put(YokimonImpl.DEFAULT_LEVEL, a1);

        a1 = new AttackImpl("Bubble", Color.BLACK, PWR_VALUE1, null);
        a2 = new AttackImpl("Leaf", Color.PURPLE, PWR_VALUE2, null);

        y1 = new YokimonImpl("Caterpie", Color.PURPLE, map1, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map3learnablemoves);

        y2 = new YokimonImpl("Squirtle", Color.BLACK, map2, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map3learnablemoves);
    }

    /**
     * Testing basic implementation.
     */
    @Test public void testBasicImpl() {
        DmgCalculator toTest = new BasicImplDmgCalculator();

        assertEquals(EXP_VAL_BASIC1, toTest.getDMG(y1, y2, a1));
        assertEquals(EXP_VAL_BASIC2, toTest.getDMG(y1, y2, a2));

    }

    /**
     * Testing multiplier implementation.
     */
    @Test public void testMultiplierImpl() {
        DmgCalculator toTest = new MultiplierDmgCalculator();

        assertEquals(EXP_VAL_MULT1, toTest.getDMG(y1, y2, a1));
        assertEquals(EXP_VAL_MULT2, toTest.getDMG(y1, y2, a2));

    }
}
