
package io.github.yokigroup.battle;

import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.core.exception.GameInitFailException;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



class YokimonImplTest {
    private static final int DEFAULT_LEVEL = 7;
    private static final int NUM_MOVES = 3;
    private static final int DEFAULT_ID = 1;
    private static final YokimonLoader LOADER;
    private static final AttackLoader LOADER_ATTACK;
    static {
        try {
            LOADER = new YokimonLoader();
            LOADER_ATTACK = new AttackLoader();
        } catch (IOException e) {
            throw new GameInitFailException(e);
        }
    }

    @Test
    void allStats() {
    final Yokimon tengu = LOADER.load(DEFAULT_ID);

    tengu.setLevel(DEFAULT_LEVEL);
    assertEquals(DEFAULT_LEVEL, tengu.getLevel());
    assertEquals(NUM_MOVES, tengu.getAttacks().size());
    }

    @Test
    void attacks() {
            final Yokimon tengu = LOADER.load(DEFAULT_ID);

            tengu.setLevel(DEFAULT_LEVEL);

            assertEquals(NUM_MOVES, tengu.getAttacks().size());
            final int atk1 = 18;
            final int atk2 = 20;
            final int atk3 = 20;
            final int atk4 = 14;
            assertTrue(tengu.getAttacks().contains(LOADER_ATTACK.load(atk1)));
            assertTrue(tengu.getAttacks().contains(LOADER_ATTACK.load(atk2)));
            assertTrue(tengu.getAttacks().contains(LOADER_ATTACK.load(atk3)));

            assertFalse(tengu.getAttacks().contains(LOADER_ATTACK.load(atk4)));
    }

    @Test
    void takeXP() {
        final Yokimon tengu = LOADER.load(DEFAULT_ID);
        final int defXp = 200;
        final int defXp2 = 300;
        assertEquals(Yokimon.ExpCode.NEW_MOVE, tengu.takeXp(defXp));
        assertEquals(DEFAULT_LEVEL, tengu.getLevel());
        assertEquals(Yokimon.ExpCode.LEVEL_UP, tengu.takeXp(defXp2));
        assertEquals(Yokimon.ExpCode.OK, tengu.takeXp(1));
    }
}
