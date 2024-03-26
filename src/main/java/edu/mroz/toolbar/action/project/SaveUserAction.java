package edu.mroz.toolbar.action.project;

import edu.mroz.components.Canvas;
import edu.mroz.data.PointerParameters;
import edu.mroz.utils.ConsoleLogAppender;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveUserAction {

    private final Canvas canvas;
    private final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();
    private final PointerParameters pointerParameters = PointerParameters.getInstance();

    public SaveUserAction(JMenuItem menuItem, Canvas canvas) {
        this.canvas = canvas;
        menuItem.addActionListener(new SaveActionListener());
    }

    private class SaveActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text to drawing format (*.ttdf)", "ttdf"));
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    if (!filePath.toLowerCase().endsWith(".ttdf")) {
                        filePath += ".ttdf";
                    }

                    FileOutputStream fileOut = new FileOutputStream(filePath);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);

                    ProjectExportData projectExportData = new ProjectExportData(pointerParameters, canvas.getShapes());
                    out.writeObject(projectExportData);

                    out.close();
                    fileOut.close();

                    consoleLogAppender.addInfoSystemLog("Saved project to " + filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    consoleLogAppender.addErrorSystemLog("Error saving project");
                }
            }
        }
    }
}
