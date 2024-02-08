package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.DMGcalculator.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import io.github.yokigroup.battle.YokimonImpl;

import static org.junit.jupiter.api.Assertions.*;

public class DMGCalcTest {

    @Test public void testBasicImpl() {
        DMGCalculator toTest = new basicImplDMGCalculator();

        Map<Yokimon.Stats, Integer> map1 = new HashMap<>();
        map1.put(Yokimon.Stats.ATK, 150);
        map1.put(Yokimon.Stats.DEF, 80);

        Map<Yokimon.Stats, Integer> map2 = new HashMap<>();
        map2.put(Yokimon.Stats.ATK, 120);
        map2.put(Yokimon.Stats.DEF, 90);

        Attack a1 = new AttackImpl("Bubble", Attack.color.BLUE, 35, null);

        Yokimon y1 = new YokimonImpl("Caterpie", Attack.color.GREEN, map1, map1, YokimonImpl.DEFAULT_LEVEL,
                            YokimonImpl.DEFAULT_GROWRATE, Collections.emptyList(), true);

        Yokimon y2 = new YokimonImpl("Squirtle", Attack.color.BLUE, map2, map2, YokimonImpl.DEFAULT_LEVEL,
                YokimonImpl.DEFAULT_GROWRATE, Collections.emptyList(), true);

        assertEquals(58, toTest.getDMG(y1,y2,a1));

    }
}
