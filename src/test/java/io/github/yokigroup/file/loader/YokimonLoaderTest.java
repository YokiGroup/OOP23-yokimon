package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.Color;
import io.github.yokigroup.battle.Yokimon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class YokimonLoaderTest {
    private static final int ATK_ID1 = 18;
    private static YokimonLoader loader;

    @BeforeEach
    void setUp() {
        loader = new YokimonLoader();
    }

    @Test
    void load() {
        AttackLoader attackLoader = new AttackLoader();
        Yokimon y = loader.load(1);

        assertEquals("Tengu", y.getName());
        assertEquals(Yokimon.GrowthRate.MEDIUM, y.getGrowRate());
        assertEquals(Color.RED, y.getYokimonColor());
        assertEquals(attackLoader.load(ATK_ID1), y.getAttacks().get(0));
        // FIXME check the validity of all yokimon fields (with reflection if need be)

    }

    @Test
    void getAll() {
        Map<Integer, Yokimon> allYokimons = loader.getAll();
        assertEquals(loader.load(2).getName(), allYokimons.get(2).getName());
    }
}
