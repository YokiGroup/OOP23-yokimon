package io.github.yokigroup.battle;

public class dummyImplOpponentAI extends OpponentAI {

    /**
     *
     * @param curr_oppYokimon the Yokimon whose AI must be implemented
     * @return the first attack on the attacks list
     */
    @Override
    public Attack getMove(Yokimon curr_oppYokimon) {
        return curr_oppYokimon.getAttacks().get(0);
    }
}
