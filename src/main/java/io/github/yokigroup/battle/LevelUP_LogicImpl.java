package io.github.yokigroup.battle;

import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.*;
public class LevelUP_LogicImpl implements  LevelUP_Logic{
    private final static int DEFAULT_VALUE=0;

    private final static int CAP=80;
    /**
     * value used for the exponent of the
     */
    private final static int exponent=3;

    /**
     *This method calculates the next XP bound to
     * level up again
     * @param level level of the level upped yokimon
     * @return double with the new value
     */
    private double nextBoundXP(int level) {
        return pow(level, exponent);
    }

    @Override
    public void set_Stats(Yokimon yokimon) {
        //level
        Map<Yokimon.Stats, Integer> newStat = yokimon.getALLStats();
        newStat.entrySet().stream()
               .filter(i -> i.getKey() != Yokimon.Stats.HP)
               .forEach(i -> i.setValue(upGrade(yokimon.getBaseStat(i.getKey()),
                        i.getValue(), yokimon.getLevel())));
        newStat.replace(Yokimon.Stats.HP, upGradeHP(yokimon.getBaseStat(Yokimon.Stats.HP),
                newStat.get(Yokimon.Stats.HP), yokimon.getLevel()));
        //reset HP
        yokimon.setMaxHP(yokimon.getBaseStat(Yokimon.Stats.HP));
        yokimon.setActualHP(yokimon.getBaseStat(Yokimon.Stats.HP));
    }

    private int upGradeHP(int baseStat, int actualStat, int level){
        actualStat = (baseStat*2*level/CAP)+level+10;
        return actualStat;
    }
    private int upGrade(int baseStat, int actualStat, int level){
        actualStat = (baseStat*2*level/CAP)+5;
        return actualStat;
    }



    @Override
    public Yokimon.exp_code LevelUP(Yokimon yokimon, int plus) {
        Yokimon.exp_code code = Yokimon.exp_code.LEVEL_UP;
        //control if the yokimon learn a new move
        for(int i = yokimon.getLevel()+1; i <= yokimon.getLevel()+plus; i++){
            if(yokimon.getLernableAttacks().containsKey(i)){
                yokimon.addAttack(yokimon.getLernableAttacks().get(i));
                code = Yokimon.exp_code.NEW_MOVE;
            }
        }
        //set new level
        yokimon.setLevel(yokimon.getLevel()+plus);

        //set the new exp calculator
        yokimon.setExpNext(this.nextBoundXP(yokimon.getLevel()));
        yokimon.setExp(yokimon.getExpNext());

        //set the new stats
        this.set_Stats(yokimon);
        return code;
    }

    @Override
    public void resetAttack(Yokimon yokimon) {
        yokimon.getAttacks().clear();
        for(int i = 0; i <= yokimon.getLevel(); i++){
            if(yokimon.getLernableAttacks().containsKey(i)){
                yokimon.addAttack(yokimon.getLernableAttacks().get(i));
            }
        }
    }

    @Override
    public void reset(Yokimon yokimon) {
        yokimon.setExpNext(this.nextBoundXP(yokimon.getLevel()));
        yokimon.setExp(this.nextBoundXP(yokimon.getLevel()-1));
        this.set_Stats(yokimon);
    }
}
