package io.github.yokigroup.view.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Observer for drawing sprites to screen.
 */
public final class JavaFXDrawObserver implements EObserver<SpriteData> {
    private final GraphicsContext gc;
    private Pair<String, Image> cachedSpriteImage = new Pair<>(null, null);

    /**
     * @param gc GraphicsContext of the game window Canvas
     */
    public JavaFXDrawObserver(final GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void update(final PublisherImpl<SpriteData> publisher, final SpriteData arg) {
        final String spriteURL = arg.getSpriteURL();
        final Canvas canvas = gc.getCanvas();
        if (!Objects.equals(cachedSpriteImage.x(), spriteURL)) {
            cachedSpriteImage = new Pair<>(spriteURL, new Image(arg.getSpriteURL()));
        }
        final Vector2 canvasDim = new Vector2Impl(canvas.getWidth(), canvas.getHeight());

        final Vector2 absSpriteDim = arg.getNormalizedDimension().times(canvasDim);
        final Vector2 absSpritePos = arg.getNormalizedPosition().times(canvasDim).minus(absSpriteDim.scale(.5));

        gc.drawImage(
                cachedSpriteImage.y(),
                absSpritePos.getX(),
                absSpritePos.getY(),
                absSpriteDim.getX(),
                absSpriteDim.getY()
        );
    }
}
