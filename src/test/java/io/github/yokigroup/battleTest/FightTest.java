package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.fight.FightImpl;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.yokigroup.battle.*;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FightTest {                                //NOTE: THIS FIGHT WAS TESTED WITH DUMMY IMPLEMENTATIONS

    private static Fight toTest;
    private static Yokimon y1, y2, y3, y4, y5;
    private final YokimonLoader yokimonLoader = new YokimonLoader();
    private final AttackLoader attackLoader = new AttackLoader();

    @BeforeEach
    public void init() {

        y1 =  yokimonLoader.load(1);
        y2 =  yokimonLoader.load(3);
        y3 =  yokimonLoader.load(4);

        y4 = yokimonLoader.load(2);
        y5 = yokimonLoader.load(2);

        List<Yokimon> myParty = new LinkedList<>();
        myParty.add(y1);
        myParty.add(y2);
        myParty.add(y3);

        List<Yokimon> oppParty = new LinkedList<>();
        oppParty.add(y4);
        oppParty.add(y5);

        toTest = new FightImpl(myParty, oppParty);
    }

    @Test public void testInstantiation(){
        assertEquals(y1, toTest.getCurrentMyYokimon());
        assertEquals(y4, toTest.getCurrentOpponent());
    }

    @Test public void testAttack() {
        Fight.Success atk1 = toTest.attack(attackLoader.load(2));
        assertNotEquals(toTest.getCurrentOpponent().getActualHp(),
                        toTest.getCurrentOpponent().getMaxHp());
        //assertEquals(26, toTest.getCurrentOpponent().getActualHp());   // (90 - 80*80/100)
        assertEquals(y4, toTest.getCurrentOpponent());                   // should still be alive
        //assertEquals(Fight.Success.WEAK, atk1);

        assertFalse(toTest.isOver());
        assertFalse(toTest.victory());

        Fight.Success atk2 = toTest.attack(attackLoader.load(4));     // (26 - 80*40/100 = -6 --> dead)
        assertEquals(y5, toTest.getCurrentOpponent());                   // should have changed
        //assertEquals(Fight.Success.WEAK, atk2);

        toTest.attack(attackLoader.load(1));
        toTest.attack(attackLoader.load(1));
        assertTrue(toTest.isOver());
        assertTrue(toTest.victory());
    }

    @Test public void testGetAttacked() {
        Fight.Success atk1 = toTest.getAttacked();
        assertNotEquals(toTest.getCurrentMyYokimon().getActualHp(),
                        toTest.getCurrentMyYokimon().getMaxHp());
        assertNotEquals(Fight.Success.FAIL, atk1);  //the best attack available shouldn't be that bad
        assertNotEquals(Fight.Success.WEAK, atk1);  //WITH DUMMY DAMAGE CALCULATOR!!

        assertFalse(toTest.isOver());
        assertFalse(toTest.victory());
    }

    @Test public void testGetXP() {
        assertEquals(0, toTest.getXP(toTest.getCurrentMyYokimon()));

        while (!toTest.isOver()) {
            toTest.attack(attackLoader.load(1));
        }
        assertEquals(200, toTest.getXP(toTest.getCurrentMyYokimon()));
    }
}


