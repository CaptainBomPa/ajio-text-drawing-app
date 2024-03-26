package edu.mroz.toolbar.action;

import edu.mroz.components.Canvas;
import edu.mroz.components.ColoredShape;
import edu.mroz.utils.ConsoleLogAppender;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class LoadUserAction {

    private final Canvas canvas;
    private final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();

    public LoadUserAction(JMenuItem menuItem, Canvas canvas) {
        this.canvas = canvas;
        menuItem.addActionListener(new LoadActionListener());
    }

    private class LoadActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a file to load");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text to drawing format (*.ttdf)", "ttdf"));
            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    String filePath = fileChooser.getSelectedFile().getPath();

                    FileInputStream fileIn = new FileInputStream(filePath);
                    ObjectInputStream in = new ObjectInputStream(fileIn);

                    @SuppressWarnings("unchecked")
                    List<ColoredShape> shapes = (List<ColoredShape>) in.readObject();

                    in.close();
                    fileIn.close();

                    canvas.clearCanvas();
                    canvas.setShapes(shapes);
                    consoleLogAppender.addInfoSystemLog("Loaded shapes from " + filePath);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    consoleLogAppender.addErrorSystemLog("Error loading project");
                }
            }
        }
    }
}
