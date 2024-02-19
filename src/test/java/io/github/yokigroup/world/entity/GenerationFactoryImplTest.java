package io.github.yokigroup.world.entity;

import io.github.yokigroup.file.loader.YokimonLoader;
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
        IntStream.range(1, 10)
                .forEach( i -> assertTrue(generator.getYokimonAltar(power).getLevel() <= power + 3));

    }

    @Test
    void getEnemyParty() {
        final int power = 1;
        IntStream.range(1, 10)
                .forEach( i -> assertEquals(1, generator.getEnemyParty(power).size()));

    }

}