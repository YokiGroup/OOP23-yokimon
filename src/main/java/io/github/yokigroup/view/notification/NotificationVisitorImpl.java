package io.github.yokigroup.view.notification;

import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.view.render.painter.Painter;

/**
 * Implementation of {@link NotificationVisitor}, Also an observer listening for notification events and relaying them
 * to the view painter.
 */
public final class NotificationVisitorImpl implements NotificationVisitor, EObserver<Notification> {
    private final Painter painter;
    // FIXME locales

    /**
     * @param painter to relay the requests captured by the observer.
     */
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
        final String damageQuantityStr = switch (notification.getAttackOutcome()) {
            case FAIL -> "non ha fatto male";
            case GOOD -> "ha fatto male";
            case SUPER -> "ha fatto molto male";
            case WEAK -> "ha fatto poco male";
        };
        return switch (notification.getAttacker()) {
            case PLAYER -> "il tuo yokimon ";
            case ENEMY -> "lo yokimon nemico ";
        } + damageQuantityStr;
    }

    @Override
    public void update(final Notification lastArg, final Notification arg) {
        painter.setEventText(arg.getMessage(this));
    }
}
