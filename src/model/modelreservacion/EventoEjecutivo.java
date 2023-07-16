/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelreservacion;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rafael
 */

public class EventoEjecutivo extends EventoPrivado {
    
    // Atributo de rif de la empresa que registra un Evento Ejecutivo
    private String rif;

    // Constructor de clase Evento Ejecutivo con atributos que se extienden de Evento Privado y Reservacion
    public EventoEjecutivo(String rif, String motivoDeEvento, Date horaInicio, Date horaCierre, 
                          String fechaDeReservacion, int numeroAdultos, int numeroNinos, double costoTotal, 
                          boolean pagado, String metodoDePago, int montoAdulto, int montoNino) {
        super(motivoDeEvento, horaInicio, horaCierre, fechaDeReservacion, numeroAdultos, numeroNinos, costoTotal, 
              pagado, metodoDePago, montoAdulto, montoNino);
        this.rif = rif;
    }
    
    // Constructor vacio para objetos reutilizables
    public EventoEjecutivo() {
    }

    // Getters y setters de Evento Ejecutivo
    
    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }
    
    public void modificarReservacionEjecutiva(String fechaA,String rif, String motivoDeEvento, Date horaInicio, Date horaCierre, 
                          String fecha, int numeroAdultos, int numeroNinos, double costoTotal, 
                          boolean pagado, String metodoDePago, int montoAdulto, int montoNino, ArrayList<Reservacion> listaReservaciones){
        for(Reservacion r: listaReservaciones){
            if(r instanceof EventoEjecutivo e){
                if(e.getFechaDeReservacion().equals(fechaA)){
                    if(e.getRif().equals(rif)){
                        e.setHoraInicio(horaInicio);
                        e.setHoraCierre(horaCierre);
                        e.setMotivoDeEvento(motivoDeEvento);
                        e.setFechaDeReservacion(fecha);
                        e.setNumeroAdultos(numeroAdultos);
                        e.setNumeroNinos(numeroNinos);
                        e.setCostoTotal(costoTotal);
                        e.setPagado(pagado);
                        e.setMetodoDePago(metodoDePago);
                        e.setMontoAdulto(montoAdulto);
                        e.setMontoNino(montoNino);
                    }
                }
            }
        }
        
    }
    
    public void eliminarReservacion(ArrayList<Reservacion> listaReservaciones, String fecha, String clave){
        EventoEjecutivo e = null;
        for(Reservacion r: listaReservaciones){
            if(r instanceof EventoEjecutivo evento){
                if(evento.getFechaDeReservacion().equals(fecha)){
                    if(evento.getRif().equals(clave)){
                        e = evento;
                    }
                }
            }
        }
        listaReservaciones.remove(e);
    }
    
}
