package edu.mroz.factory;

import edu.mroz.AppConstants;

import javax.swing.*;
import java.awt.*;

public class MainFrameFactory {

    private MainFrameFactory() {
    }

    public static JFrame createMainFrame() {
        JFrame frame = new JFrame(AppConstants.APP_NAME);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(AppConstants.MAIN_FRAME_WIDTH, AppConstants.MAIN_FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setBackground(Color.green);
        return frame;
    }

}
