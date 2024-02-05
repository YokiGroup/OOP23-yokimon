package io.github.yokigroup.battle.DMGcalculator;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

public class fullImplDMGCalculator implements DMGcalculator{

    private static final double MULTIPLIER = 1.2;
    @Override
    public int getDMG(Yokimon attackingYokimon, Yokimon attackedYokimon, Attack attack) {
        double total = (double)attackedYokimon.getStat(Yokimon.Stats.ATK)*attack.attackPower()
                        /attackedYokimon.getStat(Yokimon.Stats.DEF);

        if(attackingYokimon.getColor().equals(attack.color())) {
            total = total*MULTIPLIER;
        }
        if(attackedYokimon.getColor().equals(attack.color())) {
            total = total/MULTIPLIER;
        }

        return (int)total;
    }
}
