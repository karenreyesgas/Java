
package figuras2;

/**
 *
 * @author Karenrgast
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Figuras2 extends JFrame {
    public Figuras2()
    {
        super ( "Dibujando figuras en 2D");
        
        setBackground(Color.black);
        setSize(400,400);
        show();
    }//fin del constructor Figuras2
    @Override
    public void paint(Graphics g)
    {
        int puntosX[]=
        {55,67,109,73,83,55,27,37,1,43};
        int puntosY[]=
        {0,36,36,54,96,72,96,54,36,36};
        Graphics2D g2d=(Graphics2D)g;
        
        //crea una estrella a partir de una serie de puntos
        GeneralPath estrella = new GeneralPath();
        //establece la coordenada inicial del patron general
        estrella.moveTo(puntosX[0], puntosY[0]);
        //crea una estrella--no dibuja la estrella
        for(int k=1; k < puntosX.length; k++){
            estrella.lineTo(puntosX[k],puntosY[k]);
        }
        //cierra la figura
        estrella.closePath();
        //traslada el origen hacia 200,200
        g2d.translate(200,200);
        //rota alrededor del origen y dibuja estrellas en colores aleatorios
        for(int j=1; j<=20; j++){
            g2d.setColor(
                    new Color((int) (Math.random()*256),
                    (int) (Math.random()*256),
                    (int) (Math.random()*256)));
            g2d.fill(estrella); //dibuja una estrella rellena                
        } //fin del for      
    } //fin del metodo paint
    public static void main(String[] args) { 
        Figuras2 app = new Figuras2();
        app.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }//fin del metodo windowClosing
                }//fin de la clase interna anonima
        );//fin de WindowListener
    }//fin del main
}//fin de la clase Figuras2
