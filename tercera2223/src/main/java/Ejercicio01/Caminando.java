
package Ejercicio01;
/*Si la imagen no esta dentro de la RAM no la podremos manipular, con  el metodo getimagen podremos manipular, los metodos que empiezan con get devuelven algo
los que empiezan por set son para modificar
Desde el punto de vista orientado a objetos tenemos que crear una clase */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;

public class Caminando extends JFrame implements Runnable {
    
    public static final int FILAS =4;
    public static final int COLUMNAS =4;
    Thread animacion;
    Image img;
    Image imagen;
    Graphics noseve;
    Image fotogramas [][];
    String elementos[]={"Guerrillero/g","Hampon/h","Vaquero/v"};
    Dibujo dibujo;     
    
    public void main(String arg[]){
            Caminando App = new Caminando();
   
        this.pack();
        this.setVisible(true);
        this.setSize(400, 400);
        imagen = this.createImage(400, 400);
        noseve = imagen.getGraphics();
        fotogramas = new Image[FILAS][COLUMNAS];// creamos 12 huecos de memoria indexados, en las que meteremos la agupacion de imagenes
        for(int i=0; i<FILAS; i++)
            for(int j=0; j< COLUMNAS;j++)
             fotogramas [i][j]= getImage(getCodeBase(), "Ejercicio01/Sprites/" + elementos[i]+ (j+1) + ".gif");
                //cogemos la direccion de memoria, y le damos el path del archivo
        dibujo = new Dibujo (fotogramas[0]);
    
    //public void start(){
        animacion = new Thread (this);
        animacion.start ();
    }
        
    
    public void paint(Graphics g){
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0,0,400,400);
        //noseve.drawImage(fotogramas[1][2],0,0,100,150,this);// trabajaremos con dos parametros mas que haran 6 que seran altura y anchura(paso intermedio)   
        dibujo.paint(noseve, this);//el this se refiere al applet, frame,pannel que es donde pintass
        g.drawImage(imagen,0,0,this); 
    }
    public void update (Graphics g){
        paint(g);
    }

    public void run() {
        while (true) {
            dibujo.update();

            repaint();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
        }
    }
    public boolean KeyDown( Event ev, int tecla){
    switch (tecla) {
        case 103:
        case 71: 
            dibujo.setImagenes(fotogramas[0]);
            break;
        case 104:
        case 72: 
            dibujo.setImagenes(fotogramas[1]);
            break;
         case 118:
        case 86: 
            dibujo.setImagenes(fotogramas[2]);
            break;
    }   

    }   
            
