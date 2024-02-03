package io.github.yokigroup.battleTest;

import java.util.List;
import io.github.yokigroup.battle.XPCalculator;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl_dbg;
import io.github.yokigroup.battle.dummyImplXPCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XPCalcTest {
    @Test public void testDummyImpl() {
        XPCalculator toTest = new dummyImplXPCalculator();

        Yokimon y1 = new YokimonImpl_dbg("Blue", 10);
        Yokimon y2 = new YokimonImpl_dbg("Red", 2);
        Yokimon y3 = new YokimonImpl_dbg("Yellow", 8);

        assertEquals(0, toTest.getXP(List.of()));
        assertEquals(300, toTest.getXP(List.of(y1,y2,y3)));
        assertNotEquals(500, toTest.getXP(List.of(y1,y2)));

    }
}