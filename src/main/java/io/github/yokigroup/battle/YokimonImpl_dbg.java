package io.github.yokigroup.battle;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class YokimonImpl_dbg implements Yokimon {

    private static final int BASE_XP = 0;
    private static final int BASE_HP = 500;

    private final String name;
    private boolean active = true;
    private int level;
    private int XP = BASE_XP;
    private int HP = BASE_HP;
    private List<Attack> attacks = new LinkedList<>();

    /**
     *
     * @param name name of the Yokimon
     * @param level level of the Yokimon
     */

    public YokimonImpl_dbg(String name, int level, List<Attack> newAttacks) {
        this.name = name;
        this.level = level;
        if (newAttacks != null) {
            this.attacks.addAll(newAttacks);
        }
    }

    public YokimonImpl_dbg(String name, int level){
        this(name, level, null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<Stats, Integer> getALLStats() {
        return null;
    }

    @Override
    public Map<Stats, Integer> getALLBaseStats() {
        return null;
    }

    @Override
    public int getStat(Stats stat) {
        return 0;
    }

    @Override
    public int getBaseStat(BaseStats baseStat) {
        return 0;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public List<Attack> getAttacks() {
        return attacks;
    }

    @Override
    public int getActualHP() {
        return HP;
    }

    @Override
    public int getMAXHP() {
        return 0;
    }

    @Override
    public boolean levelUP(int n) {
        return false;
    }

    @Override
    public boolean Active() {
        return active;
    }

    @Override
    public exp_code takeXP(int n) {
        XP += n;
        return null;
    }
}
