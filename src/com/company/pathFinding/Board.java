package com.company.pathFinding;


import com.company.pathFinding.Algorithms.Astar;
import com.company.pathFinding.Algorithms.UCS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
    private final int gridSize = 15;
    private static final int gridNum= 40;
    private Node[][] nodes = new Node[gridNum][gridNum];
    private static final int SCREEN_SIZE = 600;
    private boolean isSetStart;
    private boolean isSetFinish;
    private boolean gameStart= false;
    private Node start;
    private Node finish;
    private JButton [] buttons;
    static Board instance;

    public static Board getInstance() {
        if (instance == null)
            instance = new Board();

        return instance;
    }
    private void resetBoard(){}

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }
    public Board(){

        setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
        setBackground(Color.GRAY);
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        createButtons();
        createLayout();
        initializeNodes();
        initializeEdges();
        repaint();
    }
    private void initializeEdges(){
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                nodes[i][j].getEdges(nodes);
            }
        }
    }
    private void createLayout() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        final int gap = 20;
        Dimension buttonSize = new Dimension(80, 50);
        for (JButton button : buttons) {
            button.setMaximumSize(buttonSize);
            button.setAlignmentX(Component.RIGHT_ALIGNMENT);
            this.add(Box.createRigidArea(new Dimension(gap, gap)));
            this.add(button);

        }
    }
    private void createButtons() {
        JButton button1 = new JButton("A Star");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSetFinish){
                    Astar astar = new Astar();
                    astar.findPath(start, finish);
                }
            }
        });
        button1.setPreferredSize(new Dimension(100, 50));
        JButton button2 = new JButton("Greedy");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSetFinish){
                    UCS ucs = new UCS();
                    ucs.findPath(start, finish);
                }

            }
        });
        button2.setPreferredSize(new Dimension(100, 50));
        JButton button3 = new JButton("UCS");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSetFinish){

                }
            }
        });
        button3.setPreferredSize(new Dimension(100, 50));
        buttons = new JButton[] { button1, button2, button3 };
    }
    private void initializeNodes() {
        for (int i = 0; i < gridNum; i++) {
            for (int j = 0; j < gridNum; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.GRAY);
        int x, y;
        for(int i = 0 ; i<gridNum; i++){
            for (int j = 0; j <gridNum ; j++) {
                y = i *gridSize;
                x = j *gridSize;
                State state = nodes[i][j].getState();
                g.setColor(state.getColor());
                g.fillRect(x, y, gridSize-1, gridSize-1);
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        checkMouseInBoard(e);
        {
            int x = e.getX() / gridSize;
            int y = e.getY() / gridSize;
            if (!gameStart && e.getButton() == 1 && y >= 0 && y < nodes.length && x >= 0 && x < nodes[0].length) {
                if (!isSetStart) {
                    start = this.nodes[y][x];
                    start.setState(State.START);
                    isSetStart = true;
                } else if (!isSetFinish && this.nodes[y][x].getState() != State.START && !this.start.isNextTo(this.nodes[y][x])) {
                    finish = this.nodes[y][x];
                    this.finish.setState(State.END);
                    isSetFinish = true;
                }
            }
            repaint();
        }
    }
    private boolean checkMouseInBoard(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        if(x<0 || x>SCREEN_SIZE-5)return false;
        return y >= 0 && y <= SCREEN_SIZE - 5;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(checkMouseInBoard(e)) {
            int y = e.getY() / gridSize;
            int x = e.getX() / gridSize;
            if (!isSetStart) {
                start = nodes[y][x];
                start.setState(State.START);
                isSetStart = true;
            } else if (!isSetFinish && nodes[y][x].getState() != State.START && !start.isNextTo(this.nodes[y][x])) {
                finish = nodes[y][x];
                finish.setState(State.END);
                isSetFinish = true;
            }

            if (isSetStart && isSetFinish && this.nodes[y][x].getState() != State.START && this.nodes[y][x].getState() != State.END && this.nodes[y][x].getState() != State.OBSTACLE && !gameStart) {
                this.nodes[y][x].setState(State.OBSTACLE);
            }
            this.repaint();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
