package com.mszurgot.tanks;

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
    private Gracz gracz;
    public static boolean rozgrywkaTrwa;
    private static final int TEXT_WIDTH = 600;
    private static final int TEXT_HEIGHT = 600;
    private final static int SPACES = 20;
    private static int grid[] = new int[31];
    private ArrayList<Krzak> krzaki;
    private ArrayList<Wrog> wrogowie;
    private ArrayList<KafelekKolizyjny> kafelkiKolizyjne;
    private Iterator it;
    private final Image background;

    public Board() {

        for (int i = 0; i < WindowFrame.DEFAULT_WINDOW_WIDTH / SPACES; i++) {
            grid[i] = (i - 1) * SPACES;
        }
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/background.png"));
        background = ii.getImage();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(new Color(0, 30, 0));
        setDoubleBuffered(true);
        rozgrywkaTrwa = true;
        setSize(WindowFrame.DEFAULT_WINDOW_WIDTH, WindowFrame.DEFAULT_WINDOW_HEIGHT);
        timer = new Timer(5, this);
        timer.start();
        krzaki = new ArrayList();
        wrogowie = new ArrayList<>();
        kafelkiKolizyjne = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (rozgrywkaTrwa) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.drawImage(background, 0, 0, this);

            it = kafelkiKolizyjne.listIterator();
            KafelekKolizyjny ka;
            while (it.hasNext()) {
                ka = (KafelekKolizyjny) it.next();
                if (ka.isVisible()) {
                    g2d.drawImage(ka.getImage(), grid[ka.getGridX()], grid[ka.getGridY()], this);
                }
            }

            if (gracz.isVisible()) {
                g2d.drawImage(gracz.getImage(), gracz.getX(), gracz.getY(), this);
                ArrayList<Pocisk> ms = gracz.getPociski();
                for (int i = 0; i < ms.size(); i++) {
                    Pocisk m = (Pocisk) ms.get(i);
                    if (m.isVisible()) {
                        g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
                    }
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
                        if (m.isVisible()) {
                            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
                        }
                    }
                }
            }

            it = krzaki.iterator();
            while (it.hasNext()) {
                Kafelek k = (Kafelek) it.next();
                if (k.getClass().getSimpleName().equals("Krzak")) {
                    g2d.drawImage(k.getImage(), grid[k.getGridX()], grid[k.getGridY()], this);
                }
            }
        } else {

            String msg = "Przegrana";
            Font small = new Font("Helvetica", Font.BOLD, 22);
            FontMetrics metr = this.getFontMetrics(small);
            g.setColor(Color.red);
            g.setFont(small);
            g.drawString(msg, (TEXT_WIDTH - metr.stringWidth(msg)) / 2, TEXT_HEIGHT / 2);

        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (rozgrywkaTrwa) {
            gracz.moveBullets();

            it = wrogowie.iterator();
            while (it.hasNext()) {
                Wrog wr = (Wrog) it.next();
                if (wr.isVisible()) wr.moveBullets();

            }

            gracz.makeMove();
            gracz.decReloadTimer();
            it = wrogowie.iterator();
            while (it.hasNext()) {
                Wrog tmp = (Wrog) it.next();
                if (tmp.isVisible()) {
                    tmp.makeMove();
                    tmp.decReloadTimer();
                } else {
                    tmp.decDoRespawnu();
                }
            }

            //respawn (nawet jeżeli ifVisible==false)
            checkCollisions();
            repaint();
        }
    }

    public void checkCollisions() {

        Iterator itrGP, itrW, itrWP, itrKol;
        ArrayList<KafelekKolizyjny> kafelkiDoUsuniecia = new ArrayList();
        ArrayList<Pocisk> pociskiDoUsuniecia = new ArrayList();
        Rectangle r1, r2, r3, r4;
        ArrayList<Pocisk> pociskiGracza = gracz.getPociski();
        itrGP = pociskiGracza.listIterator();
        while (itrGP.hasNext()) {
            Pocisk pociskGracza = (Pocisk) itrGP.next();
            r1 = pociskGracza.getWymiary();
            itrW = wrogowie.iterator();
            while (itrW.hasNext()) {
                Wrog pojazdWroga = (Wrog) itrW.next();
                if (pojazdWroga.isVisible()) {
                    ArrayList<Pocisk> pociskiW = pojazdWroga.getPociski();
                    itrWP = pociskiW.iterator();
                    while (itrWP.hasNext()) {
                        Pocisk pociskWroga = (Pocisk) itrWP.next();
                        r2 = pociskWroga.getWymiary();
                        if (r1.intersects(r2)) {
                            pociskiDoUsuniecia.add(pociskGracza);
                            pociskiDoUsuniecia.add(pociskWroga);
                            //System.out.println("delete pociski");
                        }
                    }
                    pociskiW.removeAll(pociskiDoUsuniecia);
                    r2 = pojazdWroga.getWymiary();
                    if (r1.intersects(r2)) {
                        pociskiDoUsuniecia.add(pociskGracza);
                        pojazdWroga.decHP();
                    }
                }
            }
            itrKol = kafelkiKolizyjne.iterator();
            KafelekKolizyjny kaf;
            while (itrKol.hasNext()) {
                kaf = (KafelekKolizyjny) itrKol.next();
                r2 = kaf.getWymiary();
                if (r1.intersects(r2)) {
                    pociskGracza.setVisible(false);
                    if (!kaf.getClass().getSimpleName().equals("Zelazo")) {
                        kaf.setVisible(false);
                        kafelkiDoUsuniecia.add(kaf);
                    }
                }
            }
        }
        pociskiGracza.removeAll(pociskiDoUsuniecia);
        kafelkiKolizyjne.removeAll(kafelkiDoUsuniecia);

        //kolizje wrogów
        itrW = wrogowie.iterator();
        while (itrW.hasNext()) {
            Wrog pojazdWroga = (Wrog) itrW.next();
            if (pojazdWroga.isVisible()) {
                ArrayList<Pocisk> pociskiW = pojazdWroga.getPociski();
                itrWP = pociskiW.iterator();
                while (itrWP.hasNext()) {
                    Pocisk pociskWroga = (Pocisk) itrWP.next();
                    r2 = pociskWroga.getWymiary();
                    r4 = gracz.getWymiary();
                    if (r2.intersects(r4)) {
                        pociskiDoUsuniecia.add(pociskWroga);
                        gracz.decHP();
                    }
                    itrKol = kafelkiKolizyjne.iterator();
                    KafelekKolizyjny kaf;
                    while (itrKol.hasNext()) {
                        kaf = (KafelekKolizyjny) itrKol.next();
                        r3 = kaf.getWymiary();
                        if (r2.intersects(r3)) {
                            pociskWroga.setVisible(false);
                            if (!kaf.getClass().getSimpleName().equals("Zelazo")) {
                                kaf.setVisible(false);
                                kafelkiDoUsuniecia.add(kaf);
                            }
                            if (kaf.getClass().getSimpleName().equals("Totem")) {
                                kaf.setVisible(false);
                                Board.rozgrywkaTrwa = false;
                            }
                        }
                    }
                }
                pociskiW.removeAll(pociskiDoUsuniecia);
            }
        }
        kafelkiKolizyjne.removeAll(kafelkiDoUsuniecia);
    }

    public static int getGridValue(int i) {
        return grid[i];
    }

    public void addKafelekKolizyjny(KafelekKolizyjny kaf) {
        this.kafelkiKolizyjne.add(kaf);
    }

    public void addWrog(Wrog wrog) {
        this.wrogowie.add(wrog);
    }

    public void addKrzak(Krzak krzak) {
        this.krzaki.add(krzak);
    }

    public void setGracz(Gracz gracz) {
        this.gracz = gracz;
    }

    public static void soutCollisionTable() {
        for (int i = 0; i <= 31; i++) {
            for (int j = 0; j <= 31; j++) {
                System.out.print((TabKolizjiSingleton.getInstance().getTabKolizji(j, i)) ? "T" : "");
            }
            System.out.println();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            gracz.keyPressed(e);
            it = wrogowie.iterator();
        }

    }
}
