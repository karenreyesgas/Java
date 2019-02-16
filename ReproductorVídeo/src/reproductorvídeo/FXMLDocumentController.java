/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductorvídeo;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javax.swing.JFileChooser;

/**
 *
 * @author LABPC15
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btnAbrir;
     
    @FXML
    private Button btnPlay;
    
     
    @FXML
    private Button btnPausar;
    
    
    @FXML
    private MediaView pantalla;
    
    private MediaPlayer Play;
    
    @FXML
    private Slider SliderVolumen;
    @FXML
    private Slider sliderTime;
    @FXML
    private Duration duracion;
    
    @FXML
    private void iniciarVideo(ActionEvent event) {
            Play.play();
       
    }
    @FXML
    private void pausarVideo(ActionEvent event) {
        
          Play.pause();
    }
    @FXML
    private void detenerVideo(ActionEvent event) {
          
          Play.stop();
          
    }
   @FXML
    private void abrirVideo(ActionEvent event) 
    {
        JFileChooser chooser = new JFileChooser();

        int retrival = chooser.showOpenDialog(null);
        
        if (retrival == JFileChooser.APPROVE_OPTION) {
        try {
           File f = new File(chooser.getSelectedFile().toString());
        
            if(f.exists())
            {
                Play = new MediaPlayer(new Media(f.toURI().toString())); 
       
                Play.setOnReady(new Runnable() {
                @Override
                public void run() {
                // TODO Auto-generated method stub
                
                duracion=Play.getMedia().getDuration();
                }
                });
                
                Play.setOnStopped(new Runnable(){
                @Override
                public void run() {
                duracion=Play.getMedia().getDuration();
                actualizarTimeSlider();
                }
                });
                
                Play.setOnPlaying(new Runnable(){
            
                @Override
                public void run()
                {
                    System.out.println("El video se está reproduciendo");
                }
                });
                Play.setOnEndOfMedia(new Runnable(){
            
                @Override
                public void run()
                {
                     System.out.println("El video ha terminado");
                     Play.stop();
                }
                });
                Play.currentTimeProperty().addListener(new InvalidationListener(){
                    @Override 
                    public void invalidated (Observable ov)
                    {
                        actualizarTimeSlider();
                    }
                }
                );
                
                sliderTime.valueProperty().addListener(new InvalidationListener(){
                    @Override
                    public void invalidated(Observable ov){
                        if(sliderTime.isValueChanging()){
                            if(duracion!=null)
                                Play.seek(duracion.multiply(sliderTime.getValue()/100));
                        }
                        actualizarTimeSlider();
                        }
                    
                }
                );
                
                
                SliderVolumen.setValue(Play.getVolume()*100);
                SliderVolumen.valueProperty().addListener(new InvalidationListener(){
                @Override
                public void invalidated(Observable ov){
                    Play.setVolume(SliderVolumen.getValue()/100);
                }
            });         
        Play.play();
        pantalla.setMediaPlayer(Play);
        }
        else
            System.out.println("El video no existe \n");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
       }
    }
    protected void actualizarTimeSlider(){
        if(sliderTime!=null){
            Platform.runLater(new Runnable(){
            public void run(){
              Duration tiempoActual=Play.getCurrentTime();
              sliderTime.setDisable(duracion.isUnknown());
              if(!sliderTime.isDisable() && duracion.greaterThan(Duration.ZERO)&& !sliderTime.isValueChanging())
                  sliderTime.setValue(tiempoActual.divide(duracion.toMillis()).toMillis()*100);
            }
        }
            );
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
