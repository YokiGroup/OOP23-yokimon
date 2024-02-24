package io.github.yokigroup.view.notification;

import io.github.yokigroup.battle.fight.Fight;

public final class AttackOutcomeNotificationImpl implements AttackOutcomeNotification {
    private final Fight.Success attackOutcome;
    private final Attacker attacker;

    public AttackOutcomeNotificationImpl(final Fight.Success attackOutcome, Attacker attacker) {
        this.attackOutcome = attackOutcome;
        this.attacker = attacker;
    }

    @Override
    public Fight.Success getAttackOutcome() {
        return attackOutcome;
    }

    @Override
    public Attacker getAttacker() {
        return attacker;
    }

    @Override
    public String getMessage(final NotificationVisitor visitor) {
        return visitor.getAttackOutcomeNotificationText(this);
    }
}
