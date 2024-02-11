package io.github.yokigroup.battle;

import static java.lang.Math.pow;

import java.util.Map;

public class LevelUpLogicImpl implements  LevelUpLogic{

    private final static int CAP=80;
    /**
     * value used for the exponent for calculate nextBoundXP
     */
    private final static int EXPONENT=3;

    /**
     *This method calculates the next XP bound to
     * level up again
     * @param level level of the level upped yokimon
     * @return double with the new value
     */
    private double nextBoundXp(int level) {
        return pow(level, EXPONENT);
    }

    @Override
    public void setStats(Yokimon yokimon) {
        //level
        Map<Yokimon.Stats, Integer> newStat = yokimon.getAllStats();
        newStat.entrySet().stream()
               .filter(i -> i.getKey() != Yokimon.Stats.HP)
               .forEach(i -> i.setValue(upGrade(yokimon.getBaseStat(i.getKey()),
                        i.getValue(), yokimon.getLevel())));
        newStat.replace(Yokimon.Stats.HP, upGradeHp(yokimon.getBaseStat(Yokimon.Stats.HP),
                newStat.get(Yokimon.Stats.HP), yokimon.getLevel()));
        //reset hp
        yokimon.setMaxHp(yokimon.getBaseStat(Yokimon.Stats.HP));
        yokimon.setActualHp(yokimon.getBaseStat(Yokimon.Stats.HP));
    }

    private int upGradeHp(int baseStat, int actualStat, int level){
        actualStat = (baseStat*2*level/CAP)+level+10;
        return actualStat;
    }
    private int upGrade(int baseStat, int actualStat, int level){
        actualStat = (baseStat*2*level/CAP)+5;
        return actualStat;
    }



    @Override
    public Yokimon.exp_code levelUp(Yokimon yokimon, int plus) {

        Yokimon.exp_code code = Yokimon.exp_code.LEVEL_UP;
        //control if the yokimon learn a new move
            for(int i = yokimon.getLevel()+1; i <= yokimon.getLevel()+plus; i++){
                if(yokimon.getLearnableAttacks().containsKey(i)){
                    yokimon.addAttack(yokimon.getLearnableAttacks().get(i));
                    code = Yokimon.exp_code.NEW_MOVE;
                }
            }
        //set new level
        yokimon.setLevel(yokimon.getLevel()+plus);

        //set the new exp calculator
        yokimon.setExpNext(this.nextBoundXp(yokimon.getLevel()));
        yokimon.setExp(yokimon.getExpNext());

        //set the new stats
        this.setStats(yokimon);
        return code;
    }

    @Override
    public void resetAttack(Yokimon yokimon) {
        yokimon.getAttacks().clear();
        for(int i = 0; i <= yokimon.getLevel(); i++){
            if(yokimon.getLearnableAttacks().containsKey(i)){
                yokimon.addAttack(yokimon.getLearnableAttacks().get(i));
            }
        }
    }

    @Override
    public void reset(Yokimon yokimon) {
        yokimon.setExpNext(this.nextBoundXp(yokimon.getLevel()));
        yokimon.setExp(this.nextBoundXp(yokimon.getLevel()-1));
        this.setStats(yokimon);
    }
}
