
package io.github.yokigroup.battle;


import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.NullPointerException;


class YokimonImplTest {
    private static final int DEFAULT_LEVEL = 10;
    private static final int NUM_MOVES = 3;
    private static final int DEFAULT_ID = 7;
    private static YokimonLoader loader;
    private static AttackLoader loaderAttack;
    private static MessageHandler messageHandler;
    @BeforeEach
    void setUp() {
        loader = new YokimonLoader();
        loaderAttack = new AttackLoader();
        MessageHandler messageHandler = new GameMessageHandler();
    }

    @Test
    void yokimonImpl() {
        YokimonImpl stone = null;
        assertThrows(NullPointerException.class, () -> new YokimonImpl(stone));

    }
    @Test
    void getAllStats() {
    Yokimon tengu = loader.load(DEFAULT_ID);

    tengu.setLevel(DEFAULT_LEVEL);

    assertEquals(DEFAULT_LEVEL, tengu.getLevel());

    assertEquals(NUM_MOVES, tengu.getAttacks().size());
    }

    @Test
    void getAttacks() {
            Yokimon tengu = loader.load(7);

            tengu.setLevel(DEFAULT_LEVEL);

            assertEquals(NUM_MOVES, tengu.getAttacks().size());
            final int atk4 = 4;
            final int atk1 = 1;
            for (int i = atk4; i > atk1; i--) {
                assertTrue(tengu.getAttacks().contains(loaderAttack.load(i)));
            }

            assertFalse(tengu.getAttacks().contains(loaderAttack.load(atk1)));
    }

    @Test
    void takeXP() {
        Yokimon tengu = loader.load(DEFAULT_ID);
        final int defXp = 1000;
        assertEquals(Yokimon.ExpCode.NEW_MOVE, tengu.takeXp(defXp));
        assertEquals(DEFAULT_LEVEL, tengu.getLevel());
        assertEquals(Yokimon.ExpCode.LEVEL_UP, tengu.takeXp(400));
        assertEquals(Yokimon.ExpCode.OK, tengu.takeXp(1));
    }
}
