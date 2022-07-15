package com.company;

/**
 * @description:
 * @author lww
 * @since 2022/7/11 10:21
 */

import com.company.clickConstants.ClickConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


class ClickGo extends JFrame implements ActionListener {

    JPanel panel;
    JButton next, exit;
    Color color = new Color(255, 255, 255);

    public ClickGo() {
        this.setTitle(ClickConstants.appTitle);

        next = new JButton("next");
        next.addActionListener(this);
        exit = new JButton("exit");
        exit.addActionListener(this);

        panel = new JPanel();
        panel.setBackground(color);
        //背景颜色  大小
        next.setBackground(color);
        next.setSize(50, 60);
        exit.setBackground(color);
        exit.setSize(50, 60);

        panel.add(next);
        panel.add(exit);

        // content背景颜色
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());
        this.add(panel);

        this.toFront();
        this.setResizable(false);
        this.setSize(400, 80);
        this.setLocation(1450, 103);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            //JOptionPane.showMessageDialog(null, "ok");
            // 切换目标焦点
            SwitchWindow.switchWin("tword – ClickGo.java Administrator");
            KeyPressUtil.keyPressSome(KeyEvent.VK_CONTROL, KeyEvent.VK_3);
            //将焦点切换回来
            SwitchWindow.switchWin(ClickConstants.appTitle);
        }
        // 退出
        if (e.getSource() == exit) {
            SwitchWindow.switchWin("tword – ClickGo.java Administrator");
            KeyPressUtil.keyPressSome(KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT,KeyEvent.VK_M);
            System.exit(0);
        }
    }

}




