package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.Color;
import io.github.yokigroup.battle.Yokimon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

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

        assertEquals("Pippo Pugile", y.getName());
        assertEquals(Yokimon.GrowthRate.FAST, y.getGrowRate());
        assertEquals(Color.BLACK, y.getYokimonColor());
        assertEquals(attackLoader.load(1), y.getAttacks().get(0));
        // FIXME check the validity of all yokimon fields (with reflection if need be)

    }

    @Test
    void getAll() {
        Map<Integer, Yokimon> allYokimons = loader.getAll();
        assertEquals(loader.load(2).getName(), allYokimons.get(2).getName());
    }
}