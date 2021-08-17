package com.suwm.tank;

import java.awt.*;

public class Bullet {
    public static final int SPEED = 10;

    private int x, y;

    private Direct direct;

    private Group group;

    private boolean living = true;

    public static final int WIDTH = ResourceMgr.bulletD.getWidth();

    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    private TankFrame tankFrame;

    Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Direct direct, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.group = group;
        this.tankFrame = tankFrame;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }
//        Rectangle rectangle1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rectangle.intersects(tank.rectangle)) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tankFrame.explodeList.add(new Explode(eX, eY, tankFrame));
        }
    }

    private void die() {
        this.living = false;
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.bulletList.remove(this);
        }

//        Color color = g.getColor();
//        g.setColor(Color.red);
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(color);

        switch (direct) {
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

        rectangle.x = this.x;
        rectangle.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }
}
