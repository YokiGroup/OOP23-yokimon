package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.AttackImpl;
import io.github.yokigroup.battle.DMGcalculator.*;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DMGCalcTest {

    /* public int getDMG(Yokimon attackingYokimon, Yokimon attackedYokimon, Attack attack) {
        return (int)(ATK)*Power()      /attackedYokimon.getStat(DEF);
    } */

    @Test public void testBasicImpl() {
        DMGCalculator toTest = new basicImplDMGCalculator();

        Map<Yokimon.Stats, Integer> map1 = new HashMap<>();
        map1.put(Yokimon.Stats.ATK, 150);
        map1.put(Yokimon.Stats.DEF, 80);

        Map<Yokimon.Stats, Integer> map2 = new HashMap<>();
        map1.put(Yokimon.Stats.ATK, 120);
        map1.put(Yokimon.Stats.DEF, 90);

        Attack a1 = new AttackImpl("Bubble", Attack.color.BLUE, 35, null);
        Attack a2 = new AttackImpl("Leaf", Attack.color.GREEN, 55, null);

        Yokimon y1 = new YokimonImpl("Squirtle", map1, null, List.of(a1));
        Yokimon y2 = new YokimonImpl("Charizard", map2, null, List.of(a2));

        assertEquals(59, toTest.getDMG(y1,y2,a1));
    }
}

