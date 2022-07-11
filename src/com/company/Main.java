package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        //aut_mobs();
    }


    public static void aut_mobs() {
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
            robot.delay(2000);

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
        }
    }
}
