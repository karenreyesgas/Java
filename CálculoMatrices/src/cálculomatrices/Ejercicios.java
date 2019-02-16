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
public class Ejercicios {
    public static void main(String[] args) {
        Scanner m=new Scanner (System.in);
        int x, min, max;

        
        //System.out.println("Ingrese el tamaño del tableau");
        //System.out.println("Tamaño de array:");
        //x=m.nextInt();
        int[] tableau1= new int []{10,67,15,170,8};
         max=tableau1[0];
         min=tableau1[0];
        
        for(int i=0; i<5; i++){

                if (tableau1[i]< min){
                    min=i;
                    System.out.println("Min"+tableau1[i]+min);
                }else if (tableau1[i] > max ){
                    max=i;
                    System.out.println("May"+tableau1[i]+max);
                }
                 
               
                
        }
                
                System.out.println("El minimo esta en :"+min);
                System.out.println("El maximo esta en :"+max);
        
    
        
    }
}
