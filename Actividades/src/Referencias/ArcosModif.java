package Referencias;
//import Figuras.Dibujoarcos; //se manda a llamar el programa dibujos arcos del package figuras
import java.awt.*;// De aquí provienen los graficos como Graphics, metodo Paint
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.geom.*;
import javax.swing.*;
/**
 *
 * @author Karenrgast
 */
//Modificar y adaptación de código 
 // Documentar al menos 10 líneas de código
//Creatividad e innovación
public class ArcosModif extends JFrame implements Runnable{
Thread delay;


   public ArcosModif(){
       super("Mickey Mouse");//Define el texto del Frame o ventana que se va abrir
       setSize(1000,820); // Define alto y ancho de la ventana que se va abrir
       show();//Es el que nos muestra la ventana.
       
   } 

   @Override
  public void paint(Graphics g){
      int i=1;
      
        g.setColor(Color.black);
        g.drawRect(15, 35, 940, 760);//Dibuja un rectangulo, empezando por los grados o el lugar donde se va a posicionar y después se define el largo y ancho
        
        g.setColor(Color.red);
        g.fillRect(15, 35, 940, 760);//Dibuja un rectangulo con relleno
        
        g.setColor(Color.BLACK);//oreja 1
        g.fillArc(100, 50, 300, 300, 30, 360);//Dibuja un arco relleno(int x, int y, int width, int height, int angulo en el que va empezar, int angulo del arco
        
        g.setColor(Color.BLACK);//oreja 2
        g.fillArc(525, 50, 300, 300, 30, 360);
        
        g.setColor(Color.BLACK);//Define el color de la figura
        g.fillArc(225, 260, 500, 500, 0, 360);//cara
        
        g.setColor(new Color(238,207,168));//Define el color de la figura, se estableció por medio de RGB code
        g.fillArc(280, 280, 250, 400, 0, 360);//rostro
        g.fillArc(425, 280, 250, 400, 0, 360);//rostro
        g.fillArc(250, 550, 450, 200, 0, 360);//rostro
        g.fillArc(370, 580, 210, 210, 0, 360);//rostro
        
        g.setColor(Color.black);//Define el color de la figura
        g.drawArc(420, 550, 125, 30, 0, 175);//dibuja un arco; ojos
        
        g.setColor(Color.black);//Define el color de la figura
        g.fillArc(430, 570, 105, 70, 0, 360);//dibuja un arco relleno; nariz
        
        g.setColor(Color.white);//Define el color de la figura
        g.fillArc(395, 375, 80, 180, 0, 360);//ojo izquierdo
        
        g.setColor(Color.black);//Define el color de la figura
        g.drawArc(395, 375, 80, 180, 0, 360);//contorno de ojo
        
        g.setColor(Color.white);//Define el color de la figura
        g.fillArc(485, 375, 80, 180, 0, 360);//ojo derecho
        
        g.setColor(Color.black);//Define el color de la figura
        g.drawArc(485, 375, 80, 180, 0, 360);//contorno de ojo
        
        g.setColor(Color.black);//Define el color de la figura
        g.fillArc(415, 460, 50, 90, 0, 360);//pupila ojo izquierdo (ancho,alto)
        
        g.setColor(Color.black);//Define el color de la figura
        g.fillArc(495, 460, 50, 90, 0, 360);//pupila ojo derecho (ancho,alto)
        
        g.setColor(Color.black);//Define el color de la figura
        g.drawArc(315, 495, 325, 200, 180, 180);//sonrisa
        
        g.setColor(new Color(238,207,168));
        g.fillArc(400, 645, 150, 50, 180, 180);//boca
        
        g.setColor(Color.black);//Define el color de la figura
        g.drawArc(411, 715, 70, 30, 0, 183);//arco lengua
        g.drawArc(480, 720, 60, 20, 0, 180);//arco lengua
        
        g.setColor(Color.darkGray);//Define el color de la figura
        g.drawArc(300, 595, 60, 20, 80, 90);//arco sonrisa
        g.drawArc(620, 595, 60, 20, 80, 90);//arco sonrisa
                
        
        for (int e=0; e<100; e++){
            for(int a=2; a<100; a++){
                try {

                                  Thread.sleep(500);
                           } catch (InterruptedException ex) { }
                if(a%2==0){
                    
        g.setColor(Color.black);//Define el color de la figura
        g.fillArc(400, 600, 150, 170, 180, 180);//boca
        g.setColor(Color.pink);//Define el color de la figura
        g.fillArc(410, 685, 130, 85, 180, 180);//lengua
        g.fillArc(411, 715, 70, 30, 0, 180);//lengua
        g.fillArc(480, 720, 60, 20, 0, 180);//lengua
        
            System.out.println(a);
        }
        
        
        else {
        
        g.setColor(new Color(238,207,168));
        g.fillArc(400, 600, 160, 160, 180, 180);//boca
        g.fillArc(400, 620, 160, 160, 180, 180);//boca
        g.setColor(Color.black);//Define el color de la figura
        g.drawArc(315, 495, 325, 200, 180, 180);//sonrisa
        
        e++;
            System.out.println("e "+e);
        }
        }
        }
        
    }
  
  
    public static void main(String[] args)  {
        ArcosModif app=new ArcosModif();//se crea el objeto
        
        app.addWindowListener(
          
            new WindowAdapter(){
                    
                public void windowsClosing (WindowEvent e)//se invoca cuando el usuario intenta cerrar la ventana desde un método de cierre propio de la ventana
                    {
                        System.exit(0);
                    }//fin del closing
            } //fin del WindowAdapter
       ); //fin del WindowsListener
        
    } //fin del main

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} //fin de la clase


