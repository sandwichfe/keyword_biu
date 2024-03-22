package com.company.TimeNotifiy;

/**
 * @description:
 * @author lww
 * @since 2024/1/24 13:17
 */

import javax.swing.*;
import java.awt.*;

public class TimeInputDialog extends JFrame {
    private JTextField targetTime;
    private JTextField remindContent;
    private JTextArea notificationTextArea;
    private JTextArea statusArea;

    private String status = "";

    public TimeInputDialog() {
        setTitle("时间输入对话框");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 230);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        Insets insets = new Insets(5, 5, 5, 5);

        JLabel label1 = new JLabel("时间：");
        GridBagConstraints gbc1 = GridBagConstraintsUtils.createConstraints(0, 0, 1, GridBagConstraints.EAST, 0, GridBagConstraints.NONE, insets);
        inputPanel.add(label1, gbc1);

        targetTime = new JTextField();
        GridBagConstraints gbc2 = GridBagConstraintsUtils.createConstraints(1, 0, GridBagConstraints.REMAINDER, GridBagConstraints.WEST, 0.3, GridBagConstraints.HORIZONTAL, insets);
        inputPanel.add(targetTime, gbc2);

        JLabel label2 = new JLabel("内容：");
        GridBagConstraints gbc3 = GridBagConstraintsUtils.createConstraints(0, 1, 1, GridBagConstraints.EAST, 0, GridBagConstraints.NONE, insets);
        inputPanel.add(label2, gbc3);

        remindContent = new JTextField();
        GridBagConstraints gbc4 = GridBagConstraintsUtils.createConstraints(1, 1, GridBagConstraints.REMAINDER, GridBagConstraints.WEST, 0.7, GridBagConstraints.HORIZONTAL, insets);
        inputPanel.add(remindContent, gbc4);

        statusArea = new JTextArea(status);
        statusArea.setBackground(new Color(238,238,238));
        GridBagConstraints gbc99 = GridBagConstraintsUtils.createConstraints(3, 2, 1, GridBagConstraints.WEST, 0, GridBagConstraints.HORIZONTAL, insets);
        inputPanel.add(statusArea, gbc99);

        contentPane.add(inputPanel, BorderLayout.CENTER);

        notificationTextArea = new JTextArea();
        notificationTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(notificationTextArea);
        contentPane.add(scrollPane, BorderLayout.SOUTH);

        JButton confirmButton = new JButton("确定");
        confirmButton.addActionListener(e -> {
            String time = targetTime.getText();
            String content = remindContent.getText();

            WindowsNotificationExample.startMsg(time,content);
            showDialog("设置成功");
            statusArea.setText("执行中...");
        });
        contentPane.add(confirmButton, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    private void showDialog(String remindContent) {
        String caption = "通知";
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, remindContent, caption, JOptionPane.INFORMATION_MESSAGE);
            // 将窗口置于最前面
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JDialog) {
                    JDialog dialog = (JDialog) window;
                    if (dialog.getTitle().equals(caption)) {
                        dialog.toFront();
                        dialog.requestFocus();
                        break;
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimeInputDialog dialog = new TimeInputDialog();
            dialog.setVisible(true);
        });
    }
}
