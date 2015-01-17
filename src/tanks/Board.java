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
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tanks.kafelki.Kafelek;
import tanks.kafelki.Krzak;
import tanks.kafelki.Ziemia;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Pojazd pojazd;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    private final static int SPACES = 20;
    private static int grid[] = new int[30]; 
    private ArrayList<Kafelek> kafelki;
    private Image background;

    public Board() {
        
        for(int i = 0 ; i < Main.FRAME_WIDTH/SPACES; i++) grid[i] = i * SPACES;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/background.png"));
        background = ii.getImage();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(new Color(0,70,0));
        setDoubleBuffered(true);
        ingame = true;
        setSize(Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
        pojazd = new Pojazd(2,2,10,4);
        timer = new Timer(5, this);
        timer.start();
        kafelki = new ArrayList();   
        /*  
        for(int i = 0 ; i < Main.FRAME_WIDTH/SPACES/2; i++){
                for(int j = 0 ; j < Main.FRAME_WIDTH/SPACES/2; j++){         
                    kafelki[i][j] = new Ziemia(grid[i*2],grid[j*2]);
                }
            }
        */
        kafelki.add(new Krzak(grid[10*2],grid[10*2]));
        kafelki.add(new Krzak(grid[3*2],grid[4*2]));
        
    }

    @Override
    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        if (ingame) {        
            Graphics2D g2d = (Graphics2D) g;

            g2d.drawImage(background, 0, 0, this);
            
            if (pojazd.isVisible()) {
                g2d.drawImage(pojazd.getImage(), pojazd.getX(), pojazd.getY(),this);
            }

            ArrayList ms = pojazd.getMissiles();

            for (int i = 0; i < ms.size(); i++) {
                Pocisk m = (Pocisk) ms.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
            // ten sposob wyswietlania trzeba poprawic na bardziej wydajny najlepiej wyswietlac wszystkie krzaki z Arraylisty
            Iterator it = kafelki.iterator();
            while (it.hasNext()) {
                Kafelek k = (Kafelek) it.next();
                if(k.getClass().getSimpleName().equals("Krzak"))g2d.drawImage(k.getImage(),k.getGridX(),k.getGridY(),this);
                System.out.println(k.getClass().getSimpleName());
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
        pojazd.decReloadTimer();
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

    public static int getGridValue(int i) {
        return grid[i];
    }
    
    private class TAdapter extends KeyAdapter {
/*
        public void keyReleased(KeyEvent e) {
            pojazd.keyReleased(e);
        }
*/
        public void keyPressed(KeyEvent e) {
            pojazd.keyPressed(e);
        }
        
    }
}
