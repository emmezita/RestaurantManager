/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelreservacion;

import model.modelusuario.Persona;
import java.util.ArrayList;

/**
 *
 * @author emmez
 */
public class Comensal extends Persona {

    /* Constructor de la clase Comensal con atributos que se extienden de Persona
       Se utiliza para efectos logicos de diferencia con la clase abstracta padre Persona*/
    public Comensal(String cedula, String nombre, String apellido, int edad, char sexo, String ubicacion, String correo, String telefono) {
        super(cedula, nombre, apellido, edad, sexo, ubicacion, correo, telefono);
    }
    
    // Constructor vacio para objeto reutilizable
    public Comensal() {
    }
    
    // Metodo para verificar si ya existe la persona en el sistema comparando su cedula
    public boolean comensalRepetido (ArrayList<Comensal> listaComensales, String cedula) {
        for(Comensal c: listaComensales){
            /* Si se ha encontrado una personaregistrada comparando las cedulas, 
               se devuelve su objeto para utilizarla */ 
            if(c.getCedula().equals(cedula)){
                return true;
            }
        }
        return false;
    } 
    
    // Metodo para buscar un comensal por cedula y devolver su objeto si se ha encontrado
    public Comensal buscarComensal (ArrayList<Comensal> listaComensales, String cedula) {
        for(Comensal c: listaComensales){
            if(c.getCedula().equals(cedula)){
                return c;
            }
        }
        return null;
    } 
    
}
