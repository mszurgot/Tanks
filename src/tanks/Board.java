package tanks;

import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Pojazd pojazd;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;

    public Board() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        ingame = true;
        setSize(Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
        pojazd = new Pojazd();
        timer = new Timer(5, this);
        timer.start();
        
    }
/*
    @Override
    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }
*/
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        if (ingame) {

            Graphics2D g2d = (Graphics2D) g;

            if (pojazd.isVisible()) {
                g2d.drawImage(pojazd.getImage(), pojazd.getX(), pojazd.getY(),
                        this);
            }

            ArrayList ms = pojazd.getMissiles();

            for (int i = 0; i < ms.size(); i++) {
                Pocisk m = (Pocisk) ms.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

        } else {
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
                    B_HEIGHT / 2);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ArrayList ms = pojazd.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Pocisk m = (Pocisk) ms.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }

        pojazd.move();
        checkCollisions();
        repaint();
    }

    public void checkCollisions() {

        Rectangle r3 = pojazd.getWymiary();

        ArrayList ms = pojazd.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Pocisk m = (Pocisk) ms.get(i);

            Rectangle r1 = m.getWymiary();
        }
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            pojazd.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            pojazd.keyPressed(e);
        }
    }
}
