/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelreservacion;

import java.util.ArrayList;
import model.modelreservacion.Reservacion;

/**
 *
 * @author Rafael
 */
public class ReservacionGeneral extends Reservacion {
    
    // Atributo de la cedula del comensal que registra una Reservacion General
    private String cedula;
    
    // Constructor de clase Reservacion General con atributos que se extienden de Reservacion
    public ReservacionGeneral(String cedula, String fechaDeReservacion, int numeroAdultos, int numeroNinos, 
                             double costoTotal, boolean pagado, String metodoDePago, int montoAdulto, int montoNino) {
        super(fechaDeReservacion, numeroAdultos, numeroNinos, costoTotal, pagado, metodoDePago, montoAdulto, montoNino);
        this.cedula = cedula;
    }
    
    // Constructor vacio para objetos reutilizables
    public ReservacionGeneral() {
        cedula = "";
    }
    
    // Getters y setters para Reservacion General
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public void modificarReservacionGeneral(String fechaA, String cedula,String fecha, int numeroAdultos, int numeroNinos, 
                             double costoTotal, boolean pagado, String metodoDePago, int montoAdulto, int montoNino, ArrayList<Reservacion> listaReservaciones){
        for(Reservacion r: listaReservaciones){
            if(r instanceof ReservacionGeneral rg){
                if(rg.getFechaDeReservacion().equals(fechaA)){
                    if(rg.getCedula().equals(cedula)){
                        rg.setFechaDeReservacion(fecha);
                        rg.setNumeroAdultos(numeroAdultos);
                        rg.setNumeroNinos(numeroNinos);
                        rg.setCostoTotal(costoTotal);
                        rg.setPagado(pagado);
                        rg.setMetodoDePago(metodoDePago);
                        rg.setMontoAdulto(montoAdulto);
                        rg.setMontoNino(montoNino);
                    }
                }
            }
        }
    }
    
    public void eliminarReservacion(ArrayList<Reservacion> listaReservaciones, String fecha, String clave){
        ReservacionGeneral g = null;
        for(Reservacion r: listaReservaciones){
            if(r instanceof ReservacionGeneral rg){
                if(rg.getFechaDeReservacion().equals(fecha)){
                    if(rg.getCedula().equals(clave)){
                        g = rg;
                    }
                }
            }
        }
        listaReservaciones.remove(g);
    }
}
