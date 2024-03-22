package com.company.NetSetting;

/**
 * @description:
 * @author lww
 * @since 2024/3/22 14:59
 */
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TT {

    public static void main(String[] args) {
        // 设置全局按钮外观
        UIManager.put("Button.focus", new Color(0, 0, 0, 0)); // 去除按钮获取焦点时的虚线框

        try {
            // 设置外观为系统默认外观
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 创建 JFrame
        JFrame frame = new JFrame("Styled Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // 创建按钮
        JButton button = new JButton("Click Me");
        button.setPreferredSize(new Dimension(120, 40));

        // 设置按钮默认的背景色
        button.setBackground(Color.LIGHT_GRAY);

        // 添加按钮到 JFrame
        frame.add(button);

        // 设置 JFrame 大小并显示
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
