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
    private static final String HELP_BUTTON = "Help";

    private ToolbarFactory() {
    }

    public static ToolbarAdapter createToolbar(Canvas canvas) {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        return new ToolbarAdapter(canvas, toolBar, createMenu(), createButton(CLEAR_BUTTON),
                createButton(SHOW_HIDE_POINTER_BUTTON), createButton(HELP_BUTTON));
    }

    private static JMenuWrapper createMenu() {
        return new JMenuWrapper(new JButton(MENU), createMenuItem(SAVE_BUTTON), createMenuItem(EXPORT_BUTTON), createMenuItem(LOAD_BUTTON));
    }

    private static JButton createButton(String name) {
        return new JButton(name);
    }

    private static JMenuItem createMenuItem(String name) {
        return new JMenuItem(name);
    }
}
