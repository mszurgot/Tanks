package tanks;

import java.awt.Rectangle;

public class BrzegiPlanszy implements IKolizyjne{
    
    private Rectangle wymiary;

    public BrzegiPlanszy(Rectangle x) {
        this.wymiary = x;
    }
    
    @Override
    public Rectangle getWymiary(){
        return wymiary;
    }

    @Override
    public void kolizja(IKolizyjne k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
