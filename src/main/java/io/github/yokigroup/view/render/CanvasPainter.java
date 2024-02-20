package io.github.yokigroup.view.render;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CanvasPainter extends Painter {
    private final Map<String, Image> imageCache = new HashMap<>();
    private final GraphicsContext gc;
    private final Label eventLabel;
    private Pair<Long, String> currentNotification = null; // notification with timestamp

    private Vector2 getCanvasDim() {
        Canvas canvas = gc.getCanvas();
        return new Vector2Impl(canvas.getWidth(), canvas.getHeight());
    }

    private Image consultCache(final String resourceURL) {
        if (!imageCache.containsKey(resourceURL)) {
            imageCache.put(resourceURL, new Image(resourceURL));
        }
        return imageCache.get(resourceURL);
    }

    public CanvasPainter(final GraphicsContext gc, final Label eventLabel) {
        super(new DrawQueueImpl());
        gc.setImageSmoothing(false);
        gc.setTextAlign(TextAlignment.CENTER);
        this.gc = gc;
        this.eventLabel = eventLabel;
    }

    public CanvasPainter(final GraphicsContext gc, final Label eventLabel, final DrawQueueReader drawQueue) {
        super(drawQueue);
        gc.setImageSmoothing(false);
        gc.setTextAlign(TextAlignment.CENTER);
        this.gc = gc;
        this.eventLabel = eventLabel;
    }

    private void paint(final SpriteData sprite) {
        if (currentNotification != null &&
                currentNotification.x() < System.currentTimeMillis()) {
            currentNotification = null;
            eventLabel.setText("");
        }
        final Vector2 canvasDim = getCanvasDim();
        final Vector2 absSpriteDim = sprite.getNormalizedDimension().times(canvasDim);
        final Vector2 absSpritePos = sprite.getNormalizedPosition().times(canvasDim).minus(absSpriteDim.scale(.5));

        gc.drawImage(
                consultCache(sprite.spriteURL()),
                absSpritePos.getX() + (sprite.flipped() ? absSpriteDim.getX() : 0),
                absSpritePos.getY(),
                absSpriteDim.getX() * (sprite.flipped() ? -1 : 1),
                absSpriteDim.getY()
        );
        if (currentNotification != null) {
            eventLabel.setFont(new Font(canvasDim.getX()/20));
            eventLabel.setText(currentNotification.y());
        }
    }

    private Set<SpriteData> filterOutNullSpriteData(final Set<SpriteData> sprites) {
        return sprites.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @Override
    public void paintEventText(final String eventText) {
        final long waitTime = 3000; // 3 seconds
        currentNotification = new Pair<>(System.currentTimeMillis() + waitTime, eventText);
    }

    @Override
    public void repaint() {
        synchronized (this) {
            drawQueue().stream().forEach(this::paint);
        }
    }
}
