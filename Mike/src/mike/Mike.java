/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mike;

/**
 *
 * @author 08
 */
public class Mike {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      int boo[][]= new int [3][3];
      for(int i=0; i<=2; i++){
          for (int j =0; j<=2; j++){
              boo[i][j]=-1;
              System.out.print(boo[i][j]+" ");
                      
          }
          System.out.println("");
      }
    }
    
}
