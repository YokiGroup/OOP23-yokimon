package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.AttackImpl;
import io.github.yokigroup.battle.Color;
import io.github.yokigroup.util.json.JsonParser;

public class AttackLoader extends AbstractJsonLoader<Attack> {
    private static final String ATTACKJSONRPATH = "skills.json";
    private static final String ATTACK_NAME_JPATHF = "$.%d.name";
    private static final String ATTACK_COLOR_JPATHF = "$.%d.color";
    private static final String ATTACK_POWER_JPATHF = "$.%d.power";
    private static final String ATTACK_EFFECT_JPATHF = "$.%d.effect";

    public AttackLoader() {
        super(ATTACKJSONRPATH);
    }

    @Override
    public Attack load(int id) {
        JsonParser parser = getParser();

        int power = parser.read(String.format(ATTACK_POWER_JPATHF, id));
        String name = parser.read(String.format(ATTACK_NAME_JPATHF, id));
        Color color = Color.valueOf(parser.read(String.format(ATTACK_COLOR_JPATHF, id)));
        Attack.effect effect = Attack.effect.valueOf(parser.read(String.format(ATTACK_EFFECT_JPATHF, id)));

        return new AttackImpl(name, color, power, effect);
    }
}
