package io.github.yokigroup.view.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.Painter;

import java.util.Set;

public class DrawSetObserver implements EObserver<Set<SpriteData>> {
    private final Painter painter;

    public DrawSetObserver(final Painter painter) {
        this.painter = painter;
    }

    @Override
    public void update(final PublisherImpl<Set<SpriteData>> publisher, final Set<SpriteData> lastArg, final Set<SpriteData> arg) {
        if (lastArg != null) {
            lastArg.forEach(painter::removeFromPersistentDrawQueue);
        }
        arg.forEach(painter::addToPersistentDrawQueue);
        painter.repaint();
    }
}
