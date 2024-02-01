package battle;


public abstract class OpponentAI {
    /**
     *
     * @param opponent the Yokimon whose AI must be implemented
     * @return the most suitable attack for the opponent to use
     */
    abstract Attack getMove(Yokimon opponent);
}