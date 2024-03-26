package edu.mroz;

import edu.mroz.components.Canvas;
import edu.mroz.components.Console;
import edu.mroz.factory.CanvasFactory;
import edu.mroz.factory.ConsoleFactory;
import edu.mroz.factory.MainFrameFactory;
import edu.mroz.factory.ToolbarFactory;
import edu.mroz.interpreter.Interpreter;
import edu.mroz.toolbar.ToolbarAdapter;
import edu.mroz.utils.ConsoleLogAppender;

import javax.swing.*;
import java.awt.*;

public class TextDrawingApp {

    private final ToolbarAdapter toolbarAdapter;
    private final Canvas canvas;
    private final Console console;

    private final Interpreter interpreter = Interpreter.getInstance();
    private final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();

    public TextDrawingApp() {
        this.canvas = CanvasFactory.createCanvas();
        this.toolbarAdapter = ToolbarFactory.createToolbar(canvas);
        this.console = ConsoleFactory.createConsole();
        createFrame();
        consoleLogAppender.setConsole(console);
    }

    private void createFrame() {
        JFrame mainFrame = MainFrameFactory.createMainFrame();
        mainFrame.setBackground(Color.gray);

        mainFrame.add(toolbarAdapter.getToolBar(), BorderLayout.NORTH);
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
