package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.attack.AttackImpl;
import io.github.yokigroup.battle.attack.Color;
import io.github.yokigroup.util.json.JsonParser;

import java.io.IOException;

/**
 * Loads Attacks from JSON file.
 */
public final class AttackLoader extends IdJsonLoader<Attack> {
    private static final String ATTACK_JSONR_PATH = "skills.json";
    private static final String ATTACK_NAME_J_PATH_F = "$.%d.name";
    private static final String ATTACK_COLOR_J_PATH_F = "$.%d.color";
    private static final String ATTACK_POWER_J_PATH_F = "$.%d.power";
    private static final String ATTACK_EFFECT_J_PATH_F = "$.%d.effect";

    /**
     * Constructor for AttackLoader.
     * @throws IOException File failed to open
     */
    public AttackLoader() throws IOException {
        super(ATTACK_JSONR_PATH);
    }

    @Override
    public Attack load(final int id) {
        final JsonParser parser = getParser();

        final int power = parser.read(String.format(ATTACK_POWER_J_PATH_F, id));
        final String name = parser.read(String.format(ATTACK_NAME_J_PATH_F, id));
        final Color color = Color.valueOf(parser.read(String.format(ATTACK_COLOR_J_PATH_F, id)));
        final Attack.Effect effect = Attack.Effect.valueOf(parser.read(String.format(ATTACK_EFFECT_J_PATH_F, id)));

        return new AttackImpl(id, name, color, power, effect);
    }
}
