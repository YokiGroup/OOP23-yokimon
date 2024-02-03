package io.github.yokigroup.battle;


public abstract class OpponentAI {
    /**
     *
     * @param curr_oppYokimon the Yokimon whose AI must be implemented
     * @return the most suitable attack for the opponent to use
     */
    public abstract Attack getMove(Yokimon curr_oppYokimon);
}