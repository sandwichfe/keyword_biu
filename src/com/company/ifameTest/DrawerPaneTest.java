package com.company.ifameTest;


import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.EventQueue;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.Timer;

import javax.swing.UIManager;

import javax.swing.UnsupportedLookAndFeelException;
/**
 * @description:
 * @author lww
 * @since 2022/7/16 15:00
 */
public class DrawerPaneTest {

    public static void main(String[] args) {

        new DrawerPaneTest();

    }

    public DrawerPaneTest() {

        EventQueue.invokeLater(new Runnable() {

            @Override

            public void run() {

                try {

                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }

                JFrame frame = new JFrame("Testing");

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new BorderLayout());

                frame.add(new DrawerPane());

                frame.pack();

                frame.setLocationRelativeTo(null);

                frame.setVisible(true);

            }

        });

    }

    public class DrawerPane extends JPanel {

        private boolean drawIn = false;

        private Timer timer;

        private int drawWidth = 0;

        private int drawDelta = 4;

        public DrawerPane() {

            addMouseListener(new MouseAdapter() {

                @Override

                public void mouseClicked(MouseEvent e) {

                    timer.stop();

                    drawIn = !drawIn;

                    drawDelta = drawIn ? 4 : -4;

                    timer.start();

                }

            });

            timer = new Timer(5, new ActionListener() {

                @Override

                public void actionPerformed(ActionEvent e) {

                    drawWidth += drawDelta;

                    if (drawWidth >= getWidth() / 2) {

                        drawWidth = getWidth() / 2;

                        timer.stop();

                    } else if (drawWidth < 0) {

                        drawWidth = 0;

                        timer.stop();

                    }

                    repaint();

                }

            });

        }

        @Override

        public Dimension getPreferredSize() {

            return new Dimension(200, 200);

        }

        @Override

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(Color.RED);

            g2d.drawRect(0, 0, drawWidth, getHeight());

            g2d.dispose();

        }

    }

}
