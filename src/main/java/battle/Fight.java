package battle;
import java.util.Optional;

public interface Fight {

    /**
     * different success rates trigger a different quote on the view
     */
    enum success {
        GOOD, SUPER, WEAK, FAIL
    }

    /**
     *
     * @param myAttack the attack that's meant to be used by my Yokimon
     * @return success rate over my Yokimon's attack
     */
    success attack(Attack myAttack);

    /**
     *
     * @param opponent my Yokimon's opponent
     * @return success rate over opponent's attack
     */
    success getAttacked(Yokimon opponent);

    /**
     * triggers end of the fight
     * @return to Logic whether the fight is over
     */
    boolean isOver();

    /**
     * @return whether my Yokimon won
     */
    boolean victory();
}
