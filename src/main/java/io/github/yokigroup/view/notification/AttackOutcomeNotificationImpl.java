package io.github.yokigroup.view.notification;

import io.github.yokigroup.battle.fight.Fight;

public class AttackOutcomeNotificationImpl implements AttackOutcomeNotification {
    private final Fight.Success attackOutcome;

    public AttackOutcomeNotificationImpl(final Fight.Success attackOutcome) {
        this.attackOutcome = attackOutcome;
    }

    @Override
    public Fight.Success getAttackOutcome() {
        return attackOutcome;
    }

    @Override
    public String getMessage(final NotificationVisitor visitor) {
        return visitor.getAttackOutcomeNotificationText(this);
    }
}
