package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.DMGcalculator.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Attack a2 = new AttackImpl("Leaf", Attack.color.GREEN, 55, null);


        Yokimon y1 = new YokimonBuilder("Caterpie", Attack.color.GREEN, map1, YokimonImpl.DEFAULT_GROWRATE)
                .stats(map1)
                .build();

        Yokimon y2 = new YokimonBuilder("Charizard", Attack.color.RED, map2, YokimonImpl.DEFAULT_GROWRATE)
                .stats(map2)
                .build();

        assert(!map1.isEmpty());
        assert (!map2.isEmpty());
        assertEquals(59, toTest.getDMG(y1,y2,a1));
    }
}

