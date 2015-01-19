package tanks;

import java.awt.Rectangle;

public abstract class KafelekKolizyjny extends Kafelek implements IKolizyjne{

    public KafelekKolizyjny(int gridX, int gridY) {
        super(gridX, gridY);
            TabKolizjiSingleton.getInstance().setTabKolizji(gridX, gridY, true);
    }
    
    @Override
    public Rectangle getWymiary(){
        return new Rectangle(Board.getGridValue(gridX), Board.getGridValue(gridY), 20, 20);
    }
    
    @Override
    public abstract void kolizja(IKolizyjne ths, IKolizyjne that);
}
