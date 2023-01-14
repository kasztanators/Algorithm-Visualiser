package com.company.pathFinding;

import java.awt.*;

public enum State{
    VISITED(Color.CYAN),
    START(Color.GREEN),
    END(Color.RED),
    OBSTACLE(Color.BLACK),
    PATH(Color.YELLOW),
    OPEN(Color.ORANGE),
    UNTOUCHED(Color.WHITE);
    private final Color color;

    State(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}