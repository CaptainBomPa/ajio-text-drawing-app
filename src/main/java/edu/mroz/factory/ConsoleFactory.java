package edu.mroz.factory;

import com.jidesoft.swing.JideButton;
import edu.mroz.components.Console;

import javax.swing.*;
import java.awt.*;

import static edu.mroz.AppConstants.*;

public class ConsoleFactory {

    public static Console createConsole() {
        JScrollPane logArea = createLogArea();
        JTextField commandField = createCommandField();
        JideButton commandButton = createCommandButton();
        return createConsole(logArea, commandField, commandButton);
    }

    private static Console createConsole(JScrollPane logArea, JTextField commandField, JideButton commandButton) {
        Console console = new Console();
        console.setSize(new Dimension(COMPONENTS_WIDTH, CONSOLE_HEIGHT));
        console.setPreferredSize(new Dimension(COMPONENTS_WIDTH, CONSOLE_HEIGHT));
        console.addLogArea(logArea);
        console.addCommandComponents(commandField, commandButton);
        return console;
    }

    private static JScrollPane createLogArea() {
        JTextPane logArea = new JTextPane();
        logArea.setEditable(false);

        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setPreferredSize(new Dimension(COMPONENTS_WIDTH, 150));
        logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        logArea.setSize(new Dimension(COMPONENTS_WIDTH, 150));
        logArea.setPreferredSize(new Dimension(COMPONENTS_WIDTH, 150));
        createDefaultBorder(logArea);
        return logScrollPane;
    }

    private static JTextField createCommandField() {
        JTextField commandField = new JTextField();
        commandField.setFont(new Font(commandField.getFont().getName(), Font.PLAIN, 22));
        commandField.setPreferredSize(new Dimension(COMPONENTS_WIDTH - CONSOLE_APPLY_BUTTON_WIDTH - 2, 35));
        commandField.setMargin(new Insets(0, 10, 0, 0));
        createDefaultBorder(commandField);
        return commandField;
    }

    private static JideButton createCommandButton() {
        JideButton commandButton = new JideButton("Apply");
        commandButton.setFont(new Font(commandButton.getFont().getName(), Font.PLAIN, 20));
        commandButton.setPreferredSize(new Dimension(CONSOLE_APPLY_BUTTON_WIDTH - 2, 35));
        createDefaultBorder(commandButton);
        return commandButton;
    }

    private static void createDefaultBorder(JComponent component) {
        component.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
