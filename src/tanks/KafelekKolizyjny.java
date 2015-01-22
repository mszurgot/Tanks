package tanks;

import java.awt.Rectangle;

public abstract class KafelekKolizyjny extends Kafelek implements IKolizyjne{
    private boolean visible;

    public KafelekKolizyjny(int gridX, int gridY) {
        super(gridX, gridY);
            TabKolizjiSingleton.getInstance().setTabKolizji(gridX, gridY, true);
            visible =true;
    }
 
    @Override
    public Rectangle getWymiary(){
        return new Rectangle(Board.getGridValue(gridX), Board.getGridValue(gridY), 20, 20);
    }
    
    public boolean isVisible(){
        return visible;
    }

    void setVisible(boolean visible) {
        if(visible == false){
            TabKolizjiSingleton.getInstance().setTabKolizji(this.gridX, this.gridY, visible);
            Board.soutCollisionTable();
            visible=false;
        }
    }
    
}
