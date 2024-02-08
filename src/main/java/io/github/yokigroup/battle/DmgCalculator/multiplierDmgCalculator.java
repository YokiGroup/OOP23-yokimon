package io.github.yokigroup.battle.DmgCalculator;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

public class multiplierDmgCalculator implements DmgCalculator {

    private static final double MULTIPLIER = 1.2;

    /**
     * This version uses a multiplier in case a Yokimon and the attack are of the same color
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon the offended Yokimon
     * @param attack the attack used by the first one
     * @return the actual damage (to subtract from the HP of the attacked Yokimon)
     */
    @Override
    public int getDMG(Yokimon attackingYokimon, Yokimon attackedYokimon, Attack attack) {
        double total = (double)(attackingYokimon.getStat(Yokimon.Stats.ATK) * attack.attackPower()
                / attackedYokimon.getStat(Yokimon.Stats.DEF));

        if (attackingYokimon.getColor().equals(attack.color())) {
            total = total * MULTIPLIER;
        }
        if (attackedYokimon.getColor().equals(attack.color())) {
            total = total / MULTIPLIER;
        }

        return (int)total;
    }
}
