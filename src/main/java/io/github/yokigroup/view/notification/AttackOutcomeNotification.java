package io.github.yokigroup.view.notification;

import io.github.yokigroup.battle.fight.Fight;

public interface AttackOutcomeNotification extends Notification {
    enum Attacker {
        PLAYER,
        ENEMY
    }
    Fight.Success getAttackOutcome();
    Attacker getAttacker();

}
