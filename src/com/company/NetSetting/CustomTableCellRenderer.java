package com.company.NetSetting;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @description:
 * @author lww
 * @since 2024/3/22 15:10
 */
public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    public CustomTableCellRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER); // 设置文本居中对齐
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table.isCellSelected(row, column)) {
            //((JComponent) c).setBorder(SELECTED_BORDER);
            // 设置选中单元格的边框颜色和厚度
            c.setBackground(new Color(214, 238, 245));
            // 选中时的背景色
        } else if (table.getMousePosition() != null && row == table.getMousePosition().y / table.getRowHeight()) {
            c.setBackground(new Color(207, 223, 229));
            // 鼠标悬停时的背景色
        } else {
            c.setBackground(Color.WHITE);
        }

        return c;
    }


}
