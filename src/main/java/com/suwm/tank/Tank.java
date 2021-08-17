package com.suwm.tank;

import java.awt.*;
import java.util.Random;

public class Tank {

    public static final int SPEED = 5;

    private int x, y;

    private Direct direct = Direct.DOWN;

    private boolean moving = true;

    private Group group;

    private boolean living = true;

    private TankFrame tankFrame;

    private Random random = new Random();

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();

    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    Rectangle rectangle = new Rectangle();

    public Tank(int x, int y, Direct direct, Group group, TankFrame tankFrame) {
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

    public void paint(Graphics g) {
        if (!living) {
            moving = false;
            tankFrame.tankList.remove(this);
        }
//        Color color = g.getColor();
//        g.setColor(Color.yellow);
//        g.fillRect(x, y, 50, 50);
//        g.setColor(color);

        switch (direct) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

        move();
    }

    private void move() {
        if (!moving) {
            return;
        }

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

        // 自动开火
        if (this.group==Group.BAD&& random.nextInt(100)>95){
            this.fire();
        }
        // 自动转向
        if (this.group==Group.BAD&& random.nextInt(100)>95){
            randomDirect();
        }

        boundsCheck();

        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void randomDirect() {
        this.direct = Direct.values()[random.nextInt(4)];
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        Bullet bullet = new Bullet(bX, bY, direct, this.group, tankFrame);
        tankFrame.bulletList.add(bullet);
    }

    public void die() {
        this.living = false;
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

    public Direct getDirect() {
        return direct;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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
