package io.github.yokigroup.battle;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * YokimonImpl is an implementation of the Yokimon interface,
 * representing a creature with various stats and abilities.
 */
public class YokimonImpl implements Yokimon {
    /**
     * The starting level of the yokimon.
     */
    public static final int DEFAULT_LEVEL = 1;

    /**
     * The base stat level of the yokimon.
     */
    public static final int DEFAULT_STAT = 1;

    private final int id;
    private final String name;
    private final Color color;
    private final Map<Stats, Integer> baseStats;
    private final Map<Stats, Integer> stats;
    private int level;
    private int maxHp;
    private int actualHp;
    private final GrowthRate growthRate;
    private double xp;
    private double xpNext;
    private final List<Attack> moves;
    private final Map<Integer, Attack> learnableMoves;
    private boolean active;
    private final static LevelUpLogic levelUtility = new LevelUpLogicImpl();

    /**
     * Constructor for YokimonImpl.
     *
     * @param id             id of the yokimon
     * @param name           Name of the Yokimon
     * @param color          Color of the Yokimon
     * @param baseStats      Base stats of the Yokimon
     * @param growthRate     Growth rate of the Yokimon
     * @param level          Initial level of the Yokimon
     * @param learnableMoves Learnable moves of the Yokimon
     */
    public YokimonImpl(final int id, final String name, final Color color, final Map<Yokimon.Stats, Integer> baseStats,
                       final GrowthRate growthRate, final int level, final Map<Integer, Attack> learnableMoves) {
        this.stats = new HashMap<>();
        for (final Stats stat : Stats.values()) {
            stats.put(stat, DEFAULT_STAT);
        }
        this.moves = new ArrayList<>();
        this.active = true;
        this.id = id;
        this.name = name;
        this.color = color;
        this.baseStats = Map.copyOf(Objects.requireNonNull(baseStats, "baseStats passed was null"));
        this.growthRate = Objects.requireNonNull(growthRate, "growthRate passed was null");
        this.level = level;
        this.learnableMoves = Map.copyOf(Objects.requireNonNull(learnableMoves, "learnableMoves passed was null"));
        this.resetAttack();
        //this.levelUtility.resetAttack(this);
        levelUtility.reset(this);

    }

    /**
     * Constructor for YokimonImpl with default level.
     *
     * @param id             id of the Yokimon
     * @param name           Name of the Yokimon
     * @param color          Color of the Yokimon
     * @param baseStats      Base stats of the Yokimon
     * @param growthRate     Growth rate of the Yokimon
     * @param learnableMoves Learnable moves of the Yokimon
     */
    public YokimonImpl(final int id, final String name, final Color color, final Map<Yokimon.Stats, Integer> baseStats,
                       final GrowthRate growthRate, final Map<Integer, Attack> learnableMoves) {
        this(id, name, color, baseStats, growthRate, DEFAULT_LEVEL, learnableMoves);
    }

    /**
     * Constructor for YokimonImpl for another yokimon.
     *
     * @param yokimon yokimon to copy
     */
    public YokimonImpl(final Yokimon yokimon) {
        this(Objects.requireNonNull(yokimon, "YokimonImpl passed was null").getId(), yokimon.getName(),
                yokimon.getYokimonColor(), Map.copyOf(yokimon.getAllBaseStats()), yokimon.getGrowRate(),
                yokimon.getLevel(), Map.copyOf(yokimon.getLearnableAttacks()));
    }

