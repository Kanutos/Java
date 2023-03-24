
package Ejercicio03;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JFrame;

public class Arquero{
    Image imagen;
    int posY;
    
    public Arquero(Image img){
        posY = 250;
        imagen = img;
    }
    public void paint(Graphics g, JFrame F){
        g.drawImage(imagen, 0, posY, 100, 120, F);
    }
    public void update(int x, int y){
        posY = y - 75;
        if(y < 75)
            posY = 0;
        if(y > Robin.SIZEY - 75)
            posY = Robin.SIZEY - 150;
    }
}
