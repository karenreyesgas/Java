/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumno;
import java.util.Scanner;
public class Alumno {
    public static void main(String[] args) {
      String nom;
      float cal, suma=0, prom=0; 
      int i, x;
      Scanner sc=new Scanner (System.in);
      System.out.println("Nombre del alumno:");
      nom =sc.nextLine();
      System.out.println("Número de calificaciones a capturar:");
      x= sc.nextInt();
      for (i=1; i<=x; i++){
       System.out.println("Calificación " +i+ ":");
       cal=sc.nextFloat();
       suma= suma+cal;    
      }
      prom=suma/x;
      System.out.println(nom+ " tiene un promedio de: " +prom);
    }
    
}
