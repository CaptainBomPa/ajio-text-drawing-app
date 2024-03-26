package edu.mroz.factory;

import edu.mroz.components.Canvas;
import edu.mroz.toolbar.ToolbarAdapter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class ToolbarFactory {

    private static final String SAVE_BUTTON = "Save";
    private static final String CLEAR_BUTTON = "Clear";
    private static final String SHOW_HIDE_POINTER_BUTTON = "Show/Hide Pointer";
    private static final String HISTORY_BUTTON = "History";
    private static final String HELP_BUTTON = "Help";

    private ToolbarFactory() {
    }

    public static ToolbarAdapter createToolbar(Canvas canvas) {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        return new ToolbarAdapter(canvas, toolBar, createSaveButton(), createClearButton(),
                createShowHidePointerButton(), createHistoryButton(), createHelpButton());
    }

    private static JButton createSaveButton() {
        JButton saveButton = new JButton(SAVE_BUTTON);
        saveButton.setToolTipText(SAVE_BUTTON);
        return saveButton;
    }

    private static JButton createClearButton() {
        JButton clearButton = new JButton(CLEAR_BUTTON);
        clearButton.setToolTipText(CLEAR_BUTTON);
        return clearButton;
    }

    private static JButton createShowHidePointerButton() {
        JButton helpButton = new JButton(SHOW_HIDE_POINTER_BUTTON);
        helpButton.setToolTipText(SHOW_HIDE_POINTER_BUTTON);
        return helpButton;
    }

    private static JButton createHistoryButton() {
        JButton helpButton = new JButton(HISTORY_BUTTON);
        helpButton.setToolTipText(HISTORY_BUTTON);
        return helpButton;
    }

    private static JButton createHelpButton() {
        JButton helpButton = new JButton(HELP_BUTTON);
        helpButton.setToolTipText(HELP_BUTTON);
        return helpButton;
    }
}
