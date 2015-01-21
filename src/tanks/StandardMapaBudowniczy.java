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
                        
                        break;
                    }
                    case 'G': {
                        
                        break;
                    }
                    case 'K': {
                        
                        break;
                    }
                    case 'M': {
                        
                        break;
                    }
                    case 'Z': {
                        break;
                    }
                    case '.': {
                        
                        break;
                    }
                    case 'O': {
                        
                        break;
                    }
                }

            }
        }
    }
    
    public Board pobierzGotowyProdukt(){
        if (budowanyBoard != null){
            return budowanyBoard;
        }
        return null;
    }

}
