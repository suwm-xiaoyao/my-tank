package com.suwm.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 200;
    int y = 200;

    final int SPEED = 5;

    Direct direct = Direct.DOWN;

    Tank myTank = new Tank(x, y, direct);

    Bullet bullet = new Bullet(myTank.getX() / 2, myTank.getY() / 2, myTank.getDirect());

    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

    public TankFrame() {
        setTitle("坦克大战");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        bullet.paint(g);

//        x += 10;
//        y += 10;
    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;


        @Override
        public void keyPressed(KeyEvent event) {
            int key = event.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    setMainTankDir();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent event) {
            int key = event.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    setMainTankDir();
                    break;
                default:
                    break;
            }
        }


        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);

                if (bL) {
                    myTank.setDirect(Direct.LEFT);
                }
                if (bU) {
                    myTank.setDirect(Direct.UP);
                }
                if (bR) {
                    myTank.setDirect(Direct.RIGHT);
                }
                if (bD) {
                    myTank.setDirect(Direct.DOWN);
                }
            }
        }
    }
}
