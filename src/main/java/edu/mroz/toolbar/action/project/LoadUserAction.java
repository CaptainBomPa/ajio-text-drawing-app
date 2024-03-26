package edu.mroz.toolbar.action.project;

import edu.mroz.components.Canvas;
import edu.mroz.data.PointerParameters;
import edu.mroz.utils.ConsoleLogAppender;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadUserAction {

    private final Canvas canvas;
    private final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();
    private final PointerParameters pointerParameters = PointerParameters.getInstance();

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

                    ProjectExportData projectExportData = (ProjectExportData) in.readObject();

                    in.close();
                    fileIn.close();

                    canvas.clearCanvas();
                    pointerParameters.restore(projectExportData.getPointerParameters());
                    canvas.setShapes(projectExportData.getShapes());
                    consoleLogAppender.addInfoSystemLog("Loaded shapes from " + filePath);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    consoleLogAppender.addErrorSystemLog("Error loading project");
                }
            }
        }
    }
}
