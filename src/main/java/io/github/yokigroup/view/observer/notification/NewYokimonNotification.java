package io.github.yokigroup.view.observer.notification;

import io.github.yokigroup.battle.Yokimon;

public interface NewYokimonNotification extends Notification {
    Yokimon getNewYokimon();
}
