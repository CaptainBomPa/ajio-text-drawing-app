package edu.mroz.components;

import edu.mroz.data.PointerParameters;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import static edu.mroz.AppConstants.CANVAS_HEIGHT;
import static edu.mroz.AppConstants.COMPONENTS_WIDTH;

public class Canvas extends JPanel {

    private final transient PointerParameters pointerParameters = PointerParameters.getInstance();
    @Getter
    @Setter
    private List<ExtendedShapeWrapper> shapes = new ArrayList<>();
    @Getter
    private ExtendedShapeWrapper pointerAsShape;

    public Canvas() {
        super();
        setMinimumSize(new Dimension(COMPONENTS_WIDTH, CANVAS_HEIGHT));
        setMaximumSize(new Dimension(COMPONENTS_WIDTH, CANVAS_HEIGHT));
        setPreferredSize(new Dimension(COMPONENTS_WIDTH, CANVAS_HEIGHT));
        setSize(new Dimension(COMPONENTS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.white);

        pointerAsShape = createPointer();
        shapes.add(pointerAsShape);
    }

    public void removeLastShape() {
        ExtendedShapeWrapper shape = shapes.remove(shapes.size() - 1);
        pointerParameters.setCurrentPointPosition(shape.getLastPointerPosition());
        pointerParameters.setDirection(shape.getDirection());
    }

    public void clearCanvas() {
        shapes.clear();
        pointerAsShape = createPointer();
        shapes.add(pointerAsShape);
        repaint();
    }

    public void drawLine(int x2, int y2) {
        int x1 = pointerParameters.getCurrentPointPosition().x;
        int y1 = pointerParameters.getCurrentPointPosition().y;

        x2 = Math.max(0, Math.min(x2, COMPONENTS_WIDTH - 1));
        y2 = Math.max(0, Math.min(y2, CANVAS_HEIGHT - 1));

        Line2D line = new Line2D.Float(x1, y1, x2, y2);
        shapes.add(new ExtendedShapeWrapper(line, pointerParameters));
        pointerParameters.setCurrentPointPosition(new Point(x2, y2));
    }

    public void drawCircle(int r, double percent, boolean clockwise) {
        int xStart = pointerParameters.getCurrentPointPosition().x;
        int yStart = pointerParameters.getCurrentPointPosition().y;
        int direction = pointerParameters.getDirection();

        int startAngle = (360 - direction + 90) % 360;

        int arcAngle = (int) (percent / 100.0 * 360);
        if (clockwise) {
            startAngle -= arcAngle;
        }
        int xCenter = xStart - r;
        int yCenter = yStart - r;
        Arc2D.Double circle = new Arc2D.Double(xCenter, yCenter, 2 * r, 2 * r, startAngle, arcAngle, Arc2D.OPEN);
        shapes.add(new ExtendedShapeWrapper(circle, pointerParameters));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (ExtendedShapeWrapper extendedShapeWrapper : shapes) {
            g2d.setStroke(extendedShapeWrapper.getStroke());
            if (extendedShapeWrapper.getShape() instanceof CanvasPointer && extendedShapeWrapper.isShouldDraw()) {
                paintPointer(g2d, (CanvasPointer) extendedShapeWrapper.getShape());
            } else if (extendedShapeWrapper.isShouldDraw()) {
                g2d.setColor(extendedShapeWrapper.getColor());
                g2d.draw(extendedShapeWrapper.getShape());
            }
        }
    }

    private void paintPointer(Graphics2D g2d, CanvasPointer canvasPointer) {
        float[] dashPattern = {6, 3};
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        g2d.setColor(Color.DARK_GRAY);
        canvasPointer.setPositionAndDirection(pointerParameters.getCurrentPointPosition().x, pointerParameters.getCurrentPointPosition().y, pointerParameters.getDirection());
        g2d.draw(canvasPointer);
        g2d.setStroke(new BasicStroke());
    }

    private ExtendedShapeWrapper createPointer() {
        pointerParameters.movePointerToCenter();
        CanvasPointer canvasPointer = new CanvasPointer(pointerParameters.getCurrentPointPosition().x, pointerParameters.getCurrentPointPosition().y, 27);
        return new ExtendedShapeWrapper(canvasPointer);
    }
}
