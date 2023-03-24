
package Ejercicio03;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JFrame;

public class Flecha extends Rectangle{
    Image imagen;
    int velX = 4;
    
    public Flecha(int y, Image img){
        super(100, y-30, 50, 25);
        imagen = img;
    }
    public void paint(Graphics g, JFrame F){
        g.drawImage(imagen, x, y, width, height, F );
    }
    public void update(){
        x += velX;
        
    }
}
//version agus
/*
import java.awt.Image;
import java.awt.Point;

public class Flecha extends point{
    public static final int VELX=4;
    Image imagen;

    public Flecha(Image img, int posY){
        super(100, posY);
        imagen=img;
}
public void paint(Graphics gg, Applet a){
        gg.drawImage(imagen,x,y,50,8,a);
}
public void update(){
x += VELX;
*/


