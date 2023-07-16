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
public class EventoFamiliar extends EventoPrivado {
    
    // Atributo de la cedula del comensal que registra un Evento Familiar
    private String cedula;

    // Constructor de clase Evento Familiar con atributos que se extienden de Evento Privado y Reservacion
    public EventoFamiliar(String cedula, String motivoDeEvento, Date horaInicio, Date horaCierre, 
                          String fechaDeReservacion, int numeroAdultos, int numeroNinos, double costoTotal, 
                          boolean pagado, String metodoDePago, int montoAdulto, int montoNino) {
        super(motivoDeEvento, horaInicio, horaCierre, fechaDeReservacion, numeroAdultos, numeroNinos, 
              costoTotal, pagado, metodoDePago, montoAdulto, montoNino);
        this.cedula = cedula;
    }

    // Constructor vacio para objetos reutilizables
    public EventoFamiliar() {
    }

    // Getters y setters de Evento Familiar
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public void modificarReservacionFamiliar(String fechaA,String cedula, String motivoDeEvento, Date horaInicio, Date horaCierre, 
                          String fecha, int numeroAdultos, int numeroNinos, double costoTotal, 
                          boolean pagado, String metodoDePago, int montoAdulto, int montoNino, ArrayList<Reservacion> listaReservaciones){
        for(Reservacion r: listaReservaciones){
            if(r instanceof EventoFamiliar ef){
                if(ef.getFechaDeReservacion().equals(fechaA)){
                    if(ef.getCedula().equals(cedula)){
                        ef.setHoraInicio(horaInicio);
                        ef.setHoraCierre(horaCierre);
                        ef.setMotivoDeEvento(motivoDeEvento);
                        ef.setFechaDeReservacion(fecha);
                        ef.setNumeroAdultos(numeroAdultos);
                        ef.setNumeroNinos(numeroNinos);
                        ef.setCostoTotal(costoTotal);
                        ef.setPagado(pagado);
                        ef.setMetodoDePago(metodoDePago);
                        ef.setMontoAdulto(montoAdulto);
                        ef.setMontoNino(montoNino);
                    }
                }
            }
        }
        
    }
    
    public void eliminarReservacion(ArrayList<Reservacion> listaReservaciones, String fecha, String clave){
        EventoFamiliar e = null;
        for(Reservacion r: listaReservaciones){
            if(r instanceof EventoFamiliar evento){
                if(evento.getFechaDeReservacion().equals(fecha)){
                    if(evento.getCedula().equals(clave)){
                        e = evento;
                    }
                }
            }
        }
        listaReservaciones.remove(e);
    }
}
