package battle;

public class FightImpl implements Fight {

    private final Yokimon myYokimon;
    private final Yokimon opponent;
    //private final OpponentAI myOpponentAI;
    private boolean isOver;


    /**
     * default builder for the fight
     * @param myYokimon my party's Yokimon involved in the fight
     * @param opponent my Yokimon's opponent
     */
    public FightImpl(Yokimon myYokimon, Yokimon opponent) {
        this.myYokimon = myYokimon;
        this.opponent = opponent;
    }

    /**
     * FOR DEBUG USE ONLY to try out different AIs
     * @param e the AI that must be tested
     */
    public void setEnemyAI(OpponentAI e) {
        this.myEnemyAIImpl = e;
    }


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
        return isOver;
    }

    @Override
    public boolean victory() {
        return false;
    }
}
