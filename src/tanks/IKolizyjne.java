package tanks;

import java.awt.Rectangle;

interface IKolizyjne {
    public Rectangle getWymiary();
    public void kolizja(IKolizyjne k);
}
