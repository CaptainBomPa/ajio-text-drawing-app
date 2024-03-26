package edu.mroz.factory;

import edu.mroz.components.Canvas;
import edu.mroz.components.JMenuWrapper;
import edu.mroz.toolbar.ToolbarAdapter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class ToolbarFactory {

    private static final String MENU = "Menu";
    private static final String SAVE_BUTTON = "Save project";
    private static final String EXPORT_BUTTON = "Export to .png";
    private static final String LOAD_BUTTON = "Load project";
    private static final String CLEAR_BUTTON = "Clear";
    private static final String SHOW_HIDE_POINTER_BUTTON = "Show/Hide Pointer";
    private static final String HISTORY_BUTTON = "History";
    private static final String HELP_BUTTON = "Help";

    private ToolbarFactory() {
    }

    public static ToolbarAdapter createToolbar(Canvas canvas) {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        return new ToolbarAdapter(canvas, toolBar, createMenu(), createClearButton(),
                createShowHidePointerButton(), createHistoryButton(), createHelpButton());
    }

    private static JMenuWrapper createMenu() {
        return new JMenuWrapper(new JButton(MENU), createSaveButton(), createExportButton(), createLoadButton());
    }

    private static JMenuItem createLoadButton() {
        JMenuItem saveButton = new JMenuItem(LOAD_BUTTON);
        saveButton.setToolTipText(LOAD_BUTTON);
        return saveButton;
    }

    private static JMenuItem createExportButton() {
        JMenuItem saveButton = new JMenuItem(EXPORT_BUTTON);
        saveButton.setToolTipText(EXPORT_BUTTON);
        return saveButton;
    }

    private static JMenuItem createSaveButton() {
        JMenuItem saveButton = new JMenuItem(SAVE_BUTTON);
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
