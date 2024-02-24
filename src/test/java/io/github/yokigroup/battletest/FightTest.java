package io.github.yokigroup.battletest;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.fight.FightImpl;
import io.github.yokigroup.core.exception.GameInitFailException;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.yokigroup.battle.yokimon.Yokimon;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for FightImpl.
 */
final class FightTest {

    private Fight toTest;
    private Yokimon tengu;
    private Yokimon oni;
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
     * Initialises the fight with some Yokimons meant for testing.
     */
    @BeforeEach
    void init() {

        tengu =  yokimonLoader.load(1);
        final Yokimon nekomata = yokimonLoader.load(3);
        final Yokimon baku = yokimonLoader.load(4);

        oni = yokimonLoader.load(2);
        final Yokimon oni2 = yokimonLoader.load(2);

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
        toTest.selectAttack(attackLoader.load(id));
        while (toTest.getCurrentOpponent().getActualHp() != toTest.getCurrentOpponent().getMaxHp()) {
            toTest.attack();
        }
        assertEquals(oni, toTest.getCurrentOpponent());
        assertFalse(toTest.isOver());
        assertFalse(toTest.victory());
    }

    /**
     * Testing getAttacked() method.
     */
    @Test
    void testGetAttacked() {
        toTest.attack();
        final Fight.Success atk1 = toTest.getAttacked();
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
