/*
vamos a pintar un cuadro de Mondrian y luego le vamos a dar movimiento.
 */
package ejercicio013;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;

public class Mondrian2 extends JFrame implements Runnable{
    int velocidad = 10;
    Thread animacion;
    Image imagen; 
    Graphics noseve;
    Rectangulo[] rectangulo;
    int numeroRectangulos;
    int [] posX =       {0, 250, 80, 80, 100, 80, 200, 0, 200};
    int [] posY =       {0, 0, 160, 220, 10, 100, 0, 110, 55};
    int [] anchura =    {90, 40, 100, 220, 90, 110, 45, 70, 60};
    int [] altura =     {90, 190, 120, 90, 80, 90, 45, 200, 135};
    Color [] color =    {Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLUE, Color.BLUE, Color.LIGHT_GRAY, Color.RED, Color.RED, Color.MAGENTA};  

    public static void main( String arg[]){
     Mondrian2 App = new Mondrian2();
    }
    public Mondrian2(){
        pack();
        setSize(600, 600);
        setVisible(true);   
    //public void init(){
        this.setSize(320, 350);
        numeroRectangulos = 9;
        rectangulo = new Rectangulo[numeroRectangulos];
        imagen = this.createImage(320, 350); //no llamo al constructor, llamo a un método que llamará a un método constructor.
        noseve = imagen.getGraphics(); //obtenemos el objeto graphics del objeto imagen y lo guardamos en noseve
        for(int i = 0; i < numeroRectangulos; i++)
            rectangulo[i] = new Rectangulo(posX[i], posY[i], anchura[i], altura[i], color[i]);
 
        animacion = new Thread(this);//lo instanciamos y le pasamos this (el frame)
        animacion.start();//es el que llama a ejecutar el método run
        
    }
    public void paint(Graphics g){
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, 320, 350);
        for(int i = 0; i < numeroRectangulos; i++)
            rectangulo[i].dibujar(noseve);
        g.drawImage(imagen, 0, 0, this);
    }
    public void update(Graphics g){ //override, lo sobreescribimos eliminando la linea de borrar
        
        paint(g);
    
    }
    public void run(){
        while(true){
            for(int i = 0; i < numeroRectangulos; i++)
                rectangulo[i].actualizar();
            repaint();
           
            try {
                Thread.sleep(velocidad);
            } catch (InterruptedException ex){
            }
        }
    }
    
}