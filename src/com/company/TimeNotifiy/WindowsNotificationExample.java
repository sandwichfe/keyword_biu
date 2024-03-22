package com.company.TimeNotifiy;
/**
 * @description:
 * @author lww
 * @since 2024/1/15 13:23
 */

import com.sun.jna.Native;
import com.sun.jna.WString;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
public class WindowsNotificationExample  {
    public static void main(String[] args) {
        startMsg("10:45:30","12312321");
        //showNotification("grg");
    }

    public static void startMsg(String time,String text){
        // 设置执行时间，格式为 HH:mm:ss

        // 创建定时任务调度器
        Timer timer = new Timer();

        // 获取当前时间
        Calendar currentTime = Calendar.getInstance();
        // 设置今天的指定时间执行任务
        Calendar scheduledTime = Calendar.getInstance();
        String[] timeParts = time.split(":");
        scheduledTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));
        scheduledTime.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
        scheduledTime.set(Calendar.SECOND, Integer.parseInt(timeParts[2]));

        // 如果当前时间已经过了今天的执行时间，则将执行时间设置为明天
        if (currentTime.after(scheduledTime)) {
            scheduledTime.add(Calendar.DATE, 1);
        }

        // 计算距离下次执行时间的延迟
        long delay = scheduledTime.getTimeInMillis() - currentTime.getTimeInMillis();

        // 定义定时任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 在这里编写需要执行的代码
                showNotification(text);
            }
        };

        // 启动定时任务
        timer.schedule(task, delay);
    }

    public interface User32 extends com.sun.jna.platform.win32.User32 {
        User32 INSTANCE = Native.load("user32", User32.class);
        int MessageBoxW(int hWnd, WString text, WString caption, int type);
        boolean SetForegroundWindow(int hWnd);
    }

    private static void showNotification(String text) {
        User32 user32 = User32.INSTANCE;
        String caption = "通知";
        WString textW = new WString(text);
        WString captionW = new WString(caption);
        int result = user32.MessageBoxW(0, textW, captionW, 0);

        // 将MessageBoxW窗口置于最前面
        user32.SetForegroundWindow(result);
    }


}




