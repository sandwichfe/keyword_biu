package com.company.TimeNotifiy;

/**
 * @description:
 * @author lww
 * @since 2024/1/26 10:00
 */
import java.awt.*;

public class GridBagConstraintsUtils {
    public static GridBagConstraints createConstraints(int gridx, int gridy, int gridwidth, int anchor, double weightx, int fill, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.anchor = anchor;
        gbc.weightx = weightx;
        gbc.fill = fill;
        gbc.insets = insets;
        return gbc;
    }
}
