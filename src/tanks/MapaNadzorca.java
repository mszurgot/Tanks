/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

/**
 *
 * @author Zet
 */
public class MapaNadzorca {
    private char[][] tab;
    private IMapaBudowniczy budowniczy;
    
    public void buduj() throws FileNotFoundException{
        try{
        BufferedReader br = new BufferedReader(new FileReader("standard.txt"));
        }catch(Exception e){
            System.out.println(e.getStackTrace().toString());
        }
    }
}
