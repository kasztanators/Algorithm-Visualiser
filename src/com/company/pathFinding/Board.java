package com.company.pathFinding;


import com.company.pathFinding.Algorithms.Astar;
import com.company.pathFinding.Algorithms.UCS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
    private final int gridSize = 15;
    private static final int gridNum= 40;
    private final Node[][] nodes = new Node[gridNum][gridNum];
    private static final int SCREEN_SIZE = 600;
    private boolean isSetStart;
    private boolean isSetFinish;
    private boolean gameStart= false;
    private Node start;
    private Node finish;
    private final JButton [] buttons;
    static Board instance;

    public static Board getInstance() {
        if (instance == null)
            instance = new Board();
        return instance;
    }

    public void refresh() {
        repaint();
    }

    void resetBoard(){
        isSetFinish = false;
        isSetStart = false;
        gameStart = false;
        initializeNodes();
        initializeEdges();
        repaint();
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }
    public Board(){

        setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
        setBackground(Color.GRAY);
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        ButtonManager buttonManager = new ButtonManager();
        this.buttons = buttonManager.getButtons();
        createLayout();
        initializeNodes();
        initializeEdges();
        repaint();
    }
    private void initializeEdges(){
        for (Node[] node : nodes) {
            for (Node value : node) {
                value.getEdges(nodes);
            }
        }
    }
    private void createLayout() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        final int gap = 20;
        Dimension buttonSize = new Dimension(100, 50);
        setButtonsAction();
        for (JButton button : buttons) {
            button.setMaximumSize(buttonSize);
            button.setAlignmentX(Component.RIGHT_ALIGNMENT);
            this.add(Box.createRigidArea(new Dimension(gap, gap)));
            this.add(button);

        }
    }

    private void initializeNodes() {
        for (int i = 0; i < gridNum; i++) {
            for (int j = 0; j < gridNum; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }
    }
    private void setButtonsAction(){
        buttons[0].addActionListener(e -> {
            if(isSetFinish){
                Astar astar = new Astar();
                astar.findPath(start, finish);
            }
        });
        buttons[1].addActionListener(e -> {
            if(isSetFinish){
                UCS ucs = new UCS();
                ucs.findPath(start, finish);
            }
        });
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
        if(checkMouseInBoard(e))
            return;
        int x = e.getX() / gridSize;
        int y = e.getY() / gridSize;
        if (!gameStart && e.getButton() == 1) {
            if (!isSetStart) {
                setStartNode(x,y);
                }
            else if (!isSetFinish) {
                setFinishNode(x,y);
                }
            }
            repaint();

    }
    private boolean checkMouseInBoard(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        if(x<0 || x>SCREEN_SIZE-5)return true;
        return y < 0 || y > SCREEN_SIZE - 5;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (checkMouseInBoard(e)) {
            return;
        }
        int y = e.getY() / gridSize;
        int x = e.getX() / gridSize;
        if (!isSetStart) {
            setStartNode(x, y);
        } else if (!isSetFinish) {
            setFinishNode(x, y);
        } else {
            setObstacleNode(x, y);
        }
        repaint();
    }

    private void setStartNode(int x, int y) {
        start = nodes[y][x];
        start.setState(State.START);
        isSetStart = true;
    }

    private void setFinishNode(int x, int y) {
        if (!start.isNextTo(nodes[y][x]) || nodes[y][x].getState() == State.START ) {
            return;
        }
        finish = nodes[y][x];
        finish.setState(State.END);
        isSetFinish = true;
    }

    private void setObstacleNode(int x, int y) {
        if (nodes[y][x].getState() == State.START || nodes[y][x].getState() == State.END
                || nodes[y][x].getState() == State.OBSTACLE || gameStart) {
            return;
        }
        nodes[y][x].setState(State.OBSTACLE);
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
