package com.company;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
/**
 * @description:
 * @author lww
 * @since 2022/7/16 14:23
 */
public class Example {

    public Example() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //<---- this will disable the frame decorations
        frame.setUndecorated(true);
        JPanel panel = new JPanel();
        panel.add(new JLabel("titleBar"));
        //Add button maximize
        JButton button_max=new JButton("max");
        button_max.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(frame.getExtendedState() == JFrame.NORMAL) {
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    button_max.setText("min");
                } else {
                    button_max.setText("max");
                    frame.setExtendedState(JFrame.NORMAL);
                }
            }
        });
        panel.add(button_max);
        //Add button close
        JButton button_close = new JButton(new AbstractAction("Close") {
            private static final long serialVersionUID = -4901571960357967734L;
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(button_close);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Example();
    }
}
