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
public class Ejercicio3 {
    public static void main(String[] args) {
        int x,fac;
        fac=1;
        Scanner m=new Scanner (System.in);
        System.out.println("Numero:");
        x=m.nextInt();
       if(x >1) {
            for(int i=x; i>0; i--){
                fac=fac*i;
            }
        }else if (x==0 || x==1){
            fac=1;
        }
        System.out.println(fac);
         
        

        
    
        
    }
    
}