    /**
     * return the id of the yokimon.
     *
     * @return int
     */
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public final GrowthRate getGrowRate() {
        return this.growthRate;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    /**
     * @return The color of the yokimon.
     */
    @Override
    public final Color getYokimonColor() {
        return this.color;
    }

    @Override
    public final Map<Stats, Integer> getAllStats() {
        return Map.copyOf(this.stats);
    }

    @Override
    public final void setStats(final Map<Stats, Integer> newStats) {
        this.stats.clear();
        this.stats.putAll(newStats);
    }

    @Override
    public final Map<Stats, Integer> getAllBaseStats() {
        return Map.copyOf(this.baseStats);
    }

    @Override
    public final int getStat(final Stats stat) {
        return this.stats.get(stat);
    }

    @Override
    public final int getBaseStat(final Stats stat) {
        return this.baseStats.get(stat);
    }


    @Override
    public final int getLevel() {
        return this.level;
    }

    @Override
    public final void setLevel(final int n) {
        if (n > 0) {
            this.level = n;
            levelUtility.reset(this);
            this.resetAttack();
        }

    }

    @Override
    public final boolean levelUP(final int n) {
        levelUtility.levelUp(this, n);
        return true;
    }

    @Override
    public final List<Attack> getAttacks() {
        return List.copyOf(this.moves);
    }

    @Override
    public final void setAttacks(final List<Attack> attacks) {
        this.moves.clear();
        this.moves.addAll(attacks);
    }
    @Override
    public final void addAttack(final Attack newAttack) {
        this.moves.add(newAttack);
    }

    @Override
    public final Map<Integer, Attack> getLearnableAttacks() {
        return this.learnableMoves;
    }

    @Override
    public final int getActualHp() {
        return this.actualHp;
    }

    @Override
    public final int getMaxHp() {
        return this.maxHp;
    }

    @Override
    public final void setActualHp(final int newValue) {
        if (newValue == 0) {
            this.active = false;
        }
        this.actualHp = newValue;
    }

    @Override
    public final void setMaxHp(final int newValue) {
        this.maxHp = newValue;
    }

    @Override
    public final boolean takeDamage(final int damage) {
        if (damage >= this.actualHp) {
            this.actualHp = 0;
            this.active = false;
        }
        this.actualHp -= damage;
        return this.active;
    }

    @Override
    public final boolean active() {
        return this.active;
    }

    @Override
    public final double getXp() {
        return this.xp;
    }

    @Override
    public final double getNextXp() {
        return this.xpNext;
    }

    @Override
    public final ExpCode takeXp(final int n) {
        ExpCode expCode = ExpCode.OK;
        double xpToTake = n * this.growthRate.get();
        while (xpToTake >= this.xpNext - this.xp) {
            xpToTake -= this.xpNext - this.xp;
            if (expCode == ExpCode.NEW_MOVE) {
                levelUtility.levelUp(this, 1);
            } else {
                expCode = levelUtility.levelUp(this, 1);
            }
        }
        this.xp += xpToTake;
        return expCode;
    }

    @Override
    public final void setExpNext(final double newExp) {
        this.xpNext = newExp;
    }

    @Override
    public final void setExp(final double exp) {
        this.xp = exp;
    }

    /**
     * Control if a yokimon is equal to another.
     *
     * @param o object
     * @return boolean
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final YokimonImpl yokimon = (YokimonImpl) o;
        return id == yokimon.id && level == yokimon.level && maxHp == yokimon.maxHp && actualHp == yokimon.actualHp
                && Double.compare(xp, yokimon.xp) == 0 && Double.compare(xpNext, yokimon.xpNext) == 0
                && active == yokimon.active && Objects.equals(name, yokimon.name) && color == yokimon.color
                && Objects.equals(baseStats, yokimon.baseStats) && Objects.equals(stats, yokimon.stats)
                && growthRate == yokimon.growthRate && Objects.equals(moves, yokimon.moves)
                && Objects.equals(learnableMoves, yokimon.learnableMoves);
    }

    /**
     * Generate hashCode for this class.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, baseStats, stats, level, maxHp,
                actualHp, growthRate, xp, xpNext, moves, learnableMoves, active);
    }

    private void resetAttack() {
        List<Attack> newAttacks = new ArrayList<>();
        for (int i = 0; i <= this.getLevel(); i++) {
            if (this.getLearnableAttacks().containsKey(i)) {
                newAttacks.add(this.getLearnableAttacks().get(i));
            }
        }
        this.setAttacks(newAttacks);
    }
}
