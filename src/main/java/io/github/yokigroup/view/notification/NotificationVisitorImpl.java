package io.github.yokigroup.view.notification;

import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.view.render.painter.Painter;

import java.util.function.Consumer;

/**
 * Implementation of {@link NotificationVisitor}, Also an observer listening for notification events and relaying them
 * to the view painter.
 */
public final class NotificationVisitorImpl implements NotificationVisitor, EObserver<Notification> {
    private final Consumer<String> setGlobalEventString;
    // FIXME locales

    /**
     * @param painter to relay the requests captured by the observer.
     */
    public NotificationVisitorImpl(final Painter painter) {
        this.setGlobalEventString = painter::setEventText;
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
            case DEFEATED_IN_BATTLE -> "You have been defeated...";
            case UNPREPARED_FOR_BATTLE -> "You cannot fight with no Yokimons";
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
            case FAIL -> "failed";
            case GOOD -> "was effective";
            case SUPER -> "was very effective";
            case WEAK -> "was rather weak";
        };
        return switch (notification.getAttacker()) {
            case PLAYER -> "Your attack ";
            case ENEMY -> "The opponent's attack ";
        } + damageQuantityStr;
    }

    @Override
    public void update(final Notification lastArg, final Notification arg) {
        this.setGlobalEventString.accept(arg.getMessage(this));
    }
}
