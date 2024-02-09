package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.dmgcalculator.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import io.github.yokigroup.battle.YokimonImpl;

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

        Attack a1 = new AttackImpl("Bubble", Attack.color.BLUE, 35, null);
        Attack a2 = new AttackImpl("Leaf", Attack.color.GREEN, 55, null);

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

        Map<Yokimon.Stats, Integer> map1 = new HashMap<>();
        map1.put(Yokimon.Stats.ATK, 150);
        map1.put(Yokimon.Stats.DEF, 80);

        Map<Yokimon.Stats, Integer> map2 = new HashMap<>();
        map2.put(Yokimon.Stats.ATK, 120);
        map2.put(Yokimon.Stats.DEF, 90);

        Attack a1 = new AttackImpl("Bubble", Attack.color.BLUE, 35, null);
        Attack a2 = new AttackImpl("Leaf", Attack.color.GREEN, 55, null);

        Yokimon y1 = new YokimonImpl("Caterpie", Attack.color.GREEN, map1, map1, YokimonImpl.DEFAULT_LEVEL,
                YokimonImpl.DEFAULT_GROWRATE, Collections.emptyList(), true);

        Yokimon y2 = new YokimonImpl("Squirtle", Attack.color.BLUE, map2, map2, YokimonImpl.DEFAULT_LEVEL,
                YokimonImpl.DEFAULT_GROWRATE, Collections.emptyList(), true);

        assertEquals(EXP_VAL_MULT1, toTest.getDMG(y1,y2,a1));
        assertEquals(EXP_VAL_MULT2, toTest.getDMG(y1,y2,a2));

    }
}
