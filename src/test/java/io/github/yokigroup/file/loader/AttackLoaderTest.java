package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.attack.Color;
import io.github.yokigroup.core.exception.GameInitFailException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttackLoaderTest {

    @Test
    void load() {
        final AttackLoader loader;
        try {
            loader = new AttackLoader();
        } catch (IOException e) {
            throw new GameInitFailException(e);
        }

        final int attackId = 1;
        final String expectedName = "Shadow Ball";
        final Color expectedColor = Color.BLACK;
        final Attack.Effect expectedEffect = Attack.Effect.NONE;
        final int expectedPower = 90;

        final Attack toTest = loader.load(attackId);

        assertEquals(expectedName, toTest.getName());
        assertEquals(expectedColor, toTest.getColor());
        assertEquals(expectedEffect, toTest.getEffectID());
        assertEquals(expectedPower, toTest.attackPower());
    }
}
