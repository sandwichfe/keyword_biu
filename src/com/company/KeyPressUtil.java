package com.company;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author lww
 * @since 2022/7/15 16:55
 */
public class KeyPressUtil
{
    public static void keyPressSome(Integer... keys) {
        // 创建Robot对象
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //判断是否null
        if (robot == null) {
            return;//如果是空结束程序
        } else {
            //1、开始时为了安全等待5秒，这是为了程序运行起来之后给用户5秒时间将操作焦点准备好
            //robot.delay(2000);
            if (keys == null || keys.length == 0) {
                return;
            }
            // 正序按下 反序释放
            for (int key : keys) {
                robot.keyPress(key);
            }
            Integer[] keyArrays = keys.clone();
            List<Integer> list = Arrays.asList(keyArrays);
            Collections.reverse(list);
            for (Integer key : list) {
                robot.keyRelease(key);
            }
        }
    }
}
