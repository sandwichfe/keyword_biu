package com.company.NetSetting;

/**
 * @description:
 * @author lww
 * @since 2024/3/22 10:24
 */

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WaitingDialogExample {

    static JDialog dialog;

    static URL resource;

    static {
        resource = WaitingDialogExample.class.getResource("/1.gif");
    }


    public static void showWaitingDialog(JFrame parent) {
        if (dialog == null) {
            dialog = new JDialog(parent, "请稍候", true);
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.WHITE);

            //JLabel label = new JLabel("任务执行中...", SwingConstants.CENTER);
            //label.setFont(new Font("Arial", Font.BOLD, 14));
            //label.setForeground(Color.BLUE);
            //
            //panel.add(label, BorderLayout.CENTER);

            JLabel iconLabel = new JLabel(new ImageIcon(resource));
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            iconLabel.setVerticalAlignment(SwingConstants.CENTER);
            panel.add(iconLabel, BorderLayout.CENTER);

            dialog.setContentPane(panel);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.pack();
            dialog.setSize(200, 150);
            dialog.setLocationRelativeTo(parent);
        }
        dialog.setVisible(true);
    }

    public static void hideWaitingDialog() {
        dialog.setVisible(false);
    }


    public static void main(String[] args) {
        JFrame parent = new JFrame();
        parent.setSize(300, 200);
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parent.setLocationRelativeTo(null);

        JDialog dialog = new JDialog(parent, "请稍候", true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);


        JLabel iconLabel = new JLabel(new ImageIcon(resource));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(iconLabel, BorderLayout.CENTER);

        dialog.setContentPane(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setSize(200, 150);
        dialog.setLocationRelativeTo(parent);

        parent.setVisible(true);
        dialog.setVisible(true);
    }


}


