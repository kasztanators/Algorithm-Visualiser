package com.company;

import com.company.algorithms.*;
import com.company.pathFinding.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.algorithms.Settings.HEIGHT;
import static com.company.algorithms.Settings.WIDTH;

public class GUI implements  ActionListener{
    private JFrame frame;
    private JPanel mainPanel;
    private JMenuItem bubble;
    private JMenuItem quick;
    private JMenuItem merge;
    private JMenuItem insertion;
    private JMenuItem aStar;
    private JMenu sorting;
    private JMenu pathFinding;
    private Container container;
    public GUI(String title) {
        frame = new JFrame(title);
        frame.setResizable(false);

        container = frame.getContentPane();



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
        menuBar.add(Box.createRigidArea(new Dimension(WIDTH,30)));
        bubble.addActionListener(this);
        quick.addActionListener(this);
        insertion.addActionListener(this);
        merge.addActionListener(this);
        aStar.addActionListener(this);
        frame.setPreferredSize(new Dimension(WIDTH+15, HEIGHT+70));
        frame.setJMenuBar(menuBar);
        frame.setLayout(new CardLayout());
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.lightGray);
        mainPanel.setLayout(null);
        container.add(mainPanel);
        frame.pack();

        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bubble){
            BubbleSort bubbleSort= new BubbleSort();
            container.removeAll();
            container.add(bubbleSort);
            frame.setVisible(true);
            bubbleSort.sort();
        }
        if(e.getSource() == quick){
            QuickSort quickSort = new QuickSort();
            container.removeAll();
            container.add(quickSort);
            frame.setVisible(true);
            quickSort.sort();
        }
        else if(e.getSource() == merge){
            MergeSort mergeSort = new MergeSort();
            container.removeAll();
            container.add(mergeSort);
            frame.setVisible(true);
            mergeSort.sort();
        }
        else if(e.getSource() == insertion){
            InsertionSort insertionSort = new InsertionSort();
            container.removeAll();
            container.add(insertionSort);
            frame.setVisible(true);
            insertionSort.sort();

        }
        else if(e.getSource()== aStar){
            container.removeAll();
            Board board = new Board();
            container.add(board);
            frame.setVisible(true);
        }
    }


}
