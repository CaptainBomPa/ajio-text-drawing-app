package edu.mroz.toolbar.action;

import edu.mroz.components.Canvas;
import edu.mroz.utils.ConsoleLogAppender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowHidePointerUserAction {

    private final Canvas canvas;
    private final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();

    public ShowHidePointerUserAction(JButton button, Canvas canvas) {
        this.canvas = canvas;
        button.addActionListener(new ShowHidePointerActionListener());
    }

    private class ShowHidePointerActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.getPointerAsShape().setShouldDraw(!canvas.getPointerAsShape().isShouldDraw());
            canvas.repaint();
            addLog();
        }

        private void addLog() {
            if (canvas.getPointerAsShape().isShouldDraw()) {
                consoleLogAppender.addInfoSystemLog("The Pointer is hidden");
            } else {
                consoleLogAppender.addInfoSystemLog("The Pointer is visible");
            }
        }
    }

}
