package io.github.yokigroup.battle;

import java.util.List;
import java.util.Map;

public class YokimonImpl implements Yokimon{

    private final static int DEFAULT_LEVEL=1;
    private final String name;
    private final Color color;
    private final Map<Stats, Integer> BaseStats;

    private Map<Stats, Integer> Stats;
    private int level;

    private int MAX_HP;

    private int ACTUAL_HP;

    private final GrowRate growRate;

    private double XP;

    private double XP_NEXTLEV;

    private List<Attack> moves;

    private final Map<Integer, Attack> learnableMoves;

    private boolean active;


    private final LevelUP_Logic LevelUtility=new LevelUP_LogicImpl();
    public YokimonImpl(String name, Color color, Map<Yokimon.Stats, Integer> baseStats, GrowRate growRate,
                       int level, Map<Integer, Attack> learnableMoves)
    {
        this.name = name;
        this.color = color;
        this.BaseStats = Map.copyOf(baseStats);
        this.growRate = growRate;
        this.level=level;
        this.LevelUtility.resetAttack(this);
        this.learnableMoves = Map.copyOf(learnableMoves);
        this.active=true;
        this.LevelUtility.set_Stats(this);

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Color getYokimonColor() {
        return this.color;
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
    public void setStat(Yokimon.Stats change, int newValue) {
        this.Stats.replace(change, newValue);
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
    public void addAttack(Attack newAttack) {
        this.moves.add(newAttack);
    }

    @Override
    public Map<Integer, Attack> getLernableAttacks() {
        return this.learnableMoves;
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
    public void setActualHP(int newValue) {
        if(newValue == 0) this.active = false ;
        this.ACTUAL_HP=newValue;
    }

    @Override
    public void setMaxHP(int newValue) {
        this.MAX_HP=newValue;
    }

    @Override
    public boolean takeDamage(int damage) {
        if(damage >= this.ACTUAL_HP){
            this.ACTUAL_HP=0;
            this.active=false;
        }
        this.ACTUAL_HP-=damage;
        return this.active;
    }

    @Override
    public boolean Active() {
        return this.active;
    }

    @Override
    public exp_code takeXP(int n) {
        exp_code expCode=exp_code.OK;
        double xpToTake = n*this.growRate.get();
        while (xpToTake >= this.XP_NEXTLEV-this.XP){
            xpToTake -= this.XP_NEXTLEV-this.XP;
            expCode = this.LevelUtility.LevelUP(this, 1);
        }
        this.XP += xpToTake;
        return expCode;
    }

    @Override
    public double getExpNext() {
        return this.XP;
    }

    @Override
    public void setExpNext(double newExp) {
        this.XP_NEXTLEV=newExp;
    }

    @Override
    public void setExp(double exp) {
        this.XP=exp;
    }
}
