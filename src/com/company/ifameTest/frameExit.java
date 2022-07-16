package com.company.ifameTest;


import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 演示Frame界面展开与关闭动画
 *
 * @author cyl
 *
 */
public class frameExit extends JFrame {
    JButton btn_cancel = null;
    frameExit frame;
    public frameExit() {
        frame = frameExit.this;
        frame.setSize(80, 60);
        btn_cancel = this.getJButton1();
        add(btn_cancel);
        actionCreate(frame);
    }
    // exit action
    private JButton getJButton1() {
        if (btn_cancel == null) {
            btn_cancel = new JButton();
            btn_cancel.setText("cancel");
            btn_cancel.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    actionExit(frame);
                }
            });
        }
        return btn_cancel;
    }
    // create motion
    private void actionCreate(Frame frame) {
        frame.setVisible(true);
        final int loop = 60;
        if (frame != null) {
            Point oldP = frame.getLocation();
            Dimension dim = frame.getSize();
            int maxwidth = 800;
            int maxheigth = 600;
            oldP.x += dim.width * 2;
            oldP.y += dim.height * 2;
            int dx = maxwidth / loop;
            int dy = maxheigth / loop;
            for (int ii = 0; ii < loop; ii++) {
                dim.width += dx;
                dim.height += dy;
                frame.setSize(dim);
                centerFrame(frame);
                frame.repaint();
                try {
                    Thread.sleep(600 / loop);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // Exit motion
    private void actionExit(Frame frame) {
        final int loop = 60;
        if (frame != null) {
            Point oldP = frame.getLocation();
            Dimension dim = frame.getSize();
            oldP.x += dim.width / 2;
            oldP.y += dim.height / 2;
            int dx = dim.width / loop;
            int dy = dim.height / loop;
            for (int ii = 0; ii < loop; ii++) {
                dim.width -= dx;
                dim.height -= dy;
                frame.setSize(dim);
                centerFrame(frame);
                try {
                    Thread.sleep(600 / loop);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        frame.setVisible(false);
        frame.dispose();
        System.exit(1);
    }
    // center frame in screen
    private void centerFrame(Frame frame) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int frameH = frame.getHeight();
        int frameW = frame.getWidth();
        frame.setLocation((screenWidth - frameW) / 2, (screenHeight - frameH) / 2);
    }
    public static void main(String[] args) {
        new frameExit();
    }
}