package com.company.NetSetting;

/**
 * @description:
 * @author lww
 * @since 2024/3/21 23:37
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.EventObject;
import java.util.List;

public class NetSetting {

    static PrintStream printStream = null;

    static Object[][] data;

    static JFrame frame;

    static DefaultTableModel model;

    static JDialog loadingDialog;

    static int dataLine;

    static final int rowHeight = 32;

    static URL logo;

    /** 标识是否正在更新数据 */
    static boolean isUpdated = false;

    static {

        // 加载数据
        List<String> netAdapters = NetDataUtil.getNetAdapters();
        data = NetDataUtil.convertData(netAdapters);
        dataLine = netAdapters.size();

        //图片
        logo = NetSetting.class.getResource("/img/icon.png");
    }

    public static void main(String[] args) {
        redirectSystemOutToFile("netSetting.log");


        // 去除按钮获取焦点时的虚线框
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));


        // Create JFrame
        frame = new JFrame("NetSetting");

        // 设置窗体图标
        Image icon = Toolkit.getDefaultToolkit().getImage(logo);
        frame.setIconImage(icon);

        // Create column names
        Object[] columns = {"Name", "status", "enable", "disable"};

        // Create default table model
        // 创建默认表格模型
        model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // 设置 2 3列的格子能被编辑   其他的返回false不能被编辑
                if (column == 2 || column == 3) {
                    return true;
                }
                return false;
            }
        };

        // 创建 JTable
        JTable table = new JTable(model) {
            @Override
            // 这里可以设置按钮格子的样式 统一由自定义render处理  不然按钮格子样式不受cell控制
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new CustomTableCellRenderer();
            }
        };

        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());

        table.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                int col = table.columnAtPoint(point);
                table.repaint();
            }
        });

        // 创建自定义单元格编辑器
        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean shouldSelectCell(EventObject anEvent) {
                // 设计格子单击即可编辑  默认是双击才能编辑
                return true;
            }
        };
        // 为表格设置自定义编辑器
        table.setDefaultEditor(Object.class, editor);

        // Set table background and foreground color
        table.setBackground(new Color(240, 240, 240));
        // 字体颜色 或者格内颜色？
        table.setForeground(Color.DARK_GRAY);

        // Set table header background and foreground color
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(163, 213, 232));
        header.setForeground(Color.WHITE);
        // 去掉了表头
        table.setTableHeader(null);

        // Create button renderer for the "Edit" and "Delete" columns
        table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer("启用"));
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer("禁用"));

        // Create button editor for the "Edit" and "Delete" columns
        table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JTextField()));
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JTextField()));

        // Set preferred column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        // 去掉表格边框
        table.setBorder(BorderFactory.createEmptyBorder());


        table.setRowHeight(rowHeight);
        table.setBackground(new Color(255, 255, 255));
        // Add table 滚动条
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(214, 238, 245));
        // Add scroll pane to panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder());
        // center
        // 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 计算窗口在屏幕中的位置
        int x = (screenSize.width - frame.getWidth()) / 2 - 250;
        int y = (screenSize.height - frame.getHeight()) / 2 - 100;
        // 设置窗口位置
        frame.setLocation(x, y);

        loadingDialog = createLoadingDialog(frame);

        // Set panel size and add to JFrame
        frame.add(panel);
        // 总高度根据行数来决定  42是窗口标题的高度
        int totalHeight = rowHeight * (dataLine) + 42;
        frame.setSize(500, totalHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(new Color(248, 222, 222));
        frame.setVisible(true);
        //frame.setResizable(false);
    }

    // Custom cell renderer for buttons
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setText(text);
            setOpaque(true);
            setBackground(new Color(189, 217, 225, 255));
            setForeground(new Color(141, 152, 154, 255));
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Custom cell editor for buttons
    static class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;

        private boolean operator;

        private String operatorName;

        public ButtonEditor(JTextField textField) {
            super(textField);
            button = new JButton();
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setOpaque(true);

            button.addActionListener(e -> {
                // 在按钮被点击时执行的操作
                button.setBackground(new Color(0, 255, 255)); // 设置按钮点击后的背景色
            });


            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            operator = column == 2 ? true : false;
            operatorName = (String) data[row][0];

            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                // 这里修改格子按钮 点击后的背景颜色。。。。
                button.setBackground(new Color(199, 198, 198));
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            // 如果正在更新数据  则弹框提示
            if (isUpdated) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(frame, "正在更新数据，请稍等！", "提示", JOptionPane.INFORMATION_MESSAGE);
                });
                return label;
            }

            // 开启或者关闭适配器操作
            new Thread(() -> {
                boolean result = NetDataUtil.updateNetAdapterStatus(operator, operatorName);
                if (result) {
                    // 弹框成功
                    NetDataUtil.successMsg(frame);
                    //执行完 隐藏dialog框
                    WaitingDialogExample.hideWaitingDialog();
                    // 刷新数据
                    reloadData(model);
                } else {
                    // 弹框失败
                    NetDataUtil.errorMsg(frame);
                    //执行完 隐藏dialog框
                    WaitingDialogExample.hideWaitingDialog();
                }


            }).start();

            // 加载loading框  一定要上面的任务先开启 再启用loading框 不然 如果先执行loading框 上面的线程就被堵塞了。。。。。。
            WaitingDialogExample.showWaitingDialog(frame);

            return label;
        }

        @Override
        public boolean stopCellEditing() {
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    private static JDialog createLoadingDialog(Frame parent) {
        JDialog dialog = new JDialog(parent, "Loading", Dialog.ModalityType.APPLICATION_MODAL);
        JLabel label = new JLabel("关闭窗口开始切换!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.add(label);
        dialog.setSize(200, 200);
        dialog.setLocationRelativeTo(parent);
        return dialog;
    }


    private static void reloadData(DefaultTableModel model) {
        isUpdated = true;
        System.out.println("重载数据...");
        data = NetDataUtil.convertData(NetDataUtil.getNetAdapters());

        // 清空原有数据
        model.setRowCount(0);

        // 加载新数据
        for (Object[] rowData : data) {
            model.addRow(rowData);
        }
        isUpdated = false;
    }



    /**
     * 日志记录
     * @author lww
     * @since 2024/3/22 16:27
     * @param filePath
     * @return
     */
    private static void redirectSystemOutToFile(String filePath)  {
        //将System.out标准输出流的打印路径更改为"d:\\exercise\\f1.txt"，默认为显示器

        try {
            printStream = new PrintStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(printStream);
        System.setErr(printStream);
        // 添加钩子，在程序结束时关闭输出流
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (printStream != null) {
                printStream.close();
            }
        }));
    }
}


