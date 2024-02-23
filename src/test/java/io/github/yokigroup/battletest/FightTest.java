package io.github.yokigroup.battletest;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.fight.FightImpl;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.yokigroup.battle.Yokimon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for FightImpl.
 */
final class FightTest {

    private static Fight toTest;
    private Yokimon tengu, nekomata, baku, oni, oni2;
    private final YokimonLoader yokimonLoader = new YokimonLoader();
    private final AttackLoader attackLoader = new AttackLoader();

    /**
     * Initialises the fight with some Yokimons meant for testing.
     */
    @BeforeEach
    void init() {

        tengu =  yokimonLoader.load(1);
        nekomata =  yokimonLoader.load(3);
        baku =  yokimonLoader.load(4);

        oni = yokimonLoader.load(2);
        oni2 = yokimonLoader.load(2);

        final List<Yokimon> myParty = new LinkedList<>();
        myParty.add(tengu);
        myParty.add(nekomata);
        myParty.add(baku);

        final List<Yokimon> oppParty = new LinkedList<>();
        oppParty.add(oni);
        oppParty.add(oni2);

        toTest = new FightImpl(myParty, oppParty);
    }

    /**
     * Testing instantiation of the fight.
     */
    @Test
    void testInstantiation() {
        assertEquals(tengu, toTest.getCurrentMyYokimon());
        assertEquals(oni, toTest.getCurrentOpponent());
    }

    /**
     * Testing attack() method.
     */
    @Test
    void testAttack() {
        final int id = 18;
        toTest.selectAttack(attackLoader.load(18));
        toTest.attack();
        assertNotEquals(toTest.getCurrentOpponent().getActualHp(),
                        toTest.getCurrentOpponent().getMaxHp());
        assertEquals(oni, toTest.getCurrentOpponent());
        assertFalse(toTest.isOver());
        assertFalse(toTest.victory());
    }

    /**
     * Testing getAttacked() method.
     */
    @Test
    void testGetAttacked() {
        final Fight.Success atk1 = toTest.getAttacked();
        assertNotEquals(toTest.getCurrentMyYokimon().getActualHp(),
                        toTest.getCurrentMyYokimon().getMaxHp());
        assertTrue(Arrays.stream(Fight.Success.values()).collect(Collectors.toSet()).contains(atk1));
        assertFalse(toTest.isOver());
        assertFalse(toTest.victory());
    }

    /**
     * Test the SPD function, which declares which party is going to start the fight.
     */
    @Test
    void testPlayerIsFirst() {
        assertTrue(toTest.playerIsFirst());
    }
}


