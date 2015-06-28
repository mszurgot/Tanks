/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

    public class WindowFrame extends JFrame {

        private final int frameWidth = 600;
        private final int frameHeight = 600;
        private Dimension wymiaryOkna;

        public WindowFrame() {
            IMapaBudowniczy budowniczy = new StandardMapaBudowniczy();
            MapaNadzorca nadzorca = new MapaNadzorca(budowniczy);
            try {
                nadzorca.buduj();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WindowFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            add(budowniczy.pobierzGotowyProdukt());
            //add(new Menu());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            wymiaryOkna = new Dimension(this.frameWidth, this.frameHeight);
            setSize(wymiaryOkna);
            setPreferredSize(wymiaryOkna);
            setLocationRelativeTo(null);
            setTitle("Tanks");
            setResizable(false);
            setVisible(true);
            pack();
        }

        public int getFrameWidth() {
            return frameWidth;
        }

        public int getFrameHeight() {
            return frameHeight;
        }

        public Dimension getWymiaryOkna() {
            return wymiaryOkna;
        }
        
    }
