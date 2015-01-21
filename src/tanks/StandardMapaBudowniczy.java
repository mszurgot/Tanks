/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

/**
 *
 * @author Zet
 */
public class StandardMapaBudowniczy implements IMapaBudowniczy {
    
    Board budowanyBoard;

    @Override
    public void budujMape(char[][] tab) {
        char c;
        /*
        w konsoli na początku jest podgląd tego co odczytuje z pliku
        trzeba do każdej litery przypisać odpowiedni element do ustawienia w this.budowanyBoard
        wiec trzeb zrobic odpowiednie setery dla Board ktore dodają pojedyncze elementy do kolekcji..
        z resztą ogarnę później.
        */     
        budowanyBoard = new Board();
        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= 30; j++) {
                switch (tab[j][i]) {
                    case 'S': {
                       if((tab[j+1][i] == '.' || (tab[j+1][i] == 'K')) && (tab[j][i+1] == '.' || (tab[j][i+1] == 'K')) && (tab[j+1][i+1] == '.' || (tab[j+1][i+1] == 'K')))
                           budowanyBoard.addWrog(new Wrog(j+1, i+1, 10, 10));
                        break;
                    }
                    case 'G': {
                        if((tab[j+1][i] == '.' || (tab[j+1][i] == 'K')) && (tab[j][i+1] == '.' || (tab[j][i+1] == 'K')) && (tab[j+1][i+1] == '.' || (tab[j+1][i+1] == 'K')))
                        budowanyBoard.setGracz(new Gracz(j+1, i+1, 20, 3));
                        break;
                    }
                    case 'K': {
                        budowanyBoard.addKrzak(new Krzak(j+1,i+1));
                        break;
                    }
                    case 'M': {
                        budowanyBoard.addKafelekKolizyjny(new Mur(j+1,i+1));
                        break;
                    }
                    case 'Z': {
                        budowanyBoard.addKafelekKolizyjny(new Zelazo(j+1,i+1));
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
        budowanyBoard.soutCollisionTable();
    }
    
    @Override
    public Board pobierzGotowyProdukt(){
        if (budowanyBoard != null){
            return budowanyBoard;
        }
        return null;
    }

}
