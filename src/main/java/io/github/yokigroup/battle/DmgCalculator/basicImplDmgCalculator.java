package io.github.yokigroup.battle.DmgCalculator;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

/**
 * Basic version for Damage Calculator, that only takes into consideration
 * the two Yokimons' stats and the attack power
 */
public final class basicImplDmgCalculator implements DmgCalculator {
    @Override
    public int getDMG(Yokimon attackingYokimon, Yokimon attackedYokimon, Attack attack) {
        return (int)(attackingYokimon.getStat(Yokimon.Stats.ATK)*attack.attackPower()
                /attackedYokimon.getStat(Yokimon.Stats.DEF));
    }
}
