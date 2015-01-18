package tanks;

public class TabKolizjiSingleton {

    private static TabKolizjiSingleton instance;
    private boolean[][] tabKolizji;

    private TabKolizjiSingleton() {
        this.tabKolizji = new boolean[30][30];
    }

    public static TabKolizjiSingleton getInstance() {
        if (instance == null) {
            instance = new TabKolizjiSingleton();
        }
        return instance;
    }

    public boolean getTabKolizji(int i, int j) {
        return tabKolizji[i][j];
    }

    public void setTabKolizji(int i, int j, boolean b) {
        this.tabKolizji[i][j] = b;
    }
}
