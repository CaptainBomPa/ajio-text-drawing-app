package edu.mroz.components;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class Console extends JPanel {

    private JScrollPane logArea;
    private JTextField commandField;
    private JButton commandButton;

    public void addLogArea(JScrollPane logArea) {
        this.logArea = logArea;
        add(this.logArea, BorderLayout.NORTH);
    }

    public void addCommandComponents(JTextField commandField, JButton commandButton) {
        this.commandField = commandField;
        this.commandButton = commandButton;
        applyEnterBehavior(commandField);
        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        commandPanel.add(commandField);
        commandPanel.add(commandButton);
        add(commandPanel, BorderLayout.SOUTH);
    }

    private void applyEnterBehavior(JTextField commandField) {
        commandField.addActionListener(action -> {
            commandButton.doClick();
        });
    }
}
