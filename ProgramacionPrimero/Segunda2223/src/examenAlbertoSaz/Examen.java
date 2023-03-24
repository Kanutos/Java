package examenAlbertoSaz;


import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;


public class Examen extends JFrame implements Runnable {
    int delay = 20;
    Thread animacion;
    Image imagen; 
    Graphics noseve;
    Color [] colores = {Color.RED, Color.ORANGE, Color.GREEN, Color.YELLOW, Color.MAGENTA};
    List<CuadradoInferior> cuadradosAbajo = new ArrayList<>();
    List<CuadradoMovil> cuadradosArriba = new ArrayList<>();
    List<DosPuntos> lineas = new ArrayList<>();
    DosPuntos actual;
    
     public static void main(String arg[]){
       Examen juego = new Examen();
    }
    public Examen(){
        init();
        start();
    }
    final public void init(){
        pack();
        setSize(800, 600);
        setVisible(true);
        for(int i = 0; i < colores.length; i++)
            cuadradosAbajo.add(new CuadradoInferior(i, colores[i]));
        for(int i = 0; i < colores.length; i++)
            cuadradosArriba.add(new CuadradoMovil(i, colores[i]));
        this.setSize(600, 600);
       
        imagen = this.createImage(600, 600); 
        noseve = imagen.getGraphics();
        
    }

    final public void start(){
        animacion = new Thread(this);//lo instanciamos y le pasamos this (el frame)
        animacion.start();//es el que llama a ejecutar el método run      
    }
    public void paint(Graphics g){
       noseve.setColor(Color.BLACK);
       noseve.fillRect(0, 0, 600, 600);
       
       for(CuadradoInferior ci : cuadradosAbajo)
           ci.paint(noseve);
       
       for(CuadradoMovil cm : cuadradosArriba)
           cm.paint(noseve);
       
       if(actual != null)
           actual.paint(noseve);
       
       if(!lineas.isEmpty())
           for(DosPuntos li : lineas)
               li.paint(noseve);
       
       
       g.drawImage(imagen, 0, 0, this);
    }
    
    public void update(Graphics g){ //override, lo sobreescribimos eliminando la linea de borrar
        paint(g);
    }
    
    public void run(){
        while(true){
            for(CuadradoMovil cm : cuadradosArriba){
                cm.update();
            if(!lineas.isEmpty())    
                for(DosPuntos li : lineas){
                    li.update();
                }              
            }
            repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex){           
            }
        }
    }
    public boolean mouseDown(Event ev, int x, int y){
        for(CuadradoInferior ci : cuadradosAbajo){
           if(ci.contains(x, y)){
               actual = new DosPuntos(x, y, ci.color);
               repaint();
               return true;
           }
        }
  
        return false;
    }
    public boolean mouseDrag(Event ev, int x, int y){//cuando haces click y sin soltar, mueves
        if(actual != null){ 
            actual.setPosFinX(x);
            actual.setPosFinY(y);
            for(DosPuntos li: lineas)
                if(li.getColor()==actual.getColor()){
                    actual = null;
                    break;
                }
            
            repaint();
        }
       
        return true;
    }
    public boolean mouseUp(Event ev, int x, int y){
        for(CuadradoMovil cm : cuadradosArriba){
            if(actual != null && cm.contains(x, y) && cm.color == actual.getColor()){
               lineas.add(new DosPuntos(actual.getPosIniX(), actual.getPosIniY(), cm));
               actual = null;
               repaint();
               break;  
           } 
           
        }
        
        actual = null;
        return true;
    }
        
}