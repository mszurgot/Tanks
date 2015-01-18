package tanks;

import java.awt.Rectangle;

public abstract class KafelekKolizyjny extends Kafelek implements IKolizyjne{

    public KafelekKolizyjny(int gridX, int gridY) {
        super(gridX, gridY);
    }
    
    @Override
    public Rectangle getWymiary(){
        return new Rectangle(Board.getGridValue(gridX), Board.getGridValue(gridY), 40, 40);
    }
    
    @Override
    public void kolizja(IKolizyjne k){
    ////////////////
    }
}
