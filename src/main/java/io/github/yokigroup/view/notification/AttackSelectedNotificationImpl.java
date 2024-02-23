package io.github.yokigroup.view.notification;

import io.github.yokigroup.battle.Attack;

public final class AttackSelectedNotificationImpl implements AttackSelectedNotification {
    private final Attack selectedAttack;

    public AttackSelectedNotificationImpl(final Attack selectedAttack) {
        this.selectedAttack = selectedAttack;
    }

    @Override
    public Attack getSelectedAttack() {
        return selectedAttack;
    }

    @Override
    public String getMessage(final NotificationVisitor visitor) {
        return visitor.getSelectedAttackNotiticationText(this);
    }
}
