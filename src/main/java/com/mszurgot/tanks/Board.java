package com.mszurgot.tanks;

import com.mszurgot.tanks.collision.CollidableTile;
import com.mszurgot.tanks.collision.Bullet;
import com.mszurgot.tanks.components.Tile;
import com.mszurgot.tanks.components.BushTile;
import com.mszurgot.tanks.vehicles.Player;
import com.mszurgot.tanks.vehicles.AIVehicle;

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

    public static boolean[][] BOUNDS_MATRIX;
    private final Timer timer;
    private Player player;
    public static boolean rozgrywkaTrwa;
    private static final int TEXT_WIDTH = 600;
    private static final int TEXT_HEIGHT = 600;
    private final static int SPACES = 20;
    private static int grid[] = new int[31];
    private ArrayList<BushTile> krzaki;
    private ArrayList<AIVehicle> wrogowie;
    private Collection<CollidableTile> kafelkiKolizyjne;
    private Iterator it;
    private final Image background;

    static {

    }

    static {
        for (int i = 0; i < WindowFrame.DEFAULT_WINDOW_WIDTH / SPACES; i++) {
            grid[i] = (i - 1) * SPACES;
        }

        BOUNDS_MATRIX = new boolean[32][32];
        for (int i = 0; i <= 31; i++) {
            for (int j = 0; j <= 31; j++) {
                if (i==0 || j==0 || i==31 || j==31) Board.BOUNDS_MATRIX[j][i]=true;
            }
        }
    }

    public Board() {
        background = new ImageIcon(this.getClass().getResource("/images/background.png")).getImage();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
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

    public static boolean getBoundsMatrixValue(int i, int j) {
        return BOUNDS_MATRIX[i][j];
    }

    public static void setBoundsMatrixValue(int i, int j, boolean b) {
        BOUNDS_MATRIX[i][j] = b;
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
                CollidableTile ka = (CollidableTile) it.next();
                if (ka.isVisible()) {
                    g2d.drawImage(ka.getImage(), grid[ka.getGridX()], grid[ka.getGridY()], this);
                } else it.remove();
            }

//            drawDrawableCollection(kafelkiKolizyjne, g2d);

            if (player.isVisible()) {
                g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
                ArrayList<Bullet> ms = player.getBullets();
                for (int i = 0; i < ms.size(); i++) {
                    Bullet m =  ms.get(i);
                    if (m.isVisible()) {
                        g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
                    }
                }
            }
            it = wrogowie.iterator();
            while (it.hasNext()) {
                AIVehicle wr = (AIVehicle) it.next();
                if (wr.isVisible()) {
                    g2d.drawImage(wr.getImage(), wr.getX(), wr.getY(), this);
                    ArrayList<Bullet> ms = wr.getBullets();
                    for (int i = 0; i < ms.size(); i++) {
                        Bullet m = ms.get(i);
                        if (m.isVisible()) {
                            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
                        }
                    }
                }
            }

            it = krzaki.iterator();
            while (it.hasNext()) {
                Tile k = (Tile) it.next();
                if (k.getClass().getSimpleName().equals("BushTile")) {
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
            player.moveBullets();

            it = wrogowie.iterator();
            while (it.hasNext()) {
                AIVehicle wr = (AIVehicle) it.next();
                if (wr.isVisible()) wr.moveBullets();

            }

            player.makeMove();
            player.decReloadTimer();
            it = wrogowie.iterator();
            while (it.hasNext()) {
                AIVehicle tmp = (AIVehicle) it.next();
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

        ArrayList<CollidableTile> kafelkiDoUsuniecia = new ArrayList();
        ArrayList<Bullet> pociskiDoUsuniecia = new ArrayList();
        ArrayList<Bullet> pociskiGracza = player.getBullets();
        Iterator<Bullet> itrGP = pociskiGracza.listIterator();
        while (itrGP.hasNext()) {
            Bullet pociskGracza = itrGP.next();
            Rectangle r1 = pociskGracza.getDimension();
            Iterator<AIVehicle> itrW = wrogowie.iterator();

            while (itrW.hasNext()) {
                AIVehicle pojazdWroga = itrW.next();
                if (pojazdWroga.isVisible()) {
                    ArrayList<Bullet> pociskiW = pojazdWroga.getBullets();
                    Iterator<Bullet> itrWP = pociskiW.iterator();
                    while (itrWP.hasNext()) {
                        Bullet pociskWroga = itrWP.next();
                        Rectangle r2 = pociskWroga.getDimension();
                        if (r1.intersects(r2)) {
                            pociskiDoUsuniecia.add(pociskGracza);
                            pociskiDoUsuniecia.add(pociskWroga);
                            //System.out.println("delete bullets");
                        }
                    }
                    pociskiW.removeAll(pociskiDoUsuniecia);
                    Rectangle r2 = pojazdWroga.getDimension();
                    if (r1.intersects(r2)) {
                        pociskiDoUsuniecia.add(pociskGracza);
                        pojazdWroga.decHP();
                    }
                }
            }

            Iterator<CollidableTile> itrKol = kafelkiKolizyjne.iterator();
            CollidableTile kaf;
            while (itrKol.hasNext()) {
                kaf = itrKol.next();
                Rectangle r2 = kaf.getDimension();
                if (r1.intersects(r2)) {
                    pociskGracza.setVisible(false);
                    if (!kaf.getClass().getSimpleName().equals("SteelTile")) {
                        kaf.setVisible(false);
                        kafelkiDoUsuniecia.add(kaf);
                    }
                }
            }
        }
        pociskiGracza.removeAll(pociskiDoUsuniecia);
        kafelkiKolizyjne.removeAll(kafelkiDoUsuniecia);

        //kolizje wrogów
        Iterator<AIVehicle> itrW = wrogowie.iterator();
        while (itrW.hasNext()) {
            AIVehicle pojazdWroga = itrW.next();
            if (pojazdWroga.isVisible()) {
                ArrayList<Bullet> pociskiW = pojazdWroga.getBullets();
                Iterator<Bullet> itrWP = pociskiW.iterator();
                while (itrWP.hasNext()) {
                    Bullet pociskWroga = itrWP.next();
                    Rectangle r2 = pociskWroga.getDimension();
                    Rectangle r4 = player.getDimension();
                    if (r2.intersects(r4)) {
                        pociskiDoUsuniecia.add(pociskWroga);
                        player.decHP();
                    }
                    Iterator<CollidableTile> itrKol = kafelkiKolizyjne.iterator();
                    CollidableTile kaf;
                    while (itrKol.hasNext()) {
                        kaf = itrKol.next();
                        Rectangle r3 = kaf.getDimension();
                        if (r2.intersects(r3)) {
                            pociskWroga.setVisible(false);
                            if (!kaf.getClass().getSimpleName().equals("SteelTile")) {
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

    public void addKafelekKolizyjny(CollidableTile kaf) {
        this.kafelkiKolizyjne.add(kaf);
    }

    public void addWrog(AIVehicle AIVehicle) {
        this.wrogowie.add(AIVehicle);
    }

    public void addKrzak(BushTile bushTile) {
        this.krzaki.add(bushTile);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static void soutCollisionTable() {
        for (int i = 0; i <= 31; i++) {
            for (int j = 0; j <= 31; j++) {
                System.out.print((getBoundsMatrixValue(j, i)) ? "T" : "");
            }
            System.out.println();
        }
    }

//    private class TAdapter extends KeyAdapter {
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            player.keyPressed(e);
////            it = wrogowie.iterator();
//        }
//
//    }
}
