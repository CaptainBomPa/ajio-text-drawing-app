package edu.mroz.components;

import edu.mroz.data.PointerParameters;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import static edu.mroz.AppConstants.CANVAS_HEIGHT;
import static edu.mroz.AppConstants.COMPONENTS_WIDTH;

public class Canvas extends JPanel {

    private final transient PointerParameters pointerParameters = PointerParameters.getInstance();
    private final List<ColoredShape> shapes = new ArrayList<>();
    @Getter
    private ColoredShape pointerAsShape;

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

    public void clearCanvas() {
        shapes.clear();
        pointerAsShape = createPointer();
        shapes.add(pointerAsShape);
        repaint();
    }

    public void drawLine(int x2, int y2) {
        Line2D line = new Line2D.Float(pointerParameters.getCurrentPointPosition().x, pointerParameters.getCurrentPointPosition().y, x2, y2);
        shapes.add(new ColoredShape(line, pointerParameters.getDrawingColor(), pointerParameters.shouldDraw()));
        pointerParameters.setCurrentPointPosition(new Point(x2, y2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (ColoredShape coloredShape : shapes) {
            if (coloredShape.getShape() instanceof CanvasPointer && coloredShape.isShouldDraw()) {
                paintPointer(g2d, (CanvasPointer) coloredShape.getShape());
            } else if (coloredShape.isShouldDraw()) {
                g2d.setColor(coloredShape.getColor());
                g2d.draw(coloredShape.getShape());
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

    private ColoredShape createPointer() {
        pointerParameters.movePointerToCenter();
        CanvasPointer canvasPointer = new CanvasPointer(pointerParameters.getCurrentPointPosition().x, pointerParameters.getCurrentPointPosition().y, 27);
        return new ColoredShape(canvasPointer, null, true);
    }
}
