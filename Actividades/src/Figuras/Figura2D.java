package Figuras;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*; //manejo de ventanas

public class Figura2D extends JFrame {
 public Figura2D(){
        super("Dibujando Figuras 2D");
        setBackground(Color.yellow);
        setSize(400,400);
        show();
       }
    @Override
    public void paint(Graphics g){
    int puntosX[]={55,67,109,73,83,55,27,37,1,43};
    int puntosY[]={0,36,36,54,96,72,96,54,36, 36};
    Graphics g2d=(Graphics2D) g;
    
    //crea estrella
    GeneralPath estrella=new GeneralPath();
    //estalece la coordenada inicial del patron general 
    estrella.moveTo(puntosX[0],puntosY[0]);
    //crea la estrella mas no la dibuja
            {
        for(int k=1;k<puntosX.length;k++)
    {
        estrella.lineTo(puntosX[k],puntosY[k]);
        
    }
    //cierra la figura
    estrella.closePath();
    //traslada el origen haci 200,200
    g2d.translate(200,200);
    //rota alrededor del origen y dibuja estrella en colores aleatorios 
            {
        
for(int j=1; j<=20; j++)
    {
        //g2d.rotate(Math.PI/10.0);
        g2d.setColor(
                new Color((int) (Math.random()*256),
                        (int) (Math.random()*256),
                        (int) (Math.random()*256)));
        g2d.create();
    }//fin del for
            }//fin del paint
    }}public static void main(String[] args) {
      Figura2D a=new Figura2D();
      a.addWindowListener(
              new WindowAdapter(){
                  //@override
                  @Override
                  public void windowClosing (WindowEvent e)
                  {
                      System.exit(0);
                      
                  }//fin del closing
              }//fin del windowadapter
      ); //fin del windowslitener
    }//fin del main
}
