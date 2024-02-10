package io.github.yokigroup.battle;

import java.util.*;

/**
 * YokimonImpl is an implementation of the Yokimon interface,
 * representing a creature with various stats and abilities.
 */
public class YokimonImpl implements Yokimon{

    public final static int DEFAULT_LEVEL=1;
    public final static GrowthRate DEFAULT_GROWRATE = GrowthRate.MEDIUM;
    private final String name;
    private final Color color;
    private final Map<Stats, Integer> baseStats;
    private Map<Stats, Integer> stats;
    private int level;
    private int maxHp;
    private int actualHp;
    private final GrowthRate growthRate;
    private double xp;
    private double xpNext;
    private List<Attack> moves;
    private final Map<Integer, Attack> learnableMoves;
    private boolean active;

    private final LevelUpLogic levelUtility = new LevelUpLogicImpl();

    /**
     * Constructor for YokimonImpl.
     * @param name Name of the Yokimon
     * @param color Color of the Yokimon
     * @param baseStats Base stats of the Yokimon
     * @param growthRate Growth rate of the Yokimon
     * @param level Initial level of the Yokimon
     * @param learnableMoves Learnable moves of the Yokimon
     */
    public YokimonImpl(String name, Color color, Map<Yokimon.Stats, Integer> baseStats, GrowthRate growthRate,
                       int level, Map<Integer, Attack> learnableMoves)
    {
        this.stats = new HashMap<>();
        this.moves = new ArrayList<>();
        this.name = name;
        this.color = color;
        this.baseStats = Map.copyOf(baseStats);
        this.growthRate = growthRate;
        this.level = level;
        this.levelUtility.resetAttack(this);
        this.learnableMoves = Map.copyOf(learnableMoves);
        this.active=true;
        this.levelUtility.setStats(this);

    }
    /**
     * Constructor for YokimonImpl with default level.
     * @param name Name of the Yokimon
     * @param color Color of the Yokimon
     * @param baseStats Base stats of the Yokimon
     * @param growthRate Growth rate of the Yokimon
     * @param learnableMoves Learnable moves of the Yokimon
     */
    public YokimonImpl(String name, Color color, Map<Yokimon.Stats, Integer> baseStats, GrowthRate growthRate,
                       Map<Integer, Attack> learnableMoves){
        this(name, color, baseStats, growthRate, DEFAULT_LEVEL, learnableMoves);
    }

    /**
     * Constructor for YokimonImpl for another yokimon.
     * @param yokimon yokimon to copy
     */
    public YokimonImpl(YokimonImpl yokimon){
        this(Objects.requireNonNull(yokimon, "YokimonImpl passed was null").name, yokimon.color,
                Map.copyOf(yokimon.baseStats), yokimon.growthRate, yokimon.level, Map.copyOf(yokimon.learnableMoves));   ;
    }

    @Override
    public GrowthRate getGrowRate() {
        return this.growthRate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Color getYokimonColor() {
        return this.color;
    }

    @Override
    public Map<Stats, Integer> getAllStats() {
        return this.stats;
    }

    @Override
    public Map<Stats, Integer> getAllBaseStats() {
        return this.baseStats;
    }

    @Override
    public int getStat(Stats stat) {
        return this.stats.get(stat);
    }

    @Override
    public int getBaseStat(Stats baseStat) {
        return this.baseStats.get(baseStat);
    }

    @Override
    public void setStat(Yokimon.Stats change, int newValue) {
        this.stats.replace(change, newValue);
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(int n) {
        if(n>0){
            this.level=n;
            this.levelUtility.setStats(this);
        }

    }

    @Override
    public boolean levelUP(int n) {
        this.levelUtility.levelUp(this, n);
        return true;
    }

    @Override
    public List<Attack> getAttacks() {
        return this.moves;
    }

    @Override
    public void addAttack(Attack newAttack) {
        this.moves.add(newAttack);
    }

    @Override
    public Map<Integer, Attack> getLearnableAttacks() {
        return this.learnableMoves;
    }

    @Override
    public int getActualHp() {
        return this.actualHp;
    }

    @Override
    public int getMaxHp() {
        return this.maxHp;
    }

    @Override
    public void setActualHp(int newValue) {
        if(newValue == 0) this.active = false ;
        this.actualHp=newValue;
    }

    @Override
    public void setMaxHp(int newValue) {
        this.maxHp=newValue;
    }

    @Override
    public boolean takeDamage(int damage) {
        if(damage >= this.actualHp){
            this.actualHp=0;
            this.active=false;
        }
        this.actualHp-=damage;
        return this.active;
    }

    @Override
    public boolean active() {
        return this.active;
    }

    @Override
    public double getXp() {
        return this.xp;
    }

    @Override
    public double getNextLevelXp() {
        return this.maxHp;
    }

    @Override
    public exp_code takeXp(int n) {
        exp_code expCode=exp_code.ok;
        double xpToTake = n*this.growthRate.get();
        while (xpToTake >= this.xpNext-this.xp){
            xpToTake -= this.xpNext-this.xp;
            expCode = this.levelUtility.levelUp(this, 1);
        }
        this.xp += xpToTake;
        return expCode;
    }

    @Override
    public double getExpNext() {
        return this.xp;
    }

    @Override
    public void setExpNext(double newExp) {
        this.xpNext=newExp;
    }

    @Override
    public void setExp(double exp) {
        this.xp=exp;
    }
}
