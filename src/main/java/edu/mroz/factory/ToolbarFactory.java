package edu.mroz.factory;

import com.jidesoft.swing.JideButton;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class ToolbarFactory {

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

    private static JideButton createSaveButton() {
        JideButton saveButton = new JideButton("Save");
        saveButton.setToolTipText("Save");
        saveButton.addActionListener(action -> {
            log.info("Clicked Save Button");
        });
        return saveButton;
    }

    private static JideButton createClearButton() {
        JideButton clearButton = new JideButton("Clear");
        clearButton.setToolTipText("Clear");
        clearButton.addActionListener(action -> {
            log.info("Clicked Clear Button");
        });
        return clearButton;
    }

    private static JideButton createHelpButton() {
        JideButton helpButton = new JideButton("Help");
        helpButton.setToolTipText("Help");
        helpButton.addActionListener(action -> {
            log.info("Clicked Help Button");
        });
        return helpButton;
    }

    private static JideButton createShowHidePointerButton() {
        JideButton helpButton = new JideButton("Show/Hide Pointer");
        helpButton.setToolTipText("Show/Hide Pointer");
        helpButton.addActionListener(action -> {
            log.info("Clicked Show/Hide Pointer Button");
        });
        return helpButton;
    }

    private static JideButton createHistoryButton() {
        JideButton helpButton = new JideButton("Changes History");
        helpButton.setToolTipText("Changes History");
        helpButton.addActionListener(action -> {
            log.info("Clicked Changes History Button");
        });
        return helpButton;
    }
}
