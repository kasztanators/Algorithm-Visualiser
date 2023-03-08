package com.company.pathFinding;


import javax.swing.*;
import java.awt.*;

public class ButtonManager {
    private JButton[] buttons;
    public ButtonManager() {

        createButtons();
    }

    private void createButtons() {
        JButton button1 = new JButton("A Star");
        button1.setPreferredSize(new Dimension(120, 50));

        JButton button2 = new JButton("UCS");
        button2.setPreferredSize(new Dimension(120, 50));

        JButton button3 = new JButton("Reset");
        button3.addActionListener(e ->Board.getInstance().resetBoard());
        button3.setPreferredSize(new Dimension(120, 50));

        JButton button4 = new JButton("Generate");
        button4.setPreferredSize(new Dimension(120, 50));

        buttons = new JButton[] { button1, button2, button3, button4 };
    }

    public JButton[] getButtons() {
        return buttons;
    }
}