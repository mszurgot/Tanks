
package com.mszurgot.tanks.gamemap;

import com.mszurgot.tanks.Board;
import com.mszurgot.tanks.components.BushTile;
import com.mszurgot.tanks.components.SteelTile;
import com.mszurgot.tanks.components.Totem;
import com.mszurgot.tanks.components.WallTile;
import com.mszurgot.tanks.vehicles.AIVehicle;
import com.mszurgot.tanks.vehicles.Player;

import java.util.Random;

@Deprecated
public class DefaultMapBuilder implements MapBuilder {
    
    Board budowanyBoard;
    Random rand = new Random();
    private final static int ILE_HP_WROGOW = 3;

    @Override
    public void budujMape(char[][] tab) {
        char c;
        budowanyBoard = new Board();
        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= 30; j++) {
                switch (tab[j][i]) {
                    case 'S': {
                       if((tab[j+1][i] == '.' || (tab[j+1][i] == 'K')) && (tab[j][i+1] == '.' || (tab[j][i+1] == 'K')) && (tab[j+1][i+1] == '.' || (tab[j+1][i+1] == 'K')))
                           budowanyBoard.addWrog(new AIVehicle(j+1, i+1, rand.nextInt(40)+10, rand.nextInt(3)+3,ILE_HP_WROGOW));
                        break;
                    }
                    case 'G': {
                        if((tab[j+1][i] == '.' || (tab[j+1][i] == 'K')) && (tab[j][i+1] == '.' || (tab[j][i+1] == 'K')) && (tab[j+1][i+1] == '.' || (tab[j+1][i+1] == 'K')))
                        budowanyBoard.setPlayer(new Player(j+1, i+1, 20, 3, 1));
                        break;
                    }
                    case 'K': {
                        budowanyBoard.addKrzak(new BushTile(j+1,i+1));
                        break;
                    }
                    case 'M': {
                        budowanyBoard.addKafelekKolizyjny(new WallTile(j+1,i+1));
                        break;
                    }
                    case 'Z': {
                        budowanyBoard.addKafelekKolizyjny(new SteelTile(j+1,i+1));
                        break;
                    }
                    case 'T': {
                        if((tab[j+1][i] == '.' || (tab[j+1][i] == 'K')) && (tab[j][i+1] == '.' || (tab[j][i+1] == 'K')) && (tab[j+1][i+1] == '.' || (tab[j+1][i+1] == 'K')))
                        budowanyBoard.addKafelekKolizyjny(new Totem(j+1,i+1));
                        break;
                    }
                }
            }
        }
        //Board.soutCollisionTable();
    }
    
    @Override
    public Board pobierzGotowyProdukt(){
        if (budowanyBoard != null){
            return budowanyBoard;
        }
        return null;
    }

}
