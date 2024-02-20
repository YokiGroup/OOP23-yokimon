package io.github.yokigroup.battletest;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.fight.FightImpl;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.yokigroup.battle.Yokimon;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for FightImpl.
 */
final class FightTest {
    private static final int EXP_XPVALUE = 320;

    private static Fight toTest;
    private static Yokimon y1, y2, y3, y4, y5;
    private final YokimonLoader yokimonLoader = new YokimonLoader();
    private final AttackLoader attackLoader = new AttackLoader();

    /**
     * Initialises the fight with some Yokimons meant for testing.
     */
    @BeforeEach
    void init() {

        y1 =  yokimonLoader.load(1);
        y2 =  yokimonLoader.load(3);
        y3 =  yokimonLoader.load(4);

        y4 = yokimonLoader.load(2);
        y5 = yokimonLoader.load(2);

        final List<Yokimon> myParty = new LinkedList<>();
        myParty.add(y1);
        myParty.add(y2);
        myParty.add(y3);

        final List<Yokimon> oppParty = new LinkedList<>();
        oppParty.add(y4);
        oppParty.add(y5);

        toTest = new FightImpl(myParty, oppParty);
    }

    /**
     * Testing instantiation of the fight.
     */
    @Test
    void testInstantiation() {
        assertEquals(y1, toTest.getCurrentMyYokimon());
        assertEquals(y4, toTest.getCurrentOpponent());
    }

    /**
     * Testing attack() method.
     */
    @Test
    void testAttack() {
        toTest.attack(attackLoader.load(2));
        assertNotEquals(toTest.getCurrentOpponent().getActualHp(),
                        toTest.getCurrentOpponent().getMaxHp());
        assertEquals(y4, toTest.getCurrentOpponent());             // should still be alive
        assertFalse(toTest.isOver());
        assertFalse(toTest.victory());
        toTest.attack(attackLoader.load(4));
        assertEquals(y5, toTest.getCurrentOpponent());            // should have changed
        toTest.attack(attackLoader.load(1));
        toTest.attack(attackLoader.load(1));
        assertTrue(toTest.isOver());
        assertTrue(toTest.victory());
    }

    /**
     * Testing getAttacked() method.
     */
    @Test
    void testGetAttacked() {
        final Fight.Success atk1 = toTest.getAttacked();
        assertEquals(toTest.getCurrentMyYokimon().getActualHp(),
                        toTest.getCurrentMyYokimon().getMaxHp());
        assertNotEquals(Fight.Success.FAIL, atk1);  //the best attack available shouldn't be that bad
        assertNotEquals(Fight.Success.WEAK, atk1);

        assertFalse(toTest.isOver());
        assertFalse(toTest.victory());
    }

    /**
     * Test to ensure the XP points are calculated correctly.
     */
    @Test
    void testGetXP() {
        assertEquals(0, toTest.getXP(toTest.getCurrentMyYokimon()));

        while (!toTest.isOver()) {
            toTest.attack(attackLoader.load(1));
        }
        assertEquals(EXP_XPVALUE, toTest.getXP(toTest.getCurrentMyYokimon()));
    }

    /**
     * Test the SPD function, which declares which party is going to start the fight.
     */
    @Test
    void testPlayerIsFirst() {
        assertFalse(toTest.playerIsFirst());
    }
}


