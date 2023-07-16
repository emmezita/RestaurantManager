/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelreservacion;

import java.util.Date;

/**
 *
 * @author Rafael
 */
public class EventoPrivado extends Reservacion {
    
    // Atributos de clase Evento Privado
    private String motivoDeEvento;
    private Date horaInicio;
    private Date horaCierre;

    // Constructor de Evento Privado con atributos que se extienden de Reservacion
    public EventoPrivado(String motivoDeEvento, Date horaInicio, Date horaCierre, String fechaDeReservacion, 
                         int numeroAdultos, int numeroNinos, double costoTotal, boolean pagado, String metodoDePago, 
                         int montoAdulto, int montoNino) {
        super(fechaDeReservacion, numeroAdultos, numeroNinos, costoTotal, pagado, metodoDePago, montoAdulto, montoNino);
        this.motivoDeEvento = motivoDeEvento;
        this.horaInicio = horaInicio;
        this.horaCierre = horaCierre;
    }
    
    // Constructor vacio para objetos reutilizables
    public EventoPrivado() {
        motivoDeEvento = "";
    }
    
    // Getters y setters para Evento Privado
    
    public String getMotivoDeEvento() {
        return motivoDeEvento;
    }

    public void setMotivoDeEvento(String motivoDeEvento) {
        this.motivoDeEvento = motivoDeEvento;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }
    
    
    
}
