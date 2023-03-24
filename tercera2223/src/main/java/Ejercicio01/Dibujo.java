//mirar el tema del encapsulado y el private para el acceso a traves de metodos y no directamente dentro de la clase
package Ejercicio01;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;

/*Que el primer sprite se mueva, segundo encapsulamos las imagenes, cuando pulsemos la h que salga uno, la g otro y la v el vaquero
Habra que hacer un metodo update que nos juegue con la posicion del vector en el sprite*/

public class Dibujo {
   private Image imagenes[];//variable de tipo puntero, donde se almacenara la direccion de memoria

    public Image[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(Image[] imagenes) {
        this.imagenes = imagenes;
    }
    int actual=0;
    /*que espera recibir en cuanto a direccion de memoria, como se la metemos? 
    atraves de los valores iniciales en el metodo constructor*/
 
      public Dibujo(Image[] imagenes){
          this.imagenes = imagenes; /*colocamos el this para referirnos al atributo de la
                                     clase, en caso de que haya varias opciones, el this sera el que nos apunte a la direccion 
                                    que nos correspona, al vaquero, al hampon o al guerrillero*/
      }   
     public void paint(Graphics g, JFrame j){
         g.drawImage(imagenes[actual],90,90,200,300,j);
   }
     public void update(){
         actual =(actual +1) % imagenes.length;// la posicion del vector +1, dividido para 4 que es el numero de imagenes, siempre dara menos de 3,asi no crea excepcion
     
     }
}
