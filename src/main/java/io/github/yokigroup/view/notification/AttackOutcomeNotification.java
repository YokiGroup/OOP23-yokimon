package io.github.yokigroup.view.notification;

import io.github.yokigroup.battle.fight.Fight;

public interface AttackOutcomeNotification extends Notification {
    Fight.Success getAttackOutcome();
}
