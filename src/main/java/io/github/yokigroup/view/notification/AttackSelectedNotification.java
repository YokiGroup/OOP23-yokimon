package io.github.yokigroup.view.notification;

import io.github.yokigroup.battle.attack.Attack;

public interface AttackSelectedNotification extends Notification {
    Attack getSelectedAttack();
}
