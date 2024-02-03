package io.github.yokigroup.ciao;

import java.util.List;
import io.github.yokigroup.battle.XPCalculator;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.dummyImplXPCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XPCalcTest {
    @Test public void testDummyImpl() {
        XPCalculator toTest = new dummyImplXPCalculator();

        assertEquals(0, toTest.getXP(List.of()));
    };
}