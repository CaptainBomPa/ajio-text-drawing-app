package edu.mroz.toolbar;

import edu.mroz.components.Canvas;
import edu.mroz.components.JMenuWrapper;
import edu.mroz.toolbar.action.*;
import lombok.Getter;

import javax.swing.*;

public class ToolbarAdapter {

    @Getter
    private final JToolBar toolBar;
    private final Canvas canvas;

    private final JButton menu;
    private final JMenuItem saveItem;
    private final JMenuItem exportItem;
    private final JMenuItem loadItem;
    private final JButton clearButton;
    private final JButton showHidePointerButton;
    private final JButton historyButton;
    private final JButton helpButton;

    public ToolbarAdapter(Canvas canvas, JToolBar toolBar, JMenuWrapper menuWrapper, JButton clearButton,
                          JButton showHidePointerButton, JButton historyButton, JButton helpButton) {
        this.toolBar = toolBar;
        this.canvas = canvas;
        this.menu = (JButton) toolBar.add(menuWrapper.getMenu());
        this.saveItem = menuWrapper.getSaveItem();
        this.exportItem = menuWrapper.getExportItem();
        this.loadItem = menuWrapper.getLoadItem();
        this.clearButton = (JButton) toolBar.add(clearButton);
        this.showHidePointerButton = (JButton) toolBar.add(showHidePointerButton);
        this.historyButton = (JButton) toolBar.add(historyButton);
        this.helpButton = (JButton) toolBar.add(helpButton);
        initButtonsLogic();
    }

    private void initButtonsLogic() {
        new ShowHidePointerUserAction(showHidePointerButton, canvas);
        new ClearUserAction(clearButton, canvas);
        new SaveUserAction(saveItem, canvas);
        new LoadUserAction(loadItem, canvas);
        new ExportUserAction(exportItem, canvas);
    }
}
