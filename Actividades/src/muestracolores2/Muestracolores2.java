
package muestracolores2;

/**
 *
 * @author Karenrgast
 */
//Demostracion de JColorChooser
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Muestracolores2 extends JFrame {
   private JButton cambiarColor;
   private Color color = Color.lightGray;
   private Container c;
   
   public Muestracolores2(){
       super ("Utilizando JColorChooser");
       c = getContentPane();
       c.setLayout(new FlowLayout());
       cambiarColor = new JButton("Cambia el color");
       cambiarColor.addActionListener(
               new ActionListener(){
                   @Override
                   public void actionPerformed(ActionEvent e){
                   color=
                           JColorChooser.showDialog(Muestracolores2.this,"Elija un color", color);
                   if(color==null){
                       color = Color.lightGray;
                   }
                   c.setBackground(color);
                   c.repaint();
               }//fin del método actionperformed
               }//fin de la case interna anónima
       );//fin de addActionListener
       c.add(cambiarColor);
       setSize(400,130);
       show();
   }//fin del constructor MuestraColores2
    public static void main(String[] args) {
        Muestracolores2 app = new Muestracolores2();
        app.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }//fin del método windowClosing
                }//fin de la clase interna anónima
        ); //fin de addWindowListener
    }//Fin del main   
}//fin de la clase MuestraColores2

