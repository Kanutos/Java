package Ejercicio01;

import java.awt.Frame;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.io.File;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;

public class Caminar extends JFrame implements Runnable {

    Thread animacion;
    Image imagen;
    Graphics noseve;
    Image img;
    Image[][] imagenes = new Image[3][4];
    String elementos[] = {"Guerrillero/g", "Hampon/h", "Vaquero/v"};
    Dibujo dib;

    public static void main(String[] args) {
        Caminar app = new Caminar();
    }

    public Caminar() {
        super("Ranas saltando :3");
        pack();
        setSize(600, 600);
        setVisible(true);
        // init
        imagen = this.createImage(600, 600);
        noseve = imagen.getGraphics();
        try {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    imagenes[i][j] = ImageIO.read(new File("C://Users/Usuario/Documents/NetBeansProjects/tercera2223/src/main/java/Ejercicio01/Sprites/" + elementos[i] + (j + 1) + ".gif"));
                }
            }
        } catch (IOException e) {
        }
        //img = imagenes[0][0];
        dib = new Dibujo(imagenes[0]);
        //Start
        animacion = new Thread(this);
        animacion.start();
    }

    public void paint(Graphics g) {
        //noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, 600, 600);

        if (dib != null) {
            dib.paint(noseve, this);//el this es JFrame del parametro del metodo en la clase
        }
        g.drawImage(imagen, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void run() {
        while (true) {
            if(imagenes[0][0] == null)
                System.out.print("a");
            if (dib != null) {
                dib.update();
            }
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
    }

    public boolean keyDown(Event ev, int tecla) {
        if (tecla == 1006) {
            dib = new Dibujo(imagenes[0]);
        }
        if (tecla == 1007) {
            dib = new Dibujo(imagenes[1]);
        }
        if (tecla == 1004) {
            dib = new Dibujo(imagenes[2]);
        }
        if (tecla == 1005) {
            dib = null;
        }
        if (tecla == 27) {
            System.exit(0);
        }
        return true;
    }
}
