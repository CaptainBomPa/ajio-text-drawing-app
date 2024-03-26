package edu.mroz.toolbar;

import edu.mroz.components.Canvas;
import edu.mroz.utils.ConsoleLogAppender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearUserAction {

    private static final String CONFIRMATION_MESSAGE = "Are you sure you want to clean the canvas?";
    private final Canvas canvas;
    private final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();

    public ClearUserAction(JButton button, Canvas canvas) {
        this.canvas = canvas;
        button.addActionListener(new ClearActionListener());
    }

    private class ClearActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(canvas, CONFIRMATION_MESSAGE, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                canvas.clearCanvas();
                consoleLogAppender.clearLogs();
                consoleLogAppender.addInfoSystemLog("The Canvas has been cleaned.");
            }

        }
    }
}
