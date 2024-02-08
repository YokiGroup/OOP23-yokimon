package io.github.yokigroup.battle;

import java.util.List;
import java.util.Map;

public class YokimonImpl implements Yokimon{

    public final static int DEFAULT_LEVEL=1;
    public final static GrowRate DEFAULT_GROWRATE = GrowRate.MEDIUM;

    private final String name;

    private final Attack.color color;

    private final Map<Stats, Integer> BaseStats;

    private Map<Stats, Integer> Stats;
    private int level;

    private int MAX_HP;

    private int ACTUAL_HP;

    private final GrowRate growRate;

    private int XP;

    private int XP_NEXTLEV;

    private List<Attack> moves;

    private boolean active;

    private final LevelUP_Logic LevelUtility=new LevelUP_LogicImpl();

    public YokimonImpl(String name, Attack.color color, Map<Yokimon.Stats, Integer> baseStats,
                       Map<Stats, Integer> Stats, int level, GrowRate growRate, List<Attack> moves, boolean active) {
        this.name = name;
        this.color = color;
        this.BaseStats = Map.copyOf(baseStats);
        this.Stats = Map.copyOf(Stats);
        this.level = level;
        this.growRate = growRate;
        this.moves=List.copyOf(moves);
        this.active=true;

        this.XP=0;
        this.XP_NEXTLEV=LevelUtility.nextBoundXP(this.level);
        this.LevelUtility.set_Stats(this);
        this.ACTUAL_HP=this.MAX_HP;
    }
    public YokimonImpl(String name, Attack.color color, Map<Yokimon.Stats, Integer> baseStats, GrowRate growRate,
                        int level, List<Attack> moves) {
        this(name, color, baseStats, baseStats, DEFAULT_LEVEL, DEFAULT_GROWRATE, moves, true);
    }

    /*MUST SUBSTITUTE WITH BUILDER USAGE*/
    public YokimonImpl(String name, Map<Yokimon.Stats, Integer> baseStats, GrowRate growRate,
                        List<Attack> moves)
    {
        this(name, null, baseStats, growRate, DEFAULT_LEVEL, moves);
    }

    public YokimonImpl(String name) {
        this(name, null, Map.of(), DEFAULT_GROWRATE, DEFAULT_LEVEL, List.of());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Attack.color getColor() {
        return color;
    }

    @Override
    public Map<Stats, Integer> getALLStats() {
        return this.Stats;
    }

    @Override
    public Map<Stats, Integer> getALLBaseStats() {
        return this.BaseStats;
    }

    @Override
    public int getStat(Stats stat) {
        return this.Stats.get(stat);
    }

    @Override
    public int getBaseStat(Stats baseStat) {
        return this.BaseStats.get(baseStat);
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public exp_code setLevel(int n) {
        if(n>0){
            this.level=n;
            this.LevelUtility.set_Stats(this);
            return exp_code.OK;
        }
        else
            return exp_code.ERROR;

    }

    @Override
    public boolean levelUP(int n) {
        this.LevelUtility.LevelUP(this, n);
        return true;
    }

    @Override
    public List<Attack> getAttacks() {
        return this.moves;
    }

    @Override
    public int getActualHP() {
        return this.ACTUAL_HP;
    }

    @Override
    public int getMAXHP() {
        return this.MAX_HP;
    }

    @Override
    public boolean Active() {
        return this.active;
    }

    @Override
    public exp_code takeXP(int n) {
        this.XP+=n*this.growRate.get();
        return exp_code.OK;
    }
}
