package edu.mroz.factory;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class ToolbarFactory {

    private ToolbarFactory() {
    }

    public static JToolBar createToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(createSaveButton());
        toolBar.add(createClearButton());
        toolBar.add(createShowHidePointerButton());
        toolBar.add(createHistoryButton());
        toolBar.add(createHelpButton());
        return toolBar;
    }

    private static JButton createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setToolTipText("Save");
        saveButton.addActionListener(action -> log.info("Clicked Save Button"));
        return saveButton;
    }

    private static JButton createClearButton() {
        JButton clearButton = new JButton("Clear");
        clearButton.setToolTipText("Clear");
        clearButton.addActionListener(action -> log.info("Clicked Clear Button"));
        return clearButton;
    }

    private static JButton createHelpButton() {
        JButton helpButton = new JButton("Help");
        helpButton.setToolTipText("Help");
        helpButton.addActionListener(action -> log.info("Clicked Help Button"));
        return helpButton;
    }

    private static JButton createShowHidePointerButton() {
        JButton helpButton = new JButton("Show/Hide Pointer");
        helpButton.setToolTipText("Show/Hide Pointer");
        helpButton.addActionListener(action -> log.info("Clicked Show/Hide Pointer Button"));
        return helpButton;
    }

    private static JButton createHistoryButton() {
        JButton helpButton = new JButton("Changes History");
        helpButton.setToolTipText("Changes History");
        helpButton.addActionListener(action -> log.info("Clicked Changes History Button"));
        return helpButton;
    }
}
