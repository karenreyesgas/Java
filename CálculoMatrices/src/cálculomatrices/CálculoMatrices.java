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
public class CálculoMatrices {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner m=new Scanner (System.in);
        int x, y, deter;
        System.out.println("Ingrese el tamaño de la matriz");
        System.out.println("Tamaño de filas:");
        x=m.nextInt();
        System.out.println("Tamaño de columnas:");
        y=m.nextInt();
        int matriz1[][]=new int [x][y];
        
        for(int i=0; i<x; i++){
            for (int j=0; j<y; j++){
                System.out.println("Ingrese el elemento de la matriz en " +i+","+j );
                matriz1[i][j]= m.nextInt();
            }
        }
        
        for(int i=0; i<x; i++){
            for (int j=0; j<y; j++){
                System.out.print(matriz1[i][j] + " ");
            }
            System.out.println(" ");
        }
        if (x==2 & y==2){
         deter=(matriz1[0][0]*matriz1[1][1])-(matriz1[1][0]*matriz1[0][1]); 
         
        }
        
        
    }
    
}
