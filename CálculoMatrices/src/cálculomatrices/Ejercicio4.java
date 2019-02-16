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
public class Ejercicio4 {
    public static void main(String[] args) {
        int [][]t;

t = new int[10][];
int MAX=9;
for(int i = 0; i < MAX; i++){
   t[i]= new int[i+1];

     for(int j = 0; j < t[i].length; j++){
        if(j == 0 || i == j)
              t[i][j] = 1;
        else
              t[i][j] = t[i-1][j] + t[i-1][j-1];
     }
}

for(int i = 0; i < MAX; i++){
    for(int j = 0; j < t[i].length; j++){
             System.out.print(t[i][j] + " ");
    }
     System.out.println();

}

        
    }
}
