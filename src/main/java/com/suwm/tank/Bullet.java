package com.suwm.tank;

import java.awt.*;

public class Bullet {
    public static final int SPEED = 10;

    private int x, y;

    private Direct direct;

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    public Bullet(int x, int y, Direct direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);

        move();
    }

    private void move() {
        switch (direct) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
    }
}
