
package fuentes;

/**
 *
 * @author Karenrgast
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fuentes extends JFrame {
    public Fuentes()
    {
        
        super ("Utilizando Fuentes");
        setSize(420,125);
        show();
        
    }//fin del constructor fuente
    @Override
    public void paint(Graphics g )
    {
        //establece la fuente actual en Serif (times), negrita 12pt
        //y dibuja una cadena
        g.setFont(new Font("Serif", Font.BOLD, 12));
        g.drawString("Serif de 12 puntos en negritas.",20, 50);
        //establece una fuente actual Monospaced(courier),
        //cursiva, 24pt and draw a string
        g.setFont(new Font ("Monospaced",Font.ITALIC,24));
        g.drawString("Monospaced de 24 puntos en cursivas", 20, 70);
        //establece la fuente actual en sansserif (helvetica),
        //en texto comun, 14pt y dibuja una cadena
        g.setFont(new Font("SansSerif",Font.PLAIN,14));
        g.drawString("SansSerif de 14 puntos en texto comun.",20,90);
        //establece la fuente actual en serif(times), negritas/cursivas,
        //en texto comun, 18 pt y dibuja una cadena
        g.setColor(Color.red);
        g.setFont(
                new Font("Serif", Font.BOLD + Font.ITALIC,18));
        g.drawString(g.getFont().getName() + " "+
                     g.getFont().getSize() +
                     "Puntos en negritas y cursivas.", 20,110);
    }//fin del metodo paint
    public static void main(String[] args) {
        Fuentes app=new Fuentes();
        app.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing( WindowEvent e)
                    {
                        System.exit(0);
             }//fin del metodo windowClosing
         }//fin de la clase interna anonima
     ); //fin de addWindowListener
   }//fin de main
}//fin de la clase fuentes
