package io.github.yokigroup.view.notification;

public interface NotificationVisitor {
    String getLevelUpNotificationText(LevelUpNotification notification);
    String getNewYokimonNotificationText(NewYokimonNotification notification);
    String getDeathNotificationText(DeathNotification notification);
    String getSelectedAttackNotiticationText(AttackSelectedNotification notification);
    String getAttackOutcomeNotificationText(AttackOutcomeNotification notification);
}
