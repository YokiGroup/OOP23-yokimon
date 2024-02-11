package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.Attack;

public class AttackLoader extends AbstractJsonLoader<Attack> {
    private static final String ATTACKJSONRPATH = "yokimons.json";

    public AttackLoader() {
        super(ATTACKJSONRPATH);
    }

    @Override
    public Attack load(int id) {
        return null;
    }
}
