package battle;

public class FightImpl implements Fight {

    /* parties */
    private final List<Yokimon> myYokimons;
    private final List<Yokimon> oppYokimons;
    private Yokimon curr_myYokimon;
    private Yokimon curr_oppYokimon;

    /* structures */
    private final OpponentAI oppAI = null;
    private final XPCalculator XPCalc = null;
    private final NextYokimon nextYok = null;

    /* boolean that triggers end of fight */
    private boolean isOver;

    @Override
    public success attack(Attack myAttack) {
        return null;
    }

    @Override
    public success getAttacked() {
        return null;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public boolean victory() {
        return false;
    }

    @Override
    public int getXP(Yokimon yokimon) {
        return 0;
    }

    @Override
    public Yokimon getCurrentMyYokimon() {
        return curr_myYokimon;
    }

    @Override
    public Yokimon getCurrentOpponent() {
        return curr_oppYokimon;
    }

    /* utilities to update Yokimons involved in fight */
    private void update_myCurr() {};
    private void update_oppCurr() {};
}
