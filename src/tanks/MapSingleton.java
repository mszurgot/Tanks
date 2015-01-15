package tanks;

import javax.swing.JPanel;

/**
 *
 * @author Zet
 */
public class MapSingleton extends JPanel{
    private static MapSingleton instance = null;
    private static int SPACES = 6; 
    private int gridSpaces[] = new int[this.getHeight()]; //nie jestem pewny czy to dobry pomysł
    protected MapSingleton(){
        for(int i =1; i < this.getHeight() ; i++){
            gridSpaces[i]= i*SPACES;
        }
    }
    
    public MapSingleton getInstance(){
        if (instance == null){
            instance = new MapSingleton();
        } 
        return instance;
    }  
}
