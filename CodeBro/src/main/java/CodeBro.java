
import java.util.Scanner;

public class CodeBro {

    public static void main(String[] args) {
      
       Scanner scanner = new Scanner (System.in);
       
       System.out.println("Cual es tu nombre?");
       String nombre = scanner.nextLine();
       
       System.out.println(" Que edad tienes?");
       int edad =scanner.nextInt();
       scanner.nextLine();/*Para limpiar el input del cajetin, despues de darle un numero
       sino hacemos una limpieza, y le ponemos un salto de carro, lo mantendria al no leerlo por el tipo de la variable asignada*/
       System.out.println("Cual es tu comida favorita");
       String Comida= scanner.nextLine();
       
       System.out.println("Hola don" + nombre);
       System.out.println("Tienes" + edad + "añazos campeon");
       System.out.println("Te gusta mucho" + Comida);
       
       
   }  
}
