package metodos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karenrgast
 */



import java.sql.*;

import javax.swing.JOptionPane;
import Pantallas.LogIn;
import Pantallas.EmpleadosP;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;





public class conectar {
    static Statement sentencia=null;
    static ResultSet resultado=null;
    static Connection link=null;


   public static void Conectar(){

         String db = "DB41";
         String url = "jdbc:mysql://localhost?useSSL=false";
         String user = "root";
         String pass = "12503970";

       try{

           Class.forName("org.gjt.mm.mysql.Driver");

           link=DriverManager.getConnection(url, user,pass);
           sentencia=link.createStatement();
           

       }catch(Exception ex){

           JOptionPane.showMessageDialog(null, ex);

       }


       

   }
public int consulta_empleados(){
            String dato;
            dato=EmpleadosP.txtDni.getText();
            int resultado=0;
        try {
            ResultSet rs=sentencia.executeQuery("SELECT Nombre,Apellido1, Apellido2, departamento.NombreDpto, subordinado.NomSubordinado, subordinado.Sexo , subordinado.FechaNac, subordinado.Relacion "
                    + "FROM DB41.empleado "
                    + "INNER JOIN departamento "
                    + "ON empleado.Dno=departamento.NumeroDpto INNER JOIN subordinado ON subordinado.DniEmpleado=empleado.Dni "
                    + "WHERE Dni ='"+dato+"';");
            if(rs.next()){
            resultado=1;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
            
}
       
       
 public int validar_ingreso(){
     
    String usuario = LogIn.txtUsuario.getText();
    String clave = String.valueOf(LogIn.txtContraseña.getPassword());

    int resultado=0;
    
    String SSQL="SELECT * FROM DB41.usuarios WHERE idusuarios='"+usuario+"' AND contraseña=('"+clave+"')";

    
    try {
        ResultSet rs = sentencia.executeQuery(SSQL);

         if(rs.next()){

            resultado=1;

        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);

    }
     

return resultado;

}

 public static ArrayList<String> llenar_comboDepartamento(){
       ArrayList<String> lista= new ArrayList<String>();
       String q= "SELECT * FROM DB41.departamento";
       try{
           resultado= sentencia.executeQuery(q);
       }catch (Exception e){
           
       }
       try{
           while(resultado.next())
               lista.add(resultado.getString("NombreDpto"));
       } catch(Exception e){
           
       }
           
       return lista;
   }
 public static ArrayList<String> llenar_comboLocaliza(){
       ArrayList<String> lista= new ArrayList<String>();
       String q= "SELECT * FROM DB41.localizaciones_dpto";
       try{
           resultado= sentencia.executeQuery(q);
       }catch (Exception e){
           
       }
       
       try{
           while(resultado.next())
               lista.add(resultado.getString("UbicacionDpto"));
              
               
       } catch(Exception e){
           
       }
           
       return lista;
   }
public static ArrayList<String> llenar_comboDirectores(){
       ArrayList<String> lista= new ArrayList<String>();
       String q= "SELECT * FROM DB41.empleado";
       try{
           resultado= sentencia.executeQuery(q);
       }catch (Exception e){
           
       }
       String[] registros = new String[2];
       try{
           while(resultado.next())
               registros[0]=(resultado.getString("Nombre"));
               registros[1]=(resultado.getString("Dni"));
               lista.add(registros[0]);
               
       } catch(Exception e){
           
       }
           
       return lista;
   }
 
}   