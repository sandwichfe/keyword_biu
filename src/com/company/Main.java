package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
	// write your code here
        aut_mobs();
    }

    private static void aut_mobs() {
        // 创建Robot对象
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        //判断是否nullaaaa
        if(robot == null) {
            return;//如果是空结束程序
        }else {
            //如果不是空开始跑程序

            //1、开始时为了安全等待5秒，这是为了程序运行起来之后给用户5秒时间将操作焦点准备好
            robot.delay(5000);

            //开始循环做左右移动操作
            int i = 0;//0左A，1右D
            int j = 0;//按键步长
            while (true) {
                if(i == 0) {
                    while(j < 50) {
                        robot.keyPress(KeyEvent.VK_D);//模拟按下D
                        j++;
                    }
                    robot.keyRelease(KeyEvent.VK_D);//释放D，普通字母键可以不经历这一步，但是其他的如Ctrl等其他键，如果不释放会造成冲突的bug，影响效果
                    i++;
                    j=0;
                }else {
                    while(j < 50) {
                        robot.keyPress(KeyEvent.VK_A);
                        j++;
                    }
                    robot.keyRelease(KeyEvent.VK_A);
                    i--;
                    j=0;
                }
                //下一次操作间隔为1分钟
                robot.delay(10000);
            }
        }
    }

}
