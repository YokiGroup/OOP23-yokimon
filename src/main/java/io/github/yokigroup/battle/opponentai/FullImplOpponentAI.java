package io.github.yokigroup.battle.opponentai;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.Color;

import java.util.Optional;

/**
 * Fuller version of OpponentAI, responsible for choosing the next best move for the opponent to use.
 */
public class FullImplOpponentAI extends OpponentAI {

    private Attack lastUsed;

    /**
     * This version detects the most effective moves to use on the player's Yokimon, based on colors.
     * @param currMyYokimon Yokimon from player's party currently involved in the fight
     * @param currOppYokimon the Yokimon whose AI must be implemented
     * @return the most suitable attack for the opponent to use
     */
    @Override
    public Optional<Attack> getMove(final Yokimon currMyYokimon, final Yokimon currOppYokimon) {

        var Attacks = currOppYokimon.getAttacks();
        Optional<Attack> best = Optional.empty();
        Attack current;

        if (Attacks.isEmpty()) {
            return Optional.empty();
        }
        for (Attack value : Attacks) {
            current = value;

            //TODO: PART WHERE YOU DETECT THE MOST SUITABLE ATTACK

            //ONCE YOU DETECTED THE BEST
            if (!current.equals(lastUsed)) {
                best = Optional.of(current);
            }
        }

        best.ifPresent(attack -> lastUsed = attack);
        return best;
    }
}
