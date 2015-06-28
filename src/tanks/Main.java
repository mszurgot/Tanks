package tanks;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

/**
 *
 * @author Zet
 */
public class Main {
    private static WindowFrame instance;
    
    public Main() throws FileNotFoundException {}
    
    public static WindowFrame getInstance(){
        if(instance ==null) instance = new WindowFrame();
        return instance;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            getInstance().setSize(instance.getFrameWidth() + 5, getInstance().getFrameHeight() + 28);
        });
    }
}
