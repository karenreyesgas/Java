
package Figuras;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Dibujoarcos extends JFrame {
    public Dibujoarcos(){
        super("Dibujando Arcos");
        setSize(300,170);
        show();
        
    }
    
    @Override
    public void paint (Graphics g){
        //uso de grados
        g.setColor(Color.red);
        g.drawRect(15, 35, 80, 80);
        g.setColor(Color.BLACK);
        g.drawArc(15, 35, 80, 80, 0, 360);
        
        g.setColor(Color.BLUE);
        g.fillArc(15,120,80,40,90,180);
    }
    
    public static void main(String[] args) {
        Dibujoarcos app=new Dibujoarcos();
        
        app.addWindowListener(
                new WindowAdapter(){
                    public void windowsClosing(WindowEvent e)
                    {
                        System.exit(0);
                    }//fin del closing
                }//Fin del WindowsAdapter
        );//fin del WindosListener
    }//fin del main
}//fin de la clase


