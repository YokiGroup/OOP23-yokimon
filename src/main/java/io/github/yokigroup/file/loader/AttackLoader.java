package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.AttackImpl;
import io.github.yokigroup.battle.Color;
import io.github.yokigroup.util.json.JsonParser;

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
     */
    public AttackLoader() {
        super(ATTACK_JSONR_PATH);
    }

    @Override
    public Attack load(final int id) {
        JsonParser parser = getParser();

        int power = parser.read(String.format(ATTACK_POWER_J_PATH_F, id));
        String name = parser.read(String.format(ATTACK_NAME_J_PATH_F, id));
        Color color = Color.valueOf(parser.read(String.format(ATTACK_COLOR_J_PATH_F, id)));
        Attack.Effect effect = Attack.Effect.valueOf(parser.read(String.format(ATTACK_EFFECT_J_PATH_F, id)));

        return new AttackImpl(id, name, color, power, effect);
    }
}
