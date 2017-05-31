package com.mszurgot.tanks;

public class TabKolizjiSingleton {

    private static TabKolizjiSingleton instance;
    private boolean[][] tabKolizji;

    private TabKolizjiSingleton() {
        this.tabKolizji = new boolean[32][32];
        for (int i = 0; i <= 31; i++) {
            for (int j = 0; j <= 31; j++) {
                if (i==0 || j==0 || i==31 || j==31)tabKolizji[j][i]=true;
            }
        }
    }
    
/*    public void wyswietlTablice(){
    
    }*/

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
