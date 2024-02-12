package io.github.yokigroup.battle.opponentai;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.dmgcalculator.DmgCalculator;
import java.util.Random;

import java.util.Optional;

/**
 * Fuller version of OpponentAI, responsible for choosing the next best move for the opponent to use.
 */
public class FullImplOpponentAI extends OpponentAI {

    private Optional<Attack> lastUsed = Optional.empty();
    private final DmgCalculator dmgCalc;
    private final Random rand = new Random();

    /**
     * The DmgCalculator is essential to assert which move is the best one.
     * @param newDamageCalculator the required damage calculator.
     */
    public FullImplOpponentAI (DmgCalculator newDamageCalculator) {
        dmgCalc = newDamageCalculator;
    }

    /**
     * It alternates the most effective move (based on DmgCalculator) with a random one,
     * chosen from the available attacks list.
     *
     * @param currMyYokimon Yokimon from player's party currently involved in the fight
     * @param currOppYokimon the Yokimon whose AI must be implemented
     * @return the most suitable attack for the opponent to use
     */
    @Override
    public Optional<Attack> getMove(final Yokimon currMyYokimon, final Yokimon currOppYokimon) {

        var Attacks = currOppYokimon.getAttacks();
        Optional<Attack> best = Optional.empty();

        int maxValue = 0;

        //there are no available attacks
        if (Attacks.isEmpty()) {
            return Optional.empty();
        }

        //there are available attacks
        for (Attack atk : Attacks) {

            int atkValue = dmgCalc.getDMG(currMyYokimon, currOppYokimon, atk);
            if (atkValue >= maxValue) {
                maxValue = atkValue;
                best = Optional.of(atk);
            }
        }
        //FIXME
        //In case the opponent Yokimon has recently used the most suitable attack,
        //a random one from the list is used instead.
        if (best.isPresent() && (lastUsed.isPresent() && lastUsed.get().equals(best.get()))) {
            best = Optional.of(Attacks.get(rand.nextInt(Attacks.size())));
        }
        best.ifPresent(attack -> lastUsed = Optional.of(attack));
        return best;
    }
}
