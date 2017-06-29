package com.mszurgot.tanks;

import com.mszurgot.tanks.collision.KafelekKolizyjny;
import com.mszurgot.tanks.collision.Pocisk;
import com.mszurgot.tanks.collision.TabKolizjiSingleton;
import com.mszurgot.tanks.components.Kafelek;
import com.mszurgot.tanks.components.Krzak;
import com.mszurgot.tanks.vehicles.Gracz;
import com.mszurgot.tanks.vehicles.Wrog;

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
import java.util.Collection;
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
    private Collection<KafelekKolizyjny> kafelkiKolizyjne;
    private Iterator it;
    private final Image background;

    static {
        for (int i = 0; i < WindowFrame.DEFAULT_WINDOW_WIDTH / SPACES; i++) {
            grid[i] = (i - 1) * SPACES;
        }
    }

    public Board() {
        background = new ImageIcon(this.getClass().getResource("/images/background.png")).getImage();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gracz.keyPressed(e);
            }
        });
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

    private <T extends IDrawable> void drawDrawableCollection(Collection<T> collection, Graphics2D graphicsContext){
        Iterator<T> iterator = collection.iterator();
        while(iterator.hasNext()){
            iterator.next().draw(graphicsContext, iterator);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (rozgrywkaTrwa) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.drawImage(background, 0, 0, this);

            it = kafelkiKolizyjne.iterator();
            while (it.hasNext()) {
                KafelekKolizyjny ka = (KafelekKolizyjny) it.next();
                if (ka.isVisible()) {
                    g2d.drawImage(ka.getImage(), grid[ka.getGridX()], grid[ka.getGridY()], this);
                } else it.remove();
            }

//            drawDrawableCollection(kafelkiKolizyjne, g2d);

            if (gracz.isVisible()) {
                g2d.drawImage(gracz.getImage(), gracz.getX(), gracz.getY(), this);
                ArrayList<Pocisk> ms = gracz.getPociski();
                for (int i = 0; i < ms.size(); i++) {
                    Pocisk m =  ms.get(i);
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
                        Pocisk m = ms.get(i);
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

        ArrayList<KafelekKolizyjny> kafelkiDoUsuniecia = new ArrayList();
        ArrayList<Pocisk> pociskiDoUsuniecia = new ArrayList();
        ArrayList<Pocisk> pociskiGracza = gracz.getPociski();
        Iterator<Pocisk> itrGP = pociskiGracza.listIterator();
        while (itrGP.hasNext()) {
            Pocisk pociskGracza = itrGP.next();
            Rectangle r1 = pociskGracza.getWymiary();
            Iterator<Wrog> itrW = wrogowie.iterator();

            while (itrW.hasNext()) {
                Wrog pojazdWroga = itrW.next();
                if (pojazdWroga.isVisible()) {
                    ArrayList<Pocisk> pociskiW = pojazdWroga.getPociski();
                    Iterator<Pocisk> itrWP = pociskiW.iterator();
                    while (itrWP.hasNext()) {
                        Pocisk pociskWroga = itrWP.next();
                        Rectangle r2 = pociskWroga.getWymiary();
                        if (r1.intersects(r2)) {
                            pociskiDoUsuniecia.add(pociskGracza);
                            pociskiDoUsuniecia.add(pociskWroga);
                            //System.out.println("delete pociski");
                        }
                    }
                    pociskiW.removeAll(pociskiDoUsuniecia);
                    Rectangle r2 = pojazdWroga.getWymiary();
                    if (r1.intersects(r2)) {
                        pociskiDoUsuniecia.add(pociskGracza);
                        pojazdWroga.decHP();
                    }
                }
            }

            Iterator<KafelekKolizyjny> itrKol = kafelkiKolizyjne.iterator();
            KafelekKolizyjny kaf;
            while (itrKol.hasNext()) {
                kaf = itrKol.next();
                Rectangle r2 = kaf.getWymiary();
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
        Iterator<Wrog> itrW = wrogowie.iterator();
        while (itrW.hasNext()) {
            Wrog pojazdWroga = itrW.next();
            if (pojazdWroga.isVisible()) {
                ArrayList<Pocisk> pociskiW = pojazdWroga.getPociski();
                Iterator<Pocisk> itrWP = pociskiW.iterator();
                while (itrWP.hasNext()) {
                    Pocisk pociskWroga = itrWP.next();
                    Rectangle r2 = pociskWroga.getWymiary();
                    Rectangle r4 = gracz.getWymiary();
                    if (r2.intersects(r4)) {
                        pociskiDoUsuniecia.add(pociskWroga);
                        gracz.decHP();
                    }
                    Iterator<KafelekKolizyjny> itrKol = kafelkiKolizyjne.iterator();
                    KafelekKolizyjny kaf;
                    while (itrKol.hasNext()) {
                        kaf = itrKol.next();
                        Rectangle r3 = kaf.getWymiary();
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
                System.out.print((TabKolizjiSingleton.getTabKolizji(j, i)) ? "T" : "");
            }
            System.out.println();
        }
    }

//    private class TAdapter extends KeyAdapter {
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            gracz.keyPressed(e);
////            it = wrogowie.iterator();
//        }
//
//    }
}
