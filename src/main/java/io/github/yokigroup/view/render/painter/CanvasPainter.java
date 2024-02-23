package io.github.yokigroup.view.render.painter;

import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.drawqueue.DrawQueue;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.HashMap;
import java.util.Map;

public class CanvasPainter extends Painter {
    private final Map<String, Image> imageCache = new HashMap<>();
    private final GraphicsContext gc;
    private final Label eventLabel;
    private Pair<Long, String> currentNotification; // notification with timestamp
    private static final int CANVAS_DIM_DIVISOR = 20;

    private Vector2 getCanvasDim() {
        final Canvas canvas = gc.getCanvas();
        return new Vector2Impl(canvas.getWidth(), canvas.getHeight());
    }

    private Image consultCache(final String resourceURL) {
        if (!imageCache.containsKey(resourceURL)) {
            imageCache.put(resourceURL, new Image(resourceURL));
        }
        return imageCache.get(resourceURL);
    }

    public CanvasPainter(final GraphicsContext gc, final Label eventLabel) {
        super();
        gc.setImageSmoothing(false);
        gc.setTextAlign(TextAlignment.CENTER);
        this.gc = gc;
        this.eventLabel = eventLabel;
    }

    private void paint(final SpriteData sprite) {
        if (currentNotification != null
                && currentNotification.x() < System.currentTimeMillis()) {
            currentNotification = null;
            eventLabel.setText("");
        }
        final Vector2 canvasDim = getCanvasDim();
        final Vector2 absSpriteDim = sprite.getNormalizedDimension().times(canvasDim);
        final Vector2 absSpritePos = sprite.getNormalizedPosition().times(canvasDim).minus(absSpriteDim.scale(.5));

        eventLabel.setFont(new Font(canvasDim.getX() / CANVAS_DIM_DIVISOR));

        gc.drawImage(
                consultCache(sprite.spriteURL()),
                absSpritePos.getX() + (sprite.flipped() ? absSpriteDim.getX() : 0),
                absSpritePos.getY(),
                absSpriteDim.getX() * (sprite.flipped() ? -1 : 1),
                absSpriteDim.getY()
        );
        if (currentNotification != null) {
            eventLabel.setText(currentNotification.y());
        }
    }

    @Override
    public void setEventText(final String eventText) {
        final long waitTime = 3000; // 3 seconds
        currentNotification = new Pair<>(System.currentTimeMillis() + waitTime, eventText);
    }

    @Override
    public void repaint() {
        final DrawQueue drawQueue = drawQueue(getPaintState());
        synchronized (drawQueue) {
            drawQueue.stream().forEach(this::paint);
        }
    }

    @Override
    public void safeDraw() {
        Platform.runLater(this::repaint);
    }
}
