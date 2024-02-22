package io.github.yokigroup.view.notification;

import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.view.render.Painter;

public class NotificationVisitorImpl implements NotificationVisitor, EObserver<Notification> {
    private final Painter painter;
    // FIXME locales


    public NotificationVisitorImpl(final Painter painter) {
        this.painter = painter;
    }

    @Override
    public String getLevelUpNotificationText(final LevelUpNotification not) {
        final String levelUpMsg = "%s leveled up to level %d!";
        return String.format(levelUpMsg, not.yokimonName(), not.levelReached());
    }

    @Override
    public String getNewYokimonNotificationText(final NewYokimonNotification notification) {
        final String newYokimonMsg = "Obtained %s";
        return String.format(newYokimonMsg, notification.yokimonName());
    }

    @Override
    public String getDeathNotificationText(final DeathNotification notification) {
        return switch (notification.getCause()) {
            case DEFEATED_IN_BATTLE -> "Sei stato sconfitto...";
            case UNPREPARED_FOR_BATTLE -> "Senza yokimon non puoi combattere";
        };
    }

    @Override
    public String getSelectedAttackNotiticationText(final AttackSelectedNotification notification) {
        final String attackSelectedMsg = "Selected %s";
        return String.format(attackSelectedMsg, notification.getSelectedAttack().getName());
    }

    @Override
    public String getAttackOutcomeNotificationText(final AttackOutcomeNotification notification) {
        return switch (notification.getAttackOutcome()) {
            case FAIL -> "non hai fatto male :(";
            case GOOD -> "hai fatto male :)";
            case LOSS -> "sei morto HAHAHA";
            case SUPER -> "HAI FATTO UN MALE CANE";
            case VICTORY -> "HAI VINTO";
            case WEAK -> "hai fatto poco male :(";
        };
    }

    @Override
    public void update(final Notification lastArg, final Notification arg) {
        painter.setEventText(arg.getMessage(this));
    }
}
