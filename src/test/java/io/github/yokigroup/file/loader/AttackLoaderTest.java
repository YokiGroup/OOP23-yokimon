package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackLoaderTest {
    private static AttackLoader loader;

    @BeforeEach
    void setUp() {
        loader = new AttackLoader();
    }

    @Test
    void load() {
        final int attack_id = 1;
        final String expectedName = "ULTRA COLPO FOLGORANTE DISTRUTTIVO DI MASSA";
        final Color expectedColor = Color.PURPLE;
        final Attack.Effect expectedEffect = Attack.Effect.NONE;
        final int expectedPower = 9001;

        Attack toTest = loader.load(attack_id);

        assertEquals(expectedName, toTest.getName());
        assertEquals(expectedColor, toTest.getColor());
        assertEquals(expectedEffect, toTest.getEffectID());
        assertEquals(expectedPower, toTest.attackPower());
    }
}