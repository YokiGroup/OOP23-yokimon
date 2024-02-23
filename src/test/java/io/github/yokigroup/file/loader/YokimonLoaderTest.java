package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.attack.Color;
import io.github.yokigroup.battle.yokimon.Yokimon;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class YokimonLoaderTest {
    private static final YokimonLoader LOADER = new YokimonLoader();


    @Test
    void load() {
        final AttackLoader attackLoader = new AttackLoader();
        final Yokimon y = LOADER.load(1);
        final int katanaSlashId = 18;
        assertEquals("Tengu", y.getName());
        assertEquals(Yokimon.GrowthRate.MEDIUM, y.getGrowRate());
        assertEquals(Color.RED, y.getYokimonColor());
        assertEquals(attackLoader.load(katanaSlashId), y.getAttacks().get(0));
        // FIXME check the validity of all yokimon fields (with reflection if need be)

    }

    @Test
    void testGetAll() {
        final Map<Integer, Yokimon> allYokimons = LOADER.getAll();
        assertEquals(LOADER.load(2).getName(), allYokimons.get(2).getName());
    }
}
