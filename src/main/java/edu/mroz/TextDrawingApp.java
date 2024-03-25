package edu.mroz;

import edu.mroz.components.Canvas;
import edu.mroz.components.Console;
import edu.mroz.factory.CanvasFactory;
import edu.mroz.factory.ConsoleFactory;
import edu.mroz.factory.MainFrameFactory;
import edu.mroz.factory.ToolbarFactory;
import edu.mroz.interpreter.ConsoleLogAppender;
import edu.mroz.interpreter.Interpreter;

import javax.swing.*;
import java.awt.*;

public class TextDrawingApp {

    private final JToolBar toolBar;
    private final Canvas canvas;
    private final Console console;

    private final Interpreter interpreter = Interpreter.getInstance();
    private final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();

    public TextDrawingApp() {
        this.toolBar = ToolbarFactory.createToolbar();
        this.canvas = CanvasFactory.createCanvas();
        this.console = ConsoleFactory.createConsole();
        createFrame();
        consoleLogAppender.setConsole(console);
    }

    private void createFrame() {
        JFrame mainFrame = MainFrameFactory.createMainFrame();
        mainFrame.setBackground(Color.gray);

        mainFrame.add(toolBar, BorderLayout.NORTH);
        mainFrame.add(wrapWithJPanel(canvas), BorderLayout.CENTER);
        mainFrame.add(console, BorderLayout.SOUTH);

        addConsoleListener();
        mainFrame.setVisible(true);
        console.getCommandField().requestFocusInWindow();
    }

    private void addConsoleListener() {
        console.getCommandButton().addActionListener(action -> {
            consoleLogAppender.addUserPromptLog(console.getCommandField().getText());
            final String userCommand = console.getCommandField().getText();
            console.getCommandField().setText("");
            interpreter.interpretCommand(userCommand, canvas);
        });
    }

    private JPanel wrapWithJPanel(JComponent component) {
        JPanel panel = new JPanel();
        panel.add(component);
        return panel;
    }
}
