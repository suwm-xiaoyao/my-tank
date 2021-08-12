package com.suwm.tank;

public class Main {
    public static void main(String[] args) {
//        Frame frame = new Frame();
//        frame.setSize(800,600);
//        frame.setResizable(false);
//        frame.setTitle("坦克大战");
//        frame.setVisible(true);
//
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });

        TankFrame tank = new TankFrame();
        while (true){
            try {
                Thread.sleep(50);
                tank.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
