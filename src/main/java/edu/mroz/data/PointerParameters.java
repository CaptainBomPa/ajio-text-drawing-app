package edu.mroz.data;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;

import static edu.mroz.AppConstants.CANVAS_HEIGHT;
import static edu.mroz.AppConstants.COMPONENTS_WIDTH;

public class PointerParameters implements Serializable {

    private static PointerParameters instance;

    @Getter
    @Setter
    private Point currentPointPosition;
    @Getter
    @Setter
    private Integer direction;
    @Getter
    @Setter
    private Color drawingColor;
    @Getter
    @Setter
    private PointerState pointerState;
    @Getter
    @Setter
    private float strokeSize;
    @Getter
    @Setter
    private DrawingStyle drawingStyle;

    private PointerParameters() {
        currentPointPosition = new Point(COMPONENTS_WIDTH / 2, CANVAS_HEIGHT / 2);
        direction = 0;
        drawingColor = Color.BLACK;
        pointerState = PointerState.DOWN;
        strokeSize = 1.0f;
        drawingStyle = DrawingStyle.LINE;
    }

    public void movePointerToCenter() {
        currentPointPosition = new Point(COMPONENTS_WIDTH / 2, CANVAS_HEIGHT / 2);
    }

    public static synchronized PointerParameters getInstance() {
        if (instance == null) {
            instance = new PointerParameters();
        }
        return instance;
    }

    public boolean shouldDraw() {
        return pointerState != PointerState.UP;
    }

    public void restore(PointerParameters pointerParameters) {
        this.currentPointPosition = pointerParameters.getCurrentPointPosition();
        this.direction = pointerParameters.getDirection();
        this.drawingColor = pointerParameters.getDrawingColor();
        this.pointerState = pointerParameters.getPointerState();
    }
}
