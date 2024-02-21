package io.github.yokigroup.world.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class GenerationFactoryImplTest {
    private final GenerationFactory generator = new GenerationFactoryImpl();

    @BeforeEach
    void setUp() {

    }

    @Test
    void getYokimonAltar() {
        final int power = 0;
        assertNotNull(generator.getYokimonAltar(power));
        final int rangeLev = 5;
        IntStream.range(1, 10)
                .forEach(i -> assertTrue(generator.getYokimonAltar(power).getLevel() <= power + rangeLev));

    }

    @Test
    void getEnemyParty() {
        final int power = 1;
        IntStream.range(1, 10)
                .forEach(i -> assertEquals(1, generator.getEnemyParty(power).size()));

    }

}
