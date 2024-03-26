package edu.mroz.toolbar;

import edu.mroz.components.Canvas;
import lombok.Getter;

import javax.swing.*;

public class ToolbarAdapter {

    @Getter
    private final JToolBar toolBar;
    private final Canvas canvas;

    private final JButton saveButton;
    private final JButton clearButton;
    private final JButton showHidePointerButton;
    private final JButton historyButton;
    private final JButton helpButton;

    public ToolbarAdapter(Canvas canvas, JToolBar toolBar, JButton saveButton, JButton clearButton,
                          JButton showHidePointerButton, JButton historyButton, JButton helpButton) {
        this.toolBar = toolBar;
        this.canvas = canvas;
        this.saveButton = (JButton) toolBar.add(saveButton);
        this.clearButton = (JButton) toolBar.add(clearButton);
        this.showHidePointerButton = (JButton) toolBar.add(showHidePointerButton);
        this.historyButton = (JButton) toolBar.add(historyButton);
        this.helpButton = (JButton) toolBar.add(helpButton);
        initButtonsLogic();
    }

    private void initButtonsLogic() {
        new ShowHidePointerUserAction(showHidePointerButton, canvas);
        new ClearUserAction(clearButton, canvas);
    }
}
