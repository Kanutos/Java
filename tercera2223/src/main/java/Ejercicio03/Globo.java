
package Ejercicio03;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JFrame;


public class Globo extends Rectangle{
    private Image imagen;
    Image explosion; 
    private int velY;
    private boolean explotado = false;
    private static final int SIZE = 100;
    
    public Globo(Image img, Image explota){
        super(Robin.SIZEX - ((int)(Math.random()*300)+100), Robin.SIZEY, SIZE, SIZE);
        imagen = img;
        explosion = explota;
        velY = - ((int)(Math.random()*3) + 2);
    }
    public void paint(Graphics g, JFrame F){
        if(!explotado)
            g.drawImage(imagen, x, y, width, height, F);
        else
            g.drawImage(explosion, x, y, width, height, F);
    }
    public void update(){
        y += velY;
    }

    public void setExplotado() {
        explotado = true;
    }

    public boolean isExplotado() {
        return explotado;
    }
    
    
}
