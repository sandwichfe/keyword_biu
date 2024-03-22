package com.company.NetSetting;

/**
 * @description:
 * @author lww
 * @since 2024/3/21 23:38
 */

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class NetDataUtil {


    public static List<String> getNetAdapters() {
        List<String> adapters = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("powershell.exe Get-NetAdapter");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("Name")) {
                    continue; // Skip the header line
                }
                if (line.trim().startsWith("----")) {
                    continue; // Skip the header line
                }
                // 超过两个空格的才开始切分
                String[] parts = line.split("(?<=\\S)\\s{2,}");
                if (parts.length >= 2) {
                    adapters.add(parts[0]);
                }
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return adapters;
    }


    public static Object[][] convertData(List<String> listData) {
        if (listData == null || listData.size() == 0) {
            return null;
        }
        // 线程等待执行工具
        CountDownLatch latch = new CountDownLatch(listData.size());
        Object[][] data = new Object[listData.size()][4];
        for (int i = 0; i < listData.size(); i++) {
            String name = listData.get(i);
            data[i][0] = name;
            //data[i][1] = getAdapterStatus(name);
            NetDataUtil.setDataValue(data, i, 1, name, latch);
            data[i][2] = "启用";
            data[i][3] = "禁用";
        }
        try {
            // 等待所有的线程执行完了 再执行这里的操作
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }


    private static void setDataValue(Object[][] data, int i, int j, String adapterName, CountDownLatch latch) {
        new Thread(() -> {
            String adapterStatus = getAdapterStatus(adapterName);
            data[i][j] = tranWord(adapterStatus);
            latch.countDown();
        }).start();
    }


    private static String tranWord(String name) {
        String ret = "";
        if ("Up".equals(name)) {
            ret = "已连接";
        }
        if ("Disconnected".equals(name)) {
            ret = "未连接";
        }
        if ("Disabled".equals(name)) {
            ret = "已禁用";
        }
        return ret;
    }


    private static String getAdapterStatus(String adapterName) {
        try {
            System.out.println("powershell.exe Get-NetAdapter -Name \'" + adapterName + "\'");
            Process process = Runtime.getRuntime().exec("powershell.exe Get-NetAdapter -Name \'" + adapterName + "\'");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith(adapterName)) {
                    String[] parts = line.split("\\s+");
                    if (parts.length > 1) {
                        return parts[parts.length - 4];
                    }
                }
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }


    /**
     * 修改适配器状态
     * @author lww
     * @since 2024/3/22 9:15
     * @param status true 启用    false 禁用
     * @param adapterName 适配器名称
     * @return 结果
     */
    public static boolean updateNetAdapterStatus(boolean status, String adapterName) {
        String cmd = (status ? "Enable-NetAdapter " : "Disable-NetAdapter ") + "-InterfaceAlias \'" + adapterName + "\' -Confirm:$false";
        return executePowerShellCommand(cmd);
    }


    public static boolean executePowerShellCommand(String command) {
        try {
            System.out.println("powershell.exe " + command);
            Process process = Runtime.getRuntime().exec("powershell.exe " + command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            // exitCode == 操作成功 否则失败
            return exitCode == 0;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void successMsg(Component component) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(component, "操作成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void errorMsg(Component component) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(component, "操作失败", "失败", JOptionPane.ERROR_MESSAGE);
        });
    }

}

