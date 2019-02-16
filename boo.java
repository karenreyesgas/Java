/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boo;
import java.util.Scanner;
public class boo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner mike=new Scanner(System.in);
      int boo[][]= new int [3][3];
      for(int i=0; i<=2; i++){
          for (int j =0; j<=2; j++){
              System.out.println("Dame el elemento "+i+" "+j);
              boo[i][j]= mike.nextInt();
              System.out.print(boo[i][j]+" ");
                      
          }
          System.out.println("");
      }
    }
    
}
