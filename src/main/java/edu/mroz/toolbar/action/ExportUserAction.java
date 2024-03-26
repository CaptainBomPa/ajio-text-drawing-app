package edu.mroz.toolbar.action;

import edu.mroz.components.Canvas;
import edu.mroz.utils.ConsoleLogAppender;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportUserAction {

    private static final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();
    private final Canvas canvas;

    public ExportUserAction(JMenuItem menuItem, Canvas canvas) {
        this.canvas = canvas;
        menuItem.addActionListener(new ExportActionListener());
    }

    private class ExportActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG images", "png"));
            int userSelection = fileChooser.showSaveDialog(canvas);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().toLowerCase().endsWith(".png")) {
                    file = new File(file.getParentFile(), file.getName() + ".png");
                }
                exportCanvasAsPNG(file);
            }
        }

        private void exportCanvasAsPNG(File file) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            canvas.paint(g2d);
            g2d.dispose();
            try {
                ImageIO.write(bufferedImage, "PNG", file);
                consoleLogAppender.addInfoSystemLog("Export successful!");
            } catch (IOException ex) {
                ex.printStackTrace();
                consoleLogAppender.addInfoSystemLog("Error during export");
            }
        }
    }
}
