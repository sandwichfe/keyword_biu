package com.company;

/**
 * @description:
 * @author lww
 * @since 2022/7/11 10:21
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Demo1{
    public static void main (String []args) {
        new Login();
    }
}

class Login extends JFrame implements ActionListener{
    JPanel jp3;
    JButton login,exit;
    Color color = new Color(255,255,255);
    public Login() {
        this.setTitle("click go");

        login=new JButton("next");
        login.addActionListener(this);
        exit=new JButton("exit");
        exit.addActionListener(this);

        jp3=new JPanel();
        jp3.setBackground(color);
        //背景颜色  大小
        login.setBackground(color);
        login.setSize(50,60);
        exit.setBackground(color);
        exit.setSize(50,60);


        // 边框
        //login.setBorder(null);
        //exit.setBorder(null);

        jp3.add(login);
        jp3.add(exit);

        // content背景颜色
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());
        this.add(jp3);

        this.toFront();
        this.setResizable(false);
        this.setSize(400,80);
        this.setLocation(1450,103);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login) {
            //JOptionPane.showMessageDialog(null, "ok");
            System.out.println("ctrl 3  "+new Date().toLocaleString());
            Main.aut_mobs();
        }
        if(e.getSource()==exit) {
            System.exit(0);
        }
    }

}




