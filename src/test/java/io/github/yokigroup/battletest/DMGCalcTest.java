package io.github.yokigroup.battletest;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.DmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.FullImplDmgCalculator;
import io.github.yokigroup.battle.dmgcalculator.MultiplierDmgCalculator;
import io.github.yokigroup.core.exception.GameInitFailException;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Damage Calculator.
 */
class DMGCalcTest {

    private static final int EXP_VAL_BASIC1 = 22;
    private static final int EXP_VAL_BASIC2 = 20;
    private static final int EXP_VAL_MULT1 = 22;
    private static final int EXP_VAL_MULT2 = 20;
    private static final short EXP_VAL_FULL1 = 11;
    private static final short EXP_VAL_FULL2 = 10;
    private static final short EXP_VAL_FULL3 = 13;
    private static final short EXP_VAL_FULL4 = 11;
    private Yokimon y1, y2;
    private Attack a1, a2;
    private final YokimonLoader yokimonLoader;
    private final AttackLoader attackLoader;

    {
        try {
            yokimonLoader = new YokimonLoader();
            attackLoader = new AttackLoader();
        } catch (IOException e) {
            throw new GameInitFailException(e);
        }
    }

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
        assertEquals(EXP_VAL_BASIC1, toTest.getDMG(y1, y2, a1).x());
        assertEquals(EXP_VAL_BASIC2, toTest.getDMG(y1, y2, a2).x());

    }

    /**
     * Testing multiplier implementation.
     */
    @Test
    void testMultiplierImpl() {
        final DmgCalculator toTest = new MultiplierDmgCalculator();
        assertEquals(EXP_VAL_MULT1, toTest.getDMG(y1, y2, a1).x());
        assertEquals(EXP_VAL_MULT2, toTest.getDMG(y1, y2, a2).x());
    }

    private int calcRandomDamage(final int damage, final Fight.Success successValue) {
        return switch (successValue) {
            case FAIL -> 0;
            case WEAK -> damage / 2;
            case SUPER -> damage * 2;
            default -> damage;
        };
    }

    /**
     * Testing full implementation with color hierarchy.
     */
    @Test
    void testFullImpl() {
        final DmgCalculator toTest = new FullImplDmgCalculator();
        final Pair<Integer, Fight.Success> damage1 = toTest.getDMG(y1, y2, a1);
        assertEquals(calcRandomDamage(EXP_VAL_FULL1, damage1.y()), damage1.x());
        final Pair<Integer, Fight.Success> damage2 = toTest.getDMG(y1, y2, a2);
        assertEquals(calcRandomDamage(EXP_VAL_FULL2, damage2.y()), damage2.x());
        final Pair<Integer, Fight.Success> damage3 = toTest.getDMG(y2, y1, a1);
        assertEquals(calcRandomDamage(EXP_VAL_FULL3, damage3.y()), damage3.x());
        final Pair<Integer, Fight.Success> damage4 = toTest.getDMG(y2, y1, a2);
        assertEquals(calcRandomDamage(EXP_VAL_FULL4, damage4.y()), damage4.x());
    }
}
