package edu.mroz.toolbar.action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpUserAction {

    public HelpUserAction(JButton button) {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JTextPane textPane = new JTextPane();
        textPane.setPreferredSize(new Dimension(700, 230));
        popupMenu.setPreferredSize(new Dimension(700, 230));
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        textPane.setBackground(button.getBackground());
        String commandsListHtml = buildHelpHTML();
        textPane.setText(commandsListHtml);
        textPane.setSize(200, textPane.getPreferredSize().height);

        popupMenu.add(new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                popupMenu.show(button, 0, button.getHeight());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                popupMenu.setVisible(false);
            }
        });
    }

    private String buildHelpHTML() {
        return "<html><body>" +
                "<b>Commands:</b><br>" +
                createEntry("color", "RED | GREEN | #f0f0f0 and so on...",
                        "Change the color of the line.") +
                createEntry("go", "0 to unlimited Integer number",
                        "Draw line in pixels.") +
                createEntry("direction", "0-359 | +60 | -90 and so on...",
                        "Change the direction of the pointer. It can be static, decreased or increased.") +
                createEntry("up", "",
                        "Raising the brush. The pointer will only move from that point.") +
                createEntry("down", "",
                        "Lowering the brush. The pointer will draw.") +
                createEntry("repeat", "0 to unlimited Integer number",
                        "Repeat next commands X times.") +
                createEntry("circle", "[1st] - Radius of the circle, [2nd] - Percentage of circle creation, [3rd] Clockwise (true|false)",
                        "Creates a circle with the given radius. For example, if the second parameter is set to 50%, only half of the circle will be painted.") +
                createEntry("style", "line | dashed | dotted", "Style of drawing") +
                "</body></html>";
    }

    private String createEntry(String command, String arguments, String explanation) {
        return "<font color='red'>" + command + "</font> <font color='green'>" + arguments + "</font> <font color='black'> - " + explanation + "</font><br>";
    }
}
