package battle;

@FunctionalInterface
public interface OpponentAI {
    /**
     *
     * @param opponent the Yokimon whose AI must be implemented
     * @return the most suitable attack for the opponent to use
     */
    Attack getMove(Yokimon opponent);
}