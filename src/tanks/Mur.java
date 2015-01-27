package tanks;

import javax.swing.ImageIcon;

public class Mur extends KafelekKolizyjny {

    public Mur(int gridX, int gridY) {
        super(gridX, gridY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/mur.png"));
        image = ii.getImage();
    }
}
