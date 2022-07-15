package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        aut_mobs();

        ////String windowName = "ttt.txt - 记事本";
        //String windowName = "tword – Main.java Administrator";
        //User32 user32 = User32.INSTANCE;
        //WinDef.HWND hwnd = user32.FindWindow(null, windowName);
        //if (hwnd == null) {
        //    System.out.println(windowName + " is not running");
        //} else {
        //    //WinDef.RECT win_rect = new WinDef.RECT();
        //    //user32.GetWindowRect(hwnd, win_rect);
        //    //int win_width = win_rect.right - win_rect.left;
        //    //int win_height = win_rect.bottom - win_rect.top;
        //
        //    //显示
        //    //User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE);
        //    User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_MAXIMIZE);
        //    // 窗口最大化
        //    //user32.ShowWindow(hwnd,WinUser.SW_MAXIMIZE);
        //    //user32.SetWindowPos(hwnd,HWND ,20,20,win_width,win_height, WinUser.SWP_NOSIZE);
        //    //user32.MoveWindow(hwnd, 300, 100, win_width, win_height, true);
        //
        //}


    }


    public static void aut_mobs() {
        // qiehuan
        SwitchWindow.switchWin("tword – Demo1.java Administrator");


        // 创建Robot对象
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //判断是否nullaaaa
        if (robot == null) {
            return;//如果是空结束程序
        } else {
            //1、开始时为了安全等待5秒，这是为了程序运行起来之后给用户5秒时间将操作焦点准备好
            //robot.delay(2000);

            //模拟按下ctrl
            robot.keyPress(KeyEvent.VK_CONTROL);
            // 按下 33
            robot.keyPress(KeyEvent.VK_3);

            // 释放 3
            robot.keyRelease(KeyEvent.VK_3);

            //释放D，普通字母键可以不经历这一步，但是其他的如Ctrl等其他键，如果不释放会造成冲突的bug，影响效果
            robot.keyRelease(KeyEvent.VK_CONTROL);

            //下一次操作间隔为1分钟
            //robot.delay(10000);

            //将焦点切换回来
            SwitchWindow.switchWin("click go");
        }
    }
}
