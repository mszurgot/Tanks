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

public class Board extends JPanel implements ActionListener {

    private final Timer timer;
    private final Gracz gracz;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    private final static int SPACES = 20;
    private static int grid[] = new int[30];
    private ArrayList<Krzak> krzaki;
    private ArrayList<Wrog> wrogowie;
    private ArrayList<KafelekKolizyjny> kafelkiKolizyjne;
    private Iterator it;
    private final Image background;

    public Board() {

        for (int i = 0; i < Main.FRAME_WIDTH / SPACES; i++) {
            grid[i] = (i - 1) * SPACES;
        }
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/background.png"));
        background = ii.getImage();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(new Color(0, 70, 0));
        setDoubleBuffered(true);
        ingame = true;
        setSize(Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
        timer = new Timer(5, this);
        timer.start();
        krzaki = new ArrayList();
        gracz = new Gracz(6, 15, 20, 3);
        wrogowie = new ArrayList<>();
        wrogowie.add(new Wrog(8, 17, 22, 5));
        krzaki.add(new Krzak(grid[10 * 2], grid[17]));
        krzaki.add(new Krzak(grid[10 * 2], grid[9 * 2]));
        krzaki.add(new Krzak(grid[10], grid[6]));
        kafelkiKolizyjne = new ArrayList<>();
        kafelkiKolizyjne.add(new Zelazo(9, 10));
        kafelkiKolizyjne.add(new Zelazo(10, 9));
        kafelkiKolizyjne.add(new Zelazo(10, 7));
        kafelkiKolizyjne.add(new Zelazo(10, 10));
        kafelkiKolizyjne.add(new Mur(14, 14));
        kafelkiKolizyjne.add(new Mur(14, 15));
        kafelkiKolizyjne.add(new Mur(15, 14));
        kafelkiKolizyjne.add(new Mur(15, 17));
        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= 30; j++) {
                System.out.print((TabKolizjiSingleton.getInstance().getTabKolizji(i,j))?"T":"F");
            }
            System.out.println();
        }
        
        //pojazdy.add(gracz);

        /*  
         for(int i = 0 ; i < Main.FRAME_WIDTH/SPACES/2; i++){
         for(int j = 0 ; j < Main.FRAME_WIDTH/SPACES/2; j++){         
         kafelki[i][j] = new Ziemia(grid[i*2],grid[j*2]);
         }
         }
         */
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

            g2d.drawImage(background, 0, 0, this);
            
            it = kafelkiKolizyjne.iterator();
            while (it.hasNext()) {
                KafelekKolizyjny k = (KafelekKolizyjny) it.next();
                g2d.drawImage(k.getImage(), grid[k.getGridX()], grid[k.getGridY()], this);
            }

            if (gracz.isVisible()) {
                g2d.drawImage(gracz.getImage(), gracz.getX(), gracz.getY(), this);
                ArrayList<Pocisk> ms = gracz.getPociski();
                for (int i = 0; i < ms.size(); i++) {
                    Pocisk m = (Pocisk) ms.get(i);
                    g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
                }
            }
            it = wrogowie.iterator();
            while (it.hasNext()) {
                Wrog wr = (Wrog) it.next();
                if (wr.isVisible()) {
                    g2d.drawImage(wr.getImage(), wr.getX(), wr.getY(), this);
                    ArrayList<Pocisk> ms = wr.getPociski();
                    for (int i = 0; i < ms.size(); i++) {
                        Pocisk m = (Pocisk) ms.get(i);
                        g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
                    }
                }
            }

            // ten sposob wyswietlania trzeba poprawic na bardziej wydajny najlepiej wyswietlac wszystkie krzaki z Arraylisty
            it = krzaki.iterator();
            while (it.hasNext()) {
                Kafelek k = (Kafelek) it.next();
                if (k.getClass().getSimpleName().equals("Krzak")) {
                    g2d.drawImage(k.getImage(), k.getGridX(), k.getGridY(), this);
                }
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

        ArrayList<Pocisk> ms = gracz.getPociski();
        for (int i = 0; i < ms.size(); i++) {
            Pocisk m = (Pocisk) ms.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
        it = wrogowie.iterator();
        while (it.hasNext()) {
            Wrog wr = (Wrog) it.next();
            if (wr.isVisible()) {
                ArrayList<Pocisk> mss = wr.getPociski();
                for (int i = 0; i < mss.size(); i++) {
                    Pocisk m = (Pocisk) mss.get(i);
                    if (m.isVisible()) {
                        m.move();
                    } else {
                        mss.remove(i);
                    }
                }
            }
        }

        gracz.makeMove();
        gracz.decReloadTimer();
        it = wrogowie.iterator();
        while (it.hasNext()) {
            Pojazd tmp = (Pojazd) it.next();
            tmp.makeMove();
            tmp.decReloadTimer();
        }
        checkCollisions();
        repaint();
    }

    public void checkCollisions() {
     /*   
        Iterator itr;
        Rectangle r1;
        Rectangle r2;

        
        
        ArrayList ms = gracz.getMissiles();
        for (int i = 0; i < ms.size(); i++) {
            Pocisk m = (Pocisk) ms.get(i);
            r1 = m.getWymiary();
            it = 
            //tu skonczylem pisac bedzie wesolo bo chyba trzeba 2 iteratory na te sama liste
            //ponadto sprawdzanie dla kazdego gracza, kazdy jego pocisk czy koliduje z kazdym innym pociskiem/ kazdym wrogiem lub graczem(bo przeciez miedzy wrogami nie ma friendlyfire ;d) oraz kazdym kolizyjnym kafelkiem POZDRO 600
        }*/
    }

    public static int getGridValue(int i) {
        return grid[i];
    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            gracz.keyPressed(e);
            it = wrogowie.iterator();
            while (it.hasNext()) {
                Wrog wr = (Wrog) it.next();
                wr.keyPressed(e);
            }

        }

    }
}
