/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mszurgot.tanks;

import com.mszurgot.tanks.gamemap.DefaultMapBuilder;
import com.mszurgot.tanks.gamemap.MapBuilder;
import com.mszurgot.tanks.gamemap.MapProvider;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WindowFrame extends JFrame {
    public final static int DEFAULT_WINDOW_WIDTH = 600;
    public final static int DEFAULT_WINDOW_HEIGHT = 600;

    private int frameWidth;
    private int frameHeight;
    private Dimension windowDimensions;

    public WindowFrame() throws HeadlessException, IOException {
        this.frameHeight = DEFAULT_WINDOW_HEIGHT;
        this.frameWidth = DEFAULT_WINDOW_WIDTH;
        windowDimensions = new Dimension(this.frameWidth, this.frameHeight);
        this.init();
    }

    public WindowFrame(int frameHeight, int frameWidth) throws IOException {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.windowDimensions = new Dimension(this.frameWidth, this.frameHeight);
        this.init();
    }

    private void init() throws IOException {
        MapBuilder budowniczy = new DefaultMapBuilder();
        MapProvider nadzorca = new MapProvider(budowniczy);

        nadzorca.buduj();

        super.add(budowniczy.pobierzGotowyProdukt());
        super.add(new Menu());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(windowDimensions);
        super.setPreferredSize(windowDimensions);
        super.setLocationRelativeTo(null);
        super.setTitle("Tanks");
        super.setResizable(false);
        super.setVisible(true);
        super.pack();

    }

    public Dimension getWindowDimensions() {
        return windowDimensions;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            //</editor-fold>

        /* Create and display the form */
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    new WindowFrame(WindowFrame.DEFAULT_WINDOW_HEIGHT + 28, WindowFrame.DEFAULT_WINDOW_WIDTH + 10).setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
