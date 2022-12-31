package com.company;

import com.company.algorithms.*;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Settings settings;
    private BubbleSort bubbleSort;
    private QuickSort quickSort;
    private InsertionSort insertionSort;
    private MergeSort mergeSort;
    private Container container;
    public GUI(String title) {
        settings = new Settings();
        frame = new JFrame(title);
        container = frame.getContentPane();
        bubbleSort= new BubbleSort(settings);
      //

        quickSort = new QuickSort(settings);


        insertionSort = new InsertionSort(settings);


        mergeSort = new MergeSort(settings);
     //

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
        insertion.addActionListener(this);
        merge.addActionListener(this);
        frame.setPreferredSize(new Dimension(this.settings.WIDTH, this.settings.HEIGHT));

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
            container.removeAll();
            container.add(bubbleSort);
            frame.setVisible(true);
            bubbleSort.sort();
        }
        if(e.getSource() == quick){
            container.removeAll();
            container.add(quickSort);
            frame.setVisible(true);
            quickSort.sort();
        }
        else if(e.getSource() == merge){
            container.removeAll();
            container.add(mergeSort);
            frame.setVisible(true);
            mergeSort.sort();
        }
        else if(e.getSource() == insertion){
            container.removeAll();
            container.add(insertionSort);
            frame.setVisible(true);
            insertionSort.sort();

        }
    }


}
