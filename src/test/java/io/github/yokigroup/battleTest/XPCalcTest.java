package io.github.yokigroup.battleTest;

import java.util.LinkedList;
import java.util.List;

import io.github.yokigroup.battle.YokimonDatabase;
import io.github.yokigroup.battle.xpcalculator.FullImplXPCalculator;
import io.github.yokigroup.battle.xpcalculator.XPCalculator;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl;
import io.github.yokigroup.battle.xpcalculator.DummyImplXPCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test class for XP Calculator.
 */
public class XPCalcTest {

    private static final int EXP_VAL_DUMMY1 = 0;
    private static final int EXP_VAL_DUMMY2 = 300;
    private static final int EXP_VAL_DUMMY3 = 200;
    private static final int EXP_VAL_FULL1 = 0;
    private static final int EXP_VAL_FULL2 = 0;
    private static final int EXP_VAL_FULL3 = 0;

    private static Yokimon y1, y2, y3;

    //FIXME -> still giving NullPointerException
    @BeforeEach
    public void init() {
        y1 = YokimonDatabase.getOni();
        y2 = YokimonDatabase.getBaku();
        y3 = YokimonDatabase.getNekomata();
    }

    /**
     * Testing dummy implementation.
     */
    @Test public void testDummyImpl() {
        XPCalculator toTest = new DummyImplXPCalculator();

        assertEquals(EXP_VAL_DUMMY1, toTest.getXP(List.of()));
        assertEquals(EXP_VAL_DUMMY2, toTest.getXP(List.of(y1, y2, y3)));
        assertNotEquals(EXP_VAL_DUMMY3, toTest.getXP(List.of(y1, y2)));

    }

    /**
     * Testing full implementation.
     */
    //TODO MUST TEST THIS CLASS
    @Test public void testFullImpl() {
        XPCalculator toTest = new FullImplXPCalculator();

        assertEquals(EXP_VAL_FULL1, toTest.getXP(List.of()));
        assertEquals(EXP_VAL_FULL2, toTest.getXP(List.of(y1, y2, y3)));
        assertNotEquals(EXP_VAL_FULL3, toTest.getXP(List.of(y1, y2)));

    }
}
