package com.company;

import com.company.algorithms.BubbleSort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

public class GUI implements  ActionListener{

    private Toolkit toolkit;
    private JFrame jFrame;
    private JPanel mainPanel;
    private JMenuItem sorting;
    private JMenu menu;
    public GUI(String title) {
        toolkit = Toolkit.getDefaultToolkit();


        jFrame = new JFrame(title);
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu = new JMenu("Menu");
        sorting = new JMenuItem("Sorting Algorithm");

        JMenuBar menuBar = new JMenuBar();
        menu.add(sorting);

        menuBar.add(menu);
        sorting.addActionListener(this);
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
    public void paint(Graphics g, int arr []) {
        this.jFrame.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        int xOffset=100 - arr.length* 1;
        for (int i =0 ; i < arr.length; i ++) {
            Line2D lin = new Line2D.Float(i + xOffset, 550, i+xOffset, 550 - arr[i]*4);
            g2.draw(lin);
            xOffset +=10;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == sorting){
            Sorting sort = new Sorting();

            paint(jFrame.getGraphics(), sort.getArr());
        }

    }


}
