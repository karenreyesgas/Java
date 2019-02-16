/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exame;

/**
 *
 * @author Karenrgast
 */
public class Proffeseur extends Personne {
    private int sueldo;
    private int heuresTravail;
    private int pago;
    public void sueldo_quincenal(){
        pago=17;
        heuresTravail=8;
        sueldo=(pago*heuresTravail)*15;
        System.out.println(sueldo);
        
    }
}
