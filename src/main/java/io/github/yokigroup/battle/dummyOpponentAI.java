package io.github.yokigroup.battle;

public class dummyOpponentAI extends OpponentAI {

    @Override
    Attack getMove(Yokimon curr_oppYokimon) {
        return new Attack() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }
}
