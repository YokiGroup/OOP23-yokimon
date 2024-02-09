package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.AttackImpl;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.DmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.MultiplierDmgCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import io.github.yokigroup.battle.YokimonImpl;

import java.util.Collections;
import java.util.HashMap;
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
    private static Map<Yokimon.Stats, Integer> map1;
    private static Map<Yokimon.Stats, Integer> map2;


    /**
     * Instantiates variables for test.
     */
    @BeforeEach
    public void init() {

        map1 = new HashMap<>();
        map1.put(Yokimon.Stats.ATK, ATK_VALUE1);
        map1.put(Yokimon.Stats.DEF, DEF_VALUE1);

        map2 = new HashMap<>();
        map2.put(Yokimon.Stats.ATK, ATK_VALUE2);
        map2.put(Yokimon.Stats.DEF, DEF_VALUE2);
    }

    /**
     * Testing basic implementation.
     */
    @Test public void testBasicImpl() {
        DmgCalculator toTest = new BasicImplDmgCalculator();

        Attack a1 = new AttackImpl("Bubble", Attack.color.BLUE, PWR_VALUE1, null);
        Attack a2 = new AttackImpl("Leaf", Attack.color.GREEN, PWR_VALUE2, null);

        Yokimon y1 = new YokimonImpl("Caterpie", Attack.color.GREEN, map1, map1, YokimonImpl.DEFAULT_LEVEL,
                            YokimonImpl.DEFAULT_GROWRATE, Collections.emptyList(), true);

        Yokimon y2 = new YokimonImpl("Squirtle", Attack.color.BLUE, map2, map2, YokimonImpl.DEFAULT_LEVEL,
                YokimonImpl.DEFAULT_GROWRATE, Collections.emptyList(), true);

        assertEquals(EXP_VAL_BASIC1, toTest.getDMG(y1, y2, a1));
        assertEquals(EXP_VAL_BASIC2, toTest.getDMG(y1, y2, a2));

    }

    /**
     * Testing multiplier implementation.
     */
    @Test public void testMultiplierImpl() {
        DmgCalculator toTest = new MultiplierDmgCalculator();

        Attack a1 = new AttackImpl("Bubble", Attack.color.BLUE, PWR_VALUE1, null);
        Attack a2 = new AttackImpl("Leaf", Attack.color.GREEN, PWR_VALUE2, null);

        Yokimon y1 = new YokimonImpl("Caterpie", Attack.color.GREEN, map1, map1, YokimonImpl.DEFAULT_LEVEL,
                YokimonImpl.DEFAULT_GROWRATE, Collections.emptyList(), true);

        Yokimon y2 = new YokimonImpl("Squirtle", Attack.color.BLUE, map2, map2, YokimonImpl.DEFAULT_LEVEL,
                YokimonImpl.DEFAULT_GROWRATE, Collections.emptyList(), true);

        assertEquals(EXP_VAL_MULT1, toTest.getDMG(y1, y2, a1));
        assertEquals(EXP_VAL_MULT2, toTest.getDMG(y1, y2, a2));

    }
}
