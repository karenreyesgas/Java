
package lineasrectangselips;

/**
 *
 * @author Karenrgast
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Lineasrectangselips extends JFrame {
    private String s = "Utilizando dranwString!";
    public Lineasrectangselips()
    {
        super("Dibujando Lineas, rectangulos y elipses");
        setSize(400,165);
        show();
    }//fin del constructor Lineasrectangselips
    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.drawLine(5,30,350,30);
        
        g.setColor(Color.blue);
        g.drawRect(5,40,90,55);
        g.fillRect(100,40,90,55);
        
        g.setColor(Color.cyan);
        g.fillRoundRect(195,40,90,55,50,50);
        g.drawRoundRect(290,40,90,55,20,20);
        
        g.setColor(Color.yellow);
        g.draw3DRect(5,100,90,55, true);
        g.fill3DRect(100, 100, 90, 55, false);
        
        g.setColor(Color.magenta);
        g.drawOval(195, 100, 90, 55);
        g.fillOval(290, 100, 90, 55);
    }//fin del metodo paint
    public static void main(String[] args) {
        Lineasrectangselips app = new Lineasrectangselips ();
        app.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }//fin del método windowClosing
                }//fin de la clase interna anónima
        ); //fin de addWindowsListener
    }//fin del main
}//fin de la clase LineasRectagsElips

