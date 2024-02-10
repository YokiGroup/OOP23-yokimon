package io.github.yokigroup.battleTest;

import java.util.*;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.nextyokimon.NextYokimon;
import io.github.yokigroup.battle.nextyokimon.DummyImplNextYokimon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Next Yokimon.
 */
public class NextYokTest {

    private static final int ATK_VALUE1 = 150;
    private static final int DEF_VALUE1 = 80;
    private static final int ATK_VALUE2 = 120;
    private static final int DEF_VALUE2 = 90;
    private static final int PWR_VALUE1 = 35;

    private static Yokimon y1, y2, y3;
    private static Attack a1;

    //FIXME -> fix yokimon constructor or get actual complete yokimons as test variables
    @BeforeEach
    public void init() {
        /*

        a1 = new AttackImpl("Bubble", Color.BLACK, PWR_VALUE1, null);

        Map<Yokimon.Stats, Integer> map1Stats = new HashMap<>();
        map1Stats.put(Yokimon.Stats.ATK, ATK_VALUE1);
        map1Stats.put(Yokimon.Stats.DEF, DEF_VALUE1);

        Map<Yokimon.Stats, Integer> map2Stats = new HashMap<>();
        map2Stats.put(Yokimon.Stats.ATK, ATK_VALUE2);
        map2Stats.put(Yokimon.Stats.DEF, DEF_VALUE2);

        Map<Integer, Attack> map1Learnables = new HashMap<>();
        map1Learnables.put(YokimonImpl.DEFAULT_LEVEL, a1);


        y1 = new YokimonImpl("Caterpie", Color.PURPLE, map1Stats, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map1Learnables);

        y2 = new YokimonImpl("Squirtle", Color.BLACK, map2Stats, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map1Learnables);

        y3 = new YokimonImpl("Meowtwo", Color.WHITE, map1Stats, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map1Learnables);

         */
    }

    /**
     * Testing dummy implementation.
     */
    @Test public void testDummyImpl() {
        /*
        NextYokimon toTest = new DummyImplNextYokimon();

        List<Yokimon> party1 = new LinkedList<>();

        party1.add(y1); party1.add(y2); party1.add(y3);
        assertEquals(Optional.of(y1), toTest.getNext(party1));

        party1.remove(y1);
        assertEquals(Optional.of(y2), toTest.getNext(party1));
         */
    }
}
