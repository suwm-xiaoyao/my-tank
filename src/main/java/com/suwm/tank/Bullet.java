package com.suwm.tank;

import java.awt.*;

public class Bullet {
    public static final int SPEED = 10;

    private int x, y;

    private Direct direct;

    public static final int WIDTH = ResourceMgr.bulletD.getWidth();;
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();;

    public Bullet(int x, int y, Direct direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void paint(Graphics g) {
//        Color color = g.getColor();
//        g.setColor(Color.red);
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(color);

        switch(direct) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

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
