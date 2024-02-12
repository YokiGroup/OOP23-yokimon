package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

/**
 * Complete version of Damage Calculator, that takes into consideration
 * the two Yokimons' color and the attack color, and the colors' hierarchy as well.
 */
public class FullImplDmgCalculator implements DmgCalculator {

    private static final double MULTIPLIER = 1.2;
    private static final double STRONG = 2.0;           //TODO MUST TEST THIS CLASS!!!!!!!!!!!
    private static final double NORMAL = 1.0;
    private static final double WEAK = 0.5;

    /**
     * This version uses a multiplier in case a Yokimon and the attack are of the same color.
     * It also considers a hierarchy which states as follows:
     * PURPLE beats RED beats BLACK beats PURPLE
     * WHITE is neutral
     *
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon the offended Yokimon
     * @param attack the attack used by the first one
     * @return the actual damage (to subtract from the HP of the attacked Yokimon)
     */
    @Override
    public int getDMG(final Yokimon attackingYokimon, final Yokimon attackedYokimon, final Attack attack) {

        double total = (double) (attackingYokimon.getStat(Yokimon.Stats.ATK) * attack.attackPower()
                / attackedYokimon.getStat(Yokimon.Stats.DEF));

        //same-color multiplier
        if (attackingYokimon.getYokimonColor().equals(attack.getColor())) {
            total = total * MULTIPLIER;
        }
        if (attackedYokimon.getYokimonColor().equals(attack.getColor())) {
            total = total / MULTIPLIER;
        }

        //hierarchy
        switch (attackingYokimon.getYokimonColor()) {
            case PURPLE -> {
                switch (attackedYokimon.getYokimonColor()) {
                    case BLACK -> {
                        total = total * WEAK;
                    }
                    case PURPLE, WHITE -> {
                        total = total * NORMAL;
                    }
                    case RED -> {
                        total = total * STRONG;
                    }
                }
            }
            case RED -> {
                switch (attackedYokimon.getYokimonColor()) {
                    case PURPLE -> {
                        total = total * WEAK;
                    }
                    case RED, WHITE -> {
                        total = total * NORMAL;
                    }
                    case BLACK -> {
                        total = total * STRONG;
                    }
                }
            }
            case BLACK -> {
                switch (attackedYokimon.getYokimonColor()) {
                    case RED -> {
                        total = total * WEAK;
                    }
                    case BLACK, WHITE -> {
                        total = total * NORMAL;
                    }
                    case PURPLE -> {
                        total = total * STRONG;
                    }
                }
            }
            case WHITE -> {
                total = total * NORMAL;
            }
        }

        return (int) total;
    }
}
