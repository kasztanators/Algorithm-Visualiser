package com.company;

import com.company.algorithms.BubbleSort;
import com.company.algorithms.QuickSort;
import com.company.algorithms.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements  ActionListener{

    private Toolkit toolkit;
    private JFrame frame;
    private JPanel mainPanel;
    private JMenuItem bubble;
    private JMenuItem quick;
    private JMenuItem merge;
    private JMenuItem insertion;
    private JMenuItem aStar;
    private JMenu sorting;
    private JMenu pathFinding;
    private Settings settings;
    private BubbleSort bubbleSort;
    private QuickSort quickSort;
    public GUI(String title) {
        toolkit = Toolkit.getDefaultToolkit();

        settings = new Settings();
        frame = new JFrame(title);

        bubbleSort= new BubbleSort(settings);
      //  frame.add(bubbleSort);

        quickSort = new QuickSort(settings);
        frame.add(quickSort);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        sorting = new JMenu("Sorting Algorithms");
        pathFinding = new JMenu("Path Finding");
        bubble = new JMenuItem("Bubble sort");
        quick = new JMenuItem("Quick Sort");
        merge = new JMenuItem("Merge Sort");
        insertion = new JMenuItem("Insertion Sort");
        aStar = new JMenuItem("A* algorithm");

        JMenuBar menuBar = new JMenuBar();
        sorting.add(bubble);
        sorting.add(quick);
        sorting.add(merge);
        sorting.add(insertion);

        pathFinding.add(aStar);

        menuBar.add(sorting);
        menuBar.add(pathFinding);
        bubble.addActionListener(this);
        quick.addActionListener(this);

        frame.setJMenuBar(menuBar);
        frame.setLayout(new CardLayout());
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.lightGray);
        mainPanel.setLayout(null);
        frame.add(mainPanel);
        frame.pack();

        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bubble){
            //quickSort.remove(frame);

            bubbleSort.sort();
        }
        if(e.getSource() == quick){
            quickSort.sort();

        }
        else if(e.getSource() == merge){

        }
        else if(e.getSource() == insertion){

        }
    }


}
