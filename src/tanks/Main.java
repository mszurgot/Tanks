package tanks;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Zet
 */
public class Main extends JFrame {

    public final static int FRAME_WIDTH = 600;
    public final static int FRAME_HEIGHT = 600;
    public static Dimension wymiaryOkna;

    public Main() {

        add(new Board());//tu można dać getInstance Singletonu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wymiaryOkna = new Dimension(Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
        setSize(wymiaryOkna);
        setPreferredSize(wymiaryOkna);
        setLocationRelativeTo(null);
        setTitle("Tanks");
        setResizable(false);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();
                main.setSize(FRAME_WIDTH + 5, FRAME_HEIGHT + 28);
            }
        });
    }
}
