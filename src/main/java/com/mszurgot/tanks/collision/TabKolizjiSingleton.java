package com.mszurgot.tanks.collision;

public class TabKolizjiSingleton {

    private static boolean[][] boundsMatrix;

    static {
        boundsMatrix = new boolean[32][32];
        for (int i = 0; i <= 31; i++) {
            for (int j = 0; j <= 31; j++) {
                if (i==0 || j==0 || i==31 || j==31) boundsMatrix[j][i]=true;
            }
        }
    }

    public static boolean getTabKolizji(int i, int j) {
        return boundsMatrix[i][j];
    }

    public static void setTabKolizji(int i, int j, boolean b) {
        boundsMatrix[i][j] = b;
    }
}
