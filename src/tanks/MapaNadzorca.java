/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author Zet
 */
public class MapaNadzorca {

    private char[][] tab;
    private IMapaBudowniczy budowniczy;

    public MapaNadzorca(IMapaBudowniczy budowniczy) {
        this.tab = new char[31][31];
        this.budowniczy = budowniczy;
    }

    public void buduj() throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("standard.txt"));
            String tmp;
            int j = 0;
            while (br.ready()) {
                tmp = br.readLine();
                for (int i = 0; i < tmp.length(); i++) {
                    tab[i][j] = tmp.charAt(i);
                }
                j++;
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= 30; j++) {
                System.out.print(tab[j][i]);
            }
            System.out.println("");
        }
        budowniczy.budujMape(tab);
    }
}
