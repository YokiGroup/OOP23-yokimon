package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.Yokimon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YokimonLoaderTest {
    private static YokimonLoader loader;

    @BeforeEach
    void setUp() {
        loader = new YokimonLoader();
    }

    @Test
    void load() {
        AttackLoader attackLoader = new AttackLoader();
        Yokimon y = loader.load(1);

        assertEquals("Pippo", y.getName());
        assertEquals(Yokimon.GrowthRate.SLOW, y.getGrowRate());
        assertEquals(attackLoader.load(1).getName(), y.getAttacks().get(0).getName());
        // FIXME check the validity of all yokimon fields (with reflection if need be)
    }
}