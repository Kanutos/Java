
package Ejercicio03;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class prueba extends JPanel {//extendemos el lienzo en la calse
    //declaramos atributos en la clae
	int x = 0;
	int y = 0;
        Color color;

	
// PROGRAMA MAIN, ES EL QUE MANDA
	public static void main(String[] args) throws InterruptedException {
                //creacion de un frame con la clase constructor
		JFrame frame = new JFrame("Mini Tennis");
		prueba game = new prueba();
                //metodos de la clase frame para visualizar la pantalla
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//condicion de funcionamiento que llamara a los metodos
		while (true) {
			game.moveBall();// son los metodos de la clase prueba que hemos llamado game en el metodo constructor
			game.repaint();
			Thread.sleep(10);
		}
        }
        private void moveBall() {//metodo que llamaremos de la clase prueba
		x = x + 1;
		y = y + 1;
	}

	public void paint(Graphics g) {//elementos que pintamos en el Jpanel
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval(x, y, 30, 30);        
	}
	}

    

