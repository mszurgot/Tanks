/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mszurgot.tanks.gamemap;

import java.io.*;

/**
 * @author Zet
 */
public class MapaNadzorca {

    private char[][] tab;
    private IMapaBudowniczy budowniczy;

    public MapaNadzorca(IMapaBudowniczy budowniczy) {
        this.tab = new char[31][31];
        this.budowniczy = budowniczy;
    }

    public void buduj() throws IOException {
        fillTab("/standard.txt");
        printTab();
        budowniczy.budujMape(tab);
    }

    private void fillTab(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(MapaNadzorca.class.getResourceAsStream(filename)));
        String tmp;
        int j = 0;
        while (br.ready()) {
            tmp = br.readLine();
            for (int i = 0; i < tmp.length(); i++) {
                tab[i][j] = tmp.charAt(i);
            }
            j++;
        }
        br.close();
    }

    private void printTab() {
        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= 30; j++) {
                System.out.print(tab[j][i]);
            }
            System.out.println("");
        }
    }
}
