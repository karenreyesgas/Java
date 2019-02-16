/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi1;

import java.util.Scanner;

public class Bi1 {

    public static void main(String[] args) {
      int arreglo[][]=new int [5][5];
      int suma=0;
      Scanner boo=new Scanner(System.in);
      for (int i=0; i<5; i++){
          for (int j=0; j<5; j++){
              System.out.println("Ingrese el elemento en "+i+","+j);
              arreglo[i][j]=boo.nextInt();
              suma=suma+arreglo[i][j];
          }
      }
      for (int i=0; i<5; i++){
          for (int j=0; j<5; j++){
              System.out.print(arreglo[i][j]+ " ");
          }
          System.out.println(" ");
      }
      System.out.println("La suma de los elementos es: "+suma);
    }
    
}
