
package ejercicio015a;


import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Arkanoid extends Frame implements Runnable{
    public static final int IZDA= -1;
    public static final int DCHA = 1;
    public static final int NUM_FIL = 5;
    public static final int NUM_COL = 10;
    public static final Color colores[] = {Color.RED, Color.YELLOW, Color.MAGENTA, Color.GREEN, Color.BLUE};
    Thread animacion;
    Image imagen;
    Graphics noseve;
    Pelota pelota;
    List<Ladrillo> ladrillos;
    Raqueta raqueta;
    
    public static void main(String arg[]){
        Arkanoid app = new Arkanoid();
    }
    
    public Arkanoid(){
        super("Arkanoid");
        init();
        start();
    }

    public void init() {
        this.pack();
        this.setSize(300,300);
        this.setVisible(true);

        pelota = new Pelota();
        ladrillos = new ArrayList<Ladrillo>();
        for(int i=0; i < NUM_FIL; i++)
            for(int j=0; j < NUM_COL; j++)
                ladrillos.add(new Ladrillo(j*(Ladrillo.ANCHURA+2)+11,i*(Ladrillo.ALTURA+2)+34,colores[i]));
        raqueta = new Raqueta();
        
        imagen = this.createImage(300,300);
        noseve = imagen.getGraphics();
    }

    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }
    
    public void paint(Graphics g){
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, 300, 300);
        
        for(int i=0; i < ladrillos.size(); i++)
            ladrillos.get(i).paint(noseve);
        pelota.paint(noseve);
        raqueta.paint(noseve);
        
        g.drawImage(imagen,0,0,this);
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    public void run(){
        while(true){
            pelota.update(raqueta,ladrillos);
            repaint();
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){}
        }
    }
    
    public boolean keyDown(Event ev, int tecla){
        if(tecla == 1006)
            raqueta.update(IZDA);
        if(tecla == 1007)
            raqueta.update(DCHA);
        if(tecla == 27)
            System.exit(0);
        return true;
    }
 
            
}


    
}
