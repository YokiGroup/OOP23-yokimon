package io.github.yokigroup.view.notification;

import io.github.yokigroup.battle.attack.Attack;

/**
 * Implementation of {@link AttackSelectedNotification}.
 */
public final class AttackSelectedNotificationImpl implements AttackSelectedNotification {
    private final Attack selectedAttack;

    /**
     * @param selectedAttack attack to notify the selection of
     */
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
