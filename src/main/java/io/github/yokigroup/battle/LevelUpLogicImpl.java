package io.github.yokigroup.battle;

import static java.lang.Math.pow;

/**
 * Implementation of LevelUpLogic.
 */
public class LevelUpLogicImpl implements LevelUpLogic {
    /**
     * Value used for the divisor for xp calculation.
     */
    private static final int CAP = 80;

    /**
     * Value used for the exponent for calculate nextBoundXP.
     */
    private static final double EXPONENT = 2.7d;

    /**
     * This method calculates the next XP bound to
     * level up again.
     * @param level Level of the level upped yokimon.
     * @return Double with the new value.
     */
    private double nextBoundXp(final int level) {
            return pow(level + 1, EXPONENT);
    }

    /**
     *
     * @param level The level of the yokimon.
     * @return Expected total xp for that level.
     */
    private double expectedXp(final int level) {
        if (level == 1) {
            return 0;
        }
        return pow(level, EXPONENT);
    }

    @Override
    public final void setStats(final Yokimon yokimon) {
        // Level
        // Map<Yokimon.Stats, Integer> newStat = yokimon.getAllStats();
        yokimon.getAllStats().entrySet()
               .forEach(i -> i.setValue(upGradeStat(yokimon, i.getKey())));
        // Reset hp
        yokimon.setMaxHp(yokimon.getStat(Yokimon.Stats.HP));
        yokimon.setActualHp(yokimon.getStat(Yokimon.Stats.HP));
    }

    /**
     *
     * @param yokimon The yokimon to upgrade the stat of.
     * @param stat The stat to upgrade.
     * @return The upgraded stat.
     */
    private int upGradeStat(final Yokimon yokimon, final Yokimon.Stats stat) {
        final int level = yokimon.getLevel();
        if (stat == Yokimon.Stats.HP) {
            final int hpExtra = 10;
            return (yokimon.getBaseStat(stat) * 2 * level / CAP) + level + hpExtra;
        }
        final int otherExtra = 5;
        return (yokimon.getBaseStat(stat) * 2 * level / CAP) + otherExtra;
    }

    @Override
    public final Yokimon.ExpCode levelUp(final Yokimon yokimon, final int plus) {
        Yokimon.ExpCode code = Yokimon.ExpCode.LEVEL_UP;
        // Control if the yokimon learn a new move
            for (int i = yokimon.getLevel() + 1; i <= yokimon.getLevel() + plus; i++) {
                if (yokimon.getLearnableAttacks().containsKey(i)) {
                    yokimon.addAttack(yokimon.getLearnableAttacks().get(i));
                    code = Yokimon.ExpCode.NEW_MOVE;
                }
            }
        // Set new level
        yokimon.setLevel(yokimon.getLevel() + plus);
        // Set the new exp calculator
        yokimon.setExpNext(this.nextBoundXp(yokimon.getLevel()));
        yokimon.setExp(this.expectedXp(yokimon.getLevel()));
        // Set the new stats
        this.setStats(yokimon);
        return code;
    }

    @Override
    public final void resetAttack(final Yokimon yokimon) {
        yokimon.getAttacks().clear();
        for (int i = 0; i <= yokimon.getLevel(); i++) {
            if (yokimon.getLearnableAttacks().containsKey(i)) {
                yokimon.addAttack(yokimon.getLearnableAttacks().get(i));
            }
        }
    }

    @Override
    public final void reset(final Yokimon yokimon) {
        yokimon.setExpNext(this.nextBoundXp(yokimon.getLevel()));
        yokimon.setExp(this.expectedXp(yokimon.getLevel()));
        this.setStats(yokimon);
    }
}
