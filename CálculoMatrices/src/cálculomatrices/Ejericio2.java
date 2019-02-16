/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cálculomatrices;

import java.util.Scanner;

/**
 *
 * @author Karenrgast
 */
public class Ejericio2 {
    public static void main(String[] args) {
        int x, o;

        Scanner m=new Scanner (System.in);
        System.out.println("Hauteur:");
        x=m.nextInt();
        System.out.println("Largeur:");
        o=m.nextInt();
        String matriz1[][]=new String [x][o];
        
        if(x!=o){
           for(int i=0; i<x; i++){
            for (int j=0; j<o; j++){
                if (j==0|| i==0 || i==(x-1)|| j==(o-1)){
                    matriz1[i][j]= "x";
                }else 
                    matriz1[i][j]= "o";
            }
        }
           for(int i = 0; i <x; i++){
                for(int j = 0; j < o; j++){
                    System.out.print(matriz1[i][j] + " ");
                }
                    System.out.println();

}
        }
  
    
        
    }
}
