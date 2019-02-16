
package muestracolores;

/**
 *
 * @author Karenrgast
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Muestracolores extends JFrame {
    public Muestracolores(){
        super("Uso de colores");
        setSize(400,400);
        show();
    }//fin del constructor MuestraColores
    @Override
    public void paint (Graphics g){
        //establece un nuevo color de dibujo por medio de enteros
        g.setColor(new Color(255,0,0));
        g.fillRect(25,25,100,20);
        g.drawString("RVA actual: " + g.getColor(),130,40);
        //establece un nuevo color de dibujo por medio de números
        //de punto flotante
        g.setColor(new Color(0.0f,1.0f,0.0f));
        g.fillRect(25,50,100,20);
        g.drawString("RVA actual; " + g.getColor(),130,65);
        //establece un nuevo color de dibujo por medio de objetos
        //esáticos Color
        g.setColor(Color.blue);
        g.fillRect(25,75,100,20);
        g.drawString("RVA actual: " + g.getColor(),130,90);
        //despliega valores individuales RGB
        Color c = Color.magenta;
        g.setColor(c);
        g.fillRect(25,100,100,20);
        g.drawString("Valores RVA: " + c.getRed() + ", " + 
                c.getGreen() + ", " + c.getBlue(),130,115);
    }//fin del método paint
    public static void main(String[] args) {
        Muestracolores app = new Muestracolores();
        app.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }//fin del método windowClosing
                }//fin de la clase interna anónima
        );//fin de WindowListener
    }//Fin del método main
}//fin de la clase MuestraColores
