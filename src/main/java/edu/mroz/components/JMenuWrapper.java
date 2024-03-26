package edu.mroz.components;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
public class JMenuWrapper {

    private final JButton menu;

    private final JMenuItem saveItem;
    private final JMenuItem exportItem;
    private final JMenuItem loadItem;

    public JMenuWrapper(JButton menu, JMenuItem saveItem, JMenuItem exportItem, JMenuItem loadItem) {
        this.menu = menu;
        this.saveItem = saveItem;
        this.exportItem = exportItem;
        this.loadItem = loadItem;
        addLogic();
    }

    private void addLogic() {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(saveItem);
        popupMenu.add(exportItem);
        popupMenu.add(loadItem);

        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                popupMenu.show(menu, 0, menu.getHeight());
            }
        });
    }
}
