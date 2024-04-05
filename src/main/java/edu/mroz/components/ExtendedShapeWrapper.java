package edu.mroz.components;

import edu.mroz.data.PointerParameters;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;

@Getter
public class ExtendedShapeWrapper implements Serializable {

    private final Shape shape;
    private final Color color;
    @Setter
    private boolean shouldDraw;
    private final Point lastPointerPosition;
    private final Integer direction;
    private final Stroke stroke;

    public ExtendedShapeWrapper(Shape shape, PointerParameters pointerParameters) {
        this.shape = shape;
        this.color = pointerParameters.getDrawingColor();
        this.shouldDraw = pointerParameters.shouldDraw();
        this.lastPointerPosition = pointerParameters.getCurrentPointPosition();
        this.direction = pointerParameters.getDirection();
        this.stroke = pointerParameters.getStroke();
    }

    public ExtendedShapeWrapper(Shape shape) {
        this.shape = shape;
        this.color = Color.black;
        this.shouldDraw = true;
        this.lastPointerPosition = null;
        this.direction = null;
        this.stroke = new BasicStroke(1.0f);
    }
}
