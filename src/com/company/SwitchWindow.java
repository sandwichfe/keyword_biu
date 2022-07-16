package com.company;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import javax.swing.*;

/**
 * @description: 切换window
 * @author lww
 * @since 2022/7/15 14:32
 */
public class SwitchWindow {


    public static void switchWin(String windowName){
        //String windowName = "tword – Main.java Administrator";
        User32 user32 = User32.INSTANCE;
        WinDef.HWND hwnd = user32.FindWindow(null, windowName);
        if (hwnd == null) {
            String info = windowName + " is not running";
            System.out.println();
            JOptionPane.showMessageDialog(ClickGo.getClickGo(),info);
        } else {
            //将程序焦点切换到此程序上
            user32.SetForegroundWindow(hwnd);
        }
    }


}
