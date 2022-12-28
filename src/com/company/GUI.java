package com.company;

import com.company.algorithms.BubbleSortVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements  ActionListener{

    private Toolkit toolkit;
    private JFrame frame;
    private JPanel mainPanel;
    private JMenuItem sorting;
    private JMenu menu;
    private BubbleSortVisualizer.Settings settings;
    private BubbleSortVisualizer bubbleSortVisualizer;
    public GUI(String title) {
        toolkit = Toolkit.getDefaultToolkit();

        settings = new BubbleSortVisualizer.Settings();
        frame = new JFrame(title);




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bubbleSortVisualizer = new BubbleSortVisualizer(settings);
        frame.add(bubbleSortVisualizer);
        menu = new JMenu("Menu");
        sorting = new JMenuItem("Sorting Algorssithm");

        JMenuBar menuBar = new JMenuBar();
        menu.add(sorting);

        menuBar.add(menu);
        sorting.addActionListener(this);

        frame.setJMenuBar(menuBar);
        frame.setLayout(new CardLayout());
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.lightGray);
        mainPanel.setLayout(null);
        frame.add(mainPanel);
        JButton button1 = new JButton("Press");
        frame.getContentPane().add(button1);
        frame.setVisible(true);
       frame.pack();

        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == sorting){
            Sorting sort = new Sorting();

            bubbleSortVisualizer.sort();

        }

    }


}
