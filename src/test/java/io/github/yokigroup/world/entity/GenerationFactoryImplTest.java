package io.github.yokigroup.world.entity;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


class GenerationFactoryImplTest {
    private final GenerationFactory generator = new GenerationFactoryImpl();
    private static final int ID = 15;
    private static final int BOSS_LEV = 40;

    @BeforeEach
    void setUp() {

    }

    @Test
    void yokimonAltar() {
        final int power = 0;
        assertNotNull(generator.getYokimonAltar(power));
        final int rangeLev = 5;
        IntStream.range(1, 10)
                .forEach(i -> assertTrue(generator.getYokimonAltar(power).getLevel() <= power + rangeLev));

    }

    @Test
    void enemyParty() {
        final int power = 1;
        IntStream.range(1, 10)
                .forEach(i -> assertEquals(1, generator.getEnemyParty(power).size()));

    }

    @Test
    void  bossParty() {
        final YokimonLoader loader = new YokimonLoader();
        final Yokimon son = loader.load(ID);
        son.setLevel(BOSS_LEV);
        assertTrue(generator.getBossParty().contains(son));
    }

}
