/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pumba;

import java.util.Scanner;

/**
 *
 * @author 08
 */
public class Pumba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      int mulan[]=new int [10];
      int nemo[]= new int[10];
      int dory[];
        dory = new int [20];
        int x=0;
      
      Scanner lilo =new Scanner (System.in);
      for(int i=0; i<=9; i++){
      System.out.println("Ingrese el elemento " + (i+1)+ ":");
      mulan[i]= lilo.nextInt();
      }
      for(int i=0; i<=9; i++){
          System.out.println("Ingrese el elemento "+ (i+1)+":");
          nemo[i]=lilo.nextInt();
      }
      System.out.println("ARREGLO MULAN" );
      for(int i=0; i<=9; i++){
          System.out. print(mulan[i]+ " ");
      }
      System.out.println(" ");
      System.out.println("ARREGLO NEMO");
      for(int i=0; i<=9; i++){
          System.out. print(nemo[i]+" ");
      }
      System.out.println(" ");
      for(int i=0; i<=9; i++){
          dory[i]=mulan[i];
      }
      for(int i=10; i<=19; i++){
          dory[i]=nemo[x];
          x++;
      }
      System.out.println("ARREGLO DORY");
      for(int i=0; i<=19; i++){
          System.out.print(dory[i]+" ");
      }
          }
    
}
