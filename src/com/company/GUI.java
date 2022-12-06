package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements  ActionListener {

    private Toolkit toolkit;
    private Dimension dimension;
    private JFrame jFrame;
    private JPanel mainPanel;
    private JMenuItem newGame, load, save, exit, Hexagonal;
    private JMenu menu;
    public GUI(String title) {
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();

        jFrame = new JFrame(title);
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu = new JMenu("Menu");
        newGame = new JMenuItem("New game");
        Hexagonal = new JMenuItem("Hexagonal");
        load = new JMenuItem("Load game");
        save = new JMenuItem("Save game");
        exit = new JMenuItem("Exit");
        JMenuBar menuBar = new JMenuBar();
        menu.add(newGame);
        menu.add(Hexagonal);
        menu.add(load);
        menu.add(save);
        menu.add(exit);
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);
        jFrame.setJMenuBar(menuBar);
        jFrame.setLayout(new CardLayout());
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.lightGray);
        mainPanel.setLayout(null);
        jFrame.add(mainPanel);
        JButton button1 = new JButton("Press");
        jFrame.getContentPane().add(button1);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            jFrame.dispose();
        }
    }
}
