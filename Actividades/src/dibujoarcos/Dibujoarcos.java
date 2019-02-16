
package dibujoarcos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Karenrgast
 */
public class Dibujoarcos extends JFrame  {
   public Dibujoarcos()
  {
    super("Dibujando arcos");
    
    setSize(300, 170);
    show();
  }//fin del constructor DibujoArcos
    
@Override
public void paint(Graphics g)
{
//comienza en 0 y barre 360 grados
g.setColor(Color.yellow);
g.drawRect(15, 35, 80, 80);
g.setColor(Color.black);
g.drawArc(15, 35, 80, 80, 0, 360);

//comienza en 0 y barre 110 grados
g.setColor(Color.yellow);
g.drawRect(100, 35, 80, 80);
g.setColor(Color.black);
g.drawArc(100, 35, 80, 80, 0, 110);

//comienza en 0 y barre -270 grados
g.setColor(Color.yellow);
g.drawRect(185, 35, 80, 80);
g.setColor(Color.black);
g.drawArc(185, 35, 80, 80, 0, -270);

//comienza en 0 y barre 360 grados
g.fillArc(15, 120, 80, 40, 0, 360);

//comienza en 270 y barre -90 grados
g.fillArc(100, 120, 80, 40, 270, -90);
 //comienza en 0 y barre -270 grados
g.fillArc(185, 120, 80, 40, 0, -270);
}//fin del metodo paint

public static void main(String[] args) {
        Dibujoarcos app=new Dibujoarcos();
        
        app.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing( WindowEvent e)
                    {
                     System.exit(0);   
                    }//fin del metodo windowClosing
                } //fin de la clase interna anonima
        );//fin de addwindowListener
}//fin de main
} //fin de la clase DibujoArcos

