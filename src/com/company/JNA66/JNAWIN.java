package com.company.JNA66;

/**
 * @description:  获取QQ窗口，若其已隐藏，将其显示；否则输出相关提示信息。
 * @author lww
 * @since 2022/7/15 14:05
 */
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser;

public class JNAWIN {
    public static void main(String[] args){
        String windowName = "QQ";
        HWND hwnd = User32.INSTANCE.FindWindow(null,windowName);
        if (hwnd==null) {
            System.out.println("Miss!");
        } else {
            System.out.println("Hit!");
            boolean showed = User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE );
            System.out.println(windowName+(showed?"窗口之前可见.":"窗口之前不可见."));
        }
    }
}