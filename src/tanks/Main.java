package tanks;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Zet
 */
public class Main extends JFrame {

    public Main() {
        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setTitle("Collision");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
              EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
               new Main();
            }
        });
        
    }
}