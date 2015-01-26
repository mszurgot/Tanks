package tanks;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Zet
 */
public class Main extends JFrame {

    public final static int FRAME_WIDTH = 600;
    public final static int FRAME_HEIGHT = 650;
    public static Dimension wymiaryOkna;

    public Main() throws FileNotFoundException {
        
        IMapaBudowniczy budowniczy = new StandardMapaBudowniczy();
        MapaNadzorca nadzorca = new MapaNadzorca(budowniczy);
        nadzorca.buduj();
        add(budowniczy.pobierzGotowyProdukt());
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
                Main main = null;
                try {
                    main = new Main();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                main.setSize(FRAME_WIDTH + 5, FRAME_HEIGHT + 28);
                
            }
        });
    }
}
